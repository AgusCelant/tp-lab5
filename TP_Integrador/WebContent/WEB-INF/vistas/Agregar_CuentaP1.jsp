<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<style>
table, td, th {
  border: 1px solid black;
}

table {
  border-collapse: collapse;
  width: 100%;
}

th {
  height: 70px;
}



.AltaCuenta{
justify-content:center;
width:200px;
height:100px;
background-color:#2C3E50 ;
border:double;
}

.h2{
color:white;
}

</style>

<center>
	<div class="AltaCuenta" align="center">	
	<h2 class="h2" align="center">Bienvenido Admin<h2>
	</div >
	</center>
	<form action="MostrarClientes.html" method="get">
	<input type="submit" value="Agregar Cliente">
	</form>
<table>
  <tr>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>Sexo</th>
    <th>Dni</th>
    <th>Nacimiento</th>
    <th>Nacionalidad</th>
    <th>Provincia</th>
    <th>Localidad</th>
    <th>Usuario</th> 
    <th></th>
  </tr>
    <c:forEach var="cliente" items="${ ListaClientes }"  >
  <tr>
  	  <td>${cliente.nombre}</td>
      <td>${cliente.apellido} </td>
      <td>${cliente.sexo} </td>
      <td>${cliente.dni} </td>
      <td>${cliente.nacimiento} </td>
      <td>${cliente.nacionalidad} </td>
      <td>${cliente.provincia} </td>
      <td>${cliente.localidad} </td>
      <td>${cliente.usuario} </td>
      <td><a href="AgregarCuentaP1.html?id=${cliente.dni}" >Agregar Cuenta </a> </td>


   
  </tr>
  
  </c:forEach>
   

</table>
</body>
</html>