/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admasstoremanagementsystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author User
 */
public class DBConnection {
    private static final String USERNAME = "admas_store";
    private static final String PASSWORD ="admas@admas@admas";
    private static final String CONN ="jdbc:mysql://localhost/admas_store";
    
    public static final Connection getConnection() throws SQLException{
    return DriverManager.getConnection(CONN, USERNAME, PASSWORD);
    }
    
}
