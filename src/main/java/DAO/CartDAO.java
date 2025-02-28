/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Cart;
import Model.Product;
import Utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DUY
 */
public class CartDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<Cart> getCartListByCartID(int cartID) {

        String query = "SELECT crt.cartItemID, crt.quantity, \n"
                + "Prd.productID, Prd.productName, Prd.description,Prd.price,Prd.stock, Prd.imageUrl, Prd.isActive\n"
                + "FROM CartItems AS crt\n"
                + "INNER JOIN Products AS Prd \n"
                + "ON Prd.productID = crt.productID\n"
                + "WHERE cartID = ? AND Prd.isActive = 1;";

        List<Cart> cartList = new ArrayList<>();

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cartID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int cartItemID = resultSet.getInt("cartItemID");
                int quantity = resultSet.getInt("quantity");
                int productID = resultSet.getInt("productID");
                String productName = resultSet.getString("productName");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");
                String imageUrl = resultSet.getString("imageUrl");
                boolean isActive = resultSet.getBoolean("isActive");

                Product product = new Product(productID, productName, description, price, stock, imageUrl, isActive);
                Cart cart = new Cart(cartItemID, cartID, quantity, product);
                cartList.add(cart);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cartList;
    }

    public int getCartID(int userID) {
        String query = "SELECT cartID FROM Cart WHERE userID = ?";

        int cartID = -1;
        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cartID = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cartID;
    }

    public int deleteCartItem(int cartItemsID) {
        String queryDeleteCartItem = "DELETE FROM CartItems WHERE cartItemID = ?";
        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(queryDeleteCartItem);
            preparedStatement.setInt(1, cartItemsID);

            int rowEffect = preparedStatement.executeUpdate();
            if (rowEffect >= 1) {
                return 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int updateQuantity(int cartItemID, int quantity) {
        String queryUpdateCartItem = "UPDATE CartItems SET quantity = ? WHERE cartItemID = ?";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(queryUpdateCartItem);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, cartItemID);

            int rowEffect = preparedStatement.executeUpdate();
            if (rowEffect >= 1) {
                return 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int createCart(int userID) {
        String query = "INSERT INTO Cart (userID) VALUES (?)";
        int cartID = -1;

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                cartID = resultSet.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cartID;
    }

    public boolean checkOwnCart(int cartID, int cartItemsID) {
        String query = "SELECT cartItemID FROM CartItems WHERE cartItemID = ? AND cartID = ?";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cartItemsID);
            preparedStatement.setInt(2, cartID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getInt(1) != 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public int checkProductIncart(int cartID, int productID) {
        String query = "SELECT cartItemID FROM CartItems WHERE cartID = ? AND productID = ?";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cartID);
            preparedStatement.setInt(2, productID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public int getQuantity(int cartItemID) {
        String query = "SELECT quantity FROM CartItems WHERE cartItemID = ?";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cartItemID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public boolean addNewProduct(int cartID, int productID, int quantity) {
        String query = "INSERT CartItems (cartID, productID, quantity)\n"
                + "VALUES (?,?,?)";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cartID);
            preparedStatement.setInt(2, productID);
            preparedStatement.setInt(3, quantity);

            int rowEffect = preparedStatement.executeUpdate();
            if (rowEffect > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public double getTotalPriceByCartItemID(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        StringBuilder query = new StringBuilder("SELECT SUM(tol) as totalPrice\n"
                + "FROM (SELECT cartItemID, sum(pro.price * crt.quantity) as tol\n"
                + "      FROM CartItems as crt\n"
                + "      INNER JOIN Products as pro\n"
                + "      ON pro.productID = crt.productID\n"
                + "      GROUP BY cartItemID) AS total\n"
                + "WHERE cartItemID IN (");

        for (int i = 0; i < array.length; i++) {
            query.append("?");
            if (i < array.length - 1) {
                query.append(", ");
            }
        }
        query.append(")");

        double totalPrice = 0;

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query.toString());

            for (int i = 0; i < array.length; i++) {
                preparedStatement.setInt(i + 1, array[i]);
            }

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalPrice = resultSet.getDouble("totalPrice");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPrice;
    }

}
