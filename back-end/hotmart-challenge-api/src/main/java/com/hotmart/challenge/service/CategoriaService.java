package com.hotmart.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hotmart.challenge.domain.model.Categoria;
import com.hotmart.challenge.persistence.repository.CategoriaRepository;
import com.hotmart.challenge.service.base.AGenericService;

@Service
public class CategoriaService extends AGenericService<Categoria, Long> {
	
	@Autowired
	private CategoriaRepository repository;

	@Override
	protected JpaRepository<Categoria, Long> getRepository() {
		return repository;
	}

	/**
	 * Busca os nomes das categorias
	 * @return lista com nomes das categorias
	 */
	public List<String> findNome() {
		return repository.findNome();
	}
}
