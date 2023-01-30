/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admasstoremanagementsystem;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
public class IssueControl extends JPanel implements ActionListener{

    
    //database variables
   
   public JTextField item_name;
   public JTextField amount_requested;
   public JTextField amount_issued;
   public JComboBox measurement;
   public JTextField issued_to;
   public JTextField store_requisition_no;
   public JTextField voucher_no;
  public JTextField remark;
   
   public String item_id;
    
    private final JLabel il;
    private final JLabel arl;
    private final JLabel ail;
    private final JLabel ml;
    private final JLabel itl;
    private final JLabel srl;
    private final JLabel vl;
    private final JLabel rl;
  
    public final JButton issue_item;
    public final JButton update_issue;
    public final JButton delete_issue;
    
    
    private final JPanel controlcontainer;
    private final JPanel labels;
    private final JPanel textfields;
    private final JPanel buttons;
   
     // action listener
   @Override
   public void actionPerformed(ActionEvent e){

 
   }
   
    IssueControl()throws SQLException{
//          conn = null;
//          sql =null;
        
        setLayout(new GridLayout(2, 1));
        
//        variables declaration
    item_name = new JTextField(15);
    amount_requested = new JTextField(15);
    amount_issued = new JTextField(15);
    measurement = new JComboBox();
    issued_to = new JTextField(15);
    store_requisition_no = new JTextField(15);
    voucher_no = new JTextField(15);
    remark = new JTextField(15);
    
    il = new JLabel("item name");
    arl = new JLabel("amount requested");
    ail = new JLabel("amount issued");
    ml = new JLabel("measurement");
    itl = new JLabel("issued to");
    srl = new JLabel("store requisition No");
    vl = new JLabel("voucher No");
    rl = new JLabel("remark");
    
    item_id = new String();
   
    issue_item = new JButton("issue item");
    update_issue = new JButton("update issue");
    delete_issue = new JButton("delete issue");
    
    labels = new JPanel();
    textfields = new JPanel();
    controlcontainer = new JPanel();
    buttons = new JPanel();
    labels.add(il);
    labels.add(arl);
    labels.add(ail);
    labels.add(ml);
    labels.add(itl);
    labels.add(srl);
    labels.add(vl);
    labels.add(rl);
    
    textfields.add(item_name);
      textfields.add(amount_requested);
       textfields.add(amount_issued);
       textfields.add(measurement);
       textfields.add(issued_to);
    textfields.add(store_requisition_no);
    textfields.add(voucher_no);
    textfields.add(remark);
    
    
    buttons.add(issue_item);
//    buttons.add(update_issue);
//    buttons.add(delete_issue);
 
    //add action listeners to the buttons
    
    
    
    //add mouse exit action listener for the claster balance text field

    
    textfields.setLayout(new GridLayout(8, 1));
    labels.setLayout(new GridLayout(8, 1));
    controlcontainer.setLayout(new GridLayout(1, 2));
    controlcontainer.add(labels);
    controlcontainer.add(textfields);

    this.setPreferredSize(new Dimension(300, 500));
    add(controlcontainer);
    add(buttons);
    }
}
