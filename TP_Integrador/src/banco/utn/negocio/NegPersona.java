package banco.utn.negocio;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banco.utn.dao.Conexion;
import banco.utn.dao.DaoPersona;
import banco.utn.dao.DaoCuenta;
import banco.utn.entidad.Cliente;
import banco.utn.entidad.ClientesxCuentas;
import banco.utn.entidad.Cuenta;
import banco.utn.entidad.Historial;

@Service("servicioPersona")
public class NegPersona implements InterfazNegPersona {

	@Autowired
	private DaoPersona daoPersona = new DaoPersona();

	
	public List<Cliente> listarPersonas() {
		return daoPersona.listarPersonas();
	}

	public boolean agregarPersona(Cliente p) {
		return daoPersona.agregarPersona(p);
	}
	public Cliente BuscarPersonaDni(String id) {
		
		return daoPersona.BuscarPersonaDni(id);
	}
	
	
	
public boolean EliminarPersona(Cliente cliente) {
		
		return daoPersona.EliminarPersona(cliente);
	}

public boolean EditarPersona(Cliente cliente) {
	
	return daoPersona.EditarPersona(cliente);
}


public List<Object[]> VerificarDni(String Dni) {
	
	return  daoPersona.VerificarDni(Dni);
}

public List<Object[]> VerificarUsuario(String Usuario) {
	
	return  daoPersona.VerificarUsuario(Usuario);
}

public List<Object[]> VerificarLogin(String Usuario,String Contraseña) {
	
	return  daoPersona.VerificarLogin(Usuario,Contraseña);
}
public ArrayList<Cliente> TraerClientes() {
	return  daoPersona.TraerClientes();
}


/*
public boolean agregarClientesxcuentas(ClientesxCuentas p) {
	return daoPersona.agregarClientesxcuentas(p);
}
*/







/*
 * 
 * Historial
 * 
 */

public boolean AgregarenHistorial(Historial histo) {
	
	return daoPersona.AgregarenHistorial(histo);
}
public List<Historial> ListarHistorial() {
	
	return daoPersona.ListarHistorial();
}
public List<Historial>ListarHistorialxCuenta(int nroCuenta) {
	
	return daoPersona.ListarHistorialxCuenta( nroCuenta);
}






}
