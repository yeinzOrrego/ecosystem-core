package com.ada.ecosystem.core.v1.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The Class DataSourceConfig.
 */
@Configuration
public class DataSourceConfig implements WebMvcConfigurer {
	

//	/** The log. */
//	private static Logger log = LoggerFactory.getLogger(DataSourceConfig.class);
//	@Autowired
//    private DatasourceInterceptor datasourceInterceptor;
//	
//	@Override
//    public void addInterceptors(InterceptorRegistry registry) {
//		log.info("addInterceptors: DatasourceInterceptor");
//		DatabaseContextHolder.clearDatabaseContext();
//		
//		log.info("[DataSourceConfig] [addInterceptors]  nuevo intercerptor addInterceptors: DatasourceInterceptor");
//        registry.addInterceptor(datasourceInterceptor).addPathPatterns("/**");
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }
//	
//    @Bean
//    public DatasourceInterceptor datasourceInterceptor() {
//        return new DatasourceInterceptor();
//    }
}