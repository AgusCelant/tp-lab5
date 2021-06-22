<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transferencia</title>
</head>
<body>
	<h2>TRANSFERENCIA</h2>
	<lablel>Cuenta origen:</lablel>
	<select id="selectCuentaOrigen" name="cuentaOrigen">
	    <option>00001</option>
	    <option>00002</option>
	</select>
	<br><br>
	
	<lablel>Cuenta destino:</lablel>
	<input type="number">
	<br><br>
	
	<label>Monto a transferir:</label><input type="number">
	<br><br>
	<span>No se puede transferir entre cuentas de distinta moneda!</span><br>
	<button>CANCELAR</button> <button>CONFIRMAR</button>
</body>
</html>