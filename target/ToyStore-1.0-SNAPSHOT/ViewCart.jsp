<%-- 
    Document   : ViewCart
    Created on : Feb 11, 2025, 8:54:16 AM
    Author     : DUY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <!-- <link rel="stylesheet" href="css/bootstrap.min.css"> -->
        <link rel="stylesheet" href="css/cartStyle-2.css">
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
        <div class="container-fluid">
            <jsp:include page="Component/Header.jsp"></jsp:include>
            </div>

            <div class="container">
                <div class="col-md-8 row Cart_detail">
                    <h3 class="col-md-12 vf">Cart</h3>

                <c:set var="totalPrice" value="0" />
                <c:choose>
                    <c:when test="${not empty cartList}"> 
                        <c:forEach items="${requestScope.cartList}" var="x">
                            <div class="col-md-12 row bag">
                                <div class="col-md-3 product_img">                   
                                    <img src="${x.product.imageUrl}" alt="product">
                                    <div style="width: 170px" class="function_place">
                                        <!--                                        <form style="width: 70%" class="function_add">
                                                                                    <i class="fa-solid fa-minus"></i>
                                                                                    <input name="" style="width: 21px;" type="text" value="${x.quantity}" max="${x.product.stock}" readonly>           
                                                                                    <i style="margin-left: 2px;" class="fa-solid fa-plus"></i>
                                                                                </form>  -->

                                        <form style="width: 70%" class="function_add" action="EditCart" method="GET">
                                            <!-- Nút giảm số lượng -->
                                            <i class="fa-solid fa-minus" onclick="updateQuantity(this, -1)"></i>

                                            <!-- Ô nhập số lượng -->
                                            <input name="quantity" style="width: 21px;" type="text" value="${x.quantity}" max="${x.product.stock}" readonly>
                                            <input name="cartItemID" style="width: 21px;" type="hidden" value="${x.cartItemID}" >
                                            <input name="action" style="width: 21px;" type="hidden" value="edit" >

                                            <!-- Nút tăng số lượng -->
                                            <i class="fa-solid fa-plus" onclick="updateQuantity(this, 1)" style="margin-left: 2px;"></i>
                                        </form>
                                        <div class="wish-list">
                                            <a href="EditCart?cartItemID=${x.cartItemID}&action=delete" style="color: red"> <i class="fa-solid fa-trash"></i></a>
                                        </div>  
                                    </div>
                                </div>
                                <div class="col-md-9 product_detail">
                                    <div class="header-productDetail">
                                        <p>${x.product.productName}</p>
                                        <c:if test = "${x.product.stock > 0}">
                                            <input type="checkbox" id="id" 
                                                   name="item" 
                                                   value="${x.cartItemID}"
                                                   data-quantity="${x.quantity}" 
                                                   data-price="${x.product.price}" 
                                                   onchange="updateTotalPrice()"
                                                   >
                                        </c:if>
                                    </div>
                                    <p>Price: ${x.product.price} $</p>
                                    <p>In Stock: ${(x.product.stock == 0)? "out of stock" : x.product.stock} </p>
                                </div>     
                                <div class="col-md-12">
                                    <div class="bottom-line">
                                    </div>
                                </div>     
                            </div>  
                            <c:set var="totalPrice" value="${totalPrice + (x.quantity * x.product.price)}" />
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div>
                            <img src="img/empty-cart.png" alt="alt"/>
                            <h5>Your cart is empty</h5>
                            <p>Add something to make me happy 💕💕</p>
                            <a href="ViewProductListController">Go Shopping</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <form class="col-md-4 summary">
                <h3 class="col-md-12 vf">Summary</h3>
                <div class="col-md-12 Summary_detail">
                    <div class="Summary_detail-1 gena">
                        <p>Subtotal</i></p>
                        <p>${totalPrice}$</p>
                    </div>
                    <div class="Summary_detail-2 gena">
                        <p>Estimated Delivery & Handling</i></p>
                        <p>Free</p>
                    </div>
                </div>
                <div class="col-md-12 Summary_detail">
                    <div class="Summary_detail-1 gena">
                        <p>Subtotal</i></p>
                        <p>${totalPrice}$</p>
                    </div>
                </div>
                <div class="col-md-12 check-out text-center">
                    <button class="check-out-button" style="background-color: green;">
                        <p>Checkout</p>
                    </button>
                </div>
            </form>
        </div>

        <footer style="margin-top: 80px;" class="container-fluid" id="footer">
            <jsp:include page="Component/Footer.jsp"></jsp:include>
        </footer>


        <script>
            function updateQuantity(element, change) {
                // Lấy thẻ input trong cùng form
                const input = element.parentElement.querySelector("input[name='quantity']");
                const max = parseInt(input.getAttribute("max")); // Lấy giá trị max
                const min = 1; // Giá trị nhỏ nhất là 1
                let quantity = parseInt(input.value); // Lấy giá trị hiện tại của ô input

                // Kiểm tra nếu số lượng đã đạt min và người dùng nhấn "-"
                if (quantity === min && change < 0) {
                    return; // Dừng lại, không giảm nữa
                }

                // Kiểm tra nếu số lượng đã đạt max và người dùng nhấn "+"
                if (quantity === max && change > 0) {
                    return; // Dừng lại, không tăng nữa
                }

                // Tăng hoặc giảm số lượng
                quantity += change;

                // Cập nhật giá trị mới
                input.value = quantity;

                // Gửi form tự động
                const form = element.parentElement; // Lấy form chứa thẻ input
                form.submit(); // Gửi form
            }
        </script>


    </body>
</html>
