package com.ada.ecosystem.core.v1.database;

/**
 * The Enum DatabaseEnvironment.
 */
public enum DatabaseEnvironment {
	
	/** The development. */
	DEVELOPMENT("dev"), 
	
	/** The testing. */
	TESTING("qa"), 
	
	/** The production. */
	PRODUCTION("prod"),
	
	/** The presentation. */
	PRESENTATION("client");
	
	/** The name. */
	private String name;	
	
	/**
	 * Instantiates a new database environment enum.
	 *
	 * @param name the name
	 * @param order the order
	 */
	private DatabaseEnvironment(String name) {
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