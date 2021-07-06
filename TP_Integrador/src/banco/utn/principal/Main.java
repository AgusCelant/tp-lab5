package banco.utn.principal;
import org.hibernate.Session;
import banco.utn.dao.Conexion;
import banco.utn.entidad.Cliente;
import banco.utn.entidad.Cuenta;

public class Main {

	public static void main(String[] args) {
		/* Conexion DAO = new Conexion();
		Cliente persona = new Cliente("222",
				"password2",
				"localidad2",
				"nacimineto2",
				"nacionalidad2",
				"provincia2",
				"sexo2",
				"usuario2",
				"apellido2",
				"nombre2",
				true);
		
		Session sessionPersona = DAO.abrirConexion();
		sessionPersona.beginTransaction();
		sessionPersona.save(persona);
		sessionPersona.getTransaction().commit();
		DAO.cerrarSession(); */

		/*Conexion DAO = new Conexion();
		Cuenta cuenta1= new Cuenta("26/06/21", "Dolares", 0001, 10500, true);
		Cuenta cuenta2= new Cuenta("27/06/21", "Pesos", 0002, 10000, true);
		
		Session session = DAO.abrirConexion();
		session.beginTransaction();
		session.save(cuenta1);
		session.save(cuenta2);
		session.getTransaction().commit();
		DAO.cerrarSession();*/
	}
}
