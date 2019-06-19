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
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.*;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author andrei
 */
public class DBHandler {
    public static boolean register(String name, String surname, String email, String password) throws NamingException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException{
        String sql = "INSERT INTO \"PetrolHead\".users (name, surname, email, password) VALUES (?, ?, ?, ?);";
        Connection c = DBConnection.getConnection();
        Statement instr1 = c.createStatement();
        if(emailExists(email)){
            return false;
        }
        PreparedStatement instr2 = c.prepareStatement(sql);
        instr2.setString(1, name);
        instr2.setString(2, surname);
        instr2.setString(3, email);
        instr2.setString(4, sha1(password));
        instr2.execute();
        return true;
    }
    
    public static boolean login(String email, String password) throws NamingException, SQLException{
        String sql = "SELECT email, password FROM \"PetrolHead\".users";
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        
        while(rs.next()){
            if(rs.getString("email").equals(email) && rs.getString("password").equals(password)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean emailExists(String email) throws NamingException, SQLException{
        String sql = "SELECT email FROM \"PetrolHead\".users";
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        while(rs.next()){
            if(rs.getString("email").equals(email)){
                return true;
            }
        }
        return false;
    }
    
    public static String sha1(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String sha1 = null;
        MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
        if(input != null){
            msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
        }
        return sha1;
    }
    
    public static String getName(String email) throws NamingException, SQLException{
        String sql = "SELECT name FROM \"PetrolHead\".users WHERE email = '" + email + "';";
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        
        rs.next();
        return rs.getString("name");
    }
    
    public static boolean isAdmin(String email) throws NamingException, SQLException{
        String sql = "SELECT email FROM \"PetrolHead\".users WHERE type = 'admin';";
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        
        while (rs.next()){
            if(rs.getString("email").equals(email)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean isOwner(String email) throws NamingException, SQLException{
        String sql = "SELECT email FROM \"PetrolHead\".users WHERE type = 'owner';";
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        
        while (rs.next()){
            if(rs.getString("email").equals(email)){
                return true;
            }
        }
        return false;
    }
    
    public static int noUsers() throws NamingException, SQLException{
        String sql = "SELECT COUNT(*) FROM \"PetrolHead\".users WHERE type = 'user';";
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        while(rs.next()){
            return rs.getInt("count");
        }
        return 0;
    }
    
    public static int getUserID(String email) throws NamingException, SQLException{
        String sql = "SELECT id, email FROM \"PetrolHead\".users;";
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        while(rs.next()){
            if(rs.getString("email").equals(email)){
                c.close();
                return rs.getInt("id");
            }
        }
        return 0;
    }
    
    public static String getUserName(int userID) throws NamingException, SQLException{
        String sql = "SELECT name, surname FROM \"PetrolHead\".users WHERE id = '" + userID + "';";
        String name = "";
        Connection c = DBConnection.getConnection();
        Statement instr = c.createStatement();
        ResultSet rs = instr.executeQuery(sql);
        while(rs.next()){
            name = rs.getString("name") + " " + rs.getString("surname");
        }
        return name;
    }
}
