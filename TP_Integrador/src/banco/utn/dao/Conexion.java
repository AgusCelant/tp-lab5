package banco.utn.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class Conexion {

	private SessionFactory sessionFactory;
	private Session session;
	private Configuration configuration;
	
	public Conexion()
	{
		//Este new configuration no se puede sacar a un bean po
		configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public Session abrirConexion()
	{
		System.out.println("abriendo");
		session=sessionFactory.openSession();
		System.out.println("pasando");
		return session;
	}
	
	public void cerrarSession()
	{
		session.close();
		cerrarSessionFactory();
	}
	
	
	private void cerrarSessionFactory()
	{
		sessionFactory.close();
	}
	
}
