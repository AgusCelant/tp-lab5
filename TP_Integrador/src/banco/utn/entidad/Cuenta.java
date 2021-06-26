package banco.utn.entidad;

import java.io.Serializable;
import javax.persistence.*;

import javax.persistence.GenerationType;

public class Cuenta implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int NumCuenta;
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "persona", nullable = true)
	private Cliente Persona;
	@Column(name = "fecha")
	private String Fecha;
	@Column(name = "tipo")
	private String TipoCuenta;
	@Column(name = "cbu")
	private int Cbu;
	@Column(name = "saldo")
	private float Saldo;
	
	public Cuenta(Cliente persona, String fecha, String tipoCuenta, int numCuenta, int cbu, float saldo) {
		super();
		Persona = persona;
		Fecha = fecha;
		TipoCuenta = tipoCuenta;
		NumCuenta = numCuenta;
		Cbu = cbu;
		Saldo = saldo;
	}

	public Cuenta() {}

	public Cliente getPersona() {
		return Persona;
	}
	public void setPersona(Cliente persona) {
		Persona = persona;
	}
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
	@Override
	public String toString() {
		return "Persona=" + Persona + ", Fecha=" + Fecha + ", TipoCuenta=" + TipoCuenta + ", NumCuenta=" + NumCuenta + ", Cbu=" + Cbu + ", Saldo=" + Saldo;
	}
	
	
	
	
}
