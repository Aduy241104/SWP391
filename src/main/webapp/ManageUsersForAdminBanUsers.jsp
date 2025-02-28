<%-- 
    Document   : ManagerOrdersForAdmin
    Created on : Feb 15, 2025, 2:41:56 PM
    Author     : thaiv
--%>

<%@page import="java.util.List"%>
<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manage Users - Admin</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styleToy.css">
        <link rel="stylesheet" href="font/fontawesome-free-6.5.2-web/css/all.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/AdminDashboardStyle.css"/>
        <link rel="stylesheet" href="css/ManageProductForAdminStyles.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600&display=swap" rel="stylesheet">
        <style>
            .product-actions {
                display: flex;
                gap: 10px;
                flex-wrap: wrap;
                justify-content: center;
                max-width: 300px;
                
            }

            .product-actions a, .product-actions button {
                flex: 1;
                max-width: 120px;
                text-align: center;
            }
        </style>
    </style>
</head>
<body>
    <div class="sidebar">
        <h2><i class="fas fa-cogs"></i> Admin</h2>
        <a href="AdminManagerUser?action=user" class="active"><i class="fas fa-users"></i> Manage Users</a>
        <a href="AdminManageStaff?action=staff"><i class="fas fa-users"></i> Manage Staff</a>
        <a href="AdminManagerProducts?action=product"><i class="fas fa-box"></i> Manage Products</a>
        <a href="AdminManagerProducts?action=order"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
        <a href="AdminManagerProducts?action=managerStock" ><i class="fas fa-warehouse"></i> Manage Stock</a>
        <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
    </div>

    <jsp:include page="Component/ManageForAdmin_Search.jsp"></jsp:include>

        <div  class="main-content">
            <h2 class="text-center"><i class="fas fa-users"></i> Manage Users</h2>
            <table class="table table-bordered table-hover mt-4">
                <tr class="table-dark">
                    <th>User ID</th>
                    <th>Username</th>
                    <th>Full Name</th>
                    <th>Action</th>
                </tr>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.username}</td>
                    <td>${user.fullName}</td>
                    <td>
                        <div class="product-actions">
                            <c:choose>
                                <c:when test="${user.role eq 'Admin'}">
                                    <button  style="padding: 5px 18px;" style="margin-left: 10px;" class="btn btn-secondary btn-sm" disabled>
                                        <i class="fas fa-ban"></i> Disabled
                                    </button>
                                </c:when>
                                <c:when test="${user.isActive eq true}">
                                    <a style="padding: 5px 16px;" href="AdminManagerUser?action=banUser&id=${user.userId}" 
                                       onclick="return confirm('Are you sure you want to ban this user?')" 
                                       class="btn btn-danger btn-sm">
                                        <i class="fas fa-ban"></i> Ban User
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="AdminManagerUser?action=unBanUser&id=${user.userId}" 
                                       onclick="return confirm('Are you sure you want to unban this user?')" 
                                       class="btn btn-success btn-sm">
                                        <i class="fas fa-check"></i> Unban User
                                    </a>
                                </c:otherwise>
                            </c:choose>
                            <a href="AdminManagerUser?action=viewUserDetails&id=${user.userId}" class="btn btn-info btn-sm">
                                <i class="fas fa-eye"></i> View Detail
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>


        </table>
        <div style="margin-bottom: 20px;" class="action-buttons-add">
            <a href="AdminManagerProducts?action=addProduct" class="btn btn-custom btn-lg shadow">
                <i class="fas fa-plus-circle"></i> Add New User
            </a>
            <a href="AdminManagerUser?action=user" class="btn btn-custom btn-lg shadow">
                <i class="fas fa-arrow-left"></i> Back to User Page
            </a>
        </div>
    </div>
</body>
</html>
