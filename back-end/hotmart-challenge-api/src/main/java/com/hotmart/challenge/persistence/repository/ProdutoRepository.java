package com.hotmart.challenge.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotmart.challenge.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query("select new Produto(p.id, p.nome, p.descricao, p.dataCriacao, p.score) from Produto as p inner join p.categoria as c order by p.score desc, p.nome asc, c.nome asc")
	Page<Produto> findProdutos(Pageable pageable);
	
	@Query("select new Produto(p.id, p.nome, p.descricao, p.dataCriacao, p.score) from Produto as p inner join p.categoria as c where p.nome like :nome order by p.score desc, p.nome asc, c.nome asc")
	Page<Produto> findProdutosByNome(Pageable pageable, @Param("nome") String nome);
	
	@Query("select p from Produto as p order by p.id")
	Page<Produto> findAllOrderById(Pageable pageable);
}
