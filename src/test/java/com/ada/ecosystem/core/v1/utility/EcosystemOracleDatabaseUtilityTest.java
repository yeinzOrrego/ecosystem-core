package com.ada.ecosystem.core.v1.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;

class EcosystemOracleDatabaseUtilityTest {

    private EntityManager mockEntityManager;
    private Connection mockConnection;
    private StoredProcedureQuery mockStoredProcedureQuery;
    private CallableStatement mockCallableStatement;
    private Query mockQuery;

    @BeforeEach
    void setUp() {
        mockEntityManager = mock(EntityManager.class);
        mockConnection = mock(Connection.class);
        mockStoredProcedureQuery = mock(StoredProcedureQuery.class);
        mockCallableStatement = mock(CallableStatement.class);
        mockQuery = mock(Query.class);
        
        when(mockEntityManager.createStoredProcedureQuery("sicof.pkg_ctx_sicof.sp_nombre_variable"))
                .thenReturn(mockStoredProcedureQuery);
        when(mockEntityManager.createStoredProcedureQuery("SICOF.SP_LOG_ERRORES"))
                .thenReturn(mockStoredProcedureQuery);
        when(mockEntityManager.createNativeQuery(anyString()))
                .thenReturn(mockQuery);
        try {
            when(mockConnection.prepareCall("{call sicof.sp_log_errores(?,?,?,?)}"))
                    .thenReturn(mockCallableStatement);
        } catch (SQLException e) {
            fail("Failed to setup mock for CallableStatement", e);
        }
    }

    @Test
    void testSingletonInstance() {
        EcosystemOracleDatabaseUtility instance1 = EcosystemOracleDatabaseUtility.getInstance();
        EcosystemOracleDatabaseUtility instance2 = EcosystemOracleDatabaseUtility.getInstance();
        assertSame(instance1, instance2, "Instances should be the same for singleton pattern");
    }

    @Test
    void testSetContextWithEntityManager() {
        // Given
        String codigoMempresa = "1234567890";

        // When
        EcosystemOracleDatabaseUtility.setContext(mockEntityManager, codigoMempresa);

        // Then
        verify(mockStoredProcedureQuery).setParameter("as_nombre", "CODIGO_MEMPRESA");
        verify(mockStoredProcedureQuery).setParameter("as_valor", codigoMempresa);
        verify(mockStoredProcedureQuery).execute();
    }

    @Test
    void testRegisterLogErroresWithEntityManager() {
        // Given
        Long codOraError = 123L;
        String msgOraError = "Error Message";
        String nomProc = "Procedure Name";
        String observacion = "Observation";

        // When
        EcosystemOracleDatabaseUtility.registerLogErrores(mockEntityManager, codOraError, msgOraError, nomProc, observacion);

        // Then
        verify(mockStoredProcedureQuery).setParameter("AN_COD_ORA_ERROR", codOraError);
        verify(mockStoredProcedureQuery).setParameter("AS_MSJ_ORA_ERROR", msgOraError);
        verify(mockStoredProcedureQuery).setParameter("AS_NOM_PROC", nomProc);
        verify(mockStoredProcedureQuery).setParameter("AS_OBSERVACION", observacion);
        verify(mockStoredProcedureQuery).execute();
    }

    @Test
    void testRegisterLogErroresWithConnection() throws SQLException {
        // Given
        Long codOraError = 123L;
        String msgOraError = "Error Message";
        String nomProc = "Procedure Name";
        String observacion = "Observation";

        // When
        EcosystemOracleDatabaseUtility.registerLogErrores(mockConnection, codOraError, msgOraError, nomProc, observacion);

        // Then
        verify(mockCallableStatement).setLong("AN_COD_ORA_ERROR", codOraError);
        verify(mockCallableStatement).setString("AS_MSJ_ORA_ERROR", msgOraError);
        verify(mockCallableStatement).setString("AS_NOM_PROC", nomProc);
        verify(mockCallableStatement).setString("AS_OBSERVACION", observacion);
        verify(mockCallableStatement).execute();
    }

    @Test
    void testCloseConnection() {
        try {
            EcosystemOracleDatabaseUtility.closeConnection(mockConnection);
            verify(mockConnection).close();
        } catch (SQLException e) {
            fail("Failed to verify connection close", e);
        }
    }

    @Test
    void testGetCurrentURL() {
        EcosystemOracleDatabaseUtility.setCurrentURL("http://example.com");
        assertEquals("http://example.com", EcosystemOracleDatabaseUtility.getCurrentURL());
    }

    @Test
    void testGetNextSecuencia() {
        // Given
        String sequenceName = "SEQ_NAME";
        when(mockQuery.getSingleResult()).thenReturn(BigDecimal.valueOf(1));

        // When
        Long result = EcosystemOracleDatabaseUtility.getNextSecuencia(mockEntityManager, sequenceName);

        // Then
        assertEquals(1L, result);
        verify(mockQuery).getSingleResult();
    }

    @Test
    void testGetSelectIntoOnlyOne() {
        // Given
        String sql = "SELECT VALUE FROM DUAL";
        String codigoMempresa = "1234567890";
        when(mockQuery.getSingleResult()).thenReturn("result");

        // When
        String result = EcosystemOracleDatabaseUtility.getSelectIntoOnlyOne(mockEntityManager, codigoMempresa, sql);

        // Then
        assertEquals("result", result);
        verify(mockQuery).getSingleResult();
    }

    @Test
    void testGetLegacyEncrypt() {
        String encrypted = "encrypted";
        when(mockStoredProcedureQuery.getOutputParameterValue(3)).thenReturn(encrypted);

    }

    @Test
    void testGetLegacyDecrypt() {
        String decrypted = "decrypted";
        when(mockStoredProcedureQuery.getOutputParameterValue(3)).thenReturn(decrypted);
    }
}
