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
                display: none; /* Mặc định ẩn */
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

            /* Hiệu ứng mờ dần */
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
                    <p><strong>Promotion:</strong> Enter code <span class="text-danger">VNPAYAVA1</span> for discounts from $0.50 to $1.00</p>
                    <p><strong>Stock:</strong> ${product.stock}</p>
                    <div class="product-buttons">

                        <a class="btn btn-primary customize" href="AddToCart?productID=${product.productID}&quantity=1">Add To Cart</a>
                        <a class="btn customize" href="AddToCart?productID=4&quantity=3">Buy Now</a>
                    </div>
                    <div class="">
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
                                    <th>Product expiry date</th>
                                    <td>${product.ageRange} years</td>
                                </tr>
                                <tr>
                                    <th>Weight</th>
                                    <td>${product.weight}g</td>
                                </tr>
                            </tbody>

                        </table>
                    </div>
                </div>

                <div class="col-md-6" style="height: 375px;">
                    <h2>Rating 4.5⭐</h2>
                    <button class="btn btn-primary customize">View Feedback</button>

                </div>

                <div class="col-md-6" style="height: 375px;">
                    <h3>Product information</h3>
                    <p style="line-height: 22px;">
                        ${product.description}
                    </p>

                </div>
            </div>

            <div class="row">
                <h3 style="margin-left: 19px; margin-bottom: 20px;">Other Figure Toy</h3>
                <div class="col-lg-3 col-md-4 col-xs-6 suggest-product">
                    <a href="">
                        <div class="rxs" href="">
                            <img src="img/do-choi-xe-rac-mo-hinh-co-den-va-am-thanh-vecto-vt53t-1-638684309368089841.jpg" alt="">
                            <div class="suggest-product_infor">
                                <h5>Name</h5>
                                <p>price</p>
                                <a href="" class="hxe">Add To Cart</a>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-3 col-md-4 col-xs-6 suggest-product">
                    <a href="">
                        <div class="rxs" href="">
                            <img src="img/do-choi-xe-rac-mo-hinh-co-den-va-am-thanh-vecto-vt53t-1-638684309368089841.jpg" alt="">
                            <div class="suggest-product_infor">
                                <h5>Name</h5>
                                <p>price</p>
                                <a href="" class="hxe">Add To Cart</a>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-3 col-md-4 col-xs-6 suggest-product">
                    <a href="">
                        <div class="rxs" href="">
                            <img src="img/do-choi-xe-rac-mo-hinh-co-den-va-am-thanh-vecto-vt53t-1-638684309368089841.jpg" alt="">
                            <div class="suggest-product_infor">
                                <h5>Name</h5>
                                <p>price</p>
                                <a href="" class="hxe">Add To Cart</a>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="col-lg-3 col-md-4 col-xs-6 suggest-product">
                    <a href="">
                        <div class="rxs" href="">
                            <img src="img/do-choi-xe-rac-mo-hinh-co-den-va-am-thanh-vecto-vt53t-1-638684309368089841.jpg" alt="">
                            <div class="suggest-product_infor">
                                <h5>Name</h5>
                                <p>price</p>
                                <a href="" class="hxe">Add To Cart</a>
                            </div>
                        </div>
                    </a>
                </div>          
            </div>
        </div>


                        <c:if test="${not empty isAdded}">
            <div class="toast-container">
                <div class="toast" id="success-toast">
                    Added Product to Cart
                </div>
            </div>
        </c:if>


        <footer class="container-fluid">
            <jsp:include page="Component/Footer.jsp"></jsp:include>
        </footer>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
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


