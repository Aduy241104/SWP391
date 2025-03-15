/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.OrderDetailDAO;
import DAO.OrdersDAO;
import DAO.ProductDAO;
import Model.Orders;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author DUY
 */
public class ConfirmBuyNow extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       String phoneNumber = request.getParameter("phoneNumber");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String phuong = request.getParameter("phuong");
        String addressDetail = request.getParameter("Address");

        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            OrdersDAO ord = new OrdersDAO();
            OrderDetailDAO ordt = new OrderDetailDAO();
            ProductDAO prd = new ProductDAO();

            //chuan bi dia chi
            addressDetail += phuong + ", " + district + ", " + city;
            Orders order = new Orders(user.getUserId(), totalAmount, addressDetail, phoneNumber);
            int orderIDGenerate = ord.addOrder(order);

            ordt.addOrderDetail(orderIDGenerate, productID, quantity, totalAmount);
            response.sendRedirect("OrderSuccessPage.jsp");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.sendRedirect("error.jsp");
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
