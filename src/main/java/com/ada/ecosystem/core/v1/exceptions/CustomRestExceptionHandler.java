package com.ada.ecosystem.core.v1.exceptions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

/**
 * The Class CustomRestExceptionHandler.
 * @author Carlos Torres - carlos.torres@ada.co
 * @version 0.0.8
 */
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
	
	/** The Constant ERROR_LABEL. */
	private static final String ERROR_LABEL = "error";

    // 400

    /**
     * Handle method argument not valid.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param ex the ex
     * @param headers the headers
     * @param status the status
     * @param request the request
     * @return the response entity
     */
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, ecosystemError, headers, ecosystemError.getStatus(), request);
    }

    /**
     * Handle bind exception.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param ex the ex
     * @param headers the headers
     * @param status the status
     * @param request the request
     * @return the response entity
     */
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, ecosystemError, headers, ecosystemError.getStatus(), request);
    }

    /**
     * Handle type mismatch.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param ex the ex
     * @param headers the headers
     * @param status the status
     * @param request the request
     * @return the response entity
     */
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();

        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }

    /**
     * Handle missing servlet request part.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param ex the ex
     * @param headers the headers
     * @param status the status
     * @param request the request
     * @return the response entity
     */
    protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final String error = ex.getRequestPartName() + " part is missing";
        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }

    /**
     * Handle missing servlet request parameter.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param ex the ex
     * @param headers the headers
     * @param status the status
     * @param request the request
     * @return the response entity
     */
    protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final String error = ex.getParameterName() + " parameter is missing";
        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }

    //    

    /**
     * Handle method argument type mismatch.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param ex the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());        
        //
        String name = "";
        if(ex.getRequiredType() != null) {
        	var requiredType = ex.getRequiredType();
        	if(requiredType != null) {
        		name = requiredType.getName();
        	}
        	else {
        		name = "unknown";
        	}
        }
        else {
        	name = "unknown";
        }
        final String error = ex.getName() + " should be of type " + name;

        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }

    /**
     * Handle constraint violation.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param ex the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final List<String> errors = new ArrayList<>();
        for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
        }

        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }    

    // 404

    /**
     * Handle no handler found exception.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param ex the ex
     * @param headers the headers
     * @param status the status
     * @param request the request
     * @return the response entity
     */
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }

    // 405

    /**
     * Handle http request method not supported.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param ex the ex
     * @param headers the headers
     * @param status the status
     * @param request the request
     * @return the response entity
     */
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");        
        var http = ex.getSupportedHttpMethods();
        if(http != null) http.forEach(t -> builder.append(t + " "));
        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), builder.toString());
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }

    // 415

    /**
     * Handle http media type not supported.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param ex the ex
     * @param headers the headers
     * @param status the status
     * @param request the request
     * @return the response entity
     */
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));

        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }    

    /**
     * Handle SQL exception.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param ex the ex
     * @param request the request
     * @return the response entity
     */
    // 500
    @ExceptionHandler({ SQLException.class })
    public ResponseEntity<Object> handleSQLException(final SQLException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error(ERROR_LABEL, ex);
        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.INTERNAL_SERVER_ERROR, 
        		ex.getLocalizedMessage(), 
        		ex.getSQLState(),
        		"check the data source resource call parameters");
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }    
    
    /**
     * Handle smart api builder exception.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param ex the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler({ EcosystemException.class })
    public ResponseEntity<Object> handleSmartApiBuilderException(final EcosystemException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error(ERROR_LABEL, ex);
        final EcosystemError ecosystemError = ex.getLogProcess().isEmpty() ? new EcosystemError(HttpStatus.INTERNAL_SERVER_ERROR, 
        		ex.getLocalizedMessage(), 
        		ex.getErrors(),
        		ex.getSolutions()) :
			new EcosystemError(HttpStatus.INTERNAL_SERVER_ERROR, 
	        		ex.getLocalizedMessage(), 
	        		ex.getErrors(),
	        		ex.getSolutions(),
	        		ex.getLogProcess());
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }    

    /**
     * Handle all.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param ex the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error(ERROR_LABEL, ex);
        final EcosystemError ecosystemError = new EcosystemError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
        return new ResponseEntity<>(ecosystemError, new HttpHeaders(), ecosystemError.getStatus());
    }	
}