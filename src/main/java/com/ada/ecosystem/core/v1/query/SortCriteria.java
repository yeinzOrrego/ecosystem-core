package com.ada.ecosystem.core.v1.query;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * The Class SortCriteria.
 */
@Schema(description = "Define el criterio de ordenamiento de una columna.")
@Data
public class SortCriteria implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** The column. */
	@Schema(description = "Nombre de la columna.")
	private String column;
    
    /** The direction. */
	@Schema(description = "Dirección de ordenamiento de la columna.")
    private SortOperation direction;
    
	/**
	 * Instantiates a new sort criteria.
	 *
	 * @param column the column
	 * @param direction the direction
	 */
	public SortCriteria(String column, SortOperation direction) {
		super();
		this.column = column;
		this.direction = direction;
	}
}