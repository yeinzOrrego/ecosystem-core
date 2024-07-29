package com.ada.ecosystem.core.v1.process.documentgenerator;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class InputParametersTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        InputParameters inputParameters = new InputParameters();
        Long expectedCodigoComercial = 123L;
        Long expectedCodigoDocumento = 456L;
        Long expectedConsecutivoDocumento = 789L;
        Date expectedFechaDocumento = new Date();
        String expectedDescripcion = "Descripción del documento";
        Long expectedCodigoTercero = 101112L;
        String expectedTipo = "Tipo de documento";
        String expectedEstado = "Activo";

        // Act
        inputParameters.setCodigoComercial(expectedCodigoComercial);
        inputParameters.setCodigoDocumento(expectedCodigoDocumento);
        inputParameters.setConsecutivoDocumento(expectedConsecutivoDocumento);
        inputParameters.setFechaDocumento(expectedFechaDocumento);
        inputParameters.setDescripcion(expectedDescripcion);
        inputParameters.setCodigoTercero(expectedCodigoTercero);
        inputParameters.setTipo(expectedTipo);
        inputParameters.setEstado(expectedEstado);

        // Assert
        assertEquals(expectedCodigoComercial, inputParameters.getCodigoComercial());
        assertEquals(expectedCodigoDocumento, inputParameters.getCodigoDocumento());
        assertEquals(expectedConsecutivoDocumento, inputParameters.getConsecutivoDocumento());
        assertEquals(expectedFechaDocumento, inputParameters.getFechaDocumento());
        assertEquals(expectedDescripcion, inputParameters.getDescripcion());
        assertEquals(expectedCodigoTercero, inputParameters.getCodigoTercero());
        assertEquals(expectedTipo, inputParameters.getTipo());
        assertEquals(expectedEstado, inputParameters.getEstado());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Arrange
        InputParameters inputParameters1 = new InputParameters();
        inputParameters1.setCodigoComercial(123L);
        inputParameters1.setCodigoDocumento(456L);

        InputParameters inputParameters2 = new InputParameters();
        inputParameters2.setCodigoComercial(123L);
        inputParameters2.setCodigoDocumento(456L);

        InputParameters inputParameters3 = new InputParameters();
        inputParameters3.setCodigoComercial(789L);
        inputParameters3.setCodigoDocumento(101L);

        // Act & Assert
        assertEquals(inputParameters1, inputParameters2, "Los objetos deberían ser iguales");
        assertNotEquals(inputParameters1, inputParameters3, "Los objetos no deberían ser iguales");
        assertEquals(inputParameters1.hashCode(), inputParameters2.hashCode(), "Los hashcodes deberían ser iguales");
        assertNotEquals(inputParameters1.hashCode(), inputParameters3.hashCode(), "Los hashcodes no deberían ser iguales");
    }

    @Test
    public void testToString() {
        // Arrange
        InputParameters inputParameters = new InputParameters();
        inputParameters.setCodigoComercial(123L);
        inputParameters.setCodigoDocumento(456L);
        inputParameters.setDescripcion("Descripción de prueba");

        // Act
        String result = inputParameters.toString();

        // Assert
        assertTrue(result.contains("123"), "Debería contener el código comercial");
        assertTrue(result.contains("456"), "Debería contener el código de documento");
        assertTrue(result.contains("Descripción de prueba"), "Debería contener la descripción");
    }
}
