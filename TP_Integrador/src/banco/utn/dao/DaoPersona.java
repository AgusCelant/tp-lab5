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
import banco.utn.entidad.Generos;
import banco.utn.entidad.Historial;
import banco.utn.entidad.Usuario;
@Repository("daoPersona")
public class DaoPersona implements InterfazDaoPersona {
	

	//anda
	public List<Cliente> listarPersonas() {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();	
		session.beginTransaction();
		List<Object[]> ListarClientes=(List<Object[]>) session.createQuery(" From Cliente as c inner join c.sexo inner join c.Usuario  inner join c.Nacionalidad inner join c.Provincia inner join c.Localidad where c.Estado=true and c.Provincia.Estado=true").list();
		DAO.cerrarSession();
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
			cliente2.setProvincia(cliente.getProvincia());
			cliente2.getNacionalidad().setNacionalidad(cliente.getNacionalidad().getNacionalidad());
			cliente2.setLocalidad(cliente.getLocalidad());
			cliente2.setUsuario(cliente.getUsuario());
			ListaClientes.add(cliente2);
		}
			
		
		return ListaClientes;
	}
//anda
	public boolean agregarPersona(Cliente p) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
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
		DAO.cerrarSession();
		return aux;
	}
	//anda
	public Cliente BuscarPersonaDni(String Dni) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		Transaction tx= session.beginTransaction();	
		
		String hql=" From Cliente as c  where c.Dni=:dnii and c.Estado=true ";	
		Query query=session.createQuery(hql);
		query.setParameter("dnii", Dni);
		Cliente cliente= new Cliente();
		cliente=(Cliente)query.uniqueResult();	
		DAO.cerrarSession();
		return cliente;
			
	}
	//anda
	public boolean EliminarPersona(Cliente cliente) {	
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();	
			session.beginTransaction();
			session.update(cliente);
			session.getTransaction().commit();					
			DAO.cerrarSession();
		return true;
	}
	//anda
	public boolean EditarPersona(Cliente p) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
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
		DAO.cerrarSession();
		return aux;
	}
	
	
	//anda
	public List<Object[]> VerificarDni(String Dni) {
		Conexion DAO = new Conexion();	
		Session session = DAO.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select c.Dni From Cliente as c  where c.Dni="+Dni+" and c.Estado=true ";		
		List<Object[]> result=(List<Object[]>)session.createQuery(hql).list();
		
		DAO.cerrarSession();
		return result;
			
	}
	//anda
	public List<Object[]> VerificarUsuario(String Usuarioo) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select c.Usuario From Cliente as c  where c.Usuario='"+Usuarioo+"' and c.Estado=true ";	
		List<Object[]> result=(List<Object[]>)session.createQuery(hql).list();
		DAO.cerrarSession();
		return result;
	}
	
	public List<Object[]> VerificarLogin(String Usuario,String Contraseña) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="Select c.Usuario From Cliente as c  where c.Usuario='"+Usuario+"' and c.Contraseña='"+Contraseña+"' and c.Estado=true";
		List<Object[]> result=(List<Object[]>)session.createQuery(hql).list();
		DAO.cerrarSession();
		return result;
			
	}
	
	public Cliente obtenerDatosDeUsuario (String usuario) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		Cliente cliente = (Cliente) session.createQuery("SELECT c FROM Cliente c WHERE Usuario='" + usuario + "'").uniqueResult();
		DAO.cerrarSession();
		return cliente;
	}
	/* 
	 *Cuentas
	 */
	//anda
	public ArrayList<Cliente> TraerClientes() {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		session.beginTransaction();						
		String hql="select c.Nombre,c.Apellido,c.Sexo,c.Nacimiento,c.Nacionalidad,c.Provincia,c.Localidad,c.Usuario,c.Dni From Cliente as c where c.Dni in(select a.Dni from ClientesxCuentas as a where a.Estado=true group by a.Dni having count (*)<4  ) and c.Estado=true or c.Dni not in (SELECT b.Dni from ClientesxCuentas as b where b.Estado=true) and c.Estado=true";
		
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
		DAO.cerrarSession();
		
		return ListaClientes;
			
	}
	
	
	


	
	
	

	
	



	
/*
 * 
 * Historial
 * 	
 */
	public boolean AgregarenHistorial(Historial histo) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
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
		DAO.cerrarSession();
		return aux;
	}
	
	public List<Historial> ListarHistorial() {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		session.beginTransaction();
		List<Historial> ListarHistorial=(List<Historial>) session.createQuery("From Historial ").list();
		DAO.cerrarSession();
		return ListarHistorial;
	}
	
	public List<Historial> ListarHistorialxCuenta(int nroCuenta) {
		Conexion DAO = new Conexion();
		Session session = DAO.abrirConexion();
		session.beginTransaction();
		List<Historial> ListarHistorial=(List<Historial>) session.createQuery("From Historial where nrocuentaorigen="+nroCuenta+"").list();
		DAO.cerrarSession();
		return ListarHistorial;
	}	
	
}
