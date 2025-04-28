<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cálculo de Vacaciones</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <div class="card">
                        <div class="card-header bg-primary text-white">
                            <h3 class="text-center">Cálculo de Vacaciones</h3>
                        </div>
                        <div class="card-body">
                            <form action="CalculoVacacionesServlet" method="post">
                                <div class="mb-3">
                                    <label for="nombres" class="form-label">Nombres:</label>
                                    <input type="text" class="form-control" id="nombres" name="nombres" required>
                                </div>
                                <div class="mb-3">
                                    <label for="apellidos" class="form-label">Apellidos:</label>
                                    <input type="text" class="form-control" id="apellidos" name="apellidos" required>
                                </div>
                                <div class="mb-3">
                                    <label for="salario" class="form-label">Salario ($):</label>
                                    <input type="text" class="form-control" id="salario" name="salario" required>
                                </div>
                                <div class="mb-3">
                                    <label for="fechaIngreso" class="form-label">Fecha de Ingreso:</label>
                                    <input type="text" class="form-control" id="fechaIngreso" name="fechaIngreso" 
                                           placeholder="dd/MM/yyyy (Ejemplo: 15/04/2023)" required>
                                    <div class="form-text text-muted">Formato: día/mes/año (dd/MM/yyyy)</div>
                                </div>
                                <div class="mb-3">
                                    <label for="tipoCalculadora" class="form-label">Tipo de Empleado:</label>
                                    <select class="form-select" id="tipoCalculadora" name="tipoCalculadora">
                                        <option value="ESTANDAR" selected>Empleado Estándar</option>
                                        <option value="EJECUTIVA">Ejecutivo</option>
                                    </select>
                                    <div class="form-text text-muted">El tipo de empleado determina la política de vacaciones aplicable</div>
                                </div>
                                <div class="d-grid gap-2">
                                    <button type="submit" class="btn btn-primary">Calcular Vacaciones</button>
                                </div>
                            </form>
                            
                            <% if (request.getAttribute("error") != null) { %>
                                <div class="alert alert-danger mt-3">
                                    <%= request.getAttribute("error") %>
                                </div>
                            <% } %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
