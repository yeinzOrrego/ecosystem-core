package com.ada.ecosystem.core.v1.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseCryptoUtilTest {

	  @Test
	    public void testCreateSuccessResponse() {
	        // Datos de prueba
	        Map<String, String> data = new HashMap<>();
	        data.put("key", "value");
	        String message = "Operation successful";
	        String process = "dataProcess";

	        // Llamada al método
	        ResponseEntity<Map<String, Object>> responseEntity = ResponseCryptoUtil.createSuccessResponse(data, message, process);

	        // Verificaciones
	        assertNotNull(responseEntity);
	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertNotNull(responseEntity.getBody());
	        
	        Map<String, Object> body = responseEntity.getBody();
	        assertEquals(HttpStatus.OK.value(), body.get("codeResponse"));
	        assertEquals(message, body.get("messageResponse"));
	        assertEquals(data, body.get(process));
	    }

	    @Test
	    public void testCreateErrorResponse() {
	        // Datos de prueba
	        HttpStatus status = HttpStatus.BAD_REQUEST;
	        String message = "Invalid request";

	        // Llamada al método
	        ResponseEntity<Map<String, Object>> responseEntity = ResponseCryptoUtil.createErrorResponse(status, message);

	        // Verificaciones
	        assertNotNull(responseEntity);
	        assertEquals(status, responseEntity.getStatusCode());
	        assertNotNull(responseEntity.getBody());
	        
	        Map<String, Object> body = responseEntity.getBody();
	        assertEquals(status.value(), body.get("codeResponse"));
	        assertEquals(message, body.get("messageResponse"));
	    }
}

