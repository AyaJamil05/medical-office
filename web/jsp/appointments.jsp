<%@page import="model.Appointment"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Rendez-vous</title>
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
        input[type="date"] {
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
        <h1>Liste des Rendez-vous</h1>

        <!-- Formulaire de filtrage -->
        <form action="/cabinet-medical/appointments" method="get">
            <label for="filterDate">Filtrer par date :</label>
            <input type="date" id="filterDate" name="filterDate" value="<%= request.getParameter("filterDate") != null ? request.getParameter("filterDate") : "" %>">
            <button type="submit">Filtrer</button>
            <button type="submit" name="resetFilter" value="true">Afficher tous les rendez-vous</button>
        </form>

        <p><a href="appointments?action=new" style="background-color: #6c63ff; color: white; padding: 10px; text-decoration: none; border-radius: 3px;">Ajouter un nouveau rendez-vous</a></p>

        <table>
            <thead>
                <tr>
                    <th>Patient</th>
                    <th>Médecin</th>
                    <th>Date</th>
                    <th>Heure</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments");
                    if (appointments != null && !appointments.isEmpty()) {
                        for (Appointment appointment : appointments) {
                %>
                <tr>
                    <td><%= appointment.getPatient().getName() %></td>
                    <td><%= appointment.getDoctor().getName() %></td>
                    <td><%= appointment.getDate() %></td>
                    <td><%= appointment.getTime() %></td>
                    <td class="actions">
                        <a href="appointments?action=edit&id=<%= appointment.getId() %>" class="edit">Modifier</a>
                        <a href="appointments?action=delete&id=<%= appointment.getId() %>" class="delete" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce rendez-vous ?')">Supprimer</a>
                    </td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5" style="text-align: center;">Aucun rendez-vous trouvé</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
