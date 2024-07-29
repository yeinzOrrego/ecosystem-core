package com.ada.ecosystem.core.v1.email.dto;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class GenericResponseDtoTest {

    @Test
    public void testDefaultConstructor() {
        // Act
        GenericResponseDto<String> dto = new GenericResponseDto<>();

        // Assert
        assertThat(dto.getCode()).isNull();
        assertThat(dto.getMessage()).isNull();
        assertThat(dto.getContent()).isNull();
    }

    @Test
    public void testConstructorWithCodeAndMessage() {
        // Act
        GenericResponseDto<String> dto = new GenericResponseDto<>(200L, "Success");

        // Assert
        assertThat(dto.getCode()).isEqualTo(200L);
        assertThat(dto.getMessage()).isEqualTo("Success");
        assertThat(dto.getContent()).isNull();
    }

    @Test
    public void testConstructorWithAllFields() {
        // Act
        String content = "Response Content";
        GenericResponseDto<String> dto = new GenericResponseDto<>(200L, "Success", content);

        // Assert
        assertThat(dto.getCode()).isEqualTo(200L);
        assertThat(dto.getMessage()).isEqualTo("Success");
        assertThat(dto.getContent()).isEqualTo(content);
    }

    @Test
    public void testGettersAndSetters() {
        // Arrange
        GenericResponseDto<String> dto = new GenericResponseDto<>();
        dto.setCode(404L);
        dto.setMessage("Not Found");
        dto.setContent("Some content");

        // Act
        Long code = dto.getCode();
        String message = dto.getMessage();
        String content = dto.getContent();

        // Assert
        assertThat(code).isEqualTo(404L);
        assertThat(message).isEqualTo("Not Found");
        assertThat(content).isEqualTo("Some content");
    }

    @Test
    public void testToString() {
        // Arrange
        GenericResponseDto<String> dto = new GenericResponseDto<>(500L, "Error", "Error details");

        // Act
        String toString = dto.toString();

        // Assert
        assertThat(toString).contains("code=500");
        assertThat(toString).contains("message=Error");
        assertThat(toString).contains("content=Error details");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Arrange
        GenericResponseDto<String> dto1 = new GenericResponseDto<>(200L, "Success", "Content");
        GenericResponseDto<String> dto2 = new GenericResponseDto<>(200L, "Success", "Content");
        GenericResponseDto<String> dto3 = new GenericResponseDto<>(404L, "Not Found", "Other content");

        // Act & Assert
        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1).isNotEqualTo(dto3);
        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
        assertThat(dto1.hashCode()).isNotEqualTo(dto3.hashCode());
    }

    @Test
    public void testSettersWithNullValues() {
        // Arrange
        GenericResponseDto<String> dto = new GenericResponseDto<>();

        // Act
        dto.setCode(null);
        dto.setMessage(null);
        dto.setContent(null);

        // Assert
        assertThat(dto.getCode()).isNull();
        assertThat(dto.getMessage()).isNull();
        assertThat(dto.getContent()).isNull();
    }
}
