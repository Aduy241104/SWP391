/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

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
public class ProductDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ProductDAO() {
        try {
            connection = new DBContext().getConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Lấy danh sách tất cả sản phẩm
    public List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM Products WHERE isActive = 1";

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            Product product = new Product(
                resultSet.getInt("productID"),
                resultSet.getString("productName"),
                resultSet.getString("description"),
                resultSet.getDouble("price"),
                resultSet.getInt("stock"),
                resultSet.getString("imageUrl"),
                resultSet.getInt("categoryID"),
                resultSet.getDate("createdAt"),
                resultSet.getBoolean("isActive"),
                resultSet.getString("size"),
                resultSet.getString("ageRange"),
                resultSet.getString("origin"),
                resultSet.getDouble("weight")
            );
            productList.add(product);
           
            
        }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    // Lấy sản phẩm theo ID
    public Product getProductByID(int productID) {
        String query = "SELECT * FROM Products WHERE productID = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Product(
                    resultSet.getInt("productID"),
                    resultSet.getString("productName"),
                    resultSet.getString("description"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("stock"),
                    resultSet.getString("imageUrl"),
                    resultSet.getInt("categoryID"),
                    resultSet.getDate("createdAt"),
                    resultSet.getBoolean("isActive"),
                    resultSet.getString("size"),
                    resultSet.getString("ageRange"),
                    resultSet.getString("origin"),
                    resultSet.getDouble("weight")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
