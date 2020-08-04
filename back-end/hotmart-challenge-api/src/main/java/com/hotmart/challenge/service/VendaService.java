package com.hotmart.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hotmart.challenge.domain.model.Produto;
import com.hotmart.challenge.domain.model.Venda;
import com.hotmart.challenge.persistence.repository.VendaRepository;
import com.hotmart.challenge.service.base.AGenericService;

@Service
public class VendaService extends AGenericService<Venda, Long> {
	
	@Autowired
	private VendaRepository repository;

	@Override
	protected JpaRepository<Venda, Long> getRepository() {
		return repository;
	}

	/**
	 * Busca a quantidade de venda por produto
	 * @param produto
	 * @return total de vendas por produtos
	 */
	public long countByProduto(Produto produto) {
		return repository.countByProduto(produto);
	}
}
