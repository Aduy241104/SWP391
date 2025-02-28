<%-- 
    Document   : AdminproductDetail
    Created on : Feb 13, 2025, 11:52:03 PM
    Author     : lanoc
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
        <link rel="stylesheet" href="css/AdminDashboardStyle.css"/>
        <link rel="stylesheet" href="css/ManageProductForAdminStyles.css"/>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <style>
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


            .product-btn {
                padding: 5px 0px;
                font-size: 15px;
                text-decoration: none;
                text-align: center;
                max-width: 200px;
                width: 100%;
                background-color: #EA83AA;
                color: white;
                font-size: 16px;
                font-weight: bold;
                border-radius: 5px;
                text-decoration: none;
                transition: all 0.3s ease-in-out;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }


            .back-btn {
                grid-row: 2;
                grid-column: 1 / span 2;
                justify-self: center;
            }

            .product-btn:hover {
                background-color: #FCEBF2;
                transform: translateY(-2px);
                box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
                text-decoration: none;
                color: #000;
            }




        </style>
    </head>
    <body>
        <!-- Sidebar -->
        <div class="sidebar">
            <h2><i class="fas fa-cogs"></i> Admin</h2>
            <a href="AdminManagerUser?action=user"><i class="fas fa-users"></i> Manage Users</a>
            <a href="AdminManageStaff?action=staff"><i class="fas fa-users"></i> Manage Staff</a>
            <a href="AdminManagerProducts?action=product" class="active"><i class="fas fa-box"></i> Manage Products</a>
            <a href="AdminManagerProducts?action=order"><i class="fas fa-shopping-cart"></i> Manage Orders</a>
            <a href="AdminManagerProducts?action=managerStock" ><i class="fas fa-warehouse"></i> Manage Stock</a>
            <a href="AdminManagerProducts?action=home"><i class="fas fa-arrow-left"></i> Back to home page</a>
        </div>

        <jsp:include page="Component/ManageForAdmin_Search.jsp"></jsp:include>

            <div style="margin-right: 100px; margin-top: 100px;" class="container main-content">
                <div class="row">
                    <div class="col-md-6" style="height: 500px;">
                        <img src="${product.imageUrl}" alt="Product Image" class="product-image">
                </div>
                <div class="col-md-6">
                    <h2>${product.productName}</h2>
                    <p class="product-price">${product.price}$</p>
                    <p><strong>Promotion:</strong> Enter code <span class="text-danger">VNPAYAVA1</span> for discounts from $0.50 to $1.00</p>
                    <h4>
                    Stock:${product.stock}
                    </h4>
                    <p><div class="status">
                        <div class="status-dot
                             ${product.stock < 7 ? 'low-stock' : (product.stock < 30 ? 'medium-stock' : 'high-stock')}">
                        </div>
                        <span>
                            ${product.stock < 7 ? "Low Stock" : (product.stock < 30 ? "Medium Stock" : "In Stock")}
                        </span>
                    </div></p>
                    <div class="product-buttons">
                        <a href="AdminManagerProducts?action=editProduct&id=${product.productID}" class="btn btn-primary customize btn-sm">
                            <i class="fas fa-edit"></i> Edit
                        </a>
                        <a href="AdminManagerProducts?action=delete&id=${product.productID}" 
                           onclick="return confirm('Are you sure you want to delete this product?')" 
                           class="btn customize btn-sm">
                            <i class="fas fa-trash"></i> Delete
                        </a>
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
            </div>

            <div class="container">
                <div class="row">
                    <div class="col-md-6 action-buttons-add" style="justify-content: center; flex-wrap: wrap; max-width: 500px;">
                        <a href="AdminManagerProducts?action=addProduct" class="product-btn">
                            <i class="fas fa-plus-circle"></i> Add New<br> Product
                        </a>
                        <a href="AdminManagerProducts?action=viewDelete" class="product-btn">
                            <i class="fas fa-trash"></i> View Deleted <br> Products
                        </a>
                        <a href="AdminManagerProducts?action=product" class="product-btn back-btn">
                            <i class="fas fa-arrow-left"></i> Back to <br>Product Page
                        </a>
                    </div>

                    <div class="col-md-6" style="min-height: 375px;">
                        <h3>Product information</h3>
                        <p style="line-height: 22px; overflow-wrap: break-word;">
                            ${product.description}
                        </p>
                    </div>
                </div>
            </div>
        </div>

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
                                   const isAdded = true; // Đây là giá trị giả lập từ server
                                   if (isAdded) {
                                       showToast();
                                   }
                               });
        </script>
    </body>
</html>