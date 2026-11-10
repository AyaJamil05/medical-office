<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            section {
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                padding: 2rem;
                width: 100%;
                max-width: 600px;
                margin-top: 20px;
            }

            h2 {
                text-align: center;
                color: #333;
                font-size: 1.5rem;
            }


            /* Formulaire */
            form {
                display: flex;
                flex-direction: column;
                gap: 1rem;
            }

            form div {
                display: flex;
                flex-direction: column;
            }

            label {
                font-size: 1rem;
                color: #333;
                margin-bottom: 0.5rem;
            }

            input[type="text"],
            input[type="password"] {
                padding: 0.8rem;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 1rem;
            }

            input[type="text"]:focus,
            input[type="password"]:focus {
                outline: none;
                border-color: #6c63ff;
            }

            button {
                background-color: #6c63ff;
                color: white;
                padding: 0.8rem 1.5rem;
                border: none;
                border-radius: 4px;
                font-size: 1rem;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            button:hover {
                background-color: #5a53e0;
            }

            button:active {
                background-color: #4a46c0;
            }
            
            .error-message {
                color: red;
                font-size: 14px;
                text-align: center;
                margin-bottom: 15px;
            }
            .success-message {
                color: green;
                font-size: 14px;
                text-align: center;
                margin-bottom: 15px;
            }
            
            a {
                color: #6c63ff;
                font-size: 15px;
                text-align: center;
                margin-top: 15px;
            }
        </style>
    </head>
    <body>
        <section>
            <h2>Connexion</h2>
            <form action="/cabinet-medical/authServlet" method="post">
                <% 
                    String error = request.getParameter("error");
                    if (error != null && error.equals("invalid")) {
                %>
                    <div class="error-message">Identifiants incorrects. Veuillez réessayer.</div>
                <% } %>
                <% 
                    String message = request.getParameter("message");
                    if (message != null && message.equals("success")) {
                %>
                    <div class="success-message">Inscription réussie ! Vous pouvez vous connecter.</div>
                <% } %>

                <div class="form-group">
                    <label for="username">Nom d'utilisateur</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Mot de passe</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit">Se connecter</button>
                <a href="SignUpServlet">Vous n'avez pas de compte? Inscrivez-vous.</a>
            </form>
        </section>
    </body>
</html>
