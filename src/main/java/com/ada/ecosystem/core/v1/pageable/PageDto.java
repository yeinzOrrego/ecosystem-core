package com.ada.ecosystem.core.v1.pageable;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Representa una pagina de una consulta paginada.")
@Data
public class PageDto<T> {
	@Schema(description = "Representa el tamaño de la página.")
	Integer size;
	@Schema(description = "Representa el número de la página.")
	Integer number;
	@Schema(description = "Representa el número de elementos de la página.")
	Integer numberOfElements;
	@Schema(description = "Representa el total de registros de la consulta.")
	Long totalElements;
	@Schema(description = "Representa el total de páginas de la consulta.")
	Integer totalPages;
	@Schema(description = "Representa el contenido de la consulta.")
	List<T> content;
}