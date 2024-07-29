package com.ada.ecosystem.core.v1.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {

    private static final String SECRET_KEY = "ADA_ECOSYSTEM_MICROSERVICES";
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int IV_SIZE = 16;

    public static SecretKey getKeyFromStaticString() throws Exception {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        keyBytes = sha.digest(keyBytes);
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    public static IvParameterSpec getIvFromRequest(Map<String, ?> request) {
        String ivBase64 = (String) request.get("iv");
        if (ivBase64 != null) {
            byte[] ivBytes = Base64.getDecoder().decode(ivBase64);
            return new IvParameterSpec(ivBytes);
        } else {
            return generateIv();
        }
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[IV_SIZE];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static String encrypt(String value, SecretKey key, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encryptedBytes = cipher.doFinal(value.getBytes());

        byte[] encryptedIvAndText = new byte[iv.getIV().length + encryptedBytes.length];
        System.arraycopy(iv.getIV(), 0, encryptedIvAndText, 0, iv.getIV().length);
        System.arraycopy(encryptedBytes, 0, encryptedIvAndText, iv.getIV().length, encryptedBytes.length);

        return Base64.getEncoder().encodeToString(encryptedIvAndText);
    }

    public static String decrypt(String encryptedValue, SecretKey key, IvParameterSpec iv) throws Exception {
        byte[] encryptedIvAndText = Base64.getDecoder().decode(encryptedValue);

        byte[] ivBytes = new byte[IV_SIZE];
        System.arraycopy(encryptedIvAndText, 0, ivBytes, 0, ivBytes.length);

        byte[] encryptedBytes = new byte[encryptedIvAndText.length - ivBytes.length];
        System.arraycopy(encryptedIvAndText, ivBytes.length, encryptedBytes, 0, encryptedBytes.length);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes);
    }
}
