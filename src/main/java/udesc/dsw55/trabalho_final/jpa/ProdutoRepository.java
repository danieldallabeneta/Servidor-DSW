package udesc.dsw55.trabalho_final.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import udesc.dsw55.trabalho_final.model.ModelProduto;

public interface ProdutoRepository extends JpaRepository<ModelProduto, Integer> {

	//@Query("select * from tbproduto where exists (Select produto,count(produto) from tbprodutopedido join tbproduto on tbproduto.id = tbprodutopedido.produto group by produto order by 2 limit 5)")
	//List<ModelProduto> top5Produto();
	
	
}
