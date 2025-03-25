/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import Model.Review;
import Utils.DBContext;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author DUY
 */
public class ReviewDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<Review> getAllProductReviews() {
        String sql = "SELECT p.productID, p.productName, "
                + "COUNT(pr.reviewText) AS totalComments, "
                + "COALESCE(AVG(pr.rating), 0) AS averageRating "
                + "FROM Products p "
                + "LEFT JOIN ProductReviews pr ON p.productID = pr.productID "
                + "GROUP BY p.productID, p.productName";

        List<Review> reviewList = new ArrayList<>();

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int productID = resultSet.getInt("productID");
                String productName = resultSet.getString("productName");
                int totalComments = resultSet.getInt("totalComments");
                double averageRating = resultSet.getDouble("averageRating");

                Review review = new Review(productID, productName, totalComments, averageRating);
                reviewList.add(review);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return reviewList;
    }

    public List<Review> getProductReviewByProductID(int productID) {
        List<Review> reviewList = new ArrayList<>();
        String query = "SELECT * FROM ProductReviews WHERE productID = ?";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int reviewID = resultSet.getInt("reviewID");
                int userID = resultSet.getInt("userID");
                int rating = resultSet.getInt("rating");
                String reviewText = resultSet.getString("reviewText");
                Date createdAt = resultSet.getDate("createdAt");

                Review review = new Review(reviewID, productID, userID, rating, reviewText, createdAt);
                reviewList.add(review);
            }
        } catch (Exception e) {
            System.out.println("Error while fetching product reviews: " + e.getMessage());
        }
        return reviewList;
    }

    public void deleteReviewById(int reviewID, int productID) {
        String query = "DELETE FROM ProductReviews WHERE reviewID = ? AND productID = ?";

        try {
            connection = new DBContext().getConnect(); // Kết nối DB
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reviewID);
            preparedStatement.setInt(2, productID);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Review deleted successfully!");
            } else {
                System.out.println("⚠️ No review found with ID: " + reviewID + " for Product ID: " + productID);
            }
        } catch (Exception e) {
            System.out.println("❌ Error deleting review: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println("❌ Error closing resources: " + e.getMessage());
            }
        }
    }


}
