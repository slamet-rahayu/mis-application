package com.example.springboot.modules.report;

import com.example.springboot.dto.ApiResponse;
import com.example.springboot.modules.logs.LogsService;
import com.example.springboot.modules.report.dto.ReportSummaryDTO;
import com.example.springboot.modules.report.services.impl.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportServiceImpl reportService;

    @Autowired
    private LogsService logsService;

    @GetMapping
    public List<ReportEntity> getAllReport() {
        return reportService.getAllReport();
    }

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse> getListReportSummary() {
        ApiResponse apiResponse = new ApiResponse();
        List<ReportSummaryDTO> reportSummaryDTOS = reportService.getListReportSummary();
        apiResponse.setData(reportSummaryDTOS);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/upload-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            List<ReportEntity> reports = reportService.readExcel(file);
            Integer rowTotal = reportService.saveReport(reports);
            reportService.uploadToIntegration(reports);
            logsService.logInfo("insert report from excel: "+rowTotal+" success");
            logsService.logInfo("upload to integration: "+rowTotal+"success");
            ApiResponse apiResponse = new ApiResponse();
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse apiResponse = new ApiResponse();
            logsService.logError("insert report from excel: error", e);
            logsService.logError("uploading to integration: failed", e);
            apiResponse.setMessage("error");
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
