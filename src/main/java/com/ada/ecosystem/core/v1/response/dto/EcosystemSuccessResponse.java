package com.ada.ecosystem.core.v1.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The Class EcosystemSuccessResponse.
 *
 * @author Carlos Torres - torrescamargo@gmail.com
 * @version 0.0.2
 * @param <T> the generic type
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EcosystemSuccessResponse<T> {
    
    /** The response. */
    private EcosystemSuccessDTO<T> response;
    
    /**
     * Instantiates a new success response.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.2
     */
    public EcosystemSuccessResponse() {        
    }

    /**
     * Gets the response.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.2
     * @return the response
     */
    public EcosystemSuccessDTO<T> getResponse() {
        return response;
    }

    /**
     * Sets the response.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.2
     * @param response the new response
     */
    public void setResponse(EcosystemSuccessDTO<T> response) {
        this.response = response;
    }

    /**
     * Instantiates a new success response.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.2
     * @param object the object
     */
    public EcosystemSuccessResponse(T object) {
        this.response = new EcosystemSuccessDTO<>(object);
    }

    /**
     * Instantiates a new success response.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.2
     * @param object the object
     * @param message the message
     */
    public EcosystemSuccessResponse(T object, String message) {
        this.response = new EcosystemSuccessDTO<>(object, message);
    }
    
    /**
     * Instantiates a new success response.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.2
     * @param object the object
     * @param message the message
     */
    public EcosystemSuccessResponse(T object, String message, int statusCode) {
    	this.response = new EcosystemSuccessDTO<>(object, message, statusCode);
    }

    /**
     * Instantiates a new success response.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.2
     * @param object the object
     * @param length the length
     * @param message the message
     */
    public EcosystemSuccessResponse(T object, Integer length, String message) {
        this.response = new EcosystemSuccessDTO<>(object, length, message);
    }

    /**
     * Instantiates a new success response.
     *
     * @author Carlos Torres - torrescamargo@gmail.com
     * @version 0.0.2
     * @param object the object
     * @param length the length
     */
    public EcosystemSuccessResponse(T object, Integer length) {
        this.response = new EcosystemSuccessDTO<>(object, length);
    }
}