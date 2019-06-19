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
import javax.naming.NamingException;
import java.util.*;

/**
 *
 * @author andrei
 */
public class DrivetrainDAO {
    private static DrivetrainDAO instance;
    
    private DrivetrainDAO() {}
    
    public DrivetrainDAO getInstance(){
        if(instance == null){
            instance = new DrivetrainDAO();
        }
        return instance;
    }
    
    public static List<Drivetrain> getDrivetrains() throws NamingException, SQLException{
        List<Drivetrain> drivetrains = new ArrayList<>();
        String sql = "SELECT c.model, d.engine, d.transmission, d.power, d.torque, d.popularity FROM drivetrains d JOIN cars c ON d.car_id = c.id;";
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        while(rs.next()){
            Drivetrain dr = new Drivetrain(rs.getString("model"), rs.getString("engine"), rs.getString("transmission"), rs.getInt("power"), rs.getInt("torque"), rs.getInt("popularity"));
            drivetrains.add(dr);
        }
        return drivetrains;
    }
    
    public static List<Drivetrain> getDrivetrains(String carModel) throws NamingException, SQLException{
        List<Drivetrain> drivetrains = new ArrayList<>();
        String sql = "SELECT d.engine, d.transmission, d.power, d.torque, d.popularity FROM drivetrains d "
                + "JOIN cars c ON c.id = d.car_id WHERE c.model = '" + carModel + "';";
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        while(rs.next()){
            Drivetrain dr = new Drivetrain(carModel, rs.getString("engine"), rs.getString("transmission"), rs.getInt("power"), rs.getInt("torque"), rs.getInt("popularity"));
            drivetrains.add(dr);
        }
        return drivetrains;
    }
    
    public static void addDrivetrain(String carModel, String engine, String transmission, String power, String torque) throws NamingException, SQLException{
        int pow = Integer.parseInt(power);
        int toq = Integer.parseInt(torque);
        int id = 0;
        String sql1 = "SELECT id FROM cars WHERE model = '" + carModel + "';";
        String sql2 = "INSERT INTO drivetrains (car_id, engine, transmission, power, torque, popularity) VALUES (?, ?, ?, ?, ?, 0);";
        Connection c = DBConnection.getConnection();
        Statement instr1 = c.createStatement();
        ResultSet rs = instr1.executeQuery(sql1);
        while(rs.next()){
            id = rs.getInt("id");
        }
        
        PreparedStatement instr2 = c.prepareStatement(sql2);
        instr2.setInt(1, id);
        instr2.setString(2, engine);
        instr2.setString(3, transmission);
        instr2.setInt(4, pow);
        instr2.setInt(5, toq);
        instr2.execute();
    }
    
    public static void computePopularity() throws NamingException, SQLException{
        
        String sql = "SELECT id FROM drivetrains;";
        
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        while(rs.next()){
            String sql2 = "SELECT COUNT(*) FROM favorites WHERE drivetrain = " + rs.getInt("id") + ";";
            Statement s = c.createStatement();
            ResultSet rs2 = s.executeQuery(sql2);
            while(rs2.next()){
                String sql3 = "UPDATE drivetrains SET popularity = ?  WHERE id = ?;"; 
                PreparedStatement ps = c.prepareStatement(sql3);
                ps.setInt(1, rs2.getInt("COUNT(*)"));
                ps.setInt(2, rs.getInt("id"));
                ps.executeUpdate();
            }
        }
    }
    
    public static ArrayList<PopularityItem> topFavoriteCars() throws NamingException, SQLException{
        ArrayList<PopularityItem> drivetrains = new ArrayList<>();
        ArrayList<PopularityItem> topFavorites = new ArrayList<>();
        String sql = "SELECT id, popularity FROM drivetrains;";
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        while(rs.next()){
            drivetrains.add(new PopularityItem(rs.getInt("id"), rs.getInt("popularity")));
        }
        
        TreeSet<PopularityItem> topFav = new TreeSet(drivetrains);
        Iterator<PopularityItem> it = topFav.descendingIterator();
        
        for(int i = 0; i < 3; i++){
            topFavorites.add(it.next());
        }
        
        return topFavorites;
    }
}
