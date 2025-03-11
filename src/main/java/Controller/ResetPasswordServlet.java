/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.PasswordResetDAO;
import DAO.userDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 *
 * @author NHATHCE181222
 */
public class ResetPasswordServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResetPasswordServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        String token = request.getParameter("token");
        String newPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (token == null || token.isEmpty()) {
            request.setAttribute("errorMessage", "Invalid reset link.");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
            return;
        }

        if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.getRequestDispatcher("resetpassword.jsp?token=" + token).forward(request, response);
            return;
        }

        PasswordResetDAO resetDAO = new PasswordResetDAO();
        userDAO userDAO = new userDAO();

        // Kiểm tra token có hợp lệ không
        Optional<Integer> userIdOpt = resetDAO.getUserIdByToken(token);
        if (userIdOpt.isPresent()) {
            int userId = userIdOpt.get();

            // Cập nhật mật khẩu mới
            boolean isUpdated = userDAO.updateUserPassword(userId, newPassword);
            if (isUpdated) {
                resetDAO.deleteToken(token); // Xóa token sau khi sử dụng
                request.setAttribute("successMessage", "Password reset successfully. You can now login.");
                request.getRequestDispatcher("signIn.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Failed to reset password.");
                request.getRequestDispatcher("resetpassword.jsp?token=" + token).forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Invalid or expired token.");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
        }
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
