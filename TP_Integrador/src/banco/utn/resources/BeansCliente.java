package banco.utn.resources;

import org.springframework.context.annotation.*;

import banco.utn.entidad.*;

@Configuration
public class BeansCliente {

	@Bean
	public Provincia provincia() {
		return new Provincia();
	}
	
	@Bean
	public Localidad localidad() {
		return new Localidad();
	}
	
	@Bean
	public Cliente cliente() {
		return new Cliente();
	}
	
	@Bean
	public Generos genero() {
		return new Generos();
	}
	
	@Bean
	public Usuario usuario() {
		return new Usuario();
	}
	
	@Bean
	public Nacionalidad nacionalidad() {
		return new Nacionalidad();
	}
}
