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
import javax.servlet.http.HttpSession;
import khanghv.dtos.Category;
import khanghv.dtos.MoonCake;
import khanghv.models.CakeBLO;

/**
 *
 * @author USER
 */
public class LoadingCakeController extends HttpServlet {

    private final static String MAIN = "catalog.jsp";
    private final static String ERROR = "error.jsp";

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
        String url = MAIN;
        try {
            List<MoonCake> listCake = (List<MoonCake>) request.getAttribute("LISTSEARCH");
            CakeBLO cake = new CakeBLO();
            if (listCake == null) {
                
                listCake = cake.getListCake();
            }
            int recordInOnePage = 20;
            String pageNum = request.getParameter("pageNum");
            if (pageNum == null) {
                pageNum = "1";
            }
            if (!pageNum.matches("[0-9]{1,5}")) {
                pageNum = "1";
            }
            int pageNumber = Integer.parseInt(pageNum);

            int totalRecord = listCake.size();
            int totalPage = 1;
            if (totalRecord % recordInOnePage == 0) {
                totalPage = totalRecord / recordInOnePage;
            } else {
                totalPage = (totalRecord / recordInOnePage) + 1;
            }
            List<MoonCake> listInPage = new ArrayList<>();
            List<Category> cateList = new ArrayList<>();
            for (int i = (pageNumber - 1) * recordInOnePage; i < pageNumber * recordInOnePage; i++) {
                if (i == totalRecord) {
                    break;
                } else {
                    listInPage.add(listCake.get(i));
                }
            }
            cateList = cake.getListCategory();
            HttpSession session = request.getSession();
            session.setAttribute("CATELIST", cateList);
            request.setAttribute("CAKELIST", listInPage);
            request.setAttribute("TOTALPAGE", totalPage);

        } catch (Exception e) {
            url = ERROR;
            request.setAttribute("ERROR", e.getMessage());
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
