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
			<th>HACIA/DESDE CUENTA</th>
		</tr>
		<tr>
			<td>02/01/21</td>
			<td>-500</td>
			<td>00003</td>
		</tr>
		<tr>
			<td>03/01/21</td>
			<td>1000</td>
			<td>00004</td>
		</tr>
		<tr>
			<td>04/01/21</td>
			<td>-250</td>
			<td>00003</td>
		</tr>
		<tr>
			<td>04/01/21</td>
			<td>-250</td>
			<td>00003</td>
		</tr>
		<tr>
			<td>05/01/21</td>
			<td>1500</td>
			<td>00004</td>
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