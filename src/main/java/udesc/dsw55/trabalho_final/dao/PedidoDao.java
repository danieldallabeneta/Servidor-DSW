package udesc.dsw55.trabalho_final.dao;

import java.util.List;

import org.springframework.data.domain.Example;

import udesc.dsw55.trabalho_final.jpa.PedidoRepository;
import udesc.dsw55.trabalho_final.model.ModelPedido;

public class PedidoDao {

	public static List<ModelPedido> getPedidoByUser(PedidoRepository repo, Integer user) {
		ModelPedido carrinho = new ModelPedido();
		carrinho.setUsuario(user);
		Example<ModelPedido> carrinhoExample = Example.of(carrinho);
		List<ModelPedido> res = repo.findAll(carrinhoExample);
		return res;	
	}
	
}
