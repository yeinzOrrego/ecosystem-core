package com.ada.ecosystem.core.v1.pageable;

import com.ada.ecosystem.core.v1.query.EcosystemRequestQuery;
import com.ada.ecosystem.core.v1.query.SearchSpecifications;

public interface PaginatedQuery <S, D> {
	
	public SearchSpecifications<S> getSearchSpecifications(EcosystemRequestQuery ecosystemRequestQuery);
	
	public PageDto<D> getPageDtoQuery(EcosystemRequestQuery ecosystemRequestQuery) ;
}
