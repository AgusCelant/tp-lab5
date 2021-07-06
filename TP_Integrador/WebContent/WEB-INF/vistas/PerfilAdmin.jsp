<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Perfil Administrador</title>
</head>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Cuenta Pesos',    ${Cuenta1}],
          ['Cuenta Dolares',     ${Cuenta2}] 
        ]);

        var options = {
          title: 'Porcentaje',
          backgroundColor: { fill:'transparent' }
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
   
<body>
<div id="piechart" style="width: 900px; height: 500px;"></div>
<div id="piechart2" style="width: 900px; height: 500px;"></div>
<jsp:include page="MenuAdmin.jsp"></jsp:include>


</body>

</html>