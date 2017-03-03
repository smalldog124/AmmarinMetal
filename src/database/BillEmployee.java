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
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BillEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField textDate;
	private JTextField textFcode;
	private JTextField textName;
	private JTextField textDcode;
	private JTextField textHour;
	private JTextField textField_5;
	private JTextField textSalary;
	private JTextField textScode;
    private String Scode;
    Connection conn = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillEmployee frame = new BillEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 public void setBillEm(String Scode){
		 
		 this.Scode = Scode;
		 
		 
	 }
	 
	 public void fillBill(String Scode){
		 String sql = "select * from salary,employee where salary.fcode = employee.fcode and Scode ='"+Scode+"'";
		 try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			  while(rs.next()){
				  String add1 = rs.getString("Scode");
				  textScode.setText(add1);
				  String add2 = rs.getString("Fcode");
				  textFcode.setText(add2);
				  String add3 = rs.getString("fname");
				  String add4 = rs.getString("lname");
				  textName.setText(add3+" "+add4);
				  String add5 = rs.getString("Dcode");
				  textDcode.setText(add5);
				  String add6 = rs.getString("Hour");
				  textHour.setText(add6);
				  String add7 = rs.getString("SalaryDate");
				  textDate.setText(add7);
				  String add8 = rs.getString("Total");
				  textSalary.setText(add8);
				  
			  }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 
	 
	public BillEmployee() {
		conn = DBConnected.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 779, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u0E43\u0E1A\u0E40\u0E2A\u0E23\u0E47\u0E08\u0E40\u0E07\u0E34\u0E19\u0E40\u0E14\u0E37\u0E2D\u0E19");
		label.setBounds(315, 27, 124, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u0E27\u0E31\u0E19\u0E17\u0E35\u0E48");
		label_1.setBounds(540, 27, 46, 14);
		contentPane.add(label_1);
		
		textDate = new JTextField();
		textDate.setBounds(579, 24, 131, 20);
		contentPane.add(textDate);
		textDate.setColumns(10);
		
		JLabel label_2 = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E1E\u0E19\u0E31\u0E01\u0E07\u0E32\u0E19");
		label_2.setBounds(26, 67, 81, 14);
		contentPane.add(label_2);
		
		textFcode = new JTextField();
		textFcode.setBounds(114, 64, 86, 20);
		contentPane.add(textFcode);
		textFcode.setColumns(10);
		
		JLabel label_3 = new JLabel("\u0E0A\u0E37\u0E48\u0E2D-\u0E2A\u0E01\u0E38\u0E25");
		label_3.setBounds(224, 67, 72, 14);
		contentPane.add(label_3);
		
		textName = new JTextField();
		textName.setBounds(306, 64, 216, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JLabel label_4 = new JLabel("\u0E41\u0E1C\u0E19\u0E01");
		label_4.setBounds(561, 67, 53, 14);
		contentPane.add(label_4);
		
		textDcode = new JTextField();
		textDcode.setBounds(624, 64, 86, 20);
		contentPane.add(textDcode);
		textDcode.setColumns(10);
		
		JLabel label_5 = new JLabel("\u0E23\u0E32\u0E22\u0E44\u0E14\u0E49");
		label_5.setBounds(41, 150, 44, 14);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("\u0E08\u0E33\u0E19\u0E27\u0E19\u0E40\u0E27\u0E25\u0E32\u0E17\u0E35\u0E48\u0E17\u0E33\u0E07\u0E32\u0E19");
		label_6.setBounds(66, 184, 124, 14);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("\u0E04\u0E48\u0E32\u0E41\u0E23\u0E07\u0E15\u0E48\u0E2D\u0E0A\u0E31\u0E48\u0E27\u0E42\u0E21\u0E07");
		label_7.setBounds(66, 221, 97, 14);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("\u0E23\u0E27\u0E21\u0E23\u0E32\u0E22\u0E44\u0E14\u0E49");
		label_8.setBounds(66, 264, 97, 14);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("\u0E08\u0E33\u0E19\u0E27\u0E19\u0E40\u0E07\u0E34\u0E19");
		label_9.setBounds(432, 150, 72, 14);
		contentPane.add(label_9);
		
		textHour = new JTextField();
		textHour.setHorizontalAlignment(SwingConstants.RIGHT);
		textHour.setBounds(406, 181, 86, 20);
		contentPane.add(textHour);
		textHour.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_5.setText("35.00");
		textField_5.setBounds(406, 218, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textSalary = new JTextField();
		textSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		textSalary.setBounds(406, 261, 86, 20);
		contentPane.add(textSalary);
		textSalary.setColumns(10);
		
		JLabel label_10 = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E1A\u0E34\u0E25");
		label_10.setBounds(26, 11, 46, 14);
		contentPane.add(label_10);
		
		textScode = new JTextField();
		textScode.setBounds(77, 8, 97, 20);
		contentPane.add(textScode);
		textScode.setColumns(10);
		
		JLabel label_11 = new JLabel("\u0E0A\u0E31\u0E48\u0E27\u0E42\u0E21\u0E07");
		label_11.setBounds(529, 184, 46, 14);
		contentPane.add(label_11);
		
		JLabel label_12 = new JLabel("\u0E1A\u0E32\u0E17");
		label_12.setBounds(529, 221, 46, 14);
		contentPane.add(label_12);
		
		JLabel label_13 = new JLabel("\u0E1A\u0E32\u0E17");
		label_13.setBounds(529, 264, 46, 14);
		contentPane.add(label_13);
		
		JLabel label_14 = new JLabel("\u0E1C\u0E39\u0E49\u0E23\u0E31\u0E1A\u0E40\u0E07\u0E34\u0E19");
		label_14.setBounds(39, 409, 46, 14);
		contentPane.add(label_14);
		fillBill(Scode);
	}

}
