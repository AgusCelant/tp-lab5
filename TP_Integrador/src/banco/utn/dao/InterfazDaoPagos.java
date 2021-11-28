package banco.utn.dao;

import java.util.List;

import banco.utn.entidad.Pagos;

public interface InterfazDaoPagos {
	
	public boolean agregarPago(Pagos p);
	public List<Pagos> ListarPagos (String dni);
}
