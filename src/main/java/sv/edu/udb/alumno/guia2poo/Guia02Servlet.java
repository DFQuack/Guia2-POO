package sv.edu.udb.alumno.guia2poo;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Guia02Servlet", urlPatterns = "/Guia02Servlet")
public class Guia02Servlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProcessRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProcessRequest(request, response);
    }

    protected void ProcessRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Hola</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Resultado de Servlet</h1>");
            out.println("<p>");
            out.println("<b>Nombre de la persona: </b>" + request.getParameter("nombre") + "<br>");
            out.println("<b>Apellido de la persona: </b>" + request.getParameter("apellido") + "<br>");
            out.println("</p>");
            out.println("</body></html>");
        }
    }

    public void destroy() {
    }
}