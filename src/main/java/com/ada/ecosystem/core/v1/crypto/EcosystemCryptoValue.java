package com.ada.ecosystem.core.v1.crypto;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ada.ecosystem.core.v1.utility.CryptoUtil;
import com.ada.ecosystem.core.v1.utility.ResponseCryptoUtil;

@Service
public class EcosystemCryptoValue {

    private String logHeader = "[EcosystemCryptoValue-lib]";

    public Map<String, String> encryptValues(Map<String, Object> request) throws Exception {
        System.out.println(logHeader + "[encryptValues] ingresa al metodo");
        SecretKey key = CryptoUtil.getKeyFromStaticString();
        System.out.println(logHeader + "[encryptValues] vuelve de generar el key " + key);
        IvParameterSpec iv = CryptoUtil.getIvFromRequest(request);
        System.out.println(logHeader + "[encryptValues] vuelve de generar el iv " + iv);
        Map<String, String> encryptedData = new HashMap<>();
        for (Map.Entry<String, Object> entry : request.entrySet()) {
            if (!entry.getKey().equals("iv")) {
                String originalValue = entry.getValue().toString();
                String encryptedValue = CryptoUtil.encrypt(originalValue, key, iv);
                encryptedData.put(entry.getKey(), encryptedValue);
            }
        }

        encryptedData.put("iv", Base64.getEncoder().encodeToString(iv.getIV()));
        return encryptedData;
    }

    public Map<String, String> decryptValues(Map<String, String> request) throws Exception {
        if (!request.containsKey("iv")) {
            throw new IllegalArgumentException("El parámetro 'iv' es obligatorio.");
        }

        SecretKey key = CryptoUtil.getKeyFromStaticString();
        IvParameterSpec iv = CryptoUtil.getIvFromRequest(request);

        Map<String, String> decryptedData = new HashMap<>();
        for (Map.Entry<String, String> entry : request.entrySet()) {
            if (!entry.getKey().equals("iv")) {
                String encryptedValue = entry.getValue();
                String decryptedValue = CryptoUtil.decrypt(encryptedValue, key, iv);
                decryptedData.put(entry.getKey(), decryptedValue);
            }
        }

        return decryptedData;
    }

    public ResponseEntity<Map<String, Object>> createSuccessResponse(Map<String, ?> data, String message, String process) {
        return ResponseCryptoUtil.createSuccessResponse(data, message, process);
    }

    public ResponseEntity<Map<String, Object>> createErrorResponse(HttpStatus status, String message) {
        return ResponseCryptoUtil.createErrorResponse(status, message);
    }
}
