<%-- 
    Document   : ManagerOrdersForAdmin
    Created on : Feb 15, 2025, 2:41:56 PM
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
        <title>Manage Orders - Staff</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styleToy.css">
        <link rel="stylesheet" href="font/fontawesome-free-6.5.2-web/css/all.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/AdminDashboardStyle.css"/>
        <link rel="stylesheet" href="css/ManageProductForAdminStyles.css"/>
    </head>
    <body>
        <jsp:include page="Component/sidebarAdmin.jsp"/>

        <jsp:include page="Component/ManageForAdmin_Search.jsp">
            <jsp:param name="page" value="order"/>
        </jsp:include>

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
                <c:forEach var="order" items="${OrdersList}">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.userId}</td>
                        <td>${order.totalAmount}</td>
                        <td>${order.createdAt}</td>
                        <td>${order.address}</td>
                        <td>${order.phoneNumber}</td>
                        <td>${order.orderStatus}</td>
                        <td>
                            <div class="action-buttons">
                                <a style="margin: 5px;" href="AdminManagerOrders?action=viewDetails&id=${order.orderId}&price=${order.totalAmount}&userID=${order.userId}" class="btn btn-info btn-sm">
                                    <i class="fas fa-eye"></i> View Details
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="action-buttons-add">
                <a href="AdminManagerOrders?action=FunctionsOfOrderManagement&func=Pending" class="btn btn-custom btn-lg shadow">
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
                <a href="StaffManagerOrders?action=ordersForDashBoard" class="btn btn-custom btn-lg shadow">
                    <i class="fas fa-arrow-left"></i> Back to Staff Page
                </a>
            </div>

        </div>
    </body>
</html>
