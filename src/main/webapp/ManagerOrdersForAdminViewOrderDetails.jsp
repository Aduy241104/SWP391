<%-- 
    Document   : ManagerOrdersForAdminPendingView
    Created on : Feb 16, 2025, 12:41:42 PM
    Author     : thaiv
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Orders"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manage Orders - Admin</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styleToy.css">
        <link rel="stylesheet" href="font/fontawesome-free-6.5.2-web/css/all.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/AdminDashboardStyle.css"/>
        <link rel="stylesheet" href="css/ManageProductForAdminStyles.css"/>
        <style>
            body {
                background-color: #fde2e4;
                font-family: Arial, sans-serif;
            }
            .container {
                max-width: 800px;
                margin: 50px auto;
                background: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h2 {
                color: #ED1164;
                text-align: center;
                margin-bottom: 20px;
            }
            .order-info {
                display: flex;
                align-items: center;
                gap: 20px;
            }
            .order-info img {
                width: 150px;
                height: 150px;
                object-fit: cover;
                border-radius: 10px;
                border: 2px solid #d63384;
            }
            .order-details {
                flex: 1;
            }
            .order-details p {
                margin: 5px 0;
                font-size: 16px;
            }
            .btn-back {
                display: block;
                text-align: center;
                margin-top: 20px;
                background: #ED1164;
                color: #fff;
                padding: 10px;
                border-radius: 5px;
                text-decoration: none;
            }
            .btn-back:hover {
                background: #b02a6b;
            }
        </style>
    </head>
    <body>
        <div class="sidebar">
            <h2 style="color: white; text-align: start;"><i class="fas fa-cogs"></i> Admin</h2>
            <a href="manageUsers.jsp"><i class="fas fa-users"></i> Manage Users</a>
            <a href="AdminManagerProducts?action=product"><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerProducts?action=order"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>

        <div class="navbar">
            <div class="logo"><i class="fas fa-store"></i> Toy Store</div>
            <div class="search-bar">
                <i class="fas fa-search"></i>
                <input type="text" placeholder="Search here">
            </div>
        </div>

        <div class="main-content">
            <div class="container">
                <h2>Order Detail</h2>
                <c:if test="${empty orderDetails}">
                    <div class="alert alert-warning" role="alert">
                        <h1> No orders found.</h1>
                    </div>
                </c:if>

                <c:forEach var="orderDetail" items="${orderDetails}">
                    <div class="order-info">
                        <img src="${orderDetail.imageUrl}" alt="Product Image">
                        <div class="order-details">
                            <p><strong>Product Name:</strong> ${orderDetail.productName}</p>
                            <p><strong>Category:</strong> ${orderDetail.categoryName}</p>
                            <p><strong>Price:</strong> $${orderDetail.price}</p>

                            <p><strong>Quantity:</strong> ${orderDetail.quantity}</p>
                        </div>
                    </div>
                    <h1>---------------------------------------------------------------</h1>
                </c:forEach>
                 <c:if test="${not empty orderDetails}">
                <h1>
                    <p><strong>Username:</strong> ${orderDetail.username}</p>
                    <p><strong>Address:</strong> ${orderDetail.address}</p>
                    <p><strong>Phone:</strong> ${orderDetail.phoneNumber}</p>
                    <p><strong>Email:</strong> ${orderDetail.email}</p>
                    <p><strong>Status:</strong> ${orderDetail.orderStatus}</p>
                    <p><strong>Total Amount:</strong> $${total}</p>
                </h1>
                 </c:if>
                <c:choose>
                    <c:when test="${orderStatus== 'pending'}">
                        <div class="action-buttons">
                            <a  href="AdminManagerOrders?id=${orderDetail.orderId}&action=accept" class="btn btn-success btn-sm">
                                <i style="padding-right: 10px;" class="fas fa-check-circle"></i> Accept
                            </a>
                            <a href="#" onclick="event.preventDefault();
                                    if (confirm('Are you sure you want to reject this order?'))
                                        window.location.href = 'AdminManagerOrders?id=${orderDetail.orderId}&action=reject';" class="btn btn-danger btn-sm">
                                <i style="padding-right: 10px;" class="fas fa-times-circle"></i> Reject
                            </a>
                        </div>
                    </c:when>
                    <c:when test="${orderStatus== 'shipping'}">
                        <div class="action-buttons">
                            <a style="padding-right: 10px;" href="AdminManagerOrders?id=${orderDetail.orderId}&action=Delivered" class="btn btn-success btn-sm">
                                <i style="padding-right: 10px;"  class="fas fa-check-circle"></i> Delivered
                            </a>
                            <a href="#" onclick="event.preventDefault();
                                    if (confirm('Are you sure you want to cancel this shipping?'))
                                        window.location.href = 'AdminManagerOrders?id=${orderDetail.orderId}&action=cancel';" class="btn btn-danger btn-sm">
                                <i style="padding-right: 10px;" class="fas fa-times-circle"></i> Cancel
                            </a>
                        </div>
                    </c:when>
                    <c:when test="${orderStatus== 'delivered'}">
                        <div class="action-buttons" style="padding: 5px; display: flex; align-items: center;">
                            <i style="padding-right: 10px;"  class="fas fa-money-bill-wave" style="color: green; margin-right: 5px;"></i>
                            <span style="font-weight: bold; color: green;">Paid</span>
                        </div>
                    </c:when>
                    <c:when test="${orderStatus== 'cancel'}">
                        <div class="action-buttons">
                            <a href="AdminManagerOrders?id=${orderDetail.orderId}&action=restore" class="btn btn-success btn-sm">
                                <i style="padding-right: 10px;"  style="padding: 10px;"  class="fas fa-check-circle"></i> Restore
                            </a>
                            <a href="#" onclick="event.preventDefault();
                                    if (confirm('Are you sure you want to delete this order?'))
                                        window.location.href = 'AdminManagerOrders?id=${orderDetail.orderId}&action=delete';" class="btn btn-danger btn-sm">
                                <i style="padding-right: 10px;"  class="fas fa-times-circle"></i> Delete
                            </a>
                        </div>
                    </c:when>
                </c:choose>

            </div>

            <div style="margin-bottom: 20px; justify-content: center; "class="action-buttons-add">
                <a href="AdminManagerProducts?action=order" class="btn btn-custom btn-lg shadow">
                    <i style="padding-right: 10px;"  class="fas fa-arrow-left"></i> Back to Manage Orders
                </a>
                </a>
            </div>
        </div>
    </body>
</html>
