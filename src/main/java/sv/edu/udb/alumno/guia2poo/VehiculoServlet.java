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

    public List<Cliente> clientes = new ArrayList<>();
    public List<Vehiculo> vehiculos = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String sexo = request.getParameter("sexo");
        String marca = request.getParameter("marca");
        int anio;
        double precio;

        try {
            anio = Integer.parseInt(request.getParameter("anio"));
            precio = Double.parseDouble(request.getParameter("precio"));

            if (nombre.isEmpty() || sexo == null || marca == null || marca.isEmpty()) {
                throw new Exception("Todos los campos son obligatorios.");
            }

            if (anio < 2000 || anio > 2025) {
                throw new Exception("El año debe estar entre 2000 y 2025.");
            }

            Cliente cliente = new Cliente(nombre, sexo);
            Vehiculo vehiculo = new Vehiculo(marca, anio, precio);

            clientes.add(cliente);
            vehiculos.add(vehiculo);

            // Guardar datos en atributos de request
            request.setAttribute("clientes", clientes);
            request.setAttribute("vehiculos", vehiculos);
            request.setAttribute("nissanTotal", Estadisticas.contarPorMarca(vehiculos, "Nissan"));
            request.setAttribute("nissanSuma", Estadisticas.sumarPorMarca(vehiculos, "Nissan"));
            request.setAttribute("toyotaTotal", Estadisticas.contarPorMarca(vehiculos, "Toyota"));
            request.setAttribute("toyotaSuma", Estadisticas.sumarPorMarca(vehiculos, "Toyota"));
            request.setAttribute("kiaTotal", Estadisticas.contarPorMarca(vehiculos, "Kia"));
            request.setAttribute("kiaSuma", Estadisticas.sumarPorMarca(vehiculos, "Kia"));
            request.setAttribute("rango1", Estadisticas.contarPorRango(vehiculos, 2000, 2015));
            request.setAttribute("rango2", Estadisticas.contarPorRango(vehiculos, 2016, 2025));

            // Redirigir al JSP de resultados
            RequestDispatcher dispatcher = request.getRequestDispatcher("ResultadoEj4.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("Ejercicio4.jsp").forward(request, response);
        }
    }
}
