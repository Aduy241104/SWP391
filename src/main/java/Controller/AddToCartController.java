/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CartDAO;
import DAO.ProductDAO;
import Model.Product;
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
public class AddToCartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            response.sendRedirect("signIn.jsp");
            return;
        }
        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            CartDAO crd = new CartDAO();
            ProductDAO prd = new ProductDAO();
            Product product = prd.getProductByID(productID);

            //kiem tra productID co ton tai hay khong va so luong co phu hop hay khong
            if (product == null || quantity <= 0) {
                throw new Exception();
            }
            
            //Kiem tra xem nguoi dung da co gio hang hay chua neu chua co thi tao mot gio hang moi
            int cartID = crd.getCartID(user.getUserId());
            if(cartID == -1) {
                cartID = crd.createCart(user.getUserId());
            }
            
            // lay cartItemID dua tren productId va cartID de kiem tra xem san pham co ton tai trong gio hang hay chua
            int cartItemID = crd.checkProductIncart(cartID, productID);
      
            //neu da ton tai thi thuc hien cong don so luong san pham them vao 
            if(cartItemID != -1) {
                int currentQuantity = crd.getQuantity(cartItemID);
                if(product.getStock() >= currentQuantity + quantity) {
                    crd.updateQuantity(cartItemID, currentQuantity + quantity);
                }
            // neu san pham chua co trong gio hang thi thuc hien tao san pham moi trong gio
            }else{
                //chi thuc hien them khi so luong trong kho dap ung du
                if(product.getStock() >= quantity ) {
                    crd.addNewProduct(cartID, productID, quantity);
                }
            }
            response.sendRedirect("ViewCartController");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.sendRedirect("ViewProductListController");
        }
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
