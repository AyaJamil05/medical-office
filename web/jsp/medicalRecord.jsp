<%@page import="java.util.List"%>
<%@page import="model.MedicalRecord"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dossier Médical</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
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
            text-align: center;
            color: #2c3e50;
        }
        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin: 20px auto;
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
        .medical-record {
            margin-bottom: 20px;
            padding: 15px;
            background-color: #ecf0f1;
            border-left: 5px solid #6c63ff;
            border-radius: 5px;
        }
        .medical-record p {
            font-size: 16px;
            color: #2c3e50;
            line-height: 1.6;
        }
        .btn {
            display: inline-block;
            padding: 10px 15px;
            background-color: #6c63ff;
            color: white;
            font-size: 16px;
            border-radius: 5px;
            text-decoration: none;
            margin-top: 10px;
        }
        .btn:hover {
            background-color: #5a53e0;
        }
        .btn-delete {
            background-color: #e74c3c;
            border: none;
            padding: 10px 15px;
            color: white;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn-delete:hover {
            background-color: #c0392b;
        }
        .no-record-message {
            font-size: 18px;
            color: #e74c3c;
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
        <div class="container">
            <h1>Dossier Médical</h1>
            <p class="patient-info">Patient : <strong>${patientName}</strong></p>

            <c:if test="${not empty medicalRecords}">
                <c:forEach var="record" items="${medicalRecords}">
                    <div class="medical-record">
                        <p><strong>Diagnostiques :</strong> ${record.diagnostiques}</p>
                        <p><strong>Médications :</strong> ${record.medications}</p>
                        <p><strong>Notes du médecin :</strong> ${record.notesDuMedecin}</p>
                        <p><strong>Date de Création :</strong> ${record.createdAt}</p>
                        <p><strong>Dernière Mise à Jour :</strong> ${record.lastUpdate}</p>
                    </div>
                    <a href="MedicalRecordServlet?action=edit&recordId=${record.id}&patientName=${patientName}" class="btn">Modifier le Dossier Médical</a>
                    <form action="MedicalRecordServlet" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="recordId" value="${record.id}">
                        <input type="hidden" name="patientId" value="${patientId}">
                        <input type="hidden" name="patientName" value="${patientName}">
                        <button type="submit" class="btn-delete">Supprimer</button>
                    </form>
                </c:forEach>
            </c:if>

            <c:if test="${empty medicalRecords}">
                <p class="no-record-message">${noMedicalRecordMessage}</p>
                <a href="MedicalRecordServlet?action=create&patientId=${patientId}&patientName=${patientName}" class="btn">Créer un Dossier Médical</a>
            </c:if>

            <p><a href="PatientServlet" class="btn">Retour à la liste des patients</a></p>
        </div>
    </div>
</body>
</html>





