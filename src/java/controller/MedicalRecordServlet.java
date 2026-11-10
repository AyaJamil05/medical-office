package controller;

import dao.MedicalRecordDAO;
import model.MedicalRecord;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "MedicalRecordServlet", urlPatterns = {"/MedicalRecordServlet"})
public class MedicalRecordServlet extends HttpServlet {
    private MedicalRecordDAO medicalRecordDAO;

    @Override
    public void init() throws ServletException {
        medicalRecordDAO = new MedicalRecordDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");


        try {
            if (action == null) {
                listMedicalRecords(request, response);
            } else {
                switch (action) {
                    case "create":
                        showCreateForm(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "delete":
                        deleteMedicalRecord(request, response);
                        break;
                    default:
                        listMedicalRecords(request, response);
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

        try {
            if (action == null) {
                listMedicalRecords(request, response);
            } else {
                switch (action) {
                    case "create":
                        createMedicalRecord(request, response);
                        break;
                    case "edit":
                        updateMedicalRecord(request, response);
                        break;
                    case "delete":
                        deleteMedicalRecord(request, response);
                        break; 
                    default:
                        listMedicalRecords(request, response);
                        break;
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listMedicalRecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 

                String patientIdParam = request.getParameter("patientId");
	String patientName = request.getParameter("patientName");
        try {
            if (patientIdParam != null) {
                int patientId = Integer.parseInt(patientIdParam);
                List<MedicalRecord> records = medicalRecordDAO.getMedicalRecordsByPatientId(patientId);

                request.setAttribute("medicalRecords", records);
                request.setAttribute("patientId", patientId);
                request.setAttribute("patientName", patientName);

                if (records.isEmpty()) {
                    request.setAttribute("noMedicalRecordMessage", "Aucun dossier médical trouvé");
                }
                
                request.getRequestDispatcher("jsp/medicalRecord.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Identifiant du patient manquant.");
            }
        } catch (SQLException e) {
            throw new ServletException("Erreur lors du chargement des dossiers médicaux.", e);
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String patientIdParam = request.getParameter("patientId");
        String patientName = request.getParameter("patientName");

        if (patientIdParam != null && patientName != null) {
            request.setAttribute("patientId", patientIdParam);
            request.setAttribute("patientName", patientName);
            request.getRequestDispatcher("/jsp/create-medicalRecord.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Informations du patient manquantes.");
        }
    }

    private void createMedicalRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int patientId = Integer.parseInt(request.getParameter("patientId"));
            String diagnostiques = request.getParameter("diagnostiques");
            String medications = request.getParameter("medications");
            String notesDuMedecin = request.getParameter("notesDuMedecin");

            MedicalRecord record = new MedicalRecord();
            record.setPatientId(patientId);
            record.setDiagnostiques(diagnostiques);
            record.setMedications(medications);
            record.setNotesDuMedecin(notesDuMedecin);

            medicalRecordDAO.createMedicalRecord(record);
            request.getSession().setAttribute("successMessage", "Dossier ajouté avec succès !");
            response.sendRedirect("PatientServlet");
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la création du dossier médical.", e);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String recordIdParam = request.getParameter("recordId");
            String patientName = request.getParameter("patientName");

            if (recordIdParam == null || patientName == null) {
                throw new IllegalArgumentException("Identifiant du dossier médical ou nom du patient manquant.");
            }

            int recordId = Integer.parseInt(recordIdParam);

            MedicalRecord record = medicalRecordDAO.getMedicalRecordById(recordId);

            if (record == null) {
                throw new SQLException("Dossier médical introuvable pour l'identifiant fourni.");
            }

            request.setAttribute("medicalRecord", record);
            request.setAttribute("patientName", patientName);

            request.getRequestDispatcher("/jsp/update-medicalRecord.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Identifiant du dossier médical invalide.");
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération du dossier médical.", e);
        }
    }


    private void updateMedicalRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int recordId = Integer.parseInt(request.getParameter("recordId"));
            String diagnostiques = request.getParameter("diagnostiques");
            String medications = request.getParameter("medications");
            String notesDuMedecin = request.getParameter("notesDuMedecin");

            if (diagnostiques == null || medications == null) {
                throw new IllegalArgumentException("Diagnostiques et médications sont obligatoires.");
            }

            MedicalRecord record = medicalRecordDAO.getMedicalRecordById(recordId);

            if (record == null) {
                throw new SQLException("Dossier médical introuvable pour l'identifiant fourni.");
            }

            record.setDiagnostiques(diagnostiques);
            record.setMedications(medications);
            record.setNotesDuMedecin(notesDuMedecin);

            medicalRecordDAO.updateMedicalRecord(record);

            request.getSession().setAttribute("successMessage", "Dossier modifié avec succès !");
            response.sendRedirect("PatientServlet");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Identifiant du dossier médical invalide.");
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la mise à jour du dossier médical.", e);
        }
    }
    
    private void deleteMedicalRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int recordId = Integer.parseInt(request.getParameter("recordId"));
            int patientId = Integer.parseInt(request.getParameter("patientId"));

            // Supprimer le dossier médical
            medicalRecordDAO.deleteMedicalRecord(recordId);

            // Message de succès
            request.getSession().setAttribute("successMessage", "Dossier supprimé avec succès !");

            // Utiliser redirection après suppression pour éviter toute tentative de forward après la réponse
            response.sendRedirect("MedicalRecordServlet?patientId=" + patientId);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Identifiant du dossier médical invalide.");
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la suppression du dossier médical.", e);
        }
    }

}


