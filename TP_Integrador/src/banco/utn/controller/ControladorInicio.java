package banco.utn.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import banco.utn.dao.DaoPersona;
import banco.utn.entidad.Cliente;

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
	public ModelAndView ingresar(String txtUsuario, String txtPass)
	{
		ModelAndView MV = new ModelAndView();
		DaoPersona lista = new DaoPersona(); 
		
		
		
		if(txtUsuario.equals("admin") && txtPass.equals("admin")){
			
			MV.setViewName("PerfilAdmin");
		}			
		else {
			
			MV.setViewName("mainCliente");
		}
		
		return MV;
	}
}