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
                                <a href="AdminManagerOrders?id=${order.orderId}&action=accept" class="btn btn-success btn-sm">
                                    <i  style="padding-right: 5px;"  class="fas fa-check-circle"></i> Accept
                                </a>
                                <a href="AdminManagerOrders?id=${order.orderId}&action=reject" 
                                   onclick="event.preventDefault();
                                           if (confirm('Are you sure you want to reject this order?')) {
                                               window.location.href = 'AdminManagerOrders?id=${order.orderId}&action=reject';
                                           }" 
                                   class="btn btn-danger btn-sm">
                                    <i style="padding-right: 5px;" class="fas fa-times-circle"></i> Reject
                                </a>

                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
              <jsp:include page="Component/actionButtonOrder.jsp"/>

        </div>
    </body>
</html>
