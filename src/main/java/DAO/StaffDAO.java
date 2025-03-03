/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Staff;
import Utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NHATHCE181222
 */
public class StaffDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public StaffDAO() {
        try {
            connection = new DBContext().getConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Staff> getAllStaffs() {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT s.staffID, u.userID, u.username, u.email, u.fullName, u.createdAt, u.isActive "
                + "FROM Staffs s JOIN Users u ON s.userID = u.userID";
        try ( PreparedStatement ps = connection.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                staffList.add(new Staff(
                        rs.getInt("staffID"),
                        String.valueOf(rs.getInt("userID")),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("fullName"),
                        rs.getDate("createdAt"),
                        "Staff",
                        rs.getBoolean("isActive")
                ));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staffList;
    }

    public boolean addStaff(String username, String email, String fullName, String password, boolean isActive) {
        String insertUserQuery = "INSERT INTO Users (username, email, fullName, password, isActive) VALUES (?, ?, ?, ?, ?)";
        String insertStaffQuery = "INSERT INTO Staffs (userID) VALUES (?)";

        try ( PreparedStatement psUser = connection.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS)) {
            psUser.setString(1, username);
            psUser.setString(2, email);
            psUser.setString(3, fullName);
            psUser.setString(4, password);
            psUser.setBoolean(5, isActive);
            psUser.executeUpdate();

            // Lấy userID vừa tạo
            ResultSet generatedKeys = psUser.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userID = generatedKeys.getInt(1);

                // Thêm vào bảng Staffs
                try ( PreparedStatement psStaff = connection.prepareStatement(insertStaffQuery)) {
                    psStaff.setInt(1, userID);
                    psStaff.executeUpdate();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkExistUsername(String username) {
        String query = "SELECT COUNT(*) FROM Users WHERE username = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkExistEmail(String email) {
        String query = "SELECT COUNT(*) FROM Users WHERE email = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean banStaff(int staffId) {
        String query = "UPDATE Users SET isActive = 0 WHERE userID = (SELECT userID FROM Staffs WHERE staffID = ?)";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, staffId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean unBanStaff(int staffId) {
        String query = "UPDATE Users SET isActive = 1 WHERE userID = (SELECT userID FROM Staffs WHERE staffID = ?)";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, staffId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Staff getStaffById(int staffId) {
        String query = "SELECT s.staffID, u.userID, u.username, u.email, u.fullName, u.createdAt, u.isActive "
                 + "FROM Staffs s JOIN Users u ON s.userID = u.userID WHERE s.staffID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, staffId);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Staff(
                            rs.getInt("staffID"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("fullName"),
                            rs.getDate("createdAt"),
                            "Staff",
                            rs.getBoolean("isActive")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean isStaffActive(int staffId) {
        String query = "SELECT u.isActive FROM Users u JOIN Staffs s ON u.userID = s.userID WHERE s.staffID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, staffId);
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
    public List<Staff> searchStaff(String keyword) {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT s.staffID, u.userID, u.username, u.email, u.fullName, u.createdAt, u.isActive "
                + "FROM Staffs s JOIN Users u ON s.userID = u.userID "
                + "WHERE u.username LIKE ? OR u.email LIKE ? OR u.fullName LIKE ?";

        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            String searchKeyword = "%" + keyword + "%";
            ps.setString(1, searchKeyword);
            ps.setString(2, searchKeyword);
            ps.setString(3, searchKeyword);

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    staffList.add(new Staff(
                            rs.getInt("staffID"),
                            String.valueOf(rs.getInt("userID")),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("fullName"),
                            rs.getDate("createdAt"),
                            "Staff",
                            rs.getBoolean("isActive")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staffList;
    }
        public int getUserIDByStaffID(int staffID) {
        String query = "SELECT Users.userID FROM Staffs INNER JOIN Users ON Staffs.userID = Users.userID WHERE Staffs.staffID = ?";
        int userID = -1; // Giá trị mặc định nếu không tìm thấy
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, staffID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    userID = rs.getInt("userID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userID;
    }

}
