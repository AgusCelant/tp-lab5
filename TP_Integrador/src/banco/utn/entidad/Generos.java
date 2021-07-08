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
@Table(name="Genero")
public class Generos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String dni;
	private String genero;
	
	


	public Generos()
	{}
	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}



	@Override
	public String toString() {
		return  genero+dni ;
	}
	
	
	
	
}
