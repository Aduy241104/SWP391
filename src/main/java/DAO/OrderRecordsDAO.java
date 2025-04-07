package DAO;

import Model.OrderRecords;
import Utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderRecordsDAO {

    private Connection connection;

    public OrderRecordsDAO() {
        try {
            connection = new DBContext().getConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean AddUserAction(OrderRecords orderRecords, int userID) {
        String query = "INSERT INTO [dbo].[OrderRecords] ([orderID], [staffID], [adminID], [action], [userName]) VALUES (?, ?, ?, ?, ?)";

        int staffID = getStaffIDByUserID(userID);
        int adminID = getAdminIDByUserID(userID);
        String userName = getUserNameByUserID(userID); // Lấy userName từ userID

        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderRecords.getOrderID());

            if (staffID != 0) {
                ps.setInt(2, staffID);
                ps.setNull(3, java.sql.Types.INTEGER);
            } else if (adminID != 0) {
                ps.setNull(2, java.sql.Types.INTEGER);
                ps.setInt(3, adminID);
            } else {
                return false;
            }

            ps.setString(4, orderRecords.getAction());
            ps.setString(5, userName); // Lưu userName vào cột mới

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getStaffIDByUserID(int userID) {
        String query = "SELECT staffID FROM Staffs WHERE userID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userID);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("staffID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getAdminIDByUserID(int userID) {
        String query = "SELECT adminID FROM Admins WHERE userID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userID);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("adminID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getUserNameByUserID(int userID) {
        String query = "SELECT username FROM Users WHERE userID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userID);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("username");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

  

    public static void main(String[] args) {
        try {
            OrderRecordsDAO orderRecordsDAO = new OrderRecordsDAO();
            int userID = 4; // Thay đổi userID để kiểm thử

            System.out.println(orderRecordsDAO.getUserNameByUserID(4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
