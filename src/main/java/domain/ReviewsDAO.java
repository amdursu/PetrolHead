/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import static domain.CarsDAO.getCarID;
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
public class ReviewsDAO {
    private static ReviewsDAO instance;
    
    private ReviewsDAO() {}
    
    public static ReviewsDAO getInstance(){
        if(instance == null){
            instance = new ReviewsDAO();
        }
        return instance;
    }
    
    public static ArrayList<Review> getReviews(String model) throws NamingException, SQLException{
        ArrayList<Review> reviews = new ArrayList<>();
        String sql = "SELECT title, body, car, user FROM reviews WHERE car = " + getCarID(model) + ";";
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        while(rs.next()){
            reviews.add(new Review(rs.getString("title"), rs.getString("body"), rs.getString("car"), rs.getString("user")));
        }
        return reviews;
    }
    
    public static void addReview(String title, String body, int car, int user) throws NamingException, SQLException{
        String sql = "INSERT INTO reviews (title, body, car, user) VALUES (?, ?, ?, ?);";
        Connection c = DBConnection.getConnection();
        PreparedStatement instr = c.prepareStatement(sql);
        
        instr.setString(1, title);
        instr.setString(2, body);
        instr.setInt(3, car);
        instr.setInt(4, user);
        instr.execute();
    }
}
