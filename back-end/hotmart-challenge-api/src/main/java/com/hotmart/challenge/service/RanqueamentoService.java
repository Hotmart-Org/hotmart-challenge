package com.hotmart.challenge.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotmart.challenge.domain.model.Produto;

/**
 * Service para ranquear os produtos diariamente.
 *  
 * @author thiago vaz
 *
 */
@Service
public class RanqueamentoService {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@Autowired
	private VendaService vendaService;
	
	@Autowired
	private ProdutoService produtoService;
	
	/**
	 * Ranquear os produtos de acordo com a fórmula:
	 * X = Média de avaliação do produto nos últimos 12 meses
	 * Y = Quantidade de vendas/dias que o produto existe
	 * Z = Quantidade de notícias da categoria do produto no dia corrente
	 * 
	 * @param produtos
	 * @param mapNoticiasCategoria
	 */
	public void ranquear(List<Produto> produtos, Map<String, Long> mapNoticiasCategoria) {

		produtos.forEach(p -> {
			
			long totalAvaliacao = avaliacaoService.countByProduto(p, 12);
			
			double x_mediaAvaliacao = (double) totalAvaliacao / 12;
			
			long totalVendas = vendaService.countByProduto(p);
			
			double y_mediaVenda = (double) totalVendas / ChronoUnit.DAYS.between(p.getDataCriacao().toLocalDate(), LocalDate.now());
			
			long z_totalNoticias = mapNoticiasCategoria.get(p.getCategoria().getNome());
			
			double score = (double) x_mediaAvaliacao + y_mediaVenda + z_totalNoticias;
			
			p.setScore(score);
			
			produtoService.update(p.getId(), p);
			
		});
	}
}
