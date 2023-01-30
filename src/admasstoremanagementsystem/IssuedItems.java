/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admasstoremanagementsystem;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */

public class IssuedItems extends JPanel{
    public JLabel name;
    public  JTable table;
    public JButton prepareExcel;
    public  DefaultTableModel dtm;
    private Connection conn;
    private Statement stat;
    private ResultSet rs;

  //  private String[] dataItem;
  public void updateTableData(){
  
  table.setModel(dtm);
  }
   public void addDataToTable() throws SQLException{
       dtm.getDataVector().removeAllElements();
dtm.fireTableDataChanged();
       conn = null;
       stat =null;
       rs = null;
     try{
        conn = DBConnection.getConnection();
        System.out.print("connected");
        stat =  conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stat.executeQuery("select id AS issue_N0,item_name,amount_requested,amount_issued,measurement,issued_to,store_requisition_no,voucher_no,remark,items_issued_at from issued_items ORDER BY items_issued_at DESC");
        ResultSetMetaData rsMetaData = rs.getMetaData();
        
        // this will store the number of columms
        int columns = rsMetaData.getColumnCount();
        
         
        //note:- look more into java Vector
        Vector columns_name = new Vector();
        Vector data_rows = new Vector();
        for(int i = 1; i<=columns; i++){
            if(i==1){
             columns_name.addElement("item No");
            }
            else{
             columns_name.addElement(rsMetaData.getColumnName(i));
            }
        System.out.println(rsMetaData.getColumnName(i));
        }
        dtm.setColumnIdentifiers(columns_name);
        while(rs.next()){
        
        data_rows = new Vector();
        for(int j=1; j<=columns; j++){
        if(j==10){
            System.out.println("it is 7");
        data_rows.addElement(rs.getTimestamp(j));
        }
        else{
        data_rows.addElement(rs.getString(j));
        }
        }
             
            dtm.addRow(data_rows);
   }
        //set the table model to dtm
        updateTableData();
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
    public IssuedItems() throws SQLException{
        dtm = new DefaultTableModel();

        
        table = new JTable();
        name = new JLabel("issued items");
        prepareExcel = new JButton("prepare Excel");
        name.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(1250, 300));
        
      addDataToTable();
       
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(name);
      add(new JScrollPane(table));
//      add(prepareExcel);
      
     
    }
}
