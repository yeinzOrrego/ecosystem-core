package com.ada.ecosystem.core.v1.query;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Clase utilizada en el consumo de peticiones que realizan consultas paginadas y filtrado dinámico.")
@Data
public class EcosystemRequestQuery implements Serializable{
	private static final long serialVersionUID = 1L;
	@Schema(description = "Número de pagina a consultar.")
	private int page;
	@Schema(description = "Tamaño de la pagina a consultar.")
	private int size;
	@Schema(description = "Lista de ordenamiento que se aplicarán en la consulta.")
	private List<SearchCriteria> searchsBy;
	@Schema(description = "Lista de filtros que se aplicarán en la consulta.")
	private List<SortCriteria> ordersBy;	
	public EcosystemRequestQuery(int page, int size, List<SortCriteria> ordersBy, List<SearchCriteria> searchsBy) {
		super();
		this.page = page;
		this.size = size;
		this.ordersBy = ordersBy;
		this.searchsBy = searchsBy;
	}
}