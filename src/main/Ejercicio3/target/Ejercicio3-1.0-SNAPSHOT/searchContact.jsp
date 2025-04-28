<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.Contact" %>
<%
    List<Contact> contacts = (List<Contact>) request.getAttribute("contacts");
    String searchName = (String) request.getAttribute("searchName");
    String error = (String) request.getAttribute("error");
%>
<html>
<head>
    <title>Buscar Contactos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="container">
    <h1>Buscar Contactos</h1>

    <% if (error != null) { %>
    <div class="error"><%= error %></div>
    <% } %>

    <form action="contact" method="get">
        <input type="hidden" name="action" value="search">

        <div class="form-group">
            <label for="searchName">Nombre:</label>
            <input type="text" id="searchName" name="searchName"
                   value="<%= searchName != null ? searchName : "" %>" required>
        </div>

        <button type="submit">Buscar</button>
    </form>

    <% if (contacts != null && !contacts.isEmpty()) { %>
    <h2>Resultados</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Teléfono</th>
            <th>Email</th>
        </tr>
        <% for (Contact contact : contacts) { %>
        <tr>
            <td><%= contact.getId() %></td>
            <td><%= contact.getName() %></td>
            <td><%= contact.getPhone() %></td>
            <td><%= contact.getEmail() %></td>
        </tr>
        <% } %>
    </table>
    <% } %>

    <a href="index.jsp" class="btn">Volver al inicio</a>
</div>
</body>
</html>