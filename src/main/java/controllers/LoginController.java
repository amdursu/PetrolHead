/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static domain.DBHandler.getName;
import static domain.DBHandler.isAdmin;
import static domain.DBHandler.isOwner;
import static domain.DBHandler.login;
import static domain.DBHandler.register;
import static domain.DBHandler.sha1;
import domain.DrivetrainDAO;
import static domain.DrivetrainDAO.computePopularity;
import static domain.FavoritesDAO.getUserFavorites;
import domain.PopularityItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andrei
 */
public class LoginController extends HttpServlet {

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
            throws ServletException, IOException, NoSuchAlgorithmException, NamingException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
           
        String email = request.getParameter("mail");
            
            if(login(email, sha1(request.getParameter("password")).toLowerCase())){
                if(!isAdmin(email) && !isOwner(email)){
                    request.getSession().setAttribute("USER", email);
                    request.getSession().setAttribute("FAVORITES", getUserFavorites(email));
                    request.getSession().setAttribute("ADMIN", null);
                    request.getSession().setAttribute("OWNER", null);
                    request.getRequestDispatcher("account.jsp").forward(request, response);
                }
                else if(isAdmin(email)){
                    request.getSession().setAttribute("USER", email);
                    request.getSession().setAttribute("ADMIN", true);
                    request.getSession().setAttribute("OWNER", false);
                    request.getRequestDispatcher("account.jsp").forward(request, response);
                }
                else if(isOwner(email)){
                    request.getSession().setAttribute("USER", email);
                    request.getSession().setAttribute("ADMIN", false);
                    request.getSession().setAttribute("OWNER", true);
                    request.getRequestDispatcher("account.jsp").forward(request, response);
                }
                
            }
            else{
                String message = "Wrong email and password combination!";
                request.setAttribute("mess", message);
                request.getRequestDispatcher("login_register.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
