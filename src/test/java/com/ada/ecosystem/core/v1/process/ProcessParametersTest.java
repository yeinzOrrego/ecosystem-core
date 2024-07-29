package com.ada.ecosystem.core.v1.process;

import com.ada.ecosystem.core.v1.kafka.TaskSend;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessParametersTest {

    @Test
    void testGettersAndSetters() {
        ProcessParameters<String> parameters = new ProcessParameters<>();

        // Set values
        String codigoCliente = "cliente123";
        String codigoEmpresa = "empresa123";
        Long codigoUsuario = 123L;
        Date fecha = new Date();
        Boolean debug = true;
        TaskSend taskSend = new TaskSend(); // Suponiendo que TaskSend es una clase simple
        String param = "paramValue";
        ProcessType processType = ProcessType.PROCESO_ESTANDAR;

        parameters.setCodigoCliente(codigoCliente);
        parameters.setCodigoEmpresa(codigoEmpresa);
        parameters.setCodigoUsuario(codigoUsuario);
        parameters.setFecha(fecha);
        parameters.setDebug(debug);
        parameters.setTaskSend(taskSend);
        parameters.setParam(param);
        parameters.setProcessType(processType);

        // Assertions
        assertEquals(codigoCliente, parameters.getCodigoCliente());
        assertEquals(codigoEmpresa, parameters.getCodigoEmpresa());
        assertEquals(codigoUsuario, parameters.getCodigoUsuario());
        assertEquals(fecha, parameters.getFecha());
        assertEquals(debug, parameters.getDebug());
        assertEquals(taskSend, parameters.getTaskSend());
        assertEquals(param, parameters.getParam());
        assertEquals(processType, parameters.getProcessType());
    }

    @Test
    void testToString() {
        ProcessParameters<String> parameters = new ProcessParameters<>();
        parameters.setCodigoCliente("cliente123");
        parameters.setCodigoEmpresa("empresa123");

        String expectedString = "ProcessParameters(codigoCliente=cliente123, codigoEmpresa=empresa123, " +
                "codigoUsuario=null, fecha=null, debug=null, taskSend=null, param=null, processType=PROCESO_ESTANDAR)";
        assertEquals(expectedString, parameters.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        ProcessParameters<String> parameters1 = new ProcessParameters<>();
        parameters1.setCodigoCliente("cliente123");
        parameters1.setCodigoEmpresa("empresa123");

        ProcessParameters<String> parameters2 = new ProcessParameters<>();
        parameters2.setCodigoCliente("cliente123");
        parameters2.setCodigoEmpresa("empresa123");

        assertEquals(parameters1, parameters2);
        assertEquals(parameters1.hashCode(), parameters2.hashCode());

        parameters2.setCodigoCliente("cliente456");
        assertNotEquals(parameters1, parameters2);
    }

    @Test
    void testDefaultConstructor() {
        ProcessParameters<String> parameters = new ProcessParameters<>();
        assertEquals(ProcessType.PROCESO_ESTANDAR, parameters.getProcessType(), "Default process type should be PROCESO_ESTANDAR");
    }
}
