package banco.utn.entidad;

public class Cuenta {
	private int IdCuenta;
	private Cliente Persona;
	private String Fecha;
	private String TipoCuenta;
	private int NumCuenta;
	private int Cbu;
	private float Saldo;
	
	
	
	
	
	public Cuenta(int idCuenta, banco.utn.entidad.Cliente persona, String fecha, String tipoCuenta, int numCuenta,
			int cbu, float saldo) {
		super();
		IdCuenta = idCuenta;
		Persona = persona;
		Fecha = fecha;
		TipoCuenta = tipoCuenta;
		NumCuenta = numCuenta;
		Cbu = cbu;
		Saldo = saldo;
	}

	public Cuenta() {
		
	}
	public int getIdCuenta() {
		return IdCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		IdCuenta = idCuenta;
	}
	public Cliente getPersona() {
		return Persona;
	}
	public void setPersona(Cliente persona) {
		Persona = persona;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public String getTipoCuenta() {
		return TipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		TipoCuenta = tipoCuenta;
	}
	public int getNumCuenta() {
		return NumCuenta;
	}
	public void setNumCuenta(int numCuenta) {
		NumCuenta = numCuenta;
	}
	public int getCbu() {
		return Cbu;
	}
	public void setCbu(int cbu) {
		Cbu = cbu;
	}
	public float getSaldo() {
		return Saldo;
	}
	public void setSaldo(float saldo) {
		Saldo = saldo;
	}
	@Override
	public String toString() {
		return "Cuenta [IdCuenta=" + IdCuenta + ", Persona=" + Persona + ", Fecha=" + Fecha + ", TipoCuenta="
				+ TipoCuenta + ", NumCuenta=" + NumCuenta + ", Cbu=" + Cbu + ", Saldo=" + Saldo + "]";
	}
	
	
	
	
}
