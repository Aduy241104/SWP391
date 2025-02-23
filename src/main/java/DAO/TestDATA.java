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
      commentDAO cmd = new commentDAO();
      List<Review> ls = cmd.filterRating(9, 5);
      
        for (Review l : ls) {
            System.out.println(l.toString());
            
        }
    }
    
}
