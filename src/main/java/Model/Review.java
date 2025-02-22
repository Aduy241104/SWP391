/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author DUY
 */
public class Review {
      private int reviewID;
    private int productID;
    private String username;  // 🔹 Đổi từ `userName` sang `username`
    private int userID;
    private int rating;
    private String reviewText;  // 🔹 Đổi từ `comment` sang `reviewText`
    private Date createdAt;

    public Review() {
    }

    public Review(int reviewID, int productID, String username, int userID, int rating, String reviewText, Date createdAt) {
        this.reviewID = reviewID;
        this.productID = productID;
        this.username = username;
        this.userID = userID;
        this.rating = rating;
        this.reviewText = reviewText;
        this.createdAt = createdAt;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    
}
