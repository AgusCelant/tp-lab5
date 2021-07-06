<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Cuenta</title>
</head>
<body>


<style>
.Formulario{
width: 400px;
background:#24303c;
padding: 30px;
margin: auto;
margin-top: 100px;
border-radius: 4px;
font-family: 'calibri';
color: white;
}
.Formulario h4{
font-size:22px;
margin-bottom:20px;

}


.controls{
width:100%;
background: #24303c;
padding:10px;
border-radius: 4px;
margin-bottom: 16px;
border: 1px solid #1f53c5;
font-size: 18px;
}
.Formulario .botons{
width: 100%;
background: #1f53c5;
border:none;
padding:12px;
color:white;
margin: 16px 0;
font-size: 16px;
animation: shadow-pulse 1000ms 1;
background-color:#434343;
}
body{
background:black;
}
</style>

<jsp:include page="MenuAdmin.jsp"></jsp:include>
<div class="Formulario">
<h1 align="center">Bienvenido Admin</h1>
<h4 align="center">Editar Cuenta</h4>

<form action="ActualizarCuenta.html" method="post">
 <c:forEach var="cuenta" items="${ ListaCuentas }"  >
	<p align="center">Cliente: <input class="controls" type="text" name="dni" value="${cuenta.dni}"readonly="readonly" ></p>

    <p align="center"> Tipo Cuenta:</p> 
    <p align="center" class="controls">
    <input type="radio" name="TipoCuenta" value="Pesos" ${cuenta.tipoCuenta == "Pesos" ? "checked" : ""} > Pesos
    <input type="radio" name="TipoCuenta" value="Dolares" ${cuenta.tipoCuenta == "Dolares" ? "checked" : ""} > Dolares
  		</p>

    <p align="center">Nº Cuenta:<input class="controls"name="nrocuenta"value="${cuenta.numCuenta}" readonly="readonly" >
    <p align="center">CBU: <input class="controls" type="text" name="cbu" value="${cuenta.cbu}"  onkeypress="return ((event.charCode >= 48 && event.charCode <= 57))"></p>
    <p align="center">Fecha Creacion: </p> <input type="date" id="start" name="fechaCreacion" value="2018-07-22" min="2018-01-01" max="2018-12-31" value="${cuenta.fecha}">
    <p align="center">SALDO: <input class="controls" type="text" name="saldo"  value="${cuenta.saldo}" min="10000"   onkeypress="return ((event.charCode >= 48 && event.charCode <= 57))"></p>
       
	  <input class="botons"type="submit" value="Aceptar">
	</div>
	</c:forEach>
</form>

</body>
</html>