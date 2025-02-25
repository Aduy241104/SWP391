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
      boolean check = cmd.deleteReviewByID(2);
      if(check) {
          System.out.println("delete done");
      }else{
          System.out.println("delete fail");
      }
    }
    
}
