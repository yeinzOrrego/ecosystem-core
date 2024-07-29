package com.ada.ecosystem.core.v1.process;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessTypeTest {

    @Test
    void testGetValue() {
        assertEquals("PAGO_CONTRATISTA", ProcessType.PAGO_CONTRATISTA.getValue(), "The value for PAGO_CONTRATISTA should be 'PAGO_CONTRATISTA'");
        assertEquals("PROCESO_ESTANDAR", ProcessType.PROCESO_ESTANDAR.getValue(), "The value for PROCESO_ESTANDAR should be 'PROCESO_ESTANDAR'");
    }

    @Test
    void testToString() {
        assertEquals("PAGO_CONTRATISTA", ProcessType.PAGO_CONTRATISTA.toString(), "The toString() method should return 'PAGO_CONTRATISTA'");
        assertEquals("PROCESO_ESTANDAR", ProcessType.PROCESO_ESTANDAR.toString(), "The toString() method should return 'PROCESO_ESTANDAR'");
    }

    @Test
    void testEnumValues() {
        // Check if all enum constants are present
        assertArrayEquals(new ProcessType[] {
                ProcessType.PAGO_CONTRATISTA,
                ProcessType.PROCESO_ESTANDAR
        }, ProcessType.values(), "The values() method should return all defined enum constants");
    }

    @Test
    void testEnumValueOf() {
        assertEquals(ProcessType.PAGO_CONTRATISTA, ProcessType.valueOf("PAGO_CONTRATISTA"), "The valueOf method should return PAGO_CONTRATISTA enum constant");
        assertEquals(ProcessType.PROCESO_ESTANDAR, ProcessType.valueOf("PROCESO_ESTANDAR"), "The valueOf method should return PROCESO_ESTANDAR enum constant");
        
        // Ensure an IllegalArgumentException is thrown for invalid value
        assertThrows(IllegalArgumentException.class, () -> ProcessType.valueOf("INVALID_TYPE"), "The valueOf method should throw IllegalArgumentException for invalid enum constant");
    }
}
