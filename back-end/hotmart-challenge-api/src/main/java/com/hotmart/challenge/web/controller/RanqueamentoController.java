package com.hotmart.challenge.web.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hotmart.challenge.domain.model.Produto;
import com.hotmart.challenge.service.CategoriaService;
import com.hotmart.challenge.service.ProdutoService;
import com.hotmart.challenge.service.RanqueamentoService;
import com.hotmart.challenge.web.dto.NoticiaDTO;

/**
 * Api para ranquear os produtos
 * @author thiago vaz
 *
 */
@RestController
@RequestMapping("/api/v1/ranquear")
public class RanqueamentoController {
	
	private final int LIMIT = 500;
	
	@Autowired
	private RanqueamentoService service;

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	Map<String, Long> mapNoticiasCategoria = new LinkedHashMap<>();

	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity ranquearProdutos() {
		
		long totalProdutos = produtoService.count();
		
		if(totalProdutos > 0) {
			List<String> categorias = categoriaService.findNome();
			
			categorias.forEach(categoria -> {
				long z_totalNoticias = getTotalNoticiasPorCategoria(categoria);
				mapNoticiasCategoria.put(categoria, z_totalNoticias);
			});
			
			for(int i = 0; i < totalProdutos; i++) {
				
				Page<Produto> produtos = produtoService.findAllOrderById(PageRequest.of(i, LIMIT));
				
				if(CollectionUtils.isNotEmpty(produtos.getContent())) {
					service.ranquear(produtos.getContent(), mapNoticiasCategoria);
				} else {
					break;
				}
			}
		}
		
		return new ResponseEntity(HttpStatus.OK);
		
	}
	
	/**
	 * Busca a quantidade de notícias de uma determinada categoria utilizando a api https://newsapi.org/
	 * @param categoria
	 * @return quantidade de noticias da categoria
	 */
	private Long getTotalNoticiasPorCategoria(String categoria) {
		
		final String url = "http://newsapi.org/v2/top-headlines?country=br&category="+ categoria + "&apiKey=43f23aa1cf7b48789ec09f7af07cf05c";
		
		RestTemplate restTemplate = new RestTemplate();
	    
	    NoticiaDTO noticias = restTemplate.getForObject(url, NoticiaDTO.class);
		
		return noticias.getTotalResults();
	}
	
}
