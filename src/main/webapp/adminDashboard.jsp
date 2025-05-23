<%-- 
    Document   : adminDashboard
    Created on : Feb 15, 2025, 2:02:16 PM
    Author     : thaiv
--%>


<%@page import="java.util.List"%>
<%@page import="Model.*"%>
<%@page import="DAO.*"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty count or empty countOrders or empty totalAmount or empty countUser}">
    <c:redirect url="AdminManagerProducts?action=count"/>
</c:if>

<%
    String view = request.getParameter("view");
    if (view == null) {
        view = "adminDashboard.jsp";
    }
    request.setAttribute("view", view);

%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Dashboard</title>
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
            <h2><i class="fas fa-cogs"></i> Admin</h2>
            <a href="AdminManagerUser?action=user"><i class="fas fa-users"></i> Manage Users</a>
            <a href="AdminManageStaff?action=staff"><i class="fas fa-users"></i> Manage Staff</a>
            <a href="AdminManageCategory" ><i class="fas fa-box"></i> Manage Category</a>
            <a href="AdminManagerProducts?action=product"><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerOrders?action=order"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="ViewRatingListForAdmin?action=reviews"><i class="fas fa-comments"></i> Manage Reviews</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>


        <jsp:include page="Component/ManageForAdmin_Search.jsp">
            <jsp:param name="page" value="searchAll"/>
        </jsp:include>


        <div class="main-content">
            <h2><i class="fas fa-chart-line"></i> Dashboard</h2>
            <div class="stats-container">
                <div onclick="location.href = 'AdminManagerUser?action=userForDashBoard';" style="cursor: pointer;" class="stat-box">
                    <h3>${countUser}</h3>
                    <p><i class="fas fa-user"></i> Users</p>
                </div>
                <div onclick="location.href = 'AdminManageStaff?action=staffForDashBoard';" style="cursor: pointer;" class="stat-box">
                    <h3>${countStaff}</h3>
                    <p><i class="fas fa-project-diagram"></i> Staff</p>

                </div>
                <div onclick="location.href = 'AdminManagerProducts?action=productForDashBoard';" style="cursor: pointer;" class="stat-box">
                    <h3>${count}</h3>
                    <p><i class="fas fa-project-diagram"></i> Products</p>
                </div>
                <div onclick="location.href = 'AdminManagerOrders?action=ordersForDashBoard';" style="cursor: pointer;" class="stat-box">
                    <h3>${countOrders}</h3>
                    <p><i class="fas fa-shopping-bag"></i> Orders</p>
                </div>
                <div class="stat-box stat-box_revenue" style="background-color: #ED1164">
                    <h3>${totalAmount}$</h3>
                    <p><i class="fas fa-shopping-bag"></i> Revenue</p>
                </div>
            </div>
                    <div style="margin-left: 70px;" class="col-md-4 product-container">

                <c:choose>
                    <c:when test="${empty productList && empty OrdersList && empty userTable && empty staffTable}">
                        <h3 style=" font-size: 50px; text-align: center;">
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

                        <div style="margin-bottom: 20px;"class="action-buttons-add">
                            <a href="AdminManagerProducts?action=BackToAdminDashboard" class="btn btn-custom btn-lg shadow">
                                <i class="fas fa-arrow-left"></i> Back to Admin Page
                            </a>
                        </div>
                    </c:when>
                    <c:when test="${view eq 'orderTable'}">
                        <table class="table table-bordered table-hover mt-4">
                            <div class="header">
                                <h2>Manage Orders</h2>
                                <a href="AdminManagerOrders?action=order" class="see-all-btn">See all →</a>
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
                                    <td>
                                        <fmt:formatNumber value="${order.totalAmount}" type="number"/> 
                                    </td>
                                    <td>${order.orderStatus}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div style="margin-bottom: 20px;"class="action-buttons-add">
                            <a href="AdminManagerProducts?action=BackToAdminDashboard" class="btn btn-custom btn-lg shadow">
                                <i class="fas fa-arrow-left"></i> Back to Admin Page
                            </a>
                        </div>
                    </c:when>
                    <c:when test="${view eq 'userTable'}">
                        <table class="table table-bordered table-hover mt-4">
                            <div class="header">
                                <h2>Manage User</h2>
                                <a href="AdminManagerUser?action=user" class="see-all-btn">See all →</a>
                            </div>
                            <tr class="table-dark">
                                <th>User ID</th>
                                <th>Username</th>
                                <th>Full Name</th>
                            </tr>
                            <c:forEach var="user" items="${userTable}">
                                <tr>
                                    <td>${user.userId}</td>
                                    <td>${user.username}</td>
                                    <td>${user.fullName}</td>
                                </tr>
                            </c:forEach>

                        </table>
                        <div style="margin-bottom: 20px;"class="action-buttons-add">
                            <a href="AdminManagerProducts?action=BackToAdminDashboard" class="btn btn-custom btn-lg shadow">
                                <i class="fas fa-arrow-left"></i> Back to Admin Page
                            </a>
                        </div>
                    </div>
                </c:when>

                <c:when test="${view eq 'staffTable'}">
                    <table class="table table-bordered table-hover mt-4">
                        <div class="header">
                            <h2>Manage Staff</h2>

                            <a href="AdminManageStaff?action=staff" class="see-all-btn">See all →</a>
                        </div>
                        <tr class="table-dark">
                            <th>Staff ID</th>
                            <th>Full Name</th>
                            <th>Is Active</th>
                        </tr>
                        <c:forEach var="staff" items="${staffTable}">
                            <tr>
                                <td>${staff.staffID}</td>
                                <td>${staff.fullName}</td>
                                <td>${staff.isActive}</td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div style="margin-bottom: 20px;"class="action-buttons-add">
                        <a href="AdminManagerProducts?action=BackToAdminDashboard" class="btn btn-custom btn-lg shadow">
                            <i class="fas fa-arrow-left"></i> Back to Admin Page
                        </a>
                    </div>
                </div>
            </c:when>
        </c:choose>
    </div>

</div>
</body>
</html>