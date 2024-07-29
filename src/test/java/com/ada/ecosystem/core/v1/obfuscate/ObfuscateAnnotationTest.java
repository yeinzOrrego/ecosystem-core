package com.ada.ecosystem.core.v1.obfuscate;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.annotation.*;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

public class ObfuscateAnnotationTest {

    @Test
    void testAnnotationPresence() throws NoSuchFieldException {
        // Define a class with a field annotated with @Obfuscate
        class TestClass {
            @Obfuscate
            private String sensitiveField;
        }

        // Retrieve the field from the class
        Field field = TestClass.class.getDeclaredField("sensitiveField");

        // Check if the field is annotated with @Obfuscate
        Annotation annotation = field.getAnnotation(Obfuscate.class);
        assertTrue(annotation instanceof Obfuscate, "The field should be annotated with @Obfuscate");
    }

    @Test
    void testAnnotationRetention() {
        // Check if @Obfuscate is retained at runtime
        boolean isRuntimeRetention = Obfuscate.class.isAnnotationPresent(Retention.class)
                && Obfuscate.class.getAnnotation(Retention.class).value() == RetentionPolicy.RUNTIME;
        assertTrue(isRuntimeRetention, "@Obfuscate should have RetentionPolicy.RUNTIME");
    }

    @Test
    void testAnnotationTarget() {
        // Check if @Obfuscate can only be applied to fields
        boolean isFieldTarget = Obfuscate.class.isAnnotationPresent(Target.class)
                && Obfuscate.class.getAnnotation(Target.class).value()[0] == ElementType.FIELD;
        assertTrue(isFieldTarget, "@Obfuscate should only be applicable to fields");
    }
}
