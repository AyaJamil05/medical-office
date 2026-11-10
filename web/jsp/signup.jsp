<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Nouveau Patient</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            display: flex;
        }
        .content {
            flex: 1;
            background-color: #ffffff;
            padding: 40px 80px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            margin: 20px 380px;
        }
        h1 {
                text-align: center;
                color: #333;
                font-size: 1.7rem;
                margin-bottom: 40px
        }
        /*form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }*/
        label {
            display: block;
            margin-bottom: 8px;
            font-size: 1rem;
            color: #333;
        }
        input[type="text"],
        input[type="email"],
        input[type="date"],
        input[type="password"]{
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
        
        .error-message {
            color: red;
            font-size: 14px;
            text-align: center;
            margin-bottom: 15px;
        }

    </style>
</head>
<body>
    <div class="content">
        <h1>Inscription</h1>
        <% 
            String error = request.getParameter("error");
            if (error != null && error.equals("internal")) {
        %>
            <div class="error-message">Une erreur est survenue. Veuillez réessayer.</div>
        <% } else if (error != null && error.equals("duplicate")) { %>
            <div class="error-message">Cet email ou nom d'utilisateur est déjà utilisé.</div>
        <% } %>

        <form action="SignUpServlet" method="post">
            <input type="hidden" name="action" value="create">
            <label for="username">Nom d'utilisateur :</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Mot de passe :</label>
            <input type="password" id="password" name="password" required>
            
            <label for="name">Nom et Prénom :</label>
            <input type="text" id="name" name="name" required>

            <label for="dob">Date de Naissance :</label>
            <input type="date" id="dob" name="dob" required>

            <label for="phone">Téléphone :</label>
            <input type="text" id="phone" name="phone" required>

            <label for="email">Email :</label>
            <input type="email" id="email" name="email" required>

            <button type="submit">Ajouter</button>
            <button type="button" onclick="window.location.href='/cabinet-medical/authServlet';">Annuler</button>
        </form>
    </div>
</body>
</html>
