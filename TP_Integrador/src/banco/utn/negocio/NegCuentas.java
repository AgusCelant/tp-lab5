package banco.utn.negocio;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banco.utn.dao.Conexion;
import banco.utn.dao.DaoPersona;
import banco.utn.dao.DaoCuenta;
import banco.utn.entidad.Cliente;
import banco.utn.entidad.ClientesxCuentas;
import banco.utn.entidad.Cuenta;
import banco.utn.entidad.Historial;
@Service("servicioCuentas")
public class NegCuentas implements InterfazNegCuentas {
	@Autowired
	private DaoCuenta daoCuenta;
	public List<Cuenta> listarCuentas() {
		
		return  daoCuenta.listarCuentas();
		}
	public Cuenta BuscarCuentaDni(String Dni, int numCuenta) {
		
		return daoCuenta.BuscarCuentaDni(Dni,numCuenta);
	}
	public ArrayList<Cuenta> BuscarTODASCuenta(String Dni) {
		return  daoCuenta.BuscarTODASCuenta(Dni);
	}
	public ClientesxCuentas BuscarCuentaxCliente(String Dni,int NumCuenta) {
		
		return daoCuenta.BuscarCuentaxCliente(Dni,NumCuenta);
	}
	public ArrayList<ClientesxCuentas> BuscarTODASCuentaxCliente(String Dni) {
		return  daoCuenta.BuscarTODASCuentaxCliente(Dni);
	}
	public boolean Eliminar1Cuenta(Cuenta cuenta) {
		
		return daoCuenta.Eliminar1Cuenta(cuenta);
	}

	public boolean Eliminar1Cuentaxcliente(ClientesxCuentas cli) {
		
		return daoCuenta.Eliminar1Cuentaxcliente(cli);
	}
	public boolean Editarcuenta(Cuenta cuenta) {
		
		
		return daoCuenta.Editarcuenta(cuenta);
		
	}
	public List<Integer> ObtenerPorcentajedeCuentasPesos() {
		
		
		return daoCuenta.ObtenerPorcentajedeCuentasPesos();
	}
	public List<Integer> ObtenerPorcentajedeCuentasDolar() {
		
		
		return daoCuenta.ObtenerPorcentajedeCuentasDolar();
	}
	public Cuenta obtenerCuentaPorNroCuenta (int nroCuenta) {
		
		return daoCuenta.obtenerCuentaPorNroCuenta(nroCuenta);
	}
	public Cuenta obtenerCuentaPorCbu (int cbu) {
		return daoCuenta.obtenerCuentaPorCbu(cbu);
	}
	public List<Cuenta> obtenerCuentasDeUsuario (String dni){
		
		return daoCuenta.obtenerCuentasDeUsuario(dni);
	}
	public boolean AgregarCuenta(Cuenta c) {
		
		return daoCuenta.AgregarCuenta(c);
	}
	public boolean EditarcuentaxClientes(ClientesxCuentas cxc) {
		return daoCuenta.EditarcuentaxClientes(cxc);
	}
	
	public boolean agregarClientesxcuentas(ClientesxCuentas c) {
		
		return daoCuenta.EditarcuentaxClientes(c);
	}	
	
}
