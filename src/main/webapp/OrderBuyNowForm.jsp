<%-- 
    Document   : OrderForm
    Created on : Feb 28, 2025, 9:53:45 AM
    Author     : DUY
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Form Đặt Hàng</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="font/fontawesome-free-6.5.2-web/css/all.min.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <style>
            body{
                font-family: "Montserrat", sans-serif;
                overflow-x: hidden;
                background-color: rgb(227, 223, 223);

            }

            .custome-input{
                padding-top: 20px;
                padding-bottom: 20px;
                border: 1px solid rgb(237, 17, 100);
                border-radius: 9px;
            }

            .custome-btn {
                background-color: rgb(237, 17, 100);
                color: white;
                border: 2px solid salmon;
            }

            .custome-btn:hover{
                background-color: rgb(24, 111, 0);
                color: white;
            }

            .hus{
                background-color: white;
            }

            h4{
                margin: 20px 0px;
            }

            .layout{
                box-shadow: 10px 10px black;
            }


        </style>
    </head>
    <body >
        <header class="" id="header">
            <jsp:include page="Component/Header.jsp"></jsp:include>
            </header>  

            <div class="layout">
                <div style="width: 48%; border-radius: 9px;" class="container mt-5 hus">

                    <ul style="list-style: none;margin-top: 50px;" class="list-group">

                  
                        <li style="border-bottom: 1px solid rgb(184, 184, 184); display: flex; justify-content: space-between; align-items: center; padding-bottom: 20px;" class="">
                            <img style="width: 20%;" src="${requestScope.product.imageUrl}" alt="">
                            <div style="width: 80%">
                                <h4 style="width: 70%">${requestScope.product.productName}</h4>
                                <div>
                                    <h5 class="text-end text-danger">${requestScope.totalAmount}$</h5>
                                    <span>x${requestScope.quantity}</span>
                                </div>
                            </div>

                        </li>
                   
                </ul>
                <form action="ConfirmBuyNow" method="POST" accept-charset="UTF-8" class="" style="padding: 30px;">
                   
                    <input type="hidden" name="productID" value="${requestScope.product.productID}">
                    <input type="hidden" name="quantity" value="${requestScope.quantity}">
               
                    <input type="hidden" name="totalAmount" value="${requestScope.totalAmount}">
                    <h2 style="text-align: center;" class="mb-3">Customer information</h2>
                    <div class="mb-3">
                        <label class="form-label">Full Name</label>
                        <p>${sessionScope.user.fullName}</p>
                        <label class="form-label">Email</label>
                        <p>${sessionScope.user.email}</p>
                    </div>
                    <div class="mb-4">
                        <label class="form-label">Phone number</label>
                        <input name="phoneNumber" required type="text" class="form-control custome-input" placeholder="Enter Your phonenumber">
                    </div>
                    <div class="mb-3">

                        <div style="display: flex; margin: 20px 0px;">
                            <select name="city" style="margin: 10px;" class="form-control" id="province">
                                <option value="">Chọn Tỉnh / Thành phố</option>
                            </select>

                            <select name="district" style="margin: 10px;" class="form-control" id="district">
                                <option value="">Chọn Quận / Huyện</option>
                            </select>

                            <select name="phuong" style="margin: 10px;" class="form-control" id="ward">
                                <option value="">Chọn Phường / Xã</option>
                            </select>
                        </div>
                        <label class="form-label">House number, street name</label>
                        <textarea required class="form-control" name="Address" id="" placeholder="Enter Your Address"></textarea>
                    </div>
                    <h4>Order Receiving Method</h4>
                    <p class="form-check-label" for="deliveryHome">Home delivery</p> 
                    <h4>Total amount</h4>

                    <h5 class="mb-3">Tax: <strong class="text-danger">38.000đ</strong></h5>
                    <h5 class="mb-3">Total Amount: <strong class="text-danger">${requestScope.totalAmount}</strong></h5>

                    <button type="submit" class="btn w-100 p-3 mt-4 custome-btn">ĐẶT HÀNG</button>
                </form>  
            </div>
            <footer style="margin-top: 80px;" class="container-fluid" id="footer">
                <jsp:include page="Component/Footer.jsp"></jsp:include>
            </footer>
        </div>

        <script>
            async function loadProvinces() {
                let response = await fetch("https://provinces.open-api.vn/api/?depth=3");
                let data = await response.json();

                let provinceSelect = document.getElementById("province");
                let districtSelect = document.getElementById("district");
                let wardSelect = document.getElementById("ward");

                // Thêm danh sách tỉnh/thành phố
                data.forEach(province => {
                    let option = document.createElement("option");
                    option.value = province.name; // Lưu tên vào value
                    option.dataset.code = province.code; // Lưu code vào data-code
                    option.textContent = province.name;
                    provinceSelect.appendChild(option);
                });

                // Khi chọn tỉnh/thành phố, load quận/huyện tương ứng
                provinceSelect.addEventListener("change", function () {
                    let selectedCode = this.options[this.selectedIndex].dataset.code; // Lấy code từ data-code
                    let selectedProvince = data.find(p => p.code == selectedCode);

                    districtSelect.innerHTML = '<option value="" data-code="">Chọn Quận / Huyện</option>';
                    wardSelect.innerHTML = '<option value="" data-code="">Chọn Phường / Xã</option>';

                    if (selectedProvince) {
                        selectedProvince.districts.forEach(district => {
                            let option = document.createElement("option");
                            option.value = district.name; // Lưu tên vào value
                            option.dataset.code = district.code; // Lưu code vào data-code
                            option.textContent = district.name;
                            districtSelect.appendChild(option);
                        });
                    }
                });

                // Khi chọn quận/huyện, load phường/xã tương ứng
                districtSelect.addEventListener("change", function () {
                    let selectedProvinceCode = provinceSelect.options[provinceSelect.selectedIndex].dataset.code;
                    let selectedDistrictCode = this.options[this.selectedIndex].dataset.code;

                    let selectedProvince = data.find(p => p.code == selectedProvinceCode);
                    let selectedDistrict = selectedProvince?.districts.find(d => d.code == selectedDistrictCode);

                    wardSelect.innerHTML = '<option value="" data-code="">Chọn Phường / Xã</option>';

                    if (selectedDistrict && selectedDistrict.wards.length > 0) {
                        selectedDistrict.wards.forEach(ward => {
                            let option = document.createElement("option");
                            option.value = ward.name; // Lưu tên vào value
                            option.dataset.code = ward.code; // Lưu code vào data-code
                            option.textContent = ward.name;
                            wardSelect.appendChild(option);
                        });
                    } else {
                        let option = document.createElement("option");
                        option.value = "Không có dữ liệu";
                        option.dataset.code = "0"; // Đánh dấu không có dữ liệu
                        option.textContent = "Không có dữ liệu";
                        wardSelect.appendChild(option);
                    }
                });
            }

            loadProvinces();
        </script>
    </body>
</html>

