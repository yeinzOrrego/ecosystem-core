package com.ada.ecosystem.core.v1.report;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Clase utilizada para consultar configuraciones de reportes.")
@Data
public class ReportSearchDto implements Serializable{
	private static final long serialVersionUID = 1L;
	@Schema(description = "Código interno del reporte.")
	private String reportCode;
	@Schema(description = "Código interno del cliente (Ese valor es igual a connectCode de la configuración de conexiones a origenes de datos).")
	private String clientCode;
	@Schema(description = "Código de la empresa.")
	private String codigoMempresa;
	public ReportSearchDto() {
		super();
		codigoMempresa = "9999999999";
	}
}