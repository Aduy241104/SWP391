/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.userDAO;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

/**
 *
 * @author NHATHCE181222
 */
public class changePasswordController extends HttpServlet {

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
            out.println("<title>Servlet changePasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet changePasswordController at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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

        HttpSession session = request.getSession();
        String userId_raw = request.getParameter("userId");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String repeatPassword = request.getParameter("repeatPassword");

        if (userId_raw == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId;
        try {
            userId = Integer.parseInt(userId_raw);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid user ID.");
            request.getRequestDispatcher("viewProfile.jsp").forward(request, response);
            return;
        }

        userDAO dao = new userDAO();

        // Kiểm tra mật khẩu cũ không được rỗng
        if (oldPassword == null || oldPassword.trim().isEmpty()) {
            request.setAttribute("errorOldPassword", "Old password cannot be empty.");
            request.getRequestDispatcher("viewProfile.jsp").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu cũ có đúng không
        if (!dao.checkOldPassword(userId, oldPassword)) {
            request.setAttribute("errorOldPassword", "Old password is incorrect.");
            request.getRequestDispatcher("viewProfile.jsp").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu mới không được rỗng
        if (newPassword == null || repeatPassword == null
                || newPassword.trim().isEmpty() || repeatPassword.trim().isEmpty()) {
            request.setAttribute("error", "New password fields cannot be empty.");
            request.getRequestDispatcher("viewProfile.jsp").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu mới có hợp lệ không
        if (!isValidPassword(newPassword)) {
            request.setAttribute("errorNewPassword",
                    "Password must be at least 8 characters long and contain at least one number and one uppercase letter.");
            request.getRequestDispatcher("viewProfile.jsp").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu mới và mật khẩu nhập lại có trùng khớp không
        if (!newPassword.equals(repeatPassword)) {
            request.setAttribute("errorRepeatPassword", "New password and repeat password do not match.");
            request.getRequestDispatcher("viewProfile.jsp").forward(request, response);
            return;
        }

        // Kiểm tra xem mật khẩu mới có trùng với mật khẩu cũ không
        if (newPassword.equals(oldPassword)) {
            request.setAttribute("error", "New password cannot be the same as the old password.");
            request.getRequestDispatcher("viewProfile.jsp").forward(request, response);
            return;
        }

        // Cập nhật mật khẩu
        boolean isChanged = dao.changePassword(userId, oldPassword, newPassword);
        if (isChanged) {
            request.setAttribute("message", "Password changed successfully!");
        } else {
            request.setAttribute("error", "Password change failed. Please try again.");
        }

        request.getRequestDispatcher("viewProfile.jsp").forward(request, response);
    }

// Kiểm tra mật khẩu có ít nhất 8 ký tự, chứa ít nhất 1 số và 1 chữ cái viết hoa
    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$";
        return Pattern.matches(regex, password);
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
