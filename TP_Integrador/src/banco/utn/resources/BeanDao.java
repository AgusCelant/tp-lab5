package banco.utn.resources;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

import banco.utn.dao.*;

@Configuration
public class BeanDao {
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public DaoCuenta BDaoCuenta() {
		return new DaoCuenta();
	}
	
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public DaoPersona BDaoPersona() {
		return new DaoPersona();
	}
	
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public DaoHistorial BDaoHistorial() {
		return new DaoHistorial();
	}
}
