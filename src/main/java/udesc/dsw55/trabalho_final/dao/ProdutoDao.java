package udesc.dsw55.trabalho_final.dao;

import java.util.ArrayList;
import java.util.List;

import udesc.dsw55.trabalho_final.jpa.ProdutoRepository;
import udesc.dsw55.trabalho_final.model.ModelProduto;

public class ProdutoDao {

	public static List<ModelProduto> getSucessos(ProdutoRepository repo) {
		List<ModelProduto> sucess = new ArrayList<>();
		List<ModelProduto> res = repo.findAll();
		int contagem = 0;
		for (int i = 0; i < res.size(); i++) {
			if(contagem < 5 && res.get(i).getQuantidade() > 0) {
				sucess.add(res.get(i));
			}
			contagem++;
		}
				
		return sucess;	
	}
	
	
}
