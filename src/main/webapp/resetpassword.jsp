<%-- 
    Document   : resetpassword
    Created on : Mar 10, 2025, 2:49:53 PM
    Author     : NHATHCE181222
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>


    <div class="bg-light py-3 py-md-5">
        <div class="container">
            <div class="row justify-content-md-center">
                <div class="col-12 col-md-11 col-lg-8 col-xl-7 col-xxl-6">
                    <div class="bg-white p-4 p-md-5 rounded shadow-sm">
                        <div class="text-center mb-5">
                            <h2 class="fw-bold">Reset Password</h2>
                            <p class="text-secondary">Enter your new password below.</p>
                        </div>

                        <!-- Form nhập mật khẩu mới -->
                        <form action="ResetPasswordServlet" method="POST">
                            <input type="hidden" name="token" value="<%= request.getParameter("token") %>">

                            <div class="mb-3">
                                <label for="password" class="form-label">New Password</label>
                                <input type="password" class="form-control" name="password" id="password" required>
                            </div>

                            <div class="mb-3">
                                <label for="confirmPassword" class="form-label">Confirm Password</label>
                                <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" required>
                            </div>

                            <div class="d-grid">
                                <button class="btn btn-primary btn-lg" type="submit">Reset Password</button>
                            </div>
                        </form>

                        <% if (request.getAttribute("errorMessage") != null) { %>
                            <div class="alert alert-danger mt-3"><%= request.getAttribute("errorMessage") %></div>
                        <% } %>
                        <% if (request.getAttribute("successMessage") != null) { %>
                            <div class="alert alert-success mt-3"><%= request.getAttribute("successMessage") %></div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>


</body>
</html>

