package database;
import java.awt.print.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.JScrollPane;
;
public class BuyProduct extends JFrame {

	private JPanel contentPane;
	private JTextField textNum;
	private JTable table;
	private JTextField textOcode;
	private JComboBox comboBoxPname;
	private String Ocode="";
	private String Ccode="";
	private String ECODEFILLED="";
	private String ECODEDELIVER="";
	private JComboBox BoxPlaceBill;
	private JComboBox BoxSendOb;
	private String Date=null;
	private String[] ColumnName = {"ชื่อสินค้า","จำนวนสินค้า","ราคา"};

	private String select=null;
    Connection con = null;
    private JTextField textDate;
    private String num;
    
    public void setCcode(String Ccodes){
    	
    	this.Ccode = Ccodes;
    	//System.out.println(Ccode);
    }
    public void CurrentDay(){
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, 1);
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    	
        Date = format1.format(cal.getTime());
        textDate.setText(Date);
    	
    }
    
    public void fillComboBox1(){
    	String query = "select * from  Employee ,Departmet where Employee.dcode=Departmet.dcode and Employee.dcode ='D001'";
    	
    	try {PreparedStatement pst;
       
    		 pst = con.prepareStatement(query);
    		ResultSet rs = pst.executeQuery();
    		while(rs.next()){
    			BoxPlaceBill.addItem(rs.getString("Fname"));
    		}

    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	
    }
    public void fillComboBox2(){
    	String query = "select * from  Employee ,Departmet where Employee.dcode=Departmet.dcode and Employee.dcode ='D002'";
    	
    	try {PreparedStatement pst;
       
    		 pst = con.prepareStatement(query);
    		ResultSet rs = pst.executeQuery();
    		while(rs.next()){
    			BoxSendOb.addItem(rs.getString("Fname"));
    		}

    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	
    }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyProduct frame = new BuyProduct();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void refreshValue(){
		textNum.setText(null);
		
		
		
	}
	

	
public void fillComboBox(){
	String query = "select * from product ";
	PreparedStatement pst;
	try {
		pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
		    comboBoxPname.addItem(rs.getString("Pname"));
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}	
public void refreshOcode(){
	try{int isum;
		String sql = "select count(Ocode) from orders";
		con = DBConnected.dbConnector(); 
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
    
		if(rs.next()){

		String sum = rs.getString("count(Ocode)");
		   isum = Integer.parseInt(sum);
		    isum = isum+1;
		  if(isum<10){
		    sum = Integer.toString(isum);
		   Ocode = "O00"+sum;
		textOcode.setText(Ocode);
		}else if(isum<100){
		    sum = Integer.toString(isum);
		    Ocode = "O0"+sum;
		textOcode.setText(Ocode);
		}else if(isum<1000){
		    sum = Integer.toString(isum);
		    Ocode = "O"+sum;
		textOcode.setText(Ocode);
		}
		}
		} catch(Exception e1){
			e1.printStackTrace();
		}
	
	
}
public void ClearTable(){
	DefaultTableModel model =(DefaultTableModel)table.getModel();
	while(model.getRowCount()>0){
		for(int i = 0;i<model.getColumnCount();i++){
			model.removeRow(i);
		}
		
	}
}
	
	public BuyProduct() {
		con = DBConnected.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 651, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		
		JLabel label_5 = new JLabel("\u0E0A\u0E37\u0E48\u0E2D\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		label_5.setBounds(31, 53, 59, 14);
		contentPane.add(label_5);
		
		JLabel label = new JLabel("\u0E08\u0E33\u0E19\u0E27\u0E19");
		label.setBounds(304, 53, 46, 14);
		contentPane.add(label);
		
		textNum = new JTextField();
		textNum.setBounds(370, 50, 86, 20);
		contentPane.add(textNum);
		textNum.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 109, 574, 300);
		contentPane.add(scrollPane);
		
		JTable table = new JTable(new DefaultTableModel(new Object[]{"รหัสสินค้า", "ชื่อสินค้า", "จำนวนสินค้า", "ราคารวม"}, 0));
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("\u0E40\u0E1E\u0E34\u0E48\u0E21\u0E23\u0E32\u0E22\u0E01\u0E32\u0E23");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(Ccode);
				
				select = (String)comboBoxPname.getSelectedItem();
				//System.out.println(select);
				//System.out.println(textNum.getText());
				String q = "select * from product where pname='"+select+"'";
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				PreparedStatement pst;
				ResultSet rs;
				try {
					pst = con.prepareStatement(q);
					rs = pst.executeQuery();
					if(rs.next()){
					String Pcode = rs.getString("Pcode");
					String Price = rs.getString("Price");
					double sum = Double.parseDouble(Price) * Double.parseDouble(textNum.getText());
					model.insertRow(model.getRowCount(), new Object[]{Pcode,select,textNum.getText(),sum});
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				refreshValue();
			}
		});
		button.setBounds(498, 49, 107, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u0E22\u0E37\u0E19\u0E22\u0E31\u0E19");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "ต้องการยืนยันข้อมูลหรือไม่","คำเตือน",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
				DBConnected db = new DBConnected();
                Connection con = (Connection) db.dbConnector();
                PreparedStatement pst;
                ResultSet rs;
                String Ocode = textOcode.getText();
                String Ecodefilled = null;
                try {
                	String query = "select Fcode from  Employee where Employee.Fname ='"+ (String)BoxPlaceBill.getSelectedItem() +"'";
                	pst = (PreparedStatement) con.prepareStatement(query);
                	rs = pst.executeQuery();
					if (rs.next()) {
						Ecodefilled = rs.getString("Fcode");
						//System.out.println(Ecodefilled);
					}
				   
				} catch (SQLException eef) {
					eef.printStackTrace();
				}
                String Ecodedeliver = null;
                try {
                	String query = "select Fcode from  Employee where Employee.Fname ='"+ (String)BoxSendOb.getSelectedItem() +"'";
                	pst = (PreparedStatement) con.prepareStatement(query);
                	rs = pst.executeQuery();
					if (rs.next()) {
						Ecodedeliver = rs.getString("Fcode");
						//System.out.println(Ecodedeliver);
					}
				   
				} catch (SQLException eed) {
					eed.printStackTrace();
				}
                String Dateplaces = textDate.getText();
                String Datepay = "NULL";
                String Datediliver = "NULL";
                String Status = "ค้างชำระ";
                String Total = null;
                try {
                	String query = "INSERT INTO orders (Ocode, Ccode, Ecodefilled, Ecodedeliver, Dateplaces, Datepay, Datediliver, Status, Total) VALUES ('"+ Ocode +"', '"+ Ccode +"', '"+ Ecodefilled +"', '"+ Ecodedeliver +"', '"+ Dateplaces +"', "+ Datepay +", "+ Datediliver +", '"+ Status +"', NULL);";
					pst = (PreparedStatement) con.prepareStatement(query);
					pst.executeUpdate();
					//JOptionPane.showMessageDialog(null, "เพิ่มสินค้าเรียบร้อย");
				   
				} catch (SQLException es) {
					es.printStackTrace();
				}
                
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                String Pcode = null;
                String Amount = null;
                String TotPrice = null;
                Double total = 0.00;
                String Odcode = null;
                for (int count = 0; count < model.getRowCount(); count++){
                	  Pcode = model.getValueAt(count, 0).toString();
                	  Amount = model.getValueAt(count, 2).toString();
                	  TotPrice = model.getValueAt(count, 3).toString();
                	  try 
                	  {
                		int od;
                		String sql = "select count(Odcode) from oderdetail";
                		pst = (PreparedStatement) con.prepareStatement(sql);
                		rs = pst.executeQuery();
                		if(rs.next()){
                			String sum = rs.getString("count(Odcode)");
                			od = Integer.parseInt(sum);
                			od = od+1;
                			//System.out.println(od);
                			  if(od<10){
                			    sum = Integer.toString(od);
                			    Odcode = "OD000"+sum;
                			}else if(od<100){
                			    sum = Integer.toString(od);
                			    Odcode = "OD00"+sum;
                			}else if(od<1000){
                			    sum = Integer.toString(od);
                			    Odcode = "OD0"+sum;
                			}else if(od<10000){
                			    sum = Integer.toString(od);
                			    Odcode = "OD"+sum;
                			}
                		}
                      	String query = "INSERT INTO oderdetail (Odcode, Ocode, Pcode, Amount, Price) VALUES ('"+ Odcode +"', '"+ Ocode +"', '"+ Pcode +"', '"+ Amount +"', '"+ TotPrice +"');";
                         
                      	pst = (PreparedStatement) con.prepareStatement(query);
                        pst.executeUpdate();
                        String sqlr ="Update product SET amount = amount - '"+Amount+ "' WHERE Pcode ='"+Pcode+"'";
                        pst = (PreparedStatement) con.prepareStatement(sqlr);
                        pst.executeUpdate();
      					//JOptionPane.showMessageDialog(null, "เพิ่มสินค้าเรียบร้อย");
      				   
      				 	} catch (SQLException eod) {
      					eod.printStackTrace();
      				 	}
                	  total += Double.parseDouble(model.getValueAt(count, 3).toString());
              	  
              
                	}
                try {
                	String query = "UPDATE orders SET Total = '"+ total +"' WHERE Ocode = '"+ Ocode +"';";
					pst = (PreparedStatement) con.prepareStatement(query);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "เพิ่มสินค้าเรียบร้อย");
					
					ClearTable();
				   
				} catch (SQLException es) {
					es.printStackTrace();
				}
                
			}}
		});
		button_1.setBounds(267, 484, 99, 23);
		contentPane.add(button_1);
		
		JLabel label_1 = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E23\u0E32\u0E22\u0E01\u0E32\u0E23");
		label_1.setBounds(31, 432, 78, 14);
		contentPane.add(label_1);
		
		textOcode = new JTextField();
		textOcode.setBounds(123, 429, 99, 20);
		contentPane.add(textOcode);
		textOcode.setColumns(10);
		
		JLabel label_2 = new JLabel("\u0E02\u0E32\u0E22\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		label_2.setBounds(279, 11, 71, 14);
		contentPane.add(label_2);
		
		comboBoxPname = new JComboBox();
		comboBoxPname.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			 select = (String)comboBoxPname.getSelectedItem();
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			
			}
		});
		comboBoxPname.setBounds(100, 50, 173, 20);
		contentPane.add(comboBoxPname);
		
		JLabel label_3 = new JLabel("\u0E1E\u0E19\u0E31\u0E01\u0E07\u0E32\u0E19\u0E2D\u0E2D\u0E01\u0E1A\u0E34\u0E25");
		label_3.setBounds(267, 420, 92, 14);
		contentPane.add(label_3);
		
		BoxPlaceBill = new JComboBox();
		BoxPlaceBill.setBounds(385, 420, 220, 20);
		contentPane.add(BoxPlaceBill);
		
		JLabel lblNewLabel = new JLabel("\u0E1E\u0E19\u0E31\u0E01\u0E07\u0E32\u0E19\u0E2A\u0E48\u0E07\u0E02\u0E2D\u0E07");
		lblNewLabel.setBounds(267, 448, 99, 14);
		contentPane.add(lblNewLabel);
		
		 BoxSendOb = new JComboBox();
		BoxSendOb.setBounds(385, 445, 220, 20);
		contentPane.add(BoxSendOb);
		
		JLabel label_4 = new JLabel("\u0E27\u0E31\u0E19\u0E2D\u0E2D\u0E01\u0E1A\u0E34\u0E25");
		label_4.setBounds(29, 472, 80, 14);
		contentPane.add(label_4);
		
		textDate = new JTextField();
		textDate.setBounds(123, 469, 134, 20);
		contentPane.add(textDate);
		textDate.setColumns(10);
		fillComboBox();
		refreshOcode();
		fillComboBox1();
		fillComboBox2();
		CurrentDay();
	}
	
}
