<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier le Dossier Médical</title>
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
        .patient-info {
            font-size: 18px;
            font-weight: bold;
            color: #2c3e50;
            margin-bottom: 20px;
        }
        .patient-info strong {
            color: #6c63ff;
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
        textarea{
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
            <h1>Modifier le Dossier Médical</h1>
            <p class="patient-info">Patient : <strong>${patientName}</strong></p>

            <form action="MedicalRecordServlet" method="post">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="recordId" value="${medicalRecord.id}">
                <input type="hidden" name="patientName" value="${patientName}">

                <label for="diagnostiques">Diagnostiques :</label>
                <textarea name="diagnostiques" required>${medicalRecord.diagnostiques}</textarea><br>

                <label for="medications">Médications :</label>
                <textarea name="medications" required>${medicalRecord.medications}</textarea><br>

                <label for="notesDuMedecin">Notes du Médecin :</label>
                <textarea name="notesDuMedecin">${medicalRecord.notesDuMedecin}</textarea><br>

                <button type="submit">Mettre à jour</button>
            </form>
        </div>
    </body>
</html>


