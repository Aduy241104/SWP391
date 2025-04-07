<%-- 
    Document   : productDetail
    Created on : Feb 13, 2025, 11:52:03 PM
    Author     : NHATHCE181222
--%>


<%@page import="Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Details</title>

        <link rel="stylesheet" href="css/PrDt.css">
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


            .customize {
                padding: 12px 50px;
                border-radius: 18px;
                font-size: 17px;
                border: none;
                font-weight: 600;
            }

            .customize:first-child {
                background-color: rgb(237, 17, 100);
            }

            .customize:last-child {
                border: 2px solid rgb(237, 17, 100);
                color: rgb(237, 17, 100);
            }

            .toast-container {
                position: fixed;
                top: 200px;
                right: 20px;
                z-index: 1000;
            }

            .toast {
                display: none;
                background-color: #d4edda;
                border: 1px solid #c3e6cb;
                border-radius: 4px;
                padding: 16px;
                color: #155724;
                font-size: 14px;
                box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
                animation: fadeInOut 3.5s ease forwards;
            }

            .toast.show {
                display: block;
            }
            @keyframes fadeInOut {
                0% {
                    opacity: 0;
                    transform: translateY(-10px);
                }
                10%, 90% {
                    opacity: 1;
                    transform: translateY(0);
                }
                100% {
                    opacity: 0;
                    transform: translateY(-10px);
                }
            }
            .modal-3 {
                display: none;
                position: fixed;
                z-index: 1000;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.4);
            }
            .modal-content-3 {
                background-color: white;
                margin: 15% auto;
                padding: 20px;
                border-radius: 8px;
                width: 300px;
                text-align: center;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }
            .close-btn-3 {
                background-color: red;
                color: white;
                border: none;
                padding: 10px 20px;
                cursor: pointer;
                border-radius: 4px;
                margin-top: 10px;
            }
            .show-3 {
                display: block;
            }
        </style>
    </head>
    <body>
        <header class="container-fluid" id="header">
            <jsp:include page="Component/Header.jsp"></jsp:include>
            </header>


            <div class="container product-details-container">
                <div class="row">
                    <div class="col-md-6" style="height: 500px;">
                        <img  src="${product.imageUrl}" alt="Product Image" class="product-image">
                </div>
                <div class="col-md-6">
                    <h2>${product.productName}</h2>
                    <p class="product-price">${product.price}$</p>

                    <p><strong>Stock: </strong> <strong class="text-primary">${(product.stock > 0) ? product.stock: "Out Of Stock"} </strong> </p>

                    <c:if test="${product.stock > 0}">
                        <div class="quantity-place">
                            <button id="decrease"><i class="fa-solid fa-minus"></i></button>
                            <input type="text" id="quantity" readonly value="1" min="1" max="${product.stock}">
                            <button id="increase"><i class="fa-solid fa-plus"></i></button>
                        </div>
                    </c:if>
                    <div class="product-buttons">
                         <c:if test="${product.stock <= 0}">
                             <a id="addToCartBtn" class="btn btn-primary customize" href="AddToCart?productID=${product.productID}&quantity=1" style="display: none">Add To Cart</a>
                        </c:if>
                        <c:if test="${product.stock > 0}">
                            <a id="addToCartBtn" class="btn btn-primary customize" href="AddToCart?productID=${product.productID}&quantity=1">Add To Cart</a>
                        </c:if>
                        <a id="buyNowBtn" class="btn customize" href="ViewFeedback?productID=${product.productID}">FeedBack</a>
                    </div>

                    <div class="" style="margin-top: 120px">
                        <h4>Product Information</h4>
                        <table class="table product-info-table">
                            <tbody>
                                <tr>
                                    <th>Origin</th>
                                    <td>${product.origin}</td>
                                </tr>
                                <tr>
                                    <th>Size</th>
                                    <td>${product.size}</td>
                                </tr>
                                <tr>
                                    <th>Age of use</th>
                                    <td>From ${product.ageRange} years old</td>
                                </tr>
                                <tr>
                                    <th>Weight</th>
                                    <td>${product.weight}g</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="col-md" style="height: 375px;">
                        <h3>Product description</h3>
                        <p style="line-height: 22px;">
                            ${product.description}
                        </p>
                    </div>
                </div>
          
            </div>
            <div class="row">
                <h3 style="margin-left: 19px; margin-bottom: 20px;">Related Toy</h3>
                <c:forEach var="ctl" items="${requestScope.listRelatedProduct}">
                    <div class="col-lg-3 col-md-4 col-xs-6 suggest-product">
                        <a href="ViewProductDetailController?productID=${ctl.productID}">
                            <div class="rxs" href="">
                                <img src="${ctl.imageUrl}" alt="">
                                <div class="suggest-product_infor">
                                    <h5>${ctl.productName}</h5>
                                    <p>${ctl.price}</p>
                                </div>
                            </div>
                        </a>
                    </div>          
                </c:forEach>
            </div>
        </div>

        <c:if test="${isAdded == 'added'}">
            <div class="toast-container">
                <div class="toast" id="success-toast">
                    Added Product to Cart
                </div>
            </div>
        </c:if>

        <c:if test="${isAdded == 'notAdded'}">
            <div id="cartModal" class="modal-3" style="display: none;">
                <div class="modal-content-3">
                    <p>The number of products in the cart has exceeded the limit!</p>
                    <button class="close-btn-3" onclick="closeModal()">Confirm</button>
                </div>
            </div>
        </c:if>

        <footer class="container-fluid">
            <jsp:include page="Component/Footer.jsp"></jsp:include>
        </footer>


        <script src="Js/Prdt.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                        function showModal() {
                            document.getElementById("cartModal").classList.add("show-3");
                            document.getElementById("cartModal").style.display = "block";
                        }
                        function closeModal() {
                            document.getElementById("cartModal").classList.remove("show-3");
                            document.getElementById("cartModal").style.display = "none";
                        }

                        // Tự động hiển thị modal khi trang tải xong
                        window.onload = function () {
                            showModal();
                        };
        </script>
        <script>
            // Hàm hiển thị Toast
            function showToast() {
                const toast = document.getElementById("success-toast");
                if (toast) {
                    // Thêm class 'show' để hiển thị Toast
                    toast.classList.add("show");

                    // Ẩn Toast sau 3 giây
                    setTimeout(() => {
                        toast.classList.remove("show");
                    }, 3000);
                }
            }

            // Gọi hàm showToast khi cần (ví dụ: khi thêm sản phẩm vào giỏ hàng thành công)
            document.addEventListener("DOMContentLoaded", () => {
                // Giả lập thêm sản phẩm thành công
                const isAdded = true;
                ; // Đây là giá trị giả lập từ server
                if (isAdded) {
                    showToast();
                }
            });
        </script>
    </body>
</html>


