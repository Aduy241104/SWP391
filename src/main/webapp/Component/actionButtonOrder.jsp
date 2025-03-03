<%-- 
    Document   : actionButtonOrder
    Created on : Mar 2, 2025, 3:54:29 PM
    Author     : thaiv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String role = (String) session.getAttribute("role");
        %>

        <% if ("admin".equals(role)) { %>
        <div class="action-buttons-add">
            <a href="AdminManagerOrders?action=FunctionsOfOrderManagement&func=Pending" class="btn btn-custom btn-lg shadow">
                <i class="fas fa-hourglass-half"></i> View Pending Orders
            </a> 
            <a href="AdminManagerOrders?action=FunctionsOfOrderManagement&func=Shipping" class="btn btn-custom btn-lg shadow">
                <i class="fas fa-truck"></i> View Shipping Orders
            </a>
            <a href="AdminManagerOrders?action=FunctionsOfOrderManagement&func=Delivered" class="btn btn-custom btn-lg shadow">
                <i class="fas fa-clipboard-check"></i> View Delivered Orders
            </a>
            <a href="AdminManagerOrders?action=FunctionsOfOrderManagement&func=Cancelled" class="btn btn-custom btn-lg shadow">
                <i class="fas fa-times"></i> View Canceled Orders
            </a>
            <a href="AdminManagerOrders?action=order" class="btn btn-custom btn-lg shadow">
                <i class="fas fa-arrow-left"></i> Back to Manage Orders
            </a>
        </div>
        <% } else { %>
        <div class="action-buttons-add">
            <a href="AdminManagerOrders?action=FunctionsOfOrderManagement&func=Pending" class="btn btn-custom btn-lg shadow">
                <i class="fas fa-hourglass-half"></i> View Pending Orders
            </a> 
            <a href="AdminManagerOrders?action=FunctionsOfOrderManagement&func=Shipping" class="btn btn-custom btn-lg shadow">
                <i class="fas fa-truck"></i> View Shipping Orders
            </a>
            <a href="AdminManagerOrders?action=FunctionsOfOrderManagement&func=Delivered" class="btn btn-custom btn-lg shadow">
                <i class="fas fa-clipboard-check"></i> View Delivered Orders
            </a>
            <a href="AdminManagerOrders?action=FunctionsOfOrderManagement&func=Cancelled" class="btn btn-custom btn-lg shadow">
                <i class="fas fa-times"></i> View Canceled Orders
            </a>
            <a href="StaffManagerOrders?action=orders" class="btn btn-custom btn-lg shadow">
                <i class="fas fa-arrow-left"></i> Back to Manage Orders
            </a>
        </div>
        <% }%>

    </body>
</html>
