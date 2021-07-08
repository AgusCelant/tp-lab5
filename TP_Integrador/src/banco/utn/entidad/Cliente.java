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
	@OneToOne(cascade= {CascadeType.ALL}) 
	@JoinColumn(name="Nacionalidad")
	private Nacionalidad Nacionalidad = new Nacionalidad();
	@OneToOne(cascade= {CascadeType.ALL}) 
	@JoinColumn(name="Provincia")
	private Provincia Provincia= new Provincia();
	@OneToOne(cascade= {CascadeType.ALL}) 
	@JoinColumn(name="Localidad")
	private Localidad Localidad = new Localidad();
	@OneToOne(cascade= {CascadeType.ALL}) 
	@JoinColumn(name="Usuario")
	private Usuario Usuario= new Usuario();
	@Column(name="Estado")
	private Boolean Estado;
	@Id
	@Column(name="DNI")
	private String Dni;
	
	public Cliente() {}



	public Cliente(String nombre, String apellido, Generos sexo, String nacimiento, Nacionalidad nacionalidad,
			Provincia provincia, Localidad localidad, Usuario usuario, Boolean estado, String dni) {
		super();
		Nombre = nombre;
		Apellido = apellido;
		this.sexo = sexo;
		Nacimiento = nacimiento;
		Nacionalidad = nacionalidad;
		Provincia = provincia;
		Localidad = localidad;
		Usuario = usuario;
		Estado = estado;
		Dni = dni;
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
		this.sexo = sexo;
	}

	public String getNacimiento() {
		return Nacimiento;
	}

	public void setNacimiento(String nacimiento) {
		Nacimiento = nacimiento;
	}

	public Nacionalidad getNacionalidad() {
		return Nacionalidad;
	}

	public void setNacionalidad(Nacionalidad nacionalidad) {
		Nacionalidad = nacionalidad;
	}

	public Provincia getProvincia() {
		return Provincia;
	}

	public void setProvincia(Provincia provincia) {
		Provincia = provincia;
	}

	public Localidad getLocalidad() {
		return Localidad;
	}

	public void setLocalidad(Localidad localidad) {
		Localidad = localidad;
	}

	
	
	
	public Usuario getUsuario() {
		return Usuario;
	}



	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
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