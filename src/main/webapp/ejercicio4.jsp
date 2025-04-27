<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Ejercicio 4 - Importadora</title>
  <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="form-container">
  <h2>Registro de Venta de Vehículo</h2>
  <form action="VehiculoServlet" method="post">
    <fieldset>
      <legend>Datos del Cliente</legend>
      Nombre Completo: <input type="text" name="nombre" required /><br/>
      Sexo:
      <select name="sexo" required>
        <option value="">Seleccione</option>
        <option value="Masculino">Masculino</option>
        <option value="Femenino">Femenino</option>
      </select><br/>
    </fieldset>

    <fieldset>
      <legend>Datos del Vehículo</legend>
      Marca:
      <select name="marca" required>
        <option value="">Seleccione</option>
        <option value="Nissan">Nissan</option>
        <option value="Toyota">Toyota</option>
        <option value="Kia">Kia</option>
      </select><br/>
      Año: <input type="number" name="anio" min="2000" max="2025" required /><br/>
      Precio: <input type="number" name="precio" step="0.01" min="0" required /><br/>
    </fieldset>

    <input type="submit" value="Registrar Venta" />
  </form>

<br/>
<form action="index.jsp" method="get">
  <input type="submit" value="← Volver al Inicio" />
</form>
</div>

</body>
</html>
