/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanghv.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanghv.dtos.OrderDetail;
import khanghv.dtos.ProductOrder;
import khanghv.models.CakeBLO;

/**
 *
 * @author USER
 */
public class UpdateProductInCartController extends HttpServlet {

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
                String quantity = request.getParameter("txtQuantity");
                System.out.println("Quantity: " + quantity);
                System.out.println("ID: " + idProduct);

                if (quantity.equals("")) {
                    quantity = "1";
                }
                if (!quantity.matches("[0-9]{1,5}")) {
                    quantity = "1";
                }
                List<OrderDetail> list = cart.getOrderDetailList();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getIDCake().getIDCake().equals(idProduct.trim())) {
                        System.out.println("ID: " + list.get(i).getIDCake().getIDCake());
                        list.get(i).setQuantity(Integer.parseInt(quantity));
                    }
                }
                boolean isGood = true;
                CakeBLO cakeBlo = new CakeBLO();
                for (int i = 0; i < cart.getOrderDetailList().size(); i++) {
                    if (Integer.parseInt(quantity) > cakeBlo.getDetail(cart.getOrderDetailList().get(i).getIDCake().getIDCake()).getQuantity()) {
                        isGood = false;
                        request.setAttribute("INSUFFICIENT", "Sorry, we don't have enough quantity of " + cart.getOrderDetailList().get(i).getIDCake().getCakeName() + " for you, please choose another products or decrease the quantity!");
                    }
                }
                if (isGood) {
                    int total = 0;
                    for (int i = 0; i < list.size(); i++) {
                        total += (list.get(i).getPrice() * list.get(i).getQuantity());
                        System.out.println("Price: " + list.get(i).getPrice() + " Quantity: " + list.get(i).getQuantity());
                    }
                    cart.setTotal(total);
                    session.setAttribute("CART", cart);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at UpdateCart: " + e.getMessage());
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
