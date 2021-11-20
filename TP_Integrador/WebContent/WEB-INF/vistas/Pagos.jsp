<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <div>
	<h1>PAGO DE SERVICIOS</h1>
    <form>
 		   
    	<input type="text" name="txtCodigoBarra" placeholder="Codigo de Barra" required="required" />
        <input type="text" name="txtMonto" placeholder="Monto a pagar" required="required" />
        <button type="submit" class="btn btn-primary btn-block btn-large" name="btnIngresar">PAGAR</button>
  
    </form>
</div>
</body>
</html>