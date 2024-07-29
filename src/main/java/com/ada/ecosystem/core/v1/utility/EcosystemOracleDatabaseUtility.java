package com.ada.ecosystem.core.v1.utility;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;

/**
 * Clase utilitaria utilizada para implementar procesos de apoyo a las transacciones de la base de datos.  
 */
public class EcosystemOracleDatabaseUtility {	
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(EcosystemOracleDatabaseUtility.class);
	
	/** The instance. */
	private static EcosystemOracleDatabaseUtility instance;
	
	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = "{call sicof.pkg_ctx_sicof.sp_nombre_variable(?,?)}";
	
	/** The Constant PROCEDURE_PARAMETER. */
	private static final String PROCEDURE_PARAMETER = "CODIGO_MEMPRESA";	
	
	/** The Constant PARAM_COD_ERROR. */
	private static final String PARAM_COD_ERROR = "AN_COD_ORA_ERROR";	
	
	/** The Constant PARAM_MSG_ERROR. */
	private static final String PARAM_MSG_ERROR = "AS_MSJ_ORA_ERROR";
	
	/** The Constant PARAM_NOM_PROC. */
	private static final String PARAM_NOM_PROC = "AS_NOM_PROC";
	
	/** The Constant PARAM_OBSERVAC. */
	private static final String PARAM_OBSERVAC = "AS_OBSERVACION";
	
	/** The Constant PARAM_EMPRESA_NOMBRE. */
	private static final String PARAM_EMPRESA_NOMBRE = "as_nombre";
	
	/** The Constant PARAM_EMPRESA_VALOR. */
	private static final String PARAM_EMPRESA_VALOR = "as_valor";
	
	/** The msg. */
	private static String msg;
	
	/** The current URL. */
	private static String currentURL;
	
	private static final String BIT_LENGTH = "AES256";
			
	/**
	 * Instantiates a new ecosystem oracle database utility.
	 */
	private EcosystemOracleDatabaseUtility() {
		super();	
	}

	/**
	 * Gets the single instance of EcosystemOracleDatabaseUtility.
	 *
	 * @return single instance of EcosystemOracleDatabaseUtility
	 */
	public static EcosystemOracleDatabaseUtility getInstance() {
		if(instance == null) instance = new EcosystemOracleDatabaseUtility(); 
		return instance;
	}

	/**
	 * Método utilitario utilizado para establecer en el contexto de base de datos
	 * la variable "codigo_mempresa" el cuál es utilizado para los clientes multiempresa.
	 * 
	 * El método controla la asignación de contexto siempre y cuando el parámetro codigoMEmpresa
	 * sea diferente de Null y del valor '9999999999' el cual se utiliza para definir clientes
	 * uniempresa los cuales no requieren definir variables de contexto.
	 * 
	 * Este método debe ser implementado en todas las instrucciones que vayan a la base de datos.
	 * 
	 * @param em Transacción que realizara el seteo del contexto en la base de datos.
	 * @param codigoMempresa Valor de la empresa que se establecera en el contexto de base de datos para la ejecución de la petición.
	 */
	public static void setContext(EntityManager em, String codigoMempresa){        
        try {        	
            if(codigoMempresa != null && !codigoMempresa.equals("9999999999") ){
                StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("sicof.pkg_ctx_sicof.sp_nombre_variable");
                storedProcedure.registerStoredProcedureParameter(PARAM_EMPRESA_NOMBRE, String.class, ParameterMode.IN);
                storedProcedure.registerStoredProcedureParameter(PARAM_EMPRESA_VALOR, String.class, ParameterMode.IN);
                storedProcedure.setParameter(PARAM_EMPRESA_NOMBRE, PROCEDURE_PARAMETER);
                storedProcedure.setParameter(PARAM_EMPRESA_VALOR, codigoMempresa);
                msg = String.format("Seteando Contexto: %s.%s", PROCEDURE_PARAMETER, codigoMempresa);
                log.info(msg);
                storedProcedure.execute();
            }
        } catch (NoResultException ex) {
        	log.info(ex.getMessage());
        }
    }
	
	/**
	 * Método utilitario utilizado para establecer en el contexto de base de datos
	 * la variable "codigo_mempresa" el cuál es utilizado para los clientes multiempresa.
	 * 
	 * El método controla la asignación de contexto siempre y cuando el parámetro codigoMEmpresa
	 * sea diferente de Null y del valor '9999999999' el cual se utiliza para definir clientes
	 * uniempresa los cuales no requieren definir variables de contexto.
	 * 
	 * Este método debe ser implementado en todas las instrucciones que vayan a la base de datos.
	 *
	 * @param connection Conexión a la base de datos donde se aplicará el contexto.
	 * @param codigoMempresa Valor de la empresa que se establecera en el contexto de base de datos para la ejecución de la petición.
	 */
	public static void setContext(Connection connection, String codigoMempresa){
		if(codigoMempresa != null && !codigoMempresa.equals("9999999999") ){
	        try (CallableStatement cs = connection.prepareCall(PROCEDURE_NAME)) {	        	
				cs.clearParameters();
				cs.setString(PARAM_EMPRESA_NOMBRE, PROCEDURE_PARAMETER);
				cs.setString(PARAM_EMPRESA_VALOR, codigoMempresa);
				msg = String.format("Seteando Contexto: %s.%s", PROCEDURE_PARAMETER, codigoMempresa);
				log.info(msg);
				cs.execute();            
	        } catch (NoResultException | SQLException ex) {
	        	log.info(ex.getMessage());
	        } 
		}
    }	
	
