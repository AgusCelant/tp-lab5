package banco.utn.resources;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

import banco.utn.negocio.NegPersona;

@Configuration
public class BeansNegocio {
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public NegPersona negocioPersona () {
		return new NegPersona();
	}
}
