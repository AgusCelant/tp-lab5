package banco.utn.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banco.utn.dao.DaoPersona;
import banco.utn.entidad.Cliente;
import banco.utn.entidad.ClientesxCuentas;

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


public boolean agregarClientesxcuentas(ClientesxCuentas p) {
	return daoPersona.agregarClientesxcuentas(p);
}


}
