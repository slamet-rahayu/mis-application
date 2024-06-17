package com.example.springboot.modules.report.services;

import com.example.springboot.modules.report.ReportEntity;
import com.example.springboot.modules.report.dto.ReportSummaryDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReportServiceInterface {
    public List<ReportEntity> getAllReport();
    public List<ReportSummaryDTO> getListReportSummary();

    public Integer saveReport(List<ReportEntity> reports);
}
