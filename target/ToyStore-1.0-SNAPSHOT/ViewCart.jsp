<%-- 
    Document   : ViewCart
    Created on : Feb 11, 2025, 8:54:16 AM
    Author     : DUY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <div class="col-md-12 row bag">
               
                <div class="col-md-3 product_img">                   
                    <img src="https://cdn.tgdd.vn/Products/Images/10421/326613/do-choi-vali-keo-dung-dung-cu-ky-thuat-latoys-la5568d-7-thumb-300x300.jpg" alt="">
                    <div class="function_place">
                        <div class="function_add">
                            <i class="fa-solid fa-trash"></i> 
                            <input type="text" value="1" readonly>           
                            <i class="fa-solid fa-plus"></i>
                        </div>                     
                        <div class="wish-list">
                            <i class="fa-regular fa-heart"></i>
                        </div>                                      
                    </div>
                </div>
                <div class="col-md-9 product_detail">
                    <div class="header-productDetail">
                        <p>Airforce 1</p>
                        <p>3,829,000₫</p>
                    </div>
                    <p>Basketball Shoes</p>
                    <p>Coconut Milk/Light Bone/Sail/Baroque Brown</p>
                    <p>Size 38</p>
                </div>   
                <div class="col-md-12">
                    <div class="bottom-line">
                    </div>
                </div>     
            </div>

            <div class="col-md-12 row bag">
                <div class="col-md-3 product_img">                   
                    <img src="https://cdn.tgdd.vn/Products/Images/10421/320298/vali-banh-xe-dung-dung-cu-bac-sy-la5568a-5-181223-110649-300x300.jpg" alt="">
                    <div class="function_place">
                        <div class="function_add">
                            <i class="fa-solid fa-trash"></i> 
                            <input type="text" value="1" readonly>           
                            <i class="fa-solid fa-plus"></i>
                        </div>                     
                        <div class="wish-list">
                            <i class="fa-regular fa-heart"></i>
                        </div>                                      
                    </div>
                </div>
                <div class="col-md-9 product_detail">
                    <div class="header-productDetail">
                        <p>Airforce 1</p>
                        <p>3,829,000₫</p>
                    </div>
                    <p>Basketball Shoes</p>
                    <p>Coconut Milk/Light Bone/Sail/Baroque Brown</p>
                    <p>Size 38</p>
                </div>     
                <div class="col-md-12">
                    <div class="bottom-line">
                    </div>
                </div>     
            </div>


            <div class="col-md-12 row bag">
                <div class="col-md-3 product_img">                   
                    <img src="img/do-choi-xe-trai-cay-winwintoys-5-300x300.jpg" alt="">
                    <div class="function_place">
                        <div class="function_add">
                            <i class="fa-solid fa-trash"></i> 
                            <input type="text" value="1" readonly>           
                            <i class="fa-solid fa-plus"></i>
                        </div>                     
                        <div class="wish-list">
                            <i class="fa-regular fa-heart"></i>
                        </div>                                      
                    </div>
                </div>
                <div class="col-md-9 product_detail">
                    <div class="header-productDetail">
                        <p>Airforce 1</p>
                        <p>3,829,000₫</p>
                    </div>
                    <p>Basketball Shoes</p>
                    <p>Coconut Milk/Light Bone/Sail/Baroque Brown</p>
                    <p>Size 38</p>
                </div>   
                <div class="col-md-12">
                    <div class="bottom-line">
                    </div>
                </div>     
            </div>

            <div class="col-md-12 row bag">
                <div class="col-md-3 product_img">                   
                    <img src="img/AIR+FORCE+107+LV8.png" alt="">
                    <div class="function_place">
                        <div class="function_add">
                            <i class="fa-solid fa-trash"></i> 
                            <input type="text" value="1" readonly>           
                            <i class="fa-solid fa-plus"></i>
                        </div>                     
                        <div class="wish-list">
                            <i class="fa-regular fa-heart"></i>
                        </div>                                      
                    </div>
                </div>
                <div class="col-md-9 product_detail">
                    <div class="header-productDetail">
                        <p>Airforce 1</p>
                        <p>3,829,000₫</p>
                    </div>
                    <p>Basketball Shoes</p>
                    <p>Coconut Milk/Light Bone/Sail/Baroque Brown</p>
                    <p>Size 38</p>
                </div>  
                <div class="col-md-12">
                    <div class="bottom-line">
                    </div>
                </div>      
            </div>
        </div>

        <form class="col-md-4 summary">
            <h3 class="col-md-12 vf">Summary</h3>
            <div class="col-md-12 Summary_detail">
                <div class="Summary_detail-1 gena">
                    <p>Subtotal</i></p>
                    <p>23,775,000₫</p>
                </div>

                <div class="Summary_detail-2 gena">
                    <p>Estimated Delivery & Handling</i></p>
                    <p>Free</p>
                </div>

            </div>

            <div class="col-md-12 Summary_detail">
                <div class="Summary_detail-1 gena">
                    <p>Subtotal</i></p>
                    <p>23,775,000₫</p>
                </div>
            </div>

            <div class="col-md-12 check-out text-center">
                <button class="check-out-button" style="background-color: green;">
                       <p>Checkout</p>
                </button>
            </div>
        </div>
      
       <footer style="margin-top: 80px;" class="container-fluid" id="footer">
         <jsp:include page="Component/Footer.jsp"></jsp:include>
    </footer>
      
      
      
    
      
    </body>
</html>
