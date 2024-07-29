package com.ada.ecosystem.core.v1.response;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.ada.ecosystem.core.v1.exceptions.EcosystemError;
import com.ada.ecosystem.core.v1.exceptions.EcosystemException;

import jakarta.validation.ConstraintViolation;

public class CustomResponseExceptionHandlerTest {

    private CustomResponseExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new CustomResponseExceptionHandler();
    }

    @Test
    void testHandleBindException() {
        FieldError fieldError = new FieldError("objectName", "field", "defaultMessage");
        ObjectError objectError = new ObjectError("objectName", "defaultMessage");
        BindException ex = new BindException(new Object(), "objectName");
        ex.getBindingResult().addError(fieldError);
        ex.getBindingResult().addError(objectError);

        HttpHeaders headers = new HttpHeaders();
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<Object> response = handler.handleBindException(ex, headers, HttpStatus.BAD_REQUEST, request);

        EcosystemError errorResponse = (EcosystemError) response.getBody();
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertTrue(errorResponse.getErrors().contains("field: defaultMessage"));
        Assertions.assertTrue(errorResponse.getErrors().contains("objectName: defaultMessage"));
    }

    @Test
    void testHandleMethodArgumentNotValid() {
        FieldError fieldError = new FieldError("objectName", "field", "defaultMessage");
        ObjectError objectError = new ObjectError("objectName", "defaultMessage");
        BindException bindException = new BindException(new Object(), "objectName");
        bindException.addError(fieldError);
        bindException.addError(objectError);
    }

    @Test
    void testHandleMethodArgumentTypeMismatch() {
        MethodArgumentTypeMismatchException ex = new MethodArgumentTypeMismatchException("value", Integer.class, "name", null, null);
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<Object> response = handler.handleMethodArgumentTypeMismatch(ex, request);

        EcosystemError errorResponse = (EcosystemError) response.getBody();
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testHandleHttpRequestMethodNotSupported() {
        HttpRequestMethodNotSupportedException ex = new HttpRequestMethodNotSupportedException("POST", List.of("GET", "PUT"));
        HttpHeaders headers = new HttpHeaders();
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<Object> response = handler.handleHttpRequestMethodNotSupported(ex, headers, HttpStatus.METHOD_NOT_ALLOWED, request);

        EcosystemError errorResponse = (EcosystemError) response.getBody();
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    }

    @Test
    void testHandleHttpMediaTypeNotSupported() {
        HttpMediaTypeNotSupportedException ex = new HttpMediaTypeNotSupportedException("application/xml", List.of());
        HttpHeaders headers = new HttpHeaders();
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<Object> response = handler.handleHttpMediaTypeNotSupported(ex, headers, HttpStatus.UNSUPPORTED_MEDIA_TYPE, request);

        EcosystemError errorResponse = (EcosystemError) response.getBody();
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
    }

    @Test
    void testHandleSQLException() {
        SQLException ex = new SQLException("SQL error", "state");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<Object> response = handler.handleSQLException(ex, request);

        EcosystemError errorResponse = (EcosystemError) response.getBody();
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testHandleEcosystemException() {
        EcosystemException ex = new EcosystemException("message", List.of("error1"), List.of("solution1"));
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<Object> response = handler.handleEcosystemExceptionException(ex, request);

        EcosystemError errorResponse = (EcosystemError) response.getBody();
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertTrue(errorResponse.getErrors().contains("error1"));
        Assertions.assertTrue(errorResponse.getSolutions().contains("solution1"));
    }

    @Test
    void testHandleAll() {
        Exception ex = new Exception("General error");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<Object> response = handler.handleAll(ex, request);

        EcosystemError errorResponse = (EcosystemError) response.getBody();
        Assertions.assertNotNull(errorResponse);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertTrue(errorResponse.getErrors().contains("error occurred"));
    }
    
    @Test
    void testHandleConstraintViolation() {
        ConstraintViolation<?> violation = mock(ConstraintViolation.class);
        when(violation.getRootBeanClass()).thenReturn(null);
        when(violation.getPropertyPath()).thenReturn(null);
        when(violation.getMessage()).thenReturn("defaultMessage");
    }
}
