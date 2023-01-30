/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admasstoremanagementsystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;




/**
 *
 * @author User
 */
public class Store extends JFrame implements ActionListener{
    private final ImageIcon image;
    private final JMenuBar menubar;
    private final JMenu store_items_menu;
    private final JMenu issued_items_menu;
    private final JMenu purchases;
    private final JMenuItem purchase_requisition_menu;
    private final JMenuItem goods_received_menu;
    private final JPanel navbar;
    private final JPanel rightnav;
//    private final JTextField search;
    private final JLabel notification;
    private final JLabel logo;
    private final JPanel test;
    //store items
    private final StoreControl storeControl;
    private final StoreItems storeitems;

    //issued items
    private final IssueControl issueControl;
    private final IssuedItems issueditems;
    
    //purchase items
    private final PurchaseControl purchaseControl;
    private final PurchaseRequisition purchaseitems;
    
    //goods received
    private final GoodsReceived goodsreceived;
    //get selected row id for store items
   public String storeItemSelectedId;
   //get selected row id for issued items
   public String issuedItemSelectedId;
//   get selected row id for purchase requisitions
   public String purchaseRequisitionSelectedId;
   //set selected item claster 
    public String selectedItemClaster;
    
   //set selected item measurement 
    public String selectedItemMeasurement;
    
    //model for issue item measurement selection model
    public DefaultComboBoxModel issueItemMeasurement;
    public String[] issueItemMeasurementData;
   
   //model for purchase measurement selection model
   public DefaultComboBoxModel purchaseMeasurement;
   public String[] purchasemeasurementData;
    
     private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;
   public void updateTable() throws SQLException{
      storeitems.addDataToTable();
}
   
     public void getStoreItemsSelectedRowData(int i){
        storeItemSelectedId = storeitems.dtm.getValueAt(i, 0).toString();
       storeControl.item_name.setText(storeitems.dtm.getValueAt(i, 1).toString());
       storeControl.measurement.setText(storeitems.dtm.getValueAt(i, 2).toString());
       storeControl.claster.setText(storeitems.dtm.getValueAt(i, 3).toString());
       storeControl.total_balance.setText(storeitems.dtm.getValueAt(i, 4).toString());
       storeControl.claster_balance.setText( storeitems.dtm.getValueAt(i, 5).toString());
       storeControl.depletion_limit.setText(storeitems.dtm.getValueAt(i, 6).toString());
       storeControl.depletion_measurement.setText(storeitems.dtm.getValueAt(i, 7).toString());
       selectedItemClaster = storeControl.claster.getText();
       selectedItemMeasurement = storeControl.measurement.getText();
   }
     
      public void getIssueItemsSelectedRowData(int i){
        issuedItemSelectedId = storeitems.dtm.getValueAt(i, 0).toString();
       issueControl.item_name.setText(storeitems.dtm.getValueAt(i, 1).toString());
       issueControl.amount_requested.setText(storeitems.dtm.getValueAt(i, 2).toString());
       issueControl.amount_issued.setText(storeitems.dtm.getValueAt(i, 3).toString());
//       issueControl.measurement.setText(storeitems.dtm.getValueAt(i, 4).toString());
       issueControl.issued_to.setText( storeitems.dtm.getValueAt(i, 5).toString());
       issueControl.store_requisition_no.setText(storeitems.dtm.getValueAt(i, 6).toString());
       issueControl.voucher_no.setText(storeitems.dtm.getValueAt(i, 7).toString());
       issueControl.remark.setText(storeitems.dtm.getValueAt(i, 8).toString());
       
   }
      
