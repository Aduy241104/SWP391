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
                                        <form style="width: 70%" class="function_add" action="EditCart" method="GET">
                                            <i class="fa-solid fa-minus" onclick="updateQuantity(this, -1)"></i>
                                            <input name="quantity" style="width: 21px;" type="text" value="${x.quantity}" max="${x.product.stock}" readonly>
                                            <input name="cartItemID" type="hidden" value="${x.cartItemID}">
                                            <input name="action" type="hidden" value="edit">
                                            <i class="fa-solid fa-plus" onclick="updateQuantity(this, 1)" style="margin-left: 2px;"></i>
                                        </form>
                                        <div class="wish-list">
                                            <a href="EditCart?cartItemID=${x.cartItemID}&action=delete" style="color: red">
                                                <i class="fa-solid fa-trash"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-9 product_detail">
                                    <div class="header-productDetail">
                                        <p>${x.product.productName}</p>
                                        <c:if test="${x.product.stock > 0}">
                                            <input type="checkbox" class="cart-checkbox"
                                                   value="${x.cartItemID}"
                                                   data-price="${x.product.price}"
                                                   onchange="updateTotalPrice()">
                                        </c:if>
                                    </div>
                                    <p>Price: ${x.product.price} $</p>
                                    <p>In Stock: ${(x.product.stock == 0) ? "out of stock" : x.product.stock}</p>
                                </div>
                                <div class="col-md-12">
                                    <div class="bottom-line"></div>
                                </div>
                            </div>
                            <c:set var="totalPrice" value="${totalPrice + (x.quantity * x.product.price)}"/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div>
                            <img src="img/empty-cart.png" alt="alt"/>
                            <h4 style="margin-top: 40px; margin-left:45px">Your cart is empty</h4>
                            <p style="margin-top:10px; margin-bottom: 20px">Add something to make me happy 💕💕</p>
                            <a style="padding: 7px 30px; text-align: center; border-radius: 9px; background-color: pink; margin-left: 65px"
                               href="ViewProductListController">Go Shopping</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- Form Checkout -->
            <form class="col-md-4 summary" action="CreateOrder" method="POST" onsubmit="prepareForm(event)">
                <h3 class="col-md-12 vf">Summary</h3>
                <div class="col-md-12 Summary_detail">
                    <div class="Summary_detail-1 gena">
                        <p>Subtotal</p>
                        <p id="totalPriceDisplay">0$</p>
                    </div>
                    <div class="Summary_detail-2 gena">
                        <p>Estimated Delivery & Handling</p>
                        <p>Free</p>
                    </div>
                </div>

                <!-- Input ẩn để lưu sản phẩm được chọn -->
                <div id="selectedItemsContainer"></div>

                <div class="col-md-12 check-out text-center">
                    <button type="submit" class="check-out-button" style="background-color: green;">
                        <p>Checkout</p>
                    </button>
                </div>
            </form>
        </div>

        <footer style="margin-top: 80px;" class="container-fluid" id="footer">
            <jsp:include page="Component/Footer.jsp"></jsp:include>
        </footer>

        <script>
            function prepareForm(event) {
                let selectedItemsContainer = document.getElementById("selectedItemsContainer");
                selectedItemsContainer.innerHTML = ""; // Xóa dữ liệu cũ

                let checkboxes = document.querySelectorAll(".cart-checkbox:checked");
                if (checkboxes.length === 0) {
                    alert("Please select at least one product before checkout!");
                    event.preventDefault(); // Ngăn form submit nếu không có sản phẩm nào được chọn
                    return;
                }

                checkboxes.forEach(checkbox => {
                    let input = document.createElement("input");
                    input.type = "hidden";
                    input.name = "selectedItems";
                    input.value = checkbox.value; // Chỉ lấy cartItemID

                    selectedItemsContainer.appendChild(input);
                });
            }

            function updateTotalPrice() {
                let checkboxes = document.querySelectorAll(".cart-checkbox:checked");
                let totalPrice = 0;

                checkboxes.forEach(checkbox => {
                    let price = parseFloat(checkbox.getAttribute("data-price"));
                    let productContainer = checkbox.closest(".product_detail");
                    let quantityInput = productContainer.parentElement.querySelector("input[name='quantity']");
                    let quantity = parseInt(quantityInput.value);
                    totalPrice += price * quantity;
                });

                document.getElementById("totalPriceDisplay").innerText = totalPrice.toFixed(2) + " $";
            }

            function updateQuantity(element, change) {
                const input = element.parentElement.querySelector("input[name='quantity']");
                const max = parseInt(input.getAttribute("max"));
                const min = 1;
                let quantity = parseInt(input.value);

                if ((quantity === min && change < 0) || (quantity === max && change > 0)) {
                    return;
                }

                quantity += change;
                input.value = quantity;

                // Gửi form tự động
                const form = element.parentElement;
                form.submit();
            }
        </script>
    </body>
</html>
