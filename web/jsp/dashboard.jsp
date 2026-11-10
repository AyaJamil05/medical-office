<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>Dashboard</title>
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

        .introduction {
            display: flex;
            justify-content: space-between;
            align-items: center;
            /*background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);*/
            padding: 30px;
            margin-bottom: 20px;
            
        }

        .introduction .text {
            flex: 1;
            padding-right: 50px;
        }

        .introduction img {
            width: 495px;
            height: auto;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #6c63ff;
            margin-bottom: 30px;
        }
        
        .dashboard-container{
            padding: 0px 30px;
        }

        .dashboard-cards {
            display: flex;
            justify-content: space-around;
            gap: 20px;
        }

        .card {
            background: #6c63ff;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            padding: 15px 10px;
            width: 300px;
            text-align: center;
        }

        .card h2 {
            color: #fff;
            margin-bottom: 5px;
        }

        .card p {
            font-size: 1.5em;
            font-weight: bold;
            color: #fff;
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
        <div class="introduction">
            <div class="text">
                <h1>Bienvenue dans notre cabinet</h1>
                <p>
                    Nous sommes dédiés à vous offrir des soins de qualité, avec une équipe de professionnels compétents et attentionnés. Notre priorité est votre bien-être et votre santé.<br><br>
                    Dans un environnement chaleureux et accueillant, nous mettons tout en œuvre pour répondre à vos besoins médicaux, en alliant expertise et écoute. Nous proposons une gamme complète de services adaptés à toute la famille, tout en veillant à ce que chaque consultation soit une expérience rassurante et personnalisée.<br><br>
                    Faites confiance à notre cabinet pour prendre soin de vous et de vos proches avec professionnalisme et humanité. Votre santé est notre vocation.<br><br>
                </p>
            </div>
            <img src="/cabinet-medical/images/welcoming-nurse.jpg" alt="Photo">
        </div>

        <div class="dashboard-container">
            <h1>Tableau de bord</h1>
            <div class="dashboard-cards">
                <div class="card">
                    <h2>Patients</h2>
                    <p id="patient-count">${patientCount}</p>
                </div>
                <div class="card">
                    <h2>Médecins</h2>
                    <p id="doctor-count">${doctorCount}</p>
                </div>
                <div class="card">
                    <h2>Rendez-vous</h2>
                    <p id="appointment-count">${appointmentCount}</p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>


