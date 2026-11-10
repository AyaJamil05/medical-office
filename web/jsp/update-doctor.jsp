<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier Médecin</title>
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
        form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            padding: 10px 15px;
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
        <h1>Modifier Médecin</h1>
        <form action="DoctorServlet" method="post">
            <input type="hidden" name="id" value="${doctor.id}">
            <label for="username">Nom d'utilisateur :</label>
            <input type="text" id="username" name="username" value="${doctor.username}" required>

            <label for="name">Nom et prénom :</label>
            <input type="text" id="name" name="name" value="${doctor.name}" required>

            <label for="phone">Téléphone :</label>
            <input type="text" id="phone" name="phone" value="${doctor.phone}" required>

            <button type="submit" name="action" value="update">Mettre à jour</button>
            <button type="button" onclick="window.location.href='DoctorServlet';">Annuler</button>
        </form>
    </div>
</body>
</html>

