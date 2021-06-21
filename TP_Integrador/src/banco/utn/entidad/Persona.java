package banco.utn.entidad;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Persona {

	@Id
	private int dni;
	private String Nombre;
	private String Apellido;
	private String Sexo;
	private String Nacimiento;
	private String Nacionalidad;
	private String Provincia;
	private String Localidad;
	private String Usuario;
	private String Contraseña;
	private boolean Estado;
	
	
	
	
	
	public Persona(int dni, String nombre, String apellido, String sexo, String nacimiento, String nacionalidad,
			String provincia, String localidad, String usuario, String contraseña, boolean estado) {
		super();
		this.dni = dni;
		Nombre = nombre;
		Apellido = apellido;
		Sexo = sexo;
		Nacimiento = nacimiento;
		Nacionalidad = nacionalidad;
		Provincia = provincia;
		Localidad = localidad;
		Usuario = usuario;
		Contraseña = contraseña;
		Estado = estado;
	}


	public Persona() {
	
	}


	public int getDni() {
		return dni;
	}





	public void setDni(int dni) {
		this.dni = dni;
	}





	public String getNombre() {
		return Nombre;
	}





	public void setNombre(String nombre) {
		Nombre = nombre;
	}





	public String getApellido() {
		return Apellido;
	}





	public void setApellido(String apellido) {
		Apellido = apellido;
	}





	public String getSexo() {
		return Sexo;
	}





	public void setSexo(String sexo) {
		Sexo = sexo;
	}





	public String getNacimiento() {
		return Nacimiento;
	}





	public void setNacimiento(String nacimiento) {
		Nacimiento = nacimiento;
	}





	public String getNacionalidad() {
		return Nacionalidad;
	}





	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}





	public String getProvincia() {
		return Provincia;
	}





	public void setProvincia(String provincia) {
		Provincia = provincia;
	}





	public String getLocalidad() {
		return Localidad;
	}





	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}





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
		return "Persona [dni=" + dni + ", Nombre=" + Nombre + ", Apellido=" + Apellido + ", Sexo=" + Sexo
				+ ", Nacimiento=" + Nacimiento + ", Nacionalidad=" + Nacionalidad + ", Provincia=" + Provincia
				+ ", Localidad=" + Localidad + ", Usuario=" + Usuario + ", Contraseña=" + Contraseña + ", Estado="
				+ Estado + "]";
	}
	
	
	
	
	
	
}

