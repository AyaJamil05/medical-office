package controller;

import dao.MedicalRecordDAO;
import model.MedicalRecord;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Patient;

@WebServlet(name = "MedicalRecordPatientServlet", urlPatterns = {"/MedicalRecordPatientServlet"})
public class MedicalRecordPatientServlet extends HttpServlet {
    private MedicalRecordDAO medicalRecordDAO;

    @Override
    public void init() throws ServletException {
        medicalRecordDAO = new MedicalRecordDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifier si le patient est connecté
        Patient patient = (Patient) request.getSession().getAttribute("user");
        if (patient == null) {
            response.sendRedirect("jsp/login.jsp?error=not_authenticated");
            return;
        }

        try {
            // Récupérer les dossiers médicaux du patient
            int patientId = patient.getId().intValue();
            List<MedicalRecord> medicalRecords = medicalRecordDAO.getMedicalRecordsByPatientId(patientId);

            // Ajouter les dossiers au contexte de la requête
            request.setAttribute("medicalRecords", medicalRecords);

            // Rediriger vers la page JSP pour afficher les dossiers
            request.getRequestDispatcher("jsp/medicalRecordPatient.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("jsp/error.jsp");
        }
    }
}

