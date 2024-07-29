package com.ada.ecosystem.core.v1.database;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DatabaseEnvironmentTest {

    @Test
    public void testEnumValues() {
        // Verificar los nombres de los enums
        assertEquals("dev", DatabaseEnvironment.DEVELOPMENT.getName());
        assertEquals("qa", DatabaseEnvironment.TESTING.getName());
        assertEquals("prod", DatabaseEnvironment.PRODUCTION.getName());
        assertEquals("client", DatabaseEnvironment.PRESENTATION.getName());
    }

    @Test
    public void testEnumConstants() {
        // Verificar que los enum constants estén presentes
        assertNotNull(DatabaseEnvironment.valueOf("DEVELOPMENT"));
        assertNotNull(DatabaseEnvironment.valueOf("TESTING"));
        assertNotNull(DatabaseEnvironment.valueOf("PRODUCTION"));
        assertNotNull(DatabaseEnvironment.valueOf("PRESENTATION"));
    }

    @Test
    public void testEnumCount() {
        // Verificar el número de valores del enum
        assertEquals(4, DatabaseEnvironment.values().length);
    }

    @Test
    public void testEnumNames() {
        // Verificar los nombres de los valores del enum
        String[] expectedNames = {"DEVELOPMENT", "TESTING", "PRODUCTION", "PRESENTATION"};
        DatabaseEnvironment[] environments = DatabaseEnvironment.values();
        for (int i = 0; i < environments.length; i++) {
            assertEquals(expectedNames[i], environments[i].name());
        }
    }
}
