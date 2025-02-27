<%-- 
    Document   : adminDashboardd
    Created on : Feb 15, 2025, 2:02:16 PM
    Author     : lanoc
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Product"%>
<%@page import="Model.Orders"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            .center-container {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                height: 60vh;
                text-align: center;
                margin-left: 600px;
            }

            .mess {
                color: red;
                font-size: 32px;
                margin-bottom: 20px;
            }

            .message-box {
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                max-width: 600px;
            }
            p {
                font-size: 18px;
                color: #333;
            }

            .example {
                font-weight: bold;
                color: #007bff;
            }
            td.description {
                max-width: 250px;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
                position: relative;
            }

            td.description.expanded {
                white-space: normal;
                overflow: visible;
                max-width: 400px;
                background: #fff;
                box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
                padding: 5px;
                position: relative; /* Giữ nguyên vị trí */
            }

            .toggle-btn {
                display: block;
                margin-top: 5px;
                background-color: #EA83AA;
                border: none;
                color: white;
                cursor: pointer;
                text-decoration: none;
            }
            .toggle-btn:hover {
                color: black;
                background-color: #FCEBF2;
                transform: scale(1.05);
                text-decoration: none;
            }
            .stock-input-form {
                display: flex;
                align-items: center;
                gap: 5px;
            }

            .stock-input-form input[type="number"] {
                width: 80px;
                padding: 5px;
                font-size: 14px;
            }

            .stock-input-form button {
                padding: 5px 10px;
                font-size: 12px;
            }

        </style>
    </head>
    <body>
        <div class="sidebar">
            <h2><i class="fas fa-cogs"></i> Admin</h2>
            <a href="AdminManagerUser?action=user"><i class="fas fa-users"></i> Manage Users</a>
            <a href="AdminManagerStaff?action=staff" class="active"><i class="fas fa-users"></i> Manage Staffs</a>
            <a href="AdminManagerProducts?action=product"><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerProducts?action=order"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
           <a href="AdminManagerProducts?action=managerStock" ><i class="fas fa-warehouse"></i> Manage Stock</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to Home</a>
        </div>

        <jsp:include page="Component/ManageForAdmin_Search.jsp"></jsp:include>

        <c:choose>
            <c:when test="${not empty productList}">
                <div class="main-content">
                    <h2 class="text-center"><i class="fas fa-box"></i> Manage Products</h2>
                    <table class="table table-bordered table-hover mt-4">
                        <tr class="table-dark">
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Stock</th>
                            <th>Image</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach var="product" items="${productList}">
                            <tr>
                                <td>${product.productID}</td>
                                <td>${product.productName}</td>
                                <td class="description">
                                    <c:choose>
                                        <c:when test="${fn:length(product.description) > 20}">
                                            <span class="short-text">${fn:substring(product.description, 0, 100)}...</span>
                                            <span class="full-text" style="display: none;">${product.description}</span>
                                            <button class="toggle-btn btn btn-sm btn-link">See more...</button>
                                        </c:when>
                                        <c:otherwise>
                                            ${product.description}
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                <td>${product.price}</td>
                                <td>
                                    <form action="AdminManagerProducts?action=updateStock" method="POST"  class="stock-input-form">
                                        <input type="hidden" name="id" value="${product.productID}">
                                        <input type="number" name="stock" value="${product.stock}" min="0" style="width: 80px; padding: 5px;" required>
                                        <button type="submit" class="btn btn-primary btn-sm" style="margin-left: 5px; padding: 5px 10px;">
                                            Update
                                        </button>
                                    </form>
                                </td>

                                <td><img src="${product.imageUrl}" alt="Product Image" width="50"></td>
                                <td>
                                    <div class="product-actions">
                                        <a href="AdminManagerProducts?action=editProduct&id=${product.productID}" class="btn btn-warning btn-sm">
                                            <i class="fas fa-edit"></i> Edit
                                        </a>
                                        <a href="AdminManagerProducts?action=delete&id=${product.productID}" 
                                           onclick="return confirm('Are you sure you want to delete this product?')" 
                                           class="btn btn-danger btn-sm">
                                            <i class="fas fa-trash"></i> Delete
                                        </a>
                                        <a href="AdminManagerProducts?action=viewProductDetail&id=${product.productID}" class="btn btn btn-info btn-sm">
                                            <i class="fas fa-eye"></i> View Detail
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:when>
            <c:when test="${not empty userList}">
                <div class="main-content">
                    <h2 class="text-center"><i class="fas fa-box"></i> Manage Products</h2>
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
                </div>
            </c:when>
            <c:when test="${not empty orderList}">
                <div class="main-content">
                    <h2 class="text-center"><i class="fas fa-shopping-cart"></i> Manage Orders</h2>
                    <table class="table table-bordered table-hover mt-4">
                        <tr class="table-dark">
                            <th>Order ID</th><th>User ID</th><th>Total Amount</th><th>Created At</th><th>Address</th><th>Phone Number</th><th>Status</th><th>Action</th>
                        </tr>
                        <c:forEach var="order" items="${orderList}">
                            <tr>
                                <td>${order.orderId}</td>
                                <td>${order.userId}</td>
                                <td>${order.totalAmount}</td>
                                <td>${order.createdAt}</td>
                                <td>${order.address}</td>
                                <td>${order.phoneNumber}</td>
                                <td>${order.orderStatus}</td>
                                <td>
                                    <a href="AdminManagerOrders?action=viewDetails&id=${order.orderId}&price=${order.totalAmount}&userID=${order.userId}" class="btn btn-info btn-sm"><i class="fas fa-eye"></i> View Details</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:when>

            <c:otherwise>
                <div class="center-container">
                    <h1 style="margin-left: 100px;" class="mess">No results found!</h1>
                </div>
            </c:otherwise>
        </c:choose>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                document.querySelectorAll(".toggle-btn").forEach(button => {
                    button.addEventListener("click", function () {
                        let td = this.closest("td");
                        td.classList.toggle("expanded");

                        let shortText = td.querySelector(".short-text");
                        let fullText = td.querySelector(".full-text");

                        if (td.classList.contains("expanded")) {
                            fullText.style.display = "inline";
                            shortText.style.display = "none";
                            this.textContent = "Collapse";
                        } else {
                            fullText.style.display = "none";
                            shortText.style.display = "inline";
                            this.textContent = "See more...";
                        }
                    });
                });
            });

        </script>
    </body>
</html>
