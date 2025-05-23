<%-- 
    Document   : Header
    Created on : Feb 11, 2025, 9:05:31 AM
    Author     : DUY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/ToyHead.css">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <header class=""> -->
<div class="row component-1 cover-page">

    <div class="col-md-6 header-logo">
        <img src="" alt="" >
    </div>
    <div class="col-md-6 option_1">
        <ul>
            <span class="line-row" style="margin: 14px 7px;"></span>
            <li class="option-header">
                <%
                    Object userObj = session.getAttribute("user");
                    String role = (userObj != null && session.getAttribute("role") != null) ? session.getAttribute("role").toString() : "";
                %>
                <% if (userObj != null) { %>
                <% if (role.equals("admin")) { %>
                <a href="AdminManagerProducts?action=productForDashBoard">Manage for Admin</a> |
                <% } %>
                <% if (role.equals("staff")) { %>
                <a href="StaffManagerOrders?action=ordersForDashBoard">Manage for Staff</a> |
                <% } %>
                <a href="LogoutController">Logout</a>
                <% } else { %>
                <a href="signIn.jsp">Sign In</a>
                <% }%>
            </li>
            <c:if test="${not empty sessionScope.user}">
                <span class="line-row"></span>
                <li class="option-header">
                    <a href="viewProfile?userId=${user.userId}">Manage Profile</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>

<div class="row component-2 cover-page">
    <div class="col-md-3 header-logo-2">
         <img src="https://toystory.vn/wp-content/uploads/2022/08/Logo-Header.png" alt="" style="width: 40%; height: 64px;" >
    </div>

    <div class="col-md-6 text-center">
        <ul class="category_List">
            <li class="category"><a href="ViewProductListController"><i style="font-size: 14px" class="fa-solid fa-house fa-beat"></i> Home Page</a></li>
            <li class="category"> <a href="AboutUs.jsp">About Us</a></li>
            <li class="category"> <a href="Contact.jsp">Contact</a></li>
        </ul>
    </div>

    <form action="SearchProduct" class="col-md-3 search_header" id="searchForm" method="GET">
        <input type="text" placeholder="search" name="searchKey" id="searchInput">
        <div id="searchButton" style="cursor: pointer; z-index: 2">
            <i style=" color: black;" class="fa-solid fa-magnifying-glass icon-search"></i>
        </div>
        <a href="ViewOrderListController"> <i class="fa-solid fa-newspaper"></i></a>
        <a href="ViewCartController"> <i class="fa-solid fa-bag-shopping"></i></a>
    </form>
</div>

<div class="component-3">
    <div class="banner_new-headerz">
        <div class="rightBanner_contentz">
            <h4>
                Move, Shop, Customise & Celebrate With Us
            </h4>
        </div>
        <div class="leftBanner_contentz">
            <span>
                No matter what you feel like doing today, It’s better as a Member. <br>
            </span>
        </div>
    </div>
</div>    

<script>
    function submitForm() {
        const searchInput = document.getElementById("searchInput");

        if (searchInput.value.trim() !== "") {
            document.getElementById("searchForm").submit();
        }
    }

    document.getElementById("searchButton").addEventListener("click", submitForm);

    document.getElementById("searchInput").addEventListener("keydown", function (event) {
        if (event.key === "Enter") {
            event.preventDefault();
            submitForm();
        }
    });

 </script>
