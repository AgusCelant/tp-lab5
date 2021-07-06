package banco.utn.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import banco.utn.dao.Conexion;
import banco.utn.dao.DaoCuenta;
import banco.utn.dao.DaoPersona;
import banco.utn.entidad.Cliente;
import banco.utn.entidad.Cuenta;
import banco.utn.negocio.NegCuentas;
import banco.utn.negocio.NegPersona;

@Controller
public class ControladorInicio {
	
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
		
		
		if(txtUsuario.equals("admin") && txtPass.equals("admin")){

			ModelAndView MV2 = new ModelAndView();
			DaoCuenta DAOCuenta = new DaoCuenta();
			//anda
			List<Integer> cuentaspesos = DAOCuenta.ObtenerPorcentajedeCuentasPesos();			
			List<Integer> cuentasdolar = DAOCuenta.ObtenerPorcentajedeCuentasDolar();		
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
			
			MV2.setViewName("PerfilAdmin");
			return MV2;
		}else {						
			List<Object[]> verificarlogin=null;
			NegPersona negocioPersona = new NegPersona();
			verificarlogin=negocioPersona.VerificarLogin(txtUsuario,txtPass);
			
			if(verificarlogin.isEmpty()) {
				String cartel="El usuario o contraseña son incorrecto, intentelo de nuevo";
			MV.addObject("Errordeconexcion", cartel);
			MV.setViewName("Login");
			return MV;		
			}else {
				DaoPersona DAOPersona = new DaoPersona();
				DaoCuenta DAOCuenta = new DaoCuenta();
				NegCuentas NegCuentas = new NegCuentas();
			
				Cliente cliente = DAOPersona.obtenerDatosDeUsuario(txtUsuario);
				List<Cuenta> cuentas = DAOCuenta.obtenerCuentasDeUsuario(cliente.getDni());
				String resumenCuentas = "";
				
				for(Cuenta cuenta : cuentas) {
					resumenCuentas = "<div>Nro de caja de ahorro: <b>" + cuenta.getNumCuenta() + "</b>, Moneda: <b>" + cuenta.getTipoCuenta() + "</b>, Saldo: <b>" + cuenta.getSaldo() + "</b></div><br>";
				}
				request.getSession().setAttribute("Usuario", cliente.getNombre());	
				request.getSession().setAttribute("Dni", cliente.getDni());
				request.getSession().setAttribute("Clienteelogueado", txtUsuario);	
				MV.addObject("clienteLogueado", cliente);
				MV.addObject("cuentasCliente", resumenCuentas);
				MV.setViewName("mainCliente");
				return MV;
			}
			
			
			
		}	
		
		/*else {
			DaoPersona DAOPersona = new DaoPersona();
			DaoCuenta DAOCuenta = new DaoCuenta();
			Cliente cliente = DAOPersona.obtenerDatosDeUsuario(txtUsuario);
			System.out.println(cliente.toString());
			List<Cuenta> cuentas = DAOCuenta.obtenerCuentasDeUsuario(cliente.getDni());
			String resumenCuentas = "";
			
			for(Cuenta cuenta : cuentas) {
				resumenCuentas = "<div>Nro de caja de ahorro: <b>" + cuenta.getNumCuenta() + "</b>, Moneda: <b>" + cuenta.getTipoCuenta() + "</b>, Saldo: <b>" + cuenta.getSaldo() + "</b></div><br>";
			}
			
			MV.addObject("clienteLogueado", cliente);
			MV.addObject("cuentasCliente", resumenCuentas);
			MV.setViewName("mainCliente");
		}*/
		
	//	MV.setViewName("mainCliente");
		//return MV;
	}
	
	
	
	private NumberFormat DecimalFormat(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping("logout.html")
	public ModelAndView CerrarSession()
	{
		ModelAndView MV = new ModelAndView();			
			MV.setViewName("Login");
		return MV;
	}
	
	
}