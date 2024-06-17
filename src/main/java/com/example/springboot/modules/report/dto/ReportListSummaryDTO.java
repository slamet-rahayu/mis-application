package com.example.springboot.modules.report.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReportListSummaryDTO {
    private String lob;
    private String causeOfClaim;
    private Long totalClaimsBurden;
    private Date date;
}
