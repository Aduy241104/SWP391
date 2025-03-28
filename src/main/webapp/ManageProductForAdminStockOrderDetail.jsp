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
                max-width: 900px;
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
                font-size: 26px;
                font-weight: bold;
            }
            .details-wrapper {
                display: flex;
                align-items: center;
                justify-content: space-between;
                gap: 20px;
            }
            .stock-details {
                flex: 1;
                font-size: 18px;
                color: #333;
            }
            .info-item {
                margin-bottom: 10px;
                font-size: 18px;
            }
            .info-item strong {
                color: #ED1164;
            }
            .product-image {
                max-width: 300px;
                height: auto;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }
            .product-info {
                text-align: center;
                margin-top: 30px;
            }
            .product-info h2 {
                font-size: 24px;
                font-weight: bold;
                color: #ED1164;
            }
            .product-info p {
                font-size: 18px;
                color: #333;
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
            <a href="AdminManagerProducts?action=product" ><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerOrders?action=order"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="ViewRatingListForAdmin?action=reviews"><i class="fas fa-comments"></i> Manage Reviews</a>
            <a href="AdminManagerProducts?action=managerStock" class="active"><i class="fas fa-warehouse"></i> Manage Stock</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>
        <% } else { %>
        <div class="sidebar">
            <h2  style="color: white; margin-bottom: 10px; " ><i class="fas fa-cogs"></i> Staff</h2>
            <a href="AdminManagerProducts?action=product"><i class="fas fa-box"></i> Manage Products</a>
            <a href="StaffManagerOrders?action=orders" ><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="AdminManagerProducts?action=managerStock" class="active"><i class="fas fa-warehouse"></i> Manage Stock</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>
        <% }%>

        <jsp:include page="Component/ManageForAdmin_Search.jsp">
            <jsp:param name="page" value="stock"/>
        </jsp:include>

        <div class="container" style="margin-top: 100px; margin-right:  200px;">
            <h2>Stock Order Details</h2>
            <c:if test="${empty stock}">
                <div class="no-orders">
                    <h3>No orders found.</h3>
                </div>
            </c:if>

            <div class="details-wrapper">
                <div class="stock-details">
                    <div class="info-item"><strong>Stock Order ID:</strong> ${stock.stockID}</div>
                    <div class="info-item"> <strong>Product Name </strong>:${product.productName}</div>
                    <div class="info-item"> <strong>User Name </strong>:${user.fullName}</div>
                    <div class="info-item"><strong>Quantity:</strong> ${stock.quantity}</div>
                    <div class="info-item"><strong>Type:</strong> ${stock.type}</div>
                    <div class="info-item"><strong>Timestamp:</strong> ${stock.timestamp}</div>
                    <div class="info-item"><strong>Total Price:</strong> ${stock.totalPrice}</div>
                   
                </div>
                <img src="${product.imageUrl}" alt="Product Image" class="product-image">
            </div>

            <div style="margin-top: 50px; margin-bottom: 20px;">
                <a href="<c:choose>
                       <c:when test='${stock.type eq "import"}'>AdminManageStock?action=Import</c:when>
                       <c:otherwise>AdminManageStock?action=Export</c:otherwise>
                   </c:choose>"
                   class="btn btn-custom btn-lg shadow">
                    <i class="fas fa-arrow-left"></i> 
                    Back to 
                    <c:choose>
                        <c:when test="${stock.type eq 'import'}">Import Orders</c:when>
                        <c:otherwise>Stock Page</c:otherwise>
                    </c:choose>
                </a>
            </div>
        </div>
    </body>
</html>