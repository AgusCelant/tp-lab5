package banco.utn.dao;

import banco.utn.entidad.Cliente;
import banco.utn.entidad.ClientesxCuentas;
import banco.utn.entidad.Cuenta;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
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
	//anda
	public boolean AgregarCuenta(Cuenta c) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		Transaction tx= session.beginTransaction();
		boolean aux = true;
		try
		{
			session.save(c); 
			tx = session.getTransaction();
			tx.commit();
		}
		catch (Exception e) {
			aux=false;
			tx.rollback();
		}
		DAO.cerrarSession();
		return aux;
	}
	//anda
	public boolean Editarcuenta(Cuenta c) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		Transaction tx= session.beginTransaction();
		boolean aux = true;
		try
		{
			session.update(c); 
			tx = session.getTransaction();
			tx.commit();
		}
		catch (Exception e) {
			aux=false;
			tx.rollback();
		}
		DAO.cerrarSession();
		return aux;
	}
	//anda
	public boolean agregarClientesxcuentas(ClientesxCuentas c) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		Transaction tx= session.beginTransaction();
		boolean aux = true;
		try
		{
			session.save(c); 
			tx = session.getTransaction();
			tx.commit();
		}
		catch (Exception e) {
			aux=false;
			tx.rollback();
		}
		DAO.cerrarSession();
		return aux;
	}
	//anda
	public int ContadordeCuentasxclientes() {
		Conexion DAO = new Conexion();	
		Session session = DAO.abrirConexion();
		int resultado=0;
		Transaction tx= session.beginTransaction();						
		String hql="Select count(*) From ClientesxCuentas";		
		List<Long> result= session.createQuery(hql).list();
		
		resultado=result.get(0).intValue();

		
		System.out.println(resultado);

		DAO.cerrarSession();
		return resultado;
	}
//anda
	public List<Cuenta> listarCuentas() {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();	
		session.beginTransaction();
		List<Cuenta> listarCuentas=(List<Cuenta>) session.createQuery("From Cuenta where Estado=true").list();
		DAO.cerrarSession();
		return listarCuentas;
	}
	
	public Cuenta obtenerCuentaPorNroCuenta (int nroCuenta) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();	
		session.beginTransaction();
		Cuenta cuenta = (Cuenta) session.createQuery("FROM Cuenta WHERE Estado=true AND NumCuenta=" + nroCuenta).uniqueResult();
		DAO.cerrarSession();
		return cuenta;
	}
	
	public Cuenta obtenerCuentaPorCbu (int cbu) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();	
		session.beginTransaction();
		Cuenta cuenta = (Cuenta) session.createQuery("FROM Cuenta WHERE Estado=true AND Cbu=" + cbu).uniqueResult();
		DAO.cerrarSession();
		return cuenta;
	}
	
	public void actualizarCuentas(Cuenta cuentaOrigen, Cuenta cuentaDestino, float monto) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
		cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
		session.beginTransaction();
		session.update(cuentaOrigen);
		session.update(cuentaDestino);
		session.getTransaction().commit();		
		DAO.cerrarSession();
	}
}
