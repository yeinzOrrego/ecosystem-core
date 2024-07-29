package com.ada.ecosystem.core.v1.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class NewTopicConfigTest {

    @Test
    void testNoArgsConstructor() {
        NewTopicConfig newTopicConfig = new NewTopicConfig();

        // Verifica que el campo 'configurations' se inicializa como un HashMap vacío
        assertNotNull(newTopicConfig.getConfigurations(), "Configurations should not be null");
        assertEquals(new HashMap<>(), newTopicConfig.getConfigurations(), "Configurations should be an empty HashMap by default");
        
        // Verifica que los otros campos están inicializados a valores por defecto
        assertNull(newTopicConfig.getTopicName(), "TopicName should be null by default");
        assertNull(newTopicConfig.getPartitions(), "Partitions should be null by default");
        assertNull(newTopicConfig.getReplicas(), "Replicas should be null by default");
    }

    @Test
    void testSettersAndGetters() {
        NewTopicConfig newTopicConfig = new NewTopicConfig();

        String topicName = "my-topic";
        Integer partitions = 3;
        Integer replicas = 2;
        Map<String, Object> configurations = new HashMap<>();
        configurations.put("cleanup.policy", "compact");

        newTopicConfig.setTopicName(topicName);
        newTopicConfig.setPartitions(partitions);
        newTopicConfig.setReplicas(replicas);
        newTopicConfig.setConfigurations(configurations);

        // Verifica que los valores se establecen correctamente
        assertEquals(topicName, newTopicConfig.getTopicName(), "TopicName should be set using setter");
        assertEquals(partitions, newTopicConfig.getPartitions(), "Partitions should be set using setter");
        assertEquals(replicas, newTopicConfig.getReplicas(), "Replicas should be set using setter");
        assertEquals(configurations, newTopicConfig.getConfigurations(), "Configurations should be set using setter");
    }

    @Test
    void testSerialization() throws Exception {
        NewTopicConfig original = new NewTopicConfig();
        original.setTopicName("my-topic");
        original.setPartitions(3);
        original.setReplicas(2);
        Map<String, Object> configurations = new HashMap<>();
        configurations.put("cleanup.policy", "compact");
        original.setConfigurations(configurations);

        byte[] serialized = serialize(original);
        NewTopicConfig deserialized = deserialize(serialized, NewTopicConfig.class);

        // Verifica que los valores se preservan durante la serialización
        assertEquals(original.getTopicName(), deserialized.getTopicName(), "TopicName should be preserved during serialization");
        assertEquals(original.getPartitions(), deserialized.getPartitions(), "Partitions should be preserved during serialization");
        assertEquals(original.getReplicas(), deserialized.getReplicas(), "Replicas should be preserved during serialization");
        assertEquals(original.getConfigurations(), deserialized.getConfigurations(), "Configurations should be preserved during serialization");
    }

    @Test
    void testEqualsAndHashCode() {
        NewTopicConfig config1 = new NewTopicConfig();
        config1.setTopicName("my-topic");
        config1.setPartitions(3);
        config1.setReplicas(2);
        Map<String, Object> configurations = new HashMap<>();
        configurations.put("cleanup.policy", "compact");
        config1.setConfigurations(configurations);

        NewTopicConfig config2 = new NewTopicConfig();
        config2.setTopicName("my-topic");
        config2.setPartitions(3);
        config2.setReplicas(2);
        config2.setConfigurations(configurations);

        // Verifica que los objetos con los mismos valores son iguales
        assertEquals(config1, config2, "NewTopicConfig objects should be equal");

        // Verifica el hashCode
        assertEquals(config1.hashCode(), config2.hashCode(), "NewTopicConfig hashCode should be equal");
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
