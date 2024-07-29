package com.ada.ecosystem.core.v1.query;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SortOperationTest {

    @Test
    void testEnumValues() {
        // Verifica que los valores del enum sean los esperados
        assertArrayEquals(new SortOperation[] { SortOperation.ASC, SortOperation.DESC }, SortOperation.values());
    }

    @Test
    void testEnumValueOf() {
        // Verifica que el método valueOf devuelve los valores correctos
        assertEquals(SortOperation.ASC, SortOperation.valueOf("ASC"));
        assertEquals(SortOperation.DESC, SortOperation.valueOf("DESC"));
    }

    @Test
    void testEnumName() {
        // Verifica que los nombres de los valores del enum sean los esperados
        assertEquals("ASC", SortOperation.ASC.name());
        assertEquals("DESC", SortOperation.DESC.name());
    }

    @Test
    void testEnumOrdinal() {
        // Verifica que los ordinales sean los esperados
        assertEquals(0, SortOperation.ASC.ordinal());
        assertEquals(1, SortOperation.DESC.ordinal());
    }

}
