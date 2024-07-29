package com.ada.ecosystem.core.v1.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReportConfigTest {
    
    private ReportConfig reportConfig;

    @BeforeEach
    public void setUp() {
        reportConfig = new ReportConfig();
    }

    @Test
    public void testDefaultConstructor() {
        // Verifica que los valores por defecto sean correctos
        assertNull(reportConfig.getConnectCode());
        assertNull(reportConfig.getReportPathCompile());
        assertNull(reportConfig.getReportName());
        assertNull(reportConfig.getParameters());
        assertNull(reportConfig.getReporteBase64());
    }

    @Test
    public void testSetAndGetConnectCode() {
        reportConfig.setConnectCode("ABC123");
        assertEquals("ABC123", reportConfig.getConnectCode());
    }

    @Test
    public void testSetAndGetReportPathCompile() {
        reportConfig.setReportPathCompile("/reports");
        assertEquals("/reports", reportConfig.getReportPathCompile());
    }

    @Test
    public void testSetAndGetReportName() {
        reportConfig.setReportName("testReport");
        assertEquals("testReport", reportConfig.getReportName());
    }

    @Test
    public void testSetAndGetParameters() {
        Map<String, Object> params = new HashMap<>();
        params.put("param1", "value1");
        reportConfig.setParameters(params);
        assertEquals(params, reportConfig.getParameters());
    }

    @Test
    public void testSetAndGetReporteBase64() {
        reportConfig.setReporteBase64("dGVzdERhdGE=");
        assertEquals("dGVzdERhdGE=", reportConfig.getReporteBase64());
    }

    @Test
    public void testEqualsAndHashCode() {
        ReportConfig reportConfig1 = new ReportConfig();
        ReportConfig reportConfig2 = new ReportConfig();
        reportConfig1.setConnectCode("ABC123");
        reportConfig2.setConnectCode("ABC123");
        
        assertEquals(reportConfig1, reportConfig2);
        assertEquals(reportConfig1.hashCode(), reportConfig2.hashCode());
    }

    @Test
    public void testToString() {
        reportConfig.setConnectCode("ABC123");
        reportConfig.setReportPathCompile("/reports");
        reportConfig.setReportName("testReport");
        
        String expectedToString = "ReportConfig(connectCode=ABC123, reportPathCompile=/reports, reportName=testReport, parameters=null, reporteBase64=null)";
        assertEquals(expectedToString, reportConfig.toString());
    }
}
