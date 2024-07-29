package com.ada.ecosystem.core.v1.email.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Contiene la configuración del archivo que se adjuntará al correo electrónico.
 * 
 * @author carlos.torres@ada.co 
 * @version 1.0
 */
@Schema(name = "AttachmentConfigDto", description = "Clase Dto que contiene la configuración del archivo que se adjuntará al correo electrónico.")
@Data
public class AttachmentBase64ConfigDto implements Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Nombre del archivo y extensión. */
	@Schema(description = "Nombre del archivo y extensión.")
	private String fileName;
	
	/** Contenido del archivo en string base 64. */
	@Schema(description = "Contenido del archivo en string base 64.")
	private String fileContentStringBase64;
	
	/** Tipo de contenido del archivo. */
	@Schema(description = "Tipo de contenido del archivo.")
	private String typeContent;
}