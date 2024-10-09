package com.ada.ecosystem.core.v1.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DatasourceInterceptorTest {

    @InjectMocks
    private DatasourceInterceptor datasourceInterceptor;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Environment env;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        DatabaseContextHolder.clearDatabaseContext();
        TokenContextHolder.clearToken();
    }

    @Test
    public void testPreHandleWithDebugFalse() throws Exception {
        // Arrange
        when(request.getHeader("token")).thenReturn("testCompany");
        datasourceInterceptor.debug = false; // Set the debug flag to false

        // Act
        boolean result = datasourceInterceptor.preHandle(request, response, new Object());

        // Assert
        assertTrue(result);
    
    }

    @Test
    public void testPreHandleWithDebugTrue() throws Exception {
        // Arrange
        datasourceInterceptor.debug = true; // Set the debug flag to true

        // Act
        boolean result = datasourceInterceptor.preHandle(request, response, new Object());

        // Assert
        assertTrue(result);
        assertNull(DatabaseContextHolder.getDatabaseContext());
        assertNull(TokenContextHolder.getToken());
    }

    @Test
    public void testAfterCompletion() throws Exception {
        // Arrange
        DatabaseContextHolder.setDatabaseContext("testContext");
        TokenContextHolder.setToken("testToken");

        // Act
        datasourceInterceptor.afterCompletion(request, response, new Object(), null);

        // Assert
        assertNull(DatabaseContextHolder.getDatabaseContext());
        assertNull(TokenContextHolder.getToken());
    }


    @Test
    public void testGetDatabaseEnumContext() {
        // Act
        String result = datasourceInterceptor.getDatabaseEnumContext("testCompany");

        // Assert
        assertEquals("testCompany", result);
    }
}
