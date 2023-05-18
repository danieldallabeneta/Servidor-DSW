package udesc.dsw55.trabalho_final.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import udesc.dsw55.trabalho_final.model.ModelEntrega;

public interface EntregaRepository extends JpaRepository<ModelEntrega, Integer>{

	Optional<ModelEntrega> findTopByOrderByIdDesc();
	
}
