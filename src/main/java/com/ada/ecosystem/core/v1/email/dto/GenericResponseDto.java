package com.ada.ecosystem.core.v1.email.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase genérica utilizada para respuestas en servicios.
 *
 * @param <T> Tipo genérico que se puede adicionar en la respuesta del servicio.
 * @author carlos.torres@ada.co 
 * @version 1.0
 */
@Schema(name = "GenericResponseDto", description = "Dto generico utilizado para respuestas en servicios.")
@Data
@NoArgsConstructor
public class GenericResponseDto<T> implements Serializable{
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;	
	
	/** Código de respuesta. */
	@Schema(description = "Código de respuesta.")
	private Long code;
	
	/** Mensaje de respuesta. */
	@Schema(description = "Mensaje de respuesta.")
	private String message;
	
	/** Contenido de la respuesta. */
	@Schema(description = "Contenido de la respuesta.")
	private transient T content;
	
	/**
	 * Crea un nuevo Dto con código y mensaje de respuesta.
	 *
	 * @param code Código de respuesta
	 * @param message Mensaje de respuesta
	 */
	public GenericResponseDto(Long code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	/**
	 * Crea un Dto con código, mensaje y contenido de respuesta.
	 *
	 * @param code Código de respuesta
	 * @param message Mensaje de respuesta
	 * @param content Contenido de respuesta
	 */
	public GenericResponseDto(Long code, String message, T content) {
		super();
		this.code = code;
		this.message = message;
		this.content = content;
	}
}
