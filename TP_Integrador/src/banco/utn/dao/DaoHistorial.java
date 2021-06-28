package banco.utn.dao;

import java.util.List;
import banco.utn.entidad.Historial;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("daoHisotrial")
public class DaoHistorial {
	public List<Historial> obtenerHisotrialPorCuenta (int idCuenta) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		String query = "SELECT h FROM Historial h WHERE NroCuentaOrigen=" + idCuenta + " OR NroCuentaDestino=" + idCuenta;
		List<Historial> historialCuenta = (List<Historial>) session.createQuery(query).list();
		DAO.cerrarSession();
		
		return historialCuenta;
	}
}
