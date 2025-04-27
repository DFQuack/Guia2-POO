package sv.edu.udb.alumno.guia2poo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sv.edu.udb.alumno.guia2poo.importadora.Cliente;
import sv.edu.udb.alumno.guia2poo.importadora.Estadisticas;
import sv.edu.udb.alumno.guia2poo.importadora.Vehiculo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/VehiculoServlet")
public class VehiculoServlet extends HttpServlet {

    // Listas que almacenarán los datos de clientes y vehículos
    public List<Cliente> clientes = new ArrayList<>();
    public List<Vehiculo> vehiculos = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configura la codificación de caracteres para soportar caracteres especiales (UTF-8)
        request.setCharacterEncoding("UTF-8");

        // Recupera los parámetros del formulario enviados por el usuario
        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo");
        String marca = request.getParameter("marca");
        int anio;
        double precio;

        try {
            // Convierte los parámetros de año y precio a tipos adecuados (int y double)
            anio = Integer.parseInt(request.getParameter("anio"));
            precio = Double.parseDouble(request.getParameter("precio"));

            // Valida que todos los campos obligatorios estén completos
            if (nombre.isEmpty() || sexo == null || marca == null || marca.isEmpty()) {
                throw new Exception("Todos los campos son obligatorios.");
            }

            // Valida que el año del vehículo esté dentro del rango permitido (2000-2025)
            if (anio < 2000 || anio > 2025) {
                throw new Exception("El año debe estar entre 2000 y 2025.");
            }

            // Si todo es válido, crea los objetos Cliente y Vehiculo
            Cliente cliente = new Cliente(nombre, sexo);
            Vehiculo vehiculo = new Vehiculo(marca, anio, precio);

            // Añade los nuevos objetos a las listas de clientes y vehículos
            clientes.add(cliente);
            vehiculos.add(vehiculo);

            // Guarda los datos en los atributos del request para pasarlos al JSP
            request.setAttribute("clientes", clientes);  // Lista de clientes
            request.setAttribute("vehiculos", vehiculos); // Lista de vehículos

            // Calcula las estadísticas por marca y las agrega al request
            request.setAttribute("nissanTotal", Estadisticas.contarPorMarca(vehiculos, "Nissan"));
            request.setAttribute("nissanSuma", Estadisticas.sumarPorMarca(vehiculos, "Nissan"));
            request.setAttribute("toyotaTotal", Estadisticas.contarPorMarca(vehiculos, "Toyota"));
            request.setAttribute("toyotaSuma", Estadisticas.sumarPorMarca(vehiculos, "Toyota"));
            request.setAttribute("kiaTotal", Estadisticas.contarPorMarca(vehiculos, "Kia"));
            request.setAttribute("kiaSuma", Estadisticas.sumarPorMarca(vehiculos, "Kia"));

            // Calcula las estadísticas por rango de años y las agrega al request
            request.setAttribute("rango1", Estadisticas.contarPorRango(vehiculos, 2000, 2015));
            request.setAttribute("rango2", Estadisticas.contarPorRango(vehiculos, 2016, 2025));

            // Redirige al JSP que mostrará los resultados
            RequestDispatcher dispatcher = request.getRequestDispatcher("ResultadoEj4.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // Si ocurre un error, se captura la excepción y se pasa el mensaje de error al JSP
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("Ejercicio4.jsp").forward(request, response);
        }
    }
}
