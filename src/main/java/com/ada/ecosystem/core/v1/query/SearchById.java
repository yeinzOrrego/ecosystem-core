package com.ada.ecosystem.core.v1.query;

import java.util.List;

public interface SearchById<D, T> {	
	public List<D> searchList(T id);
	public D searchOnlyOne(T id);
}
