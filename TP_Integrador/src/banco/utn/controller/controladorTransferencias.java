package banco.utn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import banco.utn.dao.*;
import banco.utn.entidad.Cliente;
import banco.utn.entidad.Cuenta;
import banco.utn.negocio.NegCuentas;
import banco.utn.negocio.NegHistorial;
import banco.utn.negocio.NegPersona;

@Controller
public class controladorTransferencias {

	@Autowired
	private NegHistorial NegocioHistorial;
	@Autowired
	private NegCuentas negocioCuenta;
	@Autowired
	private NegPersona negocioPersona;
	
	@RequestMapping("mostrarTransferencia.html")
	public ModelAndView mostrarTransferencia(String dni, HttpServletRequest request) {
		ModelAndView MV = new ModelAndView();
		String Cartel="Este cliente no tiene ninguna cuenta asociada";
		List<Cuenta> cuentas = negocioCuenta.obtenerCuentasDeUsuario(dni);
		if(cuentas.size()==0) {
			MV.addObject("Cartel", Cartel);
		
			String Dnii=(String) request.getSession().getAttribute("Dni");
			String Clientelogueado=(String) request.getSession().getAttribute("Clienteelogueado");
			List<Cuenta> cuentas1 = negocioCuenta.obtenerCuentasDeUsuario(Dnii);
			Cliente cliente = negocioPersona.obtenerDatosDeUsuario(Clientelogueado);
			String resumenCuentas = "";
			
			for(Cuenta cuenta : cuentas1) {
				resumenCuentas = "<div>Nro de caja de ahorro: <b>" + cuenta.getNumCuenta() + "</b>, Moneda: <b>" + cuenta.getTipoCuenta().getNombre() + "</b>, Saldo: <b>" + cuenta.getSaldo() + "</b></div><br>";
			}
			System.out.println(resumenCuentas);
			MV.addObject("clienteLogueado", cliente);
			MV.addObject("cuentasCliente", resumenCuentas);
			MV.setViewName("mainCliente");
			return MV;
		}
		
		String listaCuentas = "";
		float DineroDisponible = 0;
		int c=0;
		for(Cuenta cuenta : cuentas) {
			c++;
			listaCuentas = listaCuentas + "<option>" + cuenta.getNumCuenta() + "</option>";
			if(c==1) {DineroDisponible=cuenta.getSaldo();}
			
		}
		
		MV.addObject("listaCuentas", listaCuentas);
		MV.addObject("dni", dni);
		MV.addObject("DineroDisponible", DineroDisponible);
		MV.setViewName("Transferencia");
		
		return MV;
	}
	
	@RequestMapping("confirmarTransferencia.html")
	public ModelAndView confirmarTransferencia(String dni, String nroCuentaOrigen, String nroCuentaDestino, String cbu, String monto) {
		ModelAndView MV = new ModelAndView();

		Cuenta cuentaOrigen = negocioCuenta.obtenerCuentaPorNroCuenta(Integer.parseInt(nroCuentaOrigen));
		Cuenta cuentaDestino;
		if (cbu != null && nroCuentaDestino.equals("cuentaNoPropia")) {
			cuentaDestino = negocioCuenta.obtenerCuentaPorCbu(Integer.parseInt(cbu));
		} else {
			cuentaDestino = negocioCuenta.obtenerCuentaPorNroCuenta(Integer.parseInt(nroCuentaDestino));
		}
		
		if (cuentaDestino == null) {
			MV.addObject("mensajeError", "<h2>La cuenta desino inidcada no existe!</h2>");
		} else if (cuentaOrigen.getSaldo() < Float.parseFloat(monto)) {
			MV.addObject("mensajeError", "<h2>El monto indicado es mayor al disponible!</h2>");
		} else {
			negocioCuenta.actualizarCuentas(cuentaOrigen, cuentaDestino, Float.parseFloat(monto));
			NegocioHistorial.crearRegistroEnHisotrial(cuentaOrigen.getNumCuenta(), cuentaDestino.getNumCuenta(), Float.parseFloat(monto), cuentaOrigen.getTipoCuenta().getNombre());
			MV.addObject("mensajeError", "<h2>La transferencia se realizo correctamente!</h2>");
		}
		
		List<Cuenta> cuentas = negocioCuenta.obtenerCuentasDeUsuario(dni);

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
