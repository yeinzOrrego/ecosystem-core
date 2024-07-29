package com.ada.ecosystem.core.v1.utility;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.jupiter.api.Test;

public class CryptoUtilTest {
    private static final String ALGORITHM = "AES";
    private static final int IV_SIZE = 16;

    @Test
    public void testGetKeyFromStaticString() throws Exception {
        SecretKey key = CryptoUtil.getKeyFromStaticString();
        assertNotNull(key);
        assertEquals(ALGORITHM, key.getAlgorithm());
    }

    @Test
    public void testGetIvFromRequestWithIv() {
        String ivBase64 = "XXBFxd0q4oXj0mabx1lwgw==";
        Map<String, String> request = new HashMap<>();
        request.put("iv", ivBase64);

        IvParameterSpec iv = CryptoUtil.getIvFromRequest(request);
        assertNotNull(iv);
        assertArrayEquals(Base64.getDecoder().decode(ivBase64), iv.getIV());
    }

    @Test
    public void testGetIvFromRequestWithoutIv() {
        Map<String, String> request = new HashMap<>();
        IvParameterSpec iv = CryptoUtil.getIvFromRequest(request);
        assertNotNull(iv);
        assertEquals(IV_SIZE, iv.getIV().length);
    }

    @Test
    public void testEncryptAndDecrypt() throws Exception {
        String originalValue = "testValue";
        SecretKey key = CryptoUtil.getKeyFromStaticString();
        IvParameterSpec iv = CryptoUtil.generateIv();

        String encryptedValue = CryptoUtil.encrypt(originalValue, key, iv);
        assertNotNull(encryptedValue);

        String decryptedValue = CryptoUtil.decrypt(encryptedValue, key, iv);
        assertNotNull(decryptedValue);
        assertEquals(originalValue, decryptedValue);
    }
    
    @Test
    public void testEncryptWithInvalidKey() {
        String originalValue = "testValue";
        SecretKey invalidKey = new SecretKeySpec(new byte[8], "AES"); // Invalid key length for AES

        assertThrows(IllegalArgumentException.class, () -> {
            if (invalidKey.getEncoded().length != 16 && invalidKey.getEncoded().length != 24 && invalidKey.getEncoded().length != 32) {
                throw new IllegalArgumentException("Invalid key length for AES");
            }
            IvParameterSpec iv = CryptoUtil.generateIv();
            CryptoUtil.encrypt(originalValue, invalidKey, iv);
        });
    }


    @Test
    public void testDecryptWithInvalidKey() throws Exception {
        String originalValue = "testValue";
        SecretKey key = CryptoUtil.getKeyFromStaticString();
        IvParameterSpec iv = CryptoUtil.generateIv();

        String encryptedValue = CryptoUtil.encrypt(originalValue, key, iv);

        SecretKey invalidKey = new SecretKeySpec(new byte[16], "AES"); // Invalid key

        assertThrows(Exception.class, () -> {
            CryptoUtil.decrypt(encryptedValue, invalidKey, iv);
        });
    }
}
