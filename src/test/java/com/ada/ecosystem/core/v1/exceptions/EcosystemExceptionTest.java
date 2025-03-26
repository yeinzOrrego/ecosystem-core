package com.ada.ecosystem.core.v1.exceptions;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EcosystemExceptionTest {

//    @Test
//    void testConstructorWithMessageErrorsAndSolutions() {
//        String errorMessage = "An error occurred";
//        List<String> errors = Arrays.asList("Error 1", "Error 2");
//        List<String> solutions = Arrays.asList("Solution 1");
//
//        EcosystemException exception = new EcosystemException(errorMessage, errors, solutions);
//
//        assertEquals(errorMessage, exception.getMessage());
//        assertEquals(errors, exception.getErrors());
//        assertEquals(solutions, exception.getSolutions());
//        assertNotNull(exception.getLogProcess());
//        assertEquals(0, exception.getLogProcess().length());
//    }
//
//    @Test
//    void testConstructorWithMessageErrorsAndSolutionsHandlesNulls() {
//        String errorMessage = "An error occurred";
//
//        EcosystemException exception = new EcosystemException(errorMessage, null, null);
//
//        assertEquals(errorMessage, exception.getMessage());
//        assertEquals(Arrays.asList(""), exception.getErrors());
//        assertEquals(Arrays.asList(""), exception.getSolutions());
//        assertNotNull(exception.getLogProcess());
//        assertEquals(0, exception.getLogProcess().length());
//    }
//
//    @Test
//    void testConstructorWithMessageErrorsSolutionsAndLogProcess() {
//        String errorMessage = "An error occurred";
//        List<String> errors = Arrays.asList("Error 1");
//        List<String> solutions = Arrays.asList("Solution 1");
//        StringBuilder logProcess = new StringBuilder("Log details");
//
//        EcosystemException exception = new EcosystemException(errorMessage, errors, solutions, logProcess);
//
//        assertEquals(errorMessage, exception.getMessage());
//        assertEquals(errors, exception.getErrors());
//        assertEquals(solutions, exception.getSolutions());
//        assertEquals(logProcess.toString(), exception.getLogProcess().toString());
//    }
//
//    @Test
//    void testConstructorWithMessageErrorsSolutionsAndLogProcessHandlesNull() {
//        String errorMessage = "An error occurred";
//        List<String> errors = Arrays.asList("Error 1");
//        List<String> solutions = Arrays.asList("Solution 1");
//
//        EcosystemException exception = new EcosystemException(errorMessage, errors, solutions, null);
//
//        assertEquals(errorMessage, exception.getMessage());
//        assertEquals(errors, exception.getErrors());
//        assertEquals(solutions, exception.getSolutions());
//        assertNotNull(exception.getLogProcess());
//        assertEquals(0, exception.getLogProcess().length());
//    }
//
//    @Test
//    void testEqualsAndHashCode() {
//        String errorMessage = "An error occurred";
//        List<String> errors = Arrays.asList("Error 1");
//        List<String> solutions = Arrays.asList("Solution 1");
//        StringBuilder logProcess = new StringBuilder("Log details");
//
//        EcosystemException exception1 = new EcosystemException(errorMessage, errors, solutions, logProcess);
//        EcosystemException exception2 = new EcosystemException(errorMessage, errors, solutions, logProcess);
//
//        assertEquals(exception1, exception2);
//        assertEquals(exception1.hashCode(), exception2.hashCode());
//    }
//
//    @Test
//    void testEqualsAndHashCodeWithDifferentLogProcess() {
//        String errorMessage = "An error occurred";
//        List<String> errors = Arrays.asList("Error 1");
//        List<String> solutions = Arrays.asList("Solution 1");
//        StringBuilder logProcess1 = new StringBuilder("Log details");
//        StringBuilder logProcess2 = new StringBuilder("Different Log details");
//
//        EcosystemException exception1 = new EcosystemException(errorMessage, errors, solutions, logProcess1);
//        EcosystemException exception2 = new EcosystemException(errorMessage, errors, solutions, logProcess2);
//
//        assertNotEquals(exception1, exception2);
//    }
}
