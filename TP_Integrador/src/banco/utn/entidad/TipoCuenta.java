package banco.utn.entidad;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="TipoCuenta")
public class TipoCuenta implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int NumCuenta;
	private String Nombre;
	private boolean Estado;	
	public TipoCuenta()
	{}

	
	public int getNumCuenta() {
		return NumCuenta;
	}


	public void setNumCuenta(int numCuenta) {
		NumCuenta = numCuenta;
	}


	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public boolean isEstado() {
		return Estado;
	}
	public void setEstado(boolean estado) {
		Estado = estado;
	}


	@Override
	public String toString() {
		return "TipoCuenta [NumCuenta=" + NumCuenta + ", Nombre=" + Nombre + ", Estado=" + Estado + "]";
	}

	
	
	
}
