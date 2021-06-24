package banco.utn.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Cliente")
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="Nombre")
	private String nombre;
	@Column(name="Apellido")
	private String apellido;
	@Column(name="Sexo")
	private String Sexo;
	@Column(name="Nacimiento")
	private String Nacimiento;
	@Column(name="Nacionalidad")
	private String Nacionalidad;
	@Column(name="Provincia")
	private String Provincia;
	@Column(name="Localidad")
	private String Localidad;
	@Column(name="IdCliente")
	private static int IdCliente;
	@Id
	@Column(name="DNI")
	private String dni;
	
	public Cliente()
	{
		
	}

	public Cliente(String nombre, String apellido, String sexo, String nacimiento, String nacionalidad,
			String provincia, String localidad, String dni) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		Sexo = sexo;
		Nacimiento = nacimiento;
		Nacionalidad = nacionalidad;
		Provincia = provincia;
		Localidad = localidad;
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	public static int getIdCliente() {
		return IdCliente;
	}

	public static void setIdCliente() {
		
		IdCliente = IdCliente++;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	


	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", apellido=" + apellido + ", Sexo=" + Sexo + ", Nacimiento=" + Nacimiento
				+ ", Nacionalidad=" + Nacionalidad + ", Provincia=" + Provincia + ", Localidad=" + Localidad + ", dni="
				+ dni + "]";
	}
	

	
}