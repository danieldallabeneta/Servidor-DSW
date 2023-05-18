package udesc.dsw55.trabalho_final.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

import udesc.dsw55.trabalho_final.model.ModelProduto;

public interface ProdutoRepository extends JpaRepository<ModelProduto, Integer> {
	
}
