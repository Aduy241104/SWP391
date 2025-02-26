/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.OrdersDAO;
import DAO.ProductDAO;
import DAO.userDAO;
import Model.Orders;
import Model.Product;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thaiv
 */
public class AdminManager_Search extends HttpServlet {

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
            out.println("<title>Servlet AdminManager_Search</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminManager_Search at " + request.getContextPath() + "</h1>");
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
        String query = request.getParameter("query");
        System.out.println("Query: " + query); // Kiểm tra query đầu vào

        if (query == null || query.trim().equals("")) {
            request.getRequestDispatcher("ManageProductForAdminSearchProduct.jsp").forward(request, response);
            return;
        }

        ProductDAO productDao = new ProductDAO();
        OrdersDAO ordersDao = new OrdersDAO();
        userDAO userDao = new userDAO(); // Thêm userDAO để tìm kiếm người dùng

        List<Product> productList = new ArrayList<>();
        List<Orders> ordersList = new ArrayList<>();
        List<User> userList = new ArrayList<>(); // Thêm danh sách người dùng

        int userId = -1;
        int orderId = -1;

        if (query != null && query.contains("+")) {
            // Nếu có dấu + thì tìm đơn hàng
            String[] parts = query.split("\\+");
            if (parts.length == 2) {
                try {
                    userId = Integer.parseInt(parts[0].trim());
                    orderId = Integer.parseInt(parts[1].trim());
                    System.out.println("Searching Orders - User ID: " + userId + ", Order ID: " + orderId);
                    ordersList = ordersDao.searchOrder(userId, orderId);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format for User ID or Order ID!");
                }
            }
        } else {
            // Nếu không có dấu + thì tìm sản phẩm và người dùng
            System.out.println("Searching Products and Users for: " + query);
            productList = productDao.searchProduct(query);
            userList = userDao.searchUser(query); // Gọi hàm searchUser để tìm người dùng
        }

        // Gửi dữ liệu đến JSP
        request.setAttribute("productList", productList);
        request.setAttribute("orderList", ordersList);
        request.setAttribute("userList", userList); // Thêm danh sách người dùng vào request
        request.getRequestDispatcher("ManageProductForAdminSearchProduct.jsp").forward(request, response);
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
