package com.example.springboot.modules.report.services.impl;

import com.example.springboot.modules.report.ReportEntity;
import com.example.springboot.modules.report.ReportRepository;
import com.example.springboot.modules.report.dto.ReportListSummaryDTO;
import com.example.springboot.modules.report.dto.ReportSummaryDTO;
import com.example.springboot.modules.report.services.ReportServiceInterface;
import jakarta.transaction.Transactional;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportServiceInterface {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<ReportEntity> getAllReport() {
        return reportRepository.findAll();
    }

    @Override
    public List<ReportSummaryDTO> getListReportSummary() {
        return reportRepository.getListReportSummary();
    }

    @Value("${api.app.integration}")
    private String app2Url;

    @Override
    @Transactional
    public Integer saveReport(List<ReportEntity> reports) {
        reportRepository.saveAll(reports);
        return reports.size();
    }

    public List<ReportEntity> readExcel(MultipartFile file) throws IOException {
        List<ReportEntity> reports = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip header row
                    continue;
                }
                ReportEntity report = new ReportEntity();

                for (Cell cell : row) {
                    switch (cell.getColumnIndex()) {
                        case 0:
                            report.setLob(cell.getStringCellValue());
                            break;
                        case 1:
                            report.setCauseOfClaim(cell.getStringCellValue());
                            break;
                        case 2:
                            report.setClaimsBurden((long) cell.getNumericCellValue());
                            break;
                        case 3:
                            report.setDate(cell.getDateCellValue());
                            break;
                        default:
                            break;
                    }
                }
                reports.add(report);
            }
        }

        return reports;
    }

    @Ignore
    public void uploadToIntegration(List<ReportEntity> reports) {
        List<ReportListSummaryDTO> reportListSummaryDTOList = new ArrayList<>();

        Map<String, Map<String, Map<Date, Long>>> groupedByLobAndCoc = reports.stream()
                .collect(Collectors.groupingBy(
                        ReportEntity::getLob,
                        Collectors.groupingBy(
                                ReportEntity::getCauseOfClaim,
                                Collectors.groupingBy(
                                        ReportEntity::getDate,
                                        Collectors.summingLong(ReportEntity::getClaimsBurden)
                                )
                        )
                ));

        for (Map.Entry<String, Map<String, Map<Date, Long>>> lobEntry : groupedByLobAndCoc.entrySet()) {
            String lob = lobEntry.getKey();
            Map<String, Map<Date, Long>> lobMap = lobEntry.getValue();
            for (Map.Entry<String, Map<Date, Long>> causeEntry : lobMap.entrySet()) {
                String causeOfClaim = causeEntry.getKey();
                Map<Date, Long> dateMap = causeEntry.getValue();
                for (Map.Entry<Date, Long> dateEntry : dateMap.entrySet()) {
                    Date fDate = dateEntry.getKey();
                    Long totalClaimsBurden = dateEntry.getValue();
                    ReportListSummaryDTO re = new ReportListSummaryDTO();

                    re.setLob(lob);
                    re.setDate(fDate);
                    re.setTotalClaimsBurden(totalClaimsBurden);
                    re.setCauseOfClaim(causeOfClaim);

                    reportListSummaryDTOList.add(re);

                }
            }
        }

        restTemplate.postForObject(app2Url, reportListSummaryDTOList, ReportEntity.class);

    }

 }
