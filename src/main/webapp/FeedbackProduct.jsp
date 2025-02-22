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
                box-shadow: 2px 4px 10px #9a9a9a;
            }


            .border-bottom:hover{
                background-color: rgba(243, 109, 154, 0.3);
                cursor: pointer;
            }
            .custome{
                background-color: green;
                margin-top: 24px;
                border-radius: 17px;
                font-weight: 600;
            }

            .filter-rating{
                background-color: rgba(252, 215, 227, 0.3);
                border: 1px solid rgb(244, 161, 189);
                margin-top: 50px;
                padding: 30px;
            }

            .option-filter a{
                padding: 5px 25px;
                background-color: rgb(0, 221, 221);
                display: inline-block;
                margin-top: 18px;
                margin-left: 10px;
                border-radius: 11px;
                color: rgb(0, 0, 0);
            }

            .option-filter a:hover{
                background-color: black;
                color: white;
            }

            .avt {
                padding: 5px 13px;
                background-color: pink;
                border-radius: 140px;
                display: inline-block;
            }
        </style>
    </head>
    <body>
        <header class="" id="header">
            <jsp:include page="Component/Header.jsp"></jsp:include>
            </header>


            <div style="margin-top: 40px;" class="container">
                <h2 class="mb-4">Comments and Reviews Of Product #12345</h2>

                <!-- Comment Form -->
            <c:if test="${not empty requestScope.user}">
                <form class="card p-3 mb-4">
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
            </c:if>

            <!-- Filter Rating Section -->
            <div class="row filter-rating">
                <div class="col-lg-3">
                    <h3>Rating: 4 On 5</h3>
                    <h5>⭐⭐⭐⭐</h5>
                </div>
                <div class="col-lg-7 option-filter">
                    <a href="">All</a>
                    <a href="">5 stars</a>
                    <a href="">4 stars</a>
                    <a href="">3 stars</a>
                    <a href="">2 stars</a>
                    <a href="">1 star</a>
                </div>
            </div>


            <!-- Comment List -->
            <div class="card">
                <h3 class="mb-3">Recent Comments</h3>

                <c:forEach var="r" items="${requestScope.listReview}">
                    <div class="border-bottom mb-2">
                        <strong><i class="avt">j</i> ${r.username}</strong> 
                        <div style="display: flex; justify-content: space-between;">
                            
                             <span class="text-warning"> <c:forEach begin="1" end="${r.rating}" var="i">
                               ⭐
                            </c:forEach></span>
                          

                            <span style="font-weight: 600;">${r.createdAt}</span>
                        </div>
                        <p style="margin-top: 16px;">${r.reviewText}</p>
                    </div>
                </c:forEach>
                <div class="border-bottom mb-2">
                    <strong><i class="avt">j</i> John Doe</strong> 
                    <div style="display: flex; justify-content: space-between;">
                        <span class="text-warning">⭐⭐</span>
                        <span style="font-weight: 600;">22/11/2024</span>
                    </div>
                    <p style="margin-top: 16px;">Good quality, worth the money.</p>
                </div>
            </div>
        </div>
        <footer style="margin-top: 80px;" class="container-fluid" id="footer">
            <jsp:include page="Component/Footer.jsp"></jsp:include>
        </footer>


    </body>
</html>
