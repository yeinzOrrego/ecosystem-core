package com.ada.ecosystem.core.v1.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class TaskSendTest {

    @Test
    void testNoArgsConstructor() {
        TaskSend taskSend = new TaskSend();

        // Verifica que los campos están inicializados a valores por defecto
        assertNull(taskSend.getTopicName(), "TopicName should be null by default");
        assertNull(taskSend.getKey(), "Key should be null by default");
        assertNull(taskSend.getValue(), "Value should be null by default");
    }

    @Test
    void testSettersAndGetters() {
        TaskSend taskSend = new TaskSend();

        // Se establece valores para los campos
        String topicName = "topic-name";
        String key = "task-key";
        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setTaskId("task-123");
        taskStatus.setTaskName("Process Task");
        taskStatus.setTaskKey("key-456");
        taskStatus.setPercentageComplete(75.5f);
        taskStatus.setStatus(Status.RUNNING); // Assuming Status is an enum with RUNNING as a value

        taskSend.setTopicName(topicName);
        taskSend.setKey(key);
        taskSend.setValue(taskStatus);

        // Verifica que los valores se establecen correctamente
        assertEquals(topicName, taskSend.getTopicName(), "TopicName should be set using setter");
        assertEquals(key, taskSend.getKey(), "Key should be set using setter");
        assertEquals(taskStatus, taskSend.getValue(), "Value should be set using setter");

        // Verifica que los campos internos de `TaskStatus` están correctamente establecidos
        assertNotNull(taskSend.getValue(), "Value should not be null");
        assertEquals("task-123", taskSend.getValue().getTaskId(), "TaskId should be set correctly in Value");
        assertEquals("Process Task", taskSend.getValue().getTaskName(), "TaskName should be set correctly in Value");
        assertEquals("key-456", taskSend.getValue().getTaskKey(), "TaskKey should be set correctly in Value");
        assertEquals(75.5f, taskSend.getValue().getPercentageComplete(), "PercentageComplete should be set correctly in Value");
        assertEquals(Status.RUNNING, taskSend.getValue().getStatus(), "Status should be set correctly in Value");
    }

    @Test
    void testSerialization() throws Exception {
        TaskSend original = new TaskSend();
        original.setTopicName("topic-name");
        original.setKey("task-key");
        
        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setTaskId("task-123");
        taskStatus.setTaskName("Process Task");
        taskStatus.setTaskKey("key-456");
        taskStatus.setPercentageComplete(75.5f);
        taskStatus.setStatus(Status.RUNNING);

        original.setValue(taskStatus);

        byte[] serialized = serialize(original);
        TaskSend deserialized = deserialize(serialized, TaskSend.class);

        // Verifica que los valores se preservan durante la serialización
        assertEquals(original.getTopicName(), deserialized.getTopicName(), "TopicName should be preserved during serialization");
        assertEquals(original.getKey(), deserialized.getKey(), "Key should be preserved during serialization");
        assertNotNull(deserialized.getValue(), "Value should be preserved during serialization");
        assertEquals(original.getValue().getTaskId(), deserialized.getValue().getTaskId(), "TaskId should be preserved during serialization");
        assertEquals(original.getValue().getTaskName(), deserialized.getValue().getTaskName(), "TaskName should be preserved during serialization");
        assertEquals(original.getValue().getTaskKey(), deserialized.getValue().getTaskKey(), "TaskKey should be preserved during serialization");
        assertEquals(original.getValue().getPercentageComplete(), deserialized.getValue().getPercentageComplete(), "PercentageComplete should be preserved during serialization");
        assertEquals(original.getValue().getStatus(), deserialized.getValue().getStatus(), "Status should be preserved during serialization");
    }

    @Test
    void testEqualsAndHashCode() {
        TaskSend taskSend1 = new TaskSend();
        taskSend1.setTopicName("topic-name");
        taskSend1.setKey("task-key");
        
        TaskStatus taskStatus1 = new TaskStatus();
        taskStatus1.setTaskId("task-123");
        taskStatus1.setTaskName("Process Task");
        taskStatus1.setTaskKey("key-456");
        taskStatus1.setPercentageComplete(75.5f);
        taskStatus1.setStatus(Status.RUNNING);
        taskSend1.setValue(taskStatus1);

        TaskSend taskSend2 = new TaskSend();
        taskSend2.setTopicName("topic-name");
        taskSend2.setKey("task-key");
        
        TaskStatus taskStatus2 = new TaskStatus();
        taskStatus2.setTaskId("task-123");
        taskStatus2.setTaskName("Process Task");
        taskStatus2.setTaskKey("key-456");
        taskStatus2.setPercentageComplete(75.5f);
        taskStatus2.setStatus(Status.RUNNING);
        taskSend2.setValue(taskStatus2);

        // Verifica que los objetos con los mismos valores son iguales
        assertEquals(taskSend1, taskSend2, "TaskSend objects should be equal");

        // Verifica el hashCode
        assertEquals(taskSend1.hashCode(), taskSend2.hashCode(), "TaskSend hashCode should be equal");
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
