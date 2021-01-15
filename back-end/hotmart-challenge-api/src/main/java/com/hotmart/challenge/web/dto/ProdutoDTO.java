package com.hotmart.challenge.web.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotmart.challenge.domain.model.base.GenericBaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProdutoDTO extends GenericBaseModel<ProdutoDTO> {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private String descricao;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dataCriacao;
	
	private Double score;
	
}
