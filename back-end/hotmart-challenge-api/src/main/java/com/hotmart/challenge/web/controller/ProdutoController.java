package com.hotmart.challenge.web.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotmart.challenge.domain.model.Produto;
import com.hotmart.challenge.service.ProdutoService;
import com.hotmart.challenge.service.base.IGenericService;
import com.hotmart.challenge.web.controller.base.AGenericController;
import com.hotmart.challenge.web.converter.GenericConverter;
import com.hotmart.challenge.web.dto.ProdutoDTO;
import com.hotmart.challenge.web.dto.ProdutoResponseDTO;
import com.hotmart.challenge.web.dto.ProdutoResponsePageDTO;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController extends AGenericController<Produto, Long> {

	@Autowired
	private ProdutoService service;
	
	@Autowired
	private GenericConverter<Produto, ProdutoDTO> converter;

	@Override
	protected IGenericService<Produto, Long> getService() {
		return service;
	}	
	
	/**
	 * Busca todos os produtos
	 * @param page
	 * @param size
	 * @return lista de produtos ordenada pelo ranqueamento, pelo nome e pela categoria
	 */
	@GetMapping("/")
	public ResponseEntity<ProdutoResponsePageDTO> findProdutos(@RequestParam("page") int page, @RequestParam("size") int size) {
		Page<ProdutoDTO> produtos = service.findProdutos(PageRequest.of(page, size)).map(produto -> converter.entityToDTO(produto, ProdutoDTO.class));
		ProdutoResponsePageDTO response = getProdutoResponsePageDTO(null, produtos);
		return response.getProdutos().getTotalElements() > 0 ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
	}
	
	/**
	 * Busca produtos pelo id
	 * @param id
	 * @return ProdutoResponseDTO
	 */
	@GetMapping("/id/{id}")
	public ResponseEntity<ProdutoResponseDTO> findProdutoById(@PathVariable("id") Long id) {
		Produto produto = getService().findById(id); 
		ProdutoResponseDTO produtoResponse = getProdutoResponseDTO(Objects.nonNull(id) ? id.toString() : null, produto);
		return Objects.nonNull(produto) ? ResponseEntity.ok(produtoResponse) : ResponseEntity.notFound().build();
	}
	
	/**
	 * Busca produtos pelo nome
	 * @param page
	 * @param size
	 * @param nome
	 * @return ProdutoResponsePageDTO
	 */
	@GetMapping("/nome")
	public ResponseEntity<ProdutoResponsePageDTO> findProdutosByNome(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam String nome) {
		Page<ProdutoDTO> produtos = service.findProdutosByNome(PageRequest.of(page, size), nome).map(produto -> converter.entityToDTO(produto, ProdutoDTO.class));
		ProdutoResponsePageDTO response = getProdutoResponsePageDTO(nome, produtos);
		return response.getProdutos().getTotalElements() > 0 ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
	}
	
	/**
	 * Monta o output paginado do serviço
	 * @param termoPesquisado
	 * @param produtos
	 * @return ProdutoResponsePageDTO
	 */
	private ProdutoResponsePageDTO getProdutoResponsePageDTO(String termoPesquisado, Page<ProdutoDTO> produtos) {
		ProdutoResponsePageDTO response = new ProdutoResponsePageDTO();
		response.setTermoPesquisado(termoPesquisado);
		response.setProdutos(produtos);
		response.setDataAtual(getDataAtual());
		return response;
	}

	/**
	 * Monta o output do serviço
	 * @param nome
	 * @param produtos
	 * @return ProdutoResponseDTO
	 */
	private ProdutoResponseDTO getProdutoResponseDTO(String nome, Produto produto) {
		ProdutoResponseDTO response = new ProdutoResponseDTO();
		response.setDataAtual(getDataAtual());
		response.setTermoPesquisado(nome);
		response.setProduto(converter.entityToDTO(produto, ProdutoDTO.class));
		return response;
	}
	
	private String getDataAtual() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return LocalDateTime.now().format(formatter);
	}

}
