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
                color: rgb(6, 121, 15);
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
                padding: 5px 13px;
                background-color: rgb(217, 216, 216);
                border-radius: 140px;
                display: inline-block;
            }

            .layout-4 {
                background-color: rgb(240, 239, 239);
                padding: 30px;
                border-radius: 9px;
            }

            .fa-star{
                color: rgb(214, 197, 6);
            }
            
            .actives{
                background-color: pink !important;
            }
        </style>
    </head>
    <body>
        <header class="" id="header">
            <jsp:include page="Component/Header.jsp"></jsp:include>
            </header>


            <div style="margin-top: 40px;" class="container">
                <h2 class="mb-4">Reviews Of Product #${requestScope.productID}</h2>

            <!-- Comment Form -->
            <form class="card layout-4 p-3 mb-4">
                <h4>Write Your Comment</h4>
                <input type="hidden" class="form-control" placeholder="Enter your name">
                <div style="margin-top: 15px;" class="mb-3">
                    <label class="form-label">Rating</label>
                    <select class="form-select">
                        <option value="5">⭐⭐⭐⭐⭐ - Excellent</option>
                        <option value="4">⭐⭐⭐⭐ - Good</option>
                        <option value="3">⭐⭐⭐ - Average</option>
                        <option value="2">⭐⭐ - Poor</option>
                        <option value="1">⭐ - Very Bad</option>
                    </select>
                </div>
                <div style="margin-top: 20px;" class="mb-3">
                    <label class="form-label">Comment</label>
                    <textarea class="form-control" rows="3" placeholder="Enter your comment"></textarea>
                </div>
                <button class="btn btn-primary custome">Submit Comment</button>
            </form>

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
                    <a href="FilterRating?productID=${requestScope.productID}&rating=all" class="${rating == 'all' ? 'actives' : ''}">All</a>
                    <a href="FilterRating?productID=${requestScope.productID}&rating=5" class="${rating == '5' ? 'actives' : ''}">5 stars</a>
                    <a href="FilterRating?productID=${requestScope.productID}&rating=4" class="${rating == '4' ? 'actives' : ''}">4 stars</a>
                    <a href="FilterRating?productID=${requestScope.productID}&rating=3" class="${rating == '3' ? 'actives' : ''}">3 stars</a>
                    <a href="FilterRating?productID=${requestScope.productID}&rating=2" class="${rating == '2' ? 'actives' : ''}">2 stars</a>
                    <a href="FilterRating?productID=${requestScope.productID}&rating=1" class="${rating == '1' ? 'actives' : ''}">1 star</a>
                </div>
            </div>


            <!-- Comment List -->
            <div class="card layout-3">
                <h3 class="mb-3">Comments (${requestScope.listReview.size()})</h3>
                <c:forEach var="r" items="${requestScope.listReview}">
                    <div class="border-bottom mb-2">
                        <strong><i style="margin-right: 9px;" class="avt">j</i> 
                            <c:if test="${sessionScope.user.userId == r.userID}">
                               You
                            </c:if>
                            <c:if test="${sessionScope.user.userId != r.userID}">
                               ${r.username}
                            </c:if>
                        </strong> 
                        <div style="display: flex; flex-direction: column; margin-left: 35px;">
                            <span style="margin: 3px 7px;" class="text-warning"> <c:forEach begin="1" end="${r.rating}" var="i">
                                    <i class="fa-solid fa-star"></i>

                                </c:forEach>
                            </span>

                            <span style="margin:0px 6px; font-size: 12px;">${r.createdAt}</span>
                        </div>
                        <p style="margin-top: 16px; margin-left: 37px;">${r.reviewText}</p>
                    </div>
                </c:forEach>


            </div>
        </div>
    </body>
</html>
