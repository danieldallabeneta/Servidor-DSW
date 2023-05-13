package udesc.dsw55.trabalho_final.dao;

import java.util.List;

import org.springframework.data.domain.Example;

import udesc.dsw55.trabalho_final.jpa.ProdutoPedidoRepository;
import udesc.dsw55.trabalho_final.model.ModelProdutoPedido;

public class ProdutoPedidoDao {

	public static List<ModelProdutoPedido> getProdutosByPedido(ProdutoPedidoRepository repo, Integer pedido) {
		ModelProdutoPedido prodPed = new ModelProdutoPedido();
		prodPed.setPedido(pedido);
		Example<ModelProdutoPedido> prodPedExample = Example.of(prodPed);
		List<ModelProdutoPedido> res = repo.findAll(prodPedExample);
		return res;	
	}
	
}
