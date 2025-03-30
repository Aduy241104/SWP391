/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.EmailService;
import DAO.PasswordResetDAO;
import DAO.userDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author NHATHCE181222
 */
public class ForgotPasswordServlet extends HttpServlet {

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
            out.println("<title>Servlet ForgotPasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPasswordServlet at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("📌 ForgotPasswordServlet received a request!");

        String email = request.getParameter("email");
        System.out.println("📌 Received email: " + email);

        // Kiểm tra email có hợp lệ không
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Please enter your email.");
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
            return;
        }

        // Kiểm tra email có trong database không
        userDAO userDAO = new userDAO();
        Optional<User> userOpt = userDAO.getUserByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            System.out.println("📌 Found user: " + user.getFullName());

            // Tạo mã token
            String token = UUID.randomUUID().toString();
            PasswordResetDAO resetDAO = new PasswordResetDAO();
            resetDAO.createPasswordResetToken(user.getUserId(), token);

            // Tạo link đặt lại mật khẩu
            String resetLink = "http://localhost:8080/ToyStore23/resetpassword.jsp?token=" + token;
            System.out.println("📌 Reset link: " + resetLink);

            // Gửi email qua EmailService
            String subject = "Password Reset Request";
            String content = "Hello " + user.getFullName() + ",\n\n"
                    + "We received a request to reset your password. Click the link below to reset it:\n"
                    + resetLink + "\n\n"
                    + "If you did not request this, please ignore this email.\n\n"
                    + "Best regards,\nToyStore Team";

            EmailService.sendEmail(email, subject, content);

            request.setAttribute("successMessage", "A password reset link has been sent to your email.");
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Email not found.");
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
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
