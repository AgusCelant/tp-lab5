package banco.utn.entidad;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="ClientesxCuentas")
public class ClientesxCuentas implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Column(name="DNI")
	private String Dni;
	
	@Id
	@Column(name="IdCuenta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IdCuenta;
	
	@Column(name="Estado")
	private boolean Estado;
	
	public ClientesxCuentas()
	{
		
	}


	public boolean getEstado() {
		return Estado;
	}


	public void setEstado(boolean estado) {
		Estado = estado;
	}


	public String getDni() {
		return Dni;
	}


	public void setDni(String dni) {
		Dni = dni;
	}


	public int getIdCuenta() {
		return IdCuenta;
	}


	public void setIdCuenta(int idCuenta) {
		IdCuenta = idCuenta;
	}


	public ClientesxCuentas(String dni, boolean estado) {
		super();
		Dni = dni;		
		Estado = estado;
	}


	@Override
	public String toString() {
		return "ClientesxCuentas [Dni=" + Dni + ", IdCuenta=" + IdCuenta + ", Estado=" + Estado + "]";
	}


	
	
	

	
	
	
}
