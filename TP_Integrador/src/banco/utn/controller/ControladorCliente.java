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
	@Autowired
	private Cuenta Cuen;
	
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
	//anda
	@RequestMapping("agregarPersona.html")
	public ModelAndView eventoRedireccionarPag1(String Nombre,String Apellido,String Sexo,String Dni,String Date,String Nacionalidad,String Provincia,String Localidad,String Contraseña,String Usuario)
	{
		ModelAndView MV = new ModelAndView();

		int c=0;
		List<Object[]> dniverificar=null;
		List<Object[]> Usu=null;
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
		dniverificar=negocioPersona.VerificarDni(dni);
		Usu=negocioPersona.VerificarUsuario(Usuarioo);

		

		if(dniverificar.isEmpty()) {
			
		}else {
			c++;
		}
		if(Usu.isEmpty()) {
			
		}else {
			c++;
		}
		System.out.println(c);
		
		if(c==0) {
			boolean estado= negocioPersona.agregarPersona(cliente);
			cartel="Cliente agregado Correctamente";
		}else {
			cartel="Usuario Repetido o dni repetido";
			
		}

		MV.addObject("estadoAgregarPersona",cartel);
		MV.setViewName("Alta_Cliente");
		
		return MV;
	}
	//anda
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
	//anda
	@RequestMapping("/Eliminar.html")
	public ModelAndView eventoeliminar(HttpServletRequest request)
	{
		ModelAndView MV = new ModelAndView();
		String id=request.getParameter("id");
		java.util.List Dni = null;
		Cliente cli=negocioPersona.BuscarPersonaDni(id);
		cli.setEstado(false);
		negocioPersona.EliminarPersona(cli);
	 	ArrayList<Cliente> ListaClientes= new ArrayList<Cliente>();			
		ListaClientes = (ArrayList<Cliente>) negocioPersona.listarPersonas();
		MV.addObject("ListaClientes",ListaClientes);

		MV.setViewName("Ver_Clientes");
		return MV;
	}
	
/*
	@RequestMapping("/Editar.html")
	public ModelAndView eventoeditar(HttpServletRequest request)
	{
		ModelAndView MV = new ModelAndView();
		String id=request.getParameter("id");
		java.util.List Dni = null;
	
		Cliente cli=negocioPersona.BuscarPersonaDni(id);
		
		MV.setViewName("Editar_Cliente");
		return MV;
	}	
	*/
		
		//anda
	@RequestMapping("/MostrarClientes.html")
	public ModelAndView EventoVerClientes(HttpServletRequest request)
	{		
		ModelAndView MV = new ModelAndView();

		ArrayList<Cliente> ListaClientes= new ArrayList<Cliente>();	
		NegPersona negP=new NegPersona();
		ListaClientes=negocioPersona.TraerClientes();
		
		
		
		
		MV.addObject("ListaClientes", ListaClientes);
		MV.setViewName("Agregar_CuentaP1");
		return MV;
	}
	//anda
	@RequestMapping("AgregarCuentaP1.html")
	public ModelAndView EventoIrAltaCuenta(HttpServletRequest request)
	{
		String Dni = request.getParameter("dni");
		
		ModelAndView MV = new ModelAndView();
		MV.addObject("dni", Dni);
		MV.setViewName("Altas_Cuentas");
		
		return MV;
	}
	//falta 
	@RequestMapping("AsociarCuenta.html")
	public ModelAndView EventoAsociarCuenta(String dni, String nroCuenta, String fechaCreacion, String tipoCuenta, int cbu, float saldo)
	{
		
	
		DaoCuenta DAOCuenta = new DaoCuenta();
		int contador=DAOCuenta.ContadordeCuentasxclientes();
	
	
		

		if(nroCuenta.isEmpty()) {
			Cuen.setDni(dni);
			Cuen.setNumCuenta(1);
			Cuen.setCbu(cbu);	
			Cuen.setFecha(fechaCreacion);
			Cuen.setSaldo(saldo);
			Cuen.setTipoCuenta(tipoCuenta);
			Cuen.setEstado(true);
			DAOCuenta.AgregarCuenta(Cuen);
			C.setDni(dni);
			C.setIdCuenta(1);
			C.setEstado(true);
			DAOCuenta.agregarClientesxcuentas(C);
		}else {
		
			
		}
		//si esta vacio hace sun new 
	
	
		//asume que ya existe
		//DAOCuenta.asociarCuenta(dni, nroCuenta, fechaCreacion, tipoCuenta, cbu, saldo);
		
		ModelAndView MV = new ModelAndView();
		MV.setViewName("Agregar_CuentaP1");
		
		return MV;
	}
	//falta
	@RequestMapping("/EliminarCuenta.html")
	public ModelAndView EventoEliminarCuenta(HttpServletRequest request)
	{	DaoCuenta DAOCuenta = new DaoCuenta();
		Cuenta cuenta = new Cuenta();
		ModelAndView MV = new ModelAndView();	
		String Dni=request.getParameter("id");	
		
		String[] parts = Dni.split(",");
		String Dnii = parts[0]; 
		int numCuenta =Integer.parseInt(parts[1]);
				 
		
		
		  
		ClientesxCuentas cli = new ClientesxCuentas();
		cuenta=negocioPersona.BuscarCuentaDni(Dnii,numCuenta);
		System.out.println(cuenta.toString());
		System.out.print(cuenta.toString());
		//i=negocioPersona.BuscarCuentaxCliente(Dni);
		cuenta.setEstado(false);
		//negocioPersona.Eliminar1Cuenta(cuenta);
		//negocioPersona.Eliminar1Cuentaxcliente(cli);
		ArrayList<Cuenta> ListaCuentas= new ArrayList<Cuenta>();
		ListaCuentas = (ArrayList<Cuenta>)DAOCuenta.listarCuentas() ;
		MV.addObject("ListaCuentas",ListaCuentas);
		MV.setViewName("Ver_Cuentas");
		return MV;
	}
	
	
	//anda
	@RequestMapping("verCuenta.html")
	public ModelAndView eventovercuentas()
	{
		DaoCuenta DAOCuenta = new DaoCuenta();
		ModelAndView MV = new ModelAndView();
		ArrayList<Cuenta> ListaCuentas= new ArrayList<Cuenta>();
	
		
		ListaCuentas = (ArrayList<Cuenta>)DAOCuenta.listarCuentas() ;
		MV.addObject("ListaCuentas",ListaCuentas);
		MV.setViewName("Ver_Cuentas");
		return MV;
	}
	
	
}
