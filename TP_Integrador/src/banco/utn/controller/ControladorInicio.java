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
			//arreglar
			List<Integer> cuentaspesos = DAOCuenta.ObtenerPorcentajedeCuentasPesos();			
			
	
		
		
			int cuenta1=0;
			int cuenta2=0;
			int cuenta3=0;
			int cuenta4=0;
			if(cuentaspesos.size()==0) {			
				cuenta1=0;
				cuenta2=0;
				cuenta3=0;
				cuenta4=0;
				MV2.addObject("Cuenta1", cuenta1);
				MV2.addObject("Cuenta2", cuenta2);
				MV2.addObject("Cuenta3", cuenta3);
				MV2.addObject("Cuenta4", cuenta4);
			}
			if(cuentaspesos.size()==4) {
				MV2.addObject("Cuenta1", cuentaspesos.get(0));
				MV2.addObject("Cuenta2", cuentaspesos.get(1));
				MV2.addObject("Cuenta3", cuentaspesos.get(2));
				MV2.addObject("Cuenta4", cuentaspesos.get(3));						
			} else {
				if(cuentaspesos.size()==3) {					
					MV2.addObject("Cuenta1", cuentaspesos.get(0));
					MV2.addObject("Cuenta2", cuentaspesos.get(1));
					MV2.addObject("Cuenta3", cuentaspesos.get(2));
					MV2.addObject("Cuenta4", cuenta4);		
				} else {
					if(cuentaspesos.size()==2) {
						MV2.addObject("Cuenta1", cuentaspesos.get(0));
						MV2.addObject("Cuenta2", cuentaspesos.get(1));
						MV2.addObject("Cuenta3", cuenta3);
						MV2.addObject("Cuenta4", cuenta4);	
					} else {
						if(cuentaspesos.size()==1) {
							MV2.addObject("Cuenta1", cuentaspesos.get(0));
							MV2.addObject("Cuenta2", cuenta2);
							MV2.addObject("Cuenta3", cuenta3);
							MV2.addObject("Cuenta4", cuenta4);	
						}
					}
				}
			}
			List<Integer> cuentasdolar = NegCuentas.ObtenerPorcentajedeCuentasDolar();
			
			int cuenta1d=0;
			int cuenta2d=0;
			int cuenta3d=0;
			int cuenta4d=0;
			if(cuentasdolar.size()==0) {
				cuenta1d=0;
				cuenta2d=0;
				cuenta3d=0;
				cuenta4d=0;
				MV2.addObject("Cuenta1d", cuenta1d);
				MV2.addObject("Cuenta2d", cuenta2d);
				MV2.addObject("Cuenta3d", cuenta3d);
				MV2.addObject("Cuenta4d", cuenta4d);
			}
			if(cuentasdolar.size()==4) {
				MV2.addObject("Cuenta1d", cuentaspesos.get(0));
				MV2.addObject("Cuenta2d", cuentaspesos.get(1));
				MV2.addObject("Cuenta3d", cuentaspesos.get(2));
				MV2.addObject("Cuenta4d", cuentaspesos.get(3));						
			}else {
				if(cuentasdolar.size()==3) {					
					MV2.addObject("Cuenta1d", cuentaspesos.get(0));
					MV2.addObject("Cuenta2d", cuentaspesos.get(1));
					MV2.addObject("Cuenta3d", cuentaspesos.get(2));
					MV2.addObject("Cuenta4d", cuenta4d);		
					
				}else {
					if(cuentasdolar.size()==2) {
						
						MV2.addObject("Cuenta1d", cuentaspesos.get(0));
						MV2.addObject("Cuenta2d", cuentaspesos.get(1));
						MV2.addObject("Cuenta3d", cuenta3d);
						MV2.addObject("Cuenta4d", cuenta4d);	
						
					}
					else {
						if(cuentasdolar.size()==1) {
							
							MV2.addObject("Cuenta1d", cuentaspesos.get(0));
							MV2.addObject("Cuenta2d", cuenta2d);
							MV2.addObject("Cuenta3d", cuenta3d);
							MV2.addObject("Cuenta4d", cuenta4d);	
						}
						
						
					}
					
				}
				
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