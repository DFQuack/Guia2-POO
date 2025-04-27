<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar servicio - Ejercicio 5</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</head>

<body class="text-light">
<nav class="container-fluid">
    <h1 class="text-center display-1 p-3"><a href="index.jsp" id="title-link">Guía de ejercicios 2</a></h1>
</nav>
<main class="container my-5 p-5">
    <h2 class="display-6 text-center mb-4">Registrar servicio</h2>

    <%
        /*
         * Ejercicio 5 hecho por Daniel Enrique Flores Lino.
         * Última edición el 27/abril/2025
         * */
        String error = "";
        if (request.getAttribute("error") != null) {
            error = (String) request.getAttribute("error");
        }
    %>
    <p class="bg-danger text-center"><%= error %></p>

    <form action="CarCleanServlet" method="post" class="container px-5">
        <div class="form-floating mb-3">
            <input type="text" class="form-control bg-dark text-light" name="nombres" id="nombres" placeholder="a">
            <label for="nombres">Nombres</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control bg-dark text-light" name="apellidos" id="apellidos" placeholder="a">
            <label for="apellidos">Apellidos</label>
        </div>
        <div class="mb-3">
            <input type="checkbox" class="form-check-input" id="vip" name="vip" value="true">
            <label class="form-check-label" for="vip">¿Es VIP?</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control bg-dark text-light" name="marca" id="marca" placeholder="a">
            <label for="marca">Marca del auto</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control bg-dark text-light" name="modelo" id="modelo" placeholder="a">
            <label for="modelo">Modelo del auto</label>
        </div>
        <div class="form-floating mb-3">
            <input type="number" class="form-control bg-dark text-light" name="año" id="año" placeholder="a" step="1">
            <label for="año">Año del auto</label>
        </div>
        <div class="form-floating mb-3">
            <select class="form-select bg-dark text-light" id="tipo" name="tipo">
                <option>Motocicleta</option>
                <option>Sedán</option>
                <option>Camioneta</option>
                <option>Microbús</option>
                <option>Autobús</option>
            </select>
            <label for="tipo">Tipo de auto</label>
        </div>
        <div class="text-center mt-3">
            <input type="submit" name="enviar" id="enviar" value="Añadir" class="btn">
        </div>
    </form>
</main>
</body>
</html>
