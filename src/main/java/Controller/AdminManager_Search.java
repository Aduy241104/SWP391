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
import jakarta.servlet.http.HttpSession;
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
        String page = request.getParameter("page");
        System.out.println(page);

        if (query == null || query.trim().isEmpty()) {
            request.getRequestDispatcher("ManageProductForAdminSearchProduct.jsp").forward(request, response);
            return;
        }

        ProductDAO productDao = new ProductDAO();
        OrdersDAO ordersDao = new OrdersDAO();
        userDAO userDao = new userDAO();

        List<Product> productList = new ArrayList<>();
        List<Orders> ordersList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        int orderId = 0;
        try {
            orderId = Integer.parseInt(query);
        } catch (NumberFormatException e) {
            orderId = 0;
        }
        boolean isNumber = query.matches("\\d+");
        if (page.equals("order")) {
            if (isNumber) {
                System.out.println("Searching Orders for ID: " + orderId);
                ordersList = ordersDao.searchOrder(orderId);
                request.setAttribute("orderList", ordersList);
                request.getRequestDispatcher("ManagerOrdersForAdminSearchOrder.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("ManagerOrdersForAdminSearchOrder.jsp").forward(request, response);
            }
        } else if (page.equals("user")) {
            userList = userDao.searchUser(query);
            request.setAttribute("userList", userList);
            request.getRequestDispatcher("ManageUsersForAdminSearchUser.jsp").forward(request, response);

        } else if (page.equals("product")) {
            System.out.println("Searching Products and Users for: " + query);
            productList = productDao.searchProduct(query);
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("ManageProductForAdminSearchProduct.jsp").forward(request, response);
        } else if (page.equals("stock")) {
            System.out.println("Searching Products and Users for: " + query);
            HttpSession session = request.getSession();
            session.setAttribute("query", query);
            productList = productDao.searchProduct(query);
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("ManageProductForAdminSearchForStockUpdate.jsp").forward(request, response);
        } else if (page.equals("searchAll")) {
            if (isNumber) {
                ordersList = ordersDao.searchOrder(orderId);
                request.setAttribute("orderList", ordersList);
            }
            userList = userDao.searchUser(query);
            request.setAttribute("userList", userList);
            productList = productDao.searchProduct(query);
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("ManageAdminSearchAll.jsp").forward(request, response);

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
