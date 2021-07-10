package banco.utn.negocio;
import java.util.List;
import banco.utn.entidad.Historial;
public interface InterfazNegHistorial {
	public List<Historial> obtenerHisotrialPorCuenta (int idCuenta);
	public void crearRegistroEnHisotrial (int nroCuentaOrigen, int nroCuentaDestino, float monto, String tipoCuenta);
}
