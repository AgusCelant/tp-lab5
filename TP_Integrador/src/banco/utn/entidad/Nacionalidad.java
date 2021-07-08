package banco.utn.entidad;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="Nacionalidad")
public class Nacionalidad implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String Dni;
	private String Nacionalidad;
	private boolean Estado;	
	
	public Nacionalidad()
	{}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public String getNacionalidad() {
		return Nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}

	public boolean isEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Nacionalidad [Dni=" + Dni + ", Nacionalidad=" + Nacionalidad + ", Estado=" + Estado + "]";
	}
	
	
	
}
