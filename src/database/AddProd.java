package database;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;


public class AddProd extends JFrame {

	private JPanel contentPane;
	private JTextField textPID;
	private JTextField textPname;
	private JTextField textPrice;
	private JTextField textAmount;
	private JComboBox BoxPdesc;
	private String select = "";
	private String Ptcode = "";
    Product prod = new Product();
    public String Pcode;
	Connection con1=null;
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProd frame = new AddProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
public void refreshValue(){
	textPname.setText(null);
	textPrice.setText(null);
	textAmount.setText(null);
	
	
}
public void fillComboBox(){
	String query = "select * from ptype ";
	
	try {PreparedStatement pst;
      	con1 = DBConnected.dbConnector(); 
		pst = con1.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			BoxPdesc.addItem(rs.getString("Pdesc"));
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}

public void refreshPcode(){
	try{int isum;
		String sql = "select count(Pcode) from Product";
		con1 = DBConnected.dbConnector(); 
		PreparedStatement pst = con1.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
    
		if(rs.next()){

		String sum = rs.getString("count(Pcode)");
		   isum = Integer.parseInt(sum);
		  if(isum<10){
		    isum = isum+2;
		    sum = Integer.toString(isum);
		   Pcode = "P00"+sum;
		textPID.setText(Pcode);
		}else if(isum<100){
		    isum = isum+2;
		    sum = Integer.toString(isum);
		    Pcode = "P0"+sum;
		textPID.setText(Pcode);
		}else if(isum<1000){
		  isum = isum+2;
		    sum = Integer.toString(isum);
		    Pcode = "P"+sum;
		textPID.setText(Pcode);
		}
		}
		} catch(Exception e1){
			e1.printStackTrace();
		}
	
	
}
	public AddProd() {
		DBConnected db = new DBConnected();
		prod = new Product();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 482, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u0E40\u0E1E\u0E34\u0E48\u0E21\u0E23\u0E32\u0E22\u0E01\u0E32\u0E23\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		label.setBounds(194, 11, 115, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		label_1.setBounds(43, 67, 80, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u0E0A\u0E37\u0E48\u0E2D\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		label_2.setBounds(43, 110, 58, 14);
		contentPane.add(label_2);
		
		JLabel lblNewLabel = new JLabel("\u0E23\u0E32\u0E04\u0E32");
		lblNewLabel.setBounds(43, 147, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel label_3 = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E0A\u0E19\u0E34\u0E14\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		label_3.setBounds(43, 234, 90, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u0E08\u0E33\u0E19\u0E27\u0E19");
		label_4.setBounds(43, 188, 46, 14);
		contentPane.add(label_4);
		
		textPID = new JTextField();
		textPID.setBounds(143, 64, 176, 20);
		contentPane.add(textPID);
		textPID.setColumns(10);
		
		textPname = new JTextField();
		textPname.setColumns(10);
		textPname.setBounds(143, 107, 176, 20);
		contentPane.add(textPname);
		
		textPrice = new JTextField();
		textPrice.setColumns(10);
		textPrice.setBounds(143, 144, 176, 20);
		contentPane.add(textPrice);
		
		textAmount = new JTextField();
		textAmount.setColumns(10);
		textAmount.setBounds(143, 185, 176, 20);
		contentPane.add(textAmount);
		
		JButton button = new JButton("\u0E40\u0E1E\u0E34\u0E48\u0E21\u0E23\u0E32\u0E22\u0E01\u0E32\u0E23\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "ต้องการยืนยันข้อมูลหรือไม่","คำเตือน",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
				select = (String)BoxPdesc.getSelectedItem();
				String query1 = "select * from ptype where pdesc = '"+select+"';";
				con1 = (Connection) db.dbConnector();
				PreparedStatement pst;
				//System.out.println(select);
				try {
					pst = con1.prepareStatement(query1);
					ResultSet rs = pst.executeQuery();
					if (rs.next()) {
					Ptcode = rs.getString("Ptcode");
					//System.out.println(Ptcode);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//System.out.println(Ptcode);
					e.printStackTrace();
				}
				
				String query = "INSERT INTO product (Pcode,Pname,Price,Amount,ptcode) value(?,?,?,?,?) ";
                Connection con = (Connection) db.dbConnector();
                try {
					pst = (PreparedStatement) con.prepareStatement(query);
					pst.setString(1,textPID.getText());
					pst.setString(2, textPname.getText());
					pst.setString(3, textPrice.getText());
					pst.setString(4, textAmount.getText());
				    pst.setString(5,Ptcode);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "เพิ่มสินค้าเรียบร้อย");
				   
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
                prod.RefreshTable();
            	refreshValue();
        		refreshPcode();
        	    
				
			}
				}
		});
		button.setBounds(165, 284, 144, 23);
		contentPane.add(button);
		
		BoxPdesc = new JComboBox();
		BoxPdesc.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				
				/*select = (String)BoxPdesc.getSelectedItem();
				String query = "select * from ptype where pdesc = '"+select+"';";
				con1 = (Connection) db.dbConnector();
				PreparedStatement pst;
				System.out.println(select);
				try {
					pst = con1.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					if (rs.next()) {
					Ptcode = rs.getString("Ptcode");
					//System.out.println(Ptcode);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//System.out.println(Ptcode);
					e.printStackTrace();
				}*/
				
				
				
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		BoxPdesc.setBounds(143, 231, 176, 20);
		contentPane.add(BoxPdesc);
		fillComboBox();
		refreshPcode();
		
	
	}
}
