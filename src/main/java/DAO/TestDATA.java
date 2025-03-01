/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Cart;
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
        Cart cart = crt.getCartItemByID(62);
        System.out.println(cart.toString());
     
    }
    
}
