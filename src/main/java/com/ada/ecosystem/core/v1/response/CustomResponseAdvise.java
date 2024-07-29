package com.ada.ecosystem.core.v1.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.ada.ecosystem.core.v1.exceptions.EcosystemError;
import com.ada.ecosystem.core.v1.response.dto.EcosystemSuccessResponse;
import com.ada.ecosystem.core.v1.response.interfaces.IgnoreResponseBinding;


/**
 * The Class CustomResponseAdvise.
 * 
 * @author carlos.torres
 * @version 1.0.0
 */
@ControllerAdvice
public class CustomResponseAdvise extends CustomResponseExceptionHandler implements ResponseBodyAdvice<Object> {
	
	/** The open API. */
	private Boolean openAPI = false;
    
    /**
     * Supports.
     *
     * @param returnType the return type
     * @param converterType the converter type
     * @return true, if successful
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    	if (returnType.getMethod().getName().equalsIgnoreCase("openapiJson")) {
			openAPI = true;
			return false;
		}
        return true;
    }   

    /**
     * Before body write.
     *
     * @param body the body
     * @param returnType the return type
     * @param selectedContentType the selected content type
     * @param selectedConverterType the selected converter type
     * @param request the request
     * @param response the response
     * @return the object
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
    	if (Boolean.TRUE.equals(openAPI)) {
			openAPI = false;
			return body.toString();
		}
    	else if (returnType.getContainingClass().isAnnotationPresent(RestController.class)) {            
        	var method = returnType.getMethod(); 
        	if (method != null) {
                if (!method.isAnnotationPresent(IgnoreResponseBinding.class) 
                        &&  ((!(body instanceof EcosystemError)) 
                        		&& (!(body instanceof EcosystemSuccessResponse<?>)))) {
                        return new EcosystemSuccessResponse<>(body);
                }
        	}            
            else {
                return body;
            }
        }
        return body;
    }
}