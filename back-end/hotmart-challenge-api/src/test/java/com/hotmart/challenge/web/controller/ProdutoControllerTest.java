package com.hotmart.challenge.web.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import com.hotmart.challenge.domain.model.Produto;
import com.hotmart.challenge.service.CategoriaService;
import com.hotmart.challenge.service.ProdutoService;
import com.hotmart.challenge.service.RanqueamentoService;
import com.hotmart.challenge.web.converter.GenericConverter;
import com.hotmart.challenge.web.dto.ProdutoDTO;

import io.restassured.http.ContentType;

@WebMvcTest
public class ProdutoControllerTest {
	
	private final String URL_SUCESSO = "api/v1/produtos/nome?page=0&size=10&nome=Bytecard";
	
	private final String URL_NAO_ENCONTRADO = "api/v1/produtos/nome?page=0&size=10&nome=Teste";

	@Autowired
	private ProdutoController produtoController;
	
	@MockBean
	private ProdutoService produtoService;
	
	@MockBean
	private RanqueamentoService ranqueamentoService;
	
	@MockBean
	private CategoriaService categoriaService;
	
	@MockBean
	private GenericConverter<Produto, ProdutoDTO> converter;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.produtoController);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarProduto() {
		when(this.produtoService.findProdutosByNome(PageRequest.of(0, 10),"Bytecard"))
			.thenReturn(getProdutos());
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get(URL_SUCESSO)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void deveRetornarNaoEncontrado_QuandoBuscarProduto() {
		when(this.produtoService.findProdutosByNome(PageRequest.of(0, 10), "Teste"))
			.thenReturn(new PageImpl(Arrays.asList()));
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get(URL_NAO_ENCONTRADO)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private Page<Produto> getProdutos() {
		List<Produto> produtos = new ArrayList<>();
		produtos.add(new Produto(463L, "Bytecard", "Aenean sit amet justo.", LocalDateTime.now(), 77.3));
		produtos.add(new Produto(529L, "Bytecard", "Praesent blandit.", LocalDateTime.now(), 76.2));
		Page<Produto> page = new PageImpl<>(produtos);
		return page;
	}
}
