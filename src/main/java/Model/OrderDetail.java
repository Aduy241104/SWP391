/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author thaiv
 */

public class OrderDetail {
    private int orderId; // Thêm orderId
    private String username;
    private String productName;
    private double totalAmount;
    private String address;
    private String imageUrl;
    private double price;
    private String categoryName;
    private String email;
    private String phoneNumber;
    private String orderStatus;
    private String description;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(int orderId, String username, String productName, double totalAmount, String address, String imageUrl, double price, String categoryName, String email, String phoneNumber, String orderStatus, String description, int quantity) {
        this.orderId = orderId;
        this.username = username;
        this.productName = productName;
        this.totalAmount = totalAmount;
        this.address = address;
        this.imageUrl = imageUrl;
        this.price = price;
        this.categoryName = categoryName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.orderStatus = orderStatus;
        this.description = description;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

   
}
