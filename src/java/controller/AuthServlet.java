package controller;

import dao.AdminDAO;
import dao.DoctorDAO;
import dao.PatientDAO;
import model.Admin;
import model.Doctor;
import model.Patient;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "authServlet", urlPatterns = {"/authServlet"})
public class AuthServlet extends HttpServlet {
    private AdminDAO adminDAO;
    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;

    @Override
    public void init() throws ServletException {
        adminDAO = new AdminDAO();
        doctorDAO = new DoctorDAO();
        patientDAO = new PatientDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Admin admin = adminDAO.getAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            request.getSession().setAttribute("user", admin); 
            response.sendRedirect("/cabinet-medical/HomeServlet"); 
            return;
        }

        Doctor doctor = doctorDAO.getDoctorByUsername(username);
        if (doctor != null && doctor.getPassword().equals(password)) {
            request.getSession().setAttribute("user", doctor); 
            response.sendRedirect("/cabinet-medical/HomeServlet"); 
            return;
        }
        
        Patient patient = patientDAO.getPatientByUsername(username); 
        if (patient != null && patient.getPassword().equals(password)) {
            request.getSession().setAttribute("user", patient); 
            response.sendRedirect("jsp/home.jsp"); 
            return;
        }

        response.sendRedirect("jsp/login.jsp?error=invalid");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
}
