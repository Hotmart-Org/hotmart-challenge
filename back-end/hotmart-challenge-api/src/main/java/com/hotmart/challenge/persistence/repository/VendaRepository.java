package com.hotmart.challenge.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotmart.challenge.domain.model.Produto;
import com.hotmart.challenge.domain.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

	long countByProduto(Produto produto);
}
