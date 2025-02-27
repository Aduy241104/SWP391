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

    public static void main(String[] args) {
        // Tạo đối tượng StaffDAO để gọi phương thức
        StaffDAO staffDAO = new StaffDAO();

        // Gọi phương thức getAllStaffs()
        List<Staff> staffList = staffDAO.getAllStaffs();

        // Kiểm tra nếu danh sách rỗng
        if (staffList == null || staffList.isEmpty()) {
            System.out.println("Không có nhân viên nào trong danh sách.");
        } else {
            // Duyệt danh sách và in thông tin
            System.out.println("Danh sách nhân viên:");
            for (Staff staff : staffList) {
                System.out.println("ID: " + staff.getStaffID()
                        + ", Username: " + staff.getUsername()
                        + ", Email: " + staff.getEmail()
                        + ", Full Name: " + staff.getFullName()
                        + ", Created At: " + staff.getCreatedAt());
            }
        }
    }
}
