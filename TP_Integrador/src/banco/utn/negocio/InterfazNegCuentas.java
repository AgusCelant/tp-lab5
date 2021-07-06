package banco.utn.negocio;
import java.util.ArrayList;
import java.util.List;
import banco.utn.dao.DaoCuenta;
import banco.utn.entidad.ClientesxCuentas;
import banco.utn.entidad.Cuenta;
public interface InterfazNegCuentas {
	public List<Cuenta> listarCuentas() ;
	public Cuenta BuscarCuentaDni(String Dni, int numCuenta);
	public ArrayList<Cuenta> BuscarTODASCuenta(String Dni);
	public ClientesxCuentas BuscarCuentaxCliente(String Dni,int NumCuenta);
	public ArrayList<ClientesxCuentas> BuscarTODASCuentaxCliente(String Dni);
	public boolean Eliminar1Cuenta(Cuenta cuenta) ;
	public boolean Eliminar1Cuentaxcliente(ClientesxCuentas cli);
	public boolean Editarcuenta(Cuenta cuenta);
	public List<Integer> ObtenerPorcentajedeCuentasDolar();
	public List<Integer> ObtenerPorcentajedeCuentasPesos();
}
