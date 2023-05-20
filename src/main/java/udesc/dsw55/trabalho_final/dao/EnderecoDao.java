package udesc.dsw55.trabalho_final.dao;

import java.util.List;

import org.springframework.data.domain.Example;

import udesc.dsw55.trabalho_final.jpa.EnderecoRepository;
import udesc.dsw55.trabalho_final.model.ModelEndereco;

public class EnderecoDao {

	/*
	 * Retorna lista de endereço do usuario informado por parâmetro
	 */
	public static List<ModelEndereco> getEnderecoByUser(EnderecoRepository repo, Integer user) {
		ModelEndereco endereco = new ModelEndereco();
		endereco.setUsuario(user);
		Example<ModelEndereco> enderecoExample = Example.of(endereco);
		List<ModelEndereco> res = repo.findAll(enderecoExample);
		return res;	
	}
	
}
