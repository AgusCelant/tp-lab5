<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script>
function checkSelection (value) {
	console.log('value',value);
	if (document.getElementById('selectCuentaOrigen').value === document.getElementById('selectCuentaDestino').value) {
		document.getElementById('botonConfirmar').disabled = true;
	} else {
		document.getElementById('botonConfirmar').disabled = false;
	}
	if (document.getElementById('selectCuentaDestino').value === 'Transferir a cuenta no propia...') {
		document.getElementById('cbuDestino').disabled = false;
	} else {
		document.getElementById('cbuDestino').disabled = true;
	}
}
</script>
<title>Transferencia</title>
</head>
<body>
<h1>Bienvenido/a <%=session.getAttribute("Usuario")%></h1>
	<form action="confirmarTransferencia.html" method="post">
		<input type="hidden" name="dni" value="${dni}">
		<h2>TRANSFERENCIA</h2>
		<br>${mensajeError}<br>
		<lablel>Cuenta origen:</lablel>
		<select id="selectCuentaOrigen" name="nroCuentaOrigen" onchange="checkSelection()">
		    ${listaCuentas}
		</select>
		<br><br>
		
		<lablel>Cuenta destino:</lablel>
		<select id="selectCuentaDestino" name="nroCuentaDestino" onchange="checkSelection()">
			<option>Transferir a cuenta no propia...</option>
		    ${listaCuentas}
		</select>
		<br><br>
		<label>CBU destino:</label><input id="cbuDestino" name="cbu" type="number">
		<br><br>
		<label>Dinero disponible: $xxxx</label>
		<br>
		<label>Monto a transferir:</label><input name="monto" type="number">
		<br><br>
		<button>CANCELAR</button> <button id="botonConfirmar" type="submit">CONFIRMAR</button>
	</form>
</body>
</html>