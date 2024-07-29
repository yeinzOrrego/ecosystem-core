package com.ada.ecosystem.core.v1.kafka;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    void testEnumValues() {
        // Test if all expected enum values are present
        Status[] expectedValues = Status.values();
        
        // Assert that the number of enum values is as expected
        assertEquals(5, expectedValues.length, "Enum should have 5 values");

        // Assert the specific enum values
        assertEquals(Status.SUBMITTED, Status.valueOf("SUBMITTED"), "Expected value SUBMITTED");
        assertEquals(Status.STARTED, Status.valueOf("STARTED"), "Expected value STARTED");
        assertEquals(Status.RUNNING, Status.valueOf("RUNNING"), "Expected value RUNNING");
        assertEquals(Status.FINISHED, Status.valueOf("FINISHED"), "Expected value FINISHED");
        assertEquals(Status.TERMINATED, Status.valueOf("TERMINATED"), "Expected value TERMINATED");
    }
    
    @Test
    void testEnumOrder() {
        // Test if the enum values are in the expected order
        Status[] expectedOrder = {
            Status.SUBMITTED,
            Status.STARTED,
            Status.RUNNING,
            Status.FINISHED,
            Status.TERMINATED
        };
        
        Status[] actualOrder = Status.values();
        
        assertArrayEquals(expectedOrder, actualOrder, "Enum values are not in the expected order");
    }
}
