package com.example.springboot.modules.report;

import com.example.springboot.modules.report.dto.ReportSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

    @Query(value = "SELECT " +
            "CASE " +
            "WHEN GROUPING(r.lob) = 1 AND GROUPING(r.cause_of_claim) = 0 THEN 'Subtotal ' || r.lob " +
            "WHEN GROUPING(r.lob) = 1 AND GROUPING(r.cause_of_claim) = 1 THEN 'Total' " +
            "ELSE r.lob " +
            "END AS lob, " +
            "CASE " +
            "WHEN GROUPING(r.lob) = 1 AND GROUPING(r.cause_of_claim) = 0 THEN '' " +
            "WHEN GROUPING(r.lob) = 1 AND GROUPING(r.cause_of_claim) = 1 THEN '' " +
            "ELSE r.cause_of_claim " +
            "END AS causeOfClaim, " +
            "COUNT(*) AS totalCount, " +
            "SUM(r.claims_burden) AS totalClaimsBurden " +
            "FROM report r " +
            "GROUP BY GROUPING SETS ((r.lob, r.cause_of_claim), (r.lob), ()) " +
            "ORDER BY GROUPING(r.lob), r.lob, GROUPING(r.cause_of_claim), r.cause_of_claim", nativeQuery = true)
    List<ReportSummaryDTO> getListReportSummary();
}
