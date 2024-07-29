package com.ada.ecosystem.core.v1.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseCryptoUtil {

    public static ResponseEntity<Map<String, Object>> createSuccessResponse(Map<String, ?> data, String message, String process) {
        Map<String, Object> response = new HashMap<>();
        response.put("codeResponse", HttpStatus.OK.value());
        response.put("messageResponse", message);
        response.put(process, data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static ResponseEntity<Map<String, Object>> createErrorResponse(HttpStatus status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("codeResponse", status.value());
        response.put("messageResponse", message);
        return new ResponseEntity<>(response, status);
    }
}

