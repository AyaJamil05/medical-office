<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil - Cabinet Médical</title>
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
            

            .introduction {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 60px;
                margin: 20px auto;
                max-width: 1200px;
                padding: 20px;
            }

            .introduction .text {
                max-width: 60%;
                padding-right: 50px;
            }
            
            .introduction .text h1{
                color : #6c63ff;
            }

            .introduction img {
                max-width: 37%;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }
            
            button{
                background-color: #6c63ff;
                color: white;
                border: none;
                border-radius: 7px;
                padding: 12px 22px;
                cursor: pointer;
                font-size: 18px;
                font-weight : bold;
            }

            .specialties {
                background-color: #6c63ff;
                text-align: center;
                padding: 18px;
                margin-top: 50px;
            }

            .specialties h1 {
                margin-bottom: 35px;
                color: #fff;
            }

            .specialties .cards {
                margin: 20px auto;
                max-width: 1200px;
                padding: 20px;
                display: flex;
                justify-content: space-around;
                gap: 20px;
            }

            .card {
                background-color: #fff;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                padding: 20px;
                text-align: center;
                width: 250px;
                color : #6c63ff;
            }

            .card img {
                width: 90%;
                height: auto;
                margin-bottom: 15px;
            }
            .doctors {
                margin: 20px auto;
                max-width: 1200px;
                padding: 20px;
            }
            
            .doctors h1{
                margin-bottom: 35px;
                color: #6c63ff;
                text-align: center;
            }

            .doctor-container {
                display: flex;
                justify-content: space-between;
                margin-bottom: 20px;
            }

            .doctor {
                display: flex;
                align-items: center;
                width: 48%; 
                padding: 15px;
            }

            .doctor img {
                width: 150px;
                height: 150px;
                border-radius: 50%;
                margin-right: 20px;
            }

            .doctor p {
                font-size: 18px;
            }
            
            .doctor p strong{
                color: #6c63ff;
                margin-bottom: 5px;
            }

            button:hover {
                background-color: #5750d6;
            }
        </style>
    </head>
    <body>
        <div class="menu">
            <nav>
                <a href="/cabinet-medical/home.jsp">Accueil</a>
                <a href="/cabinet-medical/MedicalRecordPatientServlet">Dossier médical</a>
                <a href="/cabinet-medical/AppointmentPatientServlet">Prendre un Rendez-vous</a>
                <a href="/cabinet-medical/LogoutServlet">Déconnexion</a>
            </nav>
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
                    <button type="button" onclick="window.location.href='/cabinet-medical/AppointmentPatientServlet';">Prendre un Rendez-vous</button>
                </div>
                <img src="/cabinet-medical/images/welcoming-nurse.jpg" alt="Cabinet">
            </div>

            <div class="specialties">
                <h1>Nos Spécialités</h1>
                <div class="cards">
                    <div class="card">
                        <img src="/cabinet-medical/images/Medecine-Generale.jpg" alt="Médecine Générale">
                        <h3>Médecine Générale</h3>
                        <p>Soins pour toute la famille</p>
                    </div>
                    <div class="card">
                        <img src="/cabinet-medical/images/pediatrie.jpg" alt="Pédiatrie">
                        <h3>Pédiatrie</h3>
                        <p>Santé des enfants</p>
                    </div>
                    <div class="card">
                        <img src="/cabinet-medical/images/cardiologie.jpg" alt="Dermatologie">
                        <h3>Cardiologie</h3>
                        <p>Soin du coeur</p>
                    </div>
                </div>
            </div>

            <div class="doctors">
                <h1>Nos Médecins</h1>
                <div class="doctor-container">
                    <div class="doctor">
                        <img src="/cabinet-medical/images/ahmed.jpg" alt="Dr. Ahmed">
                        <p><strong>Dr. Ahmed</strong><br>Spécialiste en Médecine Générale<br>13 ans d'expérience</p>
                    </div>
                    <div class="doctor">
                        <img src="/cabinet-medical/images/samira.jpg" alt="Dr. Samira">
                        <p><strong>Dr. Samira</strong><br>Spécialiste en Pédiatrie<br>5 ans d'expérience</p>
                    </div>
                </div>
                <div class="doctor-container">
                    <div class="doctor">
                        <img src="/cabinet-medical/images/fatima.jpg" alt="Dr. Leila">
                        <p><strong>Dr. Fatima</strong><br>Spécialiste en Dermatologie<br>17 ans d'expérience</p>
                    </div>
                    <div class="doctor">
                        <img src="/cabinet-medical/images/mohamed.jpg" alt="Dr. Youssef">
                        <p><strong>Dr. mohamed</strong><br>Spécialiste en Cardiologie<br>8 ans d'expérience</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

