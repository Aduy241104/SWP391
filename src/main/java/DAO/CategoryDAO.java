/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import Model.Category;
import Utils.DBContext;
import java.util.ArrayList;

/**
 *
 * @author DUY
 */
public class CategoryDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<Category> getAllCategory() {
        String query = "SELECT * FROM Categories";
        List<Category> listCate = new ArrayList<>();

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int categoryID = resultSet.getInt(1);
                String categoryName = resultSet.getString(2);
                String description = resultSet.getString(3);

                Category cate = new Category(categoryID, categoryName, description);
                listCate.add(cate);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listCate;
    }

    public Category getCategoryById(int categoryID) {

        String query = "SELECT * FROM Categories WHERE categoryID = ?";
        Category category = null;

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, categoryID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String categoryName = resultSet.getString("categoryName");
                String description = resultSet.getString("description");

                category = new Category(categoryID, categoryName, description);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving category: " + e.getMessage());
        }
        return category;
    }

    public void deleteCategoryById(int categoryID) {
        String query = "DELETE FROM Categories WHERE categoryID = ?";

        try {
            connection = new DBContext().getConnect(); // Kết nối DB
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, categoryID);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Category deleted successfully!");
            } else {
                System.out.println("No category found with ID: " + categoryID);
            }
        } catch (Exception e) {
            System.out.println("Error deleting category: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }

    public void editCategoryById(int categoryId, String categoryName, String description) {
        String query = "UPDATE Categories SET categoryName = ?, description = ? WHERE categoryID = ?";
        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, categoryName);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, categoryId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Category updated successfully!");
            } else {
                System.out.println("No category found with ID: " + categoryId);
            }
        } catch (Exception e) {
            System.out.println("Error updating category: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                System.out.println("Error closing resources: " + ex.getMessage());
            }
        }
    }

    public void addCategory(String categoryName, String description) {
        String query = "INSERT INTO Categories (categoryName, description) VALUES (?, ?)";
        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, categoryName);
            preparedStatement.setString(2, description);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Category added successfully!");
            } else {
                System.out.println("Failed to add category.");
            }
        } catch (Exception e) {
            System.out.println("Error adding category: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                System.out.println("Error closing resources: " + ex.getMessage());
            }
        }
    }

    public List<Category> searchCategoryByName(String keyword) {
        List<Category> listCate = new ArrayList<>();
        String query = "SELECT * FROM Categories WHERE categoryName LIKE ?";

        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + keyword + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int categoryID = resultSet.getInt("categoryID");
                String categoryName = resultSet.getString("categoryName");
                String description = resultSet.getString("description");

                Category cate = new Category(categoryID, categoryName, description);
                listCate.add(cate);
            }
        } catch (Exception e) {
            System.out.println("Error searching category: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }

        return listCate;
    }

    public boolean isCategoryNameExists(String categoryName) {
        String query = "SELECT 1 FROM Categories WHERE categoryName = ?";
        try {
            connection = new DBContext().getConnect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, categoryName);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // nếu có kết quả thì tên đã tồn tại
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
