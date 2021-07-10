package banco.utn.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import banco.utn.dao.DaoCuenta;
import banco.utn.dao.DaoPersona;
import banco.utn.entidad.*;
import banco.utn.negocio.NegCuentas;
import banco.utn.negocio.NegPersona;

@Controller
public class ControladorCliente {
	@Autowired
	private NegPersona negocioPersona;
	@Autowired
	private Cliente cliente;
	private Generos gen;
	@Autowired
	private Cuenta cuenta;
	@Autowired
	private DaoCuenta BDaoCuenta;
	@Autowired
	private DaoPersona BDaoPersona;
	@Autowired
	private NegCuentas negocioCuenta;
	
	@RequestMapping("mainCliente.html")
	public ModelAndView eventoRedireccionarMainCliente(HttpServletRequest request) {
		ModelAndView MV = new ModelAndView();
		String Dnii=(String) request.getSession().getAttribute("Dni");
		String Clientelogueado=(String) request.getSession().getAttribute("Clienteelogueado");
		List<Cuenta> cuentas = BDaoCuenta.obtenerCuentasDeUsuario(Dnii);
		Cliente cliente = BDaoPersona.obtenerDatosDeUsuario(Clientelogueado);
		String resumenCuentas = "";
		
		for(Cuenta cuenta : cuentas) {
			resumenCuentas = "<div>Nro de caja de ahorro: <b>" + cuenta.getNumCuenta() + "</b>, Moneda: <b>" + cuenta.getTipoCuenta() + "</b>, Saldo: <b>" + cuenta.getSaldo() + "</b></div><br>";
		}
		System.out.println(resumenCuentas);
		MV.addObject("clienteLogueado", cliente);
		MV.addObject("cuentasCliente", resumenCuentas);
		MV.setViewName("mainCliente");
		return MV;
	}
//anda
	@RequestMapping("irAgregarCliente.html")
	public ModelAndView IrpagAgregarCliente() {
		ModelAndView MV = new ModelAndView();
		MV.setViewName("Alta_Cliente");
		return MV;
	}
	//anda
	@RequestMapping("irMenuAdmin.html")
	public ModelAndView irMenuAdmin() {
		ModelAndView MV = new ModelAndView();
		List<Integer> cuentaspesos = BDaoCuenta.ObtenerPorcentajedeCuentasPesos();			
		List<Integer> cuentasdolar = BDaoCuenta.ObtenerPorcentajedeCuentasDolar();		
		int cuenta1=0;
		int cuenta2=0;
		if (cuentaspesos.get(0)==null) {				
			MV.addObject("Cuenta1", cuenta1);
		} else {
			MV.addObject("Cuenta1", cuentaspesos.get(0));
		}
		if (cuentasdolar.get(0)==null) {				
			MV.addObject("Cuenta2", cuenta2);
		} else {
			MV.addObject("Cuenta2", cuentasdolar.get(0));
		}
		
		MV.setViewName("PerfilAdmin");
		return MV;
	}
	
	//anda con la modalidad de sexo
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
		cliente.getSexo().setDni(Dni);
		cliente.getSexo().setGenero(Sexo);
		cliente.setDni(Dni);
		cliente.setNacimiento(Date);
		cliente.getNacionalidad().setDni(Dni);
		cliente.getNacionalidad().setNacionalidad(Nacionalidad);
		cliente.getNacionalidad().setEstado(true);
		cliente.getLocalidad().setDni(Dni);
		cliente.getLocalidad().setNombre(Localidad);
		cliente.getLocalidad().setEstado(true);
		cliente.getProvincia().setDni(Dni);
		cliente.getProvincia().setNombre(Provincia);
		cliente.getProvincia().setEstado(true);
		cliente.getUsuario().setUsuario(Usuario);
		cliente.getUsuario().setContraseña(Contraseña);
		cliente.getUsuario().setEstado(true);
		cliente.setEstado(true);
		String dni=cliente.getDni();
		String Usuarioo=cliente.getUsuario().getUsuario();
		dniverificar=negocioPersona.VerificarDni(dni);
		Usu=negocioPersona.VerificarUsuario(Usuarioo);
		
		if(dniverificar.isEmpty()) {
			
		} else {
			c++;
		}
		if(Usu.isEmpty()) {
			
		}else {
			c++;
		}
		System.out.println(c);
		
		if (c==0) {
			boolean estado= negocioPersona.agregarPersona(cliente);
			cartel="Cliente agregado Correctamente";
		} else {
			cartel="Usuario Repetido o dni repetido";
			
		}

		MV.addObject("estadoAgregarPersona",cartel);
		MV.setViewName("Alta_Cliente");
		
