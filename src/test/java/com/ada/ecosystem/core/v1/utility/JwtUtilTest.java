package com.ada.ecosystem.core.v1.utility;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

public class JwtUtilTest {

    @InjectMocks
    private JwtUtil jwtUtil;

    @Mock
    private Environment env;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExisteJWTTokenWithHeader() {
        when(request.getHeader("Authorization")).thenReturn("Bearer token");

        assertTrue(jwtUtil.existeJWTToken(request));
    }

    @Test
    public void testExisteJWTTokenWithoutHeader() {
        when(request.getHeader("Authorization")).thenReturn(null);

        assertFalse(jwtUtil.existeJWTToken(request));
    }

    @Test
    public void testParseTokenValid() {
        // Crea un JWT válido
        String validJwt = Jwts.builder()
                .setClaims(Map.of(
                    "codigo_mempresa", "12345",
                    "correo", "test@example.com",
                    "company", "exampleCompany",
                    "nombre", "John",
                    "apellido", "Doe",
                    "user", "john_doe",
                    "id_user", 1L
                ))
                .signWith(SignatureAlgorithm.HS256, "testSecretKey")
                .compact();

        // Configura el mock de HttpServletRequest
        when(request.getHeader("Authorization")).thenReturn("Bearer " + validJwt);
    }

    @Test
    public void testParseTokenInvalid() {
        // Configura un JWT mal formado o inválido
        String invalidJwt = "invalid.jwt.token";

        // Configura el mock de HttpServletRequest
        when(request.getHeader("Authorization")).thenReturn("Bearer " + invalidJwt);
    }

    @Test
    public void testDecodeJWT() {
        when(env.getProperty("config.security.oauth.jwt.key")).thenReturn("testSecretKey");

    }

    @Test
    public void testDecodeJWTInvalid() {
        // Configura un JWT mal formado o inválido
        String invalidJwt = "invalid.jwt.token";

        // Mockea el entorno
        when(env.getProperty("config.security.oauth.jwt.key")).thenReturn("testSecretKey");

        // Llama al método decodeJWT y captura la excepción
        try {
            jwtUtil.decodeJWT(invalidJwt);
            assertFalse(true, "Expected exception was not thrown");
        } catch (Exception e) {
            assertTrue(e instanceof io.jsonwebtoken.JwtException, "Exception type is incorrect");
        }
    }
}
