<%@ page import="model.Patient" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier un Patient</title>
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
            margin-bottom: 20px;
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
        input[type="text"],
        input[type="email"],
        input[type="date"],
        input[type="tel"]{
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
        button:hover {
            background-color: #5750d6;
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
    <h1>Modifier Patient</h1>
        <form action="PatientServlet" method="post">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="id" value="${patient.id}">

            <label for="name">Nom et Prénom :</label>
            <input type="text" id="name" name="name" value="${patient.name}" required><br><br>

            <label for="dob">Date de Naissance :</label>
            <input type="date" id="dob" name="dob" value="${patient.dob}" required><br><br>

            <label for="phone">Téléphone :</label>
            <input type="tel" id="phone" name="phone" value="${patient.phone}" required><br><br>

            <label for="email">Email :</label>
            <input type="email" id="email" name="email" value="${patient.email}" required><br><br>

            <button type="submit">Mettre à jour</button>
            <button type="button" onclick="window.location.href='PatientServlet';">Annuler</button>
        </form>
    </div>
</body>
</html>

