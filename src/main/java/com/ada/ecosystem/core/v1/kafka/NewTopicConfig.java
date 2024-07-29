package com.ada.ecosystem.core.v1.kafka;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * The Class NewTopicConfig.
 */
@Schema(description = "Objeto que contiene la información de creación de un topic (tema) kafka.")
@Data
public class NewTopicConfig implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The topic name. */
	@Schema(description = "Nombre del topic (tema) kafka.")
	private String topicName;
	
	/** The partitions. */
	@Schema(description = "Número de particiones del topic (tema) kafka.")
	private Integer partitions;
	
	/** The replicas. */
	@Schema(description = "Número de replicas del topic (tema) kafka.")
	private Integer replicas;
	
	/** The configurations. */
	@Schema(description = "Propiedades del topic (tema) kafka.")
	private Map<String, Object> configurations;
	
	/**
	 * Instantiates a new new topic config.
	 */
	public NewTopicConfig() {
		configurations = new HashMap<>();
	}
}