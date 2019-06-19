/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import domain.Car;
import java.util.ArrayList;
import static domain.CarsDAO.getCars;
import domain.DrivetrainDAO;
import static domain.DrivetrainDAO.computePopularity;
import domain.Manufacturer;
import static domain.ManufacturersDAO.getManufacturers;
import domain.PopularityItem;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author andrei
 */
public class DataListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ArrayList<Car> cars = getCars();
            sce.getServletContext().setAttribute("cars", cars);
            ArrayList<Manufacturer> manufacturers = getManufacturers();
            sce.getServletContext().setAttribute("manufacturers", manufacturers);
            computePopularity();
            ArrayList<PopularityItem> topFavorites = DrivetrainDAO.topFavoriteCars();
            sce.getServletContext().setAttribute("top", topFavorites);
        } catch (NamingException ex) {
            Logger.getLogger(DataListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DataListener.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
