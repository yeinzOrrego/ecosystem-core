package com.ada.ecosystem.core.v1.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataSourceRoutingTest {

    private DataSourceRouting dataSourceRouting;

    @BeforeEach
    public void setUp() {
        dataSourceRouting = new DataSourceRouting();
    }

    @Test
    public void testDetermineCurrentLookupKey() {
        // Arrange
        String expectedDatabaseContext = "testContext";
        DatabaseContextHolder.setDatabaseContext(expectedDatabaseContext);

        // Act
        Object actualLookupKey = dataSourceRouting.determineCurrentLookupKey();

        // Assert
        assertEquals(expectedDatabaseContext, actualLookupKey);
    }

    @Test
    public void testDetermineCurrentLookupKeyWhenNoContext() {
        // Arrange
        DatabaseContextHolder.clearDatabaseContext(); // Ensure no context is set

        // Act
        Object actualLookupKey = dataSourceRouting.determineCurrentLookupKey();

        // Assert
        assertEquals(null, actualLookupKey);
    }
}
