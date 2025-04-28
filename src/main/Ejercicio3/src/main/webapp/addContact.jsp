<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Agregar Contacto</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<div class="container">
  <h1>Agregar Nuevo Contacto</h1>

  <% if (request.getAttribute("error") != null) { %>
  <div class="error"><%= request.getAttribute("error") %></div>
  <% } %>

  <form action="${pageContext.request.contextPath}/contact" method="post">
    <input type="hidden" name="action" value="add">

    <div class="form-group">
      <label for="name">Nombre:</label>
      <input type="text" id="name" name="name" required>
    </div>

    <div class="form-group">
      <label for="phone">Teléfono:</label>
      <input type="text" id="phone" name="phone" required>
    </div>

    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" id="email" name="email" required>
    </div>

    <button type="submit">Guardar</button>
    <a href="${pageContext.request.contextPath}/index.jsp" class="btn cancel">Cancelar</a>
  </form>
</div>
</body>
</html>