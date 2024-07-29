package com.ada.ecosystem.core.v1.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class TaskStatusTest {

    @Test
    void testNoArgsConstructor() {
        TaskStatus taskStatus = new TaskStatus();

        // Verifica que los campos están inicializados a valores por defecto
        assertNull(taskStatus.getTaskId(), "TaskId should be null by default");
        assertNull(taskStatus.getTaskName(), "TaskName should be null by default");
        assertNull(taskStatus.getTaskKey(), "TaskKey should be null by default");
        assertNull(taskStatus.getPercentageComplete(), "PercentageComplete should be null by default");
        assertNull(taskStatus.getStatus(), "Status should be null by default");
    }

    @Test
    void testSettersAndGetters() {
        TaskStatus taskStatus = new TaskStatus();

        String taskId = "task-123";
        String taskName = "Process Task";
        String taskKey = "key-456";
        Float percentageComplete = 75.5f;
        Status status = Status.RUNNING; // Assuming Status is an enum with RUNNING as a value

        taskStatus.setTaskId(taskId);
        taskStatus.setTaskName(taskName);
        taskStatus.setTaskKey(taskKey);
        taskStatus.setPercentageComplete(percentageComplete);
        taskStatus.setStatus(status);

        // Verifica que los valores se establecieron correctamente
        assertEquals(taskId, taskStatus.getTaskId(), "TaskId should be set using setter");
        assertEquals(taskName, taskStatus.getTaskName(), "TaskName should be set using setter");
        assertEquals(taskKey, taskStatus.getTaskKey(), "TaskKey should be set using setter");
        assertEquals(percentageComplete, taskStatus.getPercentageComplete(), "PercentageComplete should be set using setter");
        assertEquals(status, taskStatus.getStatus(), "Status should be set using setter");
    }

    @Test
    void testSerialization() throws Exception {
        TaskStatus original = new TaskStatus();
        original.setTaskId("task-123");
        original.setTaskName("Process Task");
        original.setTaskKey("key-456");
        original.setPercentageComplete(75.5f);
        original.setStatus(Status.RUNNING);

        byte[] serialized = serialize(original);
        TaskStatus deserialized = deserialize(serialized, TaskStatus.class);

        // Verifica que los valores se preservan durante la serialización
        assertEquals(original.getTaskId(), deserialized.getTaskId(), "TaskId should be preserved during serialization");
        assertEquals(original.getTaskName(), deserialized.getTaskName(), "TaskName should be preserved during serialization");
        assertEquals(original.getTaskKey(), deserialized.getTaskKey(), "TaskKey should be preserved during serialization");
        assertEquals(original.getPercentageComplete(), deserialized.getPercentageComplete(), "PercentageComplete should be preserved during serialization");
        assertEquals(original.getStatus(), deserialized.getStatus(), "Status should be preserved during serialization");
    }

    @Test
    void testEqualsAndHashCode() {
        TaskStatus taskStatus1 = new TaskStatus();
        taskStatus1.setTaskId("task-123");
        taskStatus1.setTaskName("Process Task");
        taskStatus1.setTaskKey("key-456");
        taskStatus1.setPercentageComplete(75.5f);
        taskStatus1.setStatus(Status.RUNNING);

        TaskStatus taskStatus2 = new TaskStatus();
        taskStatus2.setTaskId("task-123");
        taskStatus2.setTaskName("Process Task");
        taskStatus2.setTaskKey("key-456");
        taskStatus2.setPercentageComplete(75.5f);
        taskStatus2.setStatus(Status.RUNNING);

        // Verifica que los objetos con los mismos valores son iguales
        assertEquals(taskStatus1, taskStatus2, "TaskStatus objects should be equal");

        // Verifica el hashCode
        assertEquals(taskStatus1.hashCode(), taskStatus2.hashCode(), "TaskStatus hashCode should be equal");
    }

    // Utility methods for serialization
    private <T> byte[] serialize(T object) throws Exception {
        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
             java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
            oos.writeObject(object);
            return baos.toByteArray();
        }
    }

    private <T> T deserialize(byte[] data, Class<T> clazz) throws Exception {
        try (java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(data);
             java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais)) {
            return clazz.cast(ois.readObject());
        }
    }
}
