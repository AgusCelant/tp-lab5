package banco.utn.controller;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import banco.utn.dao.Conexion;
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
		Conexion DAO = new Conexion();
		Cliente persona = new Cliente("222",
				"password2",
				"localidad2",
				"nacimineto2",
				"nacionalidad2",
				"provincia2",
				"sexo2",
				"usuario2",
				"apellido2",
				"nombre2",
				true);
		
		Session session = DAO.abrirConexion();
		session.beginTransaction();
		session.save(persona);
		session.getTransaction().commit();
		DAO.cerrarSession();
		ModelAndView MV = new ModelAndView();

		if(txtUsuario.equals("admin") && txtPass.equals("admin")){
			MV.setViewName("PerfilAdmin");
		}			
		else {
			Conexion conexion = new Conexion();
			Session session = conexion.abrirConexion();
			Cliente cliente = (Cliente) session.createQuery("SELECT c FROM Cliente c WHERE Usuario='" + txtUsuario + "'").uniqueResult();
			conexion.cerrarSession();
			System.out.println("toString" + cliente.toString());
			MV.addObject("clienteLogueado", cliente);
			MV.setViewName("mainCliente");
		}
		return MV;
	}
}