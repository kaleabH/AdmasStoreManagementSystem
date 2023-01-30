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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.sql.SQLException;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class StoreControl extends JPanel implements ActionListener{
    
    //database variables
   
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public JTextField item_name;
   public JTextField total_balance;
   public JTextField claster_balance;
   public JTextField claster;
   public JTextField measurement;
   public JTextField depletion_limit;
   public JTextField depletion_measurement;
  
   
   
    
    private final JLabel il;
    private final JLabel tbl;
    private final JLabel cbl;
    private final JLabel cl;
    private final JLabel ml;
    private final JLabel dl;
    private final JLabel dml;
    
    public final JButton add_item;
    public  final JButton update;
    public final JButton delete;
    public final JButton issue_item;
    public final JButton request_purchase;
    
    private final JPanel controlcontainer;
    private final JPanel labels;
    private final JPanel textfields;
    private final JPanel buttons;
   
     // action listener
   @Override
   public void actionPerformed(ActionEvent e){

 
   }
   
    StoreControl()throws SQLException{
//          conn = null;
//          sql =null;
        
        setLayout(new GridLayout(2, 1));
        
//        variables declaration
    item_name = new JTextField(15);
    total_balance = new JTextField(15);
    claster_balance = new JTextField(15);
    claster = new JTextField(15);
    measurement = new JTextField(15);
    depletion_limit = new JTextField(15);
    depletion_measurement = new JTextField(15);
    
    il = new JLabel("item name");
    tbl = new JLabel("total balance");
    cbl = new JLabel("claster balance");
    cl = new JLabel("claster");
    ml = new JLabel("measurement");
    dl = new JLabel("depletion limit");
    dml = new JLabel("depletion measurement");
    
    add_item = new JButton("add item");
    update = new JButton("update");
    delete = new JButton("delete");
    issue_item = new JButton("issue item");
    request_purchase = new JButton("request purchase");
    
    labels = new JPanel();
    textfields = new JPanel();
    controlcontainer = new JPanel();
    buttons = new JPanel();
    labels.add(il);
    labels.add(ml);
    labels.add(cl);
    labels.add(cbl);
    labels.add(tbl);
    labels.add(dl);
    labels.add(dml);
    
    textfields.add(item_name);
      textfields.add(measurement);
       textfields.add(claster);
       textfields.add(claster_balance);
       textfields.add(total_balance);
    textfields.add(depletion_limit);
    textfields.add(depletion_measurement);
    
    buttons.add(add_item);
    buttons.add(update);
    buttons.add(delete);
    buttons.add(issue_item);
    buttons.add(request_purchase);
    //add action listeners to the buttons
    
    
    
    //add mouse exit action listener for the claster balance text field
    claster_balance.addFocusListener(new FocusAdapter(){
    
       @Override
        public void focusLost(FocusEvent e){
            //checks if claster_balance is an integer
        if(Integer.parseInt(claster_balance.getText())==(int)Integer.parseInt(claster_balance.getText()) && Integer.parseInt(claster_balance.getText())>0){
          if(Integer.parseInt(claster.getText())==(int)Integer.parseInt(claster.getText())){
              int c = Integer.parseInt(claster_balance.getText());
              int cBalance = Integer.parseInt(claster.getText());
              Integer tBalance = c * cBalance;
            total_balance.setText(Integer.toString(tBalance));
          }
        }
        }
    
    });
    
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
