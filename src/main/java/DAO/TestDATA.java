/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Product;
import Model.Review;
import java.util.List;

/**
 *
 * @author DUY
 */
public class TestDATA {
    public static void main(String[] args) {
        CartDAO crt = new CartDAO();
        int arr [] = {58,59};
        double rs = crt.getTotalPriceByCartItemID(arr);
        System.out.println(rs);
     
    }
    
}
