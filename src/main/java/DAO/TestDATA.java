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
      Review review = new Review(9, 1, 4, "holaaa");
      boolean check = cmd.addReview(review);
      if(check) {
          System.out.println("done");
      }else{
          System.out.println("fail");
      }
     
    }
    
}
