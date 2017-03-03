package database;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Product extends JFrame {

	private JPanel contentPane;
	public JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product frame = new Product();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 Connection con = null;
 private JTextField textPID;
 private JTextField textPname;
 private JTextField textPrice;
 private JTextField textAmount;
 private JTextField textPtcode;

  
 public void RefreshTable(){
	  try{
		  //System.out.println("REFRESH");
		  con = DBConnected.dbConnector();
		  String query = "select Pcode รหัสสินค้า,Pname ชื่อสินค้า,Price ราคาสินค้า ,Amount จำนวนสินค้า ,Ptype.Pdesc ประเภทสินค้า,Product.Ptcode รหัสประเภทสินค้า from product,ptype where product.ptcode=ptype.ptcode;";
		  PreparedStatement pst = con.prepareStatement(query);
		  ResultSet rs = pst.executeQuery();
		  table.setModel(DbUtils.resultSetToTableModel(rs));
		  pst.close();
		  rs.close();
		  
	  }catch(Exception e){
		  
		  e.printStackTrace();
	  }
	  
  }
	
 
 public void sendBoolean(boolean t){
	 RefreshTable();
	 
 }
	
	public Product() {
		con = DBConnected.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1001, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		
		JLabel lblb = new JLabel("\u0E23\u0E32\u0E22\u0E01\u0E32\u0E23\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		lblb.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblb.setBounds(433, 11, 112, 14);
		contentPane.add(lblb);
		table = new JTable(){
			public boolean isCellEditable(int row,int column){ 
		         return false;  
		       }  
		};
		
		JScrollPane scrollPane = new JScrollPane();
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				
				try 
				{
					int row = table.getSelectedRow();
				    String Pcode = (table.getModel().getValueAt(row, 0)).toString();
				    String query = "select * from product where Pcode='"+Pcode+"' ";
					PreparedStatement pst;
					pst = con.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{
						String add1 = rs.getString("Pcode");
						textPID.setText(add1);
						String add2 = rs.getString("Pname");
						textPname.setText(add2);
						String add3 = rs.getString("Price");
						textPrice.setText(add3);
						String add4 = rs.getString("Amount");
						textAmount.setText(add4);
						String add5 = rs.getString("Ptcode");
						textPtcode.setText(add5);
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				  
			
			
			}
		});
		scrollPane.setBounds(10, 42, 606, 485);
		contentPane.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "เพิ่มสินค้า", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(626, 40, 349, 358);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 16, 329, 331);
		panel_1.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		label.setBounds(26, 33, 67, 24);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u0E0A\u0E37\u0E48\u0E2D\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		label_1.setBounds(26, 77, 67, 24);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u0E23\u0E32\u0E04\u0E32");
		label_2.setBounds(26, 125, 67, 24);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u0E08\u0E33\u0E19\u0E27\u0E19");
		label_3.setBounds(26, 173, 67, 24);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E0A\u0E19\u0E34\u0E14\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		label_4.setBounds(26, 222, 96, 24);
		panel.add(label_4);
		
		textPID = new JTextField();
		textPID.setBounds(120, 35, 177, 20);
		//textPID.setEditable(false);
		textPID.setColumns(10);
		panel.add(textPID);
		
		textPname = new JTextField();
		textPname.setColumns(10);
		textPname.setBounds(120, 79, 177, 20);
		panel.add(textPname);
		
		textPrice = new JTextField();
		textPrice.setColumns(10);
		textPrice.setBounds(120, 127, 177, 20);
		panel.add(textPrice);
		
		textAmount = new JTextField();
		textAmount.setColumns(10);
		textAmount.setBounds(120, 175, 177, 20);
		panel.add(textAmount);
		
		textPtcode = new JTextField();
		textPtcode.setColumns(10);
		textPtcode.setBounds(120, 224, 177, 20);
		panel.add(textPtcode);
		
		JButton button_1 = new JButton("\u0E25\u0E1A\u0E02\u0E49\u0E2D\u0E21\u0E39\u0E25");
		button_1.setBounds(829, 409, 134, 23);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
					
				String Pcode = (table.getModel().getValueAt(table.getSelectedRow(), 0)).toString();
					
				int dialogResult = JOptionPane.showConfirmDialog (null, "ยืนยันการลบข้อมูล?","คำเตือน",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					String query = "DELETE FROM product WHERE Pcode = '" + Pcode + "';";
					try 
					{
		                PreparedStatement pst = con.prepareStatement(query);
						pst.executeUpdate();
						RefreshTable();
					}
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u0E41\u0E01\u0E49\u0E44\u0E02\u0E02\u0E49\u0E2D\u0E21\u0E39\u0E25");
		button_2.setBounds(670, 409, 144, 23);
		contentPane.add(button_2);
		
		JButton addButton = new JButton("\u0E40\u0E1E\u0E34\u0E48\u0E21\u0E23\u0E32\u0E22\u0E01\u0E32\u0E23\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddProd add = new AddProd();
				add.setVisible(true);
			}
		});
		addButton.setBounds(670, 443, 144, 23);
		contentPane.add(addButton);
		
		JButton btnNewButton = new JButton("\u0E42\u0E2B\u0E25\u0E14\u0E2B\u0E19\u0E49\u0E32\u0E43\u0E2B\u0E21\u0E48");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RefreshTable();
			}
		});
		btnNewButton.setBounds(829, 443, 134, 23);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("\u0E19\u0E33\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32\u0E40\u0E02\u0E49\u0E32");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ImportProduct importprob = new ImportProduct();	
			importprob.setVisible(true);
			}
		});
		button.setBounds(670, 477, 144, 23);
		contentPane.add(button);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
					
				String Pcode = (table.getModel().getValueAt(table.getSelectedRow(), 0)).toString();
					
				int dialogResult = JOptionPane.showConfirmDialog (null, "ยืนยันการแก้ไขข้อมูล สินค้ารหัส "+ Pcode +"?\nคำเตือน : รหัสสินค้าไม่สามารถเปลี่ยนได้","คำเตือน",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
				String query = "UPDATE product SET Pcode = '"+ Pcode +"'" +
									",Pname = '"+ textPname.getText() + "'" +
									",Price = '"+ textPrice.getText() + "'" +
									",Amount = '"+ textAmount.getText() + "'" +
									",Ptcode = '"+ textPtcode.getText() + "'" +
									"WHERE Pcode ='"+ Pcode + "';";
					try 
					{		
	                	PreparedStatement pst = con.prepareStatement(query);
						
						pst.executeUpdate();
					    RefreshTable();
					}
					 catch (SQLException e1) {
						System.out.println(query);
						//e1.printStackTrace();
					}
				}
			}
		});
		RefreshTable();
	}
}
