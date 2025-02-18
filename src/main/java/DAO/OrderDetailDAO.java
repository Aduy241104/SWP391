/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.OrderDetail;
import Utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author thaiv
 */
public class OrderDetailDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public OrderDetailDAO() {
        try {
            connection = new DBContext().getConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrderDetail getOrderDetailById(int orderDetailsID) {
        String query = "SELECT *\n"
                + "FROM     Orders INNER JOIN\n"
                + "                  OrderDetails ON Orders.orderID = OrderDetails.orderID INNER JOIN\n"
                + "                  Products ON OrderDetails.productID = Products.productID INNER JOIN\n"
                + "                  Categories ON Products.categoryID = Categories.categoryID INNER JOIN\n"
                + "                  Users ON Orders.userID = Users.userID\n"
                + "				  Where OrderDetails.orderDetailID = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderDetailsID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new OrderDetail(
                        resultSet.getInt("orderID"),
                        resultSet.getString("username"),
                        resultSet.getString("productName"),
                        resultSet.getDouble("totalAmount"),
                        resultSet.getString("address"),
                        resultSet.getString("imageUrl"),
                        resultSet.getDouble("price"),
                        resultSet.getString("categoryName"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("orderStatus"),
                        resultSet.getString("description")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getOrderDetailsID(int orderID, double price, int userID) {
        String query = "SELECT TOP 1 OrderDetails.orderDetailID "
                + "FROM OrderDetails "
                + "INNER JOIN Orders ON OrderDetails.orderID = Orders.orderID "
                + "WHERE OrderDetails.orderID = ? AND OrderDetails.price = ? AND Orders.userID = ?";

        try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderID);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, userID);

            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("orderDetailID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

        int orderID = 9;

        OrderDetail orderDetail = orderDetailDAO.getOrderDetailById(orderID);
        if (orderDetail != null) {
            System.out.println("===== ORDER DETAIL =====");
            System.out.printf("Username       : %s%n", orderDetail.getUsername());
            System.out.printf("Product Name   : %s%n", orderDetail.getProductName());
            System.out.printf("Total Amount   : %.2f%n", orderDetail.getTotalAmount());
            System.out.printf("Address        : %s%n", orderDetail.getAddress());
            System.out.printf("Image URL      : %s%n", orderDetail.getImageUrl());
            System.out.printf("Price          : %.2f%n", orderDetail.getPrice());
            System.out.printf("Category       : %s%n", orderDetail.getCategoryName());
            System.out.printf("Email          : %s%n", orderDetail.getEmail());
            System.out.printf("Phone Number   : %s%n", orderDetail.getPhoneNumber());
            System.out.printf("Order Status   : %s%n", orderDetail.getOrderStatus());
            System.out.printf("Description    : %s%n", orderDetail.getDescription());
        } else {
            System.out.println("Không tìm thấy đơn hàng!");
        }
    }
}
