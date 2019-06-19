/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author andrei
 */
public class DBConnection {
     public static Connection getConnection() throws NamingException, SQLException{
        Context ctx = new InitialContext();
        DataSource dts = (DataSource) ctx.lookup("jdbc/PetrolHead");
        Connection c = dts.getConnection();
        return c;
    }
}
