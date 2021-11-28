package banco.utn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import banco.utn.dao.DaoCuenta;
import banco.utn.dao.DaoPagos;
import banco.utn.dao.DaoPersona;
import banco.utn.entidad.Cliente;
import banco.utn.entidad.Cuenta;
import banco.utn.entidad.Pagos;
import banco.utn.entidad.Servicios;

@Controller
public class ControladorPagos {
	@Autowired
	private DaoCuenta BDaoCuenta;
	@Autowired
	private DaoPersona BDaoPersona;
	@Autowired
	private DaoPagos daoPagos;
	
	
	@RequestMapping("pagos.html")
	public ModelAndView pagos(String txtMonto, String dni, String idServ, String serv, HttpServletRequest request) {
		
		if(txtMonto == null) {//model ingresa por primera vez
			ModelAndView MV = new ModelAndView();
			String msg = "NO TIENE DINERO EN LA CUENTA PARA REALIZAR EL PAGO ";
			List<Cuenta> cuentas = BDaoCuenta.obtenerCuentasDeUsuario(dni);
			if(cuentas.isEmpty()){
				//mostrar msg
			}
			
			String Dnii=(String) request.getSession().getAttribute("Dni");
			String Clientelogueado=(String) request.getSession().getAttribute("Clienteelogueado");
			List<Cuenta> cuentas1 = BDaoCuenta.obtenerCuentasDeUsuario(Dnii);
			Cliente cliente = BDaoPersona.obtenerDatosDeUsuario(Clientelogueado);
			String resumenCuentas = "";
			for(Cuenta cuenta : cuentas1) {
				resumenCuentas = "<div>Nro de caja de ahorro: <b>" + cuenta.getNumCuenta() + "</b>, Saldo: <b>" + cuenta.getSaldo() + "</b></div><br>";
			}
					
			MV.addObject("cuentasCliente", resumenCuentas);
			MV.addObject("clienteLogueado", cliente);
				
			MV.setViewName("Pagos");
			return MV;
		}
		else { //model para hacer el pago
		ModelAndView MV = new ModelAndView();
		
		String msg = "NO TIENE DINERO EN LA CUENTA PARA REALIZAR EL PAGO ";
		List<Cuenta> cuentas = BDaoCuenta.obtenerCuentasDeUsuario(dni);
		if(cuentas.isEmpty()){
			//mostrar msg
		}
		
		String Dnii=(String) request.getSession().getAttribute("Dni");
		String Clientelogueado=(String) request.getSession().getAttribute("Clienteelogueado");
		List<Cuenta> cuentas1 = BDaoCuenta.obtenerCuentasDeUsuario(Dnii);
		Cliente cliente = BDaoPersona.obtenerDatosDeUsuario(Clientelogueado);
		String resumenCuentas = "";
		for(Cuenta cuenta : cuentas1) {
			resumenCuentas = "<div>Nro de caja de ahorro: <b>" + cuenta.getNumCuenta() + "</b>, Saldo: <b>" + cuenta.getSaldo() + "</b></div><br>";
		}
		
		
		Boolean pago = true;
		Pagos p = new Pagos();
		
		p.setIdCliente(Dnii);
		p.setIdServicio(idServ);
		p.setServicio(serv);
		p.setMonto(txtMonto);
		pago = daoPagos.agregarPago(p);
		
		if(pago == false) {
			String mensaje = "NO SE PUDO CARGAR EL PAGO, INTENTE MAS TARDE";
			
			MV.addObject("estadopPago",mensaje);
		}
		else
		{
			String mensaje = "EL PAGO SE EFECTUO CORRECTAMENTE";
			MV.addObject("estadoPago",mensaje);
		}
		
		MV.addObject("cuentasCliente", resumenCuentas);
		MV.addObject("clienteLogueado", cliente);
		MV.setViewName("Pagos");
		return MV;
	}
	
}
	
	@RequestMapping("verpagos.html")
	public ModelAndView VerPagos(String dni, String idServ, HttpServletRequest request) {
		ModelAndView MV = new ModelAndView();
		String Dnii=(String) request.getSession().getAttribute("Dni");
		
		List<Pagos> listadoPagos = daoPagos.ListarPagos(Dnii);
		if(listadoPagos.isEmpty()) {
			String msg = "EL USUSARIO NO TIENE PAGOS DE SERVICIOS.";
			MV.addObject("PAGOS",msg);
		}else {
		MV.addObject("listadoPagos", listadoPagos);
		}
		MV.setViewName("VerPagos");
		return MV;
	}
}

