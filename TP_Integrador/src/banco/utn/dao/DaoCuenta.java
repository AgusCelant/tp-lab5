package banco.utn.dao;

import banco.utn.entidad.Cuenta;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("daoCuenta")
public class DaoCuenta implements InterfazDaoCuenta {
	@Autowired
	private Conexion conexion;
	
	public List<Cuenta> obtenerCuentasDeUsuario (String dni) {
		Session session = conexion.abrirConexion();
		String query = "SELECT cu FROM Cuenta cu WHERE cu.Estado=true and cu.Cliente.Dni = '" + dni + "'";
		List<Cuenta> cuentasDeUsuario = (List<Cuenta>) session.createQuery(query).list();
		conexion.cerrarSession();

		return cuentasDeUsuario;
	}
	
	public void asociarCuenta (String dni, String nroCuenta, String fechaCreacion, String tipoCuenta, String cbu, String saldo) {
		Session session = conexion.abrirConexion();
		session.beginTransaction();
		
		Cuenta cuenta = (Cuenta)session.get(Cuenta.class, Integer.parseInt(nroCuenta));
		cuenta.getCliente().setDni(dni);
		cuenta.setFecha(fechaCreacion);
		cuenta.getTipoCuenta().setNombre(tipoCuenta);
		cuenta.setCbu(Integer.parseInt(cbu));
		cuenta.setSaldo(Float.parseFloat(saldo));
		
		session.update(cuenta);
		session.getTransaction().commit();
		conexion.cerrarSession();
	}
	
	public boolean AgregarCuenta(Cuenta c) {
		Session session = conexion.abrirConexion();
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
		conexion.cerrarSession();	
		return aux;
	}
	
	public boolean Editarcuenta(Cuenta c) {
		Session session = conexion.abrirConexion();
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
		conexion.cerrarSession();
		return aux;
	}

	public List<Cuenta> listarCuentas() {
		Session session = conexion.abrirConexion();	
		session.beginTransaction();
		List<Cuenta> listarCuentas=(List<Cuenta>) session.createQuery("From Cuenta where Estado=true").list();
		conexion.cerrarSession();				
		return listarCuentas;
	}
	
	public Cuenta obtenerCuentaPorNroCuenta (int nroCuenta) {
		Session session = conexion.abrirConexion();	
		session.beginTransaction();
		Cuenta cuenta = (Cuenta) session.createQuery("FROM Cuenta WHERE Estado=true AND NumCuenta=" + nroCuenta).uniqueResult();
		conexion.cerrarSession();
		return cuenta;
	}
	
	public Cuenta obtenerCuentaPorCbu (int cbu) {
		Session session = conexion.abrirConexion();	
		session.beginTransaction();
		Cuenta cuenta = (Cuenta) session.createQuery("FROM Cuenta WHERE Estado=true AND Cbu=" + cbu).uniqueResult();
	
		conexion.cerrarSession();
		return cuenta;
	}
	
	public void actualizarCuentas(Cuenta cuentaOrigen, Cuenta cuentaDestino, float monto) {
		Session session = conexion.abrirConexion();
		cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
		cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);	
		
		session.beginTransaction();
		session.update(cuentaOrigen);
		session.update(cuentaDestino);

		session.getTransaction().commit();	
	
		conexion.cerrarSession();
	}

	public List<Object[]> Verificarcbu(int Cbu) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select c.Cbu From Cuenta as c  where c.Cbu="+Cbu+" and c.Estado=true ";		
		List<Object[]> result=(List<Object[]>)session.createQuery(hql).list();		
		conexion.cerrarSession();
		return result;
	}

	public List<Object[]> CuentaVerificar(int nroCuenta,String dni) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select c.NumCuenta From Cuenta as c  where c.NumCuenta="+nroCuenta+" and c.Dni="+dni+"and c.Estado=true ";		
		List<Object[]> result=(List<Object[]>)session.createQuery(hql).list();		
		conexion.cerrarSession();
		return result;
			
	}

	public List<Object[]> CuentaVerificarEstado0(int nroCuenta,String dni) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select c.NumCuenta From Cuenta as c  where c.NumCuenta="+nroCuenta+" and c.Dni="+dni+"and c.Estado=false ";		
		List<Object[]> result=(List<Object[]>)session.createQuery(hql).list();		
		conexion.cerrarSession();
		return result;
			
	}

	public List<Integer> ObtenerPorcentajedeCuentasPesos() {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select sum(c.Saldo) as cuenta From Cuenta as c  where c.Estado=true and c.TipoCuenta.Nombre='Pesos'";	
		List<Integer> result=(List<Integer>)session.createQuery(hql).list();		
		conexion.cerrarSession();
		return result;
	}
	
	public List<Integer> ObtenerPorcentajedeCuentasDolar() {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select sum(c.Saldo) as cuenta From Cuenta as c  where c.Estado=true and c.TipoCuenta.Nombre='Dolares'";	
		List<Integer> result=(List<Integer>)session.createQuery(hql).list();
		
		conexion.cerrarSession();
		return result;
	}
		
	public Cuenta BuscarCuentaDni(String Dni, int numCuenta) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql=" From Cuenta as c where c.NumCuenta=:numcuenta and c.Cliente.Dni=:dnii and  c.Estado=true ";	
		Query query=session.createQuery(hql);
		query.setParameter("numcuenta", numCuenta);
		query.setParameter("dnii", Dni);
		Cuenta cuenta = new Cuenta();
		cuenta=(Cuenta)query.uniqueResult();	
		tx.commit();
		conexion.cerrarSession();

		return cuenta;
	}
		
	public ArrayList<Cuenta> BuscarTODASCuenta(String Dni) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql=" From Cuenta as c  where c.Cliente.Dni=:dnii and c.Estado=true ";	
		Query query=session.createQuery(hql);
		query.setParameter("dnii", Dni);
	
		List<Cuenta> result= (List<Cuenta>) query.list();
		ArrayList<Cuenta> Listacuentas= new ArrayList<Cuenta>();

		for(Cuenta obj : result) {				
			Listacuentas.add(obj);
		}
		tx.commit();
		conexion.cerrarSession();
		return Listacuentas;
	}
		
	public boolean Eliminar1Cuenta(Cuenta cuenta) {	
		Session session = conexion.abrirConexion();
		session.beginTransaction();
		session.update(cuenta);
		session.getTransaction().commit();					
		conexion.cerrarSession();
		return true;
	}
}