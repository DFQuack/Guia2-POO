package sv.edu.udb.alumno.guia2poo;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "CarCleanServlet", urlPatterns = "/CarCleanServlet")
public class CarCleanServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        //Valores obtenidos del formulario
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        boolean vip = Boolean.parseBoolean(request.getParameter("vip"));
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        int año = Integer.parseInt(request.getParameter("año"));
        String tipo = request.getParameter("tipo");
        double precio;

        //Preparación de inserción de datos
        String query;
        int idCliente = 0, idAuto = 0;
        try {
            Conexion c = new Conexion();
            Connection conn = c.getConn();

            //Inserción en tabla cliente
            query = "INSERT INTO cliente(nombres, apellidos, vip) VALUES (?,?,?)";
            c.prepSt = conn.prepareStatement(query);
            c.prepSt.setString(1, nombres);
            c.prepSt.setString(2, apellidos);
            c.prepSt.setBoolean(3, vip);
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
            c.prepSt.setString(1, marca);
            c.prepSt.setString(2, modelo);
            c.prepSt.setInt(3, año);
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
            c.prepSt.setString(3, tipo);
            precio = switch (tipo) { //Cálculo del precio
                case "Motocicleta" -> 2.75;
                case "Sedán" -> 3.50;
                case "Camioneta" -> 4;
                case "Microbús" -> 5;
                case "Autobús" -> 7;
                default -> 1;
            };
            if (vip) {
                precio -= precio * 0.15;
            }
            c.prepSt.setDouble(4, precio);
            c.prepSt.executeUpdate();

            //Cerrando la conexión
            conn.close();
            response.sendRedirect("http://localhost:8080/ejercicio5.jsp");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}