<%-- 
    Document   : index
    Created on : Feb 9, 2025, 10:58:58 AM
    Author     : DUY
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Product"%>
<%@page import="Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styleToy.css">
        <link rel="stylesheet" href="font/fontawesome-free-6.5.2-web/css/all.min.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 
    </head>
    <body>
        <div class="container-fluid" style="background-color: rgb(255, 255, 255);">

            <header class="" id="header">
                <jsp:include page="Component/Header.jsp"></jsp:include>
                </header>
                <nav class="row header-bodyWeb cover-page">
                    <div class="col-md-6 header-bodyWeb-component_1">

                        <span><a href="">New Release </a> / <a href="">Toys</a></span>
                        <h3>All Toys (<p style="display: inline; color: palevioletred;">${requestScope.productList.size()}</p>)</h3>

                </div>
                <div class="col-md-6 header-bodyWeb-component_2">
                    <h3>Hello ${sessionScope.user.fullName} 😻</h3>
                </div>
            </nav>


            <div class="row body-web cover-page">
                <div class="col-md-2 left-menu custom-scroll">
                    <form class="filter-group" action="FilterProduct" method="get">
                        <div>
                            <h4>Age Range</h4>
                            <select class="age-range" name="age">
                                <option value="all" ${selectedAge == 'all' ? 'selected' : ''}>All</option>
                                <option value="1-3" ${selectedAge == '1-3' ? 'selected' : ''}>From 1 to 3 year olds</option>
                                <option value="0-1" ${selectedAge == '0-1' ? 'selected' : ''}>From 0 to 1 year olds</option>
                                <option value="3" ${selectedAge == '3' ? 'selected' : ''}>From 3 years olds</option>
                            </select>
                        </div>

                        <div class="filter-group-1">
                            <h4>Category</h4>
                            <c:forEach var="cate" items="${requestScope.categoryList}">
                                <div>
                                    <input type="checkbox" name="category" value="${cate.categoryID}" 
                                           ${selectedCategories.contains(cate.categoryID) ? 'checked' : ''}>
                                    <label for="category">${cate.categoryName}</label>
                                </div>
                            </c:forEach>
                        </div>

                        <div class="filter-group-2">
                            <h4>Price</h4>
                           

                            <div>
                                <input type="checkbox" name="price" value="1"
                                       <c:if test="${fn:contains(selectedPrices, '1')}">checked</c:if>>
                                       <label>From 1$ to 50$</label>
                                </div>

                                <div>
                                    <input type="checkbox" name="price" value="2"
                                    <c:if test="${fn:contains(selectedPrices, '2')}">checked</c:if>>
                                    <label>From 50$ to 100$</label>
                                </div>

                                <div>
                                    <input type="checkbox" name="price" value="3"
                                    <c:if test="${fn:contains(selectedPrices, '3')}">checked</c:if>>
                                    <label>More than 100$</label>
                                </div>
                            </div>

                            <button type="submit">Apply</button>
                        </form>
                    </div>

                    <div class="col-lg-10 right-menu">
                    <c:choose>
                        <c:when test="${not empty productList}">
                            <c:forEach var="product" items="${productList}">

                                <div style="margin-bottom: 30px;background-color: white;" class="col-lg-3 items">
                                    <a href="ViewProductDetailController?productID=${product.productID}" style="padding: 20px 10px;">
                                        <img src="${product.imageUrl}" alt="">
                                        <div class="information">
                                            <p style="font-size: 13px">Just in</p>
                                            <p>${product.productName}</p>
                                            <p style="font-size: 13px">${product.description}</p>
                                            <p>${product.price}$</p>
                                        </div>
                                    </a>
                                </div>

                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p>Not Found</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <footer style="margin-top: 80px;" class="container-fluid" id="footer">
            <jsp:include page="Component/Footer.jsp"></jsp:include>
        </footer>

    </body>
</html>
