package com.ada.ecosystem.core.v1.connect.dto;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectDbDtoTest {

    @Test
    public void testGettersAndSetters() {
        ConnectDbDto dto = new ConnectDbDto();

        Long connectDbId = 100L;
        String connectCode = "ada-prod";
        String connectUrl = "jdbc:oracle:thin:@//ip:port/dbname";
        String connectUsername = "presup01";
        String connectPassword = "presup01";
        String connectDriver = "oracle.jdbc.driver.OracleDriver";
        String connectPlatform = "org.hibernate.dialect.Oracle8iDialect";
        String connectIp = "127.0.0.1";
        String connectPort = "1521";
        String connectService = "sicof";
        Date dateCreated = new Date();
        BigInteger userCreated = BigInteger.valueOf(5678);
        String status = "A";

        dto.setConnectDbId(connectDbId);
        dto.setConnectCode(connectCode);
        dto.setConnectUrl(connectUrl);
        dto.setConnectUsername(connectUsername);
        dto.setConnectPassword(connectPassword);
        dto.setConnectDriver(connectDriver);
        dto.setConnectPlatform(connectPlatform);
        dto.setConnectIp(connectIp);
        dto.setConnectPort(connectPort);
        dto.setConnectService(connectService);
        dto.setDateCreated(dateCreated);
        dto.setUserCreated(userCreated);
        dto.setStatus(status);

        assertEquals(connectDbId, dto.getConnectDbId());
        assertEquals(connectCode, dto.getConnectCode());
        assertEquals(connectUrl, dto.getConnectUrl());
        assertEquals(connectUsername, dto.getConnectUsername());
        assertEquals(connectPassword, dto.getConnectPassword());
        assertEquals(connectDriver, dto.getConnectDriver());
        assertEquals(connectPlatform, dto.getConnectPlatform());
        assertEquals(connectIp, dto.getConnectIp());
        assertEquals(connectPort, dto.getConnectPort());
        assertEquals(connectService, dto.getConnectService());
        assertEquals(dateCreated, dto.getDateCreated());
        assertEquals(userCreated, dto.getUserCreated());
        assertEquals(status, dto.getStatus());
    }

    @Test
    public void testEqualsAndHashCode() {
        ConnectDbDto dto1 = new ConnectDbDto();
        ConnectDbDto dto2 = new ConnectDbDto();

        dto1.setConnectDbId(100L);
        dto2.setConnectDbId(100L);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());

        dto2.setConnectDbId(101L);
        assertNotEquals(dto1, dto2);
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
        
        dto2.setConnectDbId(100L);
        dto2.setConnectCode("other-code");
        assertNotEquals(dto1, dto2);
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testToString() {
        ConnectDbDto dto = new ConnectDbDto();
        dto.setConnectDbId(100L);
        dto.setConnectCode("ada-prod");
        dto.setConnectUrl("jdbc:oracle:thin:@//ip:port/dbname");
        dto.setConnectUsername("presup01");
        dto.setConnectPassword("presup01");
        dto.setConnectDriver("oracle.jdbc.driver.OracleDriver");
        dto.setConnectPlatform("org.hibernate.dialect.Oracle8iDialect");
        dto.setConnectIp("127.0.0.1");
        dto.setConnectPort("1521");
        dto.setConnectService("sicof");
        dto.setDateCreated(new Date());
        dto.setUserCreated(BigInteger.valueOf(5678));
        dto.setStatus("A");

        // El valor esperado para toString puede cambiar dependiendo de cómo Lombok genere la cadena
        String expectedToString = "ConnectDbDto(connectDbId=100, connectCode=ada-prod, connectUrl=jdbc:oracle:thin:@//ip:port/dbname, connectUsername=presup01, connectPassword=presup01, connectDriver=oracle.jdbc.driver.OracleDriver, connectPlatform=org.hibernate.dialect.Oracle8iDialect, connectIp=127.0.0.1, connectPort=1521, connectService=sicof, dateCreated=" + dto.getDateCreated() + ", userCreated=5678, status=A)";
        assertEquals(expectedToString, dto.toString());
    }

    @Test
    public void testSettersWithNullValues() {
        ConnectDbDto dto = new ConnectDbDto();

        dto.setConnectDbId(null);
        dto.setConnectCode(null);
        dto.setConnectUrl(null);
        dto.setConnectUsername(null);
        dto.setConnectPassword(null);
        dto.setConnectDriver(null);
        dto.setConnectPlatform(null);
        dto.setConnectIp(null);
        dto.setConnectPort(null);
        dto.setConnectService(null);
        dto.setDateCreated(null);
        dto.setUserCreated(null);
        dto.setStatus(null);

        assertNull(dto.getConnectDbId());
        assertNull(dto.getConnectCode());
        assertNull(dto.getConnectUrl());
        assertNull(dto.getConnectUsername());
        assertNull(dto.getConnectPassword());
        assertNull(dto.getConnectDriver());
        assertNull(dto.getConnectPlatform());
        assertNull(dto.getConnectIp());
        assertNull(dto.getConnectPort());
        assertNull(dto.getConnectService());
        assertNull(dto.getDateCreated());
        assertNull(dto.getUserCreated());
        assertNull(dto.getStatus());
    }

    @Test
    public void testEqualsAndHashCodeWithNullValues() {
        ConnectDbDto dto1 = new ConnectDbDto();
        ConnectDbDto dto2 = new ConnectDbDto();

        dto1.setConnectDbId(null);
        dto2.setConnectDbId(null);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());

        dto2.setConnectDbId(100L);
        assertNotEquals(dto1, dto2);
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testToStringWithAllFields() {
        ConnectDbDto dto = new ConnectDbDto();
        dto.setConnectDbId(100L);
        dto.setConnectCode("ada-prod");
        dto.setConnectUrl("jdbc:oracle:thin:@//ip:port/dbname");
        dto.setConnectUsername("presup01");
        dto.setConnectPassword("presup01");
        dto.setConnectDriver("oracle.jdbc.driver.OracleDriver");
        dto.setConnectPlatform("org.hibernate.dialect.Oracle8iDialect");
        dto.setConnectIp("127.0.0.1");
        dto.setConnectPort("1521");
        dto.setConnectService("sicof");
        dto.setDateCreated(new Date());
        dto.setUserCreated(BigInteger.valueOf(5678));
        dto.setStatus("A");

        // Ajusta este valor según cómo se espera que sea la salida de toString
        String expectedToString = "ConnectDbDto(connectDbId=100, connectCode=ada-prod, connectUrl=jdbc:oracle:thin:@//ip:port/dbname, connectUsername=presup01, connectPassword=presup01, connectDriver=oracle.jdbc.driver.OracleDriver, connectPlatform=org.hibernate.dialect.Oracle8iDialect, connectIp=127.0.0.1, connectPort=1521, connectService=sicof, dateCreated=" + dto.getDateCreated() + ", userCreated=5678, status=A)";
        assertEquals(expectedToString, dto.toString());
    }
}
