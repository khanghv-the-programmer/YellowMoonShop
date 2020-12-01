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
import khanghv.models.AccountBLO;

/**
 *
 * @author USER
 */
public class LoginController extends HttpServlet {

    private static final String ADMIN = "LoadingCakeController";
    private static final String USER = "LoadingCakeController";
    private static final String INVALID = "index.jsp";
    private static final String ERROR = "error.jsp";

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
        String url = ERROR;
        try {
            boolean isValid = true;

            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            if (username.trim().equals("")) {
                isValid = false;
                request.setAttribute("AERR", "Please input Username!");
            }
            if (password.trim().equals("")) {
                isValid = false;
                request.setAttribute("PERR", "Please input Password!");
            }
            if (!isValid) {
                url = INVALID;
                request.setAttribute("USERNAME", username);
            } else {
                AccountBLO blo = new AccountBLO();
                Account acc = blo.checkLogin(username, password);
                if (acc == null) {
                    request.setAttribute("ERROR", "Invalid Username Or Password");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("DTO", acc);
                    session.setAttribute("ROLE", acc.getRoleID().getRoleName());
                    if (acc.getRoleID().getRoleID().equals("1")) {
                        if (session.getAttribute("CART") != null) {
                            session.removeAttribute("CART");
                        }
                        url = USER;
                    } else if (acc.getRoleID().getRoleID().equals("2")) {
                        url = ADMIN;
                        if (session.getAttribute("CART") != null) {
                            session.removeAttribute("CART");
                        }

                    } else {
                        request.setAttribute("ERROR", "Invalid role!");
                    }
                }
            }
        } catch (Exception e) {
            log("ERROR at LoginController: " + e.getMessage());
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
