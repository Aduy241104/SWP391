<%-- 
    Document   : sidebarAdmin
    Created on : Mar 2, 2025, 3:48:20 PM
    Author     : thaiv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
            String role = (String) session.getAttribute("role");
        %>

        <% if ("admin".equals(role)) { %>
        <div class="sidebar">
            <h2><i class="fas fa-cogs"></i> Admin</h2>
            <a href="AdminManagerUser?action=user"><i class="fas fa-users"></i> Manage Users</a>
            <a href="AdminManageStaff?action=staff"><i class="fas fa-users"></i> Manage Staff</a>
            <a href="AdminManagerProducts?action=product"><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerOrders?action=order" class="active"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="AdminManagerProducts?action=managerStock"><i class="fas fa-warehouse"></i> Manage Stock</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>
        <% } else { %>
        <div class="sidebar">
            <h2><i class="fas fa-cogs"></i> Staff</h2>
            <a href="AdminManagerProducts?action=product"><i class="fas fa-box"></i> Manage Products</a>
            <a href="StaffManagerOrders?action=orders" class="active"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="AdminManagerProducts?action=managerStock"><i class="fas fa-warehouse"></i> Manage Stock</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>
        <% }%>

    </body>
</html>
