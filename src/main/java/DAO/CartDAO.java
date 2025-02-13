/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        String query = "SELECT crt.cartItemID, crt.quantity, "
                + "Prd.productID, Prd.productName,Prd.price, Prd.description, Prd.imageUrl, Prd.isActive\n"
                + "FROM CartItems AS crt\n"
                + "INNER JOIN Products AS Prd \n"
                + "ON Prd.productID = crt.productID\n"
                + "WHERE cartID = ?";

        try {

        } catch (Exception e) {
        }
        return null;
    }
}
