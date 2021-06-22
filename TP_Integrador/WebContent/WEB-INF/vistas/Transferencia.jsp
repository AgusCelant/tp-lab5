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
	<select id="selectCuentaDestino" name="cuentaDestino">
		<option>Transferir a cuenta no propia...</option>
	    <option>00002</option>
	</select>
	<br><br>
	<label>CBU destino:</label><input type="number">
	<br><br>
	<label>Dinero disponible: $1000</label>
	<br>
	<label>Monto a transferir:</label><input type="number">
	<br><br>
	<button>CANCELAR</button> <button>CONFIRMAR</button>
</body>
</html>