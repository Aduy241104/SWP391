<%-- 
    Document   : orderList
    Created on : Feb 22, 2025, 7:15:43 AM
    Author     : Nguyen Phu Quy CE180789
--%>

<%@page import="Model.Orders" %>
<%@page import="java.util.List" %>
<%@page import="java.sql.Connection" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>OrderList Manage</title>

        <link rel="stylesheet" href="css/manageOrder.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet"> -->

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- <link rel="stylesheet" href="css/styleHeader.css"> -->
        <link rel="stylesheet" href="font/fontawesome-free-6.5.2-web/css/all.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <style>
            body {
                background-color: #f8f8f8;
                font-family: "Montserrat", sans-serif;
                overflow-x: hidden;
            }
            .container {
                margin-top: 30px;
            }
            .order-card {
                box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
                transition: transform 0.3s ease-in-out;
                background: #ffffff;
                padding: 15px;
                border-left: 5px solid rgb(123, 237, 17);
            }
            .order-card:hover {
                transform: translateY(-5px);
            }
            .status {
                font-weight: bold;
                font-size: 1.1em;
            }
            .btn-status {
                margin: 5px;
                font-weight: bold;
                border-radius: 20px;
                padding: 8px 15px;
            }
            .btn-status:hover {
                opacity: 0.9;
            }
            input.form-control {
                border-radius: 20px;
                padding: 10px;
                font-size: 16px;
            }
            h2 {
                font-weight: 700;
            }
        </style>
    </head>
    <body>


        <header class="" id="header">
            <jsp:include page="Component/Header.jsp"></jsp:include>
            </header>
        <div class="container">
            <h2 class="text-center mb-4">📦 Manage Order</h2>

            <div class="row">
                <div class="col-lg-3" >
                    <div style="background-color: rgb(250, 250, 250); border-radius: 5px; border: 1px solid black;" class="row optional">

                        <a href="ViewOrderListController" class="option ${selectedStatus == null ? 'actives' : ''}">All</a>
                        <a href="ViewOrderListController?oderStatus=pending" class="option ${selectedStatus == 'pending' ? 'actives' : ''}">Pending</a>
                        <a href="ViewOrderListController?oderStatus=shipping" class="option ${selectedStatus == 'shipping' ? 'actives' : ''}">Shipping</a>
                        <a href="ViewOrderListController?oderStatus=delivered" class="option ${selectedStatus == 'delivered' ? 'actives' : ''}">Delivered</a>
                    </div>

                    <div style="background-color: rgb(250, 85, 113); border-radius: 10px; margin-top: 15px;" class="row optional">
                        <h3>Total</h3>
                    </div>
                </div>

                <div class="col-lg-9">
                    <c:forEach var="order" items="${orderList}">
                        <div class="card order-card mb-3">
                            <a href="ViewOrderDetailController?orderId=${order.orderId}" class="text-decoration-none text-dark">
                                <div class="row">
                                    <div class="col-md-3"><strong>ID:</strong> ${order.orderId}</div>
                                    <div class="col-md-3"><strong>Date:</strong> ${order.createdAt}</div>
                                    <div class="col-md-3"><strong>Total:</strong> ${order.totalAmount} VND</div>
                                    <div class="col-md-3 text-end status text-warning">${order.orderStatus}</div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>




            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
