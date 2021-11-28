package banco.utn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import banco.utn.entidad.Pagos;
import banco.utn.entidad.Servicios;


@Repository("daoPagos")
public class DaoPagos {
	
	@Autowired
	private Conexion conexion;
	
	public boolean agregarPago(Pagos p) {
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
	
	public List<Pagos> ListarPagos (String dni) {
		
		Session session = conexion.abrirConexion();
		
		
		session.beginTransaction();		
		List<Pagos> listaPagos = (List<Pagos>) session.createQuery("From Pagos where idcliente= " + dni).list();
		conexion.cerrarSession();
		
		
		return listaPagos;
	
	}

}
