/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author thaiv
 */
public class Orders {

    private int orderId;
    private int userId;
    private double totalAmount;
    private Date createdAt;
    private String address;
    private String phoneNumber;
    private String orderStatus;

    public Orders(int orderId, int userId, double totalAmount, Date createdAt, String address, String phoneNumber, String orderStatus) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.orderStatus = orderStatus;
    }

    public Orders(int userId, double totalAmount, String address, String phoneNumber) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    

    public Orders() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

}
