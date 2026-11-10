<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Médecins</title>
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
        <h1>Liste des Médecins</h1>

        <form action="DoctorServlet" method="get">
            <input type="text" name="filterName" placeholder="Rechercher par nom">
            <button type="submit">Rechercher</button>
            <button type="submit" name="clearFilter" value="true">Afficher tous les médecins</button>
        </form>
        
        <p><a href="DoctorServlet?action=create" style="background-color: #6c63ff; color: white; padding: 10px; text-decoration: none; border-radius: 3px;">Ajouter un nouveau médecin</a></p>

        <c:if test="${not empty successMessage}">
            <div style="color: #155724; margin-bottom: 8px; margin-top: 10px;">
                ${successMessage}
            </div>
        </c:if>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom d'utilisateur</th>
                    <th>Nom et prénom</th>
                    <th>Téléphone</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${empty doctors}">
                    <tr>
                        <td colspan="5" style="text-align: center;">Aucun médecin trouvé</td>
                    </tr>
                </c:if>
                <c:forEach var="doctor" items="${doctors}">
                    <tr>
                        <td>${doctor.id}</td>
                        <td>${doctor.username}</td>
                        <td>${doctor.name}</td>
                        <td>${doctor.phone}</td>
                        <td class="actions">
                            <a href="DoctorServlet?action=edit&id=${doctor.id}" class="edit">Modifier</a>
                            <a href="DoctorServlet?action=delete&id=${doctor.id}" class="delete" onclick="return confirm('Etes-vous sûr de vouloir supprimer ce médecin ?')">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>


