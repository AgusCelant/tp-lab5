package banco.utn.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="pagoservicios")
public class Pagos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="idpago")
	private int idPago;
	@Column(name="idServicio")
	private String idServicio;
	@Column(name="servicio")
	private String Servicio;
	@Column(name="idCliente")
	private String idCliente;
	@Column(name="monto")
	private String monto;

	public Pagos() {}

	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String id) {
		this.idServicio = id;
	}
	
	public String getServicio() {
		return Servicio;
	}

	public void setServicio(String servicio) {
		Servicio = servicio;
	}

	public String getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(String dnii) {
		this.idCliente = dnii;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String txtMonto) {
		this.monto = txtMonto;
	}



	
}
