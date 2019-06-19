 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author andrei
 */
public class CarsDAO {
    private static CarsDAO instance;
    
    private CarsDAO() {}
    
    public CarsDAO getInstance(){
        if(instance == null){
            instance = new CarsDAO();
        }
        return instance;
    }
    
    public static ArrayList<Car> getCars() throws NamingException, SQLException{
        ArrayList<Car> cars = new ArrayList<>();
        String sql = "SELECT manufacturer, model, img, description FROM cars;";
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        
        
        while(rs.next()){
            Car car = new Car(rs.getString("manufacturer"), rs.getString("model"), rs.getString("img"), rs.getString("description"));
            cars.add(car);
        }
        return cars;
    }
    
    public static ArrayList<Car> getCars(Manufacturer m) throws SQLException, NamingException{
        ArrayList<Car> cars = new ArrayList<>();
        String sql = "SELECT model, img FROM cars WHERE manufacturer=\"" + m.getName() + "\";";
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        while(rs.next()){
            cars.add(new Car(m.getName(), rs.getString("model"), rs.getString("img"), ""));
        }
        return cars;
    }
    
    public static int getCarID(String model) throws NamingException, SQLException{
        String sql = "SELECT id FROM cars WHERE model = '" + model + "';";
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        while(rs.next()){
            return rs.getInt("id");        
        }
        return 0;
    }
    
    public static void addCar(String manufacturer, String model, String image, String description) throws NamingException, SQLException{
        String sql = "INSERT INTO cars (manufacturer, model, img, description) VALUES (?, ?, ?, ?);";
        Connection c = DBConnection.getConnection();
        PreparedStatement instr = c.prepareStatement(sql);
        instr.setString(1, manufacturer);
        instr.setString(2, model);
        instr.setString(3, image);
        instr.setString(4, description);
        instr.execute();
    }
    
}
