package com.ada.ecosystem.core.v1.param;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParamConfigSearchDtoTest {

    @Test
    void testDefaultConstructor() {
        ParamConfigSearchDto dto = new ParamConfigSearchDto();
        assertEquals("9999999999", dto.getCodigoMempresa(), "Default value for codigoMempresa should be '9999999999'");
    }

    @Test
    void testGettersAndSetters() {
        ParamConfigSearchDto dto = new ParamConfigSearchDto();
        dto.setParamName("paramName");
        dto.setClientCode("clientCode");
        dto.setCodigoMempresa("1234567890");

        assertEquals("paramName", dto.getParamName(), "ParamName should be set correctly");
        assertEquals("clientCode", dto.getClientCode(), "ClientCode should be set correctly");
        assertEquals("1234567890", dto.getCodigoMempresa(), "CodigoMempresa should be set correctly");
    }

    @Test
    void testToString() {
        ParamConfigSearchDto dto = new ParamConfigSearchDto();
        dto.setParamName("paramName");
        dto.setClientCode("clientCode");
        dto.setCodigoMempresa("1234567890");

        String expectedString = "ParamConfigSearchDto(paramName=paramName, clientCode=clientCode, codigoMempresa=1234567890)";
        assertEquals(expectedString, dto.toString(), "toString should return correct string representation");
    }

    @Test
    void testEqualsAndHashCode() {
        ParamConfigSearchDto dto1 = new ParamConfigSearchDto();
        dto1.setParamName("paramName");
        dto1.setClientCode("clientCode");
        dto1.setCodigoMempresa("1234567890");

        ParamConfigSearchDto dto2 = new ParamConfigSearchDto();
        dto2.setParamName("paramName");
        dto2.setClientCode("clientCode");
        dto2.setCodigoMempresa("1234567890");

        assertEquals(dto1, dto2, "Objects with same fields should be equal");
        assertEquals(dto1.hashCode(), dto2.hashCode(), "Hash code should be the same for equal objects");
    }

    @Test
    void testSerialization() {
        ParamConfigSearchDto dto = new ParamConfigSearchDto();
        dto.setParamName("paramName");
        dto.setClientCode("clientCode");
        dto.setCodigoMempresa("1234567890");

        assertDoesNotThrow(() -> {
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos)) {
                oos.writeObject(dto);
            }
            java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(baos.toByteArray());
            try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais)) {
                ParamConfigSearchDto deserializedDto = (ParamConfigSearchDto) ois.readObject();
                assertEquals(dto.getParamName(), deserializedDto.getParamName(), "Deserialized paramName should match");
                assertEquals(dto.getClientCode(), deserializedDto.getClientCode(), "Deserialized clientCode should match");
                assertEquals(dto.getCodigoMempresa(), deserializedDto.getCodigoMempresa(), "Deserialized codigoMempresa should match");
            }
        });
    }
}
