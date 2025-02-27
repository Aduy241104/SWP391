/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.StaffDAO;
import Model.Staff;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
            request.setAttribute("staffList", staffList);
            request.getRequestDispatcher("ManageStaffForAdmin.jsp").forward(request, response);
        } else if (action.equals("staffForDashBoard")) { 
            List<Staff> staffList = StaffDAO.getAllStaffs();
            request.setAttribute("staffList", staffList);
            request.getRequestDispatcher("adminDashboard.jsp?view=userTable").forward(request, response);
//        } else if (action.equals("viewUserDetails")) {
//            String StaffID_raw = request.getParameter("id");
//            try { 
//                int id = Integer.parseInt(StaffID_raw);
//                Staff staff = StaffDAO.getUserByIDHaveActive(id);
//                boolean isActive = userDao.isUserActive(id);
//                request.setAttribute("isActive", isActive);
//                request.setAttribute("user", user);
//                request.getRequestDispatcher("ManageUsersForAdminViewDetails.jsp").forward(request, response);
//            } catch (Exception e) {
//            }
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
