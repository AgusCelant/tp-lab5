package banco.utn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import banco.utn.dao.DaoPersona;
import banco.utn.negocio.NegPersona;
import banco.utn.entidad.Cliente;

@Controller
public class ControladorInicio {
	@Autowired
	@Qualifier("servicioPersona")
	private NegPersona negocioPersona;
	
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
		java.util.List Verificacion = null;
		
		
		if(txtUsuario.equals("admin") && txtPass.equals("admin")){
			
			MV.setViewName("PerfilAdmin");
		}			
			Verificacion=negocioPersona.VerificarLogin(txtUsuario, txtPass);
			
			if(Verificacion!=null) {
				System.out.println(Verificacion);
				MV.setViewName("mainCliente");
			}else {
				MV.setViewName("Login");
			
			}
			
		
		
		return MV;
	}
}