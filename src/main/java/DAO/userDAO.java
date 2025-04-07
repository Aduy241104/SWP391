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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public boolean checkExistAccount(String identifier) {
        String query = "SELECT COUNT(*) FROM Users WHERE (username = ? OR email = ?) AND isActive = 1";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, identifier);
            ps.setString(2, identifier);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Trả về true nếu tài khoản tồn tại và đang hoạt động
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT userID, username, password, email, fullName, createdAt, isActive FROM Users";
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
                        role,
                        rs.getBoolean("isActive")
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

    // thaiv
    public User getUserByIDHaveActive(int userId) {
        User user = null;
        String query = "SELECT userID, username, password, email, fullName, createdAt, isActive FROM Users WHERE userID = ?";
        String adminQuery = "SELECT * FROM Admins WHERE userID = ?";
        String staffQuery = "SELECT * FROM Staffs WHERE userID = ?";

        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String role = "Customer";

                    // Kiểm tra nếu user này là Admin
                    try ( PreparedStatement adminPs = connection.prepareStatement(adminQuery)) {
                        adminPs.setInt(1, userId);
                        try ( ResultSet adminRs = adminPs.executeQuery()) {
                            if (adminRs.next()) {
                                role = "Admin";
                            }
                        }
                    }

                    // Nếu không phải Admin, kiểm tra tiếp xem có phải Staff không
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

                    // Tạo đối tượng User từ dữ liệu lấy được
                    user = new User(
                            rs.getInt("userID"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("fullName"),
                            rs.getDate("createdAt"),
                            role,
                            rs.getBoolean("isActive") // Lấy trạng thái isActive từ DB
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    // thaiv

    public boolean checkExistUsername(String username) {
        String query = "SELECT COUNT(*) FROM Users WHERE username = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

// thaiv
    public boolean checkExistEmail(String email) {
        String query = "SELECT COUNT(*) FROM Users WHERE email = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //thaiv
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

    // thaiv
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

    // Kiểm tra username đã tồn tại chưa
    public boolean isUsernameExists(String username) throws SQLException {
        String query = "SELECT COUNT(*) FROM Users WHERE username = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu username đã tồn tại
            }
        }
        return false;
    }

    // thaiv
    public boolean banUser(int userId) {
        String query = "UPDATE Users SET isActive = 0 WHERE userID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra email đã tồn tại chưa
    public boolean isEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM Users WHERE email = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu email đã tồn tại
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Đăng ký tài khoản
    public boolean signUpUser(String username, String password, String confirmPassword, String email, String fullName) throws SQLException {
        if (!password.equals(confirmPassword)) {
            System.out.println("Mật khẩu nhập lại không khớp.");
            return false;
        }

        if (isUsernameExists(username)) {
            System.out.println("Tên đăng nhập đã tồn tại.");
            return false;
        }

        if (isEmailExists(email)) {
            System.out.println("Email đã được sử dụng.");
            return false;
        }

        String insertQuery = "INSERT INTO Users (username, password, email, fullName, createdAt) VALUES (?, ?, ?, ?, GETDATE())";
        try ( PreparedStatement ps = connection.prepareStatement(insertQuery)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, fullName);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean unBanUser(int userId) {
        String query = "UPDATE Users SET isActive = 1 WHERE userID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // thaiv
    public boolean isUserActive(int userId) {
        String query = "SELECT isActive FROM Users WHERE userID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("isActive");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //thaiv
    public List<User> getBannedUsers() {
        List<User> bannedUsers = new ArrayList<>();
        String query = "SELECT userID, username, password, email, fullName, createdAt FROM Users WHERE isActive = 0";

        try ( PreparedStatement ps = connection.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("userID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("fullName"),
                        rs.getDate("createdAt"),
                        "Customer",
                        false // isActive = false
                );
                bannedUsers.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bannedUsers;
    }

    // thaiv
    public List<User> searchUser(String keyword) {
        List<User> userList = new ArrayList<>();
        String query = "SELECT userID, username, password, email, fullName, createdAt, isActive FROM Users "
                + "WHERE (username LIKE ? OR email LIKE ? OR fullName LIKE ?) "; // Chỉ tìm kiếm người dùng đang active (có thể thay đổi nếu muốn bao gồm cả banned)
        String adminQuery = "SELECT * FROM Admins WHERE userID = ?";
        String staffQuery = "SELECT * FROM Staffs WHERE userID = ?";

        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int userId = rs.getInt("userID");
                    String role = "Customer";

                    // Kiểm tra nếu user này là Admin
                    try ( PreparedStatement adminPs = connection.prepareStatement(adminQuery)) {
                        adminPs.setInt(1, userId);
                        try ( ResultSet adminRs = adminPs.executeQuery()) {
                            if (adminRs.next()) {
                                role = "Admin";
                            }
                        }
                    }

                    // Nếu không phải Admin, kiểm tra tiếp xem có phải Staff không
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
                            rs.getInt("userID"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("fullName"),
                            rs.getDate("createdAt"),
                            role,
                            rs.getBoolean("isActive")
                    );
                    userList.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * Thaiv
     */
    public boolean addUser(String username, String password, String email, String fullName, String role, boolean isActive) {
        String insertUserQuery = "INSERT INTO Users (username, password, email, fullName, createdAt, isActive) VALUES (?, ?, ?, ?, GETDATE(), ?)";
        String insertAdminQuery = "INSERT INTO Admins (userID) VALUES (?)";
        String insertStaffQuery = "INSERT INTO Staffs (userID) VALUES (?)";
        String checkExistQuery = "SELECT COUNT(*) FROM Users WHERE username = ? OR email = ?";

        try {
            preparedStatement = connection.prepareStatement(checkExistQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return false;
            }

            preparedStatement = connection.prepareStatement(insertUserQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, fullName);
            preparedStatement.setBoolean(5, isActive);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                return false; // Thêm user thất bại
            }

            resultSet = preparedStatement.getGeneratedKeys();
            int userId = -1;
            if (resultSet.next()) {
                userId = resultSet.getInt(1);
            } else {
                return false;
            }

            if ("Admin".equalsIgnoreCase(role)) {
                preparedStatement = connection.prepareStatement(insertAdminQuery);
                preparedStatement.setInt(1, userId);
                preparedStatement.executeUpdate();
            } else if ("Staff".equalsIgnoreCase(role)) {
                preparedStatement = connection.prepareStatement(insertStaffQuery);
                preparedStatement.setInt(1, userId);
                preparedStatement.executeUpdate();
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<User> getUserByEmail(String email) {
        String sql = "SELECT userID, fullName, email, createdAt FROM Users WHERE email = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                            rs.getInt("userID"),
                            null, // Username không có trong truy vấn này
                            null, // Password không có trong truy vấn này
                            rs.getString("email"),
                            rs.getString("fullName"),
                            rs.getDate("createdAt"),
                            "Customer" // Mặc định là Customer
                    );

                    System.out.println("📌 Found user: " + user.getFullName() + " (ID: " + user.getUserId() + ")");

                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean updateUserPassword(int userId, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE userID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newPassword); // Mật khẩu chưa mã hóa (mã hóa sau)
            ps.setInt(2, userId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        userDAO userDao = new userDAO();

        String keyword = "john";
        List<User> searchResults = userDao.searchUser(keyword);

        if (searchResults != null && !searchResults.isEmpty()) {
            System.out.println("Search results for keyword: " + keyword);
            for (User user : searchResults) {
                System.out.println("User ID: " + user.getUserId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Full Name: " + user.getFullName());
                System.out.println("Role: " + user.getRole());
                System.out.println("Is Active: " + user.isIsActive());
                System.out.println("-------------------");
            }
        } else {
            System.out.println("No users found for keyword: " + keyword);
        }
    }
}
