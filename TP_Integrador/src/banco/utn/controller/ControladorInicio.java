package banco.utn.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import banco.utn.dao.*;
import banco.utn.entidad.Cliente;
import banco.utn.entidad.Cuenta;
import banco.utn.negocio.NegPersona;
import banco.utn.resources.BeanDao;
import banco.utn.resources.BeansCliente;
import banco.utn.resources.BeansConexion;

@Controller
public class ControladorInicio {
	private ApplicationContext appContext = null;
	@Autowired
	private DaoCuenta BDaoCuenta;
	@Autowired
	private DaoPersona BDaoPersona;
	@Autowired
	private NegPersona negocioPersona;
	
	@RequestMapping("inicioLogin.html")
	public ModelAndView Login() 
	{
		ModelAndView MV = new ModelAndView();
		MV.setViewName("Login");
		return MV;
	}
	
	@RequestMapping("login.html")
	public ModelAndView ingresar(String txtUsuario, String txtPass,HttpServletRequest request)
	{
		ModelAndView MV = new ModelAndView();
		if (appContext == null) appContext = new AnnotationConfigApplicationContext(BeansConexion.class, BeansCliente.class, BeanDao.class);
		
		if(txtUsuario.equals("admin") && txtPass.equals("admin")){

			ModelAndView MV2 = new ModelAndView();
			// DaoCuenta BDAOCuenta = (DaoCuenta)appContext.getBean("BDaoCuenta");
			//anda
			List<Integer> cuentaspesos = BDaoCuenta.ObtenerPorcentajedeCuentasPesos();			
			List<Integer> cuentasdolar = BDaoCuenta.ObtenerPorcentajedeCuentasDolar();		
			int cuenta1=0;
			int cuenta2=0;
			if (cuentaspesos.get(0)==null) {				
				MV2.addObject("Cuenta1", cuenta1);
			} else {
				MV2.addObject("Cuenta1", cuentaspesos.get(0));
			}
			if (cuentasdolar.get(0)==null) {				
				MV2.addObject("Cuenta2", cuenta2);
			} else {
				MV2.addObject("Cuenta2", cuentasdolar.get(0));
			}
			
			((ConfigurableApplicationContext)(appContext)).close();
			MV2.setViewName("PerfilAdmin");
			return MV2;
		} else {						
			List<Object[]> verificarlogin=null;
			
			verificarlogin=negocioPersona.VerificarLogin(txtUsuario,txtPass);
			
			if (verificarlogin.isEmpty()) {
				String cartel="El usuario o contraseña son incorrecto, intentelo de nuevo";
				MV.addObject("Errordeconexcion", cartel);
				MV.setViewName("Login");
				((ConfigurableApplicationContext)(appContext)).close();
				return MV;		
			} else {
				Cliente cliente = BDaoPersona.obtenerDatosDeUsuario(txtUsuario);
				List<Cuenta> cuentas = BDaoCuenta.obtenerCuentasDeUsuario(cliente.getDni());
				String resumenCuentas = "";
				
				for(Cuenta cuenta : cuentas) {
					resumenCuentas = "<div>Nro de caja de ahorro: <b>" + cuenta.getNumCuenta() + "</b>, Moneda: <b>" + cuenta.getTipoCuenta().getNombre() + "</b>, Saldo: <b>" + cuenta.getSaldo() + "</b></div><br>";
				}
				request.getSession().setAttribute("Usuario", cliente.getNombre());	
				request.getSession().setAttribute("Dni", cliente.getDni());
				request.getSession().setAttribute("Clienteelogueado", txtUsuario);	
				MV.addObject("clienteLogueado", cliente);
				MV.addObject("cuentasCliente", resumenCuentas);
				MV.setViewName("mainCliente");
				((ConfigurableApplicationContext)(appContext)).close();
				return MV;
			}
		}
	}
	
	@RequestMapping("logout.html")
	public ModelAndView CerrarSession()
	{
		ModelAndView MV = new ModelAndView();			
		MV.setViewName("Login");
		return MV;
	}
}