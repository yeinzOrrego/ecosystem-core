package com.ada.ecosystem.core.v1.enums;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ExecutionContextTest {

    @Test
    public void testDevValue() {
        // Arrange
        ExecutionContext context = ExecutionContext.DEV;

        // Act
        String value = context.getValue();

        // Assert
        assertThat(value).isEqualTo("dev");
    }

    @Test
    public void testQaValue() {
        // Arrange
        ExecutionContext context = ExecutionContext.QA;

        // Act
        String value = context.getValue();

        // Assert
        assertThat(value).isEqualTo("qa");
    }

    @Test
    public void testPreValue() {
        // Arrange
        ExecutionContext context = ExecutionContext.PRE;

        // Act
        String value = context.getValue();

        // Assert
        assertThat(value).isEqualTo("pre");
    }

    @Test
    public void testProdValue() {
        // Arrange
        ExecutionContext context = ExecutionContext.PROD;

        // Act
        String value = context.getValue();

        // Assert
        assertThat(value).isEqualTo("prod");
    }

    @Test
    public void testToString() {
        // Arrange
        ExecutionContext context = ExecutionContext.PROD;

        // Act
        String result = context.toString();

        // Assert
        assertThat(result).isEqualTo("prod");
    }
}
