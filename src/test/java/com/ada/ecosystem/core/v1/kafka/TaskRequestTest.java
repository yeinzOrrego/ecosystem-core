package com.ada.ecosystem.core.v1.kafka;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TaskRequestTest {

    @Test
    void testNoArgsConstructor() {
        TaskRequest taskRequest = new TaskRequest();
        // Check that the name field is null by default
        assertNull(taskRequest.getName(), "Name should be null by default");
    }

    @Test
    void testAllArgsConstructor() {
        String name = "Test Task";
        TaskRequest taskRequest = new TaskRequest(name);
        // Verify that the name field is correctly set
        assertEquals(name, taskRequest.getName(), "Name should be set by the constructor");
    }

    @Test
    void testGettersAndSetters() {
        TaskRequest taskRequest = new TaskRequest();
        String name = "Test Task";
        taskRequest.setName(name);
        // Verify that the getter returns the set value
        assertEquals(name, taskRequest.getName(), "Getter should return the set value");
    }

    @Test
    void testEqualsAndHashCode() {
        TaskRequest taskRequest1 = new TaskRequest("Test Task");
        TaskRequest taskRequest2 = new TaskRequest("Test Task");
        TaskRequest taskRequest3 = new TaskRequest("Different Task");
        
        // Test equality
        assertEquals(taskRequest1, taskRequest2, "Objects with the same name should be equal");
        assertNotEquals(taskRequest1, taskRequest3, "Objects with different names should not be equal");

        // Test hash code
        assertEquals(taskRequest1.hashCode(), taskRequest2.hashCode(), "Hash codes should be equal for objects with the same name");
        assertNotEquals(taskRequest1.hashCode(), taskRequest3.hashCode(), "Hash codes should be different for objects with different names");
    }
}
