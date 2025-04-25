package sv.edu.udb.alumno.guia2poo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet("/ProcesarEstudianteServlet") // Define la URL para acceder al Servlet
public class ProcesarEstudianteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Patrones de Validación (Regex)
    private static final Pattern CARNET_PATTERN = Pattern.compile("^[A-Za-z]{2}\\d{4}$"); // LLNNNN
    private static final Pattern TELEFONO_PATTERN = Pattern.compile("^\\d{4}-\\d{4}$"); // NNNN-NNNN
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"); // Email básico
    private static final String DATE_FORMAT = "dd/MM/yyyy"; // Formato fecha DD/MM/YYYY

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Establecer tipo de contenido de la respuesta
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8"); // Para manejar tildes y caracteres especiales

        // 2. Obtener los parámetros del request
        String carnet = request.getParameter("carnet");
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");

        // 3. Lista para almacenar errores de validación
        List<String> errores = new ArrayList<>();

        // 4. Realizar Validaciones
        // Validación de campos obligatorios
        if (isNullOrEmpty(carnet)) errores.add("El campo Carnet es obligatorio.");
        if (isNullOrEmpty(nombres)) errores.add("El campo Nombres es obligatorio.");
        if (isNullOrEmpty(apellidos)) errores.add("El campo Apellidos es obligatorio.");
        if (isNullOrEmpty(direccion)) errores.add("El campo Dirección es obligatorio.");
        if (isNullOrEmpty(telefono)) errores.add("El campo Teléfono es obligatorio.");
        if (isNullOrEmpty(email)) errores.add("El campo E-mail es obligatorio.");
        if (isNullOrEmpty(fechaNacimientoStr)) errores.add("El campo Fecha de Nacimiento es obligatorio.");

        // Validaciones específicas si los campos no están vacíos
        if (!isNullOrEmpty(carnet) && !CARNET_PATTERN.matcher(carnet).matches()) {
            errores.add("Formato de Carnet inválido. Debe ser LLNNNN (ej: AB1234).");
        }
        if (!isNullOrEmpty(nombres) && nombres.length() > 25) {
            errores.add("Los Nombres no deben exceder los 25 caracteres.");
        }
        if (!isNullOrEmpty(apellidos) && apellidos.length() > 25) {
            errores.add("Los Apellidos no deben exceder los 25 caracteres.");
        }
        if (!isNullOrEmpty(direccion) && direccion.length() > 255) {
            errores.add("La Dirección no debe exceder los 255 caracteres.");
        }
        if (!isNullOrEmpty(telefono) && !TELEFONO_PATTERN.matcher(telefono).matches()) {
            errores.add("Formato de Teléfono inválido. Debe ser NNNN-NNNN (ej: 1234-5678).");
        }
        if (!isNullOrEmpty(email) && !EMAIL_PATTERN.matcher(email).matches()) {
            errores.add("Formato de E-mail inválido.");
        }
        if (!isNullOrEmpty(fechaNacimientoStr)) {
            if (!isValidDate(fechaNacimientoStr)) {
                errores.add("Formato de Fecha de Nacimiento inválido. Debe ser DD/MM/YYYY y una fecha válida.");
            }
        }

        // 5. Generar la respuesta HTML
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"es\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Resultado Registro Estudiante</title>");
            out.println("<style>");
            out.println("  body { font-family: sans-serif; margin: 20px; }");
            out.println("  table { border-collapse: collapse; width: 60%; margin-top: 20px; }");
            out.println("  th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
            out.println("  th { background-color: #f2f2f2; }");
            out.println("  .errores { color: red; border: 1px solid red; padding: 10px; margin-bottom: 20px; background-color: #ffebeb; }");
            out.println("  .errores ul { margin: 0; padding-left: 20px; }");
            out.println("  a { display: inline-block; margin-top: 15px; text-decoration: none; color: blue; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            if (errores.isEmpty()) {
                // No hay errores, mostrar tabla con datos
                out.println("<h1>Datos del Estudiante Registrado</h1>");
                out.println("<table>");
                out.println("  <tr><th>Campo</th><th>Valor</th></tr>");
                out.println("  <tr><td>Carnet</td><td>" + escapeHtml(carnet) + "</td></tr>");
                out.println("  <tr><td>Nombres</td><td>" + escapeHtml(nombres) + "</td></tr>");
                out.println("  <tr><td>Apellidos</td><td>" + escapeHtml(apellidos) + "</td></tr>");
                out.println("  <tr><td>Dirección</td><td>" + escapeHtml(direccion) + "</td></tr>");
                out.println("  <tr><td>Teléfono</td><td>" + escapeHtml(telefono) + "</td></tr>");
                out.println("  <tr><td>E-mail</td><td>" + escapeHtml(email) + "</td></tr>");
                out.println("  <tr><td>Fecha Nacimiento</td><td>" + escapeHtml(fechaNacimientoStr) + "</td></tr>");
                out.println("</table>");
            } else {
                // Hay errores, mostrar lista de errores
                out.println("<h1>Error al Procesar la Información</h1>");
                out.println("<div class=\"errores\">");
                out.println("  <p>Se encontraron los siguientes inconvenientes:</p>");
                out.println("  <ul>");
                for (String error : errores) {
                    out.println("    <li>" + escapeHtml(error) + "</li>");
                }
                out.println("  </ul>");
                out.println("</div>");
                // Opcional: Enlace para volver al formulario
                // Asume que tu formulario está en index.html en la raíz del contexto
                out.println("<a href=\"" + request.getContextPath() + "/ejercicio1.jsp\">Volver al Formulario</a>");
                 // O si el formulario está en la misma página o quieres usar el historial:
                 // out.println("<a href=\"javascript:history.back()\">Volver al Formulario</a>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // Método auxiliar para verificar si un String es nulo o vacío (después de quitar espacios)
    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // Método auxiliar para validar el formato y la validez de la fecha
    private boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false); // No permite fechas inválidas como 31/02/2023
        try {
            sdf.parse(dateStr); // Intenta parsear, si falla lanza ParseException
            // Verifica que el formato coincida exactamente (evita cosas como 1/1/2023 sin ceros)
            return Pattern.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$", dateStr);
        } catch (ParseException e) {
            return false; // Error al parsear = fecha inválida o formato incorrecto
        }
    }

    // Método auxiliar simple para escapar HTML y prevenir XSS básico
    private String escapeHtml(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#x27;")
                    .replace("/", "&#x2F;");
    }

    // Opcionalmente, puedes sobreescribir doGet si quieres manejar peticiones GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Redirigir GET a POST o mostrar un mensaje/formulario
        // Por simplicidad, aquí mostramos un mensaje de error
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h1>Error: Método GET no soportado</h1>");
        resp.getWriter().println("<p>Por favor, use el formulario para enviar datos vía POST.</p>");
    }
}
