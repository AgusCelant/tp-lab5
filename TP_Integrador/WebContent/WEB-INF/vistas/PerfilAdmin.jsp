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
          ['Cuenta N°1',    ${Cuenta1}],
          ['Cuenta N°2',       ${Cuenta2}],
          ['Cuenta N°3',   ${Cuenta3}],
          ['Cuenta N°4',  ${Cuenta4}]
      
        ]);

        var options = {
          title: 'Porcentaje de Pesos',
          backgroundColor: { fill:'transparent' }
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Cuenta Dolar N°1',    ${Cuenta1d}],
          ['Cuenta Dolar N°2',       ${Cuenta2d}],
          ['Cuenta Dolar N°3',   ${Cuenta3d}],
          ['Cuenta Dolar N°4',  ${Cuenta4d}]
      
        ]);

        var options = {
          title: 'Porcentaje de Dolar',
          backgroundColor: { fill:'transparent' }
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart2'));

        chart.draw(data, options);
      }
    </script>
<body>
<div id="piechart" style="width: 900px; height: 500px;"></div>
<div id="piechart2" style="width: 900px; height: 500px;"></div>
<jsp:include page="MenuAdmin.jsp"></jsp:include>


</body>

</html>