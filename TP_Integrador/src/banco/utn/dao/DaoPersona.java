package banco.utn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import banco.utn.entidad.Cliente;

@Repository("daoPersona")
public class DaoPersona {
	
	@Autowired
	private Conexion conexion;
	
	public List<Cliente> listarPersonas() {
		Session session = conexion.abrirConexion();
		session.beginTransaction();
		List<Cliente> ListarClientes=(List<Cliente>) session.createQuery("From Cliente").list();
		
		
		
		
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
	
	public Cliente BuscarPersonaID(String ID) {
		System.out.println("a");
		Session session = conexion.abrirConexion();
		Transaction tx= session.beginTransaction();
		System.out.println("b");		
		Cliente cliente=(Cliente)session.get(Cliente.class,ID);
		tx = session.getTransaction();
		tx.commit();
		conexion.cerrarSession();
		return cliente;
			
	}
	
	public boolean EliminarPersona(Cliente cliente) {
	
		Session session = conexion.abrirConexion();

		
			session.beginTransaction();
			session.delete(cliente);
			session.getTransaction().commit();
			
		
		conexion.cerrarSession();
		return true;
		

	}
	
}
