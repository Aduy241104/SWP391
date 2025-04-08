<%-- 
    Document   : signIn.jsp
    Created on : Feb 12, 2025, 3:28:08 PM
    Author     : NHATHCE181222
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession" %>

<%
    // Lấy thông báo lỗi từ request (do Controller truyền vào)
    String errorMessage = (String) request.getAttribute("errorMessage");
     String accountNotFoundMessage = (String) request.getAttribute("accountNotFoundMessage");
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="css/ter.css">
    </head>
    <body>

        <div class="wrapper">
            <form action="LoginController" method="post">  <%-- Gửi dữ liệu đến LoginController --%>
                <h1>Login</h1>

                <%-- Hiển thị thông báo lỗi nếu có --%>
                <% if (errorMessage != null) {%>
                <p class="error"><%= errorMessage%></p>
                <% }%>
                
                <%-- Hiển thị thông báo nếu tài khoản không tồn tại --%>
                <% if (accountNotFoundMessage != null) { %>
                <p class="error"><%= accountNotFoundMessage %></p>
                <% } %>

                <div class="form-input">
                    <input type="text" name="identifier" placeholder="Username or Email" required>
                </div>

                <div class="form-input">
                    <input type="password" name="password" placeholder="Password" required>
                </div>

                <div class="remember-forgot">
                    <a href="forgotpassword.jsp">Forgot password?</a>
                </div>

                <button type="submit" class="btn">Login</button>

                <div class="register">
                    <p>Don't have an account? <a href="signUp.jsp">Register</a></p>
                </div>
            </form>
        </div>

    </body>
</html>

