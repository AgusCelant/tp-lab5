package banco.utn.negocio;
import java.util.ArrayList;
import java.util.List;
import banco.utn.dao.DaoPersona;
import banco.utn.entidad.Cliente;
public interface InterfazNegPersona {
	public List<Cliente> listarPersonas();
	public boolean agregarPersona(Cliente p);
	public Cliente BuscarPersonaDni(String id);
	public boolean EliminarPersona(Cliente cliente);
	public boolean EditarPersona(Cliente cliente);
	public List<Object[]> VerificarDni(String Dni);
	public List<Object[]> VerificarUsuario(String Usuario);
	public List<Object[]> VerificarLogin(String Usuario,String Contraseña);
	public ArrayList<Cliente> TraerClientes();
}
