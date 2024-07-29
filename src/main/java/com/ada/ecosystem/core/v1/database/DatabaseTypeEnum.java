package com.ada.ecosystem.core.v1.database;

/**
 * The Enum DatabaseTypeEnum.
 */
public enum DatabaseTypeEnum {
	
	/** The ORACLE 11 g R 2. */
	ORACLE("oracle"),
	
	/** The Postgre SQL 11. */
	POSTGRESQL("postgresq"),
	
	/** The My SQL 8. */
	MYSQL("mysql");
	
	/** The name. */
	private String name;	
	
	/**
	 * Instantiates a new database type enum.
	 *
	 * @param name the name
	 * @param order the order
	 */
	private DatabaseTypeEnum(String name) {
		this.name = name;		
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}	
}