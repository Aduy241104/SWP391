/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.orderDAO;
import DAO.OrderDetailDAO;
import DAO.OrdersDAO;
import DAO.ProductDAO;
import Model.Orders;
import Model.OrderDetail;
import Model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
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
public class CancelOrderController extends HttpServlet {

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
            out.println("<title>Servlet CancelOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CancelOrderController at " + request.getContextPath() + "</h1>");
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
            String orderIDParam = request.getParameter("id");

            if ("cancelOrder".equals(action) && orderIDParam != null) {
                int orderID = Integer.parseInt(orderIDParam);

                // 1. Lấy danh sách sản phẩm và số lượng từ đơn hàng
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                List<OrderDetail> details = orderDetailDAO.getProductQuantityInOrder(orderID);

                // 2. Tăng lại số lượng trong bảng Products
                ProductDAO productDAO = new ProductDAO();
                for (OrderDetail od : details) {
                    productDAO.increaseProductStock(od.getProductID(), od.getQuantity());
                }

                // 3. Huỷ đơn hàng
                orderDAO ordersDAO = new orderDAO();
                boolean isDeleted = ordersDAO.CancelOrderById(orderID);

                if (isDeleted) {
                    request.setAttribute("successMessage", "Đơn hàng đã được huỷ và kho đã được cập nhật.");
                } else {
                    request.setAttribute("errorMessage", "Huỷ đơn hàng thất bại.");
                }

                response.sendRedirect("ViewOrderListController");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi xử lý yêu cầu!");
            request.getRequestDispatcher("orderList.jsp").forward(request, response);
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
