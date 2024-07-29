package com.ada.ecosystem.core.v1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.ada.ecosystem.core.v1.obfuscate.ObfuscatorPropertyAccessor;
import com.ada.ecosystem.core.v1.pageable.PageDto;
import com.ada.ecosystem.core.v1.query.SearchCriteria;
import com.ada.ecosystem.core.v1.query.SearchSpecifications;
import com.ada.ecosystem.core.v1.query.SortCriteria;
import com.ada.ecosystem.core.v1.query.SortOperation;

/**
 * The Class EcosystemService.
 */
@Service
public abstract class EcosystemService implements EcosystemPageableSortingPredicate{
	
	/** The log. */
	private Logger log = LoggerFactory.getLogger(EcosystemService.class);
	
	/** The Constant PAGE_SIZE. */
	public static final Integer PAGE_SIZE = 10;	
	
	/**
	 * Gets the pageable.
	 *
	 * @param pageNo the page no
	 * @param sortBy the sort by
	 * @return the pageable
	 */
	@Override
	public Pageable getPageable(Integer pageNo, String sortBy) {
		Pageable paging;
		Integer pageSize = PAGE_SIZE;
		//==============================================================================
		// Fecha: 11:58 a. m. sábado, 27 de junio de 2020 - carlos.torres@ada.co
		// Identificar el ordenamiento
		//==============================================================================
		if(sortBy != null) {
			paging = PageRequest.of(pageNo, pageSize, buildAdvancedSort(sortBy));
		}
		else {
			paging = PageRequest.of(pageNo, pageSize);
		}
		return paging;
	}
	
	/**
	 * Gets the pageable.
	 *
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @param sortBy the sort by
	 * @return the pageable
	 */
	@Override
	public Pageable getPageable(Integer pageNo, Integer pageSize, String sortBy) {				 	
		pageSize = (pageSize != null) ? pageSize : PAGE_SIZE;
		//==============================================================================
		// Fecha: 11:58 a. m. sábado, 27 de junio de 2020 - carlos.torres@ada.co
		// Identificar el ordenamiento
		//==============================================================================
		Pageable paging;
		if(sortBy != null) {
			paging = PageRequest.of(pageNo, pageSize, buildAdvancedSort(sortBy));
		}
		else {
			paging = PageRequest.of(pageNo, pageSize);
		}
		return paging;
	}	

	/**
	 * Gets the pageable.
	 *
	 * @param pageNo the page no
	 * @param ordersBy the orders by
	 * @return the pageable
	 */
	@Override
	public Pageable getPageable(Integer pageNo, List<SortCriteria> ordersBy) {
		Integer pageSize = PAGE_SIZE;
		Pageable paging;
		if(ordersBy == null) {
			paging = PageRequest.of(pageNo, pageSize);			
		}
		else {
			paging = PageRequest.of(pageNo, pageSize, buildAdvancedSort(ordersBy));
		}
		return paging;
	}

	/**
	 * Gets the pageable.
	 *
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @param ordersBy the orders by
	 * @return the pageable
	 */
	@Override
	public Pageable getPageable(Integer pageNo, Integer pageSize, List<SortCriteria> ordersBy) {
		pageSize = (pageSize != null) ? pageSize : PAGE_SIZE;
		Pageable paging;
		if(ordersBy == null) {
			paging = PageRequest.of(pageNo, pageSize);			
		}
		else {
			paging = PageRequest.of(pageNo, pageSize, buildAdvancedSort(ordersBy));
		}
		return paging;
	}

	/**
	 * Buil advanced sort.
	 *
	 * @param sortBy the sort by
	 * @return the sort
	 */
	@Override
	public Sort buildAdvancedSort(String sortBy) {
		if (sortBy != null) {
			String separator = ",";
			String patternStr = "(\\w+?)(:)(\\w+?)" + separator;
			Pattern pattern = Pattern.compile(patternStr);
			Matcher matcher = pattern.matcher(sortBy + separator);
			List<Order> orders = new ArrayList<>();
			while (matcher.find()) {
				Order order = null;
				if (matcher.group(3).contains("desc")) {
					order = new Order(Sort.Direction.DESC, matcher.group(1));
				} else {
					order = new Order(Sort.Direction.ASC, matcher.group(1));
				}

				orders.add(order);
			}
			return Sort.by(orders);
		}
		return null;
	}
	
