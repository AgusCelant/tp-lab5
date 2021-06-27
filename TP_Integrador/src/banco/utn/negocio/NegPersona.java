package banco.utn.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banco.utn.dao.DaoPersona;
import banco.utn.entidad.Cliente;
import banco.utn.entidad.ClientesxCuentas;
import banco.utn.entidad.Cuenta;
import banco.utn.entidad.Historial;

@Service("servicioPersona")
public class NegPersona {

	@Autowired
	private DaoPersona daoPersona;
	
	public List<Cliente> listarPersonas() {
		return daoPersona.listarPersonas();
	}

	public boolean agregarPersona(Cliente p) {
		return daoPersona.agregarPersona(p);
	}
	public List BuscarPersonaID(String id) {
		
		return daoPersona.BuscarPersonaID(id);
	}
	
	
	
public boolean EliminarPersona(Cliente cliente) {
		
		return daoPersona.EliminarPersona(cliente);
	}

public List VerificarDni(String Dni) {
	
	return  daoPersona.VerificarDni(Dni);
}

public List VerificarUsuario(String Usuario) {
	
	return  daoPersona.VerificarUsuario(Usuario);
}

public List VerificarLogin(String Usuario,String Contraseņa) {
	
	return  daoPersona.VerificarLogin(Usuario,Contraseņa);
}


/*
 * 
 * Cuentass
 * 
 */



public ArrayList<Cliente> TraerClientes() {
	
	return  daoPersona.TraerClientes();
}


public List<Cuenta> listarCuentas() {
	
return  daoPersona.listarCuentas();
}

public boolean agregarClientesxcuentas(ClientesxCuentas p) {
	return daoPersona.agregarClientesxcuentas(p);
}

public Cuenta BuscarCuentaDni(String Dni) {
	
	return daoPersona.BuscarCuentaDni(Dni);
}

public ClientesxCuentas BuscarCuentaxCliente(String Dni) {
	
	return daoPersona.BuscarCuentaxCliente(Dni);
}

public boolean EliminarCuenta(Cuenta cuenta) {
	
	return daoPersona.EliminarCuenta(cuenta);
}
public boolean Eliminar1Cuenta(Cuenta cuenta) {
	
	return daoPersona.Eliminar1Cuenta(cuenta);
}
public boolean EliminarCuentaxcliente(ClientesxCuentas cli) {
	
	return daoPersona.EliminarCuentaxcliente(cli);
}
public boolean Eliminar1Cuentaxcliente(ClientesxCuentas cli) {
	
	return daoPersona.Eliminar1Cuentaxcliente(cli);
}
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
