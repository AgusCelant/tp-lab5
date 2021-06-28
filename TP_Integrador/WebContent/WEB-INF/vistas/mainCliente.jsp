<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main Cliente</title>
</head>
<body>
<jsp:include page="MenuCliente.jsp"></jsp:include>
	<center>
		<form method="post">
			<h1>Bienvenido/a Matias!</h1>
			<table>
				<tr>
					<th>USUARIO</th>
					<th>NOMBRE</th>
					<th>APELLIDO</th>
					<th>DNI</th>
					<th>DOMICILIO</th>
				</tr>
				<tr>
					<td>${clienteLogueado.usuario}</td>
					<td>${clienteLogueado.nombre}</td>
					<td>${clienteLogueado.apellido}</td>
					<td>${clienteLogueado.dni}</td><input type="hidden" name="dni" value="${clienteLogueado.dni}">
					<td>${clienteLogueado.localidad}</td>
				</tr>
			</table>
			
			<h2>ESTADO DE CUENTA</h2>
			${cuentasCliente}
			
			<h2>ACCIONES</h2>
			<button formaction="mostrarHistorial.html" type="submit">VER HISOTRIAL DE CUENTAS</button>
			<button formaction="action1" type="submit">TRANSFERENCIAS</button>
		</form>
	</center>
</body>
</html>