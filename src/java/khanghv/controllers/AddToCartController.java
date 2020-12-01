/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanghv.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanghv.dtos.Account;
import khanghv.dtos.MoonCake;
import khanghv.dtos.OrderDetail;
import khanghv.dtos.ProductOrder;
import khanghv.models.CakeBLO;

/**
 *
 * @author USER
 */
public class AddToCartController extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession();

            Account account = (Account) session.getAttribute("DTO");
            boolean isValid = true;
            ProductOrder cart = (ProductOrder) session.getAttribute("CART");
            if (cart == null) {
                String name = request.getParameter("txtFullName");
                if (name == null) {
                    name = account.getFullname();
                }
                if (name.equals("")) {
                    isValid = false;
                    request.setAttribute("NERR", "Invalid Name!");
                }
                String phone = request.getParameter("txtPhone");
                if (!phone.matches("[0-9]{10,11}")) {
                    isValid = false;
                    request.setAttribute("PERR", "Invalid Phone Number!");
                }
                String address = request.getParameter("txtAddress");
                String priceString = request.getParameter("txtPrice");
                int price = Integer.parseInt(priceString);

                if (address.equals("")) {
                    isValid = false;
                    request.setAttribute("AERR", "Invalid Address!");
                }
                String cakeID = request.getParameter("cakeID");
                if (isValid) {
                    String orderName = "Cart of " + name;
                    int total = 0;

                    Date getDate = new Date();
                    String orderDate = new SimpleDateFormat("MM-dd-yyyy").format(getDate);
                    ProductOrder newCart = new ProductOrder(orderName, orderDate, address, phone);
                    CakeBLO cakeblo = new CakeBLO();
                    MoonCake mc = cakeblo.getDetail(cakeID);
                    OrderDetail detail = new OrderDetail(price, 1, mc);
                    System.out.println("ID"+ cakeID);
                    System.out.println("NAMEEE" + mc.getCakeName());
                    newCart.getOrderDetailList().add(detail);
                    List<OrderDetail> productList = newCart.getOrderDetailList();
                    for (int i = 0; i < productList.size(); i++) {
                        total += (productList.get(i).getPrice() * productList.get(i).getQuantity());
                    }
                    newCart.setTotal(total);
                    session.setAttribute("CART", newCart);
                } else {
                    request.setAttribute("NAME", name);
                    request.setAttribute("ADRESS", address);
                    request.setAttribute("PHONE", phone);
                }
            } else {
                String priceString = request.getParameter("txtPrice");
                System.out.println("Price" + priceString);
                int price = Integer.parseInt(priceString);
                String cakeID = request.getParameter("cakeID");
                CakeBLO cakeblo = new CakeBLO();
                System.out.println("Cake ID" + cakeID);
                MoonCake mc = cakeblo.getDetail(cakeID);
                System.out.println(mc.getCakeName());
                boolean isFound = false;
                List<OrderDetail> listOfProducts = cart.getOrderDetailList();
                for (int i = 0; i < listOfProducts.size(); i++) {
                    if (mc.getIDCake().equals(listOfProducts.get(i).getIDCake().getIDCake())) {
                        isFound = true;
                        listOfProducts.get(i).setQuantity(listOfProducts.get(i).getQuantity() + 1);
                    }
                }
                if (!isFound) {
                    OrderDetail or = new OrderDetail(price, 1, mc);
                    listOfProducts.add(or);
                }
                int total = 0;
                for (int i = 0; i < listOfProducts.size(); i++) {
                    total += (listOfProducts.get(i).getPrice() * listOfProducts.get(i).getQuantity());
                }
                cart.setTotal(total);
                session.setAttribute("CART", cart);
                request.setAttribute("OK", "ADDED!");

            }
        } catch (Exception e) {
            log("ERROR at AddTOCartController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("LoadDetailController").forward(request, response);
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
