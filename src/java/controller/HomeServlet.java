package controller;

import dao.PatientDAO;
import dao.DoctorDAO;
import dao.AppointmentDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = {"/HomeServlet"})
public class HomeServlet extends HttpServlet {
    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;
    private AppointmentDAO appointmentDAO;

    @Override
    public void init() throws ServletException {
        patientDAO = new PatientDAO();
        doctorDAO = new DoctorDAO();
        appointmentDAO = new AppointmentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int patientCount = patientDAO.countPatients();
            int doctorCount = doctorDAO.countDoctors();
            int appointmentCount = appointmentDAO.countAppointments();

            request.setAttribute("patientCount", patientCount);
            request.setAttribute("doctorCount", doctorCount);
            request.setAttribute("appointmentCount", appointmentCount);

            request.getRequestDispatcher("jsp/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération des données du tableau de bord.", e);
        }
    }
}

