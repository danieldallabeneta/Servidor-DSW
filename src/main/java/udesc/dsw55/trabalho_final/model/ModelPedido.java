package udesc.dsw55.trabalho_final.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name="tbpedido")
public class ModelPedido {

	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer usuario;	
	
	/* NECESSÁRIO INSERIR UM CAMPO DE DATA */
	
	/* NECESSÁRIO APLICAR UMA SITUAÇÃO DE ENTREGA DO PEDIDO */

	public ModelPedido(Integer id, Integer usuario) {
		super();
		this.id = id;
		this.usuario = usuario;
	}

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

	@Override
	public String toString() {
		return "ModelPedido [id=" + id + ", usuario=" + usuario + "]";
	}
	
	
}
