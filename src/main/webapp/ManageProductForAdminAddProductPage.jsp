<%-- 
    Document   : ManageProductForAdminAddProductPage
    Created on : Feb 15, 2025, 7:32:15 PM
    Author     : lanoc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.Product"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add New Product</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styleToy.css">
        <link rel="stylesheet" href="font/fontawesome-free-6.5.2-web/css/all.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/AdminDashboardStyle.css"/>
        <link rel="stylesheet" href="css/ManageProductForAdminStyles.css"/>
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
            <a href="AdminManagerUser?action=user"><i class="fas fa-users"></i> Manage Users</a>
            <a href="AdminManagerProducts?action=product"  class="active"><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerProducts?action=order"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>

        <jsp:include page="Component/ManageForAdmin_Search.jsp"></jsp:include>


            <div class="main-content">
                <h2 class="text-center"><i class="fas fa-plus-circle"></i> Add New Product</h2>

                <form action="AdminManagerProducts?action=addProduct" method="Post"  class="form-container" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Product Name:</label>
                            <input type="text" class="form-control" name="productName" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Price ($):</label>
                            <input type="number" class="form-control" name="price" step="0.01" required>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Description:</label>
                        <textarea class="form-control" name="description" rows="3" required></textarea>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Stock:</label>
                            <input type="number" class="form-control" name="stock" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Category:</label>
                            <select class="form-control" name="categoryID" required>
                            <c:forEach var="category" items="${categoryList}">
                                <option value="${category.categoryID}">${category.categoryName}</option>
                            </c:forEach>
                        </select>
                    </div>

                </div>

                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label class="form-label">Size:</label>
                        <input type="text" class="form-control" name="size" required>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label class="form-label">Age Range:</label>
                        <input type="text" class="form-control" name="ageRange" required>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label class="form-label">Origin:</label>
                        <input type="text" class="form-control" name="origin" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Weight (kg):</label>
                        <input type="number" class="form-control" name="weight" step="0.01" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Active:</label>
                        <select class="form-control" name="isActive">
                            <option value="true">Yes</option>
                            <option value="false">No</option>
                        </select>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Product Image:</label>
                    <input type="file" class="form-control" name="image" accept="image/*" required>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-lg btn-custom btn-save"><i class="fas fa-save"></i> Save Product</button>
                    <a href="AdminManagerProducts?action=product" class="btn btn-lg btn-custom btn-cancel"><i class="fas fa-times"></i> Cancel</a>
                </div>
            </form>
        </div>
    </body>
</html>
