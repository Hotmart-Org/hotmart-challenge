package com.hotmart.challenge.web.converter;

import java.util.List;

public interface IGenericConverter<E, D> {

	public abstract D entityToDTO(E entity, Class<D> clazz);
	public abstract List<D> entityToCollectionDTO(List<E> entityList, Class<D> dtoClass);
	
}
