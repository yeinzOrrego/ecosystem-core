package com.ada.ecosystem.core.v1.query;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The Enum SearchOperation.
 */
@Schema(description = "Define la operación de búsqueda.")
public enum SearchOperation {
	GREATER_THAN,
    LESS_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN_EQUAL,
    NOT_EQUAL,
    EQUAL,
    MATCH,
    MATCH_START,
    MATCH_END,
    IN,
    NOT_IN;	
}