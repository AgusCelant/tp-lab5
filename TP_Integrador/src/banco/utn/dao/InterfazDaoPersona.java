package banco.utn.dao;
import java.util.ArrayList;
import java.util.List;
import banco.utn.dao.DaoPersona;
import banco.utn.entidad.Cliente;
public interface InterfazDaoPersona {
	public List<Cliente> listarPersonas();
	public boolean agregarPersona(Cliente p);
	public Cliente BuscarPersonaDni(String Dni);
	public boolean EliminarPersona(Cliente cliente);
	public boolean EditarPersona(Cliente p);
	public List<Object[]> VerificarDni(String Dni);
	public List<Object[]> VerificarUsuario(String Usuarioo);
	public List<Object[]> VerificarLogin(String Usuario,String Contraseña);
	public Cliente obtenerDatosDeUsuario (String usuario);
	public ArrayList<Cliente> TraerClientes();
}
