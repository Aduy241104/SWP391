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

    public static void main(String[] args) {
        StaffDAO staffDAO = new StaffDAO();

        // Dữ liệu nhân viên cần thêm
        String username = "staff02";
        String email = "staff02@gmail.com";
        String fullName = "Staff Two";
        String password = "pass123";
        String role = "Staff";

        // Gọi hàm addStaff để thêm nhân viên
        boolean isAdded = staffDAO.addStaff(username, email, fullName, password, true);

        // Kiểm tra kết quả
        if (isAdded) {
            System.out.println("✅ Nhân viên mới đã được thêm thành công!");
        } else {
            System.out.println("❌ Thêm nhân viên thất bại!");
        }
    }
}
