package com.ada.ecosystem.core.v1.sql;

public class EcosystemQueryUtilities {
	
	private static EcosystemQueryUtilities instance;
	
	public static final String QUERY_001_TERCERO_NO_OBLIGADO = """
			SELECT  NVL(MT.FE_NO_OBLIGADO_FACTURAR, 'N')      
			FROM    TESORE01.MAESTRO_TERCEROS MT
			WHERE   MT.CODIGO_TERCERO = :an_ctercero
			""";
	
		
	private EcosystemQueryUtilities() {}
	
	public static EcosystemQueryUtilities getInstance() {
		if(instance == null) instance = new EcosystemQueryUtilities();
		return instance;
	}
}