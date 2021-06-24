package banco.utn.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banco.utn.dao.DaoPersona;
import banco.utn.entidad.Cliente;

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

}
