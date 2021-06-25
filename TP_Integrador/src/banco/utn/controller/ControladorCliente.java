package banco.utn.controller;

import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.service.config.spi.ConfigurationService.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import antlr.collections.List;
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
	public ModelAndView eventoRedireccionarPag1(String nombre,String apellido,String Sexo,String Dni,String date,String Nacionalidad,String Provincia,String Localidad,String usuario,String contraseña)
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
		cliente.setUsuario(usuario);
		cliente.setContraseña(contraseña);
		cliente.setEstado(true);
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
	
	@RequestMapping("verCliente.html")
	public ModelAndView eventovercliente()
	{
		ModelAndView MV = new ModelAndView();
		ArrayList<Cliente> ListaClientes= new ArrayList<Cliente>();
	
		
		ListaClientes = (ArrayList<Cliente>) negocioPersona.listarPersonas();
		MV.addObject("ListaClientes",ListaClientes);
		MV.setViewName("Ver_Clientes");
		return MV;
	}
	
	
	@RequestMapping("/Eliminar.html")
	public ModelAndView eventoeliminar(HttpServletRequest request)
	{
		ModelAndView MV = new ModelAndView();
		String id=request.getParameter("id");
		System.out.println(id);
		
		
		cliente=negocioPersona.BuscarPersonaID(id);
		
		cliente.setDni(cliente.getDni());
		cliente.setNombre("asdadsa");
		cliente.setApellido("sadsadsadsadsadsa");
		cliente.setContraseña(cliente.getContraseña());
		cliente.setLocalidad(cliente.getLocalidad());
		cliente.setProvincia(cliente.getProvincia());
		cliente.setNacimiento(cliente.getNacimiento());
		cliente.setSexo(cliente.getSexo());
		cliente.setUsuario(cliente.getUsuario());
		cliente.setNacionalidad(cliente.getNacionalidad());
		cliente.setEstado(cliente.getEstado());
		//negocioPersona.EliminarPersona(cliente);
		/*String cartel="No se pudo eliminar la persona";
		if(estado)
		{
			cartel="La persona ha sido Eliminada exitosamente";
		}*/
		
		
		//ArrayList<Cliente> ListaClientes= new ArrayList<Cliente>();			
		//ListaClientes = (ArrayList<Cliente>) negocioPersona.listarPersonas();
		//MV.addObject("ListaClientes",ListaClientes);
		MV.setViewName("Ver_Clientes");
		return MV;
	}
	
	
	
	@RequestMapping("/Editar.html")
	public ModelAndView eventoeditar(HttpServletRequest request)
	{
		ModelAndView MV = new ModelAndView();
		String id=request.getParameter("id");
			System.out.println(id);
		
			
		//cliente=negocioPersona.BuscarPersonaID("2");		
		//MV.addObject("Cliente",cliente);
		MV.setViewName("Editar_Cliente");
		return MV;
	}
	
	
}
