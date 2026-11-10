<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mes Dossiers Médicaux</title>
    <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 0;
            }

            .menu {
                background-color: #6c63ff;
                color: white;
                display: flex;
                padding: 26px 18px;
                justify-content: center;
                align-items: center;
            }

            .menu a {
                color: white;
                text-decoration: none;
                margin: 0 30px;
                font-weight: bold;
                font-size: 16px;
                padding: 10px;
            }

            .menu a:hover {
                backgroud-color: #5750d6;
            }
            
        .content {
            flex: 1;
            padding: 20px;
            margin: 20px 180px;
        }
        h1 {
            text-align: center;
            color: #2c3e50;
        }
        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px 60px 40px 60px;
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
            border: #6c63ff;
            color: white;
            font-size: 16px;
            border-radius: 5px;
            text-decoration: none;
            margin-top: 10px;
        }
        .btn:hover {
            background-color: #5a53e0;
        }

        .no-record-message {
            font-size: 18px;
            color: #e74c3c;
        }
    </style>
</head>
    <body>
        <div class="menu">
            <nav>
                <a href="/cabinet-medical/jsp/home.jsp">Accueil</a>
                <a href="/cabinet-medical/MedicalRecordPatientServlet">Dossier médical</a>
                <a href="/cabinet-medical/AppointmentPatientServlet">Prendre un Rendez-vous</a>
                <a href="/cabinet-medical/LogoutServlet">Déconnexion</a>
            </nav>
        </div>
        <div class='content'>
            <div class='container'>
            <h2>Dossier Médical</h2>
                <p class="patient-info">Patient : <strong>${sessionScope.user.name}</strong></p>
                <c:if test="${not empty medicalRecords}">
                        <c:forEach var="record" items="${medicalRecords}">
                            <div class="medical-record">
                                <p><strong>Diagnostiques :</strong> ${record.diagnostiques}</p>
                                <p><strong>Médications :</strong> ${record.medications}</p>
                                <p><strong>Notes du médecin :</strong> ${record.notesDuMedecin}</p>
                                <p><strong>Date de Création :</strong> ${record.createdAt}</p>
                                <p><strong>Dernière Mise à Jour :</strong> ${record.lastUpdate}</p>
                            </div>
                        </c:forEach>
                </c:if>

                <c:if test="${empty medicalRecords}">
                    <p class="no-record-message">Vous n'avez aucun dossier médical pour le moment.</p>
                </c:if>
            </div>
        </div>
    </body>
</html>

