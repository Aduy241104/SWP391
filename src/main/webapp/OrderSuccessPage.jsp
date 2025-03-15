<%-- 
    Document   : OrderSuccessPage
    Created on : Mar 12, 2025, 1:40:35 PM
    Author     : DUY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="css/OderScuccess.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="font/fontawesome-free-6.5.2-web/css/all.min.css">
         <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <style>
              body{
                font-family: "Montserrat", sans-serif;
                overflow-x: hidden;
            }
        </style>
    </head>
    <body>
        <header class="container-fluid" id="header">
            <jsp:include page="Component/Header.jsp"></jsp:include>
            </header>
            <div class="container ghj" style="margin-bottom: 100px">
                <div class="layout-order-success">
                    <div class="text-center f1">
                       <i class="fa-solid fa-circle-check fa-beat"></i>
                        <h3>Thank you for your order</h3>
                    </div>
                    <div class="f2">
                        <p>Your order has been successfully placed and will be delivered within 4 days.</p>

                        <a class="btn btn-danger" style="border-radius: 15px;" href="">View your Order</a>
                    </div>
                </div>
            </div>


            <footer class="container-fluid">
            <jsp:include page="Component/Footer.jsp"></jsp:include>
        </footer>
    </body>
</html>