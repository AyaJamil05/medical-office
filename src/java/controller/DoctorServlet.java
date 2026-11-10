package controller;

import dao.DoctorDAO;
import model.Doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DoctorServlet", urlPatterns = {"/DoctorServlet"})
public class DoctorServlet extends HttpServlet {
    private DoctorDAO doctorDAO;

    @Override
    public void init() throws ServletException {
        doctorDAO = new DoctorDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String filterName = request.getParameter("filterName");
        String clearFilter = request.getParameter("clearFilter");

        try {
            if (action == null) {
                // Si "clearFilter" est présent, ignorer le filtre
                if (clearFilter != null && clearFilter.equals("true")) {
                    filterName = null;
                }
                listDoctors(request, response, filterName);
            } else {
                switch (action) {
                    case "create":
                        showCreateForm(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "delete":
                        deleteDoctor(request, response);
                        break;
                    default:
                        listDoctors(request, response, filterName);
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
            if ("update".equals(action)) {
                updateDoctor(request, response);
            }else if ("create".equals(action)) {
                createDoctor(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // Afficher la liste des médecins avec ou sans filtre
    private void listDoctors(HttpServletRequest request, HttpServletResponse response, String filterName) throws ServletException, IOException {
        try {
            List<Doctor> doctors;
            if (filterName != null && !filterName.isEmpty()) {
                doctors = doctorDAO.getDoctorsByName(filterName);
            } else {
                doctors = doctorDAO.getAllDoctors();
            }
            request.setAttribute("doctors", doctors);
            request.getRequestDispatcher("jsp/doctors.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erreur lors du traitement des médecins.", e);
        }
    }
    
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/create-doctor.jsp").forward(request, response);
    }
    
    private void createDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");

            Doctor doctor = new Doctor(0, username, password, name, phone); // ID 0 car généré automatiquement
            doctorDAO.createDoctor(doctor);

            request.getSession().setAttribute("successMessage", "Médecin ajouté avec succès !");
            response.sendRedirect("DoctorServlet");
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de l'ajout du médecin.", e);
        }
    }


    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Doctor doctor = doctorDAO.getDoctorById(id);
        request.setAttribute("doctor", doctor);
        request.getRequestDispatcher("jsp/update-doctor.jsp").forward(request, response);
    }
    
    private void updateDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");

            Doctor doctor = new Doctor(id, username, null, name, phone);
            doctorDAO.updateDoctor(doctor);
            
            request.getSession().setAttribute("successMessage", "Patient modifié avec succès !");
            response.sendRedirect("DoctorServlet");
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la mise à jour du médecin.", e);
        }
    }


    // Supprimer un médecin
    private void deleteDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            doctorDAO.deleteDoctor(id);
            response.sendRedirect("DoctorServlet");
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la suppression du médecin.", e);
        }
    }
}

