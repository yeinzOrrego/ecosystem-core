package com.ada.ecosystem.core.v1.query;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * The Class SearchCriteria.
 */
@Schema(description = "Define el criterio de búsqueda.")
@JsonSerialize
@Data
public class SearchCriteria implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** The key. */
	@Schema(description = "Nombre de la columna del criterio de búsqueda.")
	private String key;
    
    /** The operation. */
	@Schema(description = "Operador del criterio de búsqueda.")
    private SearchOperation operation;
    
    /** The value. */
	@Schema(description = "Valor del criterio de búsqueda.")	
    private transient Object value;	

	/**
	 * Instantiates a new search criteria.
	 *
	 * @param key the key
	 * @param operation the operation
	 * @param value the value
	 */
	public SearchCriteria(String key, SearchOperation operation, Object value) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
	}
}