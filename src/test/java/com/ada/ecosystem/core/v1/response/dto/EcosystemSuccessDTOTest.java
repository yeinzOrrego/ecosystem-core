package com.ada.ecosystem.core.v1.response.dto;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EcosystemSuccessDTOTest {

    @Test
    public void testDefaultConstructor() {
        EcosystemSuccessDTO<String> dto = new EcosystemSuccessDTO<>();
        Assertions.assertNull(dto.getBody());
        Assertions.assertEquals(1, dto.getLength());
        Assertions.assertNull(dto.getMessage());
        Assertions.assertNull(dto.getLogProcess());
    }

    @Test
    public void testConstructorWithBodyLengthAndMessage() {
        List<String> body = Arrays.asList("item1", "item2");
        EcosystemSuccessDTO<List<String>> dto = new EcosystemSuccessDTO<>(body, 2, "Success");
        
        Assertions.assertEquals(body, dto.getBody());
        Assertions.assertEquals(2, dto.getLength());
        Assertions.assertEquals("Success", dto.getMessage());
    }

    @Test
    public void testConstructorWithBodyAndMessage() {
        List<String> body = Arrays.asList("item1", "item2");
        EcosystemSuccessDTO<List<String>> dto = new EcosystemSuccessDTO<>(body, "Success");
        
        Assertions.assertEquals(body, dto.getBody());
        Assertions.assertEquals(2, dto.getLength()); // Length is computed from the body
        Assertions.assertEquals("Success", dto.getMessage());
    }

    @Test
    public void testConstructorWithBodyAndLength() {
        List<String> body = Arrays.asList("item1", "item2");
        EcosystemSuccessDTO<List<String>> dto = new EcosystemSuccessDTO<>(body, 10);
        
        Assertions.assertEquals(body, dto.getBody());
        Assertions.assertEquals(10, dto.getLength());
        Assertions.assertNull(dto.getMessage());
    }

    @Test
    public void testConstructorWithBody() {
        List<String> body = Arrays.asList("item1", "item2");
        EcosystemSuccessDTO<List<String>> dto = new EcosystemSuccessDTO<>(body);
        
        Assertions.assertEquals(body, dto.getBody());
        Assertions.assertEquals(2, dto.getLength()); // Length is computed from the body
        Assertions.assertNull(dto.getMessage());
    }

    @Test
    public void testSettersAndGetters() {
        EcosystemSuccessDTO<String> dto = new EcosystemSuccessDTO<>();
        
        dto.setBody("Body");
        dto.setLength(5);
        dto.setMessage("Message");
        dto.setLogProcess(new StringBuilder("Log"));

        Assertions.assertEquals("Body", dto.getBody());
        Assertions.assertEquals(5, dto.getLength());
        Assertions.assertEquals("Message", dto.getMessage());
        Assertions.assertEquals("Log", dto.getLogProcess().toString());
    }
}
