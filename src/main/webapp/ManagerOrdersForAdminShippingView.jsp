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
    </head>
    <body>
        <div class="sidebar">
            <h2><i class="fas fa-cogs"></i> Admin</h2>
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
            <h2 class="text-center"><i class="fas fa-shopping-cart"></i> Manage Orders</h2>
            <table class="table table-bordered table-hover mt-4">
                <tr class="table-dark">
                    <th>Order ID</th>
                    <th>User ID</th>
                    <th>Total Amount</th>
                    <th>Created At</th>
                    <th>Address</th>
                    <th>Phone Number</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="order" items="${ordersList}">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.userId}</td>
                        <td>${order.totalAmount}</td>
                        <td>${order.createdAt}</td>
                        <td>${order.address}</td>
                        <td>${order.phoneNumber}</td>
                        <td>${order.orderStatus}</td>
                        <td>
                            <div style="padding: 5px;" class="action-buttons">
                                <a href="AdminManagerOrders?id=${order.orderId}&action=Delivered" class="btn btn-success btn-sm">
                                    <i  style="padding-right: 5px;"  class="fas fa-check-circle"></i> Delivered
                                </a>
                                <a href="AdminManagerOrders?id=${order.orderId}&action=cancel" 
                                   onclick="event.preventDefault();
                                           if (confirm('Are you sure you want to cancel this shipping?')) {
                                               window.location.href = 'AdminManagerOrders?id=${order.orderId}&action=cancel';
                                           }" 
                                   class="btn btn-danger btn-sm">
                                    <i style="padding-right: 5px;" class="fas fa-times-circle"></i> Cancel
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="action-buttons-add">
                <a  href="AdminManagerOrders?action=FunctionsOfOrderManagement&func=Pending" class="btn btn-custom btn-lg shadow">
                    <i class="fas fa-hourglass-half"></i> View Pending Orders
                </a> 
                <a  href="AdminManagerOrders?action=FunctionsOfOrderManagement&func=Shipping" class="btn btn-custom btn-lg shadow">
                    <i class="fas fa-truck"></i> View Shipping Orders
                </a>
                <a href="AdminManagerOrders?action=FunctionsOfOrderManagement&func=Delivered" class="btn btn-custom btn-lg shadow">
                    <i class="fas fa-clipboard-check"></i> View Delivered Orders
                </a>
                <a href="AdminManagerOrders?action=FunctionsOfOrderManagement&func=Cancelled" class="btn btn-custom btn-lg shadow">
                    <i class="fas fa-times"></i> View Canceled Orders
                </a>
                <a href="AdminManagerProducts?action=order" class="btn btn-custom btn-lg shadow">
                    <i class="fas fa-arrow-left"></i> Back to Manage Orders
                </a>
            </div>

        </div>
    </body>
</html>
