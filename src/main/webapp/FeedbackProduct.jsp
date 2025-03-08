<%-- 
    Document   : FeedbackProduct
    Created on : Feb 22, 2025, 9:32:19 AM
    Author     : DUY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Comment & Review</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/PrDt.css">
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

            strong{
                color: rgb(46, 145, 245);
                margin-bottom: 10px;
            }

            .border-bottom{
                padding: 20px 15px;
                margin-bottom: 8px;
                background-color: rgb(255, 255, 255);
                border-bottom: 1px solid grey;
                /* box-shadow: 2px 4px 10px #9a9a9a; */
            }

            .layout-3{
                background-color: rgb(245, 244, 245);
                padding: 4px 15px;
                margin-top: 10px;
            }


            .border-bottom:hover{
                /* background-color: rgba(243, 109, 154, 0.3); */
                cursor: pointer;
            }
            .custome{
                background-color: green;
                margin-top: 24px;
                border-radius: 17px;
                font-weight: 600;
            }

            .filter-rating{
                background-color: rgba(246, 205, 218, 0.3);
                border: 1px solid rgb(244, 161, 189);
                margin-top: 50px;
                padding: 30px;
            }

            .option-filter a{
                padding: 5px 25px;
                background-color: rgb(255, 255, 255);
                display: inline-block;
                margin-top: 18px;
                margin-left: 10px;
                border-radius: 5px;
                color: rgb(0, 0, 0);
                border: 1px solid black;
            }

            .option-filter a:hover{
                background-color: pink;
                color: rgb(0, 0, 0);
                text-decoration: none;
            }

            .avt {
                padding: 5px 10px;
                border: 1px solid rgb(51, 123, 248);
                background-color: rgb(217, 216, 216);
                border-radius: 140px;
                display: inline-block;
            }

            .layout-4 {
                background-color: rgb(245, 243, 243);
                padding: 30px;
                border-radius: 9px;
                width: 50%;
                border: 1px solid salmon;
            }

            .fa-star{
                color: rgb(214, 197, 6);
            }

            .layout-5{
                background-color: rgba(252, 163, 191, 0.3);
                border-radius: 7px;
            }

            .menu-comment{
                position: relative;

            }
            .fa-ellipsis{
                position: absolute;
                right: 7px;
            }

            .menu-place{
                width: 120px;
                height: 70px;
                background-color: pink;
                position: absolute;
                right: 0;
                display: flex;
                flex-direction: column;
                padding-left: 10px;
                padding-top: 5px;
                top: 19px;
                display: none;
            }

            .menu-place i {
                font-size: 10px;
            }

            .menu-place::before{
                width: 10px;
                height: 40px;
                content: "";
                top: -32px;
                right: 0;
                position: absolute;
                border: 15px solid;
                border-color: transparent transparent pink transparent;


            }

            .mnx:hover .menu-place{
                display: block;
            }

            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
            }

            .modal-header {
                font-size: 18px;
                font-weight: bold;
            }

            /* Footer modal */
            .modal-footer {
                margin-top: 15px;
                display: flex;
                justify-content: space-between;
            }

            .modals-content {
                background-color: white;
                margin: 15% auto;
                padding: 20px;
                border-radius: 10px;
                width: 300px;
                text-align: center;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }

            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
            }

            .modal-header {
                font-size: 18px;
                font-weight: bold;
            }

            .modal-footer {
                margin-top: 15px;
                display: flex;
                justify-content: space-between;
            }
            
            .actives{
                background-color: pink;
            }
        </style>
    </head>
    <body>
        <header class="" id="header">
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
                    <a class="btn btn-primary customize" href="ViewFeedback?productID=${product.productID}">Feedback</a>

                </div>

                <div class="col-md-6" style="height: 375px;">
                    <h3>Product information</h3>
                    <p style="line-height: 22px;">
                        ${product.description}
                    </p>

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
                                    <a href="" class="hxe">Add To Cart</a>
                                </div>
                            </div>
                        </a>
                    </div>          
                </c:forEach>
            </div>
        </div>

        <div style="margin-top: 40px;" class="container" id="reviews">
            <h2 class="mb-4">Reviews Of Product #${requestScope.productID}</h2>

            <!-- Comment Form -->
            <c:if test="${not empty sessionScope.user and not empty requestScope.isBuy}">
                <form action="AddReview" method="POST" class="card layout-4 p-3 mb-4">
                    <h4>Write Your Comment</h4>
                    <input type="hidden" name="productID" class="form-control"value="${requestScope.productID}">
                    <div style="margin-top: 15px;" class="mb-3">
                        <label class="form-label">Rating</label>
                        <select name="rating" class="form-select">
                            <option value="5">⭐⭐⭐⭐⭐ - Excellent</option>
                            <option value="4">⭐⭐⭐⭐ - Good</option>
                            <option value="3">⭐⭐⭐ - Average</option>
                            <option value="2">⭐⭐ - Poor</option>
                            <option value="1">⭐ - Very Bad</option>
                        </select>
                    </div>
                    <div style="margin-top: 20px;" class="mb-3">
                        <label class="form-label">Comment</label>
                        <textarea name="reviewText" class="form-control" rows="3" placeholder="Enter your comment"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary custome">Submit Comment</button>
                </form>
            </c:if>

            <!-- Filter Rating Section -->
            <div class="row filter-rating">
                <div class="col-lg-3">
                    <h3>Rating: ${requestScope.avgRating} <i class="fa-solid fa-star"></i></h3>
                </div>

                <c:set var="rating" value="${requestScope.rating}" />
                <c:if test="${empty rating}">
                    <c:set var="rating" value="all" />
                </c:if>
                <div class="col-lg-7 option-filter">
                    <a href="FilterRating?productID=${requestScope.productID}&rating=all" class="filter-link ${rating == 'all' ? 'actives' : ''}" data-rating="all">All</a>
                    <a href="FilterRating?productID=${requestScope.productID}&rating=5" class="filter-link ${rating == '5' ? 'actives' : ''}" data-rating="5">5 stars</a>
                    <a href="FilterRating?productID=${requestScope.productID}&rating=4" class="filter-link ${rating == '4' ? 'actives' : ''}" data-rating="4">4 stars</a>
                    <a href="FilterRating?productID=${requestScope.productID}&rating=3" class="filter-link ${rating == '3' ? 'actives' : ''}" data-rating="3">3 stars</a>
                    <a href="FilterRating?productID=${requestScope.productID}&rating=2" class="filter-link ${rating == '2' ? 'actives' : ''}" data-rating="2">2 stars</a>
                    <a href="FilterRating?productID=${requestScope.productID}&rating=1" class="filter-link ${rating == '1' ? 'actives' : ''}" data-rating="1">1 star</a>
                </div>
            </div>

            <!-- Comment List -->
            <div class="card layout-3">
                <h3 class="mb-3">Comments (${requestScope.listReview.size()})</h3>
                <c:forEach var="r" items="${requestScope.listReview}">
                    <div class="border-bottom mb-2">
                        <div class="menu-comment">
                            <strong><i style="margin-right: 9px;" class="avt">D</i> 

                                <c:if test="${sessionScope.user.userId == r.userID}">
                                    You
                                </c:if>
                                <c:if test="${sessionScope.user.userId != r.userID}">
                                    ${r.username}
                                </c:if>
                            </strong> 
                            <c:if test="${sessionScope.user.userId == r.userID}">
                                <i class="fa-solid fa-ellipsis"></i>
                                <div class="mnx">
                                    <div style="width: 60px; height: 40px; right: 0; position: absolute; top: 0;">

                                    </div>
                                    <div class="menu-place">
                                        <section>
                                            <button value="${r.reviewID}"  id="openModal" style="border: none; background-color: pink;">Delete</button>
                                            <i class="fa-solid fa-trash-can"></i>
                                        </section>
                                        <section>
                                            <button style="border: none; background-color: pink;">Edit</button>
                                            <i class="fa-solid fa-pen"></i>
                                        </section> 
                                    </div>
                                </div>
                            </c:if>

                        </div>
                        <div style="display: flex; flex-direction: column; margin-left: 43px;">
                            <span style="font-size: 11px;">${r.createdAt}</span>
                            <span style="margin: 3px 0px;" class="text-warning"> <c:forEach begin="1" end="${r.rating}" var="i">
                                    <i class="fa-solid fa-star"></i>
                                </c:forEach>
                            </span>
                            <p style="margin-top: 16px;">${r.reviewText}</p>
                        </div>

                    </div>
                </c:forEach>
            </div>
        </div>

        <div id="confirmModal" class="modal">
            <div class="modals-content">
                <div class="modal-header">Confirm Action</div>
                <div id="ulck" class="modal-body">Are you sure you want to Delete this review?</div>
                <form action="DeleteReview" method="POST"  class="modal-footer">
                    <input type="hidden" id="id" name="productID" value="${requestScope.productID}">
                    <input type="hidden" id="inputHidden" name="reviewID">
                    <button class="btn btn-secondary" id="cancelBtn">No</button>
                    <button type="submit" class="btn btn-danger" id="confirmDelete">Yes, Delete</button>
                </form>
            </div>
        </div>

        <footer class="container-fluid">
            <jsp:include page="Component/Footer.jsp"></jsp:include>
        </footer>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Khôi phục vị trí cuộn từ localStorage (nếu có)
                if (localStorage.getItem("scrollPosition")) {
                    window.scrollTo(0, localStorage.getItem("scrollPosition"));
                }

                // Bắt sự kiện click vào các link filter
                document.querySelectorAll(".filter-link").forEach(link => {
                    link.addEventListener("click", function (e) {
                        e.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ a

                        // Lưu vị trí cuộn trước khi tải trang mới
                        localStorage.setItem("scrollPosition", window.scrollY);

                        // Thay đổi URL mà không bị reload lên đầu trang
                        window.location.href = this.href;
                    });
                });

