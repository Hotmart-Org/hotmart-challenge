package com.hotmart.challenge.web.dto;

import org.springframework.data.domain.Page;

import com.hotmart.challenge.domain.model.base.GenericBaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProdutoResponsePageDTO extends GenericBaseModel<ProdutoResponsePageDTO> {

	private static final long serialVersionUID = 1L;

	private String dataAtual;
	
	private String termoPesquisado;
	
	private Page<ProdutoDTO> produtos;
	
}
