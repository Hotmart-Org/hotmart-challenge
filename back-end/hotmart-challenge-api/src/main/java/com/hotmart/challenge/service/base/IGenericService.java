package com.hotmart.challenge.service.base;

import java.util.List;

import com.hotmart.challenge.domain.model.base.GenericBaseModel;

public interface IGenericService<E extends GenericBaseModel<E>, T>  {

	List<E> findAll();
	List<E> findAllById(Iterable<T> ids);
	E findById(Long id);
	E save(E entity);
	E update(Long id, E entity);
	void deleteById(Long id);
	Long getSavedEntityId(E savedEntity);
	long count();
}
