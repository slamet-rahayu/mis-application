package com.example.springboot;

import com.example.springboot.modules.report.ReportEntity;
import com.example.springboot.modules.report.ReportRepository;
import com.example.springboot.modules.report.services.impl.ReportServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportServiceImpl reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveReport() {
        List<ReportEntity> reportEntities = new ArrayList<>();
        ReportEntity report = new ReportEntity();
        report.setLob("KUR");
        report.setCauseOfClaim("Kebakaran");
        report.setClaimsBurden(100000L);
        report.setDate(new Date());

        reportEntities.add(report);

        reportService.saveReport(reportEntities);

        ArgumentCaptor<List<ReportEntity>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        verify(reportRepository, times(1)).saveAll(argumentCaptor.capture());

    }

}
