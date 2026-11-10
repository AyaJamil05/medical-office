package controller;

import dao.AppointmentDAO;
import dao.DoctorDAO;
import model.Appointment;
import model.Patient;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Doctor;

@WebServlet(name = "AppointmentPatientServlet", urlPatterns = {"/AppointmentPatientServlet"})
public class AppointmentPatientServlet extends HttpServlet {
    private DoctorDAO doctorDAO;
    private AppointmentDAO appointmentDAO;

    @Override
    public void init() throws ServletException {
        doctorDAO = new DoctorDAO();
        appointmentDAO = new AppointmentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la liste des médecins pour le formulaire
        try {
            List<?> doctors = doctorDAO.getAllDoctors();
            request.setAttribute("doctors", doctors);
            request.getRequestDispatcher("/jsp/create-appointmentPatient.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("jsp/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les informations du formulaire
        Patient patient = (Patient) request.getSession().getAttribute("user");
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        LocalTime time = LocalTime.parse(request.getParameter("time"));

        try {
            // Vérifier si l'heure est déjà réservée
            boolean isTimeReserved = appointmentDAO.isTimeReserved(doctorId, date, time);

            if (isTimeReserved) {
                request.setAttribute("error", "L'heure sélectionnée est déjà réservée. Veuillez choisir une autre heure.");
                doGet(request, response);
                return;
            }

            // Enregistrer le rendez-vous
            Appointment appointment = new Appointment(null, patient, new Doctor(doctorId, null, null, null, null), date, time);
            appointmentDAO.createAppointment(appointment);

            request.setAttribute("success", "Rendez-vous enregistré avec succès !");
            doGet(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("jsp/error.jsp");
        }
    }
}
