/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import DAO.commentDAO;
import Model.Product;
import Model.Review;
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
public class ViewFeedbackController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewFeedbackController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewFeedbackController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            commentDAO cmt = new commentDAO();
            ProductDAO productDAO = new ProductDAO();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            
            if(user != null) {
                boolean checkBuy = cmt.checkIsBuy(productID, user.getUserId());
                boolean isComment = cmt.isComment(user.getUserId(), productID);
                System.out.println(isComment);
                
                 if(checkBuy && !isComment) {
                     System.out.println("hahhaha");
                      request.setAttribute("isBuy", "buy");
                 }
            }
            
            Product product = productDAO.getProductByID(productID);
            
            List<Review> listReview = cmt.getReviewByProductIDss(productID);
            double avgRating = cmt.getAvergeRating(productID);

            request.setAttribute("product", product);
            request.setAttribute("avgRating", avgRating);
            request.setAttribute("productID", productID);
            request.setAttribute("listReview", listReview);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/FeedbackProduct.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
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
