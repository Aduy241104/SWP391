<%-- 
    Document   : adminDashboardd
    Created on : Feb 15, 2025, 2:02:16 PM
    Author     : thaiv
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
            .action-buttons-add {
                position: absolute;
                bottom: 20px;
                right: 20px;
            }

        </style>
    </head>
    <body>
        <div class="sidebar">
            <h2><i class="fas fa-cogs"></i> Admin</h2>
            <a href="AdminManagerUser?action=user"><i class="fas fa-users"></i> Manage Users</a>
            <a href="AdminManageStaff?action=staff"><i class="fas fa-users"></i> Manage Staff</a>
            <a href="AdminManagerProducts?action=product"><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerOrders?action=order" class="active" ><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="AdminManagerProducts?action=managerStock" ><i class="fas fa-warehouse"></i> Manage Stock</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>

        <jsp:include page="Component/ManageForAdmin_Search.jsp">
            <jsp:param name="page" value="order"/>
        </jsp:include>

        <c:choose>
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
                    <div class="action-buttons-add">
                        <a href="AdminManagerOrders?action=order" class="btn btn-custom btn-lg shadow">
                            <i class="fas fa-arrow-left"></i> Back to Manage Orders
                        </a>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="center-container">
                    <h1 style="margin-left: 100px;" class="mess">No results found!</h1>
                </div>
                <div class="action-buttons-add">
                    <a href="AdminManagerOrders?action=order" class="btn btn-custom btn-lg shadow">
                        <i class="fas fa-arrow-left"></i> Back to Manage Orders
                    </a>
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
