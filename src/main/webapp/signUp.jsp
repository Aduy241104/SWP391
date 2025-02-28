<%-- 
    Document   : signUp
    Created on : Feb 25, 2025, 1:31:29 PM
    Author     : NHATHCE181222
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/ter.css">
    <style>
        .error-message {
            color: red;
            font-size: 14px;
            margin-top: 5px;
        }
    </style>
</head>
<body>

    <div class="wrapper">
        <form action="SignUpController" method="post">
            <h1>Sign Up</h1>

            <p style="color: red;">
                <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
            </p>
            
            <div class="form-input">
                <input type="text" name="username" placeholder="Username" required>
                <span></span>
                <% if (request.getAttribute("errorUsername") != null) { %>
                    <small class="text-danger"><%= request.getAttribute("errorUsername") %></small>
                <% } %>
            </div>

            <div class="form-input">
                <input type="password" name="password" placeholder="Password" required>
                <span></span>
                <% if (request.getAttribute("errorNewPassword") != null) { %>
                    <small class="text-danger"><%= request.getAttribute("errorNewPassword") %></small>
                <% } %>
            </div>


            <div class="form-input">
                <input type="password" name="confirmPassword" placeholder="Confirm Password" required>
                <span></span>
                <% if (request.getAttribute("errorConfirmPassword") != null) { %>
                    <small class="text-danger"><%= request.getAttribute("errorConfirmPassword") %></small>
                <% } %>
            </div>

            <div class="form-input">
                <input type="email" name="email" placeholder="Email" required>
                <span></span>
                <% if (request.getAttribute("errorEmail") != null) { %>
                    <small class="text-danger"><%= request.getAttribute("errorEmail") %></small>
                <% } %>
            </div>

            <div class="form-input">
                <input type="text" name="fullName" placeholder="Full Name" required>
                <span></span>
            </div>

          <button type="submit" class="btn">Sign up</button>
        </form>
    </div>
    
</body>
</html>
