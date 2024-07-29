package com.ada.ecosystem.core.v1.kafka;

import java.io.Serializable;

import com.ada.ecosystem.core.v1.utility.EcosystemUtilities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * The Class KafkaMessage.
 */
@Schema(description = "Clase que envia mensajes a un broker kafka.")
@Data
public class KafkaMessage implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The uuid. */
	@Schema(description = "Identificador de proceso kafka. Se utiliza para consultar mensajes del topic (tema).")
	private String uuid;
	
	/** The topic name. */
	@Schema(description = "Nombre del topic (tema).")
	private String topicName;
	
	/** The process name. */
	@Schema(description = "Nombre del proceso.")
	private String processName;
	
	/** The message. */
	@Schema(description = "Mensajes que se enviará al topic (tema).")
	private String message;
	
	/**
	 * Instantiates a new kafka message.
	 */
	public KafkaMessage() {
		uuid = java.util.UUID.randomUUID().toString();
		processName = EcosystemUtilities.INDEFINITE_PROCESS;
	}
}
