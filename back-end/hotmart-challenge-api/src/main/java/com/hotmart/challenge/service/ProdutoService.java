package com.hotmart.challenge.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hotmart.challenge.domain.model.Produto;
import com.hotmart.challenge.persistence.repository.ProdutoRepository;
import com.hotmart.challenge.service.base.AGenericService;

@Service
public class ProdutoService extends AGenericService<Produto, Long> {

	@Autowired
	private ProdutoRepository repository;

	@Override
	protected JpaRepository<Produto, Long> getRepository() {
		return repository;
	}
	
	/**
	 * Busca todos os produtos e ordena pelo id
	 * @param pageable
	 * @return lista de produtos ordenada e paginada
	 */
	public Page<Produto> findAllOrderById(Pageable pageable) {
		return repository.findAllOrderById(pageable);
	}
	
	/**
	 * Busca todos os produtos
	 * @param pageable
	 * @return lista de produtos paginada e ordenada pelo ranqueamento, pelo nome e pela categoria
	 */
	public Page<Produto> findProdutos(Pageable pageable) {
		return repository.findProdutos(pageable);
	}
	
	/**
	 * Busca produtos pelo nome
	 * @param pageable
	 * @param nome
	 * @return lista de produtos paginada e filtrada pelo nome
	 */
	public Page<Produto> findProdutosByNome(Pageable pageable, String nome) {
		Page<Produto> produtos = repository.findProdutosByNome(pageable, nome);
				
		if(CollectionUtils.isEmpty(produtos.getContent())) {
			throw new EmptyResultDataAccessException(1);
		}
		return produtos;
	}
}
