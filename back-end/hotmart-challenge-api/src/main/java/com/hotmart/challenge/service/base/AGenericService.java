package com.hotmart.challenge.service.base;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hotmart.challenge.domain.model.base.GenericBaseModel;

import lombok.extern.slf4j.Slf4j;

/**
 * Service genérico com operações básicas de CRUD
 * @author thiago vaz
 *
 * @param <E>
 * @param <T>
 */
@Slf4j
public abstract class AGenericService<E extends GenericBaseModel<E>, T> implements IGenericService<E, T> {
	
	protected abstract JpaRepository<E, T> getRepository();

	/**
	 * Busca todos os registros
	 */
	public List<E> findAll() {
		return getRepository().findAll();
	}
	
	/**
	 * Busca todos os registros
	 */
	public Page<E> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}
	
	/**
	 * Busca pelos ids
	 */
	public List<E> findAllById(Iterable<T> ids) {
		return getRepository().findAllById(ids);
	}
	
	/**
	 * Busca pelo id
	 * @param id
	 */
	public E findById(T id) {
		return getRepository().findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
	
	/**
	 * Salva um registro e retorna o registro salvo
	 */
	public E save(E entity) {
		return getRepository().save(entity);
	}
	
	/**
	 * Atualiza um registro
	 */
	public E update(Long id, E entity) {
		E savedEntity = findById(id);
		if(Objects.isNull(savedEntity)) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(entity, savedEntity, "id");
		return save(savedEntity);
	}
	
	/**
	 * Exclui pelo id
	 * @param id
	 */
	public void deleteById(T id) {
		getRepository().deleteById(id);
	}
	
	/**
	 * Retorna o total de registros
	 */
	public long count() {
		return getRepository().count();
	}
	
	/**
	 * Retorna o id do registro salvo
	 */
	public Long getSavedEntityId(E savedEntity) {
		if(Objects.nonNull(savedEntity)) {
			try {
				Field id = savedEntity.getClass().getDeclaredField("id");
				id.setAccessible(true);
				try {
					return (Long) id.get(savedEntity);
				} catch (IllegalArgumentException e) {
					log.error(null, e);
				} catch (IllegalAccessException e) {
					log.error(null, e);
				}
			} catch (NoSuchFieldException e) {
				log.error(null, e);
			} catch (SecurityException e) {
				log.error(null, e);
			}			
		}
		return null;
	}
}
