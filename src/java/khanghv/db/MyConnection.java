/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanghv.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author USER
 */
public class MyConnection implements Serializable{
    public static Connection getConnection() throws NamingException, SQLException           
    {   
        Context context = new InitialContext();
        Context tomcatContext = (Context) context.lookup("java:com/env");
        DataSource ds = (DataSource)tomcatContext.lookup("SE140097");
        Connection conn = ds.getConnection();
        return conn;
        
    }
}
