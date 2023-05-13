package udesc.dsw55.trabalho_final.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import udesc.dsw55.trabalho_final.model.ModelPedido;

public interface PedidoRepository extends JpaRepository<ModelPedido, Integer>{

}
