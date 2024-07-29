package com.ada.ecosystem.core.v1.process;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.ada.ecosystem.core.v1.kafka.TaskSend;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>Contiene los parámetros de entrada para la ejecución de un proceso de generación de interfaz de documentos.</p>
 *
 * @author carlos.torres
 * @version 1.0.0
 * @param <T> the generic type
 */
@Schema(description = "Contiene el objeto de parametro de entrada a los procesos.")
@Data
public class ProcessMapParameters implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo comercial. */
	@Schema(description = "Código del cliente en ejecución.")
	private String codigoCliente;
	
	/** The codigo comercial. */
	@Schema(description = "Código de la empresa del cliente en ejecución.")
	private String codigoEmpresa;
	
	/** The codigo usuario. */
	@Schema(description = "Código del usuario de la empresa del cliente en ejecución.")
	private Long codigoUsuario;
	
	/** The fecha. */
	@Schema(description = "Fecha del sistema en ejecución.")
	private Date fecha;	
	
	/** The debug. */
	@Schema(description = "Bandera indica para proceso de debug.")
	private Boolean debug;
	
	/** The task send. */
	@Schema(description = "Tarea asociada al proceso.")
	private TaskSend taskSend;
	
	/** The param. */	
	@Schema(description = "Diccionario que representa los parámetros de entrada del proceso.")	
	private transient Map<String, String> paramMap;
}