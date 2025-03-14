/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.StaffDAO;
import DAO.userDAO;
import Model.Staff;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author NHATHCE181222
 */
public class AdminManageStaff extends HttpServlet {

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
            out.println("<title>Servlet AdminManageStaff</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminManageStaff at " + request.getContextPath() + "</h1>");
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
        StaffDAO StaffDAO = new StaffDAO();
        if (action.equals("staff")) {   
            List<Staff> staffList = StaffDAO.getAllStaffs();
            List<Staff> activeUser = new ArrayList<>();
            for (Staff staff : staffList) {
                if (staff.isIsActive()) {
                    activeUser.add(staff);
                }
            }
            request.setAttribute("staffList", activeUser);
            request.getRequestDispatcher("ManageStaffForAdmin.jsp").forward(request, response);
        } else if (action.equals("staffForDashBoard")) {
            List<Staff> staffList = StaffDAO.getAllStaffs();
            request.setAttribute("staffTable", staffList);
            request.getRequestDispatcher("adminDashboard.jsp?view=staffTable").forward(request, response);
        } else if (action.equals("addStaff")) {
            response.sendRedirect("ManageStaffsForAdminAddStaff.jsp");
        } else if (action.equals("banStaff")) {
            int staffID = Integer.parseInt(request.getParameter("id"));
            StaffDAO.banStaff(staffID);
            response.sendRedirect("AdminManageStaff?action=staff");
        } else if (action.equals("unBanStaff")) {
            int staffID = Integer.parseInt(request.getParameter("id"));
            StaffDAO.unBanStaff(staffID);
            response.sendRedirect("AdminManageStaff?action=staff");
        } else if (action.equals("viewStaffDetails")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Staff staff = StaffDAO.getStaffById(id);
            boolean isActive = StaffDAO.isStaffActive(id);
            int userID = StaffDAO.getUserIDByStaffID(id);
            userDAO userDao = new userDAO();
            User user = userDao.getUserById(userID);
            request.setAttribute("user", user);
            request.setAttribute("isActive", isActive);
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("ManageStaffsForAdminViewDetails.jsp").forward(request, response);
        } else if(action.equals("viewBan")){
             List<Staff> staffList = StaffDAO.getAllStaffs();
            List<Staff> activeUser = new ArrayList<>();
            for (Staff staff : staffList) {
                if (!staff.isIsActive()) {
                    activeUser.add(staff);
                }
            }
            request.setAttribute("staffList", activeUser);
            request.getRequestDispatcher("ManageStaffsForAdminViewBanStaff.jsp").forward(request, response);
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

        if ("addStaff".equals(action)) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            boolean isActive = Boolean.parseBoolean(request.getParameter("isActive"));

            StaffDAO staffDAO = new StaffDAO();
            boolean checkUserName = staffDAO.checkExistUsername(username);
            boolean checkEmail = staffDAO.checkExistEmail(email);

            if (checkUserName || checkEmail) {
                request.setAttribute("nameError", checkUserName ? "Username Already Exists!" : null);
                request.setAttribute("emailError", checkEmail ? "Email Already Exists!" : null);
                request.getRequestDispatcher("ManageStaffsForAdminAddStaff.jsp").forward(request, response);
                return;
            }

            boolean success = staffDAO.addStaff(username, email, fullName, password, isActive);
            if (success) {
                response.sendRedirect("AdminManageStaff?action=staff");
            } else {
                request.setAttribute("generalError", "Failed to add staff. Please try again.");
                request.getRequestDispatcher("ManageStaffsForAdminAddStaff.jsp").forward(request, response);
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
