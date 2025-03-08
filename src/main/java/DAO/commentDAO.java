/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Review;
import Utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DUY
 */
public class commentDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<Review> getReviewByProductIDss(int productID) {
        String query = "SELECT reviewID, productID, prv.userID, rating, reviewText, username, prv.createdAt    \n"
                + "FROM\n"
                + "ProductReviews AS prv\n"
                + "INNER JOIN Users AS usr\n"
                + "ON usr.userID = prv.userID\n"
                + "where productID = ?";

        List<Review> listResult = new ArrayList<>();
        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Review review = new Review(resultSet.getInt("reviewID"),
                        resultSet.getInt("productID"),
                        resultSet.getString("username"),
                        resultSet.getInt("userID"),
                        resultSet.getInt("rating"),
                        resultSet.getString("reviewText"), resultSet.getDate("createdAt"));
                listResult.add(review);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listResult;
    }

    public double getAvergeRating(int productID) {
        String query = "SELECT  ROUND(AVG(CAST(rating AS FLOAT)), 1) AS AverageRating\n"
                + "from ProductReviews\n"
                + "where productID = ?";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public List<Review> filterRating(int productID, int rating) {
        String query = "SELECT reviewID, productID, prv.userID, rating, reviewText, username, prv.createdAt    \n"
                + "FROM\n"
                + "ProductReviews AS prv\n"
                + "INNER JOIN Users AS usr\n"
                + "ON usr.userID = prv.userID\n"
                + "WHERE productID = ? AND rating = ?";

        List<Review> listResult = new ArrayList<>();
        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productID);
            preparedStatement.setInt(2, rating);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Review review = new Review(resultSet.getInt("reviewID"),
                        resultSet.getInt("productID"),
                        resultSet.getString("username"),
                        resultSet.getInt("userID"),
                        resultSet.getInt("rating"),
                        resultSet.getString("reviewText"), resultSet.getDate("createdAt"));
                listResult.add(review);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listResult;
    }

    public boolean deleteReviewByID(int reviewID) {
        String query = "DELETE FROM ProductReviews\n"
                + "WHERE reviewID = ?";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reviewID);
            int rowEffect = preparedStatement.executeUpdate();

            if (rowEffect > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean addReview(Review review) {
        String query = "Insert Into ProductReviews (productID, userID, rating, reviewText)"
                + "values (?,?,?,?)";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, review.getProductID());
            preparedStatement.setInt(2, review.getUserID());
            preparedStatement.setInt(3, review.getRating());
            preparedStatement.setString(4, review.getReviewText());

            int rowEffect = preparedStatement.executeUpdate();
            if (rowEffect > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean checkIsBuy(int productID, int userID) {
        String query = "select orderDetailID \n"
                + "from OrderDetails\n"
                + "where orderID IN (select orderID from Orders where userID = ? and orderStatus = 'delivered') and productID = ?";
        
        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, productID);
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

}
