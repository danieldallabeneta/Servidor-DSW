package udesc.dsw55.trabalho_final.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import udesc.dsw55.trabalho_final.model.ModelEndereco;

public interface EnderecoRepository extends JpaRepository<ModelEndereco, Integer>{

	Optional<ModelEndereco> findByUsuarioAndTipo(Integer usuario, Integer tipo);
}
