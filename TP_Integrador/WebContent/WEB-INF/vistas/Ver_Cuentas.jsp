<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>


<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
	
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#table_id').DataTable();
	});
</script>	
	
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

center {
	margin-left: 80px;
}

#table_id {
	color: black;
}
</style>
<jsp:include page="MenuAdmin.jsp"></jsp:include>

<form action="verCuenta.html" method="get">
<center>
<h4>${estadoeliminarCuenta}</h4>
<h4>${estadoEditarCuenta}</h4>
<h4>${estadoAgregarCuenta}</h4>
	<div class="AltaCuenta" align="center">	
	<h2 class="h2" align="center">Bienvenido Admin<h2>
		<a href="MostrarClientes.html?" >Agregar Cuenta </a>
	</div >
	</center>
</form>
<table style="text-align:center;" id="table_id" class="display">
<thead>
  <tr>
    <th>Cliente Asignado</th>
    <th>Fecha Creacion</th>
    <th>Tipo Cuenta</th>
    <th>Numero de cuenta</th>
    <th>CBU</th>
    <th>Saldo</th>
 
    <th></th>
    <th></th>
  </tr>
  </thead>
   <c:forEach var="cuenta" items="${ ListaCuentas }"  >
  <tr>
  	  <td>${cuenta.dni}</td>
      <td>${cuenta.fecha} </td>
      <td>${cuenta.tipoCuenta} </td>
      <td>${cuenta.numCuenta} </td>
      <td>${cuenta.cbu} </td>
      <td>${cuenta.saldo} </td>

   	  <td><a href="EditarCuentas.html?id=${cuenta.dni},${cuenta.numCuenta}" >Editar </a> </td>
   	  <td><a href="EliminarCuenta.html?id=${cuenta.dni},${cuenta.numCuenta}">Eliminar </a> </td>


   
  </tr>
    	</c:forEach>
</table>

</body>
</html>