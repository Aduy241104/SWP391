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
            <a href="AdminManagerProducts?action=product" class="active"><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerOrders?action=order"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="ViewRatingListForAdmin?action=reviews"><i class="fas fa-comments"></i> Manage Reviews</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>
        <% } else { %>
        <div class="sidebar">
            <h2  style="color: white; margin-bottom: 10px; " ><i class="fas fa-cogs"></i> Staff</h2>
            <a href="AdminManagerProducts?action=product" class="active"><i class="fas fa-box"></i> Manage Products</a>
            <a href="StaffManagerOrders?action=orders" ><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>
        <% }%>

        <jsp:include page="Component/ManageForAdmin_Search.jsp">
            <jsp:param name="page" value="product"/>
        </jsp:include>

            <div class="main-content">
                <h2 class="text-center"><i class="fas fa-box"></i> Deleted Products</h2>
                <table class="table table-bordered table-hover mt-4">
                    <tr class="table-dark">
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Stock</th>
                        <th>Image</th>
                        <th>Category</th>
                        <th>Active</th>
                        <th>Size</th>
                        <th>Age Range</th>
                        <th>Origin</th>
                        <th>Weight</th>
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
                        <td>${product.stock}</td>
                        <td><img src="${product.imageUrl}" alt="Product Image" width="50"></td>
                        <td>${product.categoryID}</td>
                        <td>${product.isActive ? "Yes" : "No"}</td>
                        <td>${product.size}</td>
                        <td>${product.ageRange}</td>
                        <td>${product.origin}</td>
                        <td>${product.weight} kg</td>
                        <td>
                            <div class="action-buttons">
                                <a href="AdminManagerProducts?action=restore&id=${product.productID}" class="btn btn-warning btn-sm">
                                    <i class="fas fa-edit"></i> Restore
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div style="margin-bottom: 20px;" class="action-buttons-add">


                <a href="AdminManagerProducts?action=product" class="btn btn-custom btn-lg shadow">
                    <i class="fas fa-arrow-left"></i> Back to Manage Products Page
                </a>
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
