/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import Model.Stock;
import DAO.StockDAO;
import DAO.userDAO;
import Model.Product;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author thaiv
 */
public class AdminManageStock extends HttpServlet {

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
            out.println("<title>Servlet AdminManageStock</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminManageStock at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
        StockDAO stockDAO = new StockDAO();

        if ("Import".equals(action)) {
            List<Stock> importStockList = stockDAO.getImportStock();
            double total = stockDAO.calculateTotalImportValue();
            request.setAttribute("total", total);
            request.setAttribute("list", importStockList);
            request.getRequestDispatcher("ManageProductForAdminStockOrder.jsp").forward(request, response);
        } else if ("Export".equals(action)) {
            List<Stock> exportStockList = stockDAO.getExportStock();
            double total = stockDAO.calculateTotalExportValue();
            request.setAttribute("total", total);
            request.setAttribute("list", exportStockList);
            request.getRequestDispatcher("ManageProductForAdminStockOrder.jsp").forward(request, response);
        } else if (action.equals("ViewDetail")) {
            Stock stock = stockDAO.getStockById(Integer.parseInt(id));
            ProductDAO productDao = new ProductDAO();
            Product product = productDao.getProductByID(stock.getProductID());
            userDAO userDao = new userDAO();
            User user = userDao.getUserById(stock.getUserID());
            request.setAttribute("user", user);
            request.setAttribute("product", product);
            request.setAttribute("stock", stock);
            request.getRequestDispatcher("ManageProductForAdminStockOrderDetail.jsp").forward(request, response);
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
        StockDAO stockDAO = new StockDAO();
        ProductDAO productDAO = new ProductDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        Integer userId = user.getUserId();

        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("newStock"));
            int currentStock = Integer.parseInt(request.getParameter("stock"));

            if ("ImportStock".equals(action)) {
                int newStock = currentStock + quantity;
                boolean isUpdated = productDAO.updateStock(productId, newStock);
                if (isUpdated) {
                    stockDAO.addStockTransaction(productId, userId, quantity, "import");
                    response.sendRedirect("AdminManagerProducts?action=managerStock");
                } else {
                    request.setAttribute("error", "Failed to update stock.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } else if ("ExportStock".equals(action)) {
                if (quantity > currentStock) {
                    response.sendRedirect("AdminManagerProducts?action=managerStockError");
                    return;
                }
                int newStock = currentStock - quantity;
                boolean isUpdated = productDAO.updateStock(productId, newStock);
                if (isUpdated) {
                    stockDAO.addStockTransaction(productId, userId, quantity, "export");
                    if (newStock == 0) {
                        productDAO.deleteProduct(productId);
                    }
                    response.sendRedirect("AdminManagerProducts?action=managerStock");
                } else {
                    request.setAttribute("error", "Failed to update stock.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid input format.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
