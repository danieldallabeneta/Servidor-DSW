package udesc.dsw55.trabalho_final.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import udesc.dsw55.trabalho_final.model.ModelCarrinho;

public interface CarrinhoRepository extends JpaRepository<ModelCarrinho, Integer> {

	List<ModelCarrinho> findByUsuarioOrderByIdAsc(Integer usuario);
	
	void deleteAllByUsuario(Integer usuario);
	
}		
