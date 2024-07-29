package com.ada.ecosystem.core.v1.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ada.ecosystem.core.v1.query.SortCriteria;

/**
 * The Interface EcosystemPageableSortingPredicate.
 */
public interface EcosystemPageableSortingPredicate {	
	
	/**
	 * Gets the pageable.
	 *
	 * @param pageNo the page no
	 * @param sortBy the sort by
	 * @return the pageable
	 */
	public Pageable getPageable(Integer pageNo, String sortBy);
	
	
	/**
	 * Gets the pageable.
	 *
	 * @param pageNo the page no
	 * @param ordersBy the orders by
	 * @return the pageable
	 */
	public Pageable getPageable(Integer pageNo, List<SortCriteria> ordersBy);
	
	/**
	 * Gets the pageable.
	 *
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @param sortBy the sort by
	 * @return the pageable
	 */
	public Pageable getPageable(Integer pageNo, Integer pageSize, String sortBy);
	
	/**
	 * Gets the pageable.
	 *
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @param ordersBy the orders by
	 * @return the pageable
	 */
	public Pageable getPageable(Integer pageNo, Integer pageSize, List<SortCriteria> ordersBy);

	/**
	 * Buil advanced sort.
	 *
	 * @param sortBy the sort by
	 * @return the sort
	 */
	public Sort buildAdvancedSort(String sortBy);	
	
	/**
	 * Builds the advanced sort.
	 *
	 * @param ordersBy the orders by
	 * @return the sort
	 */
	public Sort buildAdvancedSort(List<SortCriteria> ordersBy);	
}