<!-- Autor: Kevin Madrid  -->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio 1 - POO</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</head>
<body class="text-light">
<nav class="container-fluid">
    <h1 class="text-center display-1 p-3"><a href="index.jsp" id="title-link">Guía de ejercicios 2</a></h1>
</nav>
<main class="container my-5 p-5">
    <h2>Formulario de Registro de Estudiante</h2>

    <!-- El action debe apuntar a la URL del Servlet definida en @WebServlet -->
    <form action="ProcesarEstudianteServlet" method="post" class="container px-5">
        <div class="mb-3">
            <label for="carnet" class="form-label">Carnet:</label>
            <input type="text" id="carnet" name="carnet" required pattern="[A-Za-z]{2}\d{4}" title="Dos letras seguidas de cuatro números (ej: AB1234)" class="form-control bg-dark text-light">
            <small>Formato: LLNNNN (ej: AB1234)</small>
        </div>
        <div class="mb-3">
            <label for="nombres" class="form-label">Nombres:</label>
            <input type="text" id="nombres" name="nombres" required maxlength="25" class="form-control bg-dark text-light">
            <small>Máximo 25 caracteres.</small>
        </div>
        <div class="mb-3">
            <label for="apellidos" class="form-label">Apellidos:</label>
            <input type="text" id="apellidos" name="apellidos" required maxlength="25" class="form-control bg-dark text-light">
            <small>Máximo 25 caracteres.</small>
        </div>
        <div class="mb-3">
            <label for="direccion" class="form-label">Dirección:</label>
            <input type="text" id="direccion" name="direccion" required maxlength="255" class="form-control bg-dark text-light">
            <small>Máximo 255 caracteres.</small>
        </div>
        <div class="mb-3">
            <label for="telefono" class="form-label">Teléfono:</label>
            <input type="text" id="telefono" name="telefono" required pattern="\d{4}-\d{4}" title="Cuatro números, guión, cuatro números (ej: 1234-5678)" class="form-control bg-dark text-light">
            <small>Formato: NNNN-NNNN (ej: 1234-5678)</small>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">E-mail:</label>
            <input type="email" id="email" name="email" required class="form-control bg-dark text-light">
            <small>Ingrese un correo electrónico válido.</small>
        </div>
        <div class="mb-3">
            <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento:</label>
            <input type="text" id="fechaNacimiento" name="fechaNacimiento" required pattern="(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\d{4}" title="Ingrese la fecha en formato DD/MM/YYYY" class="form-control bg-dark text-light">
            <small>Formato: DD/MM/YYYY</small>
        </div>

        <div class="text-center">
            <input type="submit" value="Registrar Estudiante" class="btn mt-3">
        </div>
    </form>
</main>

</body>
</html>
