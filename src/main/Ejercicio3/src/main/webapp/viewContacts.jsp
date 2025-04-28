<%@ page import="java.util.List, com.example.Contact" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Buscar Contactos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="header">
    <h1>Buscar Contactos</h1>
</div>

<div class="container">
    <nav class="nav">
        <a href="${pageContext.request.contextPath}/addContact.jsp" class="nav-link">Agregar Contacto</a>
        <a href="${pageContext.request.contextPath}/contact?action=view" class="nav-link">Ver Contactos</a>
        <a href="${pageContext.request.contextPath}/searchContact.jsp" class="nav-link active">Buscar Contactos</a>
    </nav>

    <div class="form-container">
        <form action="${pageContext.request.contextPath}/contact" method="get">
            <input type="hidden" name="action" value="search">

            <div class="form-group">
                <label for="searchName">Nombre:</label>
                <input type="text" id="searchName" name="searchName" class="form-control"
                       value="<%= request.getAttribute("searchName") != null ? request.getAttribute("searchName") : "" %>">
            </div>

            <div class="btn-group">
                <button type="submit" class="btn btn-primary">Buscar</button>
            </div>
        </form>
    </div>

    <%
        List<Contact> results = (List<Contact>) request.getAttribute("contacts");
        if (results != null) {
    %>
    <div class="table-container">
        <h2>Resultados de la búsqueda</h2>
        <%
            if (results.isEmpty()) {
        %>
        <p>No se encontraron contactos</p>
        <%
        } else {
        %>
        <table class="table">
            <thead>
            <tr>
                <th>Nombre</th>
                <th>Teléfono</th>
                <th>Email</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Contact contact : results) {
            %>
            <tr>
                <td><%= contact.getName() %></td>
                <td><%= contact.getPhone() %></td>
                <td><%= contact.getEmail() %></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <%
            }
        %>
    </div>
    <%
        }
    %>
</div>
</body>
</html>