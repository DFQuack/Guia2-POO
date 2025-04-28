<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Persona"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado - Cálculo de Vacaciones</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <div class="card">
                        <div class="card-header bg-success text-white">
                            <h3 class="text-center">Resultado del Cálculo</h3>
                        </div>
                        <div class="card-body">
                            <% 
                                Persona persona = (Persona) request.getAttribute("persona");
                                String tipoCalculadora = (String) request.getAttribute("tipoCalculadora");
                                if (persona != null) {
                            %>
                                <table class="table table-bordered">
                                    <tr>
                                        <th>Nombre Completo:</th>
                                        <td><%= persona.getNombres() + " " + persona.getApellidos() %></td>
                                    </tr>
                                    <tr>
                                        <th>Salario:</th>
                                        <td>$<%= String.format("%.2f", persona.getSalario()) %></td>
                                    </tr>
                                    <tr>
                                        <th>Fecha de Ingreso:</th>
                                        <td><%= persona.getFechaIngresoFormateada() %></td>
                                    </tr>
                                    <tr>
                                        <th>Años de Servicio:</th>
                                        <td><%= persona.getAniosServicio() %> años</td>
                                    </tr>
                                    <tr>
                                        <th>Tipo de Empleado:</th>
                                        <td>
                                            <% if (tipoCalculadora != null && tipoCalculadora.equals("EJECUTIVA")) { %>
                                                Ejecutivo
                                            <% } else { %>
                                                Empleado Estándar
                                            <% } %>
                                        </td>
                                    </tr>
                                    <tr class="table-primary">
                                        <th>Días de Vacaciones:</th>
                                        <td><strong><%= persona.getDiasVacaciones() %> días</strong></td>
                                    </tr>
                                </table>
                                
                                <div class="alert alert-info mt-3">
                                    <h5>Política de Vacaciones Aplicada:</h5>
                                    <% if (tipoCalculadora != null && tipoCalculadora.equals("EJECUTIVA")) { %>
                                        <ul>
                                            <li>Entre 1-2 años: 15 días</li>
                                            <li>Entre 2-4 años: 20 días</li>
                                            <li>Mayor a 4 años: 30 días</li>
                                        </ul>
                                    <% } else { %>
                                        <ul>
                                            <li>Entre 1-3 años: 10 días</li>
                                            <li>Entre 3-5 años: 15 días</li>
                                            <li>Mayor a 5 años: 21 días</li>
                                        </ul>
                                    <% } %>
                                </div>
                            <% } else { %>
                                <div class="alert alert-danger">
                                    No se encontraron datos de la persona.
                                </div>
                            <% } %>
                            
                            <div class="d-grid gap-2 mt-3">
                                <a href="index.jsp" class="btn btn-primary">Volver al formulario</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
