package udesc.dsw55.trabalho_final.dao;

import java.util.List;

import org.springframework.data.domain.Example;

import udesc.dsw55.trabalho_final.jpa.CarrinhoRepository;
import udesc.dsw55.trabalho_final.model.ModelCarrinho;

public class CarrinhoDao {
	
	public static List<ModelCarrinho> getCarrinhoByUser(CarrinhoRepository repo, Integer user) {
		ModelCarrinho carrinho = new ModelCarrinho();
		carrinho.setUsuario(user);
		Example<ModelCarrinho> carrinhoExample = Example.of(carrinho);
		List<ModelCarrinho> res = repo.findAll(carrinhoExample);
		return res;	
	}
	
}
