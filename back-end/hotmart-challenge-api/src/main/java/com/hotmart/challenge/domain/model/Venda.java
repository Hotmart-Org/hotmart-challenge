package com.hotmart.challenge.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.hotmart.challenge.domain.model.base.GenericBaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "venda")
public class Venda extends GenericBaseModel<Venda> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_vendedor")
	private Vendedor vendedor;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_comprador")
	private Comprador comprador;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
}
