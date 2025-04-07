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
import java.sql.Statement;
import java.text.DecimalFormat;
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
    public List<Orders> getAllOrders() {
        List<Orders> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders";

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                double totalAmount = resultSet.getDouble("totalAmount");

                DecimalFormat df = new DecimalFormat("#,###");
                String formattedRevenue = df.format(totalAmount);
                try {
                    double Total = Double.parseDouble(formattedRevenue);
                    Orders order = new Orders(
                            resultSet.getInt("orderID"),
                            resultSet.getInt("userID"),
                            Total,
                            resultSet.getDate("createdAt"),
                            resultSet.getString("address"),
                            resultSet.getString("phoneNumber"),
                            resultSet.getString("orderStatus")
                    );
                    orders.add(order);
                } catch (Exception e) {
                }

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
        StockDAO stockDAO = new StockDAO();
        double totalStock = stockDAO.calculateStockDifference();
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalAmount = resultSet.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalStock < 0 ? totalAmount + totalStock : totalAmount + Math.abs(totalStock);
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
    

    public int addOrder(Orders order) {
        String query = "INSERT INTO Orders (userID,totalAmount,address,phoneNumber)\n"
                + "VALUES (?,?,?,?)";

        int rs = -1;
        try {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setDouble(2, order.getTotalAmount());
            preparedStatement.setString(3, order.getAddress());
            preparedStatement.setString(4, order.getPhoneNumber());

            int affectedRows = preparedStatement.executeUpdate();

            // Kiểm tra xem có dòng nào được chèn không
            if (affectedRows > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                while (resultSet.next()) {
                    rs = resultSet.getInt(1);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // Đóng resultSet để tránh rò rỉ bộ nhớ
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }

}
