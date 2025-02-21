<%-- 
    Document   : adminDashboard
    Created on : Feb 15, 2025, 2:02:16 PM
    Author     : lanoc
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Product"%>
<%@page import="Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manage Products - Admin</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styleToy.css">
        <link rel="stylesheet" href="font/fontawesome-free-6.5.2-web/css/all.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/AdminDashboardStyle.css"/>
        <link rel="stylesheet" href="css/ManageProductForAdminStyles.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <style>
            body{
                font-family: "Montserrat", sans-serif;
                overflow-x: hidden;
            }
        </style>
    </head>
    <body>
        <div class="sidebar">
            <h2><i class="fas fa-cogs"></i> Admin</h2>
            <a href="manageUsers.jsp"><i class="fas fa-users"></i> Manage Users</a>
            <a href="AdminManagerProducts?action=product"><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerProducts?action=order"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>

        <jsp:include page="Component/ManageForAdmin_Search.jsp"></jsp:include>

            <div class="main-content">
                <h2 class="text-center"><i class="fas fa-box"></i> Manage Products</h2>
                <table class="table table-bordered table-hover mt-4">
                    <tr class="table-dark">
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Stock</th>
                        <th>Image</th>
                        <th>Category</th>
                        <th>Active</th>
                        <th>Size</th>
                        <th>Age Range</th>
                        <th>Origin</th>
                        <th>Weight</th>
                        <th>Action</th>
                    </tr>
                <c:forEach var="product" items="${productList}">
                    <tr>
                        <td>${product.productID}</td>
                        <td>${product.productName}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                        <td>${product.stock}</td>
                        <td><img src="${product.imageUrl}" alt="Product Image" width="50"></td>
                        <td>${product.categoryID}</td>
                        <td>${product.isActive ? "Yes" : "No"}</td>
                        <td>${product.size}</td>
                        <td>${product.ageRange}</td>
                        <td>${product.origin}</td>
                        <td>${product.weight} kg</td>
                        <td>
                            <div class="action-buttons">
                                <a href="EditProduct.jsp?id=${product.productID}" class="btn btn-warning btn-sm">
                                    <i class="fas fa-edit"></i> Edit
                                </a>
                                <a href="AdminManagerProducts?action=delete&id=${product.productID}" 
                                   onclick="return confirm('Are you sure you want to delete this product?')" 
                                   class="btn btn-danger btn-sm">
                                    <i class="fas fa-trash"></i> Delete
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div style="margin-bottom: 20px;"class="action-buttons-add">
                <a href="ManageProductForAdminAddProductPage.jsp" class="btn btn-custom btn-lg shadow">
                    <i class="fas fa-plus-circle"></i> Add New Product
                </a>
                <a href="AdminManagerProducts?action=ordersForDashBoard" class="btn btn-custom btn-lg shadow">
                    <i class="fas fa-arrow-left"></i> Back to Admin Page
                </a>
            </div>
        </div>
    </body>
</html>