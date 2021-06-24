<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agregar Clientes</title>
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
background:white;
}
</style>

<form action="agregarPersona.html" method="post">
<div class="Formulario">
<h1 align="center">Bienvenido Admin</h1>
<h4 align="center">Agregar Cliente</h4>
	<p align="center">Nombre: <input class="controls" type="text" name="nombre" ></p>
    <p align="center">Apellido: <input class="controls" type="text" name="apellido"  ></p>
    <p align="center"> Sexo:</p> 
    <p align="center" class="controls">
    <input type="radio" name="Sexo" value="Hombre"> Hombre
    <input type="radio" name="Sexo" value="Mujer"> Mujer
  		</p>
     <p align="center" >Dni: <input class="controls" type="number" name="Dni" ></p>
     <p align="center">Año de nacimiento:<input class="controls" type="date" name="date">
       <p align="center">Nacionalidad: <input class="controls" type="text" name="Nacionalidad" ></p>
        <p>Provincia: <select class="controls" name='Provincia'>
        <option>Argentina</option>
         <option>Guatemala</option>
        
         </select> </p>      
      <p>Localidad: <select class="controls" name='Localidad'>
        <option>San Fernando</option>
         <option>Virreyes</option>
        
         </select> </p>    
         <p align="center">Usuario: <input class="controls" type="text" name="usuario" ></p>
    <p align="center">Contraseña: <input class="controls" type="text" name="contraseña" ></p>
        
	  <input class="botons"type="submit" value="Agregar">
	</div>
	</form>
	
<h4>${estadoAgregarPersona}</h4>
	
</body>
</html>