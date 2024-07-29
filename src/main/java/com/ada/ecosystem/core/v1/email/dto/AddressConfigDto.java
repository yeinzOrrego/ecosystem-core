package com.ada.ecosystem.core.v1.email.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Contiene la configuración de la dirección de correo electrónico.
 * 
 * @author carlos.torres@ada.co 
 * @version 1.0
 */
@Schema(name = "AddressConfigDto", description = "Clase Dto que contiene la configuración de la dirección de correo electronico.")
@Data
public class AddressConfigDto implements Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Dirección de correo electrónico. */
	@Schema(description = "Dirección de correo electrónico.")
	private String address;
	
	/** Nombre del usuario asociado a la cuenta. */
	@Schema(description = "Nombre del usuario asociado a la cuenta.")
	private String persona;
}