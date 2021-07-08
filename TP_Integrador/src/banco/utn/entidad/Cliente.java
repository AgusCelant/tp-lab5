package banco.utn.entidad;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Cliente")
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name="Nombre")
	private String Nombre;
	@Column(name="Apellido")
	private String Apellido;
	@OneToOne(cascade= {CascadeType.ALL}) 
	@JoinColumn(name="Sexo")
	private Generos sexo = new Generos();
	@Column(name="Nacimiento")
	private String Nacimiento;
	@Column(name="Nacionalidad")
	private String Nacionalidad;
	@Column(name="Provincia")
	private String Provincia;
	@Column(name="Localidad")
	private String Localidad;
	@Column(name="Usuario")
	private String Usuario;
	@Column(name="Contraseña")
	private String Contraseña;
	@Column(name="Estado")
	private Boolean Estado;
	@Id
	@Column(name="DNI")
	private String Dni;
	
	public Cliente() {}

	public Cliente(String nombre, String apellido, Generos sexo, String nacimiento, String nacionalidad,
			String provincia, String localidad, String dni, String usuario, String contraseña,Boolean estado) {
		super();
		Nombre = nombre;
		Apellido = apellido;
		this.sexo = sexo;
		Nacimiento = nacimiento;
		Nacionalidad = nacionalidad;
		Provincia = provincia;
		Localidad = localidad;
		Usuario = usuario;
		Contraseña = contraseña;
		Dni = dni;
		Estado = estado;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		this.Apellido = apellido;
	}

	
	public Generos getSexo() {
		return sexo;
	}

	public void setSexo(Generos sexo) {
		sexo = sexo;
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
		this.Usuario = usuario;
	}
	public String getContraseña() {
		return Contraseña;
	}

	public void setContraseña(String contraseña) {
		this.Contraseña = contraseña;
	}
	
	public Boolean getEstado() {
		return Estado;
	}

	public void setEstado(Boolean estado) {
		this.Estado = estado;
	}
	
	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		this.Dni = dni;
	}
	
	@Override
	public String toString() {
		return "Nombre: "+ Nombre + ", Apellido: " + Apellido + ", Sexo: " + sexo + ",  Nacimiento: " + Nacimiento
				+ ", Nacionalidad: " + Nacionalidad + ", Provincia: " + Provincia + ",  Localidad: " + Localidad + ",  Dni: "
				+ Dni;
	}
}