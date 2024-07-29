package com.ada.ecosystem.core.v1.param;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Clase utilizada para consultar parámetros de clientes.")
@Data
public class ParamConfigSearchDto implements Serializable{
	private static final long serialVersionUID = 1L;
	@Schema(description = "Nombre del parámetro.")
	private String paramName;
	@Schema(description = "Código interno del cliente (Ese valor es igual a connectCode de la configuración de conexiones a origenes de datos).")
	private String clientCode;
	@Schema(description = "Código de la empresa.")
	private String codigoMempresa;
	public ParamConfigSearchDto() {
		super();
		codigoMempresa = "9999999999";
	}
}