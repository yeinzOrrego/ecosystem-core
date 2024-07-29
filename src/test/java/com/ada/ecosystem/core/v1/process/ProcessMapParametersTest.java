package com.ada.ecosystem.core.v1.process;

import com.ada.ecosystem.core.v1.kafka.TaskSend;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessMapParametersTest {

    @Test
    void testGettersAndSetters() {
        ProcessMapParameters parameters = new ProcessMapParameters();

        // Set values
        String codigoCliente = "cliente123";
        String codigoEmpresa = "empresa123";
        Long codigoUsuario = 123L;
        Date fecha = new Date();
        Boolean debug = true;
        TaskSend taskSend = new TaskSend(); // Suponiendo que TaskSend es una clase simple
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("key", "value");

        parameters.setCodigoCliente(codigoCliente);
        parameters.setCodigoEmpresa(codigoEmpresa);
        parameters.setCodigoUsuario(codigoUsuario);
        parameters.setFecha(fecha);
        parameters.setDebug(debug);
        parameters.setTaskSend(taskSend);
        parameters.setParamMap(paramMap);

        // Assertions
        assertEquals(codigoCliente, parameters.getCodigoCliente());
        assertEquals(codigoEmpresa, parameters.getCodigoEmpresa());
        assertEquals(codigoUsuario, parameters.getCodigoUsuario());
        assertEquals(fecha, parameters.getFecha());
        assertEquals(debug, parameters.getDebug());
        assertEquals(taskSend, parameters.getTaskSend());
        assertEquals(paramMap, parameters.getParamMap());
    }

    @Test
    void testToString() {
        ProcessMapParameters parameters = new ProcessMapParameters();
        parameters.setCodigoCliente("cliente123");
        parameters.setCodigoEmpresa("empresa123");

        String expectedString = "ProcessMapParameters(codigoCliente=cliente123, codigoEmpresa=empresa123, " +
                "codigoUsuario=null, fecha=null, debug=null, taskSend=null, paramMap=null)";
        assertEquals(expectedString, parameters.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        ProcessMapParameters parameters1 = new ProcessMapParameters();
        parameters1.setCodigoCliente("cliente123");
        parameters1.setCodigoEmpresa("empresa123");

        ProcessMapParameters parameters2 = new ProcessMapParameters();
        parameters2.setCodigoCliente("cliente123");
        parameters2.setCodigoEmpresa("empresa123");

        assertEquals(parameters1, parameters2);
        assertEquals(parameters1.hashCode(), parameters2.hashCode());

        parameters2.setCodigoCliente("cliente456");
        assertNotEquals(parameters1, parameters2);
    }
}