		return MV;
	}
	//anda con la modalidad de sexo
	@RequestMapping("verCliente.html")
	public ModelAndView eventovercliente()
	{
		ModelAndView MV = new ModelAndView();
		ArrayList<Cliente> ListaClientes= new ArrayList<Cliente>();

		ListaClientes =(ArrayList<Cliente>) negocioPersona.listarPersonas();
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
		cli.getProvincia().setEstado(false);
		cli.getNacionalidad().setEstado(false);
		cli.getLocalidad().setEstado(false);
		cli.getUsuario().setEstado(false);
		System.out.println(cli.toString());
		negocioPersona.EliminarPersona(cli);
		
		
		ArrayList<Cuenta> Listatodascuentas= new ArrayList<Cuenta>();
		Listatodascuentas = (ArrayList<Cuenta>) negocioCuenta.BuscarTODASCuenta(id);
		
		for(Cuenta obj2 : Listatodascuentas) {
			obj2.setEstado(false);
			obj2.getTipoCuenta().setEstado(false);
			negocioCuenta.Eliminar1Cuenta(obj2);
			
		}
	 	ArrayList<Cliente> ListaClientes= new ArrayList<Cliente>();			
		ListaClientes = (ArrayList<Cliente>) negocioPersona.listarPersonas();
		MV.addObject("ListaClientes",ListaClientes);
		String EstadoeliminarCliente="El cliente "+cli.getNombre()+" "+cli.getApellido() +" con dni "+cli.getDni()+" se elimino correctamente";
		MV.addObject("EstadoeliminarCliente",EstadoeliminarCliente);
		MV.setViewName("Ver_Clientes");
		return MV;
	}
	
//anda
	@RequestMapping("/Editar.html")
	public ModelAndView eventoeditar(HttpServletRequest request)
	{
		ModelAndView MV = new ModelAndView();
		String id=request.getParameter("id");
		java.util.List Dni = null;
	
		Cliente cli=negocioPersona.BuscarPersonaDni(id);
		
		ArrayList<Cliente> ListaClientes= new ArrayList<Cliente>();
		ListaClientes.add(cli);
		MV.addObject("ListaClientes",ListaClientes);
		
		MV.setViewName("Editar_Cliente");
		return MV;
	}	
	//anda
	@RequestMapping("ActualizarCliente.html")
	public ModelAndView EventoActualizarCliente(String nombre,String apellido,String Sexo,String dni,String date, String nacionalidad, String Provincia,String Localidad,String usuario,String contraseña)
	{
		ModelAndView MV = new ModelAndView();
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.getSexo().setDni(dni);
		cliente.getSexo().setGenero(Sexo);
		cliente.setDni(dni);
		cliente.setNacimiento(date);
		cliente.getNacionalidad().setDni(dni);
		cliente.getNacionalidad().setNacionalidad(nacionalidad);
		cliente.getNacionalidad().setEstado(true);
		cliente.getProvincia().setDni(dni);
		cliente.getProvincia().setNombre(Provincia);
		cliente.getProvincia().setEstado(true);
		cliente.getLocalidad().setDni(dni);
		cliente.getLocalidad().setNombre(Localidad);
		cliente.getLocalidad().setEstado(true);
		cliente.getUsuario().setUsuario(usuario);
		cliente.getUsuario().setContraseña(contraseña);
		cliente.setEstado(true);
		
		negocioPersona.EditarPersona(cliente);
		ArrayList<Cliente> ListaClientes= new ArrayList<Cliente>();			
		ListaClientes = (ArrayList<Cliente>) negocioPersona.listarPersonas();
		MV.addObject("ListaClientes",ListaClientes);
		String EstadoActualizarCliente="El cliente "+nombre+" "+apellido +" con dni "+dni+" se actualizo";
		MV.addObject("EstadoActualizarCliente",EstadoActualizarCliente);
		MV.setViewName("Ver_Clientes");
		return MV;
	}
