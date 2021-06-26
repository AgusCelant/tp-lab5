<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main Cliente</title>
</head>
<body>

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
			<td>${clienteLogueado.dni}</td>
			<td>${clienteLogueado.localidad}</td>
		</tr>
	</table>
	
	<h2>ESTADO DE CUENTA</h2>
	<div><b>Caja de ahorro en pesos (Nro. 00001): </b>$1000</div>
	<div><b>Caja de ahorro en dolares (Nro. 00002): </b>$1000</div>
	
	<h2>ACCIONES</h2>
	<button>VER HISOTRIAL DE CUENTAS</button>
	<button>TRANSFERENCIAS</button>
</body>
</html>