	/**
	 * Buil advanced sort.
	 *
	 * @param ordersBy the sort by
	 * @return the sort
	 */
	@Override
	public Sort buildAdvancedSort(List<SortCriteria> ordersBy) {
		if(ordersBy != null && !ordersBy.isEmpty()) {
			List<Order> orders = new ArrayList<>();
			for(var orderBy : ordersBy) {
				if(!orderBy.getColumn().isBlank()) {
					Order order = new Order(
							orderBy.getDirection().equals(SortOperation.DESC) ? Sort.Direction.DESC : Sort.Direction.ASC, 
							orderBy.getColumn());
					orders.add(order);
				}
			}
			return orders.isEmpty() ? null : Sort.by(orders);
		}
		return null;
	}
	
	/**
	 * Gets the search specifications.
	 *
	 * @param <T> the generic type
	 * @param searchsBy the searchs by
	 * @return the search specifications
	 */
	public <T> SearchSpecifications<T> getSearchSpecifications(List<SearchCriteria> searchsBy) {
		SearchSpecifications<T> searchSpecifications = new SearchSpecifications<>();
		for(var searchBy : searchsBy) {
			if(validateSearchBy(searchBy)) {
				searchSpecifications.add(createSearchCriteria(searchBy));
			}
		}
		return searchSpecifications;
	}

	/**
	 * Creates the search criteria.
	 *
	 * @param searchBy the search by
	 * @return the search criteria
	 */
	private SearchCriteria createSearchCriteria(SearchCriteria searchBy) {
		return new SearchCriteria(searchBy.getKey(),
				searchBy.getOperation(), 
				searchBy.getValue());
	}

	/**
	 * Validate search by.
	 *
	 * @param searchBy the search by
	 * @return true, if successful
	 */
	private boolean validateSearchBy(SearchCriteria searchBy) {
		return (searchBy.getKey() != null && !searchBy.getKey().isEmpty()) &&
				(searchBy.getOperation() != null) &&
				(searchBy.getValue() != null);
	}
	
	/**
	 * Return page dto.
	 *
	 * @param <D> the generic type
	 * @param <E> the element type
	 * @param entitiesList the entities list
	 * @param dto the dto
	 * @return the page dto
	 */
	public <D, E> PageDto<D> returnPageDto(Page<E> entitiesList, Class<D> dto) {
		var modelMapper = new ModelMapper();
        PageDto<D> pageDto = new PageDto<>();
        pageDto.setNumber(entitiesList.getNumber());
        pageDto.setNumberOfElements(entitiesList.getNumberOfElements());
        pageDto.setSize(entitiesList.getSize());
        pageDto.setTotalElements(entitiesList.getTotalElements());
        pageDto.setTotalPages(entitiesList.getTotalPages());
        pageDto.setContent(entitiesList.getContent().isEmpty() ? new ArrayList<>() : entitiesList.stream().map(entidad -> modelMapper.map(entidad, dto)).toList());
		return pageDto;
	}	
	
	/**
	 * Return list dto.
	 *
	 * @param <D> the generic type
	 * @param <E> the element type
	 * @param listSource the list source
	 * @param target the target
	 * @return the list
	 */
	public <D, E> List<D> returnListDto(List<E> listSource, Class<D> target) {
		var modelMapper = new ModelMapper();
        return listSource.stream().map(entidad -> modelMapper.map(entidad, target)).toList();
	}
	
	/**
	 * Return dto.
	 *
	 * @param <D> the generic type
	 * @param <E> the element type
	 * @param source the source
	 * @param target the target
	 * @return the d
	 */
	public <D, E> D returnDto(E source, Class<D> target) {
		var modelMapper = new ModelMapper();
        return modelMapper.map(source, target);
	}
	
