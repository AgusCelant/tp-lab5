package banco.utn.dao;
import java.util.ArrayList;
import java.util.List;
import banco.utn.entidad.ClientesxCuentas;
import banco.utn.entidad.Cuenta;
public interface InterfazDaoCuenta {
	public List<Cuenta> obtenerCuentasDeUsuario (String dni);
	public void asociarCuenta (String dni, String nroCuenta, String fechaCreacion, String tipoCuenta, String cbu, String saldo);
	public boolean AgregarCuenta(Cuenta c);
	public boolean Editarcuenta(Cuenta c);
	public boolean EditarcuentaxClientes(ClientesxCuentas cxc);
	public boolean agregarClientesxcuentas(ClientesxCuentas c);
	public int ContadordeCuentasxclientes();
	public List<Cuenta> listarCuentas();
	public Cuenta obtenerCuentaPorNroCuenta (int nroCuenta);
	public Cuenta obtenerCuentaPorCbu (int cbu);
	public void actualizarCuentas(Cuenta cuentaOrigen, Cuenta cuentaDestino, float monto);
	public List<Object[]> Verificarcbu(int Cbu);
	public List<Object[]> CuentaVerificar(int nroCuenta,String dni);
	public List<Object[]> CuentaVerificarEstado0(int nroCuenta,String dni);
	public List<Integer> ObtenerPorcentajedeCuentasPesos();
	public List<Integer> ObtenerPorcentajedeCuentasDolar();
	public Cuenta BuscarCuentaDni(String Dni, int numCuenta);
	public ClientesxCuentas BuscarCuentaxCliente(String Dni,int NumCuenta);
	public ArrayList<ClientesxCuentas> BuscarTODASCuentaxCliente(String Dni);
	public ArrayList<Cuenta> BuscarTODASCuenta(String Dni);
	public boolean Eliminar1Cuenta(Cuenta cuenta);
	public boolean Eliminar1Cuentaxcliente(ClientesxCuentas cli);
}
