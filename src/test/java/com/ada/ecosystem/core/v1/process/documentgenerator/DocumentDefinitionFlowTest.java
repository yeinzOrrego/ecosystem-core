package com.ada.ecosystem.core.v1.process.documentgenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ada.ecosystem.core.v1.process.ProcessResult;

public class DocumentDefinitionFlowTest {

    @Mock
    private DocumentDefinitionFlow<String> documentDefinitionFlow; // Mocking the interface

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFillTemporaryTable() {
        // Arrange
        List<String> list = List.of("item1", "item2");
        InputParameters inputParameters = new InputParameters();
        ProcessResult<Long> expectedResult = new ProcessResult<>();
        when(documentDefinitionFlow.fillTemporaryTable(anyList(), any(InputParameters.class)))
                .thenReturn(expectedResult);

        // Act
        ProcessResult<Long> result = documentDefinitionFlow.fillTemporaryTable(list, inputParameters);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(expectedResult, result, "The result should match the expected result");
        verify(documentDefinitionFlow).fillTemporaryTable(anyList(), any(InputParameters.class));
    }

    @Test
    void testGenerateDocument() {
        // Arrange
        InputParameters inputParameters = new InputParameters();
        ProcessResult<Long> expectedResult = new ProcessResult<>();
        when(documentDefinitionFlow.generateDocument(any(InputParameters.class)))
                .thenReturn(expectedResult);

        // Act
        ProcessResult<Long> result = documentDefinitionFlow.generateDocument(inputParameters);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(expectedResult, result, "The result should match the expected result");
        verify(documentDefinitionFlow).generateDocument(any(InputParameters.class));
    }

    @Test
    void testApproveDocument() {
        // Arrange
        InputParameters inputParameters = new InputParameters();
        ProcessResult<Long> expectedResult = new ProcessResult<>();
        when(documentDefinitionFlow.approveDocument(any(InputParameters.class)))
                .thenReturn(expectedResult);

        // Act
        ProcessResult<Long> result = documentDefinitionFlow.approveDocument(inputParameters);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(expectedResult, result, "The result should match the expected result");
        verify(documentDefinitionFlow).approveDocument(any(InputParameters.class));
    }

    @Test
    void testCancelDocument() {
        // Arrange
        InputParameters inputParameters = new InputParameters();
        ProcessResult<Long> expectedResult = new ProcessResult<>();
        when(documentDefinitionFlow.cancelDocument(any(InputParameters.class)))
                .thenReturn(expectedResult);

        // Act
        ProcessResult<Long> result = documentDefinitionFlow.cancelDocument(inputParameters);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(expectedResult, result, "The result should match the expected result");
        verify(documentDefinitionFlow).cancelDocument(any(InputParameters.class));
    }
}
