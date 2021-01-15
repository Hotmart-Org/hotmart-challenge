package com.hotmart.challenge.web.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericConverter<E, D> implements IGenericConverter<E, D> {
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public D entityToDTO(E entity, Class<D> dtoClass) {
		return modelMapper.map(entity, dtoClass);
	}

	@Override
	public List<D> entityToCollectionDTO(List<E> entityList, Class<D> dtoClass) {
		return entityList.stream()
				.map(entity -> entityToDTO(entity, dtoClass))
				.collect(Collectors.toList());
	}

}
