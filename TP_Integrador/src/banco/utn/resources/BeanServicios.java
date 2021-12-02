package banco.utn.resources;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import banco.utn.dao.*;

@Configuration
public class BeanServicios {
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public DaoServicios DaoServicios() {
		return new DaoServicios();
	}
}
