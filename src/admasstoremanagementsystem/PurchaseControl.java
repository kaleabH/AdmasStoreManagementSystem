/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admasstoremanagementsystem;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class PurchaseControl extends JPanel {

 
    
    //database variables
   
   public JTextField item_name;
   public JComboBox measurement;
   public JTextField requested_amount;
   public JTextField received_amount;
   
   public String item_id;
    
    private final JLabel il;
    private final JLabel ml;
    private final JLabel ral;
    private final JLabel rel;

    public final JButton request_purchase;
    public final JButton update_request;
    public final JButton delete_request;
    
    
    private final JPanel controlcontainer;
    private final JPanel labels;
    private final JPanel textfields;
    private final JPanel buttons;
     
    PurchaseControl()throws SQLException{
//          conn = null;
//          sql =null;
        
        setLayout(new GridLayout(2, 1));
        
//        variables declaration
    item_name = new JTextField(15);
    measurement = new JComboBox();
    requested_amount = new JTextField(15);
    received_amount = new JTextField(15);
    
    item_id = new String();
    
    il = new JLabel("item name");
    ml = new JLabel("measurement");
    ral = new JLabel("requested amount");
    rel = new JLabel("received amount");
  
    request_purchase = new JButton("request purchase");
    update_request = new JButton("update request");
    delete_request = new JButton("delete request");
    
    labels = new JPanel();
    textfields = new JPanel();
    controlcontainer = new JPanel();
    buttons = new JPanel();
    labels.add(il);
    labels.add(ml);
    labels.add(ral);
    labels.add(rel);

    
    textfields.add(item_name);
       textfields.add(measurement);
       textfields.add(requested_amount);
    textfields.add(received_amount);
 
    
    buttons.add(request_purchase);
    buttons.add(update_request);
    buttons.add(delete_request);
 
    //add action listeners to the buttons
    
    
    
    //add mouse exit action listener for the claster balance text field

    
    textfields.setLayout(new GridLayout(7, 1));
    labels.setLayout(new GridLayout(7, 1));
    controlcontainer.setLayout(new GridLayout(1, 2));
    controlcontainer.add(labels);
    controlcontainer.add(textfields);

    this.setPreferredSize(new Dimension(300, 500));
    add(controlcontainer);
    add(buttons);
    }
}
