/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.OrdersDAO;
import DAO.ProductDAO;
import Model.Orders;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author thaiv
 */
public class StaffManagerOrders extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StaffManagerOrders</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StaffManagerOrders at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        System.out.println(action);
        if (action.equals("orders")) {
            OrdersDAO ordersDao = new OrdersDAO();
            List<Orders> ordersList = ordersDao.getAllOrders();
            request.setAttribute("OrdersList", ordersList);
            request.getRequestDispatcher("ManagerOrdersForStaff.jsp").forward(request, response);
        } else if (action.equals("BackToStaffDashboard")) {
            request.getSession().removeAttribute("totalAmount");
            request.getSession().removeAttribute("countOrders");
            request.getSession().removeAttribute("count");
            request.getRequestDispatcher("staffFDashboard.jsp?view=null").forward(request, response);
        } else if (action.equals("count")) {
            ProductDAO productDao = new ProductDAO();
            List<Product> productList = productDao.getProductList();
            OrdersDAO ordersDao = new OrdersDAO();
            List<Orders> ordersList = ordersDao.getAllOrders();
            Double totalAmount = ordersDao.getTotalAmountOfDeliveredOrders();
            int countOrders = ordersList.size();
            int count = productList.size();
            DecimalFormat df = new DecimalFormat("#,###");
            String formattedRevenue = df.format(totalAmount);
            request.getSession().setAttribute("totalAmount", formattedRevenue);
            request.getSession().setAttribute("countOrders", countOrders);
            request.getSession().setAttribute("count", count);
            request.getRequestDispatcher("staffFDashboard.jsp?view=null").forward(request, response);
        } else if (action.equals("ordersForDashBoard")) {
            OrdersDAO ordersDao = new OrdersDAO();
            List<Orders> OrdersList = ordersDao.getAllOrders();
            request.setAttribute("OrdersList", OrdersList);
            request.getRequestDispatcher("staffFDashboard.jsp?view=orderTable").forward(request, response);
        } else if (action.equals("productForDashBoard")) {
            ProductDAO pDao = new ProductDAO();
            List<Product> productList = pDao.getProductList();
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("staffFDashboard.jsp?view=productTable").forward(request, response);
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
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for staff to manage orders";
    }
}
