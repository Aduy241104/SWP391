/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.userDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author thaiv
 */
public class AdminManagerUser extends HttpServlet {

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
            out.println("<title>Servlet AdminManagerUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminManagerUser at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        userDAO userDao = new userDAO();
        if (action.equals("user")) {
            List<User> userList = userDao.getAllUser();
            request.setAttribute("userList", userList);
            request.getRequestDispatcher("ManageUsersForAdmin.jsp").forward(request, response);
        } else if (action.equals("userForDashBoard")) {
            List<User> userList = userDao.getAllUser();
            request.setAttribute("userTable", userList);
            request.getRequestDispatcher("adminDashboard.jsp?view=userTable").forward(request, response);
        } else if (action.equals("viewUserDetails")) {
            String UserID_raw = request.getParameter("id");
            try {
                int id = Integer.parseInt(UserID_raw);
                User user = userDao.getUserByIDHaveActive(id);
                boolean isActive = userDao.isUserActive(id);
                request.setAttribute("isActive", isActive);
                request.setAttribute("user", user);
                request.getRequestDispatcher("ManageUsersForAdminViewDetails.jsp").forward(request, response);
            } catch (Exception e) {
            }
        } else if (action.equals("banUser")) {
            int userId = Integer.parseInt(request.getParameter("id"));
            userDao.banUser(userId);
            response.sendRedirect("AdminManagerUser?action=user");

        } else if (action.equals("unBanUser")) {
            int userId = Integer.parseInt(request.getParameter("id"));
            userDao.unBanUser(userId);
            response.sendRedirect("AdminManagerUser?action=user");

        } else if (action.equals("viewBan")) {
            List<User> userList = userDao.getBannedUsers();
            request.setAttribute("userList", userList);
            request.getRequestDispatcher("ManageUsersForAdminBanUsers.jsp").forward(request, response);
        } else if (action.equals("addUser")) {
            response.sendRedirect("ManageUsersForAdminAddUser.jsp");
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
        String action = request.getParameter("action");
        if (action.equals("addUser")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String fullName = request.getParameter("fullName");
            String role = request.getParameter("role");
            String isActive_raw = request.getParameter("isActive");

            userDAO userDao = new userDAO();
            boolean checkUserName = userDao.checkExistUsername(username);
            boolean checkEmail = userDao.checkExistEmail(email);

            try {
                boolean isActive = Boolean.parseBoolean(isActive_raw);

                if (checkUserName || checkEmail) {
                    System.out.println("Username or Email already exists. Redirecting...");
                    request.setAttribute("nameError", checkUserName ? "Username Already Exists!" : null);
                    request.setAttribute("emailError", checkEmail ? "Email Already Exists!" : null);

                    request.getRequestDispatcher("ManageUsersForAdminAddUser.jsp").forward(request, response);
                    return;
                }

                if (userDao.addUser(username, password, email, fullName, role, isActive)) {
                    response.sendRedirect("AdminManagerUser?action=user");
                } else {
                    System.out.println("Failed to add user. Forwarding back to addUser page.");
                    request.setAttribute("generalError", "Failed to add user. Please try again.");
                    request.getRequestDispatcher("ManageUsersForAdminAddUser.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("generalError", "An error occurred: " + e.getMessage());
                request.getRequestDispatcher("ManageUsersForAdminAddUser.jsp").forward(request, response);
            }

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
