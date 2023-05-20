package udesc.dsw55.trabalho_final.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import udesc.dsw55.trabalho_final.model.ModelUsuario;

public interface UsuarioRepository extends JpaRepository<ModelUsuario, Integer>{
	
	Optional<ModelUsuario> findByEmail(String email);
	
}
