package banco.utn.entidad;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="ClientesxCuentas")
public class ClientesxCuentas implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="DNI")
	private String Dni;
	
	@Id
	@Column(name="IdCuenta")
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


	public ClientesxCuentas(String dni, int idCuenta, boolean estado) {
		super();
		Dni = dni;
		IdCuenta = idCuenta;
		Estado = estado;
	}


	@Override
	public String toString() {
		return "ClientesxCuentas [Dni=" + Dni + ", IdCuenta=" + IdCuenta + ", Estado=" + Estado + "]";
	}


	
	
	

	
	
	
}
