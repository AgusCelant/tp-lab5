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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
#table_id_wrapper {
	margin-left: 80px;
}

#table_id {
	color: black;
}


.AltaCliente{
justify-content:center;
width:320px;
height:150px;
background-color:#2C3E50 ;
border:double;
}

.h2{
color:white;
}

</style>



<jsp:include page="MenuAdmin.jsp"></jsp:include>

<form action="verCliente.html" method="get">

	<center>
	<h4>${EstadoActualizarCliente}</h4>
	<h4>${EstadoeliminarCliente}</h4>
	<div class="AltaCliente" align="center">	
	<h2 class="h2">Bienvenido admin<h2>
	<h2 class="h2">Clientes<h2>
<a href="irAgregarCliente.html?" >Agregar Cliente </a>
	</div >
	</center>
	</form>
	
	<p>
<table id="table_id" class="display">
<thead>
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
    <th></th>
  </tr>
  </thead>
 
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
   	  <td><a href="Editar.html?id=${cliente.dni}" >Editar </a> </td>
   	  <td><a href="Eliminar.html?id=${cliente.dni}">Eliminar </a> </td>

  </tr>
  	</c:forEach>
</table>





</body>
</html>