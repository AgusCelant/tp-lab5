<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alta Cuenta</title>
<style>
.Formulario{
width: 400px;
background:#24303c;
padding: 30px;
margin: auto;
margin-top: 100px;
border-radius: 4px;
font-family: 'calibri';
color: white;
}
.Formulario h4{
font-size:22px;
margin-bottom:20px;

}


.controls{
width:100%;
background: #24303c;
padding:10px;
border-radius: 4px;
margin-bottom: 16px;
border: 1px solid #1f53c5;
font-size: 18px;
}
.Formulario .botons{
width: 100%;
background: #1f53c5;
border:none;
padding:12px;
color:white;
margin: 16px 0;
font-size: 16px;
animation: shadow-pulse 1000ms 1;
background-color:#434343;
}
body{
background:black;
}
</style>
</head>
	<body>
		<form class="Formulario" action="AsociarCuenta.html" method="post">
			<h1 align="center">Bienvenido Admin</h1>
			<h4 align="center">Agregar Cuenta</h4>
				<p align="center">Cliente: <input class="controls" type="text" name="dni" value="${dni}" readonly="readonly"  ></p>
				<p align="center">Nro Cuenta:<input class="controls"id="date" name="nroCuenta">
			    <p align="center">Fecha Creacion: </p> <input type="date" id="start" name="fechaCreacion" value="2018-07-22" min="2018-01-01" max="2018-12-31">
			    <p align="center"> Tipo Cuenta:</p>
			    <p align="center" class="controls">
				    <input type="radio" name="tipoCuenta" value="corriente"> Peso
				    <input type="radio" name="tipoCuenta" value="ahorro"> Dolar
			    </p>
			    <p align="center">CBU: <input class="controls" type="text" name="cbu" ></p>
			    <p align="center">SALDO: <input class="controls" type="text" name="saldo" ></p>
			       
				<input class="botons"type="submit" value="Aceptar">
		</form>
	</body>
</html>