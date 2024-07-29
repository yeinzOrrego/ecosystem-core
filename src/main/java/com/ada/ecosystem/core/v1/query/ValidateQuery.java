package com.ada.ecosystem.core.v1.query;

import java.util.List;

public interface ValidateQuery {
	
	public Boolean validateQuery(Integer label, List<SearchCriteria> searchsBy, List<String> errores, List<String> soluciones);

}
