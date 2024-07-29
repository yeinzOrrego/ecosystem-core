package com.ada.ecosystem.core.v1.exceptions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

public class CustomRestExceptionHandlerTest {

    private final CustomRestExceptionHandler handler = new CustomRestExceptionHandler();


    @Test
    public void testHandleNoHandlerFoundException() {
        // Arrange
        NoHandlerFoundException ex = new NoHandlerFoundException("GET", "/test", mock(HttpHeaders.class));
        HttpHeaders headers = new HttpHeaders();
        WebRequest request = mock(WebRequest.class);

        // Act
        ResponseEntity<Object> response = handler.handleNoHandlerFoundException(ex, headers, HttpStatus.NOT_FOUND, request);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        EcosystemError body = (EcosystemError) response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getErrors()).contains("No handler found for GET /test");
    }


    @Test
    public void testHandleSQLException() {
        // Arrange
        SQLException ex = new SQLException("SQL error", "SQLSTATE");
        WebRequest request = mock(WebRequest.class);

        // Act
        ResponseEntity<Object> response = handler.handleSQLException(ex, request);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        EcosystemError body = (EcosystemError) response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getErrors()).contains("SQLSTATE");
    }

    @Test
    public void testHandleAllException() {
        // Arrange
        Exception ex = new RuntimeException("General error");
        WebRequest request = mock(WebRequest.class);

        // Act
        ResponseEntity<Object> response = handler.handleAll(ex, request);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        EcosystemError body = (EcosystemError) response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getErrors()).contains("error occurred");
    }
}
