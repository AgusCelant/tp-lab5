package banco.utn.resources;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

import banco.utn.dao.Conexion;

@Configuration
public class BeansConexion {
	@Bean
	// @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public Conexion conexion () {
		return new Conexion();
	}
}
