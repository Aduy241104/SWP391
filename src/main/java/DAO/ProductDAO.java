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
        String query = "SELECT p.* FROM Products p "
                + "JOIN Categories c ON p.categoryId = c.categoryId "
                + "WHERE (p.productName LIKE ? OR c.categoryName LIKE ?) "
                + "AND p.isActive = 1";

        List<Product> listResult = new ArrayList<>();
        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keySearch + "%");
            preparedStatement.setString(2, "%" + keySearch + "%");
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

    public List<Product> getProductListBestSeller() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT TOP 5 P.*\n"
                + "FROM Products P\n"
                + "JOIN (\n"
                + "    SELECT productID, SUM(quantity) AS Total\n"
                + "    FROM OrderDetails\n"
                + "    GROUP BY productID\n"
                + ") O ON P.productID = O.productID\n"
                + "WHERE P.isActive = 1\n"
                + "ORDER BY O.Total DESC;";

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

    public List<Product> getFilteredProducts(String age, List<Integer> categories, List<Integer> prices) {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM Products WHERE isActive = 1";

        boolean filterByAge = age != null && !age.isEmpty() && !age.equals("all");

        // Lọc theo độ tuổi
        if (filterByAge) {
            query += " AND ageRange = ?";
        }

        // Lọc theo danh mục
        if (categories != null && !categories.isEmpty()) {
            query += " AND categoryID IN (";
            for (int i = 0; i < categories.size(); i++) {
                query += "?";
                if (i < categories.size() - 1) {
                    query += ", ";
                }
            }
            query += ")";
        }

        // Lọc theo giá
        if (prices != null && !prices.isEmpty()) {
            query += " AND (";
            List<String> priceConditions = new ArrayList<>();
            for (int price : prices) {
                if (price == 1) {
                    priceConditions.add("price BETWEEN 0 AND 50");
                } else if (price == 2) {
                    priceConditions.add("price BETWEEN 50 AND 100");
                } else if (price == 3) {
                    priceConditions.add("price > 100");
                }
            }
            query += String.join(" OR ", priceConditions) + ")";
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int paramIndex = 1;

            // Chỉ set age nếu có lọc
            if (filterByAge) {
                preparedStatement.setString(paramIndex++, age);
            }

            // Set các giá trị category
            if (categories != null && !categories.isEmpty()) {
                for (int category : categories) {
                    preparedStatement.setInt(paramIndex++, category);
                }
            }

            ResultSet resultSet = preparedStatement.executeQuery();
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

    public int getStock(int productID) {
        String query = "SELECT Stock FROM Products WHERE productID = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int result = resultSet.getInt(1);
                return result;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
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

    public boolean increaseProductStock(int productID, int quantity) {
        String query = "UPDATE Products SET stock = stock + ? WHERE productID = ?";
        try {
            // Kết nối tới database
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);

            // Thiết lập các tham số trong câu lệnh SQL
            preparedStatement.setInt(1, quantity); // Số lượng cần tăng
            preparedStatement.setInt(2, productID); // ID của sản phẩm cần cập nhật

            // Thực thi câu lệnh SQL
            int rowEffect = preparedStatement.executeUpdate();

            // Kiểm tra nếu có bản ghi nào bị ảnh hưởng, nghĩa là cập nhật thành công
            return rowEffect > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
