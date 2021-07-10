package banco.utn.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import banco.utn.dao.DaoHistorial;
import banco.utn.entidad.Historial;

public class NegHistorial {
	@Autowired
	private DaoHistorial BDaoHistorial;
	
	public List<Historial> obtenerHisotrialPorCuenta (int idCuenta) {
		return BDaoHistorial.obtenerHisotrialPorCuenta(idCuenta);
	}
	
	public void crearRegistroEnHisotrial (int nroCuentaOrigen, int nroCuentaDestino, float monto, String tipoCuenta) {
		BDaoHistorial.crearRegistroEnHisotrial(nroCuentaOrigen, nroCuentaDestino, monto, tipoCuenta);
	}
}
