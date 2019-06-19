/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domain.Car;
import domain.DBConnection;
import domain.Manufacturer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author andrei
 */
public class SearchController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public static ArrayList<Car> searchCars(String search) throws NamingException, SQLException{
        ArrayList<Car> cars = new ArrayList<>();
        Connection c = DBConnection.getConnection();
        String[] words = search.split(" ");
        for(String word : words){
            String sql1 = "SELECT manufacturer, model, img, description FROM cars WHERE model LIKE '%" + word + "%';";
            Statement instr1 = c.createStatement();
            ResultSet rs1 = instr1.executeQuery(sql1);
            while(rs1.next()){
                Car car = new Car(rs1.getString("manufacturer"), rs1.getString("model"), rs1.getString("img"), rs1.getString("description"));
                if(!cars.contains(car)){
                    cars.add(car);
                }
            }
        }
       return cars; 
    }
    
    public static ArrayList<Manufacturer> searchManufacturers(String search) throws NamingException, SQLException{
        ArrayList<Manufacturer> manufacturers = new ArrayList<>();
        Connection c = DBConnection.getConnection();
        String[] words = search.split(" ");
        for(String word : words){
            String sql = "SELECT name, image_path FROM manufacturers WHERE name LIKE '%" + word + "%';";
            Statement instr = c.createStatement();
            ResultSet rs = instr.executeQuery(sql);
            while(rs.next()){
                Manufacturer m = new Manufacturer(rs.getString("name"), rs.getString("image_path"));
                if (!manufacturers.contains(m)){
                    manufacturers.add(m);
                }
            }
        }
        return manufacturers;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String input = request.getParameter("search");
        ArrayList<Car> cars = searchCars(input);
        ArrayList<Manufacturer> manufacturers = searchManufacturers(input);
        request.setAttribute("searchC", cars);
        request.setAttribute("searchM", manufacturers);
        request.getRequestDispatcher("search_results.jsp").forward(request, response);
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
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
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
