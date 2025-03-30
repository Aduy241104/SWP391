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
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
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

    // Hàm kiểm tra mật khẩu
    private boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUppercase = false;
        boolean hasNumber = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
        }
        
        return hasUppercase && hasNumber;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

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

        // Kiểm tra định dạng mật khẩu
        if (!isValidPassword(newPassword)) {
            request.setAttribute("errorMessage", "Password must be at least 8 characters long, contain at least one uppercase letter and one number.");
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}