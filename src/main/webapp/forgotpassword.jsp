<%-- 
    Document   : forgotpassword
    Created on : Mar 10, 2025, 2:22:08 PM
    Author     : NHATHCE181222
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <style>
        .logo img {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border: 5px solid #fff;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.2);
        }
        .card {
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        .btn-primary {
            background-color: #2575fc;
            border: none;
            transition: 0.3s;
        }
        .btn-primary:hover {
            background-color: #1a5ed8;
        }
        .form-control {
            border-radius: 10px;
        }
    </style>
</head>
<body>
    <div class="d-flex justify-content-center align-items-center vh-100">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card p-4 p-md-5">
                        <div class="text-center">
                            <div class="logo mb-3">
                                <img class="rounded-circle" src="img/logo_shop.jpg" alt="Logo">
                            </div>
                            <h2 class="fw-bold text-dark">Forgot Password</h2>
                            <p class="text-muted">Enter your email address to reset your password.</p>
                        </div>
                        <form action="ForgotPasswordServlet" method="POST">
                            <div class="mb-3">
                                <label for="email" class="form-label">Email <span class="text-danger">*</span></label>
                                <div class="input-group">
                                    <span class="input-group-text bg-light">
                                        <i class="bi bi-envelope"></i>
                                    </span>
                                    <input type="email" class="form-control" name="email" id="email" required 
                                           value="<%= request.getAttribute("enteredEmail") != null ? request.getAttribute("enteredEmail") : ""%>">
                                </div>
                                <% if (request.getAttribute("errorMessage") != null) {%>
                                <div class="text-danger mt-1"><%= request.getAttribute("errorMessage")%></div>
                                <% } %>
                            </div>
                            <div class="d-grid">
                                <button class="btn btn-primary btn-lg" type="submit">Reset Password</button>
                            </div>
                        </form>
                        <% if (request.getAttribute("successMessage") != null) {%>
                        <div class="alert alert-success mt-3 text-center" role="alert">
                            <%= request.getAttribute("successMessage")%>
                        </div>
                        <% }%>
                        <hr class="mt-4">
                        <p class="text-center m-0">
                            Remembered your password? 
                            <a href="signIn.jsp" class="link-primary text-decoration-none fw-bold">Log In</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>




