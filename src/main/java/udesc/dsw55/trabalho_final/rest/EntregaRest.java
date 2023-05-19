package udesc.dsw55.trabalho_final.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import udesc.dsw55.trabalho_final.jpa.EntregaRepository;
import udesc.dsw55.trabalho_final.model.ModelEntrega;
import udesc.dsw55.trabalho_final.model.ModelEntregaPreview;

@RestController
public class EntregaRest {

	private EntregaRepository repository;

	public EntregaRest(EntregaRepository repository) {
		super();
		this.repository = repository;
	}
	
	@GetMapping("/entregas")
	public List<ModelEntregaPreview> getListaEntrega() throws Exception{
		List<ModelEntrega> entregas = repository.findAll();
		List<ModelEntregaPreview> res = new ArrayList<>();
		for (ModelEntrega modelEntrega : entregas) {
			LocalDateTime saida = modelEntrega.getDataSaida();
			LocalDateTime retorno = modelEntrega.getDataRetorno();
			
			ModelEntregaPreview model = new ModelEntregaPreview();
			model.setId(modelEntrega.getId());
			model.setDataSaida(saida.getDayOfMonth()+"/"+saida.getMonth()+"/"+saida.getYear());
			model.setHoraSaida(saida.getHour()+":"+saida.getMinute()+":"+saida.getSecond());
			model.setDataRetorno(retorno.getDayOfMonth()+"/"+retorno.getMonth()+"/"+retorno.getYear());
			model.setHoraRetorno(retorno.getHour()+":"+retorno.getMinute()+":"+retorno.getSecond());
			res.add(model);
		}
		return res;
	}
	
	
}
