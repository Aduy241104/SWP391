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
            .error-message {
                color: #ff1a75;
                font-weight: bold;
                text-align: center;
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <%
            String role = (String) session.getAttribute("role");
        %>

        <% if ("admin".equals(role)) { %>
        <div class="sidebar">
            <h2 style="color: white; text-align: start; margin-bottom: 10px; "><i class="fas fa-cogs"></i> Admin</h2>
            <a href="AdminManagerUser?action=user"><i class="fas fa-users"></i> Manage Users</a>
            <a href="AdminManageStaff?action=staff"><i class="fas fa-users"></i> Manage Staff</a>
            <a href="AdminManageCategory" ><i class="fas fa-box"></i> Manage Category</a>
            <a href="AdminManagerProducts?action=product" class="active"><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerOrders?action=order"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="ViewRatingListForAdmin?action=reviews"><i class="fas fa-comments"></i> Manage Reviews</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>
        <% } else { %>
        <div class="sidebar">
            <h2  style="color: white; margin-bottom: 10px; " ><i class="fas fa-cogs"></i> Staff</h2>
            <a href="AdminManagerProducts?action=product" ><i class="fas fa-box"></i> Manage Products</a>
            <a href="StaffManagerOrders?action=orders" ><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>
        <% }%>


        <jsp:include page="Component/ManageForAdmin_Search.jsp">
            <jsp:param name="page" value="product"/>
        </jsp:include>


        <div class="main-content">
            <h2 class="text-center"><i class="fas fa-plus-circle"></i> Add New Product</h2>
            <c:if test="${not empty error}">
                <div class="error-message">${error}</div>
            </c:if>
            <form action="AdminManagerProducts?action=addProduct" method="Post" class="form-container" enctype="multipart/form-data">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Product Name:</label>
                        <input type="text" class="form-control" name="productName" value="${product.productName}" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Price ($):</label>
                        <input type="number" class="form-control" id="price" name="price" min="0" step="0.01" required>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Description:</label>
                    <textarea class="form-control" name="description" rows="3" required>${product.description}</textarea>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Stock:</label>
                        <input min="0" type="number" class="form-control" name="stock" value="${product.stock}" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Category:</label>
                        <select class="form-control" name="categoryID" required>
                            <c:forEach var="category" items="${categoryList}">
                                <option value="${category.categoryID}" ${category.categoryID == product.categoryID ? 'selected' : ''}>
                                    ${category.categoryName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label class="form-label">Size:</label>
                        <input type="text" class="form-control" id="size" name="size" min="0" value="${product.size}" required>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="ageRange">Age Range:</label>
                        <select class="form-control" id="ageRange" name="ageRange" required>
                            <option value="">-- Select Age Range --</option>
                            <option value="0-1" ${product.ageRange == '0-1' ? 'selected' : ''}>0-1</option>
                            <option value="1-3" ${product.ageRange == '1-3' ? 'selected' : ''}>1-3</option>
                            <option value="3" ${product.ageRange == '3' ? 'selected' : ''}>3</option>
                        </select>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label class="form-label">Origin:</label>
                        <input type="text" class="form-control" name="origin" value="${product.origin}" required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Weight (kg):</label>
                        <input min="0" type="number" class="form-control" name="weight" step="0.01" value="${product.weight}" required>
                    </div>
                   
                </div>

                <div class="mb-3">
                    <label class="form-label">Product Image:</label>
                    <input type="file" class="form-control" name="image" accept="image/*" required>
                    <c:if test="${not empty product.imageUrl}">
                        <small>Current image path: ${product.imageUrl}</small>
                    </c:if>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-lg btn-custom btn-save"><i class="fas fa-save"></i> Save Product</button>
                    <a href="AdminManagerProducts?action=product" class="btn btn-lg btn-custom btn-cancel"><i class="fas fa-times"></i> Cancel</a>
                </div>
            </form>
        </div>
        <script>
            document.querySelector("form").addEventListener("submit", function (e) {
                const priceInput = document.getElementById("price");
                const ageRange = document.getElementById("ageRange");
                const sizeInput = document.getElementById("size");

                let isValid = true;
                let messages = [];

                if (priceInput && parseFloat(priceInput.value) < 0) {
                    isValid = false;
                    messages.push("Price cannot be negative.");
                } 

                if (ageRange && ageRange.value === "") {
                    isValid = false;
                    messages.push("Please select an age range.");
                }
                 if (sizeInput && sizeInput.value === "") {
                    isValid = false;
                    messages.push("Please select an age range.");
                }


                if (!isValid) {
                    e.preventDefault(); // chặn gửi form
                    alert(messages.join("\n"));
                }
            });
        </script>

    </body>
</html>
