<%-- 
    Document   : ViewCategory
    Created on : Mar 2, 2025, 10:39:36 AM
    Author     : Nguyen Phu Quy CE180789
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Product"%>
<%@page import="Model.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Manage Category - Admin</title>
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

        </style>
    </head>
    <body>
        <div class="sidebar">
            <h2><i class="fas fa-cogs"></i> Admin</h2>
            <a href="AdminManagerUser?action=user"><i class="fas fa-users"></i> Manage Users</a>
            <a href="AdminManageStaff?action=staff"><i class="fas fa-users"></i> Manage Staff</a>
            <a href="AdminManageCategory" class="active"><i class="fas fa-box"></i> Manage Category</a>
            <a href="AdminManagerProducts?action=product" ><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerOrders?action=order" ><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="ViewRatingListForAdmin?action=reviews"><i class="fas fa-comments"></i> Manage Reviews</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>

         <jsp:include page="Component/ManageForAdmin_Search.jsp">
        <jsp:param name="page" value="cate"/>
        </jsp:include> 
        

        <div class="main-content">
            <h2 class="text-center"><i class="fas fa-box"></i> Manage Category</h2>
            <table class="table table-bordered table-hover mt-4">
                <tr class="table-dark">
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="category" items="${categoryList}">
                    <tr>
                        <td>${category.categoryID}</td>
                        <td>${category.categoryName}</td>
                        <td class="description">
                            <c:choose>
                                <c:when test="${fn:length(category.description) > 20}">
                                    <span class="short-text">${fn:substring(category.description, 0, 100)}</span>
                                    <span class="full-text" style="display: none;">${category.description}</span>
                                </c:when>
                                <c:otherwise>
                                    ${category.description}
                                </c:otherwise>
                            </c:choose>
                        </td>




                        <td>
                            <div class="category-actions">
                                <a href="AdminManageCategoryEdit?action=editCategory&categoryID=${category.categoryID}" class="btn btn-warning btn-sm">
                                    <i class="fas fa-edit"></i> Edit
                                </a>
                                <a href="AdminManageCategoryDelete?action=delete&categoryID=${category.categoryID}"
                                   onclick="return confirm('Are you sure you want to delete this product?')" 
                                   class="btn btn-danger btn-sm">
                                    <i class="fas fa-trash"></i> Delete
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="text-center mt-4">
                <a href="ViewCategoryForAdminAdd.jsp" class="btn btn-success btn-lg">
                    <i class="fas fa-plus-circle"></i> Add New Category
                </a>
            </div>
            <!--            <div style="margin-bottom: 20px;" class="action-buttons-add">
                            <a href="AdminManagerProducts?action=addProduct" class="btn btn-custom btn-lg shadow">
                                <i class="fas fa-plus-circle"></i> Add New Product
                            </a>
                            <a href="AdminManagerProducts?action=viewDelete" class="btn btn-custom btn-lg shadow">
                                <i class="fas fa-trash"></i> View Deleted Products
                            </a>
                            <a href="AdminManagerProducts?action=productForDashBoard" class="btn btn-custom btn-lg shadow">
                                <i class="fas fa-arrow-left"></i> Back to Admin Page
                            </a>
                        </div>-->
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