//anda
	@RequestMapping("/EditarCuentas.html")
	public ModelAndView EventoeditarCuentas(HttpServletRequest request)
	{
		ModelAndView MV = new ModelAndView();
		String id=request.getParameter("id");
		
		String[] parts = id.split(",");
		String Dnii = parts[0]; 
		int numCuenta =Integer.parseInt(parts[1]);	
		Cuenta cuenta=negocioCuenta.BuscarCuentaDni(Dnii, numCuenta);		
		ArrayList<Cuenta> ListaCuentas= new ArrayList<Cuenta>();
		ListaCuentas.add(cuenta);
		MV.addObject("ListaCuentas",ListaCuentas);		
		MV.setViewName("Editar_Cuenta");
		return MV;
	}
	//anda
	@RequestMapping("ActualizarCuenta.html")
	public ModelAndView EventoActualizarCuenta(String dni,String TipoCuenta,int nrocuenta,int cbu,String fechaCreacion, float saldo)
	{
		ModelAndView MV = new ModelAndView();
		
		Cliente cli=negocioPersona.BuscarPersonaDni(dni);
		cuenta.setCliente(cli);
		cuenta.getTipoCuenta().setNombre(TipoCuenta);
		cuenta.getTipoCuenta().setEstado(true);
		cuenta.setNumCuenta(nrocuenta);
		cuenta.setCbu(cbu);
		cuenta.setFecha(fechaCreacion);
		cuenta.setSaldo(saldo);
		cuenta.setEstado(true);
		negocioCuenta.Editarcuenta(cuenta);
		DaoCuenta DAOCuenta = new DaoCuenta();
		ArrayList<Cuenta> ListaCuentas= new ArrayList<Cuenta>();
	
		ListaCuentas = (ArrayList<Cuenta>)BDaoCuenta.listarCuentas();
		MV.addObject("ListaCuentas",ListaCuentas);
		String estadoEditarCuenta="la cuenta nro "+nrocuenta+" y el Dni: "+dni+" se actualizaron correctamente";
		MV.addObject("estadoEditarCuenta",estadoEditarCuenta);
		MV.setViewName("Ver_Cuentas");
		return MV;
	}
		
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
	public ModelAndView EventoAsociarCuenta(String dni, String fechaCreacion, String tipoCuenta, int cbu, float saldo)
	{
		/*
		//para mi seria que si o si tenga que poner el nro de cuenta, luego verificar si ese cbu existe con el estado true
			//luego verificar si existe el nro de cuenta pero con el estado true, si existe no lo agrega ni lo modifica
			//luego verificar si existe pero con el estado 0, si existe haces un set, sino le haces un new
			*/	
		
		String CbuExistente="El CBU Existe";
		List<Object[]> CbuVerificar=null;
		CbuVerificar=BDaoCuenta.Verificarcbu(cbu);
		
		if(!CbuVerificar.isEmpty()) {
			ModelAndView MV = new ModelAndView();
			String Dni =dni; 
			MV.addObject("dni", Dni);
			MV.addObject("CbuExistente", CbuExistente);
			MV.setViewName("Altas_Cuentas");	
			return MV;
		}
	
		Cliente cli=negocioPersona.BuscarPersonaDni(dni);

			cuenta.setCliente(cli);
			cuenta.setCbu(cbu);	
			cuenta.setFecha(fechaCreacion);
			cuenta.setSaldo(saldo);
			cuenta.getTipoCuenta().setNumCuenta(0);
			cuenta.getTipoCuenta().setNombre(tipoCuenta);
			cuenta.getTipoCuenta().setEstado(true);
			cuenta.setEstado(true);
			BDaoCuenta.AgregarCuenta(cuenta);
	
			ModelAndView MV = new ModelAndView();
			String AgregoCorrectamente="La cuenta se agrego Correctamente";
			MV.addObject("estadoAgregarCuenta", AgregoCorrectamente);
			ArrayList<Cuenta> ListaCuentas= new ArrayList<Cuenta>();
			ListaCuentas = (ArrayList<Cuenta>)BDaoCuenta.listarCuentas() ;
			MV.addObject("ListaCuentas",ListaCuentas);
			MV.setViewName("Ver_Cuentas");	
			return MV;
			

		
	}
	//anda
	@RequestMapping("/EliminarCuenta.html")
	public ModelAndView EventoEliminarCuenta(HttpServletRequest request)
	{	DaoCuenta DAOCuenta = new DaoCuenta();
		Cuenta cuenta = new Cuenta();
		ModelAndView MV = new ModelAndView();	
		String Dni=request.getParameter("id");			
		String[] parts = Dni.split(",");
		String Dnii = parts[0]; 
		int numCuenta =Integer.parseInt(parts[1]);
		
		cuenta=negocioCuenta.BuscarCuentaDni(Dnii,numCuenta);
	
		cuenta.setEstado(false);
		cuenta.getTipoCuenta().setEstado(false);
		negocioCuenta.Eliminar1Cuenta(cuenta);
	
		ArrayList<Cuenta> ListaCuentas= new ArrayList<Cuenta>();
		ListaCuentas = (ArrayList<Cuenta>)BDaoCuenta.listarCuentas() ;
		String EliminoCorrectamente="La cuenta nro: "+numCuenta+" y dni: "+Dnii+" se elimino correctamente";
		MV.addObject("estadoeliminarCuenta", EliminoCorrectamente);
		MV.addObject("ListaCuentas",ListaCuentas);
		MV.setViewName("Ver_Cuentas");
		return MV;
	}
	
	
	//anda
	@RequestMapping("verCuenta.html")
	public ModelAndView eventovercuentas()
	{
		ModelAndView MV = new ModelAndView();
		ArrayList<Cuenta> ListaCuentas= new ArrayList<Cuenta>();
	
		
		ListaCuentas = (ArrayList<Cuenta>)BDaoCuenta.listarCuentas() ;
		MV.addObject("ListaCuentas",ListaCuentas);
		MV.setViewName("Ver_Cuentas");
		return MV;
	}
	
	
}
