package com.ada.ecosystem.core.v1.database;

/**
 * The Class DatabaseContextHolder.
 */
public class DatabaseContextHolder {

	/** The thread local. */
	private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
	
	private DatabaseContextHolder() {}

	/**
	 * Sets the database context.
	 *
	 * @param databaseEnum the new database context
	 */
	public static void setDatabaseContext(String databaseEnum) {
		threadLocal.set(databaseEnum);
	}

	/**
	 * Gets the database context.
	 *
	 * @return the database context
	 */
	public static String getDatabaseContext() {
		return threadLocal.get();
	}

	/**
	 * Clear database context.
	 */
	public static void clearDatabaseContext() {
		threadLocal.remove();
	}
}
