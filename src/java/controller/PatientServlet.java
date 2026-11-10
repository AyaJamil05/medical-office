package controller;

import dao.PatientDAO;
import model.Patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "PatientServlet", urlPatterns = {"/PatientServlet"})
public class PatientServlet extends HttpServlet {
    private PatientDAO patientDAO;

    @Override
    public void init() throws ServletException {
        patientDAO = new PatientDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String filterName = request.getParameter("filterName");
        String resetFilter = request.getParameter("resetFilter");

        try {
            if (action == null) {
                listPatients(request, response, filterName, resetFilter);
            } else {
                switch (action) {
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "delete":
                        deletePatient(request, response);
                        break;
                    case "create":
                        showCreateForm(request, response);
                        break;
                    default:
                        listPatients(request, response, filterName, resetFilter);
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
        String filterName = request.getParameter("filterName");
        String resetFilter = request.getParameter("resetFilter");

        try {
            if (action == null) {
                listPatients(request, response, filterName, resetFilter);
            } else {
                switch (action) {
                    case "edit":
                        updatePatient(request, response);
                        break;
                    case "delete":
                        deletePatient(request, response);
                        break;
                    case "create":
                        createPatient(request, response);
                        break;
                    default:
                        listPatients(request, response, filterName, resetFilter);
                        break;
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // Afficher la liste des patients avec ou sans filtrage
    private void listPatients(HttpServletRequest request, HttpServletResponse response, String filterName, String resetFilter) throws ServletException, IOException {
        try {
            List<Patient> patients;
            if ("true".equals(resetFilter)) {
                // Réinitialiser le filtre et afficher tous les patients
                patients = patientDAO.getAllPatients();
            } else if (filterName != null && !filterName.isEmpty()) {
                // Appliquer le filtrage par nom
                patients = patientDAO.getPatientsByName(filterName);
            } else {
                // Afficher tous les patients par défaut
                patients = patientDAO.getAllPatients();
            }
            request.setAttribute("patients", patients);
            request.getRequestDispatcher("jsp/patients.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erreur lors du traitement des patients.", e);
        }
    }
    
    // Afficher le formulaire de création
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/create-patient.jsp").forward(request, response);
    }

    // Créer un patient
    private void createPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            LocalDate dob = LocalDate.parse(request.getParameter("dob"));
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");

            Patient patient = new Patient(username, password, name, dob, phone, email);
            patientDAO.createPatient(patient);

            // Ajouter un message de succès
            request.getSession().setAttribute("successMessage", "Patient ajouté avec succès !");

            // Rediriger vers la liste des patients
            response.sendRedirect("PatientServlet");
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la création du patient.", e);
        }
    }

    // Afficher le formulaire de modification
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Patient existingPatient = patientDAO.getPatientById(id);
            request.setAttribute("patient", existingPatient);
            request.getRequestDispatcher("jsp/update-patient.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la récupération des informations du patient.", e);
        }
    }

    // Mise à jour d'un patient
    private void updatePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            LocalDate dob = LocalDate.parse(request.getParameter("dob"));
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");

            Patient patient = new Patient(id, username, password, name, dob, phone, email);
            patientDAO.updatePatient(patient);

            request.getSession().setAttribute("successMessage", "Patient modifié avec succès !");
            response.sendRedirect("PatientServlet");
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la mise à jour du patient.", e);
        }
    }

    // Supprimer un patient
    private void deletePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            patientDAO.deletePatient(id);

            // Rediriger vers la page des patients pour recharger la liste
            response.sendRedirect("PatientServlet"); // Redirige vers la même page
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la suppression du patient.", e);
        }
    }
}


