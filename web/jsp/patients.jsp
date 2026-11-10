<%@page import="java.util.List"%>
<%@page import="model.Patient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Patients</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            display: flex;
        }
        .sidebar {
            width: 250px;
            background-color: #6c63ff;
            color: white;
            height: 100vh;
            padding: 20px 10px;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
        }
        .sidebar h2 {
            font-size: 24px;
            margin-bottom: 30px;
            text-align: center;
        }
        .sidebar a {
            display: block;
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            border-radius: 5px;
            margin-bottom: 10px;
            transition: background-color 0.3s;
        }
        .sidebar a:hover {
            background-color: #5750d6;
        }
        .content {
            flex: 1;
            padding: 20px;
        }
        h1 {
            text-align: left;
            font-size: 2rem;
            color: #333;
            margin-bottom: 20px;
        }
        form {
            margin-bottom: 20px;
        }
        input[type="text"] {
            padding: 8px;
            width: 200px;
            margin-right: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            padding: 8px 15px;
            background-color: #6c63ff;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #6c63ff;
            color: white;
        }
        .actions a {
            text-decoration: none;
            padding: 5px 10px;
            margin-right: 5px;
            border-radius: 3px;
        }
        .actions .edit {
            background-color: #6c63ff;
            color: white;
        }
        .actions .delete {
            background-color: #dc3545;
            color: white;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <h2>Menu</h2>
        <a href="/cabinet-medical/HomeServlet">Accueil</a>
        <a href="/cabinet-medical/PatientServlet">Patients</a>
        <a href="/cabinet-medical/DoctorServlet">Médecins</a>
        <a href="/cabinet-medical/appointments">Rendez-vous</a>
        <a href="/cabinet-medical/LogoutServlet">Déconnexion</a>
    </div>
    <div class="content">
        <h1>Liste des Patients</h1>

        <!-- Formulaire de filtrage -->
        <form action="PatientServlet" method="get">
            <input type="text" name="filterName" placeholder="Rechercher par nom" value="${param.filterName}">
            <button type="submit">Rechercher</button>
            <button type="submit" name="resetFilter" value="true">Afficher tous les patients</button>
        </form>

        <p><a href="PatientServlet?action=create" style="background-color: #6c63ff; color: white; padding: 10px; text-decoration: none; border-radius: 3px;">Ajouter un nouveau patient</a></p>

        <c:if test="${not empty successMessage}">
            <div style="color: #155724; margin-bottom: 8px; margin-top: 10px;">
                ${successMessage}
            </div>
        </c:if>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom et Prénom</th>
                    <th>Date de Naissance</th>
                    <th>Téléphone</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${empty patients}">
                    <tr>
                        <td colspan="6" style="text-align: center;">Aucun patient trouvé</td>
                    </tr>
                </c:if>

                <c:forEach var="patient" items="${patients}">
                    <tr>
                        <td>${patient.id}</td>
                        <td>${patient.name}</td>
                        <td>${patient.dob}</td>
                        <td>${patient.phone}</td>
                        <td>${patient.email}</td>
                        <td class="actions">
                            <a href="MedicalRecordServlet?patientId=${patient.id}&patientName=${patient.name}" class="edit">Dossier Médical</a>
                            <a href="PatientServlet?action=edit&id=${patient.id}" class="edit">Modifier</a>
                            <a href="PatientServlet?action=delete&id=${patient.id}" class="delete" onclick="return confirm('Etes-vous sûr de vouloir supprimer ce patient ?')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </div>
</body>
</html>



