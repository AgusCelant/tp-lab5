package banco.utn.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="servicios")
public class Servicios implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="idservicios")
	private int IdServicio;
	@Column(name="tipoServicio")
	private String Servicio;
	
	
	public Servicios(){}

	public int getIdServicio() {
		return IdServicio;
	}
	public void setIdServicio(int idServicio) {
		IdServicio = idServicio;
	}
	public String getServicio() {
		return Servicio;
	}
	public void setServicio(String servicio) {
		Servicio = servicio;
	}
	
	
	@Override
	public String toString() {
		return "Servicios [IdServicio=" + IdServicio + ", Servicio=" + Servicio + "]";
	}
	
}
