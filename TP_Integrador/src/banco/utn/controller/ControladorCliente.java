package banco.utn.controller;

import javax.servlet.ServletConfig;

import org.hibernate.service.config.spi.ConfigurationService.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import banco.utn.entidad.Cliente;
import banco.utn.negocio.NegPersona;

@Controller
public class ControladorCliente {

	@Autowired
	@Qualifier("servicioPersona")
	private NegPersona negocioPersona;
	@Autowired
	private Cliente cliente;
	
	
	@RequestMapping("agregarPersona.html")
	public ModelAndView eventoRedireccionarPag1(String nombre,String apellido,String Sexo,String Dni,String date,String Nacionalidad,String Provincia,String Localidad,String Usuario,String Contraseña)
	{
		ModelAndView MV = new ModelAndView();
		
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setSexo(Sexo);
		cliente.setDni(Dni);
		cliente.setNacimiento(date);
		cliente.setNacionalidad(Nacionalidad);
		cliente.setLocalidad(Localidad);
		cliente.setProvincia(Provincia);
		cliente.setUsuario(Usuario);
		cliente.setContraseña(Contraseña);
		
		boolean estado= negocioPersona.agregarPersona(cliente);
		String cartel="No se pudo agregar la persona";
		if(estado)
		{
			cartel="La persona ha sido agregada exitosamente";
		}
		MV.addObject("estadoAgregarPersona",cartel);
		
	
		MV.setViewName("Alta_Cliente");
		return MV;
	}
	
	
}
