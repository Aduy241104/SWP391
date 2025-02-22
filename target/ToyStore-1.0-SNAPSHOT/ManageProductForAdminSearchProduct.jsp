<%-- 
    Document   : adminDashboardd
    Created on : Feb 15, 2025, 2:02:16 PM
    Author     : lanoc
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Product"%>
<%@page import="Model.Orders"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Dashboard</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styleToy.css">
        <link rel="stylesheet" href="font/fontawesome-free-6.5.2-web/css/all.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/AdminDashboardStyle.css"/>
        <style>
            .center-container {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                height: 60vh; /* Đảm bảo nội dung căn giữa màn hình */
                text-align: center;
                margin-left: 600px;
                
            }

            .mess {
                color: red;
                font-size: 32px;
                margin-bottom: 20px;
            }

            .message-box {
                background: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                max-width: 600px;
            }
            p {
                font-size: 18px;
                color: #333;
            }

            .example {
                font-weight: bold;
                color: #007bff;
            }

        </style>
    </head>
    <body>
        <div class="sidebar">
            <h2><i class="fas fa-cogs"></i> Admin</h2>
            <a href="manageUsers.jsp"><i class="fas fa-users"></i> Manage Users</a>
            <a href="AdminManagerProducts?action=product"><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerProducts?action=order"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to Home</a>
        </div>

        <jsp:include page="Component/ManageForAdmin_Search.jsp"></jsp:include>

        <c:choose>
            <c:when test="${not empty productList}">
                <div class="main-content">
                    <h2 class="text-center"><i class="fas fa-box"></i> Manage Products</h2>
                    <table class="table table-bordered table-hover mt-4">
                        <tr class="table-dark">
                            <th>ID</th><th>Name</th><th>Description</th><th>Price</th><th>Stock</th><th>Image</th><th>Category</th><th>Active</th><th>Size</th><th>Age Range</th><th>Origin</th><th>Weight</th><th>Action</th>
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
                                    <a href="EditProduct.jsp?id=${product.productID}" class="btn btn-warning btn-sm"><i class="fas fa-edit"></i> Edit</a>
                                    <a href="DeleteProductServlet?id=${product.productID}" onclick="return confirm('Are you sure?')" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:when>

            <c:when test="${not empty orderList}">
                <div class="main-content">
                    <h2 class="text-center"><i class="fas fa-shopping-cart"></i> Manage Orders</h2>
                    <table class="table table-bordered table-hover mt-4">
                        <tr class="table-dark">
                            <th>Order ID</th><th>User ID</th><th>Total Amount</th><th>Created At</th><th>Address</th><th>Phone Number</th><th>Status</th><th>Action</th>
                        </tr>
                        <c:forEach var="order" items="${orderList}">
                            <tr>
                                <td>${order.orderId}</td>
                                <td>${order.userId}</td>
                                <td>${order.totalAmount}</td>
                                <td>${order.createdAt}</td>
                                <td>${order.address}</td>
                                <td>${order.phoneNumber}</td>
                                <td>${order.orderStatus}</td>
                                <td>
                                    <a href="AdminManagerOrders?action=viewDetails&id=${order.orderId}&price=${order.totalAmount}&userID=${order.userId}" class="btn btn-info btn-sm"><i class="fas fa-eye"></i> View Details</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:when>

            <c:otherwise>
                    <div class="center-container">
                        <h1 class="mess">No results found!</h1>
                        <div class="message-box">
                            <p>You can search for orders by entering OrderID and UserID like this:</p>
                            <p class="example">EX: "1+1"</p>
                        </div>
                    </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