	/**
	 * Método utilitario utilizado para registrar errores en la de base de datos.
	 * 
	 * @param em Transacción que realizara el seteo del contexto en la base de datos.
	 * @param codOraError Código de error de la base de datos.
	 * @param msgOraError Mnesaje de error de la base de datos.
	 * @param nomProc Nombre del procedimiento o función donde se genera el error.
	 * @param observacion Descripción del mensaje de error.
	 */
	public static void registerLogErrores(EntityManager em, Long codOraError, String msgOraError, String nomProc, String observacion){        
        try {
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("SICOF.SP_LOG_ERRORES");
            storedProcedure.registerStoredProcedureParameter(PARAM_COD_ERROR, Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter(PARAM_MSG_ERROR, String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter(PARAM_NOM_PROC, String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter(PARAM_OBSERVAC, String.class, ParameterMode.IN);
            if(codOraError == null)codOraError = 0L;
            storedProcedure.setParameter(PARAM_COD_ERROR, codOraError);
            storedProcedure.setParameter(PARAM_MSG_ERROR, msgOraError);
            storedProcedure.setParameter(PARAM_NOM_PROC, nomProc);
            if(observacion == null || observacion.isEmpty()) {
            	observacion = "N/A";
            }
            storedProcedure.setParameter(PARAM_OBSERVAC, observacion);
            msg = String.format("Registrando Error: %d,%s,%s,%s", codOraError,msgOraError,nomProc,observacion);
            log.info(msg);
            storedProcedure.execute();            
        } catch (Exception ex) {
        	log.info(ex.getMessage());
        }
    }
	
	/**
	 * Método utilitario utilizado para registrar errores en la de base de datos.
	 *
	 * @param connection Conexión a la base de datos donde se aplicará el contexto.
	 * @param codOraError Código de error de la base de datos.
	 * @param msgOraError Mnesaje de error de la base de datos.
	 * @param nomProc Nombre del procedimiento o función donde se genera el error.
	 * @param observacion Descripción del mensaje de error.
	 */
	public static void registerLogErrores(Connection connection, Long codOraError, String msgOraError, String nomProc, String observacion){		
        try (CallableStatement cs = connection.prepareCall("{call sicof.sp_log_errores(?,?,?,?)}")) {        						
			cs.clearParameters();
			if(codOraError == null)codOraError = 0L;
			cs.setLong(PARAM_COD_ERROR, codOraError);
			cs.setString(PARAM_MSG_ERROR, msgOraError);
			cs.setString(PARAM_NOM_PROC, nomProc);
			cs.setString(PARAM_OBSERVAC, observacion);
			msg = String.format("Registrando Error: %d,%s,%s,%s", codOraError,msgOraError,nomProc,observacion);
			log.info(msg);
			cs.execute();            
        } catch (Exception e) {
        	log.info(e.getMessage());
        } 
    }
	
	/**
	 * CloseConnection
	 *
	 * Cierra la conexión a un origen de datos.
	 * 
	 * @param connection Conexión a la base de datos.
	 */
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException ex) {
				log.info(ex.getMessage());				
			}
		}
	}
	
	/**
	 * Gets the current URL.
	 *
	 * @return the current URL
	 */
	public static String getCurrentURL() {
		return currentURL;
	}

	/**
	 * Sets the current URL.
	 *
	 * @param currentURL the new current URL
	 */
	public static void setCurrentURL(String currentURL) {
		EcosystemOracleDatabaseUtility.currentURL = currentURL;
	}
	
	/**
	 * Gets the next secuencia.
	 *
	 * @param entityManager the entity manager
	 * @param sequenceName the sequence name
	 * @return the next secuencia
	 */
	public static Long getNextSecuencia(EntityManager entityManager, String sequenceName) {
		String sql = "SELECT " + sequenceName + ".NEXTVAL FROM DUAL";
        BigDecimal value = (BigDecimal) getSelectIntoOnlyOne(entityManager,
        		EcosystemUtilities.CODIGO_MEMPRESA_DEFAULT,
        		sql);
        return value.longValue();
    }	
	
	/**
	 * Gets the select into only one.
	 *
	 * @param <T> the generic type
	 * @param entityManager the entity manager
	 * @param codigoMempresa the codigo mempresa
	 * @param sql the sql
	 * @return the select into only one
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSelectIntoOnlyOne(EntityManager entityManager, String codigoMempresa, String sql) {
		setContext(entityManager, codigoMempresa);  
		Query query = entityManager.createNativeQuery(sql);        
		return (T) query.getSingleResult();
	}	
	
	public static String getLegacyEncrypt(EntityManager entityManager, String value, Boolean obfuscate) {
		StoredProcedureQuery encryptQuery = entityManager.createStoredProcedureQuery("PRESUP01.AES_ENCRYPT_VARCHAR2")
				.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, byte[].class, ParameterMode.IN)
				.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT)
				.setParameter(1, BIT_LENGTH)
				.setParameter(2, value);
		encryptQuery.execute();
        String encryptedString = (String) encryptQuery.getOutputParameterValue(3);
        return Boolean.TRUE.equals(obfuscate) ? Base64.getEncoder().encodeToString(encryptedString.getBytes()) : encryptedString;        
    }
	
	public static String getLegacyDecrypt(EntityManager entityManager, String value, Boolean obfuscate) {
		StoredProcedureQuery decryptQuery = entityManager.createStoredProcedureQuery("PRESUP01.AES_DECRYPT_VARCHAR2")
				.registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, byte[].class, ParameterMode.IN)
				.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT)
				.setParameter(1, BIT_LENGTH)
				.setParameter(2, value);
		decryptQuery.execute();
        String decryptedString = (String) decryptQuery.getOutputParameterValue(3);
        return Boolean.TRUE.equals(obfuscate) ? Base64.getEncoder().encodeToString(decryptedString.getBytes()) : decryptedString;        
    }	
}