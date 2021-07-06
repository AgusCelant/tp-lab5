<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
	
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="SyleFormulario.css" />	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#table_id').DataTable();
	});
</script>
<style>
nav {
z-index: 2;
}
center {
	margin-left: 80px;
}

#table_id {
	color: black;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Cliente</title>
</head>
<body>
<jsp:include page="MenuCliente.jsp"></jsp:include>
	<center>
		<form method="post">
			<h4>${Cartel}</h4>
			<h4>${Cartel2}</h4>
			<h1>Bienvenido/a <%=session.getAttribute("Usuario")%></h1>
			<table id="table_id" class="display">
			<thead>
				<tr class="w3-red">
					<th>USUARIO</th>
					<th>NOMBRE</th>
					<th>APELLIDO</th>
					<th>DNI</th>
					<th>DOMICILIO</th>
				</tr>
				</thead>
			<tbody class="grid_Linea">
				<tr>
					<td>${clienteLogueado.usuario}</td>
					<td>${clienteLogueado.nombre}</td>
					<td>${clienteLogueado.apellido}</td>
					<td>${clienteLogueado.dni}</td><input type="hidden" name="dni" value="${clienteLogueado.dni}">
					<td>${clienteLogueado.localidad}</td>
				</tr>
				</tbody>
			</table>
			
			<h2>ESTADO DE CUENTA</h2>
			${cuentasCliente}
			
			<h2>ACCIONES</h2>
			<button formaction="mostrarHistorial.html" type="submit">VER HISOTRIAL DE CUENTAS</button>
			<button formaction="mostrarTransferencia.html" type="submit">TRANSFERENCIAS</button>
		</form>
	</center>
</body>
</html>