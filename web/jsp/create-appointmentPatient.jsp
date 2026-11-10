<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Prendre un Rendez-vous</title>
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
                font-size: 17px;
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
            margin-bottom: 30px;
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
            padding: 20px 60px 40px 60px;
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
        <div class="menu">
            <nav>
                <a href="/cabinet-medical/jsp/home.jsp">Accueil</a>
                <a href="/cabinet-medical/MedicalRecordPatientServlet">Dossier médical</a>
                <a href="/cabinet-medical/AppointmentPatientServlet">Prendre un Rendez-vous</a>
                <a href="/cabinet-medical/LogoutServlet">Déconnexion</a>
            </nav>
        </div>
        <div class="content">

            <form action="AppointmentPatientServlet" method="post">
                <h1>Prendre un Rendez-vous</h1>
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
                
                <c:if test="${not empty error}">
                    <p style="color: red; margin-top: -10px;">${error}</p>
                </c:if>
                <c:if test="${not empty success}">
                    <p style="color: green; margin-top: -10px;">${success}</p>
                </c:if>

                <button type="submit">Enregistrer</button>
                <button type="button" onclick="window.location.href='';">Annuler</button>
            </form>
        </div>
    </body>
</html>

