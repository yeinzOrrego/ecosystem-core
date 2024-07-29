package com.ada.ecosystem.core.v1.query;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogicalOperatorTest {

    @Test
    public void testEnumValues() {
        // Verify that the enum constants are present and have the expected names
        assertArrayEquals(new LogicalOperator[]{LogicalOperator.AND, LogicalOperator.OR}, LogicalOperator.values(),
                "Los valores del enum LogicalOperator no coinciden con los esperados");
    }

    @Test
    public void testEnumValuesOrder() {
        // Verify the order of the enum constants
        LogicalOperator[] expectedOrder = {LogicalOperator.AND, LogicalOperator.OR};
        LogicalOperator[] actualOrder = LogicalOperator.values();
        assertArrayEquals(expectedOrder, actualOrder,
                "El orden de los valores del enum LogicalOperator no coincide con el esperado");
    }

    @Test
    public void testEnumValueOf() {
        // Verify that the valueOf method returns the correct enum constant
        assertEquals(LogicalOperator.AND, LogicalOperator.valueOf("AND"),
                "El método valueOf debería devolver AND");
        assertEquals(LogicalOperator.OR, LogicalOperator.valueOf("OR"),
                "El método valueOf debería devolver OR");
    }
}
