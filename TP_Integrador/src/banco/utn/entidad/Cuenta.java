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
	@ManyToOne(cascade= {CascadeType.ALL}) 
	@JoinColumn(name="Dni")
	private Cliente Cliente= new Cliente();
	@OneToOne(cascade= {CascadeType.ALL}) 
	@JoinColumn(name="TipoCuenta")
	private TipoCuenta TipoCuenta= new TipoCuenta();
	@Column(name = "cbu")
	private int Cbu;
	@Column(name = "saldo")
	private float Saldo;
	@Column(name = "estado")
	private boolean Estado;
	
	public Cuenta(String fecha, TipoCuenta tipoCuenta, int cbu, float saldo, boolean estado) {
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
	public TipoCuenta getTipoCuenta() {
		return TipoCuenta;
	}
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
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

	public Cliente getCliente() {
		return Cliente;
	}

	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}

	@Override
	public String toString() {
		return "Dni=" + Cliente.getDni() +", Fecha=" + Fecha + ", TipoCuenta=" + TipoCuenta + ", NumCuenta=" + NumCuenta + ", Cbu=" + Cbu + ", Saldo=" + Saldo + ", Estado=" + Estado;
		// return "Fecha=" + Fecha + ", TipoCuenta=" + TipoCuenta + ", NumCuenta=" + NumCuenta + ", Cbu=" + Cbu + ", Saldo=" + Saldo;
	}
}
