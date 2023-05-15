package udesc.dsw55.trabalho_final.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import udesc.dsw55.trabalho_final.model.ModelProdutoPedido;

public interface ProdutoPedidoRepository extends JpaRepository<ModelProdutoPedido, Integer>{
	
	List<ModelProdutoPedido> findByPedido(Integer pedido);
}
