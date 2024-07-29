package com.ada.ecosystem.core.v1.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.ada.ecosystem.core.v1.utility.EcosystemUtilities;

public class KafkaMessageTest {

    @Test
    void testNoArgsConstructor() {
        KafkaMessage kafkaMessage = new KafkaMessage();

        // Verifica que el campo 'uuid' se inicializa con un valor no nulo y válido
        assertNotNull(kafkaMessage.getUuid(), "UUID should not be null");
        assertEquals(36, kafkaMessage.getUuid().length(), "UUID should be 36 characters long");

        // Verifica que el campo 'processName' se inicializa con el valor predeterminado de EcosystemUtilities
        assertEquals(EcosystemUtilities.INDEFINITE_PROCESS, kafkaMessage.getProcessName(), "ProcessName should be set to EcosystemUtilities.INDEFINITE_PROCESS by default");

        // Verifica que los otros campos están inicializados a valores por defecto (null)
        assertEquals(null, kafkaMessage.getTopicName(), "TopicName should be null by default");
    }

    @Test
    void testSettersAndGetters() {
        KafkaMessage kafkaMessage = new KafkaMessage();

        // Se establece un valor para los campos
        String uuid = "123e4567-e89b-12d3-a456-426614174000";
        String topicName = "my-topic";
        String processName = "process-name";
        String message = "Hello Kafka";

        kafkaMessage.setUuid(uuid);
        kafkaMessage.setTopicName(topicName);
        kafkaMessage.setProcessName(processName);
        kafkaMessage.setMessage(message);

        // Verifica que los valores se establecen correctamente
        assertEquals(uuid, kafkaMessage.getUuid(), "UUID should be set using setter");
        assertEquals(topicName, kafkaMessage.getTopicName(), "TopicName should be set using setter");
        assertEquals(processName, kafkaMessage.getProcessName(), "ProcessName should be set using setter");
        assertEquals(message, kafkaMessage.getMessage(), "Message should be set using setter");
    }

    @Test
    void testSerialization() throws Exception {
        KafkaMessage original = new KafkaMessage();
        original.setUuid("123e4567-e89b-12d3-a456-426614174000");
        original.setTopicName("my-topic");
        original.setProcessName("process-name");
        original.setMessage("Hello Kafka");

        byte[] serialized = serialize(original);
        KafkaMessage deserialized = deserialize(serialized, KafkaMessage.class);

        // Verifica que los valores se preservan durante la serialización
        assertEquals(original.getUuid(), deserialized.getUuid(), "UUID should be preserved during serialization");
        assertEquals(original.getTopicName(), deserialized.getTopicName(), "TopicName should be preserved during serialization");
        assertEquals(original.getProcessName(), deserialized.getProcessName(), "ProcessName should be preserved during serialization");
        assertEquals(original.getMessage(), deserialized.getMessage(), "Message should be preserved during serialization");
    }

    @Test
    void testEqualsAndHashCode() {
        KafkaMessage message1 = new KafkaMessage();
        message1.setUuid("123e4567-e89b-12d3-a456-426614174000");
        message1.setTopicName("my-topic");
        message1.setProcessName("process-name");
        message1.setMessage("Hello Kafka");

        KafkaMessage message2 = new KafkaMessage();
        message2.setUuid("123e4567-e89b-12d3-a456-426614174000");
        message2.setTopicName("my-topic");
        message2.setProcessName("process-name");
        message2.setMessage("Hello Kafka");

        // Verifica que los objetos con los mismos valores son iguales
        assertEquals(message1, message2, "KafkaMessage objects should be equal");

        // Verifica el hashCode
        assertEquals(message1.hashCode(), message2.hashCode(), "KafkaMessage hashCode should be equal");
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
