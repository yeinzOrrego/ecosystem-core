package com.ada.ecosystem.core.v1.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EcosystemErrorTest {


    @Test
    void testConstructorWithStatusAndMessageAndErrors() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Error message";
        List<String> errors = Arrays.asList("Error 1", "Error 2");

        EcosystemError error = new EcosystemError(status, message, errors);

        assertEquals(status, error.getStatus());
        assertEquals(message, error.getMessage());
        assertEquals(errors, error.getErrors());
        assertNotNull(error.getSolutions());
        assertNotNull(error.getLogProcess());
        assertEquals(0, error.getLogProcess().length());
    }

    @Test
    void testConstructorWithStatusMessageErrorsAndSolutions() {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "Not Found";
        List<String> errors = Arrays.asList("Error 1");
        List<String> solutions = Arrays.asList("Solution 1");

        EcosystemError error = new EcosystemError(status, message, errors, solutions);

        assertEquals(status, error.getStatus());
        assertEquals(message, error.getMessage());
        assertEquals(errors, error.getErrors());
        assertEquals(solutions, error.getSolutions());
        assertNotNull(error.getLogProcess());
        assertEquals(0, error.getLogProcess().length());
    }

    @Test
    void testConstructorWithStatusMessageErrorsAndLogProcess() {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Internal Server Error";
        List<String> errors = Arrays.asList("Error 1");
        StringBuilder logProcess = new StringBuilder("Log info");

        EcosystemError error = new EcosystemError(status, message, errors, logProcess);

        assertEquals(status, error.getStatus());
        assertEquals(message, error.getMessage());
        assertEquals(errors, error.getErrors());
        assertNotNull(error.getSolutions());
        assertTrue(!error.getSolutions().isEmpty());
    }

    @Test
    void testConstructorWithAllParameters() {
        HttpStatus status = HttpStatus.FORBIDDEN;
        String message = "Forbidden";
        List<String> errors = Arrays.asList("Error 1", "Error 2");
        List<String> solutions = Arrays.asList("Solution 1");
        StringBuilder logProcess = new StringBuilder("Log process");

        EcosystemError error = new EcosystemError(status, message, errors, solutions, logProcess);

        assertEquals(status, error.getStatus());
        assertEquals(message, error.getMessage());
        assertEquals(errors, error.getErrors());
        assertEquals(solutions, error.getSolutions());
        assertEquals(logProcess.toString(), error.getLogProcess().toString());
    }

    @Test
    void testConstructorWithStatusMessageErrorAndSolution() {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String message = "Unauthorized";
        String error = "Error occurred";
        String solution = "Provide valid credentials";

        EcosystemError err = new EcosystemError(status, message, error, solution);

        assertEquals(status, err.getStatus());
        assertEquals(message, err.getMessage());
        assertEquals(Arrays.asList(error), err.getErrors());
        assertEquals(Arrays.asList(solution), err.getSolutions());
        assertNotNull(err.getLogProcess());
        assertEquals(0, err.getLogProcess().length());
    }

    @Test
    void testConstructorWithStatusMessageErrorSolutionAndLogProcess() {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String message = "Unauthorized";
        String error = "Error occurred";
        String solution = "Provide valid credentials";
        StringBuilder logProcess = new StringBuilder("Log details");

        EcosystemError err = new EcosystemError(status, message, error, solution, logProcess);

        assertEquals(status, err.getStatus());
        assertEquals(message, err.getMessage());
        assertEquals(Arrays.asList(error), err.getErrors());
        assertEquals(Arrays.asList(solution), err.getSolutions());
        assertEquals(logProcess.toString(), err.getLogProcess().toString());
    }
}
