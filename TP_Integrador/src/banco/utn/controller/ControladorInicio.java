package banco.utn.controller;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import banco.utn.dao.Conexion;
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
		
		MV.setViewName("Agregar_CuentaP1");
		return MV;
	}
	
	@RequestMapping("login.html")
	public ModelAndView ingresar(String txtUsuario, String txtPass)
	{
		ModelAndView MV = new ModelAndView();
		DaoPersona lista = new DaoPersona(); 
		java.util.List Verificacion = null;
		Verificacion=negocioPersona.VerificarLogin(txtUsuario, txtPass);
		
		if(txtUsuario.equals("admin") && txtPass.equals("admin")){
			
			MV.setViewName("PerfilAdmin");
		}else if(Verificacion!=null) {
			Conexion conexion = new Conexion();
			Session session = conexion.abrirConexion();
			Cliente cliente = (Cliente) session.createQuery("SELECT c FROM Cliente c WHERE Usuario='" + txtUsuario + "'").uniqueResult();
			conexion.cerrarSession();
			System.out.println("toString" + cliente.toString());
			MV.addObject("clienteLogueado", cliente);
			MV.setViewName("mainCliente");	
		}
		else {
			MV.setViewName("Login");
		}
			
		
			
			
			
		
		
		return MV;
	}
}