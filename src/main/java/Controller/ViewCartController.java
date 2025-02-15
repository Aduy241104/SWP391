/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CartDAO;
import Model.Cart;
import Model.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author DUY
 */
public class ViewCartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
         
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            // Nếu user chưa đăng nhập, chuyển hướng về trang login
            response.sendRedirect("signIn.jsp");
            return;
        }

        CartDAO crd = new CartDAO();
        int userID = user.getUserId();
        int cartID = crd.getCartID(userID);

        // neu chua co thi tao gio hang moi
        if (cartID == -1) {
            cartID = crd.createCart(userID);
        }

        List<Cart> listCartItems = crd.getCartListByCartID(cartID);

        for (Cart listCartItem : listCartItems) {

            if (listCartItem.getQuantity() > listCartItem.getProduct().getStock()) {
                int stockOfProduct = listCartItem.getProduct().getStock();
                int cartItemID = listCartItem.getCartItemID();
                
                if (stockOfProduct == 0) {
                    crd.updateQuantity(cartItemID, stockOfProduct);
                } else if (stockOfProduct != 0) {
                    crd.updateQuantity(cartItemID, stockOfProduct);
                }
            }
        }

        listCartItems = crd.getCartListByCartID(cartID);
        request.setAttribute("cartList", listCartItems);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/ViewCart.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
