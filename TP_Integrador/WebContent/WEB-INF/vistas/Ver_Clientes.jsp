<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ver Clientes</title>
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






	<center>
	<div class="AltaCliente" align="center">	
	<h2 class="h2">Bienvenido admin<h2>
	<h2 class="h2">Clientes<h2>
	<input type="submit" value="Agregar Cliente">
	</div >
	</center>
	<p>
<table>
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
  <tr>
    <td>Peter</td>
    <td>Griffin</td>
    <td>Hombre</td>
    <td>42.589.277</td>
    <td>23/05/2000</td>
    <td>Argentino</td>
    <td>Argentina</td>
    <td>Virreyes</td>
    <td>PeterG</td>
    <td> <input type="submit" value="Editar"> </td>
    <td> <input type="submit" value="Eliminar"></td>

   
  </tr>
</table>

	</p>

</body>
</html>