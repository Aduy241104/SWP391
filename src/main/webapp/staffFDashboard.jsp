<%-- 
    Document   : adminDashboard
    Created on : Feb 15, 2025, 2:02:16 PM
    Author     : thaiv
--%>


<%@page import="java.util.List"%>
<%@page import="Model.*"%>
<%@page import="DAO.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${ empty countOrders or empty totalAmount or empty count}">
    <c:redirect url="StaffManagerOrders?action=count"/>
</c:if>

<%
    String view = request.getParameter("view");
    if (view == null) {
        view = "staffFDashboard.jsp";
    }
    request.setAttribute("view", view);

%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Staff Dashboard</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styleToy.css">
        <link rel="stylesheet" href="font/fontawesome-free-6.5.2-web/css/all.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/AdminDashboardStyle.css"/>
        <link rel="stylesheet" href="css/ManageProductForAdminStyles.css"/>

        <style>
            .stat-box_revenue  {
                background: linear-gradient(to bottom, #EA83AA, #ffffff);
                color: black;
                transition: all 0.3s ease-in-out;
            }

            .stat-box_revenue:hover {
                background-color: white;
                color: black;
                transform: scale(1.05);
                border: 2px solid #ED1164;
            }
        </style>
    </head>
    <body>
        <div class="sidebar">
            <h2><i class="fas fa-cogs"></i> Staff</h2>
            <a href="AdminManagerProducts?action=product"><i class="fas fa-box"></i> Manage Products</a>
            <a href="StaffManagerOrders?action=orders"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="AdminManagerProducts?action=managerStock" ><i class="fas fa-warehouse"></i> Manage Stock</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>

        <jsp:include page="Component/ManageForAdmin_Search.jsp">
            <jsp:param name="page" value="searchAll"/>
        </jsp:include>


        <div class="main-content">
            <h2><i class="fas fa-chart-line"></i> Dashboard</h2>
            <div class="stats-container">
                <div onclick="location.href = 'StaffManagerOrders?action=productForDashBoard';" style="cursor: pointer;" class="stat-box">
                    <h3>${count}</h3>
                    <p><i class="fas fa-project-diagram"></i> Products</p>
                </div>
                <div onclick="location.href = 'StaffManagerOrders?action=ordersForDashBoard';" style="cursor: pointer;" class="stat-box">
                    <h3>${countOrders}</h3>
                    <p><i class="fas fa-shopping-bag"></i> Orders</p>
                </div>
                <div class="stat-box stat-box_revenue" style="background-color: #ED1164">
                    <h3>${totalAmount}Ð</h3>
                    <p><i class="fas fa-shopping-bag"></i> Revenue</p>
                </div>
            </div>
                    <div style="margin-left: 70px;" class="col-md-4 product-container">

                <c:choose>
                    <c:when test="${empty productList and empty OrdersList }">
                        <h3 style="font-size: 50px; text-align: center;">
                            Welcome back, <span class="admin-name">${sessionScope.user.fullName}</span>! 🎉 Let’s make today awesome! 💪
                        </h3>
                    </c:when>
                    <c:when test="${view eq 'productTable'}">
                        <table class="product-table">
                            <div class="header">
                                <h2>Manage Products</h2>
                                <a href="AdminManagerProducts?action=product" class="see-all-btn">See all →</a>
                            </div>

                            <thead>
                                <tr>
                                    <th>Product Name</th>
                                    <th>Price ($)</th>
                                    <th>Stock</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="product" items="${productList}">
                                    <tr>
                                        <td>${product.productName}</td>
                                        <td>${product.price}</td>
                                        <td>${product.stock}</td>
                                        <td>
                                            <div class="status">
                                                <div class="status-dot
                                                     ${product.stock < 10 ? 'low-stock' : (product.stock < 50 ? 'medium-stock' : 'high-stock')}">
                                                </div>
                                                <span>
                                                    ${product.stock < 10 ? "Low Stock" : (product.stock < 50 ? "Medium Stock" : "In Stock")}
                                                </span>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>

                        </table>

                        <div style="margin-top: 20px;"class="action-buttons-add">
                            <a href="StaffManagerOrders?action=BackToStaffDashboard" class="btn btn-custom btn-lg shadow">
                                <i class="fas fa-arrow-left"></i> Back to Staff Page
                            </a>
                        </div>
                    </c:when>
                    <c:when test="${view eq 'orderTable'}">
                        <table class="table table-bordered table-hover mt-4">
                            <div class="header">
                                <h2>Manage Orders</h2>
                                <a href="StaffManagerOrders?action=orders" class="see-all-btn">See all →</a>
                            </div>
                            <tr class="table-dark">
                                <th>Order ID</th>
                                <th>User ID</th>
                                <th>Total Amount</th>
                                <th>Status</th>

                            </tr>
                            <c:forEach var="order" items="${OrdersList}">
                                <tr>
                                    <td>${order.orderId}</td>
                                    <td>${order.userId}</td>
                                    <td>${order.totalAmount}</td>
                                    <td>${order.orderStatus}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div style="margin-bottom: 20px;"class="action-buttons-add">
                            <a href="StaffManagerOrders?action=BackToStaffDashboard" class="btn btn-custom btn-lg shadow">
                                <i class="fas fa-arrow-left"></i> Back to Staff Page
                            </a>
                        </div>
                    </c:when>
            </c:choose>
        </div>

    </div>
</body>
</html>