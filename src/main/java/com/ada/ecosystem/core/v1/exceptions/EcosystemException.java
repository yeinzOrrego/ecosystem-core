package com.ada.ecosystem.core.v1.exceptions;

import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * The Class DatabaseColumnInspectionException.
 * @author Carlos Torres - torrescamargo@gmail.com
 * @version 0.0.8
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class EcosystemException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8689188155686006423L;
	
	/** The errors. */
	private final List<String> errors;
	
	/** The solutions. */
	private final List<String> solutions;
	
	/** The lod process. */
	private final StringBuilder logProcess;

	/**
	 * Instantiates a new ecosystem exception.
	 *
	 * @author Carlos Torres - carlos.torres@ada.co
	 * @version 0.0.8
	 * @param errorMessage the error message
	 * @param errors the errors
	 * @param solutions the solutions
	 */
	public EcosystemException(String errorMessage, List<String> errors, List<String> solutions) {
		super(errorMessage);
		this.errors = (errors == null) ? Arrays.asList("") : errors;
		this.solutions = (solutions == null) ? Arrays.asList("") : solutions;
		this.logProcess = new StringBuilder();
	}	
	
	/**
	 * Instantiates a new ecosystem exception.
	 *
	 * @author Carlos Torres - carlos.torres@ada.co
	 * @version 0.0.8
	 * @param errorMessage the error message
	 * @param errors the errors
	 * @param solutions the solutions
	 * @param logProcess the log process
	 */
	public EcosystemException(String errorMessage, List<String> errors, List<String> solutions, StringBuilder logProcess) {
		super(errorMessage);
		this.errors = (errors == null) ? Arrays.asList("") : errors;
		this.solutions = (solutions == null) ? Arrays.asList("") : solutions;
		this.logProcess = (logProcess == null) ? new StringBuilder() : logProcess;
	}
}