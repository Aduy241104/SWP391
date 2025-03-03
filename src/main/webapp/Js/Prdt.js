/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function () {
    let quantityInput = document.getElementById("quantity");
    let decreaseBtn = document.getElementById("decrease");
    let increaseBtn = document.getElementById("increase");
    let addToCartBtn = document.getElementById("addToCartBtn");
    let productID = 123; // ID sản phẩm

    function updateHref() {
        let quantity = quantityInput.value;
        let currentHref = addToCartBtn.href;

        // Tạo URL object để dễ dàng xử lý
        let url = new URL(currentHref, window.location.origin);

        // Cập nhật giá trị quantity, giữ nguyên các tham số khác
        url.searchParams.set("quantity", quantity);

        // Gán lại href mới
        addToCartBtn.href = url.toString();
    }


    increaseBtn.addEventListener("click", function () {
        let quantity = parseInt(quantityInput.value);
        let maxQuantity = parseInt(quantityInput.getAttribute("max")); // Lấy giá trị max
        if (quantity < maxQuantity) {
            quantityInput.value = quantity + 1;
            updateHref();
        }
    });

    decreaseBtn.addEventListener("click", function () {
        let quantity = parseInt(quantityInput.value);
        let minQuantity = parseInt(quantityInput.getAttribute("min")) || 1; // Lấy giá trị min (mặc định là 1)
        if (quantity > minQuantity) {
            quantityInput.value = quantity - 1;
            updateHref();
        }
    });
});
