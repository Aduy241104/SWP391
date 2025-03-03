<%-- 
    Document   : ManagerOrdersForAdminPendingView
    Created on : Feb 16, 2025, 12:41:42 PM
    Author     : thaiv
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Orders"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                max-width: 1200px;
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
            .customer-info {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
                padding: 15px;
                background-color: #f0f0f0;
                border-radius: 5px;
                font-weight: 500;
            }
            .customer-info p {
                margin: 0 8px;
                font-size: 14px;
                color: #333;
            }
            .customer-info strong {
                color: #ED1164;
            }
            .table-responsive {
                margin-top: 20px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            th, td {
                padding: 12px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            th {
                background-color: #ED1164;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            tr:hover {
                background-color: #f5f5f5;
            }
            .action-buttons {
                display: flex;
                gap: 10px;
            }
            .btn {
                padding: 8px 15px;
                font-size: 14px;
                border-radius: 5px;
            }
            .btn-success {
                background-color: #28a745;
                border-color: #28a745;
            }
            .btn-danger {
                background-color: #dc3545;
                border-color: #dc3545;
            }
            .btn-custom {
                background-color: #EA83AA;
                color: #fff;
                border-color: #ED1164;
            }
            .btn-success:hover {
                background-color: #218838;
                border-color: #218838;
            }
            .btn-danger:hover {
                background-color: #c82333;
                border-color: #c82333;
            }
            .btn-custom:hover {
                background-color: #F9D1E1;
            }
            .no-orders {
                text-align: center;
                color: #dc3545;
                padding: 20px;
            }
            .btn-back {
                display: block;
                text-align: center;
                margin-top: 20px;
                background: #ED1164;
                color: #fff;
                padding: 10px 20px;
                border-radius: 5px;
                text-decoration: none;
            }
            .btn-back:hover {
                background: #EA83AA;
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
            <a href="AdminManagerProducts?action=product"><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerOrders?action=order" class="active"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="AdminManagerProducts?action=managerStock"><i class="fas fa-warehouse"></i> Manage Stock</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>
        <% } else { %>
        <div class="sidebar">
            <h2  style="color: white; margin-bottom: 10px;text-align: start;" ><i class="fas fa-cogs"></i> Staff</h2>
            <a href="AdminManagerProducts?action=product"><i class="fas fa-box"></i> Manage Products</a>
            <a href="StaffManagerOrders?action=orders" class="active"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="AdminManagerProducts?action=managerStock"><i class="fas fa-warehouse"></i> Manage Stock</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>
        <% }%>
        <jsp:include page="Component/ManageForAdmin_Search.jsp">
            <jsp:param name="page" value="order"/>
        </jsp:include>

        <div class="main-content">
            <div class="container">
                <h2>Order Details</h2>
                <c:if test="${empty orderDetails}">
                    <div class="no-orders">
                        <h3>No orders found.</h3>
                    </div>
                </c:if>

                <c:if test="${not empty orderDetails}">
                    <div class="customer-info">
                        <p><strong>Username:</strong> ${orderDetail.username}</p>
                        <p><strong>Address:</strong> ${orderDetail.address}</p>
                        <p><strong>Phone:</strong> ${orderDetail.phoneNumber}</p>
                        <p><strong>Email:</strong> ${orderDetail.email}</p>
                        <p><strong>Status:</strong> ${orderDetail.orderStatus}</p>
                         <p>
                            <strong>Total Amount:</strong><fmt:formatNumber value="${total}" type="number"/> ₫
                        </p>
                    </div>

                    <div class="table-responsive">
                        <table>
                            <thead>
                                <tr>
                                    <th>Product Name</th>
                                    <th>Category</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="orderDetail" items="${orderDetails}">
                                    <tr>
                                        <td>${orderDetail.productName}</td>
                                        <td>${orderDetail.categoryName}</td>
                                        <td>$${orderDetail.price}</td>
                                        <td>${orderDetail.quantity}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <c:choose>
                        <c:when test="${orderStatus == 'pending'}">
                            <div class="action-buttons">
                                <a href="AdminManagerOrders?id=${orderDetail.orderId}&action=accept" class="btn btn-success btn-sm">
                                    <i style="padding-right: 10px;" class="fas fa-check-circle"></i> Accept
                                </a>
                                <a href="#" onclick="event.preventDefault();
                                        if (confirm('Are you sure you want to reject this order?'))
                                            window.location.href = 'AdminManagerOrders?id=${orderDetail.orderId}&action=reject';" class="btn btn-danger btn-sm">
                                    <i style="padding-right: 10px;" class="fas fa-times-circle"></i> Reject
                                </a>
                            </div>
                        </c:when>
                        <c:when test="${orderStatus == 'shipping'}">
                            <div class="action-buttons">
                                <a href="AdminManagerOrders?id=${orderDetail.orderId}&action=Delivered" class="btn btn-success btn-sm">
                                    <i style="padding-right: 10px;" class="fas fa-check-circle"></i> Delivered
                                </a>
                                <a href="#" onclick="event.preventDefault();
                                        if (confirm('Are you sure you want to cancel this shipping?'))
                                            window.location.href = 'AdminManagerOrders?id=${orderDetail.orderId}&action=cancel';" class="btn btn-danger btn-sm">
                                    <i style="padding-right: 10px;" class="fas fa-times-circle"></i> Cancel
                                </a>
                            </div>
                        </c:when>
                        <c:when test="${orderStatus == 'delivered'}">
                            <div class="action-buttons" style="padding: 5px; display: flex; align-items: center;">
                                <i style="padding-right: 10px;" class="fas fa-money-bill-wave" style="color: green; margin-right: 5px;"></i>
                                <span style="font-weight: bold; color: green;">Paid</span>
                            </div>
                        </c:when>
                        <c:when test="${orderStatus == 'cancel'}">
                            <div class="action-buttons">
                                <a href="AdminManagerOrders?id=${orderDetail.orderId}&action=restore" class="btn btn-success btn-sm">
                                    <i style="padding-right: 10px;" class="fas fa-check-circle"></i> Restore
                                </a>
                                <a href="#" onclick="event.preventDefault();
                                        if (confirm('Are you sure you want to delete this order?'))
                                            window.location.href = 'AdminManagerOrders?id=${orderDetail.orderId}&action=delete';" class="btn btn-danger btn-sm">
                                    <i style="padding-right: 10px;" class="fas fa-times-circle"></i> Delete
                                </a>
                            </div>
                        </c:when>
                    </c:choose>
                </c:if>
                <% if ("admin".equals(role)) { %>
                <a href="AdminManagerOrders?action=order" style="margin-left: 450px;" class="btn btn-custom btn-lg shadow">
                    <i style="padding-right: 10px;" class="fas fa-arrow-left"></i> Back to Manage Orders
                </a>  
                <% } else { %>
                <a href="StaffManagerOrders?action=orders" style="margin-left: 450px;" class="btn btn-custom btn-lg shadow">
                    <i style="padding-right: 10px;" class="fas fa-arrow-left"></i> Back to Manage Orders
                </a>  
                <% }%>
            </div>
        </div>
    </body>
</html>