/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.CartDAO;
import DAO.OrderDetailDAO;
import DAO.OrdersDAO;
import DAO.ProductDAO;
import Model.Cart;
import Model.Orders;
import Model.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DUY
 */
public class ConfirmOrderController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String cartItemID[] = request.getParameterValues("cartItemID");
        String phoneNumber = request.getParameter("phoneNumber");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String phuong = request.getParameter("phuong");
        String addressDetail = request.getParameter("Address");
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (cartItemID != null) {
            int[] selectedItemIDs = new int[cartItemID.length];
            
            //chuyen cac cartID duoc chon thanh dang int
            for (int i = 0; i < cartItemID.length; i++) {
                try {
                    selectedItemIDs[i] = Integer.parseInt(cartItemID[i]);
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
            
            CartDAO crd = new CartDAO();
            OrdersDAO ord = new OrdersDAO();
            OrderDetailDAO ordt = new OrderDetailDAO();
            ProductDAO prd = new ProductDAO();
            
            List<Cart> listItem = new ArrayList<>();
            for (int item : selectedItemIDs) {
                Cart cart = crd.getCartItemByID(item);
                listItem.add(cart);
            }
            
            for (Cart cart : listItem) {
                int stock = prd.getStock(cart.getProduct().getProductID());
                if(stock < cart.getQuantity()){
                    response.sendRedirect("OrderForm.jsp?status=error");
                    return;
                }
            }
            
            //chuan bi dia chi
            addressDetail += phuong + ", " + district + ", " + city;
            Orders order = new Orders(user.getUserId(), totalAmount, addressDetail, phoneNumber);
            int orderIDGenerate = ord.addOrder(order);
            
            //Tao order detail
            for (Cart cart : listItem) {
                
                int productID = cart.getProduct().getProductID();
                int quantity = cart.getQuantity();
                double price = cart.getProduct().getPrice() * quantity;
                ordt.addOrderDetail(orderIDGenerate, productID, quantity, price);      
                crd.deleteCartItem(cart.getCartItemID());
                prd.updateStock(productID, cart.getProduct().getStock() - quantity ); 
            }
            response.sendRedirect("OrderSuccessPage.jsp");
           
        }else {
            response.sendRedirect("OrderSuccessPage.jsp");
        }
           
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
