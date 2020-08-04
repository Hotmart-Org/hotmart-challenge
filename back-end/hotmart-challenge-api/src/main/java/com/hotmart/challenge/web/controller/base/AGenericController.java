package com.hotmart.challenge.web.controller.base;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hotmart.challenge.domain.model.base.GenericBaseModel;
import com.hotmart.challenge.service.base.IGenericService;

/**
 * Controller com operações básicas de CRUD
 * @author thiago vaz
 *
 * @param <E>
 * @param <T>
 */
public abstract class AGenericController<E extends GenericBaseModel<E>, T> {

	protected abstract IGenericService<E, T> getService();
	
	/**
	 * Busca todos os registros
	 */
	@GetMapping
	public List<E> findAll() {
		return getService().findAll();
	}
	
	/**
	 * Busca pelo id
	 * @param id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<E> findById(@PathVariable("id") Long id) {
		E entity = getService().findById(id); 
		return Objects.nonNull(entity) ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
	}
	
	/**
	 * Salva uma entidade
	 * @param entity
	 * @param response
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<E> save(@Valid @RequestBody E entity, HttpServletResponse response) {
		E savedEntity = getService().save(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(getService().getSavedEntityId(savedEntity)).toUri();
		return ResponseEntity.created(uri).body(savedEntity);
	}
	
	/**
	 * Atualiza uma entidade
	 * @param id
	 * @param entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<E> update(@PathVariable Long id, @Valid @RequestBody E entity) {
		E savedEntity = getService().update(id, entity);
		return ResponseEntity.ok(savedEntity);
	}
	
	/**
	 * Exclui pelo id
	 * @param id
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		getService().deleteById(id);
	}

}
