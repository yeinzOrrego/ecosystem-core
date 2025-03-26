package com.ada.ecosystem.core.v1.database;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


/**
 * The Interface IDataSourceConfig.
 */
public interface IDataSourceConfig {
	
	/**
	 * Data source.
	 *
	 * @return the data source
	 * @throws EcosystemException 
	 */
	@Bean
	@Primary
	@Autowired
	DataSource dataSource() ;
	
	/**
	 * Entity manager factory bean.
	 *
	 * @param builder the builder
	 * @return the local container entity manager factory bean
	 */
	@Bean(name = "entityManager")
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder);

	/**
	 * Transaction manager.
	 *
	 * @param entityManagerFactoryBean the entity manager factory bean
	 * @return the jpa transaction manager
	 */
	@Bean(name = "transcationManager")
	JpaTransactionManager transactionManager(@Autowired @Qualifier("entityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean);

}