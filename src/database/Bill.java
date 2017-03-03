package database;
import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;

import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;

public class Bill extends JFrame {

	private JPanel contentPane;
    Connection con =null;
    private JTextField textCID;
    private JTextField textOcode;
    private JTextField textCfname;
    private JTextField textClname;
    private JTextField textTel;
    private JTextField textAddr;
    private String query;
    private JTable table;
    public String Ccode="",Ocode="" ;
    private JTable table_1;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private String State;
    private String Date;
    private JTextField textDate;
    
   
	
    public void SetBill(String Ccodez , String Ocodez){
		this.Ccode = Ccodez;
		this.Ocode = Ocodez;
		
	}
    public void SetBox(String State){
		this.State = State;
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bill frame = new Bill();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void ClearTable(){
		DefaultTableModel model =(DefaultTableModel)table.getModel();
		while(model.getRowCount()>0){
			for(int i = 0;i<model.getColumnCount();i++){
				model.removeRow(i);
			}
			
		}
	}

	public void ClearTable2(){
		DefaultTableModel model =(DefaultTableModel)table_1.getModel();
		while(model.getRowCount()>0){
			for(int i = 0;i<model.getColumnCount();i++){
				model.removeRow(i);
			}
			
		}
	}
	public void RefreshTable(String Ocodez){
		ClearTable();
		 try{
			 if(Ocodez!= "" && Ocodez !="null"){
				 /*query = "select product.pcode,oderdetail.amount ,product.price "
					  		+ "from orders,oderdetail,product "
					  		+ "where product.pcode = oderdetail.pcode and oderdetail.Ocode ='"+Ocodez+"' GROUP BY odcode";*/
				 query = "select pname ชื่อสินค้า ,oderdetail.amount จำนวน ,(oderdetail.price/oderdetail.amount) ราคาต่อชิ้น ,oderdetail.price ราคารวม "
			  		+ "from orders,oderdetail,product "
			  		+ "where product.pcode = oderdetail.pcode and oderdetail.Ocode ='"+Ocodez+"' GROUP BY odcode";
				 /*query = "select pname ชื่อสินค้า ,oderdetail.amount จำนวน ,product.price ราคา "
			  		+ "from orders,oderdetail,product "
			  		+ "where product.pcode = oderdetail.pcode and oderdetail.Ocode ='"+Ocodez+"' GROUP BY odcode";*/
			  //System.out.println(query);
			  PreparedStatement pst2 = con.prepareStatement(query);
			  ResultSet rs2 = pst2.executeQuery();
			  table.setModel(DbUtils.resultSetToTableModel(rs2));
			
			  pst2.close();
			  rs2.close();
			 }
			  
		  }catch(Exception e){
			  
			  e.printStackTrace();
		  }
		
		
	}
	public void RefreshTable2(String Ocodez){
		ClearTable2();
		 try{
			 if(Ocodez!= "" && Ocodez !="null"){
				
				 query = "select Total ราคารวม  from Orders where Ocode ='"+Ocodez+"'";
				
			  PreparedStatement pst2 = con.prepareStatement(query);
			  ResultSet rs2 = pst2.executeQuery();
			  table_1.setModel(DbUtils.resultSetToTableModel(rs2));
			
			  pst2.close();
			  rs2.close();
			 }
			  
		  }catch(Exception e){
			  
			  e.printStackTrace();
		  }
		
		
	}
	
	public void fillBill(String Ccode,String Ocode){
		
	
		try {	query = "select * from customer,orders where Customer.Ccode ='"+Ccode+"' and orders.Ccode = customer.Ccode and Ocode ='"+Ocode+"' ";
		PreparedStatement pst;
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			  while(rs.next()){
				  String add1 = rs.getString("Ocode");
				  textOcode.setText(add1);
				  String add2 = rs.getString("Ccode");
				  textCID.setText(add2);
				  String add3 = rs.getString("Cfname");
				  textCfname.setText(add3);
				  String add4 = rs.getString("Clname");
				  textClname.setText(add4);
				  String add5 = rs.getString("Tel");
				  textTel.setText(add5);
				  String add6 = rs.getString("Address");
				  textAddr.setText(add6);
				 
			  }
			  RefreshTable(Ocode);
			  RefreshTable2(Ocode);
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void CurrentDay(){
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, 1);
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date = format1.format(cal.getTime());
        textDate.setText(Date);
    	
    }
    
	
	public Bill() {
		con = DBConnected.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 770, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u0E43\u0E1A\u0E40\u0E2A\u0E23\u0E47\u0E08\u0E23\u0E31\u0E1A\u0E40\u0E07\u0E34\u0E19");
		label.setBounds(305, 11, 96, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E23\u0E32\u0E22\u0E01\u0E32\u0E23");
		label_1.setBounds(528, 11, 74, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E25\u0E39\u0E01\u0E04\u0E49\u0E32");
		label_2.setBounds(44, 41, 60, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u0E0A\u0E37\u0E48\u0E2D\u0E08\u0E23\u0E34\u0E07");
		label_3.setBounds(44, 66, 46, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u0E19\u0E32\u0E21\u0E2A\u0E01\u0E38\u0E25");
		label_4.setBounds(272, 66, 56, 14);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("\u0E42\u0E17\u0E23\u0E28\u0E31\u0E1E\u0E17\u0E4C");
		label_5.setBounds(480, 66, 74, 14);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("\u0E17\u0E35\u0E48\u0E2D\u0E22\u0E39\u0E48");
		label_6.setBounds(44, 91, 46, 14);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("\u0E23\u0E32\u0E22\u0E01\u0E32\u0E23\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		label_7.setBounds(315, 141, 86, 14);
		contentPane.add(label_7);
		
		textCID = new JTextField();
		textCID.setBounds(114, 38, 86, 20);
		contentPane.add(textCID);
		textCID.setColumns(10);
		
		textOcode = new JTextField();
		textOcode.setBounds(628, 8, 86, 20);
		contentPane.add(textOcode);
		textOcode.setColumns(10);
		
		textCfname = new JTextField();
		textCfname.setBounds(114, 63, 142, 20);
		contentPane.add(textCfname);
		textCfname.setColumns(10);
		
		textClname = new JTextField();
		textClname.setBounds(338, 63, 132, 20);
		contentPane.add(textClname);
		textClname.setColumns(10);
		
		textTel = new JTextField();
		textTel.setBounds(556, 63, 126, 20);
		contentPane.add(textTel);
		textTel.setColumns(10);
		
		textAddr = new JTextField();
		textAddr.setBounds(114, 91, 475, 20);
		contentPane.add(textAddr);
		textAddr.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 166, 670, 363);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Print");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
						 /*query = "select product.pcode,oderdetail.amount ,product.price "
							  		+ "from orders,oderdetail,product "
							  		+ "where product.pcode = oderdetail.pcode and oderdetail.Ocode ='"+Ocodez+"' GROUP BY odcode";*/
						 query = "select pname Product ,oderdetail.amount Amount ,(oderdetail.price/oderdetail.amount) UnitPrice ,oderdetail.price TotPrice "
					  		+ "from orders,oderdetail,product "
					  		+ "where product.pcode = oderdetail.pcode and oderdetail.Ocode ='"+textOcode.getText()+"' GROUP BY odcode";
						 /*query = "select pname ชื่อสินค้า ,oderdetail.amount จำนวน ,product.price ราคา "
					  		+ "from orders,oderdetail,product "
					  		+ "where product.pcode = oderdetail.pcode and oderdetail.Ocode ='"+Ocodez+"' GROUP BY odcode";*/
					  //System.out.println(query);
					  PreparedStatement pst2 = con.prepareStatement(query);
					  ResultSet rs2 = pst2.executeQuery();
					  table.setModel(DbUtils.resultSetToTableModel(rs2));
					
					  pst2.close();
					  rs2.close();
					  
				  }catch(Exception e1){
					  
					  e1.printStackTrace();
				  }
				PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
                set.add(OrientationRequested.PORTRAIT);
				MessageFormat header = new MessageFormat("ร้านศุภกร");
				MessageFormat footer = new MessageFormat("{0}");
				try {
					table.print(JTable.PrintMode.FIT_WIDTH, header, footer , true , set ,true);
				} catch (PrinterException e1) {
					//System.out.println("HELLO");
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(625, 137, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(480, 532, 232, 58);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JButton button = new JButton("\u0E0A\u0E33\u0E23\u0E30\u0E43\u0E1A\u0E40\u0E2A\u0E23\u0E47\u0E08");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                	String query = "UPDATE orders SET Status = 'ชำระแล้ว',Datepay = '"+textDate.getText()+"',Datediliver ='"+textDate.getText()+"' WHERE Ocode = '"+ textOcode.getText() +"';";
                	//System.out.println(query);
					PreparedStatement pst = (PreparedStatement) con.prepareStatement(query);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "อัตเดตเรียบร้อย");
					
				} catch (SQLException es) {
					es.printStackTrace();
				}
			
			
			}
		});
		button.setBounds(79, 540, 120, 23);
		contentPane.add(button);
		
		textDate = new JTextField();
		textDate.setBounds(114, 574, 152, 20);
		contentPane.add(textDate);
		textDate.setColumns(10);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    	
        Date = format1.format(cal.getTime());
        textDate.setText(Date);
		
		JLabel label_8 = new JLabel("\u0E27\u0E31\u0E19\u0E17\u0E35\u0E48\u0E08\u0E48\u0E32\u0E22");
		label_8.setBounds(30, 576, 74, 14);
		contentPane.add(label_8);
		
		
		
	}
}
