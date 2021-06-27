package banco.utn.entidad;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Cuentas")
public class Cuenta implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int NumCuenta;
	@Column(name = "fecha")
	private String Fecha;
	@Column(name = "dni", nullable = true)
	private String Dni;
	@Column(name = "tipo")
	private String TipoCuenta;
	@Column(name = "cbu")
	private int Cbu;
	@Column(name = "saldo")
	private float Saldo;
	@Column(name = "estado")
	private boolean Estado;
	
	public Cuenta(String fecha, String tipoCuenta, int cbu, float saldo, boolean estado) {
	// public Cuenta(String fecha, String tipoCuenta, int cbu, float saldo) {
		super();
		Fecha = fecha;
		TipoCuenta = tipoCuenta;
		Cbu = cbu;
		Saldo = saldo;
		Estado = estado;
	}

	public Cuenta() {}

	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public String getTipoCuenta() {
		return TipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		TipoCuenta = tipoCuenta;
	}
	public int getNumCuenta() {
		return NumCuenta;
	}
	public void setNumCuenta(int numCuenta) {
		NumCuenta = numCuenta;
	}
	public int getCbu() {
		return Cbu;
	}
	public void setCbu(int cbu) {
		Cbu = cbu;
	}
	public float getSaldo() {
		return Saldo;
	}
	public void setSaldo(float saldo) {
		Saldo = saldo;
	}
	public Boolean getEstado() {
		return Estado;
	}
	public void setEstado(Boolean estado) {
		Estado =  estado;
	}
	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}
	@Override
	public String toString() {
		return "Dni=" + Dni +", Fecha=" + Fecha + ", TipoCuenta=" + TipoCuenta + ", NumCuenta=" + NumCuenta + ", Cbu=" + Cbu + ", Saldo=" + Saldo + ", Estado=" + Estado;
		// return "Fecha=" + Fecha + ", TipoCuenta=" + TipoCuenta + ", NumCuenta=" + NumCuenta + ", Cbu=" + Cbu + ", Saldo=" + Saldo;
	}
}
