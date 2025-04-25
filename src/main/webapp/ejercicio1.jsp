<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Estudiante</title>
    <style>
        body { font-family: sans-serif; margin: 20px; }
        form { max-width: 500px; border: 1px solid #ccc; padding: 20px; border-radius: 5px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="email"], input[type="date"] {
            width: calc(100% - 12px); 
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        small { color: #666; display: block; margin-top: -10px; margin-bottom: 10px; }
    </style>
</head>
<body>

    <h1>Formulario de Registro de Estudiante</h1>

    <!-- El action debe apuntar a la URL del Servlet definida en @WebServlet -->
    <form action="ProcesarEstudianteServlet" method="post">
        <div>
            <label for="carnet">Carnet:</label>
            <input type="text" id="carnet" name="carnet" required pattern="[A-Za-z]{2}\d{4}" title="Dos letras seguidas de cuatro números (ej: AB1234)">
            <small>Formato: LLNNNN (ej: AB1234)</small>
        </div>
        <div>
            <label for="nombres">Nombres:</label>
            <input type="text" id="nombres" name="nombres" required maxlength="25">
            <small>Máximo 25 caracteres.</small>
        </div>
        <div>
            <label for="apellidos">Apellidos:</label>
            <input type="text" id="apellidos" name="apellidos" required maxlength="25">
            <small>Máximo 25 caracteres.</small>
        </div>
        <div>
            <label for="direccion">Dirección:</label>
            <input type="text" id="direccion" name="direccion" required maxlength="255">
             <small>Máximo 255 caracteres.</small>
        </div>
        <div>
            <label for="telefono">Teléfono:</label>
            <input type="text" id="telefono" name="telefono" required pattern="\d{4}-\d{4}" title="Cuatro números, guión, cuatro números (ej: 1234-5678)">
            <small>Formato: NNNN-NNNN (ej: 1234-5678)</small>
        </div>
        <div>
            <label for="email">E-mail:</label>
            <input type="email" id="email" name="email" required>
            <small>Ingrese un correo electrónico válido.</small>
        </div>
        <div>
            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
            <input type="text" id="fechaNacimiento" name="fechaNacimiento" required pattern="(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\d{4}" title="Ingrese la fecha en formato DD/MM/YYYY">
            <small>Formato: DD/MM/YYYY</small>
        </div>

        <input type="submit" value="Registrar Estudiante">
    </form>

</body>
</html>
