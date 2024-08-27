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

/**
 * CryptoUtil provides utility methods for AES encryption and decryption.
 * 
 * <p>
 * Project: Migracion SICOF - ADA
 * Year: 2024
 * </p>
 * 
 * <p>
 * This utility class includes methods to generate and retrieve encryption keys, 
 * manage Initialization Vectors (IVs), and perform encryption and decryption 
 * operations using AES/CBC/PKCS5Padding.
 * </p>
 */
public class CryptoUtil {

    private static final String SECRET_KEY = "a8b03e17c94a24d57d5f830b9f7b603f2d6ea481bfb6f10236ad095e6e3c84f7ac9d1be2f4bcd1a927ec18b037b4d2e49e00f7c7c3a58e4df5f84912c782e3ab";
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int IV_SIZE = 16;

    /**
     * Generates a SecretKey object from a static string.
     * 
     * @return a SecretKey derived from the static SECRET_KEY string.
     * @throws Exception if an error occurs during key generation.
     */
    public static SecretKey getKeyFromStaticString() throws Exception {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        keyBytes = sha.digest(keyBytes);
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    /**
     * Retrieves the Initialization Vector (IV) from the request map.
     * 
     * @param request a map containing the IV encoded as a Base64 string.
     * @return an IvParameterSpec object created from the provided IV, or a new IV if not provided.
     */
    public static IvParameterSpec getIvFromRequest(Map<String, ?> request) {
        String ivBase64 = (String) request.get("iv");
        if (ivBase64 != null) {
            byte[] ivBytes = Base64.getDecoder().decode(ivBase64);
            return new IvParameterSpec(ivBytes);
        } else {
            return generateIv();
        }
    }

    /**
     * Generates a new random Initialization Vector (IV).
     * 
     * @return a new IvParameterSpec object containing a random IV.
     */
    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[IV_SIZE];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    /**
     * Encrypts the provided value using the specified SecretKey and IV.
     * 
     * @param value the string to be encrypted.
     * @param key the SecretKey used for encryption.
     * @param iv the Initialization Vector used for encryption.
     * @return the encrypted value as a Base64 encoded string.
     * @throws Exception if an error occurs during encryption.
     */
    public static String encrypt(String value, SecretKey key, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encryptedBytes = cipher.doFinal(value.getBytes());

        byte[] encryptedIvAndText = new byte[iv.getIV().length + encryptedBytes.length];
        System.arraycopy(iv.getIV(), 0, encryptedIvAndText, 0, iv.getIV().length);
        System.arraycopy(encryptedBytes, 0, encryptedIvAndText, iv.getIV().length, encryptedBytes.length);

        return Base64.getEncoder().encodeToString(encryptedIvAndText);
    }

    /**
     * Decrypts the provided encrypted value using the specified SecretKey and IV.
     * 
     * @param encryptedValue the Base64 encoded string to be decrypted.
     * @param key the SecretKey used for decryption.
     * @param iv the Initialization Vector used for decryption.
     * @return the decrypted string.
     * @throws Exception if an error occurs during decryption.
     */
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
