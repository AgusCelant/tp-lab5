<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hisotrial</title>
</head>
<body>
	<form action="mostrarHistorial.html" method="post">
		<h2>HISTORIAL</h2>
		<input type="hidden" name="dni" value="${dni}">
		<lablel>Cuenta:</lablel>
		<select id="selectCuentaOrigen" name="cuentaSeleccionada" onchange="this.form.submit()">
		    ${listaCuentas}
		</select>
		<br>
		<table>
			<tr>
				<th>FECHA DE MOVIMIENTO</th>
				<th>MONTO</th>
				<th>TIPO DE CUENTA</th>
				<th>DESDE CUENTA</th>
				<th>HACIA LA CUENTA</th>
			</tr>
			<c:forEach var="itemHistorial" items="${listaHistorial}">
				<tr>
					<td>${itemHistorial.fecha}</td>
					<td>${itemHistorial.monto}</td>
					<td>${itemHistorial.tipodeCuenta}</td>
					<td>${itemHistorial.nroCuentaOrigen}</td>
					<td>${itemHistorial.nroCuentadestino}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="pagination">
		  <input type="button" value="|<"/>
		  <input type="button" value="<"/>
		  <span>0</span>
		  <input type="button" value=">"/>
		  <input type="button" value=">|"/>
		</div>
		<br>
		<button>VOLVER</button>
	</form>
</body>
</html>