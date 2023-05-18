package udesc.dsw55.trabalho_final.rest;

import org.springframework.web.bind.annotation.RestController;

import udesc.dsw55.trabalho_final.jpa.ProdutoPedidoRepository;
import udesc.dsw55.trabalho_final.jpa.ProdutoRepository;

@RestController
public class ProdutoPedidoRest {

	private ProdutoPedidoRepository repository;
	private ProdutoRepository prodRepository;
	
	public ProdutoPedidoRest(ProdutoPedidoRepository repository, ProdutoRepository prodRepository) {
		super();
		this.repository = repository;
		this.prodRepository = prodRepository;
	}

	
}
