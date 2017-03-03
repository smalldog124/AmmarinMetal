package database;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HistCust extends JFrame {

	private JPanel contentPane;
	private JTextField textCID;
	private JTextField textCfname;
	private JTextField textClname;
	private JTextField textTel;
	private JTextField textAddr;
	Connection con = null;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label_5;
	private JPanel panel_1;
	private JTable table_1;
	private JTable table_2;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	public String Ccode;
	public String Ocode;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistCust frame = new HistCust();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void RefreshTable(){
		  try{
			  String query = "select Ccode รหัสสินค้า,Cfname ชื่อลูกค้า,Clname นามสกุลลูกค้า from customer";
			  PreparedStatement pst = con.prepareStatement(query);
			  ResultSet rs = pst.executeQuery();
			  table.setModel(DbUtils.resultSetToTableModel(rs));
			  pst.close();
			  rs.close();
			  
		  }catch(Exception e){
			  
			  e.printStackTrace();
		  }
		  
	  }
	public void RefreshTable1(String Ccode){
		  try{
			  String query = "select Ocode,Dateplaces,Datepay,Status from orders where Status = 'ชำระแล้ว' and Ccode='"+Ccode+"'";
			  PreparedStatement pst = con.prepareStatement(query);
			  ResultSet rs = pst.executeQuery();
			  table_1.setModel(DbUtils.resultSetToTableModel(rs));
			  pst.close();
			  rs.close();
			  
		  }catch(Exception e){
			  
			  e.printStackTrace();
		  }
		  
	  }
	public void RefreshTable2(String Ccode){
		  try{
			  String query = "select Ocode,Dateplaces,Datepay,Status from orders where Status = 'ค้างชำระ' and Ccode='"+Ccode+"'";
			  PreparedStatement pst = con.prepareStatement(query);
			  ResultSet rs = pst.executeQuery();
			  table_2.setModel(DbUtils.resultSetToTableModel(rs));
			  pst.close();
			  rs.close();
			  
		  }catch(Exception e){
			  
			  e.printStackTrace();
		  }
		  
	  }
	public HistCust() {
		con = DBConnected.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 932, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		
		textCID = new JTextField();
		textCID.setBounds(100, 74, 86, 20);
		contentPane.add(textCID);
		textCID.setColumns(10);
		
		textCfname = new JTextField();
		textCfname.setBounds(100, 114, 149, 20);
		contentPane.add(textCfname);
		textCfname.setColumns(10);
		
		textClname = new JTextField();
		textClname.setBounds(340, 114, 175, 20);
		contentPane.add(textClname);
		textClname.setColumns(10);
		
		textTel = new JTextField();
		textTel.setColumns(10);
		textTel.setBounds(100, 160, 147, 20);
		contentPane.add(textTel);
		
		textAddr = new JTextField();
		textAddr.setColumns(10);
		textAddr.setBounds(99, 202, 382, 20);
		contentPane.add(textAddr);
		
		JLabel label = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E25\u0E39\u0E01\u0E04\u0E49\u0E32");
		label.setBounds(10, 77, 70, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u0E0A\u0E37\u0E48\u0E2D\u0E08\u0E23\u0E34\u0E07");
		label_1.setBounds(10, 117, 68, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u0E19\u0E32\u0E21\u0E2A\u0E01\u0E38\u0E25");
		label_2.setBounds(283, 117, 59, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u0E40\u0E1A\u0E2D\u0E23\u0E4C\u0E42\u0E17\u0E23\u0E28\u0E31\u0E1E\u0E17\u0E4C");
		label_3.setBounds(10, 163, 96, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u0E17\u0E35\u0E48\u0E2D\u0E22\u0E39\u0E48");
		label_4.setBounds(10, 205, 46, 14);
		contentPane.add(label_4);
		table = new JTable();
		scrollPane = new JScrollPane();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{ int row = table.getSelectedRow();
				
			    Ccode = (table.getModel().getValueAt(row, 0)).toString();
			    String query = "select * from customer where Ccode='"+Ccode+"' ";
				  PreparedStatement pst = con.prepareStatement(query);
				  ResultSet rs = pst.executeQuery();
				  while(rs.next()){
					  String add1 = rs.getString("Ccode");
					  textCID.setText(add1);
					  String add2 = rs.getString("Cfname");
					  textCfname.setText(add2);
					  String add3 = rs.getString("Clname");
					  textClname.setText(add3);
					  String add4 = rs.getString("Tel");
					  textTel.setText(add4);
					  String add5 = rs.getString("Address");
					  textAddr.setText(add5);
					  
				  }
				  RefreshTable1(Ccode);
				  RefreshTable2(Ccode);
				
			}catch(Exception e1){
				JOptionPane.showMessageDialog(null, e1);
			}
			
			}
		});
		scrollPane.setBounds(527, 50, 379, 387);
		contentPane.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		
		label_5 = new JLabel("\u0E1B\u0E23\u0E30\u0E27\u0E31\u0E15\u0E34\u0E25\u0E39\u0E01\u0E04\u0E49\u0E32");
		label_5.setBounds(453, 23, 86, 14);
		contentPane.add(label_5);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(29, 262, 461, 293);
		contentPane.add(tabbedPane);
		tabbedPane.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	            RefreshTable1(Ccode);
	            RefreshTable2(Ccode);
	        }
	    });
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("ชำระแล้ว", null, panel, null);
		panel.setLayout(null);
		table_1 = new JTable();
		scrollPane_1 = new JScrollPane();
		table_1 .addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				   int row1 = table_1.getSelectedRow();
					Ocode = (table_1.getModel().getValueAt(row1, 0)).toString();
					
				
			}
		});
		scrollPane_1.setBounds(10, 11, 436, 210);
		panel.add(scrollPane_1);
		
		
		scrollPane_1.setViewportView(table_1);
		
		btnNewButton = new JButton("\u0E15\u0E23\u0E27\u0E08\u0E2A\u0E2D\u0E1A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
Bill bill = new Bill();
				
				bill.fillBill(Ccode, Ocode);
				bill.setVisible(true);
			}
		});
		btnNewButton.setBounds(326, 232, 104, 23);
		panel.add(btnNewButton);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("ค้างชำระ", null, panel_1, null);
		panel_1.setLayout(null);
		
		table_2 = new JTable();
		scrollPane_2 = new JScrollPane();
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 
				int row1 = table_2.getSelectedRow();
					Ocode = (table_2.getModel().getValueAt(row1, 0)).toString();
				
			}
		});
		scrollPane_2.setBounds(10, 11, 436, 210);
		panel_1.add(scrollPane_2);
		
		
		scrollPane_2.setViewportView(table_2);
		
		btnNewButton_1 = new JButton("\u0E15\u0E23\u0E27\u0E08\u0E2A\u0E2D\u0E1A");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
Bill bill = new Bill();
				
				bill.fillBill(Ccode, Ocode);
				bill.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(325, 232, 106, 23);
		panel_1.add(btnNewButton_1);
		 RefreshTable();
	}
}
