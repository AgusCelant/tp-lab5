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
@Table(name="Historial")
public class Historial implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int NroTransaccion;
	//@Column(name="DNI")
	//private String Dni;
	//@Column(name="dniDestino")
	//private String DniDestino;
	@Column(name="nroCuentaOrigen")
	private int NroCuentaOrigen;
	@Column(name="nroCuentadestino")
	private int NroCuentadestino;
	/*@Column(name="cbu")
	private int Cbu;*/
	@Column(name="tipodeCuenta")
	private String TipodeCuenta;
	@Column(name="fecha")
	private String Fecha;
	@Column(name="monto")
	private float Monto;
	//@Column(name="movimiento")
	//private String Movimiento;
	
	public Historial() {}
	
	public Historial(/*String dni, String dniDestino, */int nroCuentaOrigen, int nroCuentadestino, /*int cbu,*/
			String tipodeCuenta, String fecha, float monto/*, String movimiento*/) {
		super();
		//Dni = dni;
		//DniDestino = dniDestino;
		NroCuentaOrigen = nroCuentaOrigen;
		NroCuentadestino = nroCuentadestino;
		// Cbu = cbu;
		TipodeCuenta = tipodeCuenta;
		Fecha = fecha;
		Monto = monto;
		//Movimiento = movimiento;
	}

	/*public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public String getDniDestino() {
		return DniDestino;
	}

	public void setDniDestino(String dniDestino) {
		DniDestino = dniDestino;
	}*/

	public int getNroCuentaOrigen() {
		return NroCuentaOrigen;
	}

	public void setNroCuentaOrigen(int nroCuentaOrigen) {
		NroCuentaOrigen = nroCuentaOrigen;
	}

	public int getNroCuentadestino() {
		return NroCuentadestino;
	}

	public void setNroCuentadestino(int nroCuentadestino) {
		NroCuentadestino = nroCuentadestino;
	}

	/*public int getCbu() {
		return Cbu;
	}

	public void setCbu(int cbu) {
		Cbu = cbu;
	}*/

	public String getTipodeCuenta() {
		return TipodeCuenta;
	}

	public void setTipodeCuenta(String tipodeCuenta) {
		TipodeCuenta = tipodeCuenta;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public float getMonto() {
		return Monto;
	}

	public void setMonto(float monto) {
		Monto = monto;
	}

	/*public String getMovimiento() {
		return Movimiento;
	}

	public void setMovimiento(String movimiento) {
		Movimiento = movimiento;
	}*/

	@Override
	public String toString() {
		return "Historial [NroCuentaOrigen=" + NroCuentaOrigen + ", NroCuentadestino=" + NroCuentadestino + ", TipodeCuenta=" + TipodeCuenta
			+ ", Fecha=" + Fecha + ", Monto=" + Monto + /*", Movimiento=" + Movimiento +*/ "]";
	}	
}
