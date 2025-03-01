<%-- 
    Document   : orderdetail
    Created on : Feb 24, 2025, 10:45:01 AM
    Author     : Nguyen Phu Quy CE180789
--%>

<%@page import="DAO.orderDAO"%>
<%@page import="Model.Orders" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order Detail</title>
        <link rel="stylesheet" href="OrderDetail.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="font/fontawesome-free-6.5.2-web/css/all.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">


        <style>

            body {
                font-family: "Montserrat", sans-serif;
                overflow-x: hidden;
            }

            /* Nút mở modal */
            .btn {
                padding: 10px 20px;
                font-size: 16px;
                border: none;
                cursor: pointer;
                border-radius: 5px;
            }

            .btn-danger {
                background-color: rgb(201, 23, 23);
                color: white;
            }

            .btn-secondary {
                background-color: gray;
                color: white;
            }

            /* Modal container */
            .modal {
                display: none;
                position: fixed;
                z-index: 1000;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                display: flex;
                align-items: center;
                justify-content: center;
            }

            /* Modal content */
            .modal-content {
                background-color: white;
                width: 400px;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);
                text-align: center;
                animation: fadeIn 0.3s ease-in-out;
            }

            /* Header */
            .modal-header {
                font-size: 20px;
                font-weight: bold;
                margin-bottom: 10px;
            }

            /* Body */
            .modal-body {
                font-size: 16px;
                padding: 10px 0;
            }

            /* Footer buttons */
            .modal-footer {
                margin-top: 15px;
                display: flex;
                justify-content: center;
                gap: 15px;
            }

            /* Nút */
            .btn {
                padding: 10px 15px;
                border-radius: 5px;
                font-size: 14px;
                font-weight: bold;
                cursor: pointer;
                transition: all 0.3s ease-in-out;
            }

            .btn-secondary {
                background-color: gray;
                color: white;
            }

            .btn-danger {
                background-color: rgb(201, 23, 23);
                color: white;
            }

            /* Hiệu ứng */
            @keyframes fadeIn {
                from {
                    opacity: 0;
                    transform: translateY(-20px);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }


        </style>
    </head>
    <body style="width: 100%;">

        <header class="" id="header">
            <jsp:include page="Component/Header.jsp"></jsp:include>
            </header>
            <div class="container my-5">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h4>Order #${order.orderId}</h4>
                <div>
                    <span class="badge bg-info text-dark badge-status">${order.orderStatus}</span>
                </div>

            </div>
            <p>${formattedDate}</p>

            <div class="row">
                <!-- Order Details -->
                <div class="col-md-8">
                    <div class="card card-custom">
                        <div class="d-flex justify-content-between">
                            <h5>Order Details</h5>
                        </div>
                        <table class="table mt-3">
                            <thead>
                                <tr>    
                                    <th>Image</th>
                                    <th>Products</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>TotalAmount</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="detail" items="${OrderDetails}">
                                    <tr>
                                        <td><img style="width: 50%" src="${detail.imageUrl}" alt="${detail.productName}" class="product-image"></td>
                                        <td class="product-name">
                                            ${detail.productName} <br>
<!--                                            <small>${detail.description}</small>-->
                                        </td>
                                        <td>$${detail.price}</td>
                                        <td>${detail.quantity}</td>
                                        <td>$${detail.price * detail.quantity}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <div class="text-end total-section">
                            <p><strong>Total: <span>$${order.totalAmount}</span></strong></p>                        
                        </div>
                    </div>
                </div>

                <!-- Customer Details -->
                <div class="col-md-4">
                    <div class="card card-custom mb-3">
                        <div class="d-flex justify-content-between">
                            <h5>Customer Details</h5>
                            <a style="color: palevioletred; font-weight: 600;" href="#">Edit</a>
                        </div>
                        <p class="mt-3 mb-1">${user.fullName}</p>
                        <p>Customer ID: #${user.userId}</p>
                        <p><i class="bi bi-cart"></i></p>
                        <p>Contact Info:</p>
                        <p>Email: ${user.email}</p>
                        <p>Mobile: ${order.phoneNumber}</p>
                    </div>
                    <div class="card card-custom">
                        <div class="d-flex justify-content-between">
                            <h5>Shipping Address</h5>
                            <a style="color: palevioletred; font-weight: 600;" href="#">Edit</a>
                        </div>
                        <p class="mt-3"> ${order.address}</p>
                    </div>
                    <button class="btn btn-danger mt-3" id="openModal">Cancel Order</button>
                </div>
            </div>
        </div>


        <!-- Modal -->
        <div id="confirmModal" class="modal">
            <div class="modal-content">
                <div class="modal-header">Confirm Action</div>
                <div class="modal-body">Are you sure you want to cancel this order? 😿</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" id="cancelBtn">No</button>
                    <button class="btn btn-danger" id="confirmDelete"><a style="color: white" href="CancelOrderController?action=cancelOrder&id=${order.orderId}">Yes, Cancel</a></button>
                </div>
            </div>
        </div>



        <script>
            const modal = document.getElementById("confirmModal");
            const openModalBtn = document.getElementById("openModal");
            const cancelBtn = document.getElementById("cancelBtn");
            const confirmBtn = document.getElementById("confirmDelete");
            
             modal.style.display = "none";

            // Mở modal
            openModalBtn.addEventListener("click", () => {
                modal.style.display = "flex";
            });

            // Đóng modal khi nhấn Hủy
            cancelBtn.addEventListener("click", () => {
                modal.style.display = "none";
            });

            // Xử lý xác nhận
            confirmBtn.addEventListener("click", () => {
                modal.style.display = "none";
            });

            // Đóng modal nếu nhấn ra ngoài
            window.addEventListener("click", (event) => {
                if (event.target === modal) {
                    modal.style.display = "none";
                }
            });

        </script>






    </body>
</html>
