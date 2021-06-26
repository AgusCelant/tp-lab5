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
	
	
	@Column(name="IdCuenta")
	private int IdCuenta;
	
	
	public ClientesxCuentas()
	{
		
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


	@Override
	public String toString() {
		return "ClientesxCuentas [Dni=" + Dni + ", IdCuenta=" + IdCuenta + "]";
	}

	
	

	
	
	
}
