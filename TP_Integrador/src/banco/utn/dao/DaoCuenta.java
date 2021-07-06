package banco.utn.dao;

import banco.utn.entidad.ClientesxCuentas;
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
		String query = "SELECT cu FROM Cuenta cu WHERE cu.Estado=true and cu.Dni = '" + dni + "'";
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
	public boolean EditarcuentaxClientes(ClientesxCuentas cxc) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		Transaction tx= session.beginTransaction();
		boolean aux = true;
		try
		{
			session.update(cxc); 
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
		System.out.println(c.toString()+" adasdsadsa");
		try
		{
			session.save(c); 
			tx = session.getTransaction();
			tx.commit();
		}
		catch (Exception e) {
			aux=false;
			tx.rollback();
			System.out.println(e.toString());
		}
		
		DAO.cerrarSession();
		System.out.println(aux);
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
		String hql="Select sum(c.Saldo) as cuenta From Cuenta as c  where c.Estado=true and c.TipoCuenta='Pesos'";	
		List<Integer> result=(List<Integer>)session.createQuery(hql).list();

		
		DAO.cerrarSession();
		return result;
			
	}
	//anda
	public List<Integer> ObtenerPorcentajedeCuentasDolar() {
		Conexion DAO = new Conexion();	
		Session session = DAO.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select sum(c.Saldo) as cuenta From Cuenta as c  where c.Estado=true and c.TipoCuenta='Dolares'";	
		List<Integer> result=(List<Integer>)session.createQuery(hql).list();
	
		
		DAO.cerrarSession();
		return result;
			
	}
	
	
		//anda
		public Cuenta BuscarCuentaDni(String Dni, int numCuenta) {
			Conexion DAO = new Conexion();
			Session session = DAO.abrirConexion();
			Transaction tx= session.beginTransaction();						
			String hql="Select c.NumCuenta,c.Dni,c.Fecha,c.TipoCuenta,c.Cbu,c.Saldo,c.Estado From Cuenta as c  where c.Dni="+Dni+" and c.NumCuenta="+numCuenta+"and  c.Estado=true ";		
			Query query=session.createQuery(hql);
			List<Object[]> result= (List<Object[]>) session.createQuery(hql).list();
			Cuenta cuenta = new Cuenta();
			for(Object[] obj : result) {
				
				cuenta.setNumCuenta((int) obj[0]);
				cuenta.setDni((String)obj[1]);
				cuenta.setFecha((String)obj[2]);
				cuenta.setTipoCuenta((String)obj[3]);
				cuenta.setCbu((int)obj[4]);
				cuenta.setSaldo((float)obj[5]);
				cuenta.setEstado((boolean)obj[6]);
				
			}
			tx.commit();
			DAO.cerrarSession();

			return cuenta;
				
		}
		//anda
		public ClientesxCuentas BuscarCuentaxCliente(String Dni,int NumCuenta) {
			Conexion DAO = new Conexion();
			Session session = DAO.abrirConexion();
			Transaction tx= session.beginTransaction();						
			String hql="select c.Dni,c.IdCuenta,c.Estado From ClientesxCuentas as c WHERE c.Dni="+Dni+"and c.IdCuenta="+NumCuenta+" and c.Estado=true ";
			Query query=session.createQuery(hql);
			List<Object[]> result= (List<Object[]>) session.createQuery(hql).list();
			ClientesxCuentas cli = new ClientesxCuentas();
			for(Object[] obj : result) {
				
				cli.setDni((String) obj[0]);
				cli.setIdCuenta((int)obj[1]);
				cli.setEstado((boolean)obj[2]);
			}
			tx.commit();
			DAO.cerrarSession();

			return cli;
				
		}
		//anda
		public ArrayList<ClientesxCuentas> BuscarTODASCuentaxCliente(String Dni) {
			Conexion DAO = new Conexion();
			Session session = DAO.abrirConexion();
			Transaction tx= session.beginTransaction();						
			String hql="select c.Dni,c.IdCuenta,c.Estado From ClientesxCuentas as c WHERE c.Dni="+Dni+" and c.Estado=true ";
			Query query=session.createQuery(hql);
			List<Object[]> result= (List<Object[]>) session.createQuery(hql).list();
			
			ArrayList<ClientesxCuentas> ListaClientesxcuentas= new ArrayList<ClientesxCuentas>();
			for(Object[] obj : result) {
				ClientesxCuentas cli = new ClientesxCuentas();
				cli.setDni((String) obj[0]);
				cli.setIdCuenta((int)obj[1]);
				cli.setEstado((boolean)obj[2]);
				ListaClientesxcuentas.add(cli);
			}
			tx.commit();
			DAO.cerrarSession();

			return ListaClientesxcuentas;
				
		}
		//anda
		public ArrayList<Cuenta> BuscarTODASCuenta(String Dni) {
			Conexion DAO = new Conexion();
			Session session = DAO.abrirConexion();
			Transaction tx= session.beginTransaction();						
			String hql="Select c.NumCuenta,c.Dni,c.Fecha,c.TipoCuenta,c.Cbu,c.Saldo,c.Estado From Cuenta as c  where c.Dni="+Dni+"and  c.Estado=true ";
			Query query=session.createQuery(hql);
			List<Object[]> result= (List<Object[]>) session.createQuery(hql).list();
			
			ArrayList<Cuenta> Listacuentas= new ArrayList<Cuenta>();
		for(Object[] obj : result) {
				Cuenta cuenta = new Cuenta();
				cuenta.setNumCuenta((int) obj[0]);
				cuenta.setDni((String)obj[1]);
				cuenta.setFecha((String)obj[2]);
				cuenta.setTipoCuenta((String)obj[3]);
				cuenta.setCbu((int)obj[4]);
				cuenta.setSaldo((float)obj[5]);
				cuenta.setEstado((boolean)obj[6]);
				Listacuentas.add(cuenta);
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
		//anda
		public boolean Eliminar1Cuentaxcliente(ClientesxCuentas cli) {	
			Conexion DAO = new Conexion();
			Session session = DAO.abrirConexion();
			session.beginTransaction();
			session.update(cli);
			session.getTransaction().commit();					
			DAO.cerrarSession();
				return true;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
