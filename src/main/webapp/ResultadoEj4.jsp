<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="sv.edu.udb.alumno.guia2poo.importadora.Vehiculo" %>
<%@ page import="sv.edu.udb.alumno.guia2poo.importadora.Cliente" %>

<html>
<head>
    <title>Resultados - Importadora</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />

</head>
<body>

<div class="container">
    <h2>Datos Ingresados</h2>
    <table>
        <tr><th>Nombre</th><th>Sexo</th><th>Marca</th><th>Año</th><th>Precio</th></tr>
        <%
            List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
            List<Vehiculo> vehiculos = (List<Vehiculo>) request.getAttribute("vehiculos");
            for (int i = 0; i < clientes.size(); i++) {
                Cliente c = clientes.get(i);
                Vehiculo v = vehiculos.get(i);
        %>
        <tr>
            <td><%= c.getNombre() %></td>
            <td><%= c.getSexo() %></td>
            <td><%= v.getMarca() %></td>
            <td><%= v.getAnio() %></td>
            <td>$<%= v.getPrecio() %></td>
        </tr>
        <% } %>
    </table>

    <h2>Estadísticas</h2>
    <table>
        <tr><th>Marca</th><th>Total Vendidos</th><th>Total Ventas</th></tr>
        <tr><td>Nissan</td><td><%= request.getAttribute("nissanTotal") %></td><td>$<%= request.getAttribute("nissanSuma") %></td></tr>
        <tr><td>Toyota</td><td><%= request.getAttribute("toyotaTotal") %></td><td>$<%= request.getAttribute("toyotaSuma") %></td></tr>
        <tr><td>Kia</td><td><%= request.getAttribute("kiaTotal") %></td><td>$<%= request.getAttribute("kiaSuma") %></td></tr>
    </table>

    <h2>Rangos por Año</h2>
    <p>Vehículos entre 2000 y 2015: <%= request.getAttribute("rango1") %></p>
    <p>Vehículos entre 2016 y 2025: <%= request.getAttribute("rango2") %></p>

    <br>
    <a href="ejercicio4.jsp">← Registrar otro vehículo</a>
</div>

</body>
</html>
