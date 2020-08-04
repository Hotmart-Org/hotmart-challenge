package com.hotmart.challenge.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.hotmart.challenge.domain.model.base.GenericBaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto extends GenericBaseModel<Produto> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 100)
	private String nome;
	
	@NotNull
	@Size(max = 1000)
	private String descricao;
	
	@NotNull
	private LocalDateTime dataCriacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	
	private double score;
	
	public Produto(Long id, String nome, String descricao, LocalDateTime dataCriacao, Double score) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
		this.score = score;
	}
	
}
