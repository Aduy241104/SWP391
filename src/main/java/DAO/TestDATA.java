/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Cart;
import Model.Orders;
import Model.Product;
import Model.Review;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DUY
 */
public class TestDATA {
    public static void main(String[] args) {
        ProductDAO prd = new ProductDAO();
        String age = "3"; // Giá trị phải giống với cơ sở dữ liệu
            List<Integer> categories = new ArrayList<>();
            categories.add(2);
            categories.add(7); // Ví dụ chọn category ID 1 và 3
            
            
             List<Integer> prices = new ArrayList<>();
            prices.add(1); // Chọn sản phẩm từ 0$ đến 50$
            
            List<Product> products = prd.getFilteredProducts(age, categories, prices);

            // In kết quả
            System.out.println("Danh sách sản phẩm đã lọc:");
            for (Product product : products) {
                System.out.println("ID: " + product.getProductID() + 
                                   ", Name: " + product.getProductName() +
                                   ", AgeRange: " + product.getAgeRange() +
                                   ", Price: $" + product.getPrice() +
                                   ", Stock: " + product.getStock());
            }
        
      }
    
}
