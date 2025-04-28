package com.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ContactServlet", urlPatterns = {"/contact"})
public class ContactServlet extends HttpServlet {

    private List<Contact> contacts = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        // Datos de ejemplo (opcional)
        contacts.add(new Contact("Juan Pérez", "555-1234567", "juan@example.com"));
        contacts.add(new Contact("María García", "555-7654321", "maria@example.com"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String redirectPath = request.getContextPath();

        try {
            if ("view".equals(action)) {
                request.setAttribute("contacts", contacts);
                request.getRequestDispatcher("/viewContacts.jsp").forward(request, response);
            }
            else if ("search".equals(action)) {
                handleSearch(request, response);
            }
            else {
                response.sendRedirect(redirectPath + "/index.jsp");
            }
        } catch (Exception e) {
            response.sendRedirect(redirectPath + "/error.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String redirectPath = request.getContextPath();

        try {
            if (!isValidContact(name, phone, email)) {
                request.setAttribute("error", "Datos inválidos. Verifique los campos.");
                request.getRequestDispatcher("/addContact.jsp").forward(request, response);
                return;
            }

            Contact newContact = new Contact(name, phone, email);
            contacts.add(newContact);
            response.sendRedirect(redirectPath + "/contact?action=view");
        } catch (Exception e) {
            response.sendRedirect(redirectPath + "/error.jsp");
        }
    }

    private void handleSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("searchName");
        List<Contact> results = new ArrayList<>();

        if (name != null && !name.trim().isEmpty()) {
            String searchTerm = name.trim().toLowerCase();
            for (Contact c : contacts) {
                if (c.getName().toLowerCase().contains(searchTerm)) {
                    results.add(c);
                }
            }
        }

        request.setAttribute("contacts", results);
        request.setAttribute("searchName", name);
        request.getRequestDispatcher("/searchContact.jsp").forward(request, response);
    }

    private boolean isValidContact(String name, String phone, String email) {
        return name != null && !name.trim().isEmpty() &&
                phone != null && !phone.trim().isEmpty() &&
                email != null && !email.trim().isEmpty() &&
                email.contains("@") && email.contains(".");
    }
}