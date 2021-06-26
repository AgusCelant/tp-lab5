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
import banco.utn.dao.Conexion;
import banco.utn.entidad.Cliente;
import banco.utn.negocio.NegPersona;
import org.hibernate.Session;

@Controller
public class ControladorCliente {

	@Autowired
	@Qualifier("servicioPersona")
	private NegPersona negocioPersona;
	@Autowired
	private Cliente cliente;
	
	@RequestMapping("mainCliente.html")
	public void eventoRedireccionarMainCliente(String txtUsuario, String txtPass) {
		ModelAndView MV = new ModelAndView();
		Conexion conexion = new Conexion();
		Session session = conexion.abrirConexion();
		Cliente cliente = (Cliente) session.createQuery("SELECT c FROM Cliente c WHERE dni='123'").uniqueResult();
		conexion.cerrarSession();
		MV.addObject("clienteLogueado", cliente);
		MV.setViewName("mainCliente");
	}

	@RequestMapping("agregarPersona.html")
	public ModelAndView eventoRedireccionarPag1(String Nombre,String Apellido,String Sexo,String Dni,String Date,String Nacionalidad,String Provincia,String Localidad,String Contraseña,String Usuario)
	{
		ModelAndView MV = new ModelAndView();
		int c=0;
		String cartel=" ";
	
		cliente.setNombre(Nombre);
		cliente.setApellido(Apellido);
		cliente.setSexo(Sexo);
		cliente.setDni(Dni);
		cliente.setNacimiento(Date);
		cliente.setNacionalidad(Nacionalidad);
		cliente.setLocalidad(Localidad);
		cliente.setProvincia(Provincia);
		cliente.setUsuario(Usuario);
		cliente.setContraseña(Contraseña);
		cliente.setEstado(true);
		String dni=cliente.getDni();
		String Usuarioo=cliente.getUsuario();
		java.util.List verifDni = null;
		java.util.List Usu=null;
		verifDni=negocioPersona.VerificarDni(dni);
		System.out.println(verifDni.toString());
		System.out.println(Usuarioo);
		Usu=negocioPersona.VerificarUsuario(Usuarioo);
		if(Usu!=null) {c++;}
		
		if(c==0) {
		//boolean estado= negocioPersona.agregarPersona(cliente);
			boolean estado=true;
		if(estado)
		{
		cartel = "La persona ha sido agregada exitosamente";
		
		}
		}
		else {
		cartel="No se pudo agregar la persona el dni o el usuario ya existen";
			
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
		boolean aux = true;
		
		cliente=negocioPersona.BuscarPersonaID(id);
		
		//aux=negocioPersona.BuscarPersonaID("2");
		System.out.println(cliente.toString());
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
	
	@RequestMapping("/verificardni.html")
	public ModelAndView eventoverificardni(HttpServletRequest request)
	{
		ModelAndView MV = new ModelAndView();
		String dni=request.getParameter("id");
		
		java.util.List aux = null;
		String Usuario="asdsadsa";	
		
		java.util.List Usu=null;
		//aux=negocioPersona.VerificarDni(dni);
		Usu=negocioPersona.VerificarUsuario(Usuario);
		
		System.out.println(Usu.toString());
		
		
		String Dniduplicado="asd";
		if(aux!=null) {
			Dniduplicado="El dni ya existe";
		}
		
		MV.addObject("Dniduplicado",Dniduplicado);
		MV.setViewName("Ver_Clientes");
		return MV;
	}
}
