package com.hotmart.challenge.persistence.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotmart.challenge.domain.model.Avaliacao;
import com.hotmart.challenge.domain.model.Produto;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
	
	@Query("select count(id) from Avaliacao a where a.data >= :data and a.venda.produto = :produto")
	long countByProduto(@Param("data") LocalDateTime data, @Param("produto") Produto produto);
}
