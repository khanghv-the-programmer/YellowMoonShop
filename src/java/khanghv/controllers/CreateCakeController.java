/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanghv.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanghv.dtos.Account;
import khanghv.dtos.Category;
import khanghv.dtos.MoonCake;
import khanghv.models.CakeBLO;

/**
 *
 * @author USER
 */
public class CreateCakeController extends HttpServlet {

    private static final String ERROR = "errorupdate.jsp";
    private static final String SUCCESS = "LoadingCakeController";
    private static final String INVALID = "createcake.jsp";

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
        String url = ERROR;
        try {
            boolean isValid = true;
            CakeBLO blo = new CakeBLO();
            int lastId = blo.getLastId();
            String cakeID = Integer.toString(lastId + 1);
            String cakeName = request.getParameter("txtName").trim();
            if (cakeName.equals("")) {
                isValid = false;
                request.setAttribute("NERR", "Invalid Cake Name!");
            }
            String priceInString = request.getParameter("txtPrice");
            if (!priceInString.matches("[0-9]{1,10}")) {
                isValid = false;
                request.setAttribute("PERR", "Invalid Price!");
            }
            String img = request.getParameter("txtImg");
            if (img.equals("")) {
                img = "https://image-us.eva.vn/upload/3-2019/images/2019-09-02/10-loai-banh-trung-thu-doc-la-cua-trung-quoc-1-1567427101-337-width650height650.jpg";
            }
            String desc = request.getParameter("txtDesc");
            if (desc.equals("")) {
                isValid = false;
                request.setAttribute("DERR", "Invalid Description!");
            }
            String status = "active";
            String quantityInString = request.getParameter("txtQuantity");
            if (!quantityInString.matches("[0-9]{1,10}")) {
                isValid = false;
                request.setAttribute("QERR", "Invalid quantity!");
            }
            String createDate = request.getParameter("txtCreate");
            String exDate = request.getParameter("txtExpired");

            if (createDate.equals("") || exDate.equals("")) {
                isValid = false;
                request.setAttribute("DTERR", "Please Input Date!");

            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date createDateCheck = sdf.parse(createDate.trim());
                System.out.println("Create" + createDate);

                Date expiredDateCheck = sdf.parse(exDate);
                System.out.println("Create" + exDate);
                if (createDateCheck.after(expiredDateCheck)) {
                    isValid = false;
                    request.setAttribute("DTERR", "Create Date must be BEFORE Expired Date!");
                }
            }
            if (isValid) {
                int quantity = Integer.parseInt(quantityInString);
                int price = Integer.parseInt(priceInString);
                
                String currentDate = new SimpleDateFormat("MM-dd-yyyy").format(new Date());

                String cate = request.getParameter("txtCategory");
                Category category = new Category(cate);

                HttpSession session = request.getSession();
                Account acc = (Account) session.getAttribute("DTO");
                MoonCake mc = new MoonCake(cakeID, cakeName, img, desc, price, createDate, exDate, status, acc.getFullname(), currentDate, quantity);
                
                mc.setCategoryID(category);
                System.out.println(mc.toString());
                boolean isOk = blo.addCake(mc);
                if (isOk) {
                    url = SUCCESS;
                    request.setAttribute("OK", "Created!");
                } else {
                    request.setAttribute("ERROR", "Create Failed!");
                    request.setAttribute("ID", cakeID);

                }
            } else {
                url = INVALID;
                request.setAttribute("ID", cakeID);
                request.setAttribute("NAME", cakeName);
                request.setAttribute("PRICE", priceInString);
                request.setAttribute("IMG", img);
                request.setAttribute("DESC", desc);
                request.setAttribute("ID", cakeID);
                request.setAttribute("QUAN", quantityInString);
                request.setAttribute("CREATE", createDate);
                request.setAttribute("EXPIRED", exDate);
            }
        } catch (Exception e) {
            log("ERROR at CreateCakeController: " + e.getMessage());
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
