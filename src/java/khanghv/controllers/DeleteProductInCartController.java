/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanghv.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanghv.dtos.OrderDetail;
import khanghv.dtos.ProductOrder;

/**
 *
 * @author USER
 */
public class DeleteProductInCartController extends HttpServlet {

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
        try {

            HttpSession session = request.getSession();
            ProductOrder cart = (ProductOrder) session.getAttribute("CART");
            if (cart != null) {
                String idProduct = request.getParameter("IDProduct");
                List<OrderDetail> list = cart.getOrderDetailList();
                for(int i = 0; i < list.size(); i++)
                {
                    if(list.get(i).getIDCake().getIDCake().equals(idProduct.trim()))
                    {
                        list.remove(i);
                    }
                }
                int total = 0;
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        total += (list.get(i).getPrice() * list.get(i).getQuantity());
                    }
                    cart.setTotal(total);
                    session.setAttribute("CART", cart);
                } else {
                    session.removeAttribute("CART");
                }

            }
        } catch (Exception e) {
            log("ERROR at DeleteProductInCartController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("viewcart.jsp").forward(request, response);
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
