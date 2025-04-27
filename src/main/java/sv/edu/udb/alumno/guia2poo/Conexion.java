package sv.edu.udb.alumno.guia2poo;
import java.sql.*;

public class Conexion {
    Connection conn;
    public Statement st = null;
    public PreparedStatement prepSt = null;

    public Conexion() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_clean", "root", "");
    }

    public Connection getConn() {
        return conn;
    }
}
