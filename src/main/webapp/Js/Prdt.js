/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function () {
    let quantityInput = document.getElementById("quantity");
    let decreaseBtn = document.getElementById("decrease");
    let increaseBtn = document.getElementById("increase");
    let addToCartBtn = document.getElementById("addToCartBtn");
    let buyNowBtn = document.getElementById("buyNowBtn");

    function updateHref() {
        let quantity = quantityInput.value;

        // Cập nhật href cho Add To Cart
        let addToCartUrl = new URL(addToCartBtn.href, window.location.origin);
        addToCartUrl.searchParams.set("quantity", quantity);
        addToCartBtn.href = addToCartUrl.toString();

        // Cập nhật href cho Buy Now
        let buyNowUrl = new URL(buyNowBtn.href, window.location.origin);
        buyNowUrl.searchParams.set("quantity", quantity);
        buyNowBtn.href = buyNowUrl.toString();
    }

    increaseBtn.addEventListener("click", function () {
        let quantity = parseInt(quantityInput.value);
        let maxQuantity = parseInt(quantityInput.getAttribute("max"));
        if (quantity < maxQuantity) {
            quantityInput.value = quantity + 1;
            updateHref();
        }
    });

    decreaseBtn.addEventListener("click", function () {
        let quantity = parseInt(quantityInput.value);
        let minQuantity = parseInt(quantityInput.getAttribute("min")) || 1;
        if (quantity > minQuantity) {
            quantityInput.value = quantity - 1;
            updateHref();
        }
    });

    // Cập nhật href ngay khi tải trang
    updateHref();
});
