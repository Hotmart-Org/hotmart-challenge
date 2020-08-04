package com.hotmart.challenge.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hotmart.challenge.domain.model.Avaliacao;
import com.hotmart.challenge.domain.model.Produto;
import com.hotmart.challenge.persistence.repository.AvaliacaoRepository;
import com.hotmart.challenge.service.base.AGenericService;

@Service
public class AvaliacaoService extends AGenericService<Avaliacao, Long> {
	
	@Autowired
	private AvaliacaoRepository repository;

	@Override
	protected JpaRepository<Avaliacao, Long> getRepository() {
		return repository;
	}
	
	/**
	 * Busca o total de avaliações por produto nos últimos N meses
	 * @param produto
	 * @param qtdMeses
	 * @return quantidade de produtos avaliados nos últimos N meses
	 */
	public long countByProduto(Produto produto, long qtdMeses) {
		return repository.countByProduto(LocalDateTime.now().minusMonths(qtdMeses), produto);
	}
}
