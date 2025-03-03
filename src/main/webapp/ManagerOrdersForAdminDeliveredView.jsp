<%-- 
    Document   : ManagerOrdersForAdminDeliveredView
    Created on : Feb 16, 2025, 12:42:47 PM
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
        <jsp:include page="Component/sidebarAdmin.jsp"/>

        <jsp:include page="Component/ManageForAdmin_Search.jsp">
            <jsp:param name="page" value="order"/>
        </jsp:include>

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
                        <th>Status</th>
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
                            <div style="padding: 5px; display: flex; align-items: center;">
                                <i class="fas fa-money-bill-wave" style="color: green; margin-right: 5px;"></i>
                                <span style="font-weight: bold; color: green;">Paid</span>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
              <jsp:include page="Component/actionButtonOrder.jsp"/>

        </div>
    </body>
</html>
