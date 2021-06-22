<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hisotrial</title>
</head>
<body>
	<h2>HISTORIAL</h2>
	<lablel>Cuenta:</lablel>
	<select id="selectCuentaOrigen" name="cuentaOrigen">
	    <option>00001</option>
	    <option>00002</option>
	</select>
	<br>
	<table>
		<tr>
			<th>FECHA DE MOVIMIENTO</th>
			<th>MONTO</th>
			<th>TIPO DE OPERACION</th>
			<th>DESDE CUENTA</th>
			<th>HACIA LA CUENTA</th>
		</tr>
		<tr>
			<td>02/01/21</td>
			<td>500</td>
			<td>Extraccion</td>
			<td>00003</td>
			<td>00001</td>
		</tr>
		<tr>
			<td>03/01/21</td>
			<td>1000</td>
			<td>Deposito</td>
			<td>00004</td>
			<td>00003</td>
		</tr>
		<tr>
			<td>04/01/21</td>
			<td>250</td>
			<td>Extraccion</td>
			<td>00003</td>
			<td>00002</td>
		</tr>
		<tr>
			<td>04/01/21</td>
			<td>250</td>
			<td>Extraccion</td>
			<td>00001</td>
			<td>00004</td>
		</tr>
		<tr>
			<td>05/01/21</td>
			<td>1500</td>
			<td>Deposito</td>
			<td>00004</td>
			<td>00001</td>
		</tr>
		<tr>
			<td>07/02/21</td>
			<td>1154</td>
			<td>Extraccion</td>
			<td>00003</td>
			<td>00001</td>
		</tr>
		<tr>
			<td>22/05/21</td>
			<td>2540</td>
			<td>Deposito</td>
			<td>00001</td>
			<td>00002</td>
		</tr>
		<tr>
			<td>26/06/21</td>
			<td>5460</td>
			<td>Deposito</td>
			<td>00004</td>
			<td>00001</td>
		</tr>
		<tr>
			<td>11/07/21</td>
			<td>6485</td>
			<td>Extraccion</td>
			<td>00003</td>
			<td>00001</td>
		</tr>
		<tr>
			<td>04/11/21</td>
			<td>15000</td>
			<td>Deposito</td>
			<td>00002</td>
			<td>00003</td>
		</tr>
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
</body>
</html>