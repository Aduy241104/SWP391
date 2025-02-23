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
import java.util.ArrayList;
import java.util.List;

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

    public User getUser(String identifier, String password) {
        String query = "SELECT * FROM Users WHERE (username = ? OR email = ?) AND password = ?";
        String adminQuery = "SELECT * FROM Admins WHERE userID = ?";
        String staffQuery = "SELECT * FROM Staffs WHERE userID = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, identifier);
            preparedStatement.setString(2, identifier);
            preparedStatement.setString(3, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("userID");
                String role = "Customer";

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
                        role
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT userID, username, password, email, fullName, createdAt FROM Users";
        String adminQuery = "SELECT * FROM Admins WHERE userID = ?";
        String staffQuery = "SELECT * FROM Staffs WHERE userID = ?";

        try ( PreparedStatement ps = connection.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int userId = rs.getInt("userID");
                String role = "Customer";

                try ( PreparedStatement adminPs = connection.prepareStatement(adminQuery)) {
                    adminPs.setInt(1, userId);
                    try ( ResultSet adminRs = adminPs.executeQuery()) {
                        if (adminRs.next()) {
                            role = "Admin";
                        }
                    }
                }

                // Kiểm tra trong bảng Staffs nếu không phải Admin
                if (role.equals("Customer")) {
                    try ( PreparedStatement staffPs = connection.prepareStatement(staffQuery)) {
                        staffPs.setInt(1, userId);
                        try ( ResultSet staffRs = staffPs.executeQuery()) {
                            if (staffRs.next()) {
                                role = "Staff";
                            }
                        }
                    }
                }

                User user = new User(
                        userId,
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("fullName"),
                        rs.getDate("createdAt"),
                        role
                );
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User getUserById(int userId) {
        String query = "SELECT userID, username, password, email, fullName, createdAt FROM Users WHERE userID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        userId,
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("fullName"),
                        rs.getDate("createdAt"),
                        "Customer"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(int userId, String fullName, String email) {
        String query = "UPDATE Users SET fullName = ?, email = ? WHERE userID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setInt(3, userId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        String checkQuery = "SELECT * FROM Users WHERE userID = ? AND password = ?";
        String updateQuery = "UPDATE Users SET password = ? WHERE userID = ?";

        try {
            // Kiểm tra mật khẩu cũ
            preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, oldPassword);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Cập nhật mật khẩu mới
                preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, newPassword);
                preparedStatement.setInt(2, userId);
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkOldPassword(int userId, String oldPassword) {
        String query = "SELECT COUNT(*) FROM Users WHERE userID = ? AND password = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setString(2, oldPassword); // Nếu mật khẩu lưu dưới dạng hash, cần kiểm tra với phương thức mã hóa
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Trả về true nếu mật khẩu đúng
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        int userId = 1;
        String oldPassword = "Nha0338666928@"; // Thay bằng mật khẩu thực tế trong DB
        userDAO userDAO = new userDAO();
        boolean isValid = userDAO.checkOldPassword(userId, oldPassword);
        if (isValid) {
            System.out.println("Mật khẩu đúng!");
        } else {
            System.out.println("Mật khẩu sai!");
        }
    }

}
