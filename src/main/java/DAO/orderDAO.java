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
}
