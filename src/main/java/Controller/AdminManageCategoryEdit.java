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
public class AdminManageCategoryEdit extends HttpServlet {

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
            out.println("<title>Servlet AdminManageCategoryEdit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminManageCategoryEdit at " + request.getContextPath() + "</h1>");
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
        CategoryDAO categoryDAO = new CategoryDAO();
        String action = request.getParameter("action");

        try {
            if ("editCategory".equals(action)) {
                String categoryIdParam = request.getParameter("categoryID");

                // Kiểm tra nếu categoryID bị null hoặc không hợp lệ
                if (categoryIdParam == null || categoryIdParam.isEmpty()) {
                    System.out.println("Error: categoryID is missing!");
                    response.sendRedirect("ViewCategoryForAdmin.jsp");
                    return;
                }

                int categoryId = Integer.parseInt(categoryIdParam);
                System.out.println("Category ID received: " + categoryId);

                if ("POST".equalsIgnoreCase(request.getMethod())) {
                    String categoryName = request.getParameter("categoryName");
                    String description = request.getParameter("description");

                    System.out.println("Received categoryName: " + categoryName);
                    System.out.println("Received description: " + description);

                    // Kiểm tra nếu dữ liệu nhập vào bị rỗng
                    if (categoryName == null || categoryName.trim().isEmpty() || description == null || description.trim().isEmpty()) {
                        System.out.println("Error: Category name or description is empty!");
                        request.setAttribute("errorMessage", "Tên danh mục và mô tả không được để trống!");
                        request.getRequestDispatcher("ViewCategoryForAdminEdit.jsp").forward(request, response);
                        return;
                    }

                    // Gọi DAO để cập nhật danh mục
                    categoryDAO.editCategoryById(categoryId, categoryName, description);

                    // Chuyển hướng về trang danh sách danh mục sau khi cập nhật
                    response.sendRedirect("AdminManageCategory"); // Điều hướng lại servlet để lấy danh sách mới
                    return;
                } else {
                    // Nếu chưa gửi dữ liệu, chỉ lấy danh mục hiển thị
                    Category category = categoryDAO.getCategoryById(categoryId);
                    if (category != null) {
                        request.setAttribute("category", category);
                        request.getRequestDispatcher("ViewCategoryForAdminEdit.jsp").forward(request, response);
                        return;
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid category ID: " + e.getMessage());
            response.sendRedirect("ViewCategoryForAdmin.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật danh mục!");
            request.getRequestDispatcher("ViewCategoryForAdminEdit.jsp").forward(request, response);
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
        try {
            // Lấy dữ liệu từ form
            String categoryIdParam = request.getParameter("categoryID");
            String categoryName = request.getParameter("categoryName");
            String description = request.getParameter("description");

            // Kiểm tra nếu categoryID bị null hoặc không hợp lệ
            if (categoryIdParam == null || categoryIdParam.isEmpty()
                    || categoryName == null || categoryName.trim().isEmpty()
                    || description == null || description.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin!");
                request.getRequestDispatcher("ViewCategoryForAdminEdit.jsp").forward(request, response);
                return;
            }

            int categoryId = Integer.parseInt(categoryIdParam);
            CategoryDAO categoryDAO = new CategoryDAO();

            // Cập nhật danh mục
            categoryDAO.editCategoryById(categoryId, categoryName, description);

            // Chuyển hướng về trang danh sách danh mục sau khi cập nhật
            response.sendRedirect("AdminManageCategory?action=viewAll");

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "ID danh mục không hợp lệ!");
            request.getRequestDispatcher("ViewCategoryForAdminEdit.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi khi cập nhật danh mục!");
            request.getRequestDispatcher("ViewCategoryForAdminEdit.jsp").forward(request, response);
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
