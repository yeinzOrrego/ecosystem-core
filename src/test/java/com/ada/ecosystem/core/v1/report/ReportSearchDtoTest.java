package com.ada.ecosystem.core.v1.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReportSearchDtoTest {

    private ReportSearchDto reportSearchDto;

    @BeforeEach
    public void setUp() {
        reportSearchDto = new ReportSearchDto();
    }

    @Test
    public void testDefaultConstructor() {
        // Verifica que el constructor por defecto inicializa codigoMempresa
        assertEquals("9999999999", reportSearchDto.getCodigoMempresa());
        assertNull(reportSearchDto.getReportCode());
        assertNull(reportSearchDto.getClientCode());
    }

    @Test
    public void testSetAndGetReportCode() {
        reportSearchDto.setReportCode("REPORT123");
        assertEquals("REPORT123", reportSearchDto.getReportCode());
    }

    @Test
    public void testSetAndGetClientCode() {
        reportSearchDto.setClientCode("CLIENT456");
        assertEquals("CLIENT456", reportSearchDto.getClientCode());
    }

    @Test
    public void testSetAndGetCodigoMempresa() {
        reportSearchDto.setCodigoMempresa("1234567890");
        assertEquals("1234567890", reportSearchDto.getCodigoMempresa());
    }

    @Test
    public void testEqualsAndHashCode() {
        ReportSearchDto dto1 = new ReportSearchDto();
        ReportSearchDto dto2 = new ReportSearchDto();
        dto1.setReportCode("REPORT123");
        dto2.setReportCode("REPORT123");
        dto1.setClientCode("CLIENT456");
        dto2.setClientCode("CLIENT456");
        dto1.setCodigoMempresa("1234567890");
        dto2.setCodigoMempresa("1234567890");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testToString() {
        reportSearchDto.setReportCode("REPORT123");
        reportSearchDto.setClientCode("CLIENT456");
        reportSearchDto.setCodigoMempresa("1234567890");

        String expectedToString = "ReportSearchDto(reportCode=REPORT123, clientCode=CLIENT456, codigoMempresa=1234567890)";
        assertEquals(expectedToString, reportSearchDto.toString());
    }
}
