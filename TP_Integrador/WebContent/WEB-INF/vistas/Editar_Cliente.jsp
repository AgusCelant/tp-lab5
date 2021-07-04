<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Clientes</title>
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
<jsp:include page="MenuAdmin.jsp"></jsp:include>
<form action="ActualizarCliente.html" method="post">
<div class="Formulario">
  <c:forEach var="cliente" items="${ ListaClientes }"  >
<h1 align="center">Bienvenido Admin</h1>
<h4 align="center">Editar Cliente</h4>
	<p align="center">Nombre: <input class="controls" type="text" name="nombre" value="${cliente.nombre}" required onkeypress="return (event.charCode ==32 || (event.charCode >= 97 && event.charCode <= 122) || (event.charCode >= 65 && event.charCode <= 90))"></p>
    <p align="center">Apellido: <input class="controls" type="text" name="apellido" value="${cliente.apellido}" required onkeypress="return (event.charCode ==32 || (event.charCode >= 97 && event.charCode <= 122) || (event.charCode >= 65 && event.charCode <= 90))"></p>
    <p align="center"> Sexo:</p> 
    <p align="center" class="controls">
   	<input type="radio" name="Sexo" value="${cliente.sexo}" checked="checked" />
    <input type="radio" name="Sexo" value="h"> Hombre
    <input type="radio" name="Sexo" value="m"> Mujer
  		</p>
 	 <p align="center">Dni: <input class="controls" type="text" name="dni" value="${cliente.dni}" readonly="readonly"></p>
     <p align="center">Año de nacimiento:<input class="controls"name="date" type="date" value="${cliente.nacimiento}">
       <p align="center">Nacionalidad: <input class="controls" type="text" name="nacionalidad" value="${cliente.nacionalidad}" ></p>
        <p>Provincia: <select class="controls" name='Provincia'>
         <option selected="selected">${cliente.provincia} </option>
        <option>Argentina</option>
         <option>Guatemala</option>
        
         </select> </p>      
      <p>Localidad: <select class="controls" name='Localidad'>
         <option selected="selected">${cliente.localidad} </option>
        <option>San Fernando</option>
         <option>Virreyes</option>
        
         </select> </p>    
        <p align="center">Usuario: <input class="controls" type="text" name="usuario" value="${cliente.usuario}" readonly="readonly"></p>
         <p align="center">Usuario: <input class="controls" type="password" name="contraseña" value="${cliente.contraseña}" readonly="readonly"></p>
	  <input class="botons"type="submit" value="Aceptar">
	</div>
	</c:forEach>

</form>
</body>
</html>