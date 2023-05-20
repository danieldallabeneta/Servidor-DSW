package udesc.dsw55.trabalho_final.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import udesc.dsw55.trabalho_final.model.ModelEntregaPedido;

public interface EntregaPedidoRepository extends JpaRepository<ModelEntregaPedido, Integer>{
	
	List<ModelEntregaPedido> findAllByEntrega(Integer entrega);	
	
	Optional<ModelEntregaPedido> findTopByPedidoOrderByIdDesc(Integer pedido);
	
}
