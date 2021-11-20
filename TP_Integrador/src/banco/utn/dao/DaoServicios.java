package banco.utn.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import banco.utn.entidad.Servicios;

@Repository("daoServicios")
public class DaoServicios implements InterfazDaoServicios{
	@Autowired
	private Conexion conexion;
	
	public List<Servicios> ListarServicios () {
		Session session = conexion.abrirConexion();
		String query = "select idservicios, tipoServicio from servicios";
		List<Servicios> listaServicios = (List<Servicios>) session.createQuery(query).list();
		conexion.cerrarSession();

		return listaServicios;
	}
}
