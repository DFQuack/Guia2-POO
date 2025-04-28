package controlador;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Persona;
import modelo.factory.CalculadoraVacacionesFactory.TipoCalculadora;

/**
 * Servlet que procesa los datos del formulario para el cálculo de vacaciones
 * @author Estudiante
 * @version 1.0
 * @since 26/04/2023
 */
@WebServlet("/CalculoVacacionesServlet")
public class CalculoVacacionesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor por defecto
     */
    public CalculoVacacionesServlet() {
        super();
    }

    /**
     * Procesa las solicitudes HTTP POST
     * @param request Solicitud del cliente
     * @param response Respuesta al cliente
     * @throws ServletException Si ocurre un error específico del servlet
     * @throws IOException Si ocurre un error de I/O
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("CalculoVacacionesServlet: Iniciando procesamiento de solicitud POST");
        
        // Configurar la codificación de caracteres
        request.setCharacterEncoding("UTF-8");
        
        try {
            // Obtener los parámetros del formulario
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String salarioStr = request.getParameter("salario");
            String fechaIngresoStr = request.getParameter("fechaIngreso");
            String tipoCalculadoraStr = request.getParameter("tipoCalculadora");
            
            System.out.println("Parámetros recibidos: nombres=" + nombres + 
                               ", apellidos=" + apellidos + 
                               ", salario=" + salarioStr + 
                               ", fechaIngreso=" + fechaIngresoStr + 
                               ", tipoCalculadora=" + tipoCalculadoraStr);
            
            // Validar que los campos no estén vacíos
            if (nombres == null || apellidos == null || salarioStr == null || fechaIngresoStr == null ||
                nombres.trim().isEmpty() || apellidos.trim().isEmpty() || 
                salarioStr.trim().isEmpty() || fechaIngresoStr.trim().isEmpty()) {
                
                System.out.println("Error: Campos vacíos");
                request.setAttribute("error", "Todos los campos son obligatorios");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }
            
            // Validar nombres y apellidos
            Persona.validarTexto(nombres, "Nombres");
            Persona.validarTexto(apellidos, "Apellidos");
            
            // Validar y convertir el salario
            double salario;
            try {
                salario = Double.parseDouble(salarioStr);
                Persona.validarSalario(salario);
            } catch (NumberFormatException e) {
                System.out.println("Error: Formato de salario inválido");
                request.setAttribute("error", "El salario debe ser un valor numérico");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }
            
            // Validar y convertir la fecha de ingreso
            Date fechaIngreso;
            try {
                fechaIngreso = Persona.validarFecha(fechaIngresoStr);
            } catch (IllegalArgumentException e) {
                // Captura errores de formato y validación de la fecha
                System.out.println("Error en fecha: " + e.getMessage());
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            } catch (ParseException e) {
                // Este caso ya debería estar cubierto por la validación con regex,
                // pero lo mantenemos por seguridad
                System.out.println("Error al parsear fecha: " + e.getMessage());
                request.setAttribute("error", "Error al procesar la fecha: " + e.getMessage());
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }
            
            // Determinar el tipo de calculadora
            TipoCalculadora tipoCalculadora = TipoCalculadora.ESTANDAR; // Valor por defecto
            if (tipoCalculadoraStr != null && !tipoCalculadoraStr.isEmpty()) {
                try {
                    tipoCalculadora = TipoCalculadora.valueOf(tipoCalculadoraStr);
                } catch (IllegalArgumentException e) {
                    // Si el valor no es válido, usamos el valor por defecto
                    System.out.println("Tipo de calculadora inválido, usando ESTANDAR");
                    tipoCalculadora = TipoCalculadora.ESTANDAR;
                }
            }
            
            // Crear objeto Persona con los datos validados y el tipo de calculadora
            Persona persona = new Persona(nombres, apellidos, salario, fechaIngreso, tipoCalculadora);
            
            System.out.println("Persona creada: " + persona.getNombres() + " " + persona.getApellidos() + 
                               ", Años de servicio: " + persona.getAniosServicio() + 
                               ", Días de vacaciones: " + persona.getDiasVacaciones());
            
            // Enviar el objeto Persona a la página de resultados
            request.setAttribute("persona", persona);
            request.setAttribute("tipoCalculadora", tipoCalculadora.name());
            
            System.out.println("Redirigiendo a resultado.jsp");
            request.getRequestDispatcher("resultado.jsp").forward(request, response);
            
        } catch (IllegalArgumentException e) {
            // Capturar cualquier excepción de validación
            System.out.println("Error de validación: " + e.getMessage());
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            // Capturar cualquier otra excepción
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        System.out.println("CalculoVacacionesServlet: Finalizando procesamiento de solicitud POST");
    }

    /**
     * Devuelve información sobre el servlet
     * @return Cadena con información del servlet
     */
    @Override
    public String getServletInfo() {
        return "Servlet para el cálculo de vacaciones";
    }
}
