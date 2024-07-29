package com.ada.ecosystem.core.v1.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

import org.junit.jupiter.api.Test;

import com.ada.ecosystem.core.v1.kafka.KafkaMessage;
import com.ada.ecosystem.core.v1.kafka.Status;
import com.ada.ecosystem.core.v1.kafka.TaskSend;
import com.ada.ecosystem.core.v1.kafka.TaskStatus;
import com.ada.ecosystem.core.v1.response.dto.EcosystemSuccessDTO;
import com.ada.ecosystem.core.v1.response.dto.EcosystemSuccessResponse;

import jakarta.servlet.http.HttpServletRequest;

class EcosystemUtilitiesTest {

    @Test
    void testGetFormattedText() {
        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setUuid(UUID.randomUUID().toString());
        kafkaMessage.setMessage("Test Message");
    }

    @Test
    void testConvertFromUtilDate() {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = EcosystemUtilities.convertFromUtilDate(utilDate);
        assertEquals(utilDate.getTime(), sqlDate.getTime());
    }

    @Test
    void testConvertFromSqlDate() {
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        java.util.Date utilDate = EcosystemUtilities.convertFromSqlDate(sqlDate);
        assertEquals(sqlDate.getTime(), utilDate.getTime());
    }

    @Test
    void testGetStringFromSHA256() throws NoSuchAlgorithmException {
        String input = "test";
        String sha256Hex = EcosystemUtilities.getStringFromSHA256(input);
        assertNotNull(sha256Hex);
        assertEquals(64, sha256Hex.length()); // SHA-256 produces a 64-char hex string
    }

    @Test
    void testPackEntry() throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "Test Content".getBytes());
        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(Files.createTempFile("test", ".zip")))) {
            EcosystemUtilities.packEntry(tempFile, zos, tempFile);
        }
    }

    @Test
    void testGetMessageOnlineService() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getMethod()).thenReturn("GET");
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        EcosystemUtilities.setApplicationTitle("Test Service");
        EcosystemUtilities.setApplicationVersion("1.0.0");

        String message = EcosystemUtilities.getMessageOnlineService(request);
        assertTrue(message.contains("Service Test Service status: Online!!!"));
        assertTrue(message.contains("Version Release: 1.0.0"));
        assertTrue(message.contains("Method: GET"));
        assertTrue(message.contains("Ip: 127.0.0.1"));
    }

    @Test
    void testCreateDefaultTaskProcess() {
        TaskSend taskSend = EcosystemUtilities.createDefaultTaskProcess("topic", "taskId", "taskName");
        assertNotNull(taskSend);
        assertEquals("topic", taskSend.getTopicName());
        assertEquals("CREATE PROCESS", taskSend.getKey());
        assertNotNull(taskSend.getValue());
        assertTrue(taskSend.getValue() instanceof TaskStatus);
    }

    @Test
    void testCreateDefaultTaskProcessWithKey() {
        TaskSend taskSend = EcosystemUtilities.createDefaultTaskProcess("topic", "key", "taskId", "taskName");
        assertNotNull(taskSend);
        assertEquals("topic", taskSend.getTopicName());
        assertEquals("key", taskSend.getKey());
        assertNotNull(taskSend.getValue());
        assertTrue(taskSend.getValue() instanceof TaskStatus);
    }

    @Test
    void testGetTaskStatus() {
        TaskStatus taskStatus = EcosystemUtilities.getTaskStatus("taskId", "taskName", Status.SUBMITTED, 50.0f);
        assertNotNull(taskStatus);
        assertEquals("taskId", taskStatus.getTaskId());
        assertEquals("taskName", taskStatus.getTaskName());
        assertEquals(Status.SUBMITTED, taskStatus.getStatus());
        assertEquals(50.0f, taskStatus.getPercentageComplete());
    }

    @Test
    void testGetDateWithoutTimeUsingFormat() throws ParseException {
        Date date = EcosystemUtilities.getDateWithoutTimeUsingFormat();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(date);
        assertEquals(formattedDate, formatter.format(new Date()));
    }

    @Test
    void testGetNumero() {
        String numero = EcosystemUtilities.getNumero();
        assertNotNull(numero);
        assertEquals(14, numero.length()); // "yyyyMMddHHmmss" format
    }

    @Test
    void testGetInternalClientResponse() {
    	EcosystemSuccessDTO<String> processResult = new EcosystemSuccessDTO<>();
        EcosystemSuccessResponse<String> response = new EcosystemSuccessResponse<>();
        response.setResponse(processResult);
    }


    @Test
    void testGetSimpleDateFormatVigencia() {
        Date date = new Date();
        String vigencia = EcosystemUtilities.getSimpleDateFormatVigencia(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        assertEquals(formatter.format(date), vigencia);
    }

    @Test
    void testGetSimpleDateFormatPeriodo() {
        Date date = new Date();
        String periodo = EcosystemUtilities.getSimpleDateFormatPeriodo(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        assertEquals(formatter.format(date), periodo);
    }

    @Test
    void testInvalidString() {
        assertTrue(EcosystemUtilities.invalidString(null));
        assertTrue(EcosystemUtilities.invalidString(""));
        assertFalse(EcosystemUtilities.invalidString("test"));
    }

    @Test
    void testIsNumeric() {
        assertTrue(EcosystemUtilities.isNumeric("123456"));
        assertFalse(EcosystemUtilities.isNumeric("123abc"));
    }

    @Test
    void testGetPercentageComplete() {
        Float result = EcosystemUtilities.getPercentageComplete(200.0f, 50.0f);
        assertEquals(25.0f, result);
    }

}
