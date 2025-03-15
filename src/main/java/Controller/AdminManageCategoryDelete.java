/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyen Phu Quy CE180789
 */
public class AdminManageCategoryDelete extends HttpServlet {

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
            out.println("<title>Servlet AdminManageCategoryDelete</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminManageCategoryDelete at " + request.getContextPath() + "</h1>");
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
        try {
            String action = request.getParameter("action");
            String categoryIdParam = request.getParameter("categoryID");

            if ("delete".equals(action) && categoryIdParam != null) {
                try {
                    int categoryId = Integer.parseInt(categoryIdParam);
                    CategoryDAO categoryDAO = new CategoryDAO();
                    categoryDAO.deleteCategoryById(categoryId);

                    // ✅ Chuyển hướng về AdminManageCategory servlet sau khi xóa thành công
                    response.sendRedirect("AdminManageCategory");
                    return;
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "Lỗi: ID danh mục không hợp lệ!");
                } catch (Exception e) {
                    request.setAttribute("errorMessage", "Lỗi khi xóa danh mục: " + e.getMessage());
                }
            } else {
                request.setAttribute("errorMessage", "Lỗi: Thao tác không hợp lệ!");
            }

            // Nếu có lỗi, forward về AdminManageCategory servlet
            request.getRequestDispatcher("AdminManageCategory").forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
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
