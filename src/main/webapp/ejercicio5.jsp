<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="sv.edu.udb.alumno.guia2poo.carclean.Conexion" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%
    /*
    * Ejercicio 5 hecho por Daniel Enrique Flores Lino.
    * Última edición el 27/abril/2025
    * */
    String sqlDatos = """
            SELECT c.id,a.id,c.nombres,c.apellidos,c.vip,a.marca,a.modelo,a.año,s.tipo,s.precio
            FROM cliente c
            INNER JOIN servicio s ON c.id = s.id_cliente
            INNER JOIN automotor a ON s.id_auto = a.id
            """;
    String error = "";
%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio 5 - Guía 2</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</head>

<body class="text-light">
<nav class="container-fluid">
    <h1 class="text-center display-1 p-3"><a href="index.jsp" id="title-link">Guía de ejercicios 2</a></h1>
</nav>
<main class="container my-5 p-5">
    <h2 class="display-6 text-center mb-4">Car Clean - Servicios realizados</h2>
    <div class="text-center my-3">
        <a href="ejercicio5-add.jsp" class="btn">Añadir registro</a>
    </div>

    <div class="table-responsive">
        <table class="table table-dark table-bordered table-striped table-hover borde text-center">
            <thead class="table-dark">
            <tr>
                <th class="col-small">ID cliente</th>
                <th class="col-small">ID auto</th>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th class="col-small">¿Es VIP?</th>
                <th class="col-small">Marca</th>
                <th class="col-small">Modelo</th>
                <th class="col-small">Año</th>
                <th>Tipo</th>
                <th class="col-small">Total</th>
            </tr>
            </thead>
            <tbody>
            <% try { //Para obtener datos de la BD
                Conexion c = new Conexion();
                Connection conn = c.getConn();
                c.st = conn.createStatement();
                ResultSet rs = c.st.executeQuery(sqlDatos);
                //Obteniendo cada columna
                while (rs.next()) { %>
            <tr>
                <td><%= rs.getInt(1) %></td>
                <td><%= rs.getInt(2) %></td>
                <td><%= rs.getString(3) %></td>
                <td><%= rs.getString(4) %></td>
                <td>
                    <% String vip;
                    if (rs.getBoolean(5)) {
                        vip = "Sí";
                    } else { vip = "No"; }
                    %>
                    <%= vip %>
                </td>
                <td><%= rs.getString(6) %></td>
                <td><%= rs.getString(7) %></td>
                <td><%= rs.getInt(8) %></td>
                <td><%= rs.getString(9) %></td>
                <td>$<%= rs.getDouble(10) %></td>
            </tr>
            <% }
                conn.close();
            } catch (SQLException | ClassNotFoundException e) {
                error = "Error. " + e;
            } %>
            </tbody>
        </table>
        <p><%= error %></p>
    </div>
</main>
</body>
