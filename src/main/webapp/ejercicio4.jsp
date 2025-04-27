<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Ejercicio 4 - Importadora</title>
  <link rel="stylesheet" href="css/bootstrap.css">
  <link rel="stylesheet" href="css/style.css">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</head>

<body class="text-light">
<nav class="container-fluid">
  <h1 class="text-center display-1 p-3"><a href="index.jsp" id="title-link">Guía de ejercicios 2</a></h1>
</nav>

<main class="container my-5 p-5">
  <h2 class="display-6 text-center mb-4">Registro de Venta de Vehículo</h2>
  <form action="VehiculoServlet" method="post" class="container px-5">
    <fieldset>
      <legend>Datos del Cliente</legend>
      Nombre Completo: <input type="text" class="form-control bg-dark text-light" name="nombre" required /><br/>
      Sexo:
      <select name="sexo" class="form-select bg-dark text-light" required>
        <option value="">Seleccione</option>
        <option value="Masculino">Masculino</option>
        <option value="Femenino">Femenino</option>
      </select><br/>
    </fieldset>

    <fieldset>
      <legend>Datos del Vehículo</legend>
      Marca:
      <select name="marca" class="form-select bg-dark text-light" required>
        <option value="">Seleccione</option>
        <option value="Nissan">Nissan</option>
        <option value="Toyota">Toyota</option>
        <option value="Kia">Kia</option>
      </select><br/>
      Año: <input type="number" class="form-control bg-dark text-light" name="anio" min="2000" max="2025" required /><br/>
      Precio: <input type="number" class="form-control bg-dark text-light" name="precio" step="0.01" min="0" required /><br/>
    </fieldset>

    <div class="text-center mt-3">
      <input type="submit" value="Registrar Venta" class="btn" />
    </div>
  </form>

<br/>
<form action="index.jsp" method="get">
  <div class="text-center mt-3">
    <input type="submit" value="← Volver al Inicio" class="btn" />
  </div>
</form>
</main>
</body>
</html>
