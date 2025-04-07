/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import Model.Category;
import Model.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DUY
 */
public class FilterProductController extends HttpServlet {

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
        String age = request.getParameter("age");
        System.out.println(age);
        String[] categoryParams = request.getParameterValues("category");
        String[] priceParams = request.getParameterValues("price");

        // Chuyển danh mục và giá thành danh sách số
        List<Integer> categories = new ArrayList<>();
        if (categoryParams != null) {
            for (String c : categoryParams) {
                categories.add(Integer.parseInt(c));
            }
        }

        List<Integer> prices = new ArrayList<>();
        if (priceParams != null) {
            for (String p : priceParams) {
                prices.add(Integer.parseInt(p));
            }
        }
        ProductDAO prd = new ProductDAO();
        CategoryDAO ctd = new CategoryDAO();
        
        List<Category> categoryList = ctd.getAllCategory();
        List<Product> products = prd.getFilteredProducts(age, categories, prices);
        
        request.setAttribute("productList", products);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("selectedAge", age);
        request.setAttribute("selectedCategories", categories);
        request.setAttribute("selectedPrices", prices);
        
        RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/SearchProduct.jsp");
        rd.forward(request, response);
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
