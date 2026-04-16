package com.ada.ecosystem.core.v1.utility;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
	
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EcosystemSuccessDTO<T> implements java.io.Serializable {
	    
	    /** The Constant serialVersionUID. */
	    private static final long serialVersionUID = 2384923404990945927L;
	    
	    /** The body. */
	    private transient T body;
	    
	    /** The length. */
	    private int length = 1;
	    
	    /** The message. */
	    private String message = null;
	    
	    /** The statusCode. */
	    private int statusCode;
	    
	    /** The log process. */
	    private StringBuilder logProcess;
	    
	    /**
	     * Instantiates a new success DTO.
	     *
	     * @author Carlos Torres - carlos.torres@ada.co
	     * @version 0.0.2
	     * @param body the body
	     */
	    public EcosystemSuccessDTO() {
	    }

	    /**
	     * Instantiates a new success DTO.
	     *
	     * @author Carlos Torres - carlos.torres@ada.co
	     * @version 0.0.2
	     * @param body the body
	     * @param length the length
	     * @param message the message
	     */
	    public EcosystemSuccessDTO(T body, int length, String message) {
	        this.body = body;
	        this.length = length;
	        this.message = message;
	        if (length == 0) {
	            if (this.body instanceof List) {
	                this.length = ((List<?>) this.body).size();
	            }

	            if (this.body instanceof Map) {
	                this.length = ((Map<?, ?>) this.body).size();
	            }
	        }

	    }

	    /**
	     * Instantiates a new success DTO.
	     *
	     * @author Carlos Torres - carlos.torres@ada.co
	     * @version 0.0.2
	     * @param body the body
	     * @param message the message
	     */
	    public EcosystemSuccessDTO(T body, String message) {
	        this.body = body;
	        this.message = message;

	        if (this.body instanceof List) {
	            this.length = ((List<?>) this.body).size();
	        }

	        if (this.body instanceof Map) {
	            this.length = ((Map<?, ?>) this.body).size();
	        }
	    }
	    
	    
	    /**
	     * Instantiates a new success DTO.
	     *
	     * @author Carlos Torres - carlos.torres@ada.co
	     * @version 0.0.2
	     * @param body the body
	     * @param message the message
	     */
	    public EcosystemSuccessDTO(T body, String message, int statusCode) {
	    	this.body = body;
	    	this.message = message;
	    	this.statusCode = statusCode;
	    	
	    	if (this.body instanceof List) {
	    		this.length = ((List<?>) this.body).size();
	    	}
	    	
	    	if (this.body instanceof Map) {
	    		this.length = ((Map<?, ?>) this.body).size();
	    	}
	    }

	    /**
	     * Instantiates a new success DTO.
	     *
	     * @author Carlos Torres - carlos.torres@ada.co
	     * @version 0.0.2
	     * @param body the body
	     * @param length the length
	     */
	    public EcosystemSuccessDTO(T body, Integer length) {
	        this.body = body;
	        this.length = length;
	    }

	    /**
	     * Sets the length.
	     *
	     * @author Carlos Torres - carlos.torres@ada.co
	     * @version 0.0.2
	     * @param length the new length
	     */
	    public void setLength(Integer length) {
	        this.length = length;
	    }

	    /**
	     * Instantiates a new success DTO.
	     *
	     * @author Carlos Torres - carlos.torres@ada.co
	     * @version 0.0.2
	     * @param body the body
	     */
	    public EcosystemSuccessDTO(T body) {
	        this.body = body;
	        if (this.body instanceof List) {
	            this.length = ((List<?>) this.body).size();
	        }

	        if (this.body instanceof Map) {
	            this.length = ((Map<?, ?>) this.body).size();
	        }
	    }

	    /**
	     * Gets the body.
	     *
	     * @author Carlos Torres - carlos.torres@ada.co
	     * @version 0.0.2
	     * @return the body
	     */
	    public T getBody() {

	        return body;
	    }

	    /**
	     * Sets the body.
	     *
	     * @author Carlos Torres - carlos.torres@ada.co
	     * @version 0.0.2
	     * @param body the new body
	     */
	    public void setBody(T body) {
	        this.body = body;
	    }

	    /**
	     * Gets the length.
	     *
	     * @author Carlos Torres - carlos.torres@ada.co
	     * @version 0.0.2
	     * @return the length
	     */
	    public int getLength() {
	        return length;
	    }

	    /**
	     * Sets the length.
	     *
	     * @author Carlos Torres - carlos.torres@ada.co
	     * @version 0.0.2
	     * @param length the new length
	     */
	    public void setLength(int length) {
	        this.length = length;
	    }

	    /**
	     * Gets the message.
	     *
	     * @author Carlos Torres - carlos.torres@ada.co
	     * @version 0.0.2
	     * @return the message
	     */
	    public String getMessage() {
	        return message;
	    }

	    /**
	     * Sets the message.
	     *
	     * @author Carlos Torres - carlos.torres@ada.co
	     * @version 0.0.2
	     * @param message the new message
	     */
	    public void setMessage(String message) {
	        this.message = message;
	    }

		/**
		 * Gets the log process.
		 * 
		 * @author Carlos Torres - carlos.torres@ada.co
	     * @version 0.0.2
		 *
		 * @return the log process
		 */
		public StringBuilder getLogProcess() {
			return logProcess;
		}

		/**
		 * Sets the log process.
		 * 
		 * @author Carlos Torres - carlos.torres@ada.co
	     * @version 0.0.2
		 *
		 * @param logProcess the new log process
		 */
		public void setLogProcess(StringBuilder logProcess) {
			this.logProcess = logProcess;
		}

		public int getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}    
		

}
