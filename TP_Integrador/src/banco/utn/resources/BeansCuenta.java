package banco.utn.resources;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

import banco.utn.entidad.Cuenta;

public class BeansCuenta {
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Cuenta cuenta() {
		return new Cuenta();
	}
}
