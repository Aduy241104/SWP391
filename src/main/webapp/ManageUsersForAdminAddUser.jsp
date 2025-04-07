<%--
    Document   : ManageUserForAdminAddUserPage
    Created on : Feb 26, 2025, 
    Author     : thaiv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add New User</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styleToy.css">
        <link rel="stylesheet" href="font/fontawesome-free-6.5.2-web/css/all.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/AdminDashboardStyle.css"/>
        <link rel="stylesheet" href="css/ManageProductForAdminStyles.css"/>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <style>
            .form-container {
                background: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
                max-width: 700px;
                margin: 20px auto;
            }
            .form-container h2 {
                text-align: center;
                color: #333;
                font-weight: bold;
            }
            .form-label {
                font-weight: 600;
                color: #555;
            }
            .form-control {
                border-radius: 8px;
                border: 1px solid #ccc;
                transition: all 0.3s ease;
            }
            .form-control:focus {
                border-color: #ff66b2;
                box-shadow: 0px 0px 5px rgba(255, 102, 178, 0.5);
            }
            .btn-custom {
                width: 100%;
                padding: 10px;
                border-radius: 8px;
                font-size: 16px;
                transition: all 0.3s ease;
            }
            .text-center {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }
            .btn-custom {
                margin-right: 20px;
                width: 100%;
                padding: 12px;
                border-radius: 10px;
                font-size: 16px;
                transition: all 0.3s ease;
            }
            .btn-save {
                margin-right: 20px;
                background-color: #ff66b2;
                color: white;
                border: none;
                font-weight: bold;
            }
            .btn-save:hover {
                background-color: #ff3385;
            }
            .btn-cancel {
                background-color: #ff1a75;
                color: white;
                border: none;
                font-weight: bold;
            }
            .btn-cancel:hover {
                background-color: #cc005f;
            }
        </style>
    </head>
    <body>
        <!-- Sidebar -->
        <div class="sidebar">
            <h2><i class="fas fa-cogs"></i> Admin</h2>
            <a href="AdminManagerUser?action=user" class="active"><i class="fas fa-users"></i> Manage Users</a>
            <a href="AdminManageStaff?action=staff"><i class="fas fa-users"></i> Manage Staff</a>
            <a href="AdminManageCategory" ><i class="fas fa-box"></i> Manage Category</a>
            <a href="AdminManagerProducts?action=product"><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerOrders?action=order" ><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="ViewRatingListForAdmin?action=reviews"><i class="fas fa-comments"></i> Manage Reviews</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>

        <jsp:include page="Component/ManageForAdmin_Search.jsp">
             <jsp:param name="page" value="user"/>
        </jsp:include>

            <div class="main-content">
                <h2 class="text-center"><i class="fas fa-plus-circle"></i> Add New User</h2>
                <input type="hidden" value="user" name="userSearch">

                <form action="AdminManagerUser?action=addUser" method="Post" class="form-container" 
                      onsubmit="return validatePassword()">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Username:</label>
                            <input type="text" class="form-control" name="username" required>
                            <span class="text-danger">${requestScope.nameError}</span>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Password:</label>
                        <input id="password" type="password" class="form-control" name="password" required>
                        <span id="newPasswordError" class="text-danger"></span>
                    </div>

                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Email:</label>
                        <input type="email" class="form-control" name="email" required>
                        <span class="text-danger">${requestScope.emailError}</span>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Full Name:</label>
                        <input type="text" class="form-control" name="fullName" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Role:</label>
                        <select class="form-control" name="role" required>
                            <option value="Admin">Admin</option>
                            <option value="Customer">Customer</option>
                            <option value="Staff">Staff</option>
                        </select>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Active:</label>
                        <select class="form-control" name="isActive">
                            <option value="true">Yes</option>
                            <option value="false">No</option>
                        </select>
                    </div>
                </div>
                <c:if test="${not empty requestScope.generalError}">
                    <div class="alert alert-danger">${requestScope.generalError}</div>
                </c:if>

                <div class="text-center">
                    <button type="submit" class="btn btn-lg btn-custom btn-save"><i class="fas fa-save"></i> Save User</button>
                    <a href="AdminManagerUser?action=user" class="btn btn-lg btn-custom btn-cancel"><i class="fas fa-times"></i> Cancel</a>
                </div>
            </form>
            <script>
                function validatePassword() {
                    let newPassword = document.getElementById("password").value;
                    let passwordRegex = /^(?=.*[0-9])(?=.*[A-Z]).{8,}$/;
                    let newPasswordError = document.getElementById("newPasswordError");

                    newPasswordError.textContent = "";

                    if (!passwordRegex.test(newPassword)) {
                        newPasswordError.textContent = "Password must be at least 8 characters long, contain at least one number and one uppercase letter.";
                        return false;
                    }
                    return true;
                }

            </script>
        </div>
    </body>
</html>