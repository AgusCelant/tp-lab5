package banco.utn.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import banco.utn.dao.Conexion;
import banco.utn.dao.DaoCuenta;
import banco.utn.dao.DaoPersona;
import banco.utn.entidad.Cliente;
import banco.utn.entidad.Cuenta;

@Controller
public class ControladorInicio {
	
	@RequestMapping("inicioLogin.html")
	public ModelAndView Login() 
	{
		/*Conexion DAO = new Conexion();
		Cuenta cuenta1= new Cuenta("26/06/21", "Dolar", 0001, 10500, true);
		Cuenta cuenta2= new Cuenta("27/06/21", "Peso", 0002, 10000, true);
		Session session = DAO.abrirConexion();
		session.beginTransaction();
		session.save(cuenta1);
		session.save(cuenta2);
		session.getTransaction().commit();
		DAO.cerrarSession();*/
		
		ModelAndView MV = new ModelAndView();
		MV.setViewName("Ver_Cuentas");
		return MV;
	}
	
	@RequestMapping("login.html")
	public ModelAndView ingresar(String txtUsuario, String txtPass)
	{
		ModelAndView MV = new ModelAndView();

		if(txtUsuario.equals("admin") && txtPass.equals("admin")){
			MV.setViewName("PerfilAdmin");
		}			
		else {
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
		}
		return MV;
	}
}