	/**
	 * Return dto.
	 *
	 * @param <D> the generic type
	 * @param <E> the element type
	 * @param source the source
	 * @param target the target
	 * @return the d
	 */
	public <D, E> D returnObfuscateDto(E source, Class<D> target) {
		ModelMapper modelMapper = new ModelMapper();
		Converter<E, D> obfuscatorConverter = new ObfuscatorPropertyAccessor<>();		
		modelMapper.createTypeMap(source, target).setPostConverter(obfuscatorConverter);
		return modelMapper.map(source, target);
	}	
	
	/**
	 * Return list dto.
	 *
	 * @param <D> the generic type
	 * @param <E> the element type
	 * @param listSource the list source
	 * @param target the target
	 * @return the list
	 */
	public <D, E> List<D> returnObfuscateListDto(List<E> sourceList, Class<D> target) {
		ObfuscatorPropertyAccessor<E, D> obfuscator = new ObfuscatorPropertyAccessor<>();
        return obfuscator.convertList(sourceList, target);
	}
	
	public <D, E> PageDto<D> returnObfuscatePageDto(Page<E> sourcePage, Class<D> target) {
		ObfuscatorPropertyAccessor<E, D> obfuscator = new ObfuscatorPropertyAccessor<>();
        return obfuscator.convertPage(sourcePage, target);
	}	
	
	/**
	 * Prints the info log.
	 *
	 * @param logProcess the log process
	 * @param log the log
	 * @param message the message
	 */
	protected void printInfoLog(StringBuilder logProcess, Logger log, String message) {
		log.info(message);
		logProcess.append(message);
	}
	
	/**
	 * Prints the info log.
	 *
	 * @param logProcess the log process
	 * @param message the message
	 */
	protected void printInfoLog(StringBuilder logProcess, String message) {
		log.info(message);
		logProcess.append(message);
	}
	
	/**
	 * Prints the info log.
	 *
	 * @param message the message
	 */
	protected void printInfoLog(String message) {
		log.info(message);		
	}
	
	/**
	 * Prints the error log.
	 *
	 * @param logProcess the log process
	 * @param log the log
	 * @param message the message
	 */
	protected void printErrorLog(StringBuilder logProcess, Logger log, String message) {
		log.error(message);
		logProcess.append(message);
	}
	
	/**
	 * Prints the error log.
	 *
	 * @param logProcess the log process
	 * @param message the message
	 */
	protected void printErrorLog(StringBuilder logProcess, String message) {
		log.error(message);
		logProcess.append(message);
	}
	
	/**
	 * Prints the error log.
	 *
	 * @param message the message
	 */
	protected void printErrorLog(String message) {
		log.error(message);		
	}
	
	/**
	 * Prints the warn log.
	 *
	 * @param logProcess the log process
	 * @param log the log
	 * @param message the message
	 */
	protected void printWarnLog(StringBuilder logProcess, Logger log, String message) {
		log.warn(message);
		logProcess.append(message);
	}
	
	/**
	 * Prints the warn log.
	 *
	 * @param logProcess the log process
	 * @param message the message
	 */
	protected void printWarnLog(StringBuilder logProcess, String message) {
		log.warn(message);
		logProcess.append(message);
	}
	
	/**
	 * Prints the warn log.
	 *
	 * @param message the message
	 */
	protected void printWarnLog(String message) {
		log.warn(message);		
	}
	
	/**
	 * Prints the debug log.
	 *
	 * @param logProcess the log process
	 * @param log the log
	 * @param message the message
	 */
	protected void printDebugLog(StringBuilder logProcess, Logger log, String message) {
		log.debug(message);
		logProcess.append(message);
	}
	
	/**
	 * Prints the warn log.
	 *
	 * @param logProcess the log process
	 * @param message the message
	 */
	protected void printDebugLog(StringBuilder logProcess, String message) {
		log.debug(message);
		logProcess.append(message);
	}
	
	/**
	 * Prints the debug log.
	 *
	 * @param message the message
	 */
	protected void printDebugLog(String message) {
		log.debug(message);		
	}
}