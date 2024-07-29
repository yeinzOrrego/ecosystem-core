package com.ada.ecosystem.core.v1.database;

import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.springframework.beans.factory.annotation.Autowired;

import com.ada.ecosystem.core.v1.utility.EcosystemOracleDatabaseUtility;
import com.ada.ecosystem.core.v1.utility.EcosystemUtilities;

import jakarta.persistence.EntityManager;

public class EcosystemInterceptorStatement implements StatementInspector{

	private static final long serialVersionUID = 1L;	
	
	@Autowired
	private transient EntityManager em;

	@Override
	public String inspect(String sql) {
		if(!sql.equalsIgnoreCase("codigo_mempresa") && em != null) {
			String codigoMempresa = EcosystemUtilities.codigoMempresa; 
			EcosystemOracleDatabaseUtility.getInstance();
			EcosystemOracleDatabaseUtility.setContext(em, codigoMempresa);
		}
		return sql;
	}
}
