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
	
<table style="text-align:center;">
  <tr>
    <th>Cliente Asignado</th>
    <th>Fecha Creacion</th>
    <th>Tipo Cuenta</th>
    <th>Numero de cuenta</th>
    <th>CBU</th>
    <th>Nombre</th>
    <th>Saldo</th>
 
    <th></th>
    <th></th>
  </tr>
  <tr>
    <td>Cliente</td>
    <td>23/05/2000</td>
    <td>Corriente</td>
    <td>00010001000200010001000000000000</td>
    <td>2850590940090410000000</td>
    <td>Nombre</td>
    <td>15000.00</td>
  
    <td>  <input type="submit" value="Editar"></td>
    <td> <input type="submit" value="Eliminar"> </td>


   
  </tr>
</table>
</body>
</html>