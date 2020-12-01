/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanghv.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khanghv.dtos.OrderDetail;
import khanghv.dtos.ProductOrder;
import khanghv.models.CartBLO;

/**
 *
 * @author USER
 */
public class TrackOrderController extends HttpServlet {
    private static final String INVALID = "LoadingCakeController";
    private static final String SUCCESS = "trackorder.jsp";
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
        request.setCharacterEncoding("UTF-8");
        String url = INVALID;
        try {
            String orderID = request.getParameter("txtOrder");
            if(orderID.equals(""))
            {
                request.setAttribute("NOORDER", "Please input Order ID!");
            }
            else
            {   
                url = SUCCESS;
                CartBLO cartBlo = new CartBLO();
                ProductOrder cart = cartBlo.getDetail(Integer.parseInt(orderID.trim()));
                List<OrderDetail> listDetail = cartBlo.getDetailByOrder(cart);
                
                cart.setOrderDetailList(listDetail);
                request.setAttribute("CART", cart);
                request.setAttribute("DETAIL", listDetail);
            }
        } catch (Exception e) {
            log("ERROR at TrackOrderController: " + e.getMessage());
        }finally
        {
            request.getRequestDispatcher(url).forward(request, response);
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
