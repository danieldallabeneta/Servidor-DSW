package udesc.dsw55.trabalho_final.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import udesc.dsw55.trabalho_final.jpa.EntregaRepository;
import udesc.dsw55.trabalho_final.model.ModelEntrega;

public class EntregaRest {

	private EntregaRepository repository;

	public EntregaRest(EntregaRepository repository) {
		super();
		this.repository = repository;
	}
	
	@GetMapping("/entregas")
	public List<ModelEntrega> getListaEntrega() throws Exception{
		System.out.println(repository.findAll());
		return repository.findAll();
	}
	
	
}
