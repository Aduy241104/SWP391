<%-- 
    Document   : adminDashboard
    Created on : Feb 15, 2025, 2:02:16 PM
    Author     : lanoc
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manage Products - Admin</title>
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
            .total-amount {
                display: flex;
                justify-content: space-evenly;
                text-align: right;
                font-size: 18px;
                font-weight: bold;
                margin-bottom: 15px;

            }
            .total-h2{
                padding: 10px;
                text-align: center;
                display: block;
                border: 2px solid;
                border-radius: 10px;
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

        <div class="main-content">
             <div class="total-amount">
                 <h4 class="total-h2">Total Import: <span style="color: #ED1164; margin-top: 5px;">${totalIm}$</span></h4>
                 <h4 class="total-h2">Total Export: <span style="color: #ED1164; margin-top: 5px;">${totalEx}$</span></h4>
                <h4 class="total-h2">Total Amount: <span style="color: #ED1164; margin-top: 5px;">${totalStock}$</span></h4>
            </div>
            <h2 class="text-center"><i class="fas fa-box"></i> Manage Stock</h2>
            <table class="table table-bordered table-hover mt-4">
                <tr class="table-dark">
                    <th>ID</th>
                    <th>Name</th>
                    <th>Stock</th>
                    <th>Action</th>
                </tr>
                <h2 style="text-align: center; color: red;">${requestScope.error}</h2>
                <c:forEach var="product" items="${productList}">
                    <tr>
                        <td>${product.productID}</td>
                        <td>${product.productName}</td>
                        <td>${product.stock}</td>
                        <td>
                            <form action="AdminManageStock?action=ImportStock" method="POST"  class="stock-input-form">
                                <input type="hidden" name="id" value="${product.productID}">
                                <input type="hidden" name="stock" value="${product.stock}">
                                <input type="number" name="newStock"  min="0" style="width: 80px; padding: 5px;" required>
                                <button type="submit" class="btn btn-primary btn-sm" style="margin-left: 5px; padding: 5px 10px;">
                                    Import
                                </button>
                            </form>
                            <form action="AdminManageStock?action=ExportStock" method="POST"  class="stock-input-form">
                                <input type="hidden" name="id" value="${product.productID}">
                                <input type="hidden" name="stock" value="${product.stock}">
                                <input type="number" name="newStock"  min="0" style="width: 80px; padding: 5px;" required>
                                <button type="submit" class="btn btn-primary btn-sm" style="margin-left: 5px; padding: 5px 10px;">
                                    Export 
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
                <div class="action-buttons-add" style="margin-top: 20px">
                    <a href="AdminManageStock?action=Import&id=${product.productID}" style="margin-bottom: 20px;" class="btn btn-custom btn-lg shadow">
                    <i class="fas fa-hourglass-half"></i> View Import Orders
                </a>
                <a  href="AdminManageStock?action=Export&id=${product.productID}" style="margin-bottom: 20px;"  class="btn btn-custom btn-lg shadow">
                    <i class="fas fa-truck"></i> View Export Orders
                </a>
                <% if ("admin".equals(role)) {%>
                <div style="margin-bottom: 20px;" class="">
                    <a href="AdminManagerProducts?action=productForDashBoard" class="btn btn-custom btn-lg shadow">
                        <i class="fas fa-arrow-left"></i> Back to Admin Page
                    </a>
                </div>
                <% } else { %>
                <div style="margin-bottom: 20px;" class="">
                    <a href="StaffManagerOrders?action=productForDashBoard" class="btn btn-custom btn-lg shadow">
                        <i class="fas fa-arrow-left"></i> Back to Admin Page
                    </a>
                </div>
                <% }%>
            </div>


        </div>
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
