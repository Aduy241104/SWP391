/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.User;
import Utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author NHATHCE181222
 */
public class userDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public userDAO() {
        try {
            connection = new DBContext().getConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUser(String username, String password) {
    String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
    String adminQuery = "SELECT * FROM Admins WHERE userID = ?";
    String staffQuery = "SELECT * FROM Staffs WHERE userID = ?";
    
    try {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            int userId = resultSet.getInt("userID");
            String role = "Customer"; // Mặc định là User
            
            // Kiểm tra trong bảng Admins
            preparedStatement = connection.prepareStatement(adminQuery);
            preparedStatement.setInt(1, userId);
            ResultSet adminResult = preparedStatement.executeQuery();
            if (adminResult.next()) {
                role = "Admin";
            } else {
                // Kiểm tra trong bảng Staffs
                preparedStatement = connection.prepareStatement(staffQuery);
                preparedStatement.setInt(1, userId);
                ResultSet staffResult = preparedStatement.executeQuery();
                if (staffResult.next()) {
                    role = "Staff";
                }
            }
            
            return new User(
                userId,
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("email"),
                resultSet.getString("fullName"),
                resultSet.getDate("createdAt"),
                role // Gán role lấy được
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
}
