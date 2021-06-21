<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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



.AltaCuenta{
justify-content:center;
width:200px;
height:100px;
background-color:#2C3E50 ;
border:double;
}

.h2{
color:white;
}

</style>

<center>
	<div class="AltaCuenta" align="center">	
	<h2 class="h2" align="center">Bienvenido Admin<h2>
	</div >
	</center>
	
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
    <td> <input type="submit" value="Agregar Cuenta"> </td>


   
  </tr>
</table>
</body>
</html>