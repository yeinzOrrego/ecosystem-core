package com.ada.ecosystem.core.v1.obfuscate;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import org.springframework.data.domain.Page;
import com.ada.ecosystem.core.v1.pageable.PageDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObfuscatorPropertyAccessor<S, D> implements Converter<S, D> {

	/**
	 * Convert.
	 *
	 * @param context the context
	 * @return the d
	 */
	@Override
	public D convert(MappingContext<S, D> context) {		
		return convertDto(context.getSource(), context.getDestinationType());		
	}	
	
	/**
	 * Convert dto.
	 *
	 * @param source the source
	 * @param targetClass the target class
	 * @return the d
	 */
	public D convertDto(S source, Class<D> targetClass) {		
		try {			
			D dtoInstance = ObfuscatorPropertyAccessor.createObject(targetClass);		
			ReflectionUtil.copyColumnValues(source, dtoInstance);
			return dtoInstance;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;		
	}
	
	/**
	 * Convert list.
	 *
	 * @param sourceList the source list
	 * @param targetClass the target class
	 * @return the list
	 */
	public List<D> convertList(List<S> sourceList, Class<D> targetClass) {
		return sourceList.stream().map(source -> {
            return convertDto(source, targetClass);
        }).toList();
    }
	
	/**
	 * Convert page.
	 *
	 * @param sourcePage the source page
	 * @param targetClass the target class
	 * @return the page dto
	 */
	public PageDto<D> convertPage(Page<S> sourcePage, Class<D> targetClass) {
		PageDto<D> pageDto = new PageDto<>();
        pageDto.setNumber(sourcePage.getNumber());
        pageDto.setNumberOfElements(sourcePage.getNumberOfElements());
        pageDto.setSize(sourcePage.getSize());
        pageDto.setTotalElements(sourcePage.getTotalElements());
        pageDto.setTotalPages(sourcePage.getTotalPages());
        pageDto.setContent(sourcePage.getContent().isEmpty() ? new ArrayList<>() : convertList(sourcePage.getContent(), targetClass));
		return pageDto;
    }
	
	/**
	 * Creates the object.
	 *
	 * @param <T> the generic type
	 * @param clazz the clazz
	 * @return the t
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException the no such method exception
	 * @throws SecurityException the security exception
	 */
	public static <T> T createObject(Class<T> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {	 
	    return clazz.getDeclaredConstructor().newInstance();
	}
}