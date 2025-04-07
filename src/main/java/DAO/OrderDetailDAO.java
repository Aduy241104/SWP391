/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Cart;
import Model.OrderDetail;
import Model.UserAction;
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

    public List<OrderDetail> getOrderDetailById(int orderID) {
        List<OrderDetail> orderDetailsList = new ArrayList<>();
        String query = "SELECT *\n"
                + "FROM     Orders INNER JOIN\n"
                + "                  OrderDetails ON Orders.orderID = OrderDetails.orderID INNER JOIN\n"
                + "                  Products ON OrderDetails.productID = Products.productID INNER JOIN\n"
                + "                  Categories ON Products.categoryID = Categories.categoryID INNER JOIN\n"
                + "                  Users ON Orders.userID = Users.userID\n"
                + "				  Where Orders.orderID = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail(
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
                        resultSet.getString("description"),
                        resultSet.getInt("quantity")
                );
                orderDetailsList.add(orderDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDetailsList;
    }

    public boolean addOrderDetail(int orderID, int productID, int quantity, double price) {
        String query = "INSERT INTO OrderDetails (orderID, productID, quantity, price) \n"
                + "VALUES (?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderID);
            preparedStatement.setInt(2, productID);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setDouble(4, price);

            int rowEffect = preparedStatement.executeUpdate();

            if (rowEffect > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;

    }

    public List<UserAction> getUserActionsByOrderID(int orderID) {
        String query = "SELECT userName, action "
                + "FROM OrderRecords "
                + "WHERE orderID = ?";
        List<UserAction> userActions = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderID);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String userName = rs.getString("userName");
                    String action = rs.getString("action");
                    userActions.add(new UserAction(userName, action));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userActions;
    }

    public List<OrderDetail> getProductQuantityInOrder(int orderID) {
        List<OrderDetail> details = new ArrayList<>();
        String query = "SELECT productID, quantity FROM OrderDetails WHERE orderID = ?";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderDetail od = new OrderDetail();
                od.setProductID(resultSet.getInt("productID"));
                od.setQuantity(resultSet.getInt("quantity"));
                details.add(od);
            }
        } catch (Exception e) {
            System.out.println("Error getting product quantity in order: " + e.getMessage());
        }

        return details;
    }

}