       public void getStoreItemsSelectedRowDataForPurchaseRequest(int i){
        purchaseRequisitionSelectedId =storeitems.dtm.getValueAt(i, 0).toString();
       purchaseControl.item_name.setText(storeitems.dtm.getValueAt(i, 1).toString());
       }
       public void getPurchaseRequestSelectedRowData(int i){
        purchaseRequisitionSelectedId = purchaseitems.dtm.getValueAt(i, 0).toString();
       purchaseControl.item_name.setText(purchaseitems.dtm.getValueAt(i, 1).toString());
//       purchaseControl.amount_requested.setText(storeitems.dtm.getValueAt(i, 2).toString());
       purchaseControl.requested_amount.setText(purchaseitems.dtm.getValueAt(i, 3).toString());
       purchaseControl.received_amount.setText(purchaseitems.dtm.getValueAt(i, 4).toString());
     
   }
     
     private void addItems(String itemName, String measurement, String claster, String totalBalance, String clasterBalance, String depletionLimit, String depletionMeasurement) throws SQLException{
   sql = "insert into store_items(item_name,measurement,claster,claster_balance,total_balance,depletion_limit,depletion_measurement) values (?,?,?,?,?,?,?)";
   try{
            conn = DBConnection.getConnection();
        System.out.print("add connected");
        ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, itemName);
        ps.setString(2, measurement);
        ps.setString(3, claster);
        ps.setString(4, clasterBalance);
        ps.setString(5, totalBalance);
        ps.setString(6, depletionLimit);
        ps.setString(7, depletionMeasurement);
        
        ps.execute();
//        rs = ps.executeQuery();
       // stat =  conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }catch(SQLException e){
        System.out.print(e+"add item");
        }
   finally{
    if(ps != null){
     ps.close();
    }
    if(conn != null){
    conn.close();
    }
   }
   }
   
     private void compensateBalance( String totalBalance, String clasterBalance , String id) throws SQLException{
     
         sql="update store_items set claster_balance = ?, total_balance = ? where id = ?";
          try{
            conn = DBConnection.getConnection();
        System.out.print("add connected");
        ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(4, clasterBalance);
        ps.setString(5, totalBalance);
        
        ps.setString(8, id);
        
        ps.execute();
        }catch(SQLException e){
        System.out.print(e +"in compensate balance");
        }
   finally{
    if(ps != null){
     ps.close();
    }
    if(conn != null){
    conn.close();
    }
   }
     
     }
    private void updateItems(String itemName, String measurement, String claster, String totalBalance, String clasterBalance, String depletionLimit, String depletionMeasurement) throws SQLException{
   sql = "update store_items set item_name = ?,measurement = ?,claster = ?,claster_balance = ?,total_balance = ?,depletion_limit = ?,depletion_measurement = ? where id  = ?";
   try{
            conn = DBConnection.getConnection();
        System.out.print("add connected");
        ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, itemName);
        ps.setString(2, measurement);
        ps.setString(3, claster);
        ps.setString(4, clasterBalance);
        ps.setString(5, totalBalance);
        ps.setString(6, depletionLimit);
        ps.setString(7, depletionMeasurement);
        
        ps.setString(8, storeItemSelectedId);
        
        ps.execute();
//        rs = ps.executeQuery();
       // stat =  conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }catch(SQLException e){
        System.out.print(e+ "in update item");
        }
   finally{
    if(ps != null){
     ps.close();
    }
    if(conn != null){
    conn.close();
    }
   }
   }
    private void deleteItems() throws SQLException{
   sql = "delete from store_items where id  = ?";
   try{
            conn = DBConnection.getConnection();
        System.out.print("add connected");
        ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
        
        ps.setString(1, storeItemSelectedId);
        
        ps.execute();
//        rs = ps.executeQuery();
       // stat =  conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }catch(SQLException e){
        System.out.print(e+ "in delete item");
        }
   finally{
    if(ps != null){
     ps.close();
    }
    if(conn != null){
    conn.close();
    }
   }
   }
   private void issueItems(String item_name, String amountrequested, String amountissued, String measurement, String issuedto, String storeReqno, String voucherno, String remark, String item_id) throws SQLException{
   sql = "insert into issued_items(item_name,amount_requested,amount_issued,measurement,issued_to,store_requisition_no,voucher_no,remark,item_id) values (?,?,?,?,?,?,?,?,?)";
   try{
            conn = DBConnection.getConnection();
        System.out.print("add connected");
        ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, item_name);
        ps.setString(2, amountrequested);
        ps.setString(3, amountissued);
        ps.setString(4, measurement);
        ps.setString(5, issuedto);
        ps.setString(6, storeReqno);
        ps.setString(7, voucherno);
        ps.setString(8, remark);
        ps.setString(9, item_id);
        
        ps.execute();
//        rs = ps.executeQuery();
       // stat =  conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }catch(SQLException e){
        System.out.print(e+"in issue item");
        }
   finally{
    if(ps != null){
     ps.close();
    }
    if(conn != null){
    conn.close();
    }
   }
   }
  
   private void addPurchaseRequest(String itemName, String measurement, String requestAmount, String receivedAmount) throws SQLException{
   sql = "insert into purchase_requisition(item_name,measurement,requested_amount, received_amount) values (?,?,?,?)";
   try{
            conn = DBConnection.getConnection();
        System.out.print("add connected");
        ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, itemName);
        ps.setString(2, measurement);
        ps.setString(3, requestAmount);
        ps.setString(4, receivedAmount);
        
        ps.execute();
//        rs = ps.executeQuery();
       // stat =  conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }catch(SQLException e){
        System.out.print(e+"add item");
        }
   finally{
    if(ps != null){
     ps.close();
    }
    if(conn != null){
    conn.close();
    }
   }
   }
    /*
     
  id int primary key auto_increment,
item_name varchar(50),
requested_amount int,
received_amount int,
measurement varchar(20),
purchase_requested_at timestamp
      textfields.add(item_name);
       textfields.add(measurement);
       textfields.add(requested_amount);
    textfields.add(received_amount);
     */
     private void updateRequest(String measurement, String requestAmount, String receivedAmount) throws SQLException{
   sql = "update purchase_requisition set measurement = ?,requested_amount = ?,received_amount = ? where id = ?";
   try{
            conn = DBConnection.getConnection();
        System.out.print("add connected");
        ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, measurement);
        ps.setString(2, requestAmount);
        ps.setString(3, receivedAmount);
        ps.setString(4, purchaseRequisitionSelectedId);
        ps.execute();
//        rs = ps.executeQuery();
       // stat =  conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }catch(SQLException e){
        System.out.print(e);
        }
   finally{
    if(ps != null){
     ps.close();
    }
    if(conn != null){
    conn.close();
    }
   }
   }
     private void deleteRequest() throws SQLException{
   sql = "delete from purchase_requisition where id  = ?";
   try{
            conn = DBConnection.getConnection();
        System.out.print("add connected");
        ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
        
        ps.setString(1, purchaseRequisitionSelectedId);
        
        ps.execute();
//        rs = ps.executeQuery();
       // stat =  conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }catch(SQLException e){
        System.out.print(e);
        }
   finally{
    if(ps != null){
     ps.close();
    }
    if(conn != null){
    conn.close();
    }
   }
   }
    
    public Store() throws SQLException{
        super("admas university store");
        super.setLayout(new BorderLayout());
        super.setBackground(Color.BLUE);
       
        
     image = new ImageIcon(getClass().getResource("logo.png"));
     menubar = new JMenuBar();
     store_items_menu = new JMenu("store_items");
     issued_items_menu = new JMenu("issued_items");
     purchases = new JMenu("purchases");
     purchase_requisition_menu = new JMenuItem("purchase requisition");
     goods_received_menu = new JMenuItem("goods_received");
     navbar = new JPanel();
     rightnav = new JPanel();
//     search = new JTextField(15);
     notification = new JLabel();
     test = new JPanel();
     //store initialization
     storeControl = new StoreControl();
     storeitems = new StoreItems();
     
     //issue initialization
     issueControl = new IssueControl();
     issueditems = new IssuedItems();
     
      issueItemMeasurement = new DefaultComboBoxModel();
    //set issue item meaurement array data for measurement combo box
     issueItemMeasurementData = new String[2];
     issueItemMeasurementData[0] = "piece";
     issueItemMeasurement.addElement(issueItemMeasurementData[0]);

       //purchase initialization
     purchaseitems = new PurchaseRequisition();
     purchaseControl = new PurchaseControl();
     
     //received goods initialization
     goodsreceived = new GoodsReceived();
     
       purchaseMeasurement = new DefaultComboBoxModel();
    //set purchase meaurement array data for measurement combo box
    purchasemeasurementData= new String[2];
    purchasemeasurementData[0]="piece";
    purchaseMeasurement.addElement(purchasemeasurementData[0]);
     
     //navbar set layout
     navbar.setLayout(new GridLayout(1, 2, 450, 0));
     navbar.setBackground(new Color(51, 153,255));
     test.setBackground(new Color(51, 153,255));
     rightnav.setLayout(new GridLayout(1, 2, 100,0));
     rightnav.setBackground(new Color(51, 153,255));
     //add focus lost event listener to amount issue to check if amount issued is less than the balance in the database
     
     issueControl.amount_issued.addFocusListener(new FocusAdapter(){
     
         @Override
         public void focusLost(FocusEvent e){
        
             int total_balance;
             
      
          if("piece".equals(issueControl.measurement.getSelectedItem())){
           total_balance = Integer.parseInt(selectedItemClaster)*Integer.parseInt(storeControl.claster_balance.getText());
           if(Integer.parseInt(issueControl.amount_issued.getText()) > total_balance){
               JOptionPane.showMessageDialog(null, "amount issued greater than amount in store");
           issueControl.amount_issued.setText("");
           }
          }
             else{
           total_balance = Integer.parseInt(storeControl.claster_balance.getText());
            if(Integer.parseInt(issueControl.amount_issued.getText()) > total_balance){
                JOptionPane.showMessageDialog(null, "amount issued greater than amount in store");
           issueControl.amount_issued.setText("");
           }
          }
          
  
         }
     });
     

     
    //rightnav.setLayout(new FlowLayout());
     
     logo = new JLabel();
     logo.setIcon(new ImageIcon(getClass().getResource("logo2.png")));
   
     
     ImageIcon im = new ImageIcon(getClass().getResource("icon.png"));
     Image dabimage = im.getImage();
     Image modimage = dabimage.getScaledInstance(30, 35, java.awt.Image.SCALE_SMOOTH);
     
     notification.setIcon(new ImageIcon(modimage));
     
     //rightnav set layout
     rightnav.setLayout(new FlowLayout());
      //add to rightbar
    rightnav.add(logo);
//    rightnav.add(search);
    rightnav.add(notification);
    
        //add to navbar
     navbar.add(test);
     navbar.add(logo);
     navbar.add(rightnav);
   
     purchases.add(purchase_requisition_menu);
     purchases.add(goods_received_menu);
     //assigning action listener to store controls
     storeControl.add_item.addActionListener(this);
    storeControl.update.addActionListener(this);
    storeControl.delete.addActionListener(this);
    storeControl.issue_item.addActionListener(this);
    storeControl.request_purchase.addActionListener(this);
    
    //assigning action listener to issued controls
    issueControl.issue_item.addActionListener(this);
    issueControl.update_issue.addActionListener(this);
    issueControl.delete_issue.addActionListener(this);
    //assigning action listener to purchase controls
    purchaseControl.request_purchase.addActionListener(this);
     purchaseControl.update_request.addActionListener(this);
      purchaseControl.request_purchase.addActionListener(this);
       purchaseControl.delete_request.addActionListener(this);
     // assigning action listener to menu bar items
     store_items_menu.addMenuListener(new MenuListener(){
            @Override
            public void menuSelected(MenuEvent e) {
                storeControl.setVisible(true);
                storeitems.setVisible(true);
               issueControl.setVisible(false);
                issueditems.setVisible(false);
                purchaseControl.setVisible(false);
               purchaseitems.setVisible(false);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
               
            }

            @Override
            public void menuCanceled(MenuEvent e) {
                
            }
     
     });
     issued_items_menu.addMenuListener(new MenuListener(){
         
//         dont use (this)  some event handlers like this event handler
            @Override
            public void menuSelected(MenuEvent e) {
                storeControl.setVisible(false);
                storeitems.setVisible(false);
                add(issueControl,BorderLayout.WEST);
                add(issueditems,BorderLayout.CENTER);
               issueControl.setVisible(false);
                issueditems.setVisible(true);
                purchaseControl.setVisible(false);
               purchaseitems.setVisible(false);
                goodsreceived.setVisible(false);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
               
            }

            @Override
            public void menuCanceled(MenuEvent e) {
                
            }
     
     });
     purchase_requisition_menu.addActionListener(this);
     goods_received_menu.addActionListener(this);
     
     menubar.add(store_items_menu);
     menubar.add(issued_items_menu);
     menubar.add(purchases);
     
     //instead of using this.setJMenuBar(menubar) because of overrideable method warning (try it)
     super.setJMenuBar(menubar);
     super.setIconImage(image.getImage());
     //could us super but the method will be overrideable
     this.add(navbar, BorderLayout.NORTH);
     
     //set the visible of issue and purchase elements to false
      this.issueControl.setVisible(false);
     this.issueditems.setVisible(false);
     this.purchaseControl.setVisible(false);
     this.purchaseitems.setVisible(false);
     
     //add store elements
     this.add(storeControl, BorderLayout.WEST);
     this.add(storeitems, BorderLayout.CENTER);
     
    
     
     storeitems.table.addMouseListener(new MouseAdapter(){
     
      @Override
        public void mouseClicked(MouseEvent e){
            if(e.getSource() == storeitems.table){
                 int i = storeitems.table.getSelectedRow();
                         getStoreItemsSelectedRowData(i);
            }
            else if (e.getSource() == issueditems.table){
             int j = storeitems.table.getSelectedRow();
                         getIssueItemsSelectedRowData(j);
            }
            else if(e.getSource() == storeitems.table){
                 int l = purchaseitems.table.getSelectedRow();
                         getStoreItemsSelectedRowDataForPurchaseRequest(l);
            }
       
        }
         
     });
     
     purchaseitems.table.addMouseListener(new MouseAdapter(){
         
         @Override
      public void mouseClicked(MouseEvent e){
            if(e.getSource() == purchaseitems.table){
                 int k = purchaseitems.table.getSelectedRow();
                         getPurchaseRequestSelectedRowData(k);
            }
      }
     });
  
    }
    @Override
    public void actionPerformed(ActionEvent e){
//        store controls event handlers
              if(e.getSource() ==storeControl.add_item){
          int response =  JOptionPane.showConfirmDialog(null,"do you want to add item ?","confirm add item", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
          /*
          JOptionPane.YES_OPTION =0
          JOptionPane.NO_OPTION =1
          JOptionPane.CANCEL_OPTION =2
          */
          if (response ==  JOptionPane.YES_OPTION){
            try {
               addItems(storeControl.item_name.getText(),storeControl.measurement.getText(), storeControl.claster.getText(), storeControl.total_balance.getText(), storeControl.claster_balance.getText(), storeControl.depletion_limit.getText(), storeControl.depletion_measurement.getText());
           } catch (SQLException ex) {
               System.out.println(ex);
           }
            finally{
            JOptionPane.showMessageDialog(null,"item added sucessfully");
                try {
                    storeitems.addDataToTable();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
          }
          
          
       }
       else if(e.getSource() ==storeControl.update){
        int response =  JOptionPane.showConfirmDialog(null,"do you want to update item ?","confirm update item", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
         
          if (response ==  JOptionPane.YES_OPTION){
            try {
               updateItems(storeControl.item_name.getText(),storeControl.measurement.getText(), storeControl.claster.getText(), storeControl.total_balance.getText(), storeControl.claster_balance.getText(), storeControl.depletion_limit.getText(), storeControl.depletion_measurement.getText());
           } catch (SQLException ex) {
               System.out.println(ex);
           }
            finally{
            JOptionPane.showMessageDialog(null,"item updated sucessfully");
            try {
                    storeitems.addDataToTable();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
          }
       }
       else if(e.getSource() ==storeControl.delete){
         int response =  JOptionPane.showConfirmDialog(null,"do you want to delete item ?","confirm delete item", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
         
          if (response ==  JOptionPane.YES_OPTION){
            try {
               deleteItems();
           } catch (SQLException ex) {
               System.out.println(ex);
           }
            finally{
            JOptionPane.showMessageDialog(null,"item delete sucessfully");
            try {
                    storeitems.addDataToTable();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
          }
       }
       else if(e.getSource() ==storeControl.issue_item){
       issueControl.item_id = storeItemSelectedId;
       issueControl.item_name.setText(storeControl.item_name.getText());
       
       if(!"piece".equals(storeControl.measurement.getText())){
            issueItemMeasurementData[1] = storeControl.measurement.getText();
            issueItemMeasurement.addElement(issueItemMeasurementData[1]);
            issueControl.measurement.setModel(issueItemMeasurement);
        }

       this.storeControl.setVisible(false);
       this.storeitems.setVisible(false);
       this.purchaseControl.setVisible(false);
       this.purchaseitems.setVisible(false);
     add(this.issueControl,BorderLayout.WEST);
     add(this.issueditems,BorderLayout.CENTER);
       this.issueControl.setVisible(true);
       this.issueditems.setVisible(true);
       
       }
       else if(e.getSource() ==storeControl.request_purchase){
        purchaseControl.item_id = storeItemSelectedId;
        purchaseControl.item_name.setText(storeControl.item_name.getText());
        
        
        if(!"piece".equals(storeControl.measurement.getText())){
            purchasemeasurementData[1] = storeControl.measurement.getText();
            purchaseMeasurement.addElement(purchasemeasurementData[1]);
            purchaseControl.measurement.setModel(purchaseMeasurement);
        }
        
         this.storeControl.setVisible(false);
       this.storeitems.setVisible(false);
       this.issueControl.setVisible(false);
       this.issueditems.setVisible(false);
     add(this.purchaseControl,BorderLayout.WEST);
     add(this.purchaseitems,BorderLayout.CENTER);
     this.purchaseControl.setVisible(true);
       this.purchaseitems.setVisible(true);
       }
       
//   issue controls event handlers
       else if(e.getSource() ==issueControl.issue_item){
          int response =  JOptionPane.showConfirmDialog(null,"do you want to issue item ?","confirm issue item", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
          /*
          JOptionPane.YES_OPTION =0
          JOptionPane.NO_OPTION =1
          JOptionPane.CANCEL_OPTION =2
          */
          if (response ==  JOptionPane.YES_OPTION){
            try {
                int total_balance = 0;
                int claster_balance =0;
               
                    total_balance = Integer.parseInt(storeControl.total_balance.getText()) - Integer.parseInt(issueControl.amount_issued.getText());
                    claster_balance = Integer.parseInt(storeControl.claster_balance.getText()) - (Integer.parseInt(issueControl.amount_issued.getText()) /Integer.parseInt(selectedItemClaster)) ;
                compensateBalance(String.valueOf(total_balance), String.valueOf(total_balance), issuedItemSelectedId);
                
               issueItems(issueControl.item_name.getText(), issueControl.amount_requested.getText(), issueControl.amount_requested.getText(), issueControl.measurement.getSelectedItem().toString(), issueControl.issued_to.getText(),issueControl.store_requisition_no.getText(), issueControl.voucher_no.getText(), issueControl.remark.getText(),storeItemSelectedId);
           } catch (SQLException ex) {
               System.out.println(ex);
           }
            finally{
            JOptionPane.showMessageDialog(null,"item issued sucessfully");
                try {
                    issueditems.addDataToTable();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
          }
          
          
       }
     
       //purchase controllers event handlers
       
        else if(e.getSource() == purchaseControl.request_purchase){
            int response =  JOptionPane.showConfirmDialog(null,"do you want to request purchase ?","request purchase", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
         
          if (response ==  JOptionPane.YES_OPTION){
            try {
              addPurchaseRequest(purchaseControl.item_name.getText(),purchaseControl.measurement.getSelectedItem().toString(),purchaseControl.requested_amount.getText(),purchaseControl.received_amount.getText());
           } catch (SQLException ex) {
               System.out.println(ex);
           }
            finally{
            JOptionPane.showMessageDialog(null,"purchase requested sucessfully");
            try {
                    purchaseitems.addDataToTable();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
          }
            
     
     }
          else if(e.getSource() ==purchaseControl.update_request){
        int response =  JOptionPane.showConfirmDialog(null,"do you want to update request ?","confirm request", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
         
          if (response ==  JOptionPane.YES_OPTION){
            try {
              updateRequest(purchaseControl.measurement.getSelectedItem().toString(),purchaseControl.requested_amount.getText(),purchaseControl.received_amount.getText());
           } catch (SQLException ex) {
               System.out.println(ex);
           }
            finally{
            JOptionPane.showMessageDialog(null,"purchase request updated sucessfully");
            try {
                    purchaseitems.addDataToTable();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
          }
       }
       else if(e.getSource() == purchaseControl.delete_request){
         int response =  JOptionPane.showConfirmDialog(null,"do you want to delete request ?","confirm delete request", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
         
          if (response ==  JOptionPane.YES_OPTION){
            try {
               deleteRequest();
           } catch (SQLException ex) {
               System.out.println(ex);
           }
            finally{
            JOptionPane.showMessageDialog(null,"purchase delete sucessfully");
            try {
                    issueditems.addDataToTable();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
          }
       }
      
       
       //menu items event handlers
       
       else if(e.getSource() == store_items_menu){
//      this.storeControl.setVisible(true);
//       this.storeitems.setVisible(true);
//       this.issueControl.setVisible(false);
//       this.issueditems.setVisible(false);
//       this.purchaseControl.setVisible(false);
//       this.purchaseitems.setVisible(false);

     }
     else if(e.getSource()== issued_items_menu){
     this.storeControl.setVisible(false);
     this.storeitems.setVisible(false);
     this.issueControl.setVisible(false);
     add(issueditems,BorderLayout.CENTER);
     this.issueditems.setVisible(true);
     this.purchaseControl.setVisible(false);
     this.purchaseitems.setVisible(false);
      this.goodsreceived.setVisible(false);
     }
     else if(e.getSource() == purchase_requisition_menu){
      this.storeControl.setVisible(false);
     this.storeitems.setVisible(false);
     this.issueControl.setVisible(false);
     this.issueditems.setVisible(false);
     this.purchaseControl.setVisible(false);
      this.goodsreceived.setVisible(false);
      add(purchaseitems,BorderLayout.CENTER);
     this.purchaseitems.setVisible(true);
    
     }
     else if(e.getSource() == goods_received_menu){
         this.storeControl.setVisible(false);
     this.storeitems.setVisible(false);
     this.issueControl.setVisible(false);
     this.issueditems.setVisible(false);
     this.purchaseControl.setVisible(false);
     this.purchaseitems.setVisible(false);
     add(goodsreceived,BorderLayout.CENTER);
     this.goodsreceived.setVisible(true);
     this.purchaseitems.setVisible(false);
     
     }
    }
    
}
