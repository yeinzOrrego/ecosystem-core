package com.ada.ecosystem.core.v1.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.ada.ecosystem.core.v1.connect.dto.ConnectDbDto;

/**
 * The Class DataSourceConfigBase.
 */
public abstract class DataSourceConfigBase implements IDataSourceConfig {
	
	/** The log. */
	Logger log = LoggerFactory.getLogger(DataSourceConfigBase.class); 
	
	/**
	 * Data source.
	 *
	 * @return the data source
	 */
	@Bean
	@Primary
	@Autowired
	public DataSource dataSource() {		
		List<ConnectDbDto> dataSourceConnections = new ArrayList<>();
		return getRoutingDataSourceDevelopment(dataSourceConnections);
	}

	/**
	 * Gets the routing data source development.
	 *
	 * @return the routing data source development
	 */
	protected DataSource getRoutingDataSourceDevelopment(List<ConnectDbDto> dataSourceConnections) {
		log.info("getRoutingDataSourceDevelopment init...");
		var routingDataSource = new DataSourceRouting();
		Map<Object, Object> dataSourceMap = new HashMap<>();
		for(var dataSourceConnection : dataSourceConnections) {
			dataSourceMap.put(dataSourceConnection.getConnectCode(), getDataSourceConfig(dataSourceConnection));
		}
		routingDataSource.setTargetDataSources(dataSourceMap);
		var defaultTargetDataSource = getDataSourceConfig(dataSourceConnections.get(0));
		routingDataSource.setDefaultTargetDataSource(defaultTargetDataSource);
		log.info("end getRoutingDataSourceDevelopment");
		return routingDataSource;
	}	

	/**
	 * Gets the data source config.
	 *
	 * @param client              the client
	 * @param databaseEnvironment the database environment
	 * @return the data source config
	 */

	protected DataSource getDataSourceConfig(ConnectDbDto connectDbDto) {
		log.info("getDataSourceConfig {}", connectDbDto.getConnectCode());
		var dataSource = new DriverManagerDataSource();
		dataSource.setUrl(connectDbDto.getConnectUrl());
		dataSource.setUsername(connectDbDto.getConnectUsername());
		dataSource.setPassword(connectDbDto.getConnectPassword());
		dataSource.setDriverClassName(connectDbDto.getConnectDriver());
		return dataSource;
	}
}