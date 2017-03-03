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
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CheckCust extends JFrame {
    Connection con = null;
	private JPanel contentPane;
	private JTextField textFname;
	private JTextField textLname;
	 private String Ccode = null;
	 private BuyProduct buy;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckCust frame = new CheckCust();
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
	public CheckCust() {
	  
		con = DBConnected.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 558, 159);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u0E0A\u0E37\u0E48\u0E2D\u0E08\u0E23\u0E34\u0E07");
		label.setBounds(21, 53, 57, 14);
		contentPane.add(label);
		
		textFname = new JTextField();
		textFname.setBounds(88, 50, 157, 20);
		contentPane.add(textFname);
		textFname.setColumns(10);
		
		JLabel label_1 = new JLabel("\u0E19\u0E32\u0E21\u0E2A\u0E01\u0E38\u0E25");
		label_1.setBounds(265, 53, 70, 14);
		contentPane.add(label_1);
		
		textLname = new JTextField();
		textLname.setBounds(345, 50, 146, 20);
		contentPane.add(textLname);
		textLname.setColumns(10);
		
		JLabel label_2 = new JLabel("\u0E0A\u0E37\u0E48\u0E2D-\u0E2A\u0E01\u0E38\u0E25 \u0E25\u0E39\u0E01\u0E04\u0E49\u0E32");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(240, 11, 111, 16);
		contentPane.add(label_2);
		
		JButton button = new JButton("\u0E15\u0E23\u0E27\u0E08\u0E2A\u0E2D\u0E1A\u0E02\u0E49\u0E2D\u0E21\u0E39\u0E25");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query = "select * from customer where Cfname = '"+textFname.getText()+"' and Clname= '"+textFname.getText()+"'";
				//System.out.println(query);
				try {
					PreparedStatement pst = con.prepareStatement(query);
					//pst.setString(1,textFname.getText());
					//pst.setString(2, textLname.getText());
					ResultSet rs = pst.executeQuery();
					if (rs.next()){
						Ccode = rs.getString("CCode");
						//System.out.println(Ccode);
					}
					//Ccode = rs.getString("CCode");
					buy = new BuyProduct();
					buy.setCcode(Ccode);
					int count = 0;
					rs = pst.executeQuery();
					while (rs.next()){
						count = count +1;
						//System.out.println(count);
					}
					rs = pst.executeQuery();
					if(count == 1){
						JOptionPane.showMessageDialog(null, "ชื่อของคุณอยู่ในระบบ");
						if (rs.next()){
							Ccode = rs.getString("CCode");
							buy.setVisible(true);
						}
						}else if(count <1){
						JOptionPane.showMessageDialog(null, "ชื่อของคุณไม่อยู่ในระบบ โปรดข้อมูลเข้าระบบ");
					    AddCustomer cust = new AddCustomer();
					    cust.setVisible(true);
					}
					//rs.close();
					//pst.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				closeWindow();
			}
		});
		button.setBounds(217, 87, 126, 23);
		contentPane.add(button);
		
	}
}
