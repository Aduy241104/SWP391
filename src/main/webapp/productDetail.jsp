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

        <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"> -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
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



        </style>
    </head>
    <body>
        <header class="container-fluid" id="header">
            <script src="js/jk.js"></script>
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
                        <button class="btn btn-primary customize">Add to Cart</button>
                        <button class="btn btn-secondary customize">Buy Now</button>
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

        <footer class="container-fluid">
            <div class="row footer-header" style="padding-left: 100px;">
                <div class="col-lg-4 descript">
                    <h4>100% Genuine Products</h4>
                    <p>Over 100 famous brands</p>
                </div>
                <div class="col-lg-4 descript">
                    <h4>100% Genuine Products</h4>
                    <p>Over 100 famous brands</p>
                </div>
                <div class="col-lg-4 descript">
                    <h4>100% Genuine Products</h4>
                    <p>Over 100 famous brands</p>
                </div>
            </div>

            <div class="row footer-last" style="padding-left: 100px;">
                <div class="col-lg-3">
                    <h4>HOTLINE</h4>
                    <p class="urt">Order: 1900.866.874</p>
                    <p class="urt">Complaints: 1900.866.894</p>
                </div>
                <div class="col-lg-3">
                    <h4>STORE SYSTEM</h4>
                    <ul class="urt" style="list-style: none; padding: 0;">
                        <li>62-store system</li>
                        <li>Store regulations</li>
                        <li>Service quality</li>
                        <li>Return & warranty policy</li>
                        <li>VIP Reward Points & Gifts</li>
                        <li>Bulk purchase discounts</li>
                    </ul>
                </div>
                <div class="col-lg-3">
                    <h4>CUSTOMER SUPPORT</h4>
                    <ul class="urt" style="list-style: none; padding: 0;">
                        <li>General transaction terms</li>
                        <li>Online shopping guide</li>
                        <li>Shipping policy</li>
                        <li>Payment policy</li>
                        <li>Account deletion policy</li>
                        <li>Order history</li>
                    </ul>
                </div>
                <div class="col-lg-3">
                    <h4>ABOUT AVAKIDS</h4>
                    <ul class="urt" style="list-style: none; padding: 0;">
                        <li>Company introduction</li>
                        <li>Comment posting regulations</li>
                        <li>Personal data processing policy</li>
                        <li>AVAKids Smile Fund</li>
                        <li>SIM cards & top-up vouchers</li>
                        <li>Installment payment</li>
                        <li>View mobile version</li>
                    </ul>
                </div>
            </div>
        </footer>
    </body>
</html>


