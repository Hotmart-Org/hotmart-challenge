package com.hotmart.challenge.web.dto;

import com.hotmart.challenge.domain.model.base.GenericBaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProdutoResponseDTO extends GenericBaseModel<ProdutoResponseDTO> {

	private static final long serialVersionUID = 1L;

	private String dataAtual;
	
	private String termoPesquisado;
	
	private ProdutoDTO produto;

}
