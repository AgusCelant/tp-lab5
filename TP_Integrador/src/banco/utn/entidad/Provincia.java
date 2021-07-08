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
@Table(name="Provincia")
public class Provincia implements Serializable{
private static final long serialVersionUID = 1L;

@Id
private String Dni;
private String Nombre;
private boolean Estado;	
public Provincia()
{}
public String getDni() {
	return Dni;
}
public void setDni(String dni) {
	Dni = dni;
}
public String getNombre() {
	return Nombre;
}
public void setNombre(String nombre) {
	Nombre = nombre;
}
public boolean isEstado() {
	return Estado;
}
public void setEstado(boolean estado) {
	Estado = estado;
}
@Override
public String toString() {
	return "Provincia [Dni=" + Dni + ", Nombre=" + Nombre + ", Estado=" + Estado + "]";
}



}
