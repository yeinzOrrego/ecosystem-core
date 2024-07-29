package com.ada.ecosystem.core.v1.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TokenContextHolderTest {

    @BeforeEach
    public void setUp() {
        TokenContextHolder.clearToken();
    }

    @Test
    public void testSetAndGetToken() {

        String token = "testToken";

        TokenContextHolder.setToken(token);
        String retrievedToken = TokenContextHolder.getToken();
        assertEquals(token, retrievedToken, "El token recuperado debería ser el mismo que el establecido.");
    }

    @Test
    public void testClearToken() {
        String token = "testToken";
        TokenContextHolder.setToken(token);

        TokenContextHolder.clearToken();
        String retrievedToken = TokenContextHolder.getToken();

        assertNull(retrievedToken, "El token debería ser nulo después de limpiarlo.");
    }

    @Test
    public void testGetTokenWithoutSetting() {
        String retrievedToken = TokenContextHolder.getToken();

        assertNull(retrievedToken, "El token debería ser nulo si no se ha establecido.");
    }

    @Test
    public void testMultipleThreads() throws InterruptedException {
        String token1 = "token1";
        String token2 = "token2";

        Thread thread1 = new Thread(() -> {
            TokenContextHolder.setToken(token1);
            assertEquals(token1, TokenContextHolder.getToken(), "El token en el primer hilo debería ser 'token1'.");
            TokenContextHolder.clearToken(); 
        });

        Thread thread2 = new Thread(() -> {
            TokenContextHolder.setToken(token2);
            assertEquals(token2, TokenContextHolder.getToken(), "El token en el segundo hilo debería ser 'token2'.");
            TokenContextHolder.clearToken(); 
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    @Test
    public void testTokenClearingInBetween() {
        String token1 = "token1";
        String token2 = "token2";

        TokenContextHolder.setToken(token1);
        TokenContextHolder.clearToken();
        TokenContextHolder.setToken(token2);
        String retrievedToken = TokenContextHolder.getToken();

        assertEquals(token2, retrievedToken, "El token recuperado después de establecer y limpiar debería ser 'token2'.");
    }
}
