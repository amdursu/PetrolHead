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
//import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 *
 * @author andrei
 */
public class DBConnection {
     public static Connection getConnection() throws NamingException, SQLException{
        //Context ctx = new InitialContext();
        //DataSource dts = (DataSource) ctx.lookup("jdbc/PetrolHead");
        PoolProperties p = new PoolProperties();
          p.setUrl(System.getenv("JDBC_DATABASE_URL"));
          p.setDriverClassName("org.postgresql.Driver");
          p.setUsername("andrei");
          p.setPassword("a");
          p.setJmxEnabled(true);
          p.setTestWhileIdle(false);
          p.setTestOnBorrow(true);
          p.setValidationQuery("SELECT 1");
          p.setTestOnReturn(false);
          p.setValidationInterval(30000);
          p.setTimeBetweenEvictionRunsMillis(30000);
          p.setMaxActive(3000);
          p.setInitialSize(5);
          p.setMaxWait(10000);
          p.setRemoveAbandonedTimeout(30);
          p.setMinEvictableIdleTimeMillis(30000);
          p.setMinIdle(10);
          p.setLogAbandoned(true);
          p.setRemoveAbandoned(true);
          p.setJdbcInterceptors(
            "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
            "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
          DataSource datasource = new DataSource();
          datasource.setPoolProperties(p);
          Connection c = datasource.getConnection();
        return c;
    }
}
