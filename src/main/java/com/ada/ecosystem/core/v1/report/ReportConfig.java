package com.ada.ecosystem.core.v1.report;

import java.io.Serializable;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Clase que almacena la información de lanzamiento de petición de consulta deun reporte o formato genérico.")
@Data
public class ReportConfig implements Serializable{
	private static final long serialVersionUID = 1L;
	@Schema(description = "Código del cliente. Identifica la conexión y la configuración interna del reporte.")
	private String connectCode;
	@Schema(description = "Ruta base donde se almacena el reporte.")
	private String reportPathCompile;	 
	@Schema(description = "Nombre del reporte.")
	private String reportName;
	@Schema(description = "Parámetros que requiere el reporte para generarse.")
	private transient Map<String, Object> parameters;
	@Schema(description = "Archivo del reporte (Esta propiedad se utiliza cuando el reporte es generado dinamicamente sin configuración).")
	private String reporteBase64;
}
