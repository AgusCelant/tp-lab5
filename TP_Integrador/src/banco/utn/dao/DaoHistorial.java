package banco.utn.dao;

import java.time.LocalDate;
import java.util.List;
import banco.utn.entidad.Historial;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("daoHisotrial")
public class DaoHistorial implements InterfazDaoHistorial{
	@Autowired
	private Conexion conexion;
	
	public List<Historial> obtenerHisotrialPorCuenta (int idCuenta) {
		Session session = conexion.abrirConexion();
		String query = "SELECT h FROM Historial h WHERE NroCuentaOrigen=" + idCuenta + " OR NroCuentaDestino=" + idCuenta;
		List<Historial> historialCuenta = (List<Historial>) session.createQuery(query).list();
		conexion.cerrarSession();
		
		return historialCuenta;
	}
	
	public void crearRegistroEnHisotrial (int nroCuentaOrigen, int nroCuentaDestino, float monto, String tipoCuenta) {
		Session session = conexion.abrirConexion();
		String fecha = java.time.LocalDate.now().toString();
		Historial registro = new Historial(nroCuentaOrigen, nroCuentaDestino, tipoCuenta, fecha, monto);
		
		session.beginTransaction();
		session.save(registro);
		session.getTransaction().commit();
		conexion.cerrarSession();
	}
}
