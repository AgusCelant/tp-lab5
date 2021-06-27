package banco.utn.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import banco.utn.entidad.Cliente;
import banco.utn.entidad.ClientesxCuentas;
import banco.utn.entidad.Cuenta;
import banco.utn.entidad.Historial;
@Repository("daoPersona")
public class DaoPersona {
	
	@Autowired
	private Conexion conexion;
	
	public List<Cliente> listarPersonas() {
		Session session = conexion.abrirConexion();
		session.beginTransaction();
		List<Cliente> ListarClientes=(List<Cliente>) session.createQuery("From Cliente where Estado=true").list();
		//session.close();
		conexion.cerrarSession();
		return ListarClientes;
	}

	public boolean agregarPersona(Cliente p) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();
		boolean aux = true;
		try
		{
			session.save(p); 
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
	
	public List BuscarPersonaID(String Dni) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="select * From Cliente as c WHERE c.Dni=:usuarioID and c.Estado=true ";
		Query query=session.createQuery(hql);
		query.setParameter("usuarioID", Dni);
		List result=query.list();
		tx.commit();
		conexion.cerrarSession();
		return result;
			
	}
	
	public boolean EliminarPersona(Cliente cliente) {	
		Session session = conexion.abrirConexion();		
			session.beginTransaction();
			session.update(cliente);
			session.getTransaction().commit();					
		conexion.cerrarSession();
		return true;
	}
	
