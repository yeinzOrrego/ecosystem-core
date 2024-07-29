package com.ada.ecosystem.core.v1.process;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.jupiter.api.Test;

class ProcessResultTest {

    @Test
    void testGettersAndSetters() {
        ProcessResult<String> result = new ProcessResult<>();
        result.setResultCode(123L);
        result.setResultMessage("Success");
        result.setResultLog(new StringBuilder("Log message"));
        result.setResult("Result data");

        assertEquals(123L, result.getResultCode());
        assertEquals("Success", result.getResultMessage());
        assertEquals("Log message", result.getResultLog().toString());
        assertEquals("Result data", result.getResult());
    }

    @Test
    void testToString() {
        ProcessResult<String> result = new ProcessResult<>();
        result.setResultCode(123L);
        result.setResultMessage("Success");
        result.setResultLog(new StringBuilder("Log message"));
        result.setResult("Result data");

        String resultString = result.toString();
        assertTrue(resultString.contains("resultCode=123"));
        assertTrue(resultString.contains("resultMessage=Success"));
        assertTrue(resultString.contains("resultLog=Log message"));
        assertTrue(resultString.contains("result=Result data"));
    }

    @Test
    void testEqualsAndHashCode() {
        ProcessResult<String> result1 = new ProcessResult<>();
        result1.setResultCode(123L);
        result1.setResultMessage("Success");

        ProcessResult<String> result2 = new ProcessResult<>();
        result2.setResultCode(123L);
        result2.setResultMessage("Success");

        assertEquals(result1, result2);
        assertEquals(result1.hashCode(), result2.hashCode());

        result2.setResultMessage("Failure");
        assertNotEquals(result1, result2);
        assertNotEquals(result1.hashCode(), result2.hashCode());
    }

    @Test
    void testSerializable() {
        assertTrue(Serializable.class.isAssignableFrom(ProcessResult.class));
    }

    @SuppressWarnings("unchecked")
	@Test
    void testTransientFieldSerialization() throws Exception {
        ProcessResult<String> result = new ProcessResult<>();
        result.setResult("Some data");

        // Serialize the object
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(result);
        oos.close();

        // Deserialize the object
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        ProcessResult<String> deserializedResult = (ProcessResult<String>) ois.readObject();
        ois.close();

        // Check if the transient field is null
        assertNull(deserializedResult.getResult());
    }

    @Test
    void testNullFields() {
        ProcessResult<String> result = new ProcessResult<>();
        result.setResultLog(null);
        assertNull(result.getResultLog());

        result.setResultMessage(null);
        assertNull(result.getResultMessage());
    }

    // Helper method for testing serialization, you can implement this or use a library like Apache Commons Lang
    @SuppressWarnings("unused")
	private ProcessResult<String> serializeAndDeserialize(ProcessResult<String> result) {
        // Serialization logic
        return result; // Placeholder return
    }
}
