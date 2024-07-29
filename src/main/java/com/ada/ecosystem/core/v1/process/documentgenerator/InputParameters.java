package com.ada.ecosystem.core.v1.process.documentgenerator;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>Contiene los parámetros de entrada para la ejecución de un proceso de generación de interfaz de documentos.</p>
 * @author carlos.torres
 * @version 1.0.0
 */
@Data
public class InputParameters implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo comercial. */
	private Long codigoComercial;
	
	/** The codigo documento. */
	private Long codigoDocumento;
	
	/** The consecutivo documento. */
	private Long consecutivoDocumento;
	
	/** The fecha documento. */
	private Date fechaDocumento;
	
	/** The descripcion. */
	private String descripcion;	
	
	/** The codigo tercero. */
	private Long codigoTercero;
	
	/** The tipo. */
	private String tipo;
	
	/** The estado. */
	private String estado;
}