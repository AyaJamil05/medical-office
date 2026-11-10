package controller;

import dao.AppointmentDAO;
import dao.DoctorDAO;
import dao.PatientDAO;
import model.Appointment;
import model.Doctor;
import model.Patient;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;


@WebServlet(name = "appointments", urlPatterns = {"/appointments"})
public class AppointmentServlet extends HttpServlet {
    private AppointmentDAO appointmentDAO;
    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;

    @Override
    public void init() throws ServletException {
        appointmentDAO = new AppointmentDAO();
        patientDAO = new PatientDAO();
        doctorDAO = new DoctorDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String filterDate = request.getParameter("filterDate");
        String resetFilter = request.getParameter("resetFilter");
        try {
            if (action == null) {
                listAppointments(request, response, filterDate, resetFilter); // Passer resetFilter à la méthode
            } else {
                switch (action) {
                    case "new":
                        showCreateForm(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "delete":
                        deleteAppointment(request, response);
                        break;
                    default:
                        listAppointments(request, response, filterDate, resetFilter); // Passer resetFilter
                        break;
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String filterDate = request.getParameter("filterDate");
        String resetFilter = request.getParameter("resetFilter"); // Ajout de la gestion du bouton "Afficher tout"
        try {
            if (action == null) {
                listAppointments(request, response, filterDate, resetFilter); // Passer resetFilter à la méthode
            } else {
                switch (action) {
                    case "new":
                        createAppointment(request, response);
                        break;
                    case "edit":
                        updateAppointment(request, response);
                        break;
                    case "delete":
                        deleteAppointment(request, response);
                        break;
                    default:
                        listAppointments(request, response, filterDate, resetFilter); // Passer resetFilter
                        break;
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        } 
    }

    private void listAppointments(HttpServletRequest request, HttpServletResponse response, String filterDate, String resetFilter) throws ServletException, IOException {
        try {
            List<Appointment> appointments;
            if (resetFilter != null && resetFilter.equals("true")) {
                // Si le bouton "Afficher tous les rendez-vous" a été cliqué
                appointments = appointmentDAO.getAllAppointments();
            } else if (filterDate != null && !filterDate.isEmpty()) {
                // Appliquer le filtrage par date
                appointments = appointmentDAO.getAppointmentsByDate(LocalDate.parse(filterDate));
            } else {
                // Si aucun filtre n'est appliqué, afficher tous les rendez-vous
                appointments = appointmentDAO.getAllAppointments();
            }
            request.setAttribute("appointments", appointments);
            request.getRequestDispatcher("jsp/appointments.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Patient> patients = patientDAO.getAllPatients();
            List<Doctor> doctors = doctorDAO.getAllDoctors();

            request.setAttribute("patients", patients);
            request.setAttribute("doctors", doctors);

            request.getRequestDispatcher("/jsp/create-appointment.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Appointment existingAppointment = appointmentDAO.getAppointmentById(id);

            List<Patient> patients = patientDAO.getAllPatients();
            List<Doctor> doctors = doctorDAO.getAllDoctors();

            request.setAttribute("appointment", existingAppointment);
            request.setAttribute("patients", patients);
            request.setAttribute("doctors", doctors);

            request.getRequestDispatcher("jsp/update-appointment.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void createAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long patientId = Long.parseLong(request.getParameter("patientId"));
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            LocalDate date = LocalDate.parse(request.getParameter("date"));
            LocalTime time = LocalTime.parse(request.getParameter("time"));

            Patient patient = patientDAO.getPatientById(patientId);
            Doctor doctor = doctorDAO.getDoctorById(doctorId);

            Appointment newAppointment = new Appointment(null, patient, doctor, date, time);
            appointmentDAO.createAppointment(newAppointment);

            // Ajouter un message de confirmation
            request.getSession().setAttribute("successMessage", "Rendez-vous créé avec succès !");
            response.sendRedirect("appointments");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // Mise à jour d'un rendez-vous
    private void updateAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Long patientId = Long.parseLong(request.getParameter("patientId"));
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            LocalDate date = LocalDate.parse(request.getParameter("date"));
            LocalTime time = LocalTime.parse(request.getParameter("time"));

            Patient patient = patientDAO.getPatientById(patientId);
            Doctor doctor = doctorDAO.getDoctorById(doctorId);

            Appointment updatedAppointment = new Appointment(id, patient, doctor, date, time);
            appointmentDAO.updateAppointment(updatedAppointment);

            // Ajouter un message de confirmation
            request.getSession().setAttribute("successMessage", "Rendez-vous modifié avec succès !");
            response.sendRedirect("appointments");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // Supprimer un rendez-vous
    private void deleteAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            appointmentDAO.deleteAppointment(id);

            // Rediriger vers la page des rendez-vous pour recharger la liste
            response.sendRedirect("appointments"); // Redirige vers la même page
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
