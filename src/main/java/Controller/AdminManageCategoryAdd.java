/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CategoryDAO;
import Model.Category;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyen Phu Quy CE180789
 */
public class AdminManageCategoryAdd extends HttpServlet {

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
            out.println("<title>Servlet AdminManageCategoryAdd</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminManageCategoryAdd at " + request.getContextPath() + "</h1>");
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
        String categoryName = request.getParameter("categoryName");
        String description = request.getParameter("description");

        if (categoryName == null || categoryName.trim().isEmpty()) {
            request.setAttribute("error", "Category name is required.");
            request.getRequestDispatcher("ViewCategoryForAdmin").forward(request, response);
            return;
        }

        CategoryDAO categoryDAO = new CategoryDAO();

// Kiểm tra trùng tên
        if (categoryDAO.isCategoryNameExists(categoryName)) {
            request.setAttribute("error", "Invalid category name");
            request.setAttribute("categoryName", categoryName); // để giữ lại dữ liệu người dùng đã nhập
            request.setAttribute("description", description);
            request.getRequestDispatcher("ViewCategoryForAdminAdd.jsp").forward(request, response);
            return;
        }

// Nếu không trùng thì thêm mới
        categoryDAO.addCategory(categoryName, description);
        response.sendRedirect("AdminManageCategory");
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
