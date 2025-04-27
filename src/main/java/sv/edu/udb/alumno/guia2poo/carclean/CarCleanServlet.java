package sv.edu.udb.alumno.guia2poo.carclean;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "CarCleanServlet", urlPatterns = "/CarCleanServlet")
public class CarCleanServlet extends HttpServlet {
    /*
     * Ejercicio 5 hecho por Daniel Enrique Flores Lino.
     * Última edición el 27/abril/2025
     * */

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Valores obtenidos del formulario. Algunos se obtienen más abajo.
        Cliente cliente = new Cliente();
        cliente.setNombre(request.getParameter("nombres"));
        cliente.setApellidos(request.getParameter("apellidos"));
        cliente.setVip(Boolean.parseBoolean(request.getParameter("vip")));
        Automotor auto = new Automotor();
        auto.setMarca(request.getParameter("marca"));
        auto.setModelo(request.getParameter("modelo"));
        Servicio servicio = new Servicio();
        servicio.setTipo(request.getParameter("tipo"));

        //Validación de datos
        StringBuilder error = new StringBuilder();
        boolean hayError = false;
        if (cliente.getNombre().isEmpty()) {
            error.append("El campo de nombre no puede estar vacío.<br>");
            hayError = true;
        }
        if (cliente.getApellidos().isEmpty()) {
            error.append("El campo de apellido no puede estar vacío.<br>");
            hayError = true;
        }
        if (auto.getMarca().isEmpty()) {
            error.append("El campo de marca no puede estar vacío.<br>");
            hayError = true;
        }
        if (auto.getModelo().isEmpty()) {
            error.append("El campo de modelo no puede estar vacío.<br>");
            hayError = true;
        }
        try {
            auto.setAño(Integer.parseInt(request.getParameter("año")));
            if (auto.getAño() < 1950 || auto.getAño() > 2025) {
                error.append("El año del vehículo debe estar entre 1950 y 2025.<br>");
                hayError = true;
            }
        } catch (NumberFormatException e) {
            error.append("El campo de año debe tener un valor numérico.<br>");
            hayError = true;
        }
        if (hayError) {
            request.setAttribute("error", error.toString());
            request.getRequestDispatcher("ejercicio5-add.jsp").forward(request, response);
            return;
        }

        //Preparación de inserción de datos
        String query;
        int idCliente = 0, idAuto = 0;
        try {
            Conexion c = new Conexion();
            Connection conn = c.getConn();

            //Inserción en tabla cliente
            query = "INSERT INTO cliente(nombres, apellidos, vip) VALUES (?,?,?)";
            c.prepSt = conn.prepareStatement(query);
            c.prepSt.setString(1, cliente.getNombre());
            c.prepSt.setString(2, cliente.getApellidos());
            c.prepSt.setBoolean(3, cliente.isVip());
            c.prepSt.executeUpdate();
            //Obteniendo el id del cliente insertado
            query = "SELECT LAST_INSERT_ID()";
            c.st = conn.createStatement();
            ResultSet rs = c.st.executeQuery(query);
            while (rs.next()) {
                idCliente = rs.getInt(1);
            }
            //Inserción en tabla automotor
            query = "INSERT INTO automotor(marca, modelo, año) VALUES (?,?,?)";
            c.prepSt = conn.prepareStatement(query);
            c.prepSt.setString(1, auto.getMarca());
            c.prepSt.setString(2, auto.getModelo());
            c.prepSt.setInt(3, auto.getAño());
            c.prepSt.executeUpdate();
            //Obteniendo el id del auto insertado
            query = "SELECT LAST_INSERT_ID()";
            c.st = conn.createStatement();
            rs = c.st.executeQuery(query);
            while (rs.next()) {
                idAuto = rs.getInt(1);
            }
            //Inserción en tabla servicio
            query = "INSERT INTO servicio VALUES (?,?,?,?)";
            c.prepSt = conn.prepareStatement(query);
            c.prepSt.setInt(1, idCliente);
            c.prepSt.setInt(2, idAuto);
            c.prepSt.setString(3, servicio.getTipo());
            servicio.setPrecio(cliente.totalPago(servicio.getTipo(),cliente.isVip()));
            c.prepSt.setDouble(4, servicio.getPrecio());
            c.prepSt.executeUpdate();

            //Cerrando la conexión
            conn.close();
            response.sendRedirect("http://localhost:8080/ejercicio5.jsp");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}