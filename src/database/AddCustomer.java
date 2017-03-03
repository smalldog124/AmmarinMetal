package database;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField textCID;
	private JTextField textCfname;
	private JTextField textClname;
	private JTextField textTel;
	private JTextField textAddr;
    public String Ccode;
	/**
	 * Launch the application.
	 */
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCustomer frame = new AddCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void closeWindow(){
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
		
		
	}
	Connection con = null;
	public void refreshCcode(){
		try{int isum;
			String sql = "select count(Ccode) from customer";
			con = DBConnected.dbConnector(); 
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
	    
			if(rs.next()){

			String sum = rs.getString("count(Ccode)");
			   isum = Integer.parseInt(sum);
			  if(isum<10){
			    isum = isum+1;
			    sum = Integer.toString(isum);
			   Ccode = "C00"+sum;
			textCID.setText(Ccode);
			}else if(isum<100){
			    isum = isum+1;
			    sum = Integer.toString(isum);
			    Ccode = "C0"+sum;
			textCID.setText(Ccode);
			}else if(isum<1000){
			  isum = isum+1;
			    sum = Integer.toString(isum);
			    Ccode = "C"+sum;
			textCID.setText(Ccode);
			}
			}
			} catch(Exception e2){
				e2.printStackTrace();
			}
	}
	public AddCustomer() {
		con = DBConnected.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E25\u0E39\u0E01\u0E04\u0E49\u0E32");
		label.setBounds(22, 39, 80, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u0E0A\u0E37\u0E48\u0E2D\u0E08\u0E23\u0E34\u0E07");
		label_1.setBounds(22, 64, 61, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u0E19\u0E32\u0E21\u0E2A\u0E01\u0E38\u0E25");
		label_2.setBounds(22, 89, 61, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u0E40\u0E1A\u0E2D\u0E23\u0E4C\u0E42\u0E17\u0E23\u0E28\u0E31\u0E1E\u0E17\u0E4C");
		label_3.setBounds(22, 114, 106, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u0E17\u0E35\u0E48\u0E2D\u0E22\u0E39\u0E48");
		label_4.setBounds(22, 139, 61, 14);
		contentPane.add(label_4);
		
		textCID = new JTextField();
		textCID.setBounds(127, 36, 149, 20);
		contentPane.add(textCID);
		textCID.setColumns(10);
		
		textCfname = new JTextField();
		textCfname.setColumns(10);
		textCfname.setBounds(127, 61, 149, 20);
		contentPane.add(textCfname);
		
		textClname = new JTextField();
		textClname.setColumns(10);
		textClname.setBounds(127, 86, 149, 20);
		contentPane.add(textClname);
		
		textTel = new JTextField();
		textTel.setColumns(10);
		textTel.setBounds(127, 111, 149, 20);
		contentPane.add(textTel);
		
		textAddr = new JTextField();
		textAddr.setColumns(10);
		textAddr.setBounds(127, 136, 149, 20);
		contentPane.add(textAddr);
		
		JLabel label_5 = new JLabel("\u0E01\u0E23\u0E2D\u0E01\u0E1B\u0E23\u0E30\u0E27\u0E31\u0E15\u0E34\u0E25\u0E39\u0E01\u0E04\u0E49\u0E32");
		label_5.setBounds(184, 11, 112, 14);
		contentPane.add(label_5);
		
		JButton button = new JButton("\u0E22\u0E37\u0E19\u0E22\u0E31\u0E19\u0E02\u0E49\u0E2D\u0E21\u0E39\u0E25");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "ต้องการยืนยันข้อมูลหรือไม่","คำเตือน",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
			String query = "insert into customer (Ccode,Cfname,Clname,Tel,Address) value(?,?,?,?,?)";
			try {
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, textCID.getText());
				pst.setString(2, textCfname.getText());
				pst.setString(3, textClname.getText());
				pst.setString(4, textTel.getText());
				pst.setString(5, textAddr.getText());
				pst.executeUpdate();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			closeWindow();
			
			}
			}
		});
		button.setBounds(160, 192, 106, 23);
		contentPane.add(button);
		refreshCcode();
	}

}
