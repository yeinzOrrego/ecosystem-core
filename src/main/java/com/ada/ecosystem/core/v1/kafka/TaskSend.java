package com.ada.ecosystem.core.v1.kafka;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * The Class TaskSend.
 */
@Schema(description = "Objeto que encapsula el envio de una tarea a un borker kafka.")
@Data
public class TaskSend implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The topic name. */
	@Schema(description = "Nombre del topic.")
	private String topicName;
	
	/** The key. */
	@Schema(description = "Mensaje enviado al topic.")
	private String key;
	
	/** The value. */
	@Schema(description = "Estado del mensaje de tarea enviado al topic.")
	private TaskStatus value;
}