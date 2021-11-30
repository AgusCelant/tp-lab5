package banco.utn.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import banco.utn.entidad.*;
@Repository("daoPersona")
public class DaoPersona implements InterfazDaoPersona {
	@Autowired
	private Conexion conexion;
	
	public List<Cliente> listarPersonas() {
		Session session = conexion.abrirConexion();
		session.beginTransaction();
		List<Object[]> ListarClientes=(List<Object[]>) session.createQuery(" From Cliente as c inner join c.sexo inner join c.Usuario  inner join c.Nacionalidad inner join c.Provincia inner join c.Localidad where c.Estado=true and c.Provincia.Estado=true").list();
		conexion.cerrarSession();
		ArrayList<Cliente> ListaClientes= new ArrayList<Cliente>();
		for(Object[] objeto: ListarClientes) {
			Cliente cliente= new Cliente();
			Cliente cliente2= new Cliente();
			cliente=(Cliente) objeto[0];
			cliente2.setDni(cliente.getDni());
			cliente2.setNombre(cliente.getNombre());
			cliente2.setApellido(cliente.getApellido());
			cliente2.getSexo().setGenero(cliente.getSexo().getGenero());
			cliente2.setNacimiento(cliente.getNacimiento());
		/*	cliente2.setProvincia(cliente.getProvincia());
			cliente2.getNacionalidad().setNacionalidad(cliente.getNacionalidad().getNacionalidad());
			cliente2.setLocalidad(cliente.getLocalidad());*/
			cliente2.setUsuario(cliente.getUsuario());
			ListaClientes.add(cliente2);
		}
			
		
		return ListaClientes;
	}
//anda
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
	//anda
	public Cliente BuscarPersonaDni(String Dni) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();	
		
		String hql=" From Cliente as c  where c.Dni=:dnii and c.Estado=true ";	
		Query query=session.createQuery(hql);
		query.setParameter("dnii", Dni);
		Cliente cliente= new Cliente();
		cliente=(Cliente)query.uniqueResult();	
		conexion.cerrarSession();
		return cliente;
			
	}
	//anda
	public boolean EliminarPersona(Cliente cliente) {	
		Session session = conexion.abrirConexion();	
			session.beginTransaction();
			session.update(cliente);
			session.getTransaction().commit();					
			conexion.cerrarSession();
		return true;
	}
	//anda
	public boolean EditarPersona(Cliente p) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();
		boolean aux = true;
		try
		{
			session.update(p); 
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
	//anda
	public List<Object[]> VerificarDni(String Dni) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select c.Dni From Cliente as c  where c.Dni="+Dni+" and c.Estado=true ";		
		List<Object[]> result=(List<Object[]>)session.createQuery(hql).list();
		
		conexion.cerrarSession();
		return result;
	}
	//anda
	public List<Object[]> VerificarUsuario(String Usuarioo) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select c.Usuario From Cliente as c  where c.Usuario='"+Usuarioo+"' and c.Estado=true ";	
		List<Object[]> result=(List<Object[]>)session.createQuery(hql).list();
		conexion.cerrarSession();
		return result;
	}
	
	public List<Object[]> VerificarLogin(String Usuario,String Contraseña) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select c.Usuario From Cliente as c  where c.Usuario.Usuario='"+Usuario+"' and c.Usuario.Contraseña='"+Contraseña+"' and c.Estado=true";
		List<Object[]> result=(List<Object[]>)session.createQuery(hql).list();
		conexion.cerrarSession();
		return result;
			
	}
	
	public Cliente obtenerDatosDeUsuario (String usuario) {
		Session session = conexion.abrirConexion();
		//Cliente cliente = (Cliente) session.createQuery("SELECT c FROM Cliente c WHERE Usuario.Usuario='" + usuario + "'").uniqueResult();
		String hql=" From Cliente as c  where c.Usuario.Usuario=:Usu and c.Estado=true ";	
		Query query=session.createQuery(hql);
		query.setParameter("Usu", usuario);	
		Cliente cliente= new Cliente();
		cliente=(Cliente)query.uniqueResult();	
		
		conexion.cerrarSession();
		return cliente;
	}
	/* 
	 *Cuentas
	 */
	//anda
	public ArrayList<Cliente> TraerClientes() {
		Session session = conexion.abrirConexion();
		session.beginTransaction();						
		List<Object[]> ListarClientes=(List<Object[]>) session.createQuery("From Cliente as c inner join c.sexo inner join c.Usuario  inner join c.Nacionalidad inner join c.Provincia inner join c.Localidad where c.Dni in(select a.Cliente.Dni from Cuenta as a where a.Estado=true group by a.Cliente.Dni having count (*)<4  ) and c.Estado=true or c.Dni not in (select b.Cliente.Dni from Cuenta as b where b.Estado=true) and c.Estado=true").list();	
		ArrayList<Cliente> ListaClientes= new ArrayList<Cliente>();	
		for(Object[] result: ListarClientes) {
			Cliente cliente= new Cliente();
			Cliente cliente2= new Cliente();
			cliente=(Cliente) result[0];
			cliente2.setDni(cliente.getDni());
			cliente2.setNombre(cliente.getNombre());
			cliente2.setApellido(cliente.getApellido());
			cliente2.getSexo().setGenero(cliente.getSexo().getGenero());
			cliente2.setNacimiento(cliente.getNacimiento());
			/*cliente2.setProvincia(cliente.getProvincia());
			cliente2.getNacionalidad().setNacionalidad(cliente.getNacionalidad().getNacionalidad());
			cliente2.setLocalidad(cliente.getLocalidad());*/
			cliente2.setUsuario(cliente.getUsuario());
			ListaClientes.add(cliente2);
		}
		conexion.cerrarSession();
		
		return ListaClientes;
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
