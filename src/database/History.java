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

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class History extends JFrame {

	private JPanel contentPane;
   Connection con = null;
   private JTable table;
   private String Ocode;
   private String Ccode;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					History frame = new History();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void RefreshTable(){
		  try{
			  String query = "select Ocode รหัสใบสั่งสินค้า,customer.Ccode รหัสลูกค้า,Cfname ชื่อลูกค้า,Clname นามสกุลลูกค้า,DatePlaces วันที่ซื้อสินค้า,Status สถานะชำระเงิน from orders,customer where customer.Ccode = orders.Ccode ORDER BY ocode";
			  PreparedStatement pst = con.prepareStatement(query);
			  ResultSet rs = pst.executeQuery();
			  table.setModel(DbUtils.resultSetToTableModel(rs));
			  pst.close();
			  rs.close();
			  
		  }catch(Exception e){
			  
			  e.printStackTrace();
		  }
		  
	  }

	public History() {
		con = DBConnected.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 710, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u0E1B\u0E23\u0E30\u0E27\u0E31\u0E15\u0E34\u0E01\u0E32\u0E23\u0E0B\u0E37\u0E49\u0E2D\u0E02\u0E32\u0E22");
		label.setBounds(320, 11, 135, 14);
		contentPane.add(label);
		
		JButton button = new JButton("\u0E15\u0E23\u0E27\u0E08\u0E2A\u0E2D\u0E1A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*String q = "select * from orders where ocode ='"+Ocode+"'";
				String State=null;
				try {
					PreparedStatement pst = con.prepareStatement(q);
					ResultSet rs = pst.executeQuery();
					if(rs.next()){
					State = rs.getString("Status");	
					System.out.println(State);	
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			
			Bill bill = new Bill();
			bill.fillBill(Ccode, Ocode);
			
			bill.setVisible(true);	
			RefreshTable();
			}
		});
		button.setBounds(286, 456, 103, 23);
		contentPane.add(button);
		table = new JTable();	
		JScrollPane scrollPane = new JScrollPane();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				   int row1 = table.getSelectedRow();
					Ocode = (table.getModel().getValueAt(row1, 0)).toString();
					Ccode =(table.getModel().getValueAt(row1, 1)).toString();
			}
		});
		scrollPane.setBounds(28, 48, 641, 380);
		contentPane.add(scrollPane);
		
	
		scrollPane.setViewportView(table);
		RefreshTable();
	}
}
