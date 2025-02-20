/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Product;
import java.util.List;

/**
 *
 * @author DUY
 */
public class TestDATA {
    public static void main(String[] args) {
        ProductDAO prd = new ProductDAO();
        List<Product> ls = prd.searchProduct("VT");
        for (Product l : ls) {
            System.out.println(l.toString());
            
        }
    }
    
}
