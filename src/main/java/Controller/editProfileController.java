/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.userDAO;
import Model.User;
import jakarta.servlet.http.HttpSession;
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
public class editProfileController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editProfileController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editProfileController at " + request.getContextPath() + "</h1>");
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

        if (userId_raw == null) {
            System.out.println("null");
            response.sendRedirect("error.jsp");
            return;
        }

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        int userId = 0;
        
        try {
            userId = Integer.parseInt(userId_raw);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid user ID.");
            request.getRequestDispatcher("viewProfile.jsp").forward(request, response);
            return;
        }

        userDAO userDAO = new userDAO();
        
        // Lấy thông tin user hiện tại để so sánh email
        User currentUser = userDAO.getUserById(userId);
        if (currentUser == null) {
            request.setAttribute("error", "User not found.");
            request.getRequestDispatcher("viewProfile.jsp").forward(request, response);
            return;
        }

        // Kiểm tra email đã tồn tại nhưng không phải của user hiện tại
        if (!email.equals(currentUser.getEmail())) { // Chỉ kiểm tra nếu email mới khác email cũ
            Optional<User> existingUser = userDAO.getUserByEmail(email);
            if (existingUser.isPresent()) {
                // Email đã tồn tại trong hệ thống
                request.setAttribute("user", currentUser);
                request.setAttribute("errorEmail", "This email is already in use by another account.");
                request.getRequestDispatcher("viewProfile.jsp").forward(request, response);
                return;
            }
        }

        // Nếu email hợp lệ, tiến hành cập nhật
        boolean success = userDAO.updateUser(userId, fullName, email);

        if (success) {
            // Cập nhật lại thông tin user trong session
            User updatedUser = userDAO.getUserById(userId);
            if (updatedUser != null) {
                session.setAttribute("user", updatedUser); // Cập nhật user trong session
            }
            session.setAttribute("successMessageGeneral", "Profile updated successfully!");
            response.sendRedirect("viewProfile"); // Redirect đến viewProfileController
            return;
        } else {
            // Nếu thất bại, cần tải lại thông tin user để hiển thị
            User user = userDAO.getUserById(userId);
            request.setAttribute("user", user);
            request.setAttribute("error", "Failed to update profile. Please try again.");
            request.getRequestDispatcher("viewProfile.jsp").forward(request, response);
        }
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
