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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import banco.utn.dao.Conexion;
import banco.utn.dao.DaoCuenta;
import banco.utn.dao.DaoPersona;
import banco.utn.entidad.Cliente;
import banco.utn.entidad.ClientesxCuentas;
import banco.utn.entidad.Cuenta;
import banco.utn.negocio.NegCuentas;
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
	@Autowired
	private DaoCuenta DaoCuen;
	@Autowired
	private NegCuentas negocioCuentas;
	
	@RequestMapping("mainCliente.html")
	public ModelAndView eventoRedireccionarMainCliente(HttpServletRequest request) {
		ModelAndView MV = new ModelAndView();
		DaoCuenta DAOCuenta = new DaoCuenta();
		DaoPersona DAOPersona = new DaoPersona();
		String Dnii=(String) request.getSession().getAttribute("Dni");
		String Clientelogueado=(String) request.getSession().getAttribute("Clienteelogueado");
		List<Cuenta> cuentas = DAOCuenta.obtenerCuentasDeUsuario(Dnii);
		Cliente cliente = DAOPersona.obtenerDatosDeUsuario(Clientelogueado);
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
		DaoCuenta DAOCuenta = new DaoCuenta();
		List<Integer> cuentaspesos = DAOCuenta.ObtenerPorcentajedeCuentasPesos();			
		List<Integer> cuentasdolar = DAOCuenta.ObtenerPorcentajedeCuentasDolar();		
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
		ArrayList<ClientesxCuentas> ListaClientesxcuentas= new ArrayList<ClientesxCuentas>();
		ListaClientesxcuentas = (ArrayList<ClientesxCuentas>) negocioCuentas.BuscarTODASCuentaxCliente(id);
		
		for(ClientesxCuentas obj : ListaClientesxcuentas) {
			obj.setEstado(false);
			negocioCuentas.Eliminar1Cuentaxcliente(obj);
			
		}
		
		ArrayList<Cuenta> Listatodascuentas= new ArrayList<Cuenta>();
		Listatodascuentas = (ArrayList<Cuenta>) negocioCuentas.BuscarTODASCuenta(id);
		
		for(Cuenta obj2 : Listatodascuentas) {
			obj2.setEstado(false);
			negocioCuentas.Eliminar1Cuenta(obj2);
			
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
		
		Cliente cli= new Cliente();
		cli.setNombre(nombre);
		cli.setApellido(apellido);
		cli.setSexo(Sexo);
		cli.setDni(dni);
		cli.setNacimiento(date);
		cli.setNacionalidad(nacionalidad);
		cli.setProvincia(Provincia);
		cli.setLocalidad(Localidad);
		cli.setUsuario(usuario);
		cli.setContraseña(contraseña);
		cli.setEstado(true);
;
		
		
		negocioPersona.EditarPersona(cli);
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
	
		Cuenta Cuen=negocioCuentas.BuscarCuentaDni(Dnii, numCuenta);
		
		ArrayList<Cuenta> ListaCuentas= new ArrayList<Cuenta>();
		ListaCuentas.add(Cuen);
		MV.addObject("ListaCuentas",ListaCuentas);		
		MV.setViewName("Editar_Cuenta");
		return MV;
	}
	//anda
	@RequestMapping("ActualizarCuenta.html")
	public ModelAndView EventoActualizarCuenta(String dni,String TipoCuenta,int nrocuenta,int cbu,String fechaCreacion, float saldo)
	{
		ModelAndView MV = new ModelAndView();
		
		Cuenta cuen= new Cuenta();
		cuen.setDni(dni);
		cuen.setTipoCuenta(TipoCuenta);
		System.out.println(cuen.getTipoCuenta()+"asdasa");
		cuen.setNumCuenta(nrocuenta);
		cuen.setCbu(cbu);
		cuen.setFecha(fechaCreacion);
		cuen.setSaldo(saldo);
		cuen.setEstado(true);
		negocioCuentas.Editarcuenta(cuen);
		DaoCuenta DAOCuenta = new DaoCuenta();
		ArrayList<Cuenta> ListaCuentas= new ArrayList<Cuenta>();
	
		ListaCuentas = (ArrayList<Cuenta>)DAOCuenta.listarCuentas();
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
		String CuentaExistente="La cuenta ya fue creada";
		DaoCuenta DAOCuenta = new DaoCuenta();
		
		List<Object[]> CbuVerificar=null;
		List<Object[]> CuentaVerificar=null;
		List<Object[]> CuentaVerificarEstado0=null;
		CbuVerificar=DAOCuenta.Verificarcbu(cbu);
		// CuentaVerificar=DAOCuenta.CuentaVerificar(nroCuenta, dni);
		// CuentaVerificarEstado0=DAOCuenta.CuentaVerificarEstado0(nroCuenta, dni);
		C=new ClientesxCuentas();
		C.setDni(dni);
		C.setEstado(true);
		//System.out.println(C.toString());
		DAOCuenta.agregarClientesxcuentas(C);
		if(!CbuVerificar.isEmpty()) {
			ModelAndView MV = new ModelAndView();
			String Dni =dni; 
			MV.addObject("dni", Dni);
			MV.addObject("CbuExistente", CbuExistente);
			MV.setViewName("Altas_Cuentas");	
			return MV;
		}
		
		/*if(!CuentaVerificar.isEmpty()) {
			ModelAndView MV = new ModelAndView();
			String Dni =dni; 
			MV.addObject("dni", Dni);
			MV.addObject("Cuentayaexiste", CuentaExistente);
			MV.setViewName("Altas_Cuentas");	
			return MV;
		}*/
		
		//if(CuentaVerificarEstado0.isEmpty()) {
			Cuen.setDni(dni);
			//Cuen.setNumCuenta(nroCuenta);
			Cuen.setCbu(cbu);	
			Cuen.setFecha(fechaCreacion);
			Cuen.setSaldo(saldo);
			Cuen.setTipoCuenta(tipoCuenta);
			Cuen.setEstado(true);
			System.out.println(Cuen.toString()+"cuentaas");
			DAOCuenta.AgregarCuenta(Cuen);
			
			//C.setIdCuenta(nroCuenta);
			
			ModelAndView MV = new ModelAndView();
			String AgregoCorrectamente="La cuenta se agrego Correctamente";
			MV.addObject("estadoAgregarCuenta", AgregoCorrectamente);
			ArrayList<Cuenta> ListaCuentas= new ArrayList<Cuenta>();
			ListaCuentas = (ArrayList<Cuenta>)DAOCuenta.listarCuentas() ;
			MV.addObject("ListaCuentas",ListaCuentas);
			MV.setViewName("Ver_Cuentas");	
			return MV;
			
		/*} else {
			System.out.println("Entro a modificar");
			Cuen.setDni(dni);
			//Cuen.setNumCuenta(nroCuenta);
			Cuen.setCbu(cbu);	
			Cuen.setFecha(fechaCreacion);
			Cuen.setSaldo(saldo);
			Cuen.setTipoCuenta(tipoCuenta);
			Cuen.setEstado(true);
			DAOCuenta.Editarcuenta(Cuen);
			C.setDni(dni);
			//C.setIdCuenta(nroCuenta);
			C.setEstado(true);
			DAOCuenta.EditarcuentaxClientes(C);
			ModelAndView MV = new ModelAndView();
			String AgregoCorrectamente="La cuenta se agrego Correctamente";
			MV.addObject("estadoAgregarCuenta", AgregoCorrectamente);
			MV.setViewName("Altas_Cuentas");	
			return MV;
		}*/

		
	}
	//anda
	@RequestMapping("/EliminarCuenta.html")
	public ModelAndView EventoEliminarCuenta(HttpServletRequest request)
	{	DaoCuenta DAOCuenta = new DaoCuenta();
		Cuenta cuenta = new Cuenta();
		ClientesxCuentas cli = new ClientesxCuentas();
		ModelAndView MV = new ModelAndView();	
		String Dni=request.getParameter("id");	
		
		String[] parts = Dni.split(",");
		String Dnii = parts[0]; 
		int numCuenta =Integer.parseInt(parts[1]);
				 
		
		cuenta=negocioCuentas.BuscarCuentaDni(Dnii,numCuenta);
		cli=negocioCuentas.BuscarCuentaxCliente(Dnii,numCuenta); 
	
		cuenta.setEstado(false);
		cli.setEstado(false);
		negocioCuentas.Eliminar1Cuentaxcliente(cli);
		negocioCuentas.Eliminar1Cuenta(cuenta);


		
	
		ArrayList<Cuenta> ListaCuentas= new ArrayList<Cuenta>();
		ListaCuentas = (ArrayList<Cuenta>)DAOCuenta.listarCuentas() ;
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
		DaoCuenta DAOCuenta = new DaoCuenta();
		ModelAndView MV = new ModelAndView();
		ArrayList<Cuenta> ListaCuentas= new ArrayList<Cuenta>();
	
		
		ListaCuentas = (ArrayList<Cuenta>)DAOCuenta.listarCuentas() ;
		MV.addObject("ListaCuentas",ListaCuentas);
		MV.setViewName("Ver_Cuentas");
		return MV;
	}
	
	
}
