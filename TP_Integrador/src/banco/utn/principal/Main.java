package banco.utn.principal;

import org.hibernate.Session;

import banco.utn.dao.Conexion;
import banco.utn.entidad.Cliente;

public class Main {

	public static void main(String[] args) {

	/*	ApplicationContext appContext = new 
				ClassPathXmlApplicationContext("frgp/utn/edu/ar/resources/beans1.xml");
		Persona m1 = (Persona) appContext.getBean("PersonaPepe");
		//Mundo m = (Mundo)appContext.getBean(Mundo.class);
		System.out.println( m1.toString());
		*/
		Conexion DAO = new Conexion();
		Cliente persona = new Cliente();
		
		
		Session session = DAO.abrirConexion();
	
		session.beginTransaction();
		/*
		Cliente.setNombre("Prueba1");
		Cliente.setApellido("Prueba1");
		Cliente.setDni(1234);
	*/
		session.save(persona);
		
		session.getTransaction().commit();
		
		session.close();
	}
}
