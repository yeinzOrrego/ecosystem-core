package com.ada.ecosystem.core.v1.kafka;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TaskResponseTest {

    @Test
    void testNoArgsConstructor() {
        TaskResponse taskResponse = new TaskResponse();
        // Check that the fields are initialized to default values
        assertNull(taskResponse.getTaskId(), "TaskId should be null by default");
        assertNull(taskResponse.getName(), "Name should be null by default");
    }

    @Test
    void testAllArgsConstructor() {
        String taskId = "123";
        String name = "Test Task";
        TaskResponse taskResponse = new TaskResponse(taskId, name);
        // Verify that the fields are correctly set
        assertEquals(taskId, taskResponse.getTaskId(), "TaskId should be set by the constructor");
        assertEquals(name, taskResponse.getName(), "Name should be set by the constructor");
    }

    @Test
    void testGettersAndSetters() {
        TaskResponse taskResponse = new TaskResponse();
        String taskId = "123";
        String name = "Test Task";
        taskResponse.setTaskId(taskId);
        taskResponse.setName(name);
        // Verify that the getters return the set values
        assertEquals(taskId, taskResponse.getTaskId(), "Getter should return the set TaskId");
        assertEquals(name, taskResponse.getName(), "Getter should return the set Name");
    }

    @Test
    void testEqualsAndHashCode() {
        TaskResponse taskResponse1 = new TaskResponse("123", "Test Task");
        TaskResponse taskResponse2 = new TaskResponse("123", "Test Task");
        TaskResponse taskResponse3 = new TaskResponse("456", "Different Task");
        
        // Test equality
        assertEquals(taskResponse1, taskResponse2, "Objects with the same taskId and name should be equal");
        assertNotEquals(taskResponse1, taskResponse3, "Objects with different taskId or name should not be equal");

        // Test hash code
        assertEquals(taskResponse1.hashCode(), taskResponse2.hashCode(), "Hash codes should be equal for objects with the same taskId and name");
        assertNotEquals(taskResponse1.hashCode(), taskResponse3.hashCode(), "Hash codes should be different for objects with different taskId or name");
    }
}
