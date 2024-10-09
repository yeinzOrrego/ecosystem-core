package com.ada.ecosystem.core.v1.database;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * The Class DataSourceRouting.
 */
public class DataSourceRouting extends AbstractRoutingDataSource {

	/**
	 * Determine current lookup key.
	 *
	 * @return the object
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DatabaseContextHolder.getDatabaseContext();
	}
	

    public Map<Object, DataSource> getTargetDataSources() {
        return super.getResolvedDataSources();
    }
}