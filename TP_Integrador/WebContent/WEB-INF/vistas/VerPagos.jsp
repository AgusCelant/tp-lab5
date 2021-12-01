<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
	
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="SyleFormulario.css" />	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#table_id').DataTable();
	});
</script>
<style>
nav {
z-index: 2;
}
center {
	margin-left: 80px;
}

#table_id {
	color: black;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>VER PAGOS</title>
</head>
<body>
<center>
<jsp:include page="MenuCliente.jsp"></jsp:include>
<h1>${PAGOS}</h1>
  <table id="table_id" class="display">
			<thead>
				<tr class="w3-red">
					<th>SERVICIO</th>
					<th>CLIENTE</th>
					<th>PAGO</th>
					<th>COD.BARRA</th>
					
				</tr>
				</thead>
			<tbody class="grid_Linea">
				<c:forEach var="pagos" items="${ listadoPagos }"  >
				<tr>
					<td>${pagos.getServicio()}</td>
					<td>${pagos.getIdCliente()}</td>
					<td>${pagos.getMonto()}</td>
					<td>${pagos.getCodbarra()}</td>
					
				</tr>
				</c:forEach>
				</tbody>
			</table>
		
			</center>
</body>
</html>