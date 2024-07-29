package com.ada.ecosystem.core.v1.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DatasourceInterceptor implements HandlerInterceptor   {
	
	private static final String TOKEN_PARAM = "token";
	
	/** The env. */
	@Autowired
	protected Environment env;
	
	/** The log. */
	Logger log = LoggerFactory.getLogger(DatasourceInterceptor.class);	
	
	Boolean debug = false;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(Boolean.FALSE.equals(debug)) {
			String company = processHeader(request);			
			log.info("Interceptando Empresa para determinar Datasource: {}", company);
			DatabaseContextHolder.setDatabaseContext(getDatabaseEnumContext(company));
			log.info("Datasource asignado: {}", DatabaseContextHolder.getDatabaseContext());			
			TokenContextHolder.setToken(company);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Método después de que el controlador maneja la solicitud
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Método después de que se completa el procesamiento de la solicitud, incluso después de que la vista se ha rendereado
    	DatabaseContextHolder.clearDatabaseContext();
    	TokenContextHolder.clearToken();
    }
    
    /**
	 * Process header.
	 *
	 * @param request the request
	 * @return the string
	 */
	private String processHeader(HttpServletRequest request) {		
		return request.getHeader(TOKEN_PARAM);
	}
	
	/**
	 * Gets the database enum context.
	 *
	 * @param company the company
	 * @return the database enum context
	 */
	protected String getDatabaseEnumContext(String company) {
		return company;
	}
}