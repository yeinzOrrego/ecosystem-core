package com.ada.ecosystem.core.v1.query;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The Enum SortOperation.
 */
@Schema(description = "Define la operación de ordenamiento de una columna.")
public enum SortOperation {
	@Schema(description = "Operación de ordenamiento ascendente.")
	ASC,
	@Schema(description = "Operación de ordenamiento descendente.")
	DESC;	
}