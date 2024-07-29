package com.ada.ecosystem.core.v1.process;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>Contiene la respuesta de ejecución de un proceso de generación de interfaz de documentos.</p>
 * @author carlos.torres
 * @version 1.0.0
 */
@Schema(description = "Contiene la respuesta de ejecución de un proceso.")
@Data
public class ProcessResult<T> implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The result code. */
	@Schema(description = "Código de respuesta del proceso.")
	private Long resultCode;
	
	/** The result message. */
	@Schema(description = "Mensaje de respuesta del proceso.")
	private String resultMessage;
	
	/** The result log. */
	@Schema(description = "Log resultado del proceso.")
	private StringBuilder resultLog;
	
	@Schema(description = "Tipo de resultado del proceso.")
	private transient T result; 
}