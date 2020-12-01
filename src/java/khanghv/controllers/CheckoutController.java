/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanghv.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanghv.dtos.Account;
import khanghv.dtos.MoonCake;
import khanghv.dtos.ProductOrder;
import khanghv.models.CakeBLO;
import khanghv.models.CartBLO;

/**
 *
 * @author USER
 */
public class CheckoutController extends HttpServlet {

    private static final String ERROR = "errorupdate.jsp";
    private static final String SUCCESS = "LoadingCakeController";
    private static final String INSUFFICIENT = "viewcart.jsp";

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
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            ProductOrder cart = (ProductOrder) session.getAttribute("CART");
            CakeBLO cakeBlo = new CakeBLO();
            CartBLO blo = new CartBLO();
            String payment = "COD";
            boolean isGood = true;
            for (int i = 0; i < cart.getOrderDetailList().size(); i++) {
                if (cart.getOrderDetailList().get(i).getQuantity() > cakeBlo.getDetail(cart.getOrderDetailList().get(i).getIDCake().getIDCake()).getQuantity()) {
                    url = INSUFFICIENT;
                    isGood = false;
                    
                    request.setAttribute("INSUFFICIENT", "Sorry, we don't have enough quantity of " + cart.getOrderDetailList().get(i).getIDCake().getCakeName() + " for you, please choose another products or decrease the quantity!");
                }
            }
            if (isGood) {
                cart.setPayment(payment);
                String payStatus = "Waiting for Paying";
                cart.setPaymentStatus(payStatus);
                for (int i = 0; i < cart.getOrderDetailList().size(); i++) {
                    cart.getOrderDetailList().get(i).setOrderId(cart);
                }
                Account acc = (Account) session.getAttribute("DTO");
                if (acc != null) {
                    cart.setUserID(acc);
                } else {
                    cart.setUserID(new Account("Guest"));
                }
                Integer isAddCart = blo.addCart(cart);
                boolean[] isOk = new boolean[cart.getOrderDetailList().size()];
                if (isAddCart != 0) {
                    for (int i = 0; i < cart.getOrderDetailList().size(); i++) {
                        MoonCake cake = cakeBlo.getDetail(cart.getOrderDetailList().get(i).getIDCake().getIDCake());
                        int newQuantity = cake.getQuantity() - cart.getOrderDetailList().get(i).getQuantity();
                        cake.setQuantity(newQuantity);

                        isOk[i] = cakeBlo.updateCakeAfterCheck(cake);
                    }
                    boolean isAllOk = true;
                    for (int i = 0; i < isOk.length; i++) {
                        if (!isOk[i]) {
                            isAllOk = false;
                        }
                    }
                    if (isAllOk) {
                        url = SUCCESS;
                        session.removeAttribute("CART");
                        request.setAttribute("OK", "Check Out Completed! Your Order is " + isAddCart + "! Please sign in to track your order!");
                    } else {
                        request.setAttribute("ERROR", "Upload QUantityFailed!");
                    }

                } else {
                    request.setAttribute("ERROR", "Add Cart Failed");
                }
            }

        } catch (Exception e) {
            log("ERROR at CheckoutController: " + e.getMessage());
        } finally {
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
