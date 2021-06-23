package banco.utn.principal;

import org.hibernate.Session;

import banco.utn.dao.Conexion;
import banco.utn.entidad.Persona;

public class Main {

	public static void main(String[] args) {

	/*	ApplicationContext appContext = new 
				ClassPathXmlApplicationContext("frgp/utn/edu/ar/resources/beans1.xml");
		Persona m1 = (Persona) appContext.getBean("PersonaPepe");
		//Mundo m = (Mundo)appContext.getBean(Mundo.class);
		System.out.println( m1.toString());
		*/
		Conexion DAO = new Conexion();
		Persona persona = new Persona();
		
		
		Session session = DAO.abrirConexion();
	
		session.beginTransaction();
		
		persona.setNombre("Prueba1");
		persona.setApellido("Prueba1");
		persona.setDni(1234);
	
		session.save(persona);
		
		session.getTransaction().commit();
		
		session.close();
	}
}
