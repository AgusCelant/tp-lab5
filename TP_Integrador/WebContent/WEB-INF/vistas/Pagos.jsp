<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagos</title>
</head>
<body>
<center>
 <div>
	<h1>PAGO DE SERVICIOS</h1>
	<jsp:include page="MenuCliente.jsp"></jsp:include>
    <form action="pagos.html" method="post">
 		<input type="hidden" name="dni" value="${clienteLogueado.dni}">
 		  <h2>ESTADO DE CUENTA</h2>
			${cuentasCliente}
			
			
			<% String id;
				String serv;
			id=request.getParameter("idServ");
        	serv = request.getParameter("serv");
			%>
			
			<h3>ID SERVICIO: <%=id%></h3>
			<h3>SERVICIO A PAGAR: <%=serv%></h3>
			
				<input type="hidden" name="idServ" id="idServ" value="<%=id%>">
				<input type="hidden" name="serv" id="serv" value="<%=serv%>">
				
    	<label>N° Codigo de Barra: </label><input type="text" name="txtCodigoBarra" placeholder="Codigo de Barra" required="required" />
        <label>Monto a pagar: </label><input type="text" name="txtMonto" placeholder="Monto a pagar" required="required"/>
        <button type="submit" class="btn btn-primary btn-block btn-large" name="btnPagar">PAGAR</button>
  
  		<button formaction="verpagos.html" type="submit"> VER PAGOS</button>
  		
  		<br><br>
  		<h1>${estadoPago}</h1>
  		<h1>${MensajePago}</h1>
    </form>
 </div>
</center>
</body>
</html>