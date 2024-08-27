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

/**
 * EcosystemCryptoValue class provides methods for encrypting and decrypting data using AES encryption.
 * 
 * <p>
 * Project: Migracion SICOF - ADA
 * Year: 2024
 * </p>
 * 
 * <p>
 * This service handles the encryption and decryption of values within a map, ensuring that sensitive information is secured.
 * Additionally, it provides utility methods to create standardized success and error responses.
 * </p>
 * 
 * <p>
 * The methods in this class utilize a static secret key and an IV (Initialization Vector) provided in the request.
 * </p>
 */
@Service
public class EcosystemCryptoValue {
	
    /**
     * Encrypts the values in the provided request map, excluding the IV parameter.
     * The IV is included in the encrypted response.
     *
     * @param request a map containing the data to be encrypted, with the IV included as a parameter.
     * @return a map containing the encrypted values along with the encoded IV.
     * @throws Exception if an error occurs during encryption.
     */
    public Map<String, String> encryptValues(Map<String, Object> request) throws Exception {
        SecretKey key = CryptoUtil.getKeyFromStaticString();
        IvParameterSpec iv = CryptoUtil.getIvFromRequest(request);
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

    /**
     * Decrypts the values in the provided request map using the provided IV.
     *
     * @param request a map containing the encrypted data along with the IV.
     * @return a map containing the decrypted values.
     * @throws Exception if an error occurs during decryption.
     * @throws IllegalArgumentException if the IV is not provided in the request.
     */
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

    /**
     * Creates a standardized success response.
     *
     * @param data    the data to include in the response.
     * @param message a message describing the success.
     * @param process a string identifying the process that was successful.
     * @return a ResponseEntity containing the success response.
     */
    public ResponseEntity<Map<String, Object>> createSuccessResponse(Map<String, ?> data, String message, String process) {
        return ResponseCryptoUtil.createSuccessResponse(data, message, process);
    }

    /**
     * Creates a standardized error response.
     *
     * @param status  the HTTP status code to return.
     * @param message a message describing the error.
     * @return a ResponseEntity containing the error response.
     */
    public ResponseEntity<Map<String, Object>> createErrorResponse(HttpStatus status, String message) {
        return ResponseCryptoUtil.createErrorResponse(status, message);
    }
}
