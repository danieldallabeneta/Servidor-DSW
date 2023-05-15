package udesc.dsw55.trabalho_final.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity(name="tbpedido")
public class ModelPedido {

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	private Integer usuario;
	
	@NotNull
	private String dataPedido;
	
	private String situacao;
	
	public ModelPedido(Integer id, Integer usuario) {
		super();
		this.id = id;
		this.usuario = usuario;
	}
	
	public ModelPedido() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "ModelPedido [id=" + id + ", usuario=" + usuario + ", dataPedido=" + dataPedido + ", situacao="
				+ situacao + "]";
	}
	
	
}
