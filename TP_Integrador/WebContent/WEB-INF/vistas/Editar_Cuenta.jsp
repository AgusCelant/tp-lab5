<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Cuenta</title>
</head>
<body>


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


<div class="Formulario">
<h1 align="center">Bienvenido Admin</h1>
<h4 align="center">Editar Cuenta</h4>
	<p align="center">Cliente: <input class="controls" type="text" name="nombre" ></p>

    <p align="center"> Tipo Cuenta:</p> 
    <p align="center" class="controls">
    <input type="radio" name="TipoCuenta" value="corriente"> Corriente
    <input type="radio" name="TipoCuenta" value="ahorro"> 
  		Ahorro</p>

     <p align="center">Nº Cuenta:<input class="controls"id="date" type="date">
       <p align="center">CBU: <input class="controls" type="text" name="apellido" ></p>
        <p align="center">Nombre: <input class="controls" type="text" name="apellido" ></p>
            <p align="center">SALDO: <input class="controls" type="text" name="apellido" ></p>
       
	  <input class="botons"type="submit" value="Aceptar">
	</div>



</body>
</html>