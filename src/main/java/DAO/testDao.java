/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Product;
import java.util.List;

/**
 *
 * @author NHATHCE181222
 */
public class testDao {
    public static void main(String[] args) {
        ProductDAO prd = new ProductDAO();

        // ID của sản phẩm cần kiểm tra
        int productID = 1;

        try {
            Product product = prd.getProductByID(productID);

            if (product == null) {
                System.out.println("Không tìm thấy sản phẩm với ID: " + productID);
            } else {
                System.out.println("Thông tin sản phẩm với ID " + productID + ":");
                System.out.println(product.toString());
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy dữ liệu sản phẩm: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

    
