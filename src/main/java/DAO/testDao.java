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
        List<Product> ars = prd.getProductList();
        
        for (Product ar : ars) {
            System.out.println(ar.toString());
            
        }
    }
    
}
