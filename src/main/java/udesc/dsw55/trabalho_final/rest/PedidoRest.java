package udesc.dsw55.trabalho_final.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import udesc.dsw55.trabalho_final.dao.CarrinhoDao;
import udesc.dsw55.trabalho_final.dao.ProdutoPedidoDao;
import udesc.dsw55.trabalho_final.jpa.CarrinhoRepository;
import udesc.dsw55.trabalho_final.jpa.PedidoRepository;
import udesc.dsw55.trabalho_final.jpa.ProdutoPedidoRepository;
import udesc.dsw55.trabalho_final.model.ModelCarrinho;
import udesc.dsw55.trabalho_final.model.ModelPedido;
import udesc.dsw55.trabalho_final.model.ModelProdutoPedido;

@RestController
public class PedidoRest {

	private PedidoRepository repository;
	private CarrinhoRepository carrinhoRepository;
	private ProdutoPedidoRepository prodPedidoRepository;
	
	public PedidoRest(PedidoRepository repository, CarrinhoRepository carrinhoRepository,
			ProdutoPedidoRepository prodPedidoRepository) {
		super();
		this.repository = repository;
		this.carrinhoRepository = carrinhoRepository;
		this.prodPedidoRepository = prodPedidoRepository;
	}

	@GetMapping("/pedidos/{id}")
	public ModelPedido getPedidoById(@PathVariable int id) throws Exception{
		Optional<ModelPedido> pedido = repository.findById(id);
		if(pedido.isEmpty()) {
			throw new Exception("Erro: Id do pedido não encontrado");
		}
		return pedido.get();
	} 
	
	@PostMapping("/pedidos")
	public ResponseEntity<ModelPedido> createPedido(@Valid @RequestBody ModelPedido pedido){
		ModelPedido savedPedido = repository.save(pedido);
		List<ModelCarrinho> carrinho = CarrinhoDao.getCarrinhoByUser(carrinhoRepository, savedPedido.getUsuario());
		
		for (ModelCarrinho modelCarrinho : carrinho) {
			ModelProdutoPedido prodPedido = new ModelProdutoPedido();
			prodPedido.setPedido(savedPedido.getId());
			prodPedido.setProduto(modelCarrinho.getProduto());
			prodPedido.setQuantidade(modelCarrinho.getQuantidade());
			prodPedidoRepository.save(prodPedido);
			carrinhoRepository.delete(modelCarrinho);
		}
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPedido.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();		
	}
	
	@GetMapping("/pedidos/{id}/product")
	public List<ModelProdutoPedido> getListProdutoPedido(@PathVariable int id) throws Exception{
		List<ModelProdutoPedido> lista = ProdutoPedidoDao.getProdutosByPedido(prodPedidoRepository, id);
		return lista;
	}
	
	@GetMapping("/pedidos")
	public List<ModelPedido> getListPedido(){
		return repository.findAll();
	} 
	
	
}
