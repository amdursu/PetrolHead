/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.Car;
import domain.DBConnection;
import domain.Drivetrain;
import domain.DrivetrainDAO;
import static domain.DrivetrainDAO.computePopularity;
import static domain.DrivetrainDAO.getDrivetrains;
import domain.Favorite;
import static domain.FavoritesDAO.addFavorite;
import static domain.FavoritesDAO.getUserFavorites;
import domain.PopularityItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class FavoriteController extends HttpServlet {

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
            throws ServletException, IOException, NamingException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        ArrayList<Drivetrain> drivetrains = (ArrayList<Drivetrain>) getDrivetrains();
        ArrayList<Favorite> favorites = (ArrayList<Favorite>) request.getSession().getAttribute("FAVORITES");
        String userEmail = (String) request.getSession().getAttribute("USER");
        Favorite f = null;
        
        if(userEmail != null){
            for(Drivetrain d : drivetrains){
                String car = d.getCarModel() + " " + d.getEngine() + " " + d.getTransmission() + " " + d.getPower();
                if(request.getParameter(car) != null){
                    if(!addFavorite(userEmail, d.getCarModel(), d.getEngine(), d.getTransmission(), d.getPower())){
                        request.setAttribute("err", "You already added this car to favorites!");
                    }
                }
            }
        }
        
        computePopularity();
        ArrayList<PopularityItem> popularFavorites = DrivetrainDAO.topFavoriteCars();
        getServletContext().setAttribute("top", popularFavorites);
        request.getRequestDispatcher("account.jsp").forward(request, response);
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
        } catch (NamingException ex) {
            Logger.getLogger(FavoriteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FavoriteController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NamingException ex) {
            Logger.getLogger(FavoriteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FavoriteController.class.getName()).log(Level.SEVERE, null, ex);
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
