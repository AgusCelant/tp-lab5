package banco.utn.resources;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

import banco.utn.negocio.NegPersona;
import banco.utn.negocio.NegCuentas;

@Configuration
public class BeansNegocio {
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public NegPersona negocioPersona () {
		return new NegPersona();
	}
	
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public NegCuentas negocioCuenta () {
		return new NegCuentas();
	}
}
