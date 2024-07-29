package com.ada.ecosystem.core.v1.crypto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ada.ecosystem.core.v1.utility.CryptoUtil;

public class EcosystemCryptoValueTest {

	   private EcosystemCryptoValue ecosystemCryptoValue;

	    @BeforeEach
	    void setUp() {
	        ecosystemCryptoValue = new EcosystemCryptoValue();
	    }

	    @Test
	    void testEncryptValues() throws Exception {
	        Map<String, Object> request = new HashMap<>();
	        request.put("testKey", "testValue");
	        request.put("iv", "PUMkdx7DJKGoaDeJIMFaaw==");

	        Map<String, String> encryptedData = ecosystemCryptoValue.encryptValues(request);

	        assertNotNull(encryptedData);
	        assertTrue(encryptedData.containsKey("testKey"));
	        assertTrue(encryptedData.containsKey("iv"));
	    }

	    @Test
	    void testDecryptValues() throws Exception {
	        Map<String, String> request = new HashMap<>();
	        request.put("testKey", "testValue");

	        assertThrows(IllegalArgumentException.class, () -> {
	            ecosystemCryptoValue.decryptValues(request);
	        });

	    }

	    @Test
	    void testCreateSuccessResponse() {
	        Map<String, String> data = new HashMap<>();
	        data.put("key", "value");
	        ResponseEntity<Map<String, Object>> response = ecosystemCryptoValue.createSuccessResponse(data, "Success", "process");

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("Success", response.getBody().get("messageResponse"));
	    }

	    @Test
	    void testCreateErrorResponse() {
	        ResponseEntity<Map<String, Object>> response = ecosystemCryptoValue.createErrorResponse(HttpStatus.BAD_REQUEST, "Error occurred");

	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	        assertEquals("Error occurred", response.getBody().get("messageResponse"));
	    }
	    
	    @Test
	    public void testDecryptValuesThrowsExceptionWhenIvMissing() {
	        Map<String, String> request = new HashMap<>();
	        request.put("password", "XXBFxd0q4oXj0mabx1lwg5wphu38ZskuPI9f1Ta1sES4JTpYHs1j1lhZ4X276jj3GEIWRQklV04b+9oa8RStPcbpCNq3hvsbt4oCJPhiTJc=");
	        request.put("email", "XXBFxd0q4oXj0mabx1lwg1KCK6PrOJim9AXboCuuYto=");
	        request.put("username", "XXBFxd0q4oXj0mabx1lwg/iNnDVbkHknWmad/kFLSEw=");
	        request.put("token", "XXBFxd0q4oXj0mabx1lwgxoC+sdSFh53SO51IrxQqAgLL9xHmytc1xTH9bLStOihrGOgkfXbRa7XxMqoNx673tRMnBSBjs3DiM3jSz2XEWQsKymVZe5TXBlW7p0UsWyYS3SwQ3iTT0Y/mPDdR7IoMxTyBTa7z+G82B+x7PwuPb6Lcgqo1cx18FUOWry5hDTSnj07GpeX/IhzZHdz77irYij65reB79g/Wh27e3M8MTkB0Y0y9Eo0DO8jaAhY4bIL");

	        Executable executable = () -> ecosystemCryptoValue.decryptValues(request);
	        assertThrows(IllegalArgumentException.class, executable, "Expected decryptValues to throw IllegalArgumentException when 'iv' is missing");
	    }
	    
	    @Test
	    public void testDecryptValuesSuccessfully() throws Exception {
	        // Mockear dependencias
	        SecretKey mockKey = mock(SecretKey.class);
	        IvParameterSpec mockIv = mock(IvParameterSpec.class);

	        // Suponemos que CryptoUtil.getKeyFromStaticString() y CryptoUtil.getIvFromRequest() son métodos estáticos
	        try (var mockStaticCryptoUtil = Mockito.mockStatic(CryptoUtil.class)) {
	            mockStaticCryptoUtil.when(CryptoUtil::getKeyFromStaticString).thenReturn(mockKey);
	            mockStaticCryptoUtil.when(() -> CryptoUtil.getIvFromRequest(anyMap())).thenReturn(mockIv);

	            // Suponemos que el valor desencriptado será el siguiente
	            String decryptedValue = "decryptedValue";
	            mockStaticCryptoUtil.when(() -> CryptoUtil.decrypt(anyString(), eq(mockKey), eq(mockIv))).thenReturn(decryptedValue);

	            // Datos de entrada para el método
	            Map<String, String> request = new HashMap<>();
	            request.put("password", "XXBFxd0q4oXj0mabx1lwg5wphu38ZskuPI9f1Ta1sES4JTpYHs1j1lhZ4X276jj3GEIWRQklV04b+9oa8RStPcbpCNq3hvsbt4oCJPhiTJc=");
	            request.put("iv", "XXBFxd0q4oXj0mabx1lwgw==");
	            request.put("email", "XXBFxd0q4oXj0mabx1lwg1KCK6PrOJim9AXboCuuYto=");
	            request.put("username", "XXBFxd0q4oXj0mabx1lwg/iNnDVbkHknWmad/kFLSEw=");
	            request.put("token", "XXBFxd0q4oXj0mabx1lwgxoC+sdSFh53SO51IrxQqAgLL9xHmytc1xTH9bLStOihrGOgkfXbRa7XxMqoNx673tRMnBSBjs3DiM3jSz2XEWQsKymVZe5TXBlW7p0UsWyYS3SwQ3iTT0Y/mPDdR7IoMxTyBTa7z+G82B+x7PwuPb6Lcgqo1cx18FUOWry5hDTSnj07GpeX/IhzZHdz77irYij65reB79g/Wh27e3M8MTkB0Y0y9Eo0DO8jaAhY4bIL");

	            // Esperado
	            Map<String, String> expectedDecryptedData = new HashMap<>();
	            expectedDecryptedData.put("password", decryptedValue);
	            expectedDecryptedData.put("email", decryptedValue);
	            expectedDecryptedData.put("username", decryptedValue);
	            expectedDecryptedData.put("token", decryptedValue);

	            // Llamar al método
	            Map<String, String> actualDecryptedData = ecosystemCryptoValue.decryptValues(request);

	            // Verificaciones
	            assertEquals(expectedDecryptedData, actualDecryptedData);
	        }
	    }
}
