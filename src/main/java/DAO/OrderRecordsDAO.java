/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.OrderRecords;
import Model.Staff;
import Utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author thaiv
 */
public class OrderRecordsDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public OrderRecordsDAO() {
        try {
            connection = new DBContext().getConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean AddStaffAction(OrderRecords orderRecords) {
        String query = "INSERT INTO [dbo].[OrderRecords] ([orderID], [staffID], [action]) VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderRecords.getOrderID());
            preparedStatement.setInt(2, orderRecords.getStaffID());
            preparedStatement.setString(3, orderRecords.getAction());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
public int getStaffIDByUserID(int userID) {
    String query = "SELECT staffID FROM Staffs WHERE userID = ?";
    int staffID = 0; 
    
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, userID); 
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                staffID = rs.getInt("staffID");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return staffID;
}

}
