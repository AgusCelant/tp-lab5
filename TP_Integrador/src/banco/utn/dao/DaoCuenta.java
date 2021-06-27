package banco.utn.dao;

import banco.utn.entidad.Cuenta;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.metamodel.source.annotations.UnknownInheritanceTypeException;
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
	
	public void asociarCuenta (String dni, String nroCuenta, String fechaCreacion, String tipoCuenta, String cbu, String saldo) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		session.beginTransaction();
		
		Cuenta cuenta = (Cuenta)session.get(Cuenta.class, Integer.parseInt(nroCuenta));
		System.out.println(cuenta.toString());
		cuenta.setDni(dni);
		cuenta.setFecha(fechaCreacion);
		cuenta.setTipoCuenta(tipoCuenta);
		cuenta.setCbu(Integer.parseInt(cbu));
		cuenta.setSaldo(Float.parseFloat(saldo));
		
		session.update(cuenta);
		session.getTransaction().commit();
		DAO.cerrarSession();
	}
}
