<%@ page import="model.Patient" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Doctor" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter un Rendez-Vous</title>
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
        select,
        input[type = "date"],
        input[type = "time"]{
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
            <h1>Prendre un Rendez-vous</h1>
            <form action="appointments" method="POST">
                <input type="hidden" name="action" value="new">

                <label for="patientId">Patient :</label>
                <select id="patientId" name="patientId" required>
                    <option value="">Sélectionner un Patient</option>
                    <c:forEach var="patient" items="${patients}">
                        <option value="${patient.id}">${patient.name}</option>
                    </c:forEach>
                </select><br><br>

                <label for="doctorId">Médecin :</label>
                <select id="doctorId" name="doctorId" required>
                    <option value="">Sélectionner un Médecin</option>
                    <c:forEach var="doctor" items="${doctors}">
                        <option value="${doctor.id}">${doctor.name}</option>
                    </c:forEach>
                </select><br><br>

                <label for="date">Date du Rendez-vous :</label>
                <input type="date" id="date" name="date" required><br><br>

                <label for="time">Heure du Rendez-vous :</label>
                <input type="time" id="time" name="time" required><br><br>

                <button type="submit">Enregistrer</button>
                <button type="button" onclick="window.location.href='appointments';">Annuler</button>
            </form>
        </div>
    </body>
</html>


