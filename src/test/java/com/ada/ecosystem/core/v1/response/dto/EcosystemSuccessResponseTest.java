package com.ada.ecosystem.core.v1.response.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class EcosystemSuccessResponseTest {

    @Test
    public void testDefaultConstructor() {
        EcosystemSuccessResponse<String> response = new EcosystemSuccessResponse<>();
        Assertions.assertNull(response.getResponse());
    }

    @Test
    public void testConstructorWithObject() {
        List<String> body = Arrays.asList("item1", "item2");
        EcosystemSuccessResponse<List<String>> response = new EcosystemSuccessResponse<>(body);
        
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertEquals(body, response.getResponse().getBody());
        Assertions.assertEquals(2, response.getResponse().getLength()); // Length should be computed from the body
    }

    @Test
    public void testConstructorWithObjectAndMessage() {
        List<String> body = Arrays.asList("item1", "item2");
        EcosystemSuccessResponse<List<String>> response = new EcosystemSuccessResponse<>(body, "Success");
        
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertEquals(body, response.getResponse().getBody());
        Assertions.assertEquals(2, response.getResponse().getLength()); // Length should be computed from the body
        Assertions.assertEquals("Success", response.getResponse().getMessage());
    }

    @Test
    public void testConstructorWithObjectLengthAndMessage() {
        List<String> body = Arrays.asList("item1", "item2");
        EcosystemSuccessResponse<List<String>> response = new EcosystemSuccessResponse<>(body, 10, "Success");
        
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertEquals(body, response.getResponse().getBody());
        Assertions.assertEquals(10, response.getResponse().getLength()); // Length should be set to the provided value
        Assertions.assertEquals("Success", response.getResponse().getMessage());
    }

    @Test
    public void testConstructorWithObjectAndLength() {
        List<String> body = Arrays.asList("item1", "item2");
        EcosystemSuccessResponse<List<String>> response = new EcosystemSuccessResponse<>(body, 10);
        
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertEquals(body, response.getResponse().getBody());
        Assertions.assertEquals(10, response.getResponse().getLength()); // Length should be set to the provided value
    }

    @Test
    public void testSetResponse() {
        EcosystemSuccessDTO<String> dto = new EcosystemSuccessDTO<>("Body", 5, "Message");
        EcosystemSuccessResponse<String> response = new EcosystemSuccessResponse<>();
        response.setResponse(dto);
        
        Assertions.assertEquals(dto, response.getResponse());
        Assertions.assertEquals("Body", response.getResponse().getBody());
        Assertions.assertEquals(5, response.getResponse().getLength());
        Assertions.assertEquals("Message", response.getResponse().getMessage());
    }
}
