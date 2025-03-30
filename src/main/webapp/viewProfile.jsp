<%-- 
    Document   : viewProfile
    Created on : Feb 18, 2025, 1:27:51 PM
    Author     : NHATHCE181222
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ManageAccount</title>
        <link rel="stylesheet" href="css/ManageAccount.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="font/fontawesome-free-6.5.2-web/css/all.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <style>
            body {
                font-family: "Montserrat", sans-serif;
                overflow-x: hidden;
            }
        </style>
    </head>

    <body>
        <header class="" id="header">
            <jsp:include page="Component/Header.jsp"></jsp:include>
            </header>
            <div class="container light-style flex-grow-1 container-p-y">
                <h2 class="font-weight-bold py-3 mb-4 text-center">
                    Manage Profile
                </h2>
                <div class="card overflow-hidden" style="border: 2px solid rgb(45, 1, 1);">
                    <div class="row no-gutters row-bordered row-border-light">
                        <div class="col-md-3 pt-0">
                            <div class="list-group list-group-flush account-settings-links">
                                <div style="width: 140px; height: 140px; background-color: rgb(251, 15, 176); margin-left: 60px; margin-top: 50px; margin-bottom: 30px ;border-radius: 120px; text-align: center; line-height: 140px;font-size: 45px; font-weight: 700; color: white;">
                                    D
                                </div>
                                <a class="list-group-item list-group-item-action <%= (request.getAttribute("error") == null && request.getAttribute("errorOldPassword") == null && session.getAttribute("successMessage") == null) ? "active show" : ""%>" data-toggle="list"
                               href="#account-general">General</a>
                            <a class="list-group-item list-group-item-action <%= (request.getAttribute("error") != null || request.getAttribute("errorOldPassword") != null || session.getAttribute("successMessage") != null) ? "active show" : ""%>" data-toggle="list"
                               href="#account-change-password">Change password</a>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <div class="tab-content">
                            <form class="tab-pane fade <%= (session.getAttribute("successMessageGeneral") != null || (request.getAttribute("error") == null && request.getAttribute("errorOldPassword") == null && session.getAttribute("successMessage") == null)) ? "active show" : ""%>" 
                                  id="account-general" action="editProfile" method="post">
                                <hr class="border-light m-0">
                                <div class="card-body">
                                    <% 
                                        String successMessageGeneral = (String) session.getAttribute("successMessageGeneral");
                                        if (successMessageGeneral != null) {
                                    %>
                                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                                            <%= successMessageGeneral %>
                                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                    <% 
                                        session.removeAttribute("successMessageGeneral"); // Xóa sau khi hiển thị
                                        } 
                                    %>
                                    <% 
                                        if (request.getAttribute("error") != null) {
                                    %>
                                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                            <%= request.getAttribute("error") %>
                                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                    <% } %>
                                    <div class="form-group">
                                        <label class="form-label">UserID</label>
                                        <input name="userId" readonly type="text" class="form-control" value="${user.userId}">
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Username</label>
                                        <input name="userName" readonly type="text" class="form-control mb-1" value="${user.username}">
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Full Name</label>
                                        <input required name="fullName" type="text" class="form-control" value="${user.fullName}">
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">E-mail</label>
                                        <input required name="email" type="email" class="form-control mb-1" value="${user.email} " required>
                                    </div>
                                </div>
                                <div class="text-right mt-3">
                                    <button type="submit" class="btn" style="border-radius: 17px; margin-bottom: 20px; background-color: rgb(251, 59, 59); color: white;">Save changes</button> 
                                    <a href="" type="button" class="btn btn-default" style="margin-bottom: 20px;">Cancel</a>
                                </div>
                            </form>
                            <form class="tab-pane fade <%= (request.getAttribute("error") != null || request.getAttribute("errorOldPassword") != null || session.getAttribute("successMessage") != null) ? "active show" : ""%>" 
                                  id="account-change-password" action="changePassword" method="post" onsubmit="return validatePassword()">
                                <div class="card-body pb-2">
                                    <%
                                        String successMessage = (String) session.getAttribute("successMessage");
                                        if (successMessage != null) {
                                    %>
                                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                                        <%= successMessage%>
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <%
                                            session.removeAttribute("successMessage"); // Xóa sau khi hiển thị
                                        }
                                    %>
                                    <%
                                        if (request.getAttribute("error") != null) {
                                    %>
                                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                        <%= request.getAttribute("error")%>
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <% } %>
                                    <input type="hidden" name="userId" value="${user.userId}">
                                    <div class="form-group">
                                        <label class="form-label">Current password</label>
                                        <input name="oldPassword" id="oldPassword" type="password" class="form-control" required>
                                        <% if (request.getAttribute("errorOldPassword") != null) {%>
                                        <small class="text-danger"><%= request.getAttribute("errorOldPassword")%></small>
                                        <% }%>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">New password</label>
                                        <input name="newPassword" id="newPassword" type="password" class="form-control" required>
                                        <small id="newPasswordError" class="text-danger"></small>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Repeat new password</label>
                                        <input name="repeatPassword" id="repeatPassword" type="password" class="form-control" required>
                                        <small id="repeatPasswordError" class="text-danger"></small>
                                    </div>
                                </div>
                                <div class="text-right mt-3">
                                    <button type="submit" class="btn" style="border-radius: 17px; margin-bottom: 20px; background-color: rgb(251, 59, 59); color: white;">Save changes</button> 
                                    <a href="" type="button" class="btn btn-default" style="margin-bottom: 20px;">Cancel</a>
                                </div>
                            </form>

                            <script>
                                function validatePassword() {
                                    let newPassword = document.getElementById("newPassword").value;
                                    let repeatPassword = document.getElementById("repeatPassword").value;
                                    let passwordRegex = /^(?=.*[0-9])(?=.*[A-Z]).{8,}$/;
                                    let newPasswordError = document.getElementById("newPasswordError");
                                    let repeatPasswordError = document.getElementById("repeatPasswordError");

                                    newPasswordError.textContent = "";
                                    repeatPasswordError.textContent = "";

                                    if (!passwordRegex.test(newPassword)) {
                                        newPasswordError.textContent = "Password must be at least 8 characters long and contain at least one number and one uppercase letter.";
                                        return false;
                                    }

                                    if (newPassword !== repeatPassword) {
                                        repeatPasswordError.textContent = "New password and repeat password do not match.";
                                        return false;
                                    }

                                    return true;
                                }
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <footer style="margin-top: 80px;" class="container-fluid" id="footer">
            <jsp:include page="Component/Footer.jsp"></jsp:include>
        </footer>

        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
