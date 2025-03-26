package com.ada.ecosystem.core.v1.database;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class DatasourceInterceptor    {
	//implements HandlerInterceptor	
//	private static final String TOKEN_PARAM = "token";
//	
//	/** The env. */
//	@Autowired
//	protected Environment env;
//	@Autowired
//	@Lazy
//	    private DataSourceRouting dataSourceRouting;
//
//	/** The log. */
//	Logger log = LoggerFactory.getLogger(DatasourceInterceptor.class);	
//	
//	Boolean debug = false;
//	@Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception, EcosystemException {
//       
//        if (handler instanceof HandlerMethod) {
//            Method method = ((HandlerMethod) handler).getMethod();
//            if (method.isAnnotationPresent(SkipInterceptor.class)) {         	
//            	 log.info("no pasa por el interceptor");
//                return true; 
//            }else {
//            	
//            	 Boolean existeConexionDisponible = false; 
//                 if (Boolean.FALSE.equals(debug)) {
//                     DatabaseContextHolder.clearDatabaseContext();
//                     log.info("Conexion Limpia: {}");
//                     String company = processHeader(request);
//                     if (company == null || company.isEmpty()) {
//                         throw new EcosystemException("Se han generado errores al intentar conectarse al cliente.",
//                                 Arrays.asList(" "),
//                                 Arrays.asList("Revise el estado de la conexión."));
//                     }
//                     Map<Object, DataSource> dataSourceMap = dataSourceRouting.getTargetDataSources();
//                     for (Map.Entry<Object, DataSource> entry : dataSourceMap.entrySet()) {
//                         Object key = entry.getKey();
//                         log.info("Key conexión: {}", key.toString());
//                         if (key.equals(company)) {
//                             log.info("Existe conexión disponible para su solicitud");
//                             existeConexionDisponible = true;
//                             break;
//                         }
//                     }
//                     if (existeConexionDisponible) {
//                         log.info("Interceptando Empresa para determinar Datasource: {}", company);
//                         DatabaseContextHolder.setDatabaseContext(getDatabaseEnumContext(company));
//                         log.info("Datasource asignado: {}", DatabaseContextHolder.getDatabaseContext());
//                         TokenContextHolder.setToken(company);
//                     } else {
//                         throw new EcosystemException("Se han generado errores al intentar conectarse al cliente.",
//                                 Arrays.asList("No es posible acceder a este cliente, puede estar bloqueado temporalmente."),
//                                 Arrays.asList("Revise el estado de la conexión."));
//                     }
//                 }
// 	
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        // Método después de que el controlador maneja la solicitud
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        // Método después de que se completa el procesamiento de la solicitud, incluso después de que la vista se ha rendereado
//    	DatabaseContextHolder.clearDatabaseContext();
//    	TokenContextHolder.clearToken();
//    }
//    
//    /**
//	 * Process header.
//	 *
//	 * @param request the request
//	 * @return the string
//	 */
//	private String processHeader(HttpServletRequest request) {		
//		return request.getHeader(TOKEN_PARAM);
//	}
//	
//	/**
//	 * Gets the database enum context.
//	 *
//	 * @param company the company
//	 * @return the database enum context
//	 */
//	protected String getDatabaseEnumContext(String company) {
//		return company;
//	}
//	
//	

}