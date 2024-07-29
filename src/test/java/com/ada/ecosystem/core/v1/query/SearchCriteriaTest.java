package com.ada.ecosystem.core.v1.query;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SearchCriteriaTest {

    @Test
    public void testSearchCriteriaConstructor() {
        // Arrange
        String key = "testKey";
        SearchOperation operation = SearchOperation.EQUAL;
        Object value = "testValue";

        // Act
        SearchCriteria searchCriteria = new SearchCriteria(key, operation, value);

        // Assert
        assertEquals(key, searchCriteria.getKey(), "Key should be set correctly");
        assertEquals(operation, searchCriteria.getOperation(), "Operation should be set correctly");
        assertEquals(value, searchCriteria.getValue(), "Value should be set correctly");
    }

    @Test
    public void testSearchCriteriaWithDifferentOperations() {
        // Act & Assert
        for (SearchOperation operation : SearchOperation.values()) {
            SearchCriteria searchCriteria = new SearchCriteria("testKey", operation, "testValue");

            assertEquals("testKey", searchCriteria.getKey(), "Key should be 'testKey'");
            assertEquals(operation, searchCriteria.getOperation(), "Operation should be set correctly");
            assertEquals("testValue", searchCriteria.getValue(), "Value should be 'testValue'");
        }
    }

    @Test
    public void testSearchCriteriaDefaultConstructor() {
        // Act
    	SearchCriteria search = null;
        SearchCriteria searchCriteria = new SearchCriteria(null, null, search);

        // Assert
        assertNull(searchCriteria.getKey(), "Default key should be null");
        assertNull(searchCriteria.getOperation(), "Default operation should be null");
        assertNull(searchCriteria.getValue(), "Default value should be null");
    }
    
    @Test
    public void testSetters() {
        // Arrange
    	SearchCriteria search = null;
        SearchCriteria searchCriteria = new SearchCriteria(null, null, search);
        String key = "newKey";
        SearchOperation operation = SearchOperation.NOT_EQUAL;
        Object value = "newValue";

        // Act
        searchCriteria.setKey(key);
        searchCriteria.setOperation(operation);
        searchCriteria.setValue(value);

        // Assert
        assertEquals(key, searchCriteria.getKey(), "Key should be set correctly");
        assertEquals(operation, searchCriteria.getOperation(), "Operation should be set correctly");
        assertEquals(value, searchCriteria.getValue(), "Value should be set correctly");
    }
}
