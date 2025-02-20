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

}
