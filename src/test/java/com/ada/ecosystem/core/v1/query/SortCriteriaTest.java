package com.ada.ecosystem.core.v1.query;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SortCriteriaTest {

    @Test
    void testSortCriteriaConstructorAndGetters() {
        // Arrange
        String column = "name";
        SortOperation direction = SortOperation.ASC;

        // Act
        SortCriteria sortCriteria = new SortCriteria(column, direction);

        // Assert
        assertEquals(column, sortCriteria.getColumn(), "Column should be correctly set");
        assertEquals(direction, sortCriteria.getDirection(), "Direction should be correctly set");
    }

    @Test
    void testDefaultSortCriteria() {
        // Act
        SortCriteria sortCriteria = new SortCriteria(null, null);

        // Assert
        assertNull(sortCriteria.getColumn(), "Column should be null");
        assertNull(sortCriteria.getDirection(), "Direction should be null");
    }

    @Test
    void testSetters() {
        // Arrange
        SortCriteria sortCriteria = new SortCriteria("name", SortOperation.ASC);

        // Act
        sortCriteria.setColumn("age");
        sortCriteria.setDirection(SortOperation.DESC);

        // Assert
        assertEquals("age", sortCriteria.getColumn(), "Column should be updated");
        assertEquals(SortOperation.DESC, sortCriteria.getDirection(), "Direction should be updated");
    }

    @Test
    void testSerialization() throws Exception {
        // Arrange
        SortCriteria original = new SortCriteria("name", SortOperation.ASC);

        // Act
        byte[] serialized = serialize(original);
        SortCriteria deserialized = deserialize(serialized, SortCriteria.class);

        // Assert
        assertEquals(original.getColumn(), deserialized.getColumn(), "Column should be preserved during serialization");
        assertEquals(original.getDirection(), deserialized.getDirection(), "Direction should be preserved during serialization");
    }

    @Test
    void testSerializationWithNulls() throws Exception {
        // Arrange
        SortCriteria original = new SortCriteria(null, null);

        // Act
        byte[] serialized = serialize(original);
        SortCriteria deserialized = deserialize(serialized, SortCriteria.class);

        // Assert
        assertNull(deserialized.getColumn(), "Column should be null after deserialization");
        assertNull(deserialized.getDirection(), "Direction should be null after deserialization");
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
