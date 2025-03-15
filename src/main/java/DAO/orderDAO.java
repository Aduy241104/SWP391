/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Orders;
import Utils.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nguyen Phu Quy CE180789
 */
public class orderDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<Orders> getOrderListByUserID(int userID) {
        List<Orders> orderList = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE userID = ?";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int orderID = resultSet.getInt("orderID");
                double totalAmount = resultSet.getDouble("totalAmount");
                Date createdAt = resultSet.getDate("createdAt");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String orderStatus = resultSet.getString("orderStatus");

                Orders order = new Orders(orderID, userID, totalAmount, createdAt, address, phoneNumber, orderStatus);
                orderList.add(order);
            }
        } catch (Exception e) {
            System.out.println("Error to get orderlist: " + e.getMessage());
        }
        return orderList;
    }

    public List<Orders> getDeliveredOrders(int userID) {
        List<Orders> orderHistory = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE userID = ? AND orderStatus = 'delivered'";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int orderID = resultSet.getInt("orderID");
                double totalAmount = resultSet.getDouble("totalAmount");
                Date createdAt = resultSet.getDate("createdAt");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String orderStatus = resultSet.getString("orderStatus");

                Orders order = new Orders(orderID, userID, totalAmount, createdAt, address, phoneNumber, orderStatus);
                orderHistory.add(order);
            }
        } catch (Exception e) {
            System.out.println("Error to get delivered order history: " + e.getMessage());
        }
        return orderHistory;
    }

    public List<Orders> getOrdersByStatus(int userID, String orderStatus) {
        List<Orders> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE userId = ? AND orderStatus = ?";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, orderStatus);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int orderID = resultSet.getInt("orderID");
                double totalAmount = resultSet.getDouble("totalAmount");
                Date createdAt = resultSet.getDate("createdAt");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");

                Orders order = new Orders(orderID, userID, totalAmount, createdAt, address, phoneNumber, orderStatus);
                orders.add(order);
            }
        } catch (Exception e) {
            System.out.println("Error to get delivered order history: " + e.getMessage());
        }
        return orders;
    }
    public Orders getOrderById(int orderId) {
        Orders order = null;
        String query = "SELECT * FROM Orders WHERE orderID = ?";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) { // Chỉ có 1 đơn hàng
                int userID = resultSet.getInt("userID");
                double totalAmount = resultSet.getDouble("totalAmount");
                Date createdAt = resultSet.getDate("createdAt");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String orderStatus = resultSet.getString("orderStatus");

                order = new Orders(orderId, userID, totalAmount, createdAt, address, phoneNumber, orderStatus);
            }
        } catch (Exception e) {
            System.out.println("Error getting order by ID: " + e.getMessage());
        }
        return order; // Chỉ trả về một đơn hàng hoặc null nếu không tìm thấy
    }

    public boolean CancelOrderById(int orderID) {
        String query = "DELETE FROM Orders WHERE orderID = ?";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderID);
            int rowsDeleted = preparedStatement.executeUpdate(); // Trả về số hàng bị xóa
            System.out.println("Xóa order ID: " + orderID + " | rowDeleted: " + rowsDeleted);
            return rowsDeleted > 0; // Trả về true nếu xóa thành công
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Nếu có lỗi, trả về false
    }
    
    public boolean updateCustomerDetailsById(int orderID, String phoneNumber, String address) {
        String query = "UPDATE Orders SET phoneNumber = ?, address = ? WHERE orderID = ?";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, address);
            preparedStatement.setInt(3, orderID);

            int rowsUpdated = preparedStatement.executeUpdate(); // Trả về số dòng bị ảnh hưởng
            System.out.println("Cập nhật order ID: " + orderID + " | rowUpdated: " + rowsUpdated);
            return rowsUpdated > 0; // Trả về true nếu cập nhật thành công
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Nếu có lỗi, trả về false
    }
}
