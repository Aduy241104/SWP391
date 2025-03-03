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

    public List<Product> searchProduct(String keySearch) {
        String query = "SELECT * FROM Products WHERE productName LIKE ? AND isActive = 1";

        List<Product> listResult = new ArrayList<>();
        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keySearch + "%");
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
                listResult.add(product);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listResult;
    }

    public boolean addProduct(Product product) {
        String query = "INSERT INTO Products (productName, description, price, stock, imageUrl, categoryID, createdAt, isActive, size, ageRange, origin, weight) "
                + "VALUES (?, ?, ?, ?, ?, ?, GETDATE(), ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getStock());
            preparedStatement.setString(5, product.getImageUrl());
            preparedStatement.setInt(6, product.getCategoryID());
            preparedStatement.setBoolean(7, true);
            preparedStatement.setString(8, product.getSize());
            preparedStatement.setString(9, product.getAgeRange());
            preparedStatement.setString(10, product.getOrigin());
            preparedStatement.setDouble(11, product.getWeight());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProduct(int productID) {
        String query = "UPDATE Products\n"
                + "SET isActive = 0\n"
                + "WHERE productID = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productID);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Product> getRelatedProduct(int categoryID) {
        String query = "SELECT TOP 4 * FROM Products WHERE categoryID = ? AND isActive = 1 ORDER BY price DESC";
        List<Product> listResult = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, categoryID);
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
                listResult.add(product);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listResult;
    }

    public boolean updateProduct(Product product) {
        String query = "UPDATE Products SET productName = ?, description = ?, price = ?, stock = ?, imageUrl = ?, categoryID = ?, size = ?, ageRange = ?, origin = ?, weight = ? WHERE productID = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getStock());
            preparedStatement.setString(5, product.getImageUrl());
            preparedStatement.setInt(6, product.getCategoryID());
            preparedStatement.setString(7, product.getSize());
            preparedStatement.setString(8, product.getAgeRange());
            preparedStatement.setString(9, product.getOrigin());
            preparedStatement.setDouble(10, product.getWeight());
            preparedStatement.setInt(11, product.getProductID());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Product> getDisabledProducts() {
        List<Product> disabledProducts = new ArrayList<>();
        String query = "SELECT * FROM Products WHERE isActive = 0";

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
                disabledProducts.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return disabledProducts;
    }

    public boolean restoreProduct(int productID) {
        String query = "UPDATE Products SET isActive = 1 WHERE productID = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productID);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStock(int productID, int newStock) {
        String query = "UPDATE Products SET stock = ? WHERE productID = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, newStock); // Giá trị số lượng mới
            preparedStatement.setInt(2, productID); // ID của sản phẩm

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //thaiv
    public boolean isProductNameAndCategoryExist(String productName, int categoryID) {
        String query = "SELECT COUNT(*) FROM Products WHERE productName = ? AND categoryID = ? AND isActive = 1";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productName);
            preparedStatement.setInt(2, categoryID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1); 
                return count > 0; 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; 
    }

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        boolean exists = productDAO.isProductNameAndCategoryExist("Thai", 1);
        if (exists) {
            System.out.println("Product with this name and category already exists!");
        } else {
            System.out.println("Product does not exist. You can add it.");
        }
    }
}
