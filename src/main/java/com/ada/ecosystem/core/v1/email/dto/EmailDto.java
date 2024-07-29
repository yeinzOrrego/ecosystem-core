package com.ada.ecosystem.core.v1.email.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Contiene las configuraciones de autenticación y envío del correo electronico.
 * 
 * @author carlos.torres@ada.co 
 * @version 1.0
 */
@Schema(name = "EmailDto", description = "Clase Dto que contiene la configuración e información del correo electrónico.")
@Data
public class EmailDto implements Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The from. */
	private AddressConfigDto from;
	
	/** Título del correo electrónico. */
	@Schema(description = "Título del correo electrónico.")
	private String subject;
	
	/** Contenido del correo electrónico. */
	@Schema(description = "Contenido del correo electrónico.")
	private String content;
	
	/** Indica si el contenido está generado en html. */
	@Schema(description = "Indica si el contenido está generado en html.")
	private Boolean html;
	
	/** Listado de objetos adjunto en string base 64. */
	@Schema(description = "Listado de objetos adjunto en string base 64.")
	private List<AttachmentBase64ConfigDto> attachmentBase64;
	
	/** Listado de direcciones de correo principal. */
	@Schema(description = "Listado de direcciones de correo principal.")
	private List<AddressConfigDto> toEmail;
	
	/** Listado de direcciones de correo de copia. */
	@Schema(description = "Listado de direcciones de correo de copia.")
	private List<AddressConfigDto> ccEmail;
	
	/** Listado de direcciones de correo de copia oculta. */
	@Schema(description = "Listado de direcciones de correo de copia oculta.")
	private List<AddressConfigDto> bccEmail;
		
	/** Listado de direcciones de correo para controlar duplicados. */
	@Schema(description = "Listado de direcciones de correo para controlar duplicados.")
	private List<String> uniqueEmail;

	/**
	 * Instantiates a new email dto.
	 */
	public EmailDto() {
		super();
		attachmentBase64 = new ArrayList<>();		
		toEmail = new ArrayList<>();		
		ccEmail = new ArrayList<>();		
		bccEmail = new ArrayList<>();			
		uniqueEmail = new ArrayList<>();
	}	
}