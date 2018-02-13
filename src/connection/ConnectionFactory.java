/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author horaciocome1
 */
public class ConnectionFactory {
    
    private final String URL = "jdbc:mysql://localhost:3306/caderninho";
    private final String USER = "root";
    private final String PASS = "head2010";
    
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void closeConnection(Connection con) {
        try {
            if (con != null) 
                con.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        try {
            if (stmt != null) 
                stmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        try {
            if (rs != null) 
                rs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
