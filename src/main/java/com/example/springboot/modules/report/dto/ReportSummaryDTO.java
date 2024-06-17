package com.example.springboot.modules.report.dto;

public interface ReportSummaryDTO {
    String getLob();
    String getCauseOfClaim();
    Long getTotalCount();
    Long getTotalClaimsBurden();
}
