package banco.utn.dao;

import banco.utn.entidad.Cliente;
import banco.utn.entidad.Cuenta;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.metamodel.source.annotations.UnknownInheritanceTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("daoCuenta")
public class DaoCuenta implements InterfazDaoCuenta {
	
	public List<Cuenta> obtenerCuentasDeUsuario (String dni) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		// String query = "SELECT cu FROM Cuenta cu WHERE cu.id IN (SELECT cxc.IdCuenta FROM ClientesxCuentas cxc WHERE cxc.Dni = '" + dni + "')";
		String query = "SELECT cu FROM Cuenta cu WHERE cu.Estado=true and cu.Cliente.Dni = '" + dni + "'";
		List<Cuenta> cuentasDeUsuario = (List<Cuenta>) session.createQuery(query).list();
		System.out.println("djsajdsjadssadddd");
		DAO.cerrarSession();

		return cuentasDeUsuario;
	}
	
	public void asociarCuenta (String dni, String nroCuenta, String fechaCreacion, String tipoCuenta, String cbu, String saldo) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		session.beginTransaction();
		
		Cuenta cuenta = (Cuenta)session.get(Cuenta.class, Integer.parseInt(nroCuenta));
		System.out.println(cuenta.toString());
		cuenta.getCliente().setDni(dni);
		cuenta.setFecha(fechaCreacion);
		cuenta.getTipoCuenta().setNombre(tipoCuenta);
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
	
	
	
	//anda
	public List<Object[]> Verificarcbu(int Cbu) {
		Conexion DAO = new Conexion();	
		Session session = DAO.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select c.Cbu From Cuenta as c  where c.Cbu="+Cbu+" and c.Estado=true ";		
		List<Object[]> result=(List<Object[]>)session.createQuery(hql).list();		
		DAO.cerrarSession();
		return result;
			
	}
	//anda
	public List<Object[]> CuentaVerificar(int nroCuenta,String dni) {
		Conexion DAO = new Conexion();	
		Session session = DAO.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select c.NumCuenta From Cuenta as c  where c.NumCuenta="+nroCuenta+" and c.Dni="+dni+"and c.Estado=true ";		
		List<Object[]> result=(List<Object[]>)session.createQuery(hql).list();		
		DAO.cerrarSession();
		return result;
			
	}
	//anda
	public List<Object[]> CuentaVerificarEstado0(int nroCuenta,String dni) {
		Conexion DAO = new Conexion();	
		Session session = DAO.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select c.NumCuenta From Cuenta as c  where c.NumCuenta="+nroCuenta+" and c.Dni="+dni+"and c.Estado=false ";		
		List<Object[]> result=(List<Object[]>)session.createQuery(hql).list();		
		DAO.cerrarSession();
		return result;
			
	}
//anda
	public List<Integer> ObtenerPorcentajedeCuentasPesos() {
		Conexion DAO = new Conexion();	
		Session session = DAO.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select sum(c.Saldo) as cuenta From Cuenta as c  where c.Estado=true and c.TipoCuenta.Nombre='Pesos'";	
		List<Integer> result=(List<Integer>)session.createQuery(hql).list();		
		DAO.cerrarSession();
		return result;
			
	}
	//anda
	public List<Integer> ObtenerPorcentajedeCuentasDolar() {
		Conexion DAO = new Conexion();	
		Session session = DAO.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select sum(c.Saldo) as cuenta From Cuenta as c  where c.Estado=true and c.TipoCuenta.Nombre='Dolares'";	
		List<Integer> result=(List<Integer>)session.createQuery(hql).list();
	
		
		DAO.cerrarSession();
		return result;
			
	}
	
	
		//anda
		public Cuenta BuscarCuentaDni(String Dni, int numCuenta) {
			Conexion DAO = new Conexion();
			Session session = DAO.abrirConexion();
			Transaction tx= session.beginTransaction();						
			String hql=" From Cuenta as c where c.NumCuenta=:numcuenta and c.Cliente.Dni=:dnii and  c.Estado=true ";	
			Query query=session.createQuery(hql);
			query.setParameter("numcuenta", numCuenta);
			query.setParameter("dnii", Dni);
			Cuenta cuenta = new Cuenta();
			cuenta=(Cuenta)query.uniqueResult();	
			tx.commit();
			DAO.cerrarSession();

			return cuenta;
				
		}
		

		//anda
		public ArrayList<Cuenta> BuscarTODASCuenta(String Dni) {
			Conexion DAO = new Conexion();
			Session session = DAO.abrirConexion();
			Transaction tx= session.beginTransaction();						
		//	String hql="Select c.NumCuenta,c.Dni,c.Fecha,c.TipoCuenta,c.Cbu,c.Saldo,c.Estado From Cuenta as c  where c.Dni="+Dni+"and  c.Estado=true ";
			//Query query=session.createQuery(hql);
			//List<Object[]> result= (List<Object[]>) session.createQuery(hql).list();
			
			String hql=" From Cuenta as c  where c.Cliente.Dni=:dnii and c.Estado=true ";	
			Query query=session.createQuery(hql);
			query.setParameter("dnii", Dni);
		
			List<Cuenta> result= (List<Cuenta>) query.list();
			

			ArrayList<Cuenta> Listacuentas= new ArrayList<Cuenta>();
			
	
		for(Cuenta obj : result) {				
				Listacuentas.add(obj);
			}
			tx.commit();
			DAO.cerrarSession();
			return Listacuentas;
				
		}
		//anda
		public boolean Eliminar1Cuenta(Cuenta cuenta) {	
			Conexion DAO = new Conexion();
			Session session = DAO.abrirConexion();
			session.beginTransaction();
			session.update(cuenta);
			session.getTransaction().commit();					
				DAO.cerrarSession();
				return true;
		}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
