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

import java.util.List;
import banco.utn.dao.Conexion;
import banco.utn.dao.DaoCuenta;
import banco.utn.dao.DaoPersona;
import banco.utn.entidad.Cliente;
import banco.utn.entidad.ClientesxCuentas;
import banco.utn.entidad.Cuenta;
import banco.utn.negocio.NegPersona;
import org.hibernate.Session;

@Controller
public class ControladorCliente {

	@Autowired
	@Qualifier("servicioPersona")
	private NegPersona negocioPersona;
	@Autowired
	private Cliente cliente;
	@Autowired
	private ClientesxCuentas C;
	
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
	public ModelAndView eventoRedireccionarPag1(/*String Nombre,String Apellido,String Sexo,String Dni,String Date,String Nacionalidad,String Provincia,String Localidad,String Contraseña,String Usuario*/)
	{
		ModelAndView MV = new ModelAndView();
		int c=0;
		String cartel=" ";
	
		C.setDni("1");
		C.setIdCuenta(1);
	
	/*	cliente.setNombre(Nombre);
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
		
		*/
		

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
		java.util.List Dni = null;
	
		Dni=negocioPersona.BuscarPersonaID(id);
			/*		
			 * Cuenta cuenta = new Cuenta();
		ClientesxCuentas cli = new ClientesxCuentas();
		cuenta=negocioPersona.BuscarCuentaDni(Dni);
		cli=negocioPersona.BuscarCuentaxCliente(Dni);
		cuenta.setEstado(false);
		cli.setEstado(1);
		negocioPersona.EliminarCuenta(cuenta);
		negocioPersona.EliminarCuentaxcliente(cli);
		System.out.println(cliente.toString());
		cliente.setDni(cliente.getDni());
		cliente.setNombre(cliente.getNombre());
		cliente.setApellido(cliente.getApellido());
		cliente.setContraseña(cliente.getContraseña());
		cliente.setLocalidad(cliente.getLocalidad());
		cliente.setProvincia(cliente.getProvincia());
		cliente.setNacimiento(cliente.getNacimiento());
		cliente.setSexo(cliente.getSexo());
		cliente.setUsuario(cliente.getUsuario());
		cliente.setNacionalidad(cliente.getNacionalidad());
		cliente.setEstado(cliente.getEstado());
		
		*/
		
		//negocioPersona.EliminarPersona(cliente);
		/*String cartel="No se pudo eliminar la persona";
		if(estado)
		{
			cartel="La persona ha sido Eliminada exitosamente";
		}*/
		
		/*
		 * 
		 * Listar personas
		 * 
		 * 	//ArrayList<Cliente> ListaClientes= new ArrayList<Cliente>();			
		//ListaClientes = (ArrayList<Cliente>) negocioPersona.listarPersonas();
		//MV.addObject("ListaClientes",ListaClientes);
		 * 
		 */
	
		MV.setViewName("Ver_Clientes");
		return MV;
	}
	

	@RequestMapping("/Editar.html")
	public ModelAndView eventoeditar(HttpServletRequest request)
	{
		ModelAndView MV = new ModelAndView();
		String id=request.getParameter("id");
		java.util.List Dni = null;
	
		Dni=negocioPersona.BuscarPersonaID(id);
		
		MV.setViewName("Editar_Cliente");
		return MV;
	}	
	
	@RequestMapping("/MostrarClientes.html")
	public ModelAndView EventoVerClientes(HttpServletRequest request)
	{		
		ModelAndView MV = new ModelAndView();
		
		ClientesxCuentas cli = new ClientesxCuentas();
		cli.setDni("2");
		cli.setIdCuenta(2);
	
		
		System.out.println(cli.toString());
		negocioPersona.Eliminar1Cuentaxcliente(cli);
		MV.setViewName("Agregar_CuentaP1");
		return MV;
	}
	
	@RequestMapping("AgregarCuentaP1.html")
	public ModelAndView EventoIrAltaCuenta(HttpServletRequest request)
	{
		String Dni = request.getParameter("dni");
		
		ModelAndView MV = new ModelAndView();
		MV.addObject("dni", Dni);
		MV.setViewName("Altas_Cuentas");
		
		return MV;
	}
	
	@RequestMapping("AsociarCuenta.html")
	public ModelAndView EventoAsociarCuenta(String dni, String nroCuenta, String fechaCreacion, String tipoCuenta, String cbu, String saldo)
	{
		DaoCuenta DAOCuenta = new DaoCuenta();
		DAOCuenta.asociarCuenta(dni, nroCuenta, fechaCreacion, tipoCuenta, cbu, saldo);
		
		ModelAndView MV = new ModelAndView();
		MV.setViewName("Agregar_CuentaP1");
		
		return MV;
	}
	
	@RequestMapping("/EliminarCuenta.html")
	public ModelAndView EventoEliminarCuenta(HttpServletRequest request)
	{		
		ModelAndView MV = new ModelAndView();	
		String Dni=request.getParameter("id");		
		Cuenta cuenta = new Cuenta();
		ClientesxCuentas cli = new ClientesxCuentas();
		cuenta=negocioPersona.BuscarCuentaDni(Dni);
		cli=negocioPersona.BuscarCuentaxCliente(Dni);
		cuenta.setEstado(false);
		negocioPersona.Eliminar1Cuenta(cuenta);
		negocioPersona.Eliminar1Cuentaxcliente(cli);
		
		MV.setViewName("Ver_Cuentas");
		return MV;
	}	
}
