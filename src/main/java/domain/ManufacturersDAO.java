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
public class ManufacturersDAO {
    private static ManufacturersDAO instance;
    
    private ManufacturersDAO(){}
    
    public static ManufacturersDAO getInstance(){
        if(instance == null){
            instance = new ManufacturersDAO();
        }
        return instance;
    }
    
    public static ArrayList<Manufacturer> getManufacturers() throws NamingException, SQLException{
        ArrayList<Manufacturer> manufacturers = new ArrayList<>();
        String sql = "SELECT name, image_path FROM \"PetrolHead\".manufacturers;";
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        
        while(rs.next()){
            Manufacturer m = new Manufacturer(rs.getString("name"), rs.getString("image_path"));
            manufacturers.add(m);
        }
        c.close();
        return manufacturers;
    }
    
    public static void addManufacturer(String name, String image) throws NamingException, SQLException{
        String sql = "INSERT INTO \"PetrolHead\".manufacturers (name, image_path) VALUES (?, ?);";
        Connection c = DBConnection.getConnection();
        PreparedStatement instr = c.prepareStatement(sql);
        instr.setString(1, name);
        instr.setString(2, image);
        instr.execute();
        c.close();
    }
    
    
}