	public List VerificarDni(String Dni) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="select c.Dni From Cliente as c WHERE c.Dni=:usuarioID and c.Estado=true ";
		Query query=session.createQuery(hql);
		query.setParameter("usuarioID", Dni);
		List result=query.list();
		tx.commit();
		System.out.println(result.toString());
		conexion.cerrarSession();
		return result;
			
	}
	
	public List VerificarUsuario(String Usuario) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="select c.Usuario From Cliente as c WHERE c.Usuario=:usuario and c.Estado=true ";
		Query query=session.createQuery(hql);
		query.setParameter("usuario", Usuario);
		List result=query.list();
		tx.commit();
		conexion.cerrarSession();
		return result;
	}
	
	public List VerificarLogin(String Usuario,String Contraseņa) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="select c.Usuario From Cliente as c WHERE c.Usuario=:usuario and c.Contraseņa=:contraseņa and c.Estado=true ";
		Query query=session.createQuery(hql);
		query.setParameter("usuario", Usuario);
		query.setParameter("contraseņa", Contraseņa);
		List result=query.list();
		tx.commit();
		conexion.cerrarSession();
		return result;
			
	}
	
	public Cliente obtenerDatosDeUsuario (String usuario) {
		Conexion conexion = new Conexion();
		Session session = conexion.abrirConexion();
		Cliente cliente = (Cliente) session.createQuery("SELECT c FROM Cliente c WHERE Usuario='" + usuario + "'").uniqueResult();
		conexion.cerrarSession();
		return cliente;
	}
	/* 
	 *Cuentas
	 */
	
	public ArrayList<Cliente> TraerClientes() {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="select c.Nombre,c.Apellido,c.Sexo,c.Nacimiento,c.Nacionalidad,c.Provincia,c.Localidad,c.Usuario,c.Dni From Cliente as c where c.Dni in(select a.Dni from ClientesxCuentas as a group by a.Dni having count (*)<4  ) and c.Estado=true or c.Dni not in (SELECT b.Dni from ClientesxCuentas as b) and c.Estado=true";
		//String hql="From Cliente  ";
		
		ArrayList<Cliente> ListaClientes= new ArrayList<Cliente>();
		List<Object[]> result= (List<Object[]>) session.createQuery(hql).list();
		for(Object[] obj : result) {
			Cliente cli = new Cliente();
			cli.setNombre((String)obj[0]);
			cli.setApellido((String)obj[1]);
			cli.setSexo((String)obj[2]);
			cli.setNacimiento((String)obj[3]);
			cli.setNacionalidad((String)obj[4]);
			cli.setProvincia((String)obj[5]);
			cli.setLocalidad((String)obj[6]);
			cli.setUsuario((String)obj[7]);	
			cli.setDni((String)obj[8]);	
			System.out.println(cli.toString());
			ListaClientes.add(cli);
		}

	
		conexion.cerrarSession();
		
		return ListaClientes;
			
	}
	

	public List<Cuenta> listarCuentas() {
		Session session = conexion.abrirConexion();
		session.beginTransaction();
		List<Cuenta> listarCuentas=(List<Cuenta>) session.createQuery("From Cuenta where Estado=true").list();
		conexion.cerrarSession();
		return listarCuentas;
	}
	

	public boolean agregarClientesxcuentas(ClientesxCuentas c) {
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
	
	public Cuenta BuscarCuentaDni(String Dni) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="select * From Cliente as c WHERE c.Dni=:usuarioID and c.Estado=true ";
		Query query=session.createQuery(hql);
		query.setParameter("usuarioID", Dni);	
		List<Object[]> result= (List<Object[]>) session.createQuery(hql).list();
		Cuenta cuenta = new Cuenta();
		for(Object[] obj : result) {
			
			cuenta.setNumCuenta((int) obj[0]);
			cuenta.setFecha((String)obj[1]);
			cuenta.setTipoCuenta((String)obj[2]);
			cuenta.setCbu((int)obj[3]);
			cuenta.setSaldo((float)obj[4]);
			cuenta.setDni((String)obj[5]);
			
		}
		tx.commit();
		conexion.cerrarSession();

		return cuenta;
			
	}
	
	public ClientesxCuentas BuscarCuentaxCliente(String Dni) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="select * From Cliente as c WHERE c.Dni=:usuarioID and c.Estado=true ";
		Query query=session.createQuery(hql);
		query.setParameter("usuarioID", Dni);	
		List<Object[]> result= (List<Object[]>) session.createQuery(hql).list();
		ClientesxCuentas cli = new ClientesxCuentas();
		for(Object[] obj : result) {
			
			cli.setDni((String) obj[0]);
			cli.setIdCuenta((int)obj[1]);

		}
		tx.commit();
		conexion.cerrarSession();

		return cli;
			
	}
	
	public boolean EliminarCuenta(Cuenta cuenta) {	
		Session session = conexion.abrirConexion();	
		String Dni="a";
		String hql="Update  Cuenta set Estado=false where Dni="+Dni+" ";
		Query query=session.createQuery(hql);
		int result=query.executeUpdate();					
			conexion.cerrarSession();
			return true;
	}

	public boolean Eliminar1Cuenta(Cuenta cuenta) {	
		Session session = conexion.abrirConexion();
		String Dni="a";
			String hql="Update  Cuenta set Estado=false where Dni="+Dni+" and NumCuenta="+cuenta.getNumCuenta()+"";
			Query query=session.createQuery(hql);
			int result=query.executeUpdate();						
			conexion.cerrarSession();
			return true;
	}
	public boolean EliminarCuentaxcliente(ClientesxCuentas cli) {	
		System.out.println(cli.toString());
		Session session = conexion.abrirConexion();		
		//String hql="Update  ClientesxCuentas  set Dni=5 where IdCuenta=2";
		String hql="Update  ClientesxCuentas c set Estado=false where Dni="+cli.getDni()+"";
		
		Query query=session.createQuery(hql);
		int result=query.executeUpdate();					
			conexion.cerrarSession();
			return true;
	}
	public boolean Eliminar1Cuentaxcliente(ClientesxCuentas cli) {	
		System.out.println(cli.toString());
		Session session = conexion.abrirConexion();		
		String hql="Update  ClientesxCuentas c set c.Estado=false where c.Dni="+cli.getDni()+" and c.IdCuenta="+cli.getIdCuenta()+"";

		Query query=session.createQuery(hql);

		int result=query.executeUpdate();					
			conexion.cerrarSession();
			return true;
	}
/*
 * 
 * Historial
 * 	
 */
	public boolean AgregarenHistorial(Historial histo) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();
		boolean aux = true;
		try
		{
			session.save(histo); 
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
	
	public List<Historial> ListarHistorial() {
		Session session = conexion.abrirConexion();
		session.beginTransaction();
		List<Historial> ListarHistorial=(List<Historial>) session.createQuery("From Historial ").list();
		conexion.cerrarSession();
		return ListarHistorial;
	}
	
	public List<Historial> ListarHistorialxCuenta(int nroCuenta) {
		Session session = conexion.abrirConexion();
		session.beginTransaction();
		List<Historial> ListarHistorial=(List<Historial>) session.createQuery("From Historial where nrocuentaorigen="+nroCuenta+"").list();
		conexion.cerrarSession();
		return ListarHistorial;
	}
	
	
	
	
}