// Sau khi trang tải xong, xóa vị trí cuộn đã lưu
                window.addEventListener("load", function () {
                    localStorage.removeItem("scrollPosition");
                });
            });

            document.addEventListener("DOMContentLoaded", function () {
                let element = document.getElementById("reviews"); // Thay "reviews" bằng ID của phần tử bạn muốn cuộn đến
                if (element) {
                    element.scrollIntoView({behavior: "smooth"}); // Cuộn mượt xuống phần tử
                }
            });

        </script>

        <script>


            const modal = document.getElementById("confirmModal");
            const cancelBtn = document.getElementById("cancelBtn");
            const confirmBtn = document.getElementById("confirmDelete");
            const kj = document.getElementById("ulck");
            const inputHid = document.getElementById("inputHidden");

// Lấy tất cả các nút "Delete"
            const openModalBtns = document.querySelectorAll("#openModal");

// Gán sự kiện click cho từng nút "Delete"
            openModalBtns.forEach((btn) => {
                btn.addEventListener("click", function () {
                    modal.style.display = "block";
                    inputHid.value = this.value;
                });
            });

// Đóng modal khi nhấn Hủy
            cancelBtn.addEventListener("click", function () {
                modal.style.display = "none";

            });

// Xác nhận xóa
            confirmBtn.addEventListener("click", function () {
                modal.style.display = "none";
            });

// Đóng modal khi click ra ngoài
            window.addEventListener("click", function (event) {
                if (event.target === modal) {
                    modal.style.display = "none";
                }
            });

        </script>
    </body>
</html>
