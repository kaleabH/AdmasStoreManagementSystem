/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admasstoremanagementsystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JFrame;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;


/**
 *
 * @author User
 */
public class Main {
//         private Connection conn;
//       private Statement stat;
//       private ResultSet rs;

    public static void main(String[] args) throws SQLException, ClassNotFoundException{
    	Class.forName("com.mysql.cj.jdbc.Driver");
         Connection conn =DBConnection.getConnection();
          Statement stat =  conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         ResultSet  rs = stat.executeQuery("select id AS item_N0,item_name,measurement,claster,total_balance,claster_balance,depletion_limit,depletion_measurement,items_stored_at from store_items ORDER BY items_stored_at DESC");;
        try{
          conn = DBConnection.getConnection();
        
      stat =  conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
       rs = stat.executeQuery("select * from user");
        
        System.out.print("connected");
        String username = null;
        String password = null;
        if(rs.next()){
            
           username = rs.getString(1);
           password = rs.getString(2);
           String getuser = JOptionPane.showInputDialog(null,"enter username", "login", 0);
            String getpass = JOptionPane.showInputDialog(null,"enter password", "login", 0);
            if(username.equals(getuser) && password.equals(getpass)){
             Store store = new Store();
           store.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           store.setExtendedState(JFrame.MAXIMIZED_BOTH);
//           store.setUndecorated(true);
//      store.setSize(500, 600);
          store.setVisible(true);
            }
            
        }
        else{
            String us;
            String pas;
        String sql = "insert into user (username, password) values (?,?)";
          PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         us = JOptionPane.showInputDialog(null,"enter username", "register", 0);
          pas = JOptionPane.showInputDialog(null,"enter password", "enter password", 0);
           if(!us.equals("") || !pas.equals("")){
            ps.setString(1, us);
           ps.setString(2,pas);
           ps.execute();
         Store store = new Store();
           store.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           store.setExtendedState(JFrame.MAXIMIZED_BOTH);
//           store.setUndecorated(true);
//      store.setSize(500, 600);
          store.setVisible(true);
           }
        
        }
        
        // this will store the number of columms
       
        }
        catch(SQLException e){
            System.out.println(e);
        }
        finally {
            if (rs!=null)
             rs.close();
            if (stat!=null)
             stat.close();
         if (conn!=null)
             conn.close();
        }
        
     
      

    }
}
