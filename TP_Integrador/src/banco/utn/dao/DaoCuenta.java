package banco.utn.dao;

import banco.utn.entidad.Cuenta;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("daoCuenta")
public class DaoCuenta {
	
	public List<Cuenta> obtenerCuentasDeUsuario (String dni) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		// String query = "SELECT cu FROM Cuenta cu WHERE cu.id IN (SELECT cxc.IdCuenta FROM ClientesxCuentas cxc WHERE cxc.Dni = '" + dni + "')";
		String query = "SELECT cu FROM Cuenta cu WHERE cu.Dni = '" + dni + "'";
		List<Cuenta> cuentasDeUsuario = (List<Cuenta>) session.createQuery(query).list();
		DAO.cerrarSession();

		return cuentasDeUsuario;
	}
}
