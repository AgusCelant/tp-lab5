package banco.utn.dao;

import java.time.LocalDate;
import java.util.List;
import banco.utn.entidad.Historial;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("daoHisotrial")
public class DaoHistorial implements InterfazDaoHistorial{
	public List<Historial> obtenerHisotrialPorCuenta (int idCuenta) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		String query = "SELECT h FROM Historial h WHERE NroCuentaOrigen=" + idCuenta + " OR NroCuentaDestino=" + idCuenta;
		List<Historial> historialCuenta = (List<Historial>) session.createQuery(query).list();
		DAO.cerrarSession();
		
		return historialCuenta;
	}
	
	public void crearRegistroEnHisotrial (int nroCuentaOrigen, int nroCuentaDestino, float monto, String tipoCuenta) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		
		String fecha = java.time.LocalDate.now().toString();
		Historial registro = new Historial(nroCuentaOrigen, nroCuentaDestino, tipoCuenta, fecha, monto);
		
		session.beginTransaction();
		session.save(registro);
		session.getTransaction().commit();
		DAO.cerrarSession();
	}
}
