package banco.utn.entidad;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="Usuario")
public class Usuario implements Serializable {
private static final long serialVersionUID = 1L;

	@Id
	private String Usuario;
	private String Contraseña;
	private boolean Estado;	
	
public Usuario()
{}

public String getUsuario() {
	return Usuario;
}

public void setUsuario(String usuario) {
	Usuario = usuario;
}

public String getContraseña() {
	return Contraseña;
}

public void setContraseña(String contraseña) {
	Contraseña = contraseña;
}



public boolean isEstado() {
	return Estado;
}

public void setEstado(boolean estado) {
	Estado = estado;
}

@Override
public String toString() {
	return "Usuario [Usuario=" + Usuario + ", Contraseña=" + Contraseña + ", Estado=" + Estado + "]";
}





}
