package com.hotmart.challenge.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hotmart.challenge.domain.model.base.GenericBaseModel;
import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "avaliacao")
public class Avaliacao extends GenericBaseModel<Avaliacao> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer avaliacao;
	
	private LocalDateTime data;

	@NotNull
	@OneToOne
	@JoinColumn(name = "id_venda")
	private Venda venda;
}
