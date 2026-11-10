package controller;


import dao.PatientDAO;
import model.Patient;

import java.time.LocalDate;
import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {
private PatientDAO patientDAO;

    @Override
    public void init() throws ServletException {
        patientDAO = new PatientDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            try {
                // Récupérer les données du formulaire
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String name = request.getParameter("name");
                LocalDate dob = LocalDate.parse(request.getParameter("dob"));
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");

                // Créer un objet Patient
                Patient patient = new Patient(username, password, name, dob, phone, email);

                // Ajouter le patient dans la base de données
                try {
                    patientDAO.createPatient(patient);
                    response.sendRedirect("/cabinet-medical/authServlet?message=success");
                } catch (SQLException e) {
                    if (e.getMessage().contains("existe déjà")) {
                        response.sendRedirect("/jsp/signup.jsp?error=duplicate");
                    } else {
                        e.printStackTrace();
                        response.sendRedirect("/jsp/signup.jsp?error=internal");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("/jsp/signup.jsp?error=internal");
            }
        }
    }

}
