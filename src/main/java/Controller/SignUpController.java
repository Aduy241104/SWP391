/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.userDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author NHATHCE181222
 */
public class SignUpController extends HttpServlet {

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
            out.println("<title>Servlet SignUpController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpController at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");

        userDAO userDAO = new userDAO();

        try {
            if (userDAO.isUsernameExists(username)) {
                request.setAttribute("errorUsername", "Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
                request.getRequestDispatcher("signUp.jsp").forward(request, response);
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Kiểm tra email đã tồn tại chưa
        if (userDAO.isEmailExists(email)) {
            request.setAttribute("errorEmail", "Email đã được sử dụng. Vui lòng nhập email khác.");
            request.getRequestDispatcher("signUp.jsp").forward(request, response);
            return;
        }
        
        // Kiểm tra mật khẩu mới có hợp lệ không
        if (!isValidPassword(password)) {
            request.setAttribute("errorNewPassword",
                    "Password must be at least 8 characters long and contain at least one number and one uppercase letter.");
            request.getRequestDispatcher("signUp.jsp").forward(request, response);
            return;
        }

        // Kiểm tra confirm password
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorConfirmPassword", "Mật khẩu nhập lại không khớp. Vui lòng thử lại.");
            request.getRequestDispatcher("signUp.jsp").forward(request, response);
            return;
        }

        try {
            // Nếu tất cả hợp lệ, tiến hành đăng ký
            if (userDAO.signUpUser(username, password, confirmPassword, email, fullName)) {
                request.setAttribute("message", "Đăng ký thành công!");
                request.getRequestDispatcher("signIn.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Đăng ký thất bại! Kiểm tra lại thông tin.");
                request.getRequestDispatcher("signUp.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
