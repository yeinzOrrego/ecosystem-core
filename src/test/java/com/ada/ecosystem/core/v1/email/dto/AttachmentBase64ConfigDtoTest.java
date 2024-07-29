package com.ada.ecosystem.core.v1.email.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.Test;

public class AttachmentBase64ConfigDtoTest {

    @Test
    public void testDefaultConstructor() {
        // Act
        AttachmentBase64ConfigDto dto = new AttachmentBase64ConfigDto();

        // Assert
        assertThat(dto.getFileName()).isNull();
        assertThat(dto.getFileContentStringBase64()).isNull();
        assertThat(dto.getTypeContent()).isNull();
    }

    @Test
    public void testGettersAndSetters() {
        // Arrange
        AttachmentBase64ConfigDto dto = new AttachmentBase64ConfigDto();
        dto.setFileName("testFile.txt");
        dto.setFileContentStringBase64("dGVzdCBjb250ZW50"); // "test content" en base64
        dto.setTypeContent("text/plain");

        // Act & Assert
        assertThat(dto.getFileName()).isEqualTo("testFile.txt");
        assertThat(dto.getFileContentStringBase64()).isEqualTo("dGVzdCBjb250ZW50");
        assertThat(dto.getTypeContent()).isEqualTo("text/plain");
    }

    @Test
    public void testToString() {
        // Arrange
        AttachmentBase64ConfigDto dto = new AttachmentBase64ConfigDto();
        dto.setFileName("testFile.txt");
        dto.setFileContentStringBase64("dGVzdCBjb250ZW50");
        dto.setTypeContent("text/plain");

        // Act
        String toString = dto.toString();

        // Assert
        assertThat(toString).contains("fileName=testFile.txt");
        assertThat(toString).contains("fileContentStringBase64=dGVzdCBjb250ZW50");
        assertThat(toString).contains("typeContent=text/plain");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Arrange
        AttachmentBase64ConfigDto dto1 = new AttachmentBase64ConfigDto();
        dto1.setFileName("testFile.txt");
        dto1.setFileContentStringBase64("dGVzdCBjb250ZW50");
        dto1.setTypeContent("text/plain");

        AttachmentBase64ConfigDto dto2 = new AttachmentBase64ConfigDto();
        dto2.setFileName("testFile.txt");
        dto2.setFileContentStringBase64("dGVzdCBjb250ZW50");
        dto2.setTypeContent("text/plain");

        AttachmentBase64ConfigDto dto3 = new AttachmentBase64ConfigDto();
        dto3.setFileName("differentFile.txt");
        dto3.setFileContentStringBase64("dGVzdCBjb250ZW50");
        dto3.setTypeContent("text/plain");

        // Act & Assert
        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1).isNotEqualTo(dto3);
        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
        assertThat(dto1.hashCode()).isNotEqualTo(dto3.hashCode());
    }

    @Test
    public void testSerialization() {
        // Arrange
        AttachmentBase64ConfigDto dto = new AttachmentBase64ConfigDto();
        dto.setFileName("testFile.txt");
        dto.setFileContentStringBase64("dGVzdCBjb250ZW50");
        dto.setTypeContent("text/plain");

        // Act & Assert
        assertDoesNotThrow(() -> {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(dto);
            }
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                AttachmentBase64ConfigDto deserializedDto = (AttachmentBase64ConfigDto) ois.readObject();
                assertThat(deserializedDto).isEqualTo(dto);
            }
        });
    }

    @Test
    public void testNullValues() {
        // Arrange
        AttachmentBase64ConfigDto dto = new AttachmentBase64ConfigDto();
        dto.setFileName(null);
        dto.setFileContentStringBase64(null);
        dto.setTypeContent(null);

        // Act & Assert
        assertThat(dto.getFileName()).isNull();
        assertThat(dto.getFileContentStringBase64()).isNull();
        assertThat(dto.getTypeContent()).isNull();
    }

    @Test
    public void testSerialVersionUID() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        long expectedSerialVersionUID = 1L;

        // Act
        java.lang.reflect.Field serialVersionUIDField = AttachmentBase64ConfigDto.class.getDeclaredField("serialVersionUID");
        serialVersionUIDField.setAccessible(true); // Make the field accessible
        long actualSerialVersionUID = serialVersionUIDField.getLong(null); // static field, so pass null

        // Assert
        assertThat(actualSerialVersionUID).isEqualTo(expectedSerialVersionUID);
    }

    @Test
    public void testEqualityWithDifferentTypes() {
        // Arrange
        AttachmentBase64ConfigDto dto = new AttachmentBase64ConfigDto();
        dto.setFileName("testFile.txt");
        dto.setFileContentStringBase64("dGVzdCBjb250ZW50");
        dto.setTypeContent("text/plain");

        // Act & Assert
        assertThat(dto).isNotEqualTo(new Object()); // Ensure not equal to different type
    }
}
