<%-- 
    Document   : ViewCategory
    Created on : Mar 2, 2025, 10:39:36 AM
    Author     : Nguyen Phu Quy CE180789
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manage Category - Admin</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styleToy.css">
        <link rel="stylesheet" href="font/fontawesome-free-6.5.2-web/css/all.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/AdminDashboardStyle.css"/>
        <link rel="stylesheet" href="css/ManageProductForAdminStyles.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600&display=swap" rel="stylesheet">
        <style>
            td.description {
                max-width: 250px;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
                position: relative;
            }

            td.description.expanded {
                white-space: normal;
                overflow: visible;
                max-width: 400px;
                background: #fff;
                box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
                padding: 5px;
                position: relative; /* Giữ nguyên vị trí */
            }

            .toggle-btn {
                display: block;
                margin-top: 5px;
                background-color: #EA83AA;
                border: none;
                color: white;
                cursor: pointer;
                text-decoration: none;
            }

            .toggle-btn:hover {
                color: black;
                background-color: #FCEBF2;
                transform: scale(1.05);
                text-decoration: none;
            }

            .stock-input-form {
                display: flex;
                align-items: center;
                gap: 5px;
            }

            .stock-input-form input[type="number"] {
                width: 80px;
                padding: 5px;
                font-size: 14px;
            }

            .stock-input-form button {
                padding: 5px 10px;
                font-size: 12px;
            }

            .form-container {
                max-width: 320px; /* Adjusted width for better fit */
                margin: 20px auto;
                padding: 15px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            }

            .form-container .mb-3 {
                width: 100%;
                margin-bottom: 10px;
            }

            .form-container .mb-3 input,
            .form-container .mb-3 textarea {
                padding: 6px;
                font-size: 13px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            /* For buttons inside the form */
            .btn-custom {
                background-color: #EA83AA;
                color: white;
                border: none;
                padding: 10px 15px;
                cursor: pointer;
                font-size: 14px;
            }

            .btn-custom:hover {
                background-color: #FCEBF2;
                transform: scale(1.05);
                color: black;
            }

            .btn-cancel {
                background-color: #EA83AA;
                margin-left: 10px;
            }

            /* Adjust button spacing */
            .text-center {
                display: flex;
                justify-content: center;
                gap: 15px; /* Increased gap between buttons */
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <div class="sidebar">
            <h2><i class="fas fa-cogs"></i> Admin</h2>
            <a href="AdminManagerUser?action=user"><i class="fas fa-users"></i> Manage Users</a>
            <a href="AdminManageStaff?action=staff"><i class="fas fa-users"></i> Manage Staff</a>
            <a href="AdminManageCategory" class="active"><i class="fas fa-box"></i> Manage Category</a>
            <a href="AdminManagerProducts?action=product" ><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerOrders?action=order" ><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="ViewRatingListForAdmin?action=reviews"><i class="fas fa-comments"></i> Manage Reviews</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>


        <div class="main-content">
            <h2 class="text-center"><i class="fas fa-edit"></i> Update Category</h2>

            <div class="form-container">
                <form action="AdminManageCategoryEdit" method="POST">
                    <input type="hidden" name="categoryID" value="${category.categoryID}">
                    <div class="form-group">
                        <label for="categoryName">Category Name:</label>
                        <input type="text" class="form-control" id="categoryName" name="categoryName" value="${category.categoryName}" required>

                        <c:if test="${not empty errorMessage}">
                            <div style="color: red; font-weight: bold; margin-bottom: 10px; text-align: center;">
                                ${errorMessage}
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea class="form-control" id="description" name="description" required>${category.description}</textarea>
                    </div>

                    <div class="text-center">
                        <button type="submit"  class="btn btn-lg btn-custom btn-save">
                            <i class="fas fa-save"></i> Save Changes
                        </button>
                        <a href="AdminManageCategory" class="btn btn-lg btn-custom btn-cancel">
                            <i class="fas fa-times"></i> Cancel
                        </a>
                    </div>
                </form>
            </div>
        </div>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                document.querySelectorAll(".toggle-btn").forEach(button => {
                    button.addEventListener("click", function () {
                        let td = this.closest("td");
                        td.classList.toggle("expanded");

                        let shortText = td.querySelector(".short-text");
                        let fullText = td.querySelector(".full-text");

                        if (td.classList.contains("expanded")) {
                            fullText.style.display = "inline";
                            shortText.style.display = "none";
                            this.textContent = "Collapse";
                        } else {
                            fullText.style.display = "none";
                            shortText.style.display = "inline";
                            this.textContent = "See more...";
                        }
                    });
                });
            });

        </script>
    </body>
</html>
