/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.OrderDetailDAO;
import DAO.OrderRecordsDAO;
import DAO.OrdersDAO;
import Model.OrderDetail;
import Model.OrderRecords;
import Model.Orders;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thaiv
 */
public class AdminManagerOrders extends HttpServlet {

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
            out.println("<title>Servlet AdminManagerOrders</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminManagerOrders at " + request.getContextPath() + "</h1>");
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
        String action = (String) request.getParameter("action");
        String orderID = request.getParameter("id");
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        User user = (User) session.getAttribute("user");
        if (action.equals("FunctionsOfOrderManagement")) {
            String func = request.getParameter("func");
            OrdersDAO ordersdao = new OrdersDAO();
            List<Orders> ordersList = ordersdao.getOrdersByStatus(func);

            switch (func) {
                case "Pending":
                    request.setAttribute("ordersList", ordersList);
                    request.getRequestDispatcher("ManagerOrdersForAdminPendingView.jsp").forward(request, response);
                    break;
                case "Shipping":
                    request.setAttribute("ordersList", ordersList);
                    request.getRequestDispatcher("ManagerOrdersForAdminShippingView.jsp").forward(request, response);
                    break;
                case "Delivered":
                    request.setAttribute("ordersList", ordersList);
                    request.getRequestDispatcher("ManagerOrdersForAdminDeliveredView.jsp").forward(request, response);
                    break;
                case "Reject":
                    request.setAttribute("ordersList", ordersList);
                    request.getRequestDispatcher("ManagerOrdersForAdminRejectedView.jsp").forward(request, response);
                    break;
                case "Cancelled":
                    request.setAttribute("ordersList", ordersList);
                    request.getRequestDispatcher("ManagerOrdersForAdminCancelView.jsp").forward(request, response);
                    break;
                case "viewDetails":
                    request.setAttribute("ordersList", ordersList);
                    request.getRequestDispatcher("ManagerOrdersForAdminViewOrderDetails.jsp").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        } else if (action.equals("accept")) {
            OrdersDAO ordersDao = new OrdersDAO();
            try {
                int OrderID = Integer.parseInt(orderID);
                if ("staff".equals(role)) {
                    OrderRecordsDAO orderRecordsDao = new OrderRecordsDAO();
                    int staffID = orderRecordsDao.getStaffIDByUserID(user.getUserId());
                    OrderRecords orderRecord = new OrderRecords(OrderID, staffID, action);
                    boolean isStaff = orderRecordsDao.AddStaffAction(orderRecord);
                }
                ordersDao.updateOrderStatusToShipping(OrderID);
                request.getRequestDispatcher("AdminManagerOrders?action=FunctionsOfOrderManagement&func=Pending").forward(request, response);
            } catch (NumberFormatException e) {

            }
        } else if (action.equals("reject")) {
            OrdersDAO ordersDao = new OrdersDAO();
            try {
                int OrderID = Integer.parseInt(orderID);
                if ("staff".equals(role)) {
                    OrderRecordsDAO orderRecordsDao = new OrderRecordsDAO();
                    int staffID = orderRecordsDao.getStaffIDByUserID(user.getUserId());
                    OrderRecords orderRecord = new OrderRecords(OrderID, staffID, action);
                    boolean isStaff = orderRecordsDao.AddStaffAction(orderRecord);
                }
                ordersDao.cancelPendingOrder(OrderID);
                request.getRequestDispatcher("AdminManagerOrders?action=FunctionsOfOrderManagement&func=Pending").forward(request, response);
            } catch (NumberFormatException e) {

            }
        } else if (action.equals("cancel")) {
            OrdersDAO ordersDao = new OrdersDAO();
            try {
                int OrderID = Integer.parseInt(orderID);
                if ("staff".equals(role)) {
                    OrderRecordsDAO orderRecordsDao = new OrderRecordsDAO();
                    int staffID = orderRecordsDao.getStaffIDByUserID(user.getUserId());
                    OrderRecords orderRecord = new OrderRecords(OrderID, staffID, action);
                    boolean isStaff = orderRecordsDao.AddStaffAction(orderRecord);
                }
                ordersDao.cancelShippingOrder(OrderID);
                request.getRequestDispatcher("AdminManagerOrders?action=FunctionsOfOrderManagement&func=Shipping").forward(request, response);
            } catch (NumberFormatException e) {

            }
        } else if (action.equals("Delivered")) {
            OrdersDAO ordersDao = new OrdersDAO();
            try {
                int OrderID = Integer.parseInt(orderID);
                if ("staff".equals(role)) {
                    OrderRecordsDAO orderRecordsDao = new OrderRecordsDAO();
                    int staffID = orderRecordsDao.getStaffIDByUserID(user.getUserId());
                    OrderRecords orderRecord = new OrderRecords(OrderID, staffID, action);
                    boolean isStaff = orderRecordsDao.AddStaffAction(orderRecord);
                }
                ordersDao.updateOrderStatusToDelivered(OrderID);
                request.getRequestDispatcher("AdminManagerOrders?action=FunctionsOfOrderManagement&func=Shipping").forward(request, response);
            } catch (NumberFormatException e) {

            }
        } else if (action.equals("restore")) {
            OrdersDAO ordersDao = new OrdersDAO();
            try {
                int OrderID = Integer.parseInt(orderID);
                if ("staff".equals(role)) {
                    OrderRecordsDAO orderRecordsDao = new OrderRecordsDAO();
                    int staffID = orderRecordsDao.getStaffIDByUserID(user.getUserId());
                    OrderRecords orderRecord = new OrderRecords(OrderID, staffID, action);
                    boolean isStaff = orderRecordsDao.AddStaffAction(orderRecord);
                }
                ordersDao.restoreCancelledOrder(OrderID);
                request.getRequestDispatcher("AdminManagerOrders?action=FunctionsOfOrderManagement&func=Cancelled").forward(request, response);
            } catch (NumberFormatException e) {

            }
        } else if (action.equals("delete")) {
            OrdersDAO ordersDao = new OrdersDAO();
            try {
                int OrderID = Integer.parseInt(orderID);
                if ("staff".equals(role)) {
                    OrderRecordsDAO orderRecordsDao = new OrderRecordsDAO();
                    int staffID = orderRecordsDao.getStaffIDByUserID(user.getUserId());
                    OrderRecords orderRecord = new OrderRecords(OrderID, staffID, action);
                    boolean isStaff = orderRecordsDao.AddStaffAction(orderRecord);
                }
                ordersDao.deleteCancelledOrder(OrderID);
                request.getRequestDispatcher("AdminManagerOrders?action=FunctionsOfOrderManagement&func=Cancelled").forward(request, response);
            } catch (NumberFormatException e) {

            }
        } else if (action.equals("viewDetails")) {
            OrderDetailDAO orderDetailsDao = new OrderDetailDAO();
            String orderId = request.getParameter("id");
            double total = 0;
            OrderDetail orderDetail = null;
            String formattedRevenue = null;
            try {
                int OrderID = Integer.parseInt(orderId);
                List<OrderDetail> orderList = orderDetailsDao.getOrderDetailById(OrderID);
                String orderStatus = "0";
                if (orderList.size() != 0) {
                    DecimalFormat df = new DecimalFormat("#,###");
                    
                    total = orderList.get(0).getTotalAmount();
                    formattedRevenue = df.format(total);
                    orderDetail = orderList.get(0);
                    orderStatus = orderDetailsDao.getOrderDetailById(OrderID).get(0).getOrderStatus();
                }
                List<OrderDetail> mergedOrderList = new ArrayList<>();  

                for (int i = 0; i < orderList.size(); i++) {
                    OrderDetail currentOrder = orderList.get(i);
                    boolean foundDuplicate = false;

                    for (int j = 0; j < mergedOrderList.size(); j++) {
                        OrderDetail mergedOrder = mergedOrderList.get(j);
                        if (mergedOrder.getProductName().equals(currentOrder.getProductName())) {
                            mergedOrder.setQuantity(mergedOrder.getQuantity() + currentOrder.getQuantity());
                            foundDuplicate = true;
                            break;
                        }
                    }

                    if (!foundDuplicate) {
                        mergedOrderList.add(currentOrder);
                    }
                }

                request.setAttribute("orderDetail", orderDetail);
                request.setAttribute("total", formattedRevenue);
                request.setAttribute("orderDetails", mergedOrderList);
                request.setAttribute("orderStatus", orderStatus);
                request.getRequestDispatcher("AdminManagerOrders?action=FunctionsOfOrderManagement&func=viewDetails").forward(request, response);
            } catch (NumberFormatException e) {

            }
        } else if (action.equals("ordersForDashBoard")) {
            OrdersDAO ordersDao = new OrdersDAO();
            List<Orders> OrdersList = ordersDao.getAllOrders();
            request.setAttribute("OrdersList", OrdersList);
            request.getRequestDispatcher("adminDashboard.jsp?view=orderTable").forward(request, response);
        } else if (action.equals("order")) {
            OrdersDAO orderDao = new OrdersDAO();
            List<Orders> orderList = orderDao.getAllOrders();
            request.setAttribute("orderList", orderList);
            request.getRequestDispatcher("ManagerOrdersForAdmin.jsp").forward(request, response);
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
