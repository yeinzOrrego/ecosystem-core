package com.ada.ecosystem.core.v1.database;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DatabaseTypeEnumTest {

    @Test
    public void testEnumValues() {
        // Verificar los nombres de los enums
        assertEquals("oracle", DatabaseTypeEnum.ORACLE.getName());
        assertEquals("postgresq", DatabaseTypeEnum.POSTGRESQL.getName());
        assertEquals("mysql", DatabaseTypeEnum.MYSQL.getName());
    }

    @Test
    public void testEnumConstants() {
        // Verificar que los enum constants estén presentes
        assertNotNull(DatabaseTypeEnum.valueOf("ORACLE"));
        assertNotNull(DatabaseTypeEnum.valueOf("POSTGRESQL"));
        assertNotNull(DatabaseTypeEnum.valueOf("MYSQL"));
    }

    @Test
    public void testEnumCount() {
        // Verificar el número de valores del enum
        assertEquals(3, DatabaseTypeEnum.values().length);
    }

    @Test
    public void testEnumNames() {
        // Verificar los nombres de los valores del enum
        String[] expectedNames = {"ORACLE", "POSTGRESQL", "MYSQL"};
        DatabaseTypeEnum[] types = DatabaseTypeEnum.values();
        for (int i = 0; i < types.length; i++) {
            assertEquals(expectedNames[i], types[i].name());
        }
    }
}
