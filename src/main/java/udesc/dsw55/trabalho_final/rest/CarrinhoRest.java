package udesc.dsw55.trabalho_final.rest;

import java.net.URI;
import java.util.Optional;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import udesc.dsw55.trabalho_final.jpa.CarrinhoRepository;
import udesc.dsw55.trabalho_final.model.ModelCarrinho;

@RestController
public class CarrinhoRest {

	private CarrinhoRepository repository;

	public CarrinhoRest(CarrinhoRepository repository) {
		super();
		this.repository = repository;	
	}

	@PostMapping("/users/{id}/carrinho")
	public ResponseEntity<ModelCarrinho> createUser(@PathVariable int id, @Valid @RequestBody String carrinho) throws JSONException{
		JSONObject jsonObject= new JSONObject(carrinho);
		ModelCarrinho newCarrinho = new ModelCarrinho();		
		newCarrinho.setUsuario(id);		
		newCarrinho.setProduto(jsonObject.getInt("produto"));
		newCarrinho.setQuantidade(jsonObject.getInt("quantidade"));
		ModelCarrinho savedCarrinho = repository.save(newCarrinho);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedCarrinho.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();		
	}	
	
	@GetMapping("/carrinho/{id}")
	public ModelCarrinho getCarrinho(@PathVariable int id) throws Exception{
		Optional<ModelCarrinho> carrinho = repository.findById(id);
		if(carrinho.isEmpty()) {
			throw new Exception("Erro: Id do carrinho não encontrado");
		}
		return carrinho.get();
	}
	
	
	
}
