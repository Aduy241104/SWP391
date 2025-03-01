/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Cart;
import Model.Orders;
import Model.Product;
import Model.Review;
import java.util.List;

/**
 *
 * @author DUY
 */
public class TestDATA {
    public static void main(String[] args) {
      Orders or = new Orders(1, 190, "Phường Tân Bình", "0374721054");
     OrdersDAO ord = new OrdersDAO();
     int rs = ord.addOrder(or);
        System.out.println(rs);
    }
    
}
