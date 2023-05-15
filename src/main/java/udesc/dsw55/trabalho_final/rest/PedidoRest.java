package udesc.dsw55.trabalho_final.rest;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import udesc.dsw55.trabalho_final.dao.PedidoDao;
import udesc.dsw55.trabalho_final.jpa.CarrinhoRepository;
import udesc.dsw55.trabalho_final.jpa.PedidoRepository;
import udesc.dsw55.trabalho_final.jpa.ProdutoPedidoRepository;
import udesc.dsw55.trabalho_final.jpa.ProdutoRepository;
import udesc.dsw55.trabalho_final.model.ModelCarrinho;
import udesc.dsw55.trabalho_final.model.ModelPedido;
import udesc.dsw55.trabalho_final.model.ModelProduto;
import udesc.dsw55.trabalho_final.model.ModelProdutoPedido;
import udesc.dsw55.trabalho_final.model.ModelProdutoPedidoReview;

@RestController
public class PedidoRest {

	private PedidoRepository repository;
	private CarrinhoRepository carrinhoRepository;
	private ProdutoPedidoRepository prodPedidoRepository;
	private ProdutoRepository prodRepository;
	
	public PedidoRest(PedidoRepository repository, CarrinhoRepository carrinhoRepository,
			ProdutoPedidoRepository prodPedidoRepository, ProdutoRepository prodRepository) {
		super();
		this.repository = repository;
		this.carrinhoRepository = carrinhoRepository;
		this.prodPedidoRepository = prodPedidoRepository;
		this.prodRepository = prodRepository;
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
	public ModelPedido createPedido(@Valid @RequestBody ModelPedido pedido){
		
		ModelPedido savedPedido = repository.save(pedido);
		List<ModelCarrinho> carrinho = carrinhoRepository.findByUsuarioOrderByIdAsc(savedPedido.getUsuario());
		
		for (ModelCarrinho modelCarrinho : carrinho) {
			Optional<ModelProduto> prod = prodRepository.findById(modelCarrinho.getProduto());
			ModelProduto product = prod.get();
			//Salva o Produto relacionado ao pedido
			ModelProdutoPedido prodPedido = new ModelProdutoPedido();
			prodPedido.setPedido(savedPedido.getId());
			prodPedido.setProduto(product.getId());
			prodPedido.setQuantidade(modelCarrinho.getQuantidade());
			prodPedido.setPreco(product.getPreco());
			prodPedidoRepository.save(prodPedido);
			//Diminui a quantidade do pedido do produto
			product.setQuantidade(product.getQuantidade() - prodPedido.getQuantidade());
			prodRepository.save(product);
			//Remove o produto do carrinho
			carrinhoRepository.delete(modelCarrinho);
		}
				
		return savedPedido;		
	}
	
	@GetMapping("/pedidos/{id}/product")
	public List<ModelProdutoPedidoReview> getListProdutoPedido(@PathVariable int id) throws Exception{
		List<ModelProdutoPedidoReview> res = new ArrayList<>();
				
		List<ModelProdutoPedido> lista = prodPedidoRepository.findByPedido(id);
				
		if(!lista.isEmpty()) {
			for (ModelProdutoPedido modelProdutoPedido : lista) {
				Optional<ModelProduto> produto = prodRepository.findById(modelProdutoPedido.getProduto());
				
				ModelProdutoPedidoReview prod = new ModelProdutoPedidoReview();
				prod.setId(modelProdutoPedido.getId());
				prod.setPreco(modelProdutoPedido.getPreco());
				prod.setQuantidade(modelProdutoPedido.getQuantidade());
				prod.setProduto(produto.get());
				prod.setTotal(modelProdutoPedido.getPreco() * modelProdutoPedido.getQuantidade());
				res.add(prod);
			}
		}
		return res;
	}
	
	@GetMapping("/pedidos")
	public List<ModelPedido> getListPedido(){
		return repository.findAll();
	} 
	
	@GetMapping("/pedidouser/{id}")
	public List<ModelPedido> getPedidoUsuarioById(@PathVariable int id) throws Exception{
		List<ModelPedido> pedido = PedidoDao.getPedidoByUser(repository, id);
		if(pedido.isEmpty()) {
			throw new Exception("Usuário não possui pedido");
		} else {
			for (ModelPedido modelPedido : pedido) {
				modelPedido.setSituacao("Entregue");
			}
		}
		return pedido;
	} 
	
	
}
