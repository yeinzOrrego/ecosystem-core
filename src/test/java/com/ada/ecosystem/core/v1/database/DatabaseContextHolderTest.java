package com.ada.ecosystem.core.v1.database;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseContextHolderTest {

    @Test
    public void testSetAndGetDatabaseContext() {
        String database = "TEST_DB";

        // Establecer el contexto de la base de datos
        DatabaseContextHolder.setDatabaseContext(database);

        // Verificar que el contexto se haya establecido correctamente
        assertEquals(database, DatabaseContextHolder.getDatabaseContext());
    }

    @Test
    public void testClearDatabaseContext() {
        String database = "TEST_DB";

        // Establecer el contexto de la base de datos
        DatabaseContextHolder.setDatabaseContext(database);

        // Limpiar el contexto de la base de datos
        DatabaseContextHolder.clearDatabaseContext();

        // Verificar que el contexto esté vacío después de limpiar
        assertNull(DatabaseContextHolder.getDatabaseContext());
    }

    @Test
    public void testInitialContextIsNull() {
        // Verificar que inicialmente el contexto sea null
        assertNull(DatabaseContextHolder.getDatabaseContext());
    }
}
