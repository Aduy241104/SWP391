/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Orders;
import Utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thaiv
 */
public class OrdersDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public OrdersDAO() {
        try {
            connection = new DBContext().getConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Orders> getAllOrders() {
        List<Orders> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Orders order = new Orders(
                        resultSet.getInt("orderID"),
                        resultSet.getInt("userID"),
                        resultSet.getDouble("totalAmount"),
                        resultSet.getDate("createdAt"),
                        resultSet.getString("address"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("orderStatus")
                );
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    // loc status ne :))
    public List<Orders> getOrdersByStatus(String status) {
        List<Orders> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE orderStatus = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Orders order = new Orders(
                        resultSet.getInt("orderID"),
                        resultSet.getInt("userID"),
                        resultSet.getDouble("totalAmount"),
                        resultSet.getDate("createdAt"),
                        resultSet.getString("address"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("orderStatus")
                );
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    // accept ne
    public boolean updateOrderStatusToShipping(int orderID) {
        String query = "UPDATE Orders SET orderStatus = 'shipping' WHERE orderID = ? AND orderStatus = 'pending'";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderID);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // delivered ne
    public boolean updateOrderStatusToDelivered(int orderId) {
        String query = "UPDATE Orders SET orderStatus = 'delivered' WHERE orderID = ? AND orderStatus = 'shipping'";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderId);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // reject ne
    public boolean cancelPendingOrder(int orderID) {
        String query = "UPDATE Orders SET orderStatus = 'cancelled' WHERE orderID = ? AND orderStatus = 'pending'";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderID);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // cancel ne
    public boolean cancelShippingOrder(int orderID) {
        String query = "UPDATE Orders SET orderStatus = 'cancelled' WHERE orderID = ? AND orderStatus = 'shipping'";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderID);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ting ting 
    public double getTotalAmountOfDeliveredOrders() {
        String query = "SELECT SUM(totalAmount) FROM Orders WHERE orderStatus = 'delivered'";
        double totalAmount = 0;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalAmount = resultSet.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalAmount;
    }

    // restore ne
    public boolean restoreCancelledOrder(int orderID) {
        String query = "UPDATE Orders SET orderStatus = 'pending' WHERE orderID = ? AND orderStatus = 'cancelled'";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderID);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // delete luon ne
    public boolean deleteCancelledOrder(int orderID) {
        String query = "DELETE FROM Orders WHERE orderID = ? AND orderStatus = 'cancelled'";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderID);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Orders> searchOrder(int orderId) {
        String query = "SELECT * FROM Orders WHERE orderId = ?";
        List<Orders> listResult = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Orders order = new Orders(
                        resultSet.getInt("orderId"),
                        resultSet.getInt("userId"),
                        resultSet.getDouble("totalAmount"),
                        resultSet.getDate("createdAt"),
                        resultSet.getString("address"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("orderStatus")
                );
                listResult.add(order);
            }
        } catch (Exception e) {
        }

        return listResult;
    }

    // test xiu nhe :)))
    public static void main(String[] args) {
        OrdersDAO orderDAO = new OrdersDAO();

        // Ví dụ nhập từ người dùng
        String userInput = "1+3"; // Người dùng nhập "1+3" tức là userID = 1, orderID = 3

        int userId = -1;
        int orderId = -1;
        if (userInput.contains("+")) {
            String[] parts = userInput.split("\\+");
            if (parts.length == 2) {
                try {
                    userId = Integer.parseInt(parts[0].trim());
                    orderId = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi chuyển đổi số: " + e.getMessage());
                }
            }
        }
        List<Orders> orders = orderDAO.searchOrder(orderId);

        if (orders.isEmpty()) {
            System.out.println("Không tìm thấy đơn hàng.");
        } else {
            for (Orders order : orders) {
                System.out.println("Mã đơn hàng: " + order.getOrderId());
                System.out.println("Mã khách hàng: " + order.getUserId());
                System.out.println("Tổng tiền: " + order.getTotalAmount());
                System.out.println("Ngày tạo: " + order.getCreatedAt());
                System.out.println("Địa chỉ: " + order.getAddress());
                System.out.println("Số điện thoại: " + order.getPhoneNumber());
                System.out.println("Trạng thái: " + order.getOrderStatus());
                System.out.println("---------------------------");
            }
        }
    }
}
