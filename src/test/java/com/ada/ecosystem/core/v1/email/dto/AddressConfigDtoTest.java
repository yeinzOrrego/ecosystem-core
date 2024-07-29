package com.ada.ecosystem.core.v1.email.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

public class AddressConfigDtoTest {

    @Test
    public void testDefaultConstructor() {
        // Act
        AddressConfigDto dto = new AddressConfigDto();

        // Assert
        assertNull(dto.getAddress(), "Default address should be null");
        assertNull(dto.getPersona(), "Default persona should be null");
    }

    @Test
    public void testGettersAndSetters() {
        // Arrange
        String address = "test@example.com";
        String persona = "Test User";
        AddressConfigDto dto = new AddressConfigDto();
        dto.setAddress(address);
        dto.setPersona(persona);

        // Act & Assert
        assertThat(dto.getAddress()).isEqualTo(address);
        assertThat(dto.getPersona()).isEqualTo(persona);
    }

    @Test
    public void testToString() {
        // Arrange
        String address = "test@example.com";
        String persona = "Test User";
        AddressConfigDto dto = new AddressConfigDto();
        dto.setAddress(address);
        dto.setPersona(persona);

        // Act
        String toString = dto.toString();

        // Assert
        assertThat(toString).contains("AddressConfigDto(address=test@example.com, persona=Test User)");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Arrange
        AddressConfigDto dto1 = new AddressConfigDto();
        dto1.setAddress("test@example.com");
        dto1.setPersona("Test User");

        AddressConfigDto dto2 = new AddressConfigDto();
        dto2.setAddress("test@example.com");
        dto2.setPersona("Test User");

        AddressConfigDto dto3 = new AddressConfigDto();
        dto3.setAddress("different@example.com");
        dto3.setPersona("Different User");

        // Act & Assert
        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1).isNotEqualTo(dto3);
        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
        assertThat(dto1.hashCode()).isNotEqualTo(dto3.hashCode());
    }

    @Test
    public void testSerialization() {
        // Arrange
        AddressConfigDto dto = new AddressConfigDto();
        dto.setAddress("test@example.com");
        dto.setPersona("Test User");

        // Act & Assert
        assertDoesNotThrow(() -> {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(dto);
            }
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                AddressConfigDto deserializedDto = (AddressConfigDto) ois.readObject();
                assertThat(deserializedDto).isEqualTo(dto);
            }
        });
    }

    @Test
    public void testNullValues() {
        // Arrange
        AddressConfigDto dto = new AddressConfigDto();
        dto.setAddress(null);
        dto.setPersona(null);

        // Act & Assert
        assertThat(dto.getAddress()).isNull();
        assertThat(dto.getPersona()).isNull();
    }

    @Test
    public void testSerialVersionUID() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        long expectedSerialVersionUID = 1L;
        Field serialVersionUIDField = AddressConfigDto.class.getDeclaredField("serialVersionUID");
        serialVersionUIDField.setAccessible(true); // Make the field accessible
        long actualSerialVersionUID = serialVersionUIDField.getLong(null); // static field, so pass null

        // Act & Assert
        assertThat(actualSerialVersionUID).isEqualTo(expectedSerialVersionUID);
    }

    @Test
    public void testEqualityWithDifferentTypes() {
        // Arrange
        AddressConfigDto dto = new AddressConfigDto();
        dto.setAddress("test@example.com");
        dto.setPersona("Test User");

        // Act & Assert
        assertThat(dto).isNotEqualTo(new Object()); // Ensure not equal to different type
    }
}
