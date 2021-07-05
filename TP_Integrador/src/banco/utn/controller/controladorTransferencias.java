package banco.utn.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import banco.utn.dao.DaoCuenta;
import banco.utn.dao.DaoHistorial;
import banco.utn.entidad.Cuenta;

@Controller
public class controladorTransferencias {

	@RequestMapping("mostrarTransferencia.html")
	public ModelAndView mostrarTransferencia(String dni) {
		ModelAndView MV = new ModelAndView();
		DaoCuenta DAOCuenta = new DaoCuenta();
		
		List<Cuenta> cuentas = DAOCuenta.obtenerCuentasDeUsuario(dni);
		
		String listaCuentas = "";
		for(Cuenta cuenta : cuentas) {
			listaCuentas = listaCuentas + "<option>" + cuenta.getNumCuenta() + "</option>";
		}
		
		MV.addObject("listaCuentas", listaCuentas);
		MV.addObject("dni", dni);
		MV.setViewName("Transferencia");
		
		return MV;
	}
	
	@RequestMapping("confirmarTransferencia.html")
	public ModelAndView confirmarTransferencia(String dni, String nroCuentaOrigen, String nroCuentaDestino, String cbu, String monto) {
		ModelAndView MV = new ModelAndView();
		DaoCuenta DAOCuenta = new DaoCuenta();
		DaoHistorial DAOHistorial = new DaoHistorial();
		
		Cuenta cuentaOrigen = DAOCuenta.obtenerCuentaPorNroCuenta(Integer.parseInt(nroCuentaOrigen));
		Cuenta cuentaDestino = new Cuenta();
		if (cbu != null && nroCuentaDestino.equals("cuentaNoPropia")) {
			cuentaDestino = DAOCuenta.obtenerCuentaPorCbu(Integer.parseInt(cbu));
		} else {
			cuentaDestino = DAOCuenta.obtenerCuentaPorNroCuenta(Integer.parseInt(nroCuentaDestino));
		}
		
		if (cuentaDestino == null) {
			MV.addObject("mensajeError", "<h2>La cuenta desino inidcada no existe!</h2>");
		} else if (cuentaOrigen.getSaldo() < Float.parseFloat(monto)) {
			MV.addObject("mensajeError", "<h2>El monto indicado es mayor al disponible!</h2>");
		} else {
			DAOCuenta.actualizarCuentas(cuentaOrigen, cuentaDestino, Float.parseFloat(monto));
			DAOHistorial.crearRegistroEnHisotrial(cuentaOrigen.getNumCuenta(), cuentaDestino.getNumCuenta(), Float.parseFloat(monto), cuentaOrigen.getTipoCuenta());
			MV.addObject("mensajeError", "<h2>La transferencia se realizo correctamente!</h2>");
		}
		
		List<Cuenta> cuentas = DAOCuenta.obtenerCuentasDeUsuario(dni);
		
		String listaCuentas = "";
		for(Cuenta cuenta : cuentas) {
			listaCuentas = listaCuentas + "<option>" + cuenta.getNumCuenta() + "</option>";
		}
		
		MV.addObject("listaCuentas", listaCuentas);
		MV.addObject("dni", dni);
		MV.setViewName("Transferencia");
		
		return MV;
	}
}
