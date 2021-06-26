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
	
	public List VerificarLogin(String Usuario,String Contraseña) {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		String hql="select c.Usuario From Cliente as c WHERE c.Usuario=:usuario and c.Contraseña=:contraseña and c.Estado=true ";
		Query query=session.createQuery(hql);
		query.setParameter("usuario", Usuario);
		query.setParameter("contraseña", Contraseña);
		List result=query.list();
		tx.commit();
		conexion.cerrarSession();
		return result;
			
	}
	

	
	/* 
	 *Cuentasss 
	 *
	 */
	
	
	public ArrayList<Cliente> TraerClientes() {
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();						
		//String hql="select c.Nombre,c.Apellido,c.Sexo,c.Nacimiento,c.Nacionalidad,c.Provincia,c.Localidad,c.Usuario,c.Dni From Cliente as c where c.Dni in(select a.Dni from ClientesxCuentas as a group by a.Dni having count (*)<4  ) or c.Dni not in (SELECT b.Dni from ClientesxCuentas as b)";
		String hql="From Cliente  ";
		
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
			
			ListaClientes.add(cli);
		}

	
		conexion.cerrarSession();
		
		return ListaClientes;
			
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
			cuenta.setPersona((Cliente)obj[5]);
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
			session.beginTransaction();
			session.update(cuenta);
			session.getTransaction().commit();					
			conexion.cerrarSession();
			return true;
	}
	public boolean EliminarCuentaxcliente(ClientesxCuentas cli) {	
		System.out.println(cli.toString());
		Session session = conexion.abrirConexion();		
			session.beginTransaction();
			session.update(cli);
			session.getTransaction().commit();					
			conexion.cerrarSession();
			return true;
	}
	
	
	
}
