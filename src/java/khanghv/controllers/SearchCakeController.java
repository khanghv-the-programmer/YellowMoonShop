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
import khanghv.dtos.Category;
import khanghv.dtos.MoonCake;
import khanghv.models.CakeBLO;

/**
 *
 * @author USER
 */
public class SearchCakeController extends HttpServlet {

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
            String how = request.getParameter("how");
            CakeBLO cakeBlo = new CakeBLO();
            if (how.equals("name")) {
                String searchName = request.getParameter("txtSearch");
                if (searchName.equals("")) {
                    request.setAttribute("EMPTY", "Please Input something to search!");
                } else {

                    List<MoonCake> getList = cakeBlo.getListCakeByName(searchName);
                    request.setAttribute("LISTSEARCH", getList);
                }
            } else if (how.equals("category")) {
                String category = request.getParameter("Category");
                Category cate = new Category(category);
                List<MoonCake> getListByCate = cakeBlo.getListCakeByCategory(cate);
                request.setAttribute("LISTSEARCH", getListByCate);
            } else if (how.equals("price")) {
                String range = request.getParameter("txtPrice");
                String min = request.getParameter("txtMin");
                String max = request.getParameter("txtMax");
                int limitLeft = Integer.parseInt(min);
                int limitRight = Integer.parseInt(max);
                if (limitLeft >= limitRight) {
                    request.setAttribute("NOLOGIC", "Min value cannot be higher than Max value!");
                } else {
                    List<MoonCake> listP = cakeBlo.getListPrice(limitLeft, limitRight);
                    request.setAttribute("LISTSEARCH", listP);
                    request.setAttribute("RANGE", "Cakes from " + limitLeft + " to " + limitRight + " VND:");
                }

            } else {
                request.setAttribute("INVALID", "Invalid method search!");
            }

        } catch (Exception e) {
            log("ERROR at SearchCakeController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("LoadingCakeController").forward(request, response);

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
