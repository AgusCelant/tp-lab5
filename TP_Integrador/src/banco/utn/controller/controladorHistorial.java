package banco.utn.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import banco.utn.dao.DaoCuenta;
import banco.utn.dao.DaoHistorial;
import banco.utn.entidad.Cuenta;
import banco.utn.entidad.Historial;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controladorHistorial {

	@RequestMapping("mostrarHistorial.html")
	public ModelAndView mostrarHistorial(String dni, String cuentaSeleccionada) {
		DaoCuenta DAOCuenta = new DaoCuenta();
		DaoHistorial DAOHistorial = new DaoHistorial();
		ModelAndView MV = new ModelAndView();
		ArrayList<Historial> listaHistorial = new ArrayList<Historial>();
		
		List<Cuenta> cuentas = DAOCuenta.obtenerCuentasDeUsuario(dni);
		
		String listaCuentas = "";
		for(Cuenta cuenta : cuentas) {
			if (cuentaSeleccionada != null && cuentaSeleccionada.equals(String.valueOf(cuenta.getNumCuenta()))) {
				listaCuentas = listaCuentas + "<option selected>" + cuenta.getNumCuenta() + "</option>";
			} else {
				listaCuentas = listaCuentas + "<option>" + cuenta.getNumCuenta() + "</option>";
			}
		}
		
		if (cuentaSeleccionada != null) {
			listaHistorial = (ArrayList<Historial>)DAOHistorial.obtenerHisotrialPorCuenta(Integer.parseUnsignedInt(cuentaSeleccionada));
		} else {
			listaHistorial = (ArrayList<Historial>)DAOHistorial.obtenerHisotrialPorCuenta(cuentas.get(0).getNumCuenta());
		}
		
		MV.addObject("dni", dni);
		MV.addObject("listaCuentas", listaCuentas);
		MV.addObject("listaHistorial", listaHistorial);
		MV.setViewName("Historial");
		
		return MV;
	}
}
