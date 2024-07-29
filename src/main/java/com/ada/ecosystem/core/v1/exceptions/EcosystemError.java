package com.ada.ecosystem.core.v1.exceptions;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;



/**
 * Clase genérica que representa los errores que se generan en el ecosistema
 *
 * 
 */
@Schema(description = "Clase genérica que representa los errores que se generan en el ecosistema.")
@Data
public class EcosystemError {	
	
	/** The status. */
	@Schema(description = "Código HTTP de devolución que genera en el error.")
    private HttpStatus status;
    
	
    /** The message. */
	@Schema(description = "Mensaje principal que describe el error.")
    private String message;
    
    /** The errors. */
	@Schema(description = "Lista de errores o validaciones fallidas que se generan.")
    private List<String> errors;
    
    /** The solutions. */
	@Schema(description = "Lista de soluciones o recomendaciones para evitar la generación del error.")
    private List<String> solutions;
	
	/** The lod process. */
	@Schema(description = "Log de mensajes del proceso que se estaba ejecutando en el momento del error.")
	private StringBuilder logProcess;
    
    /**
     * Instantiates a new api error.
     * @author Carlos Torres - torrescamargo@gmail.com
	 * @version 0.0.8
     */
    public EcosystemError() {
        super();
    }

    /**
     * Instantiates a new api error.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param status the status
     * @param message the message
     * @param errors the errors
     */
    public EcosystemError(final HttpStatus status, final String message, final List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
        solutions = Arrays.asList("");
        logProcess = new StringBuilder();
    }
    
    /**
     * Instantiates a new ecosystem error.
     *
     * @param status the status
     * @param message the message
     * @param errors the errors
     * @param logProcess the log process
     */
    public EcosystemError(final HttpStatus status, final String message, final List<String> errors, StringBuilder logProcess) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
        solutions = Arrays.asList("");
        this.logProcess = logProcess;
    }
    
    /**
     * Instantiates a new api error.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param status the status
     * @param message the message
     * @param errors the errors
     * @param solutions the solutions
     */
    public EcosystemError(final HttpStatus status, final String message, final List<String> errors, List<String> solutions) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.solutions = solutions;
        logProcess = new StringBuilder();
    }
    
    /**
     * Instantiates a new ecosystem error.
     *
     * @param status the status
     * @param message the message
     * @param errors the errors
     * @param solutions the solutions
     * @param logProcess the log process
     */
    public EcosystemError(final HttpStatus status, final String message, final List<String> errors, List<String> solutions, StringBuilder logProcess) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.solutions = solutions;
        this.logProcess = logProcess;
    }

    /**
     * Instantiates a new api error.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param status the status
     * @param message the message
     * @param error the error
     */
    public EcosystemError(final HttpStatus status, final String message, final String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
        solutions = Arrays.asList("");
        logProcess = new StringBuilder();
    }
    
    /**
     * Instantiates a new ecosystem error.
     *
     * @param status the status
     * @param message the message
     * @param error the error
     * @param logProcess the log process
     */
    public EcosystemError(final HttpStatus status, final String message, final String error, StringBuilder logProcess) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
        solutions = Arrays.asList("");
        this.logProcess = logProcess;
    }
    
    /**
     * Instantiates a new api error.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.8
     * @param status the status
     * @param message the message
     * @param error the error
     * @param solution the solution
     */
    public EcosystemError(final HttpStatus status, final String message, final String error, final String solution) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
        solutions = Arrays.asList(solution);
        logProcess = new StringBuilder();
    }
    
    /**
     * Instantiates a new ecosystem error.
     *
     * @param status the status
     * @param message the message
     * @param error the error
     * @param solution the solution
     * @param logProcess the log process
     */
    public EcosystemError(final HttpStatus status, final String message, final String error, final String solution, StringBuilder logProcess) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
        solutions = Arrays.asList(solution);
        this.logProcess = logProcess;
    }
}