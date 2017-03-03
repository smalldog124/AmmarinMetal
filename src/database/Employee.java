package database;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Employee extends JFrame {

	private JPanel contentPane;
    Connection conn = null;
    private JTable table;
    private JTable table_1;
    private JTextField textEp;
    private JTextField textEname;
    private JTextField textLname;
    private JTextField textTimeS;
    private JTextField textTimeE;
    private JTextField textDate;
    private String Epcode;
    private String date=null;
    private String Fcode;
    private String Scode;
    private String Total;
    private float Salary;
    private String salary;
    private JTable table_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee frame = new Employee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void refreshText(){
		
		textTimeS.setText("hh:mm:00");
		textTimeE.setText("hh:mm:00");
		
	}
	
	 public void CurrentDay(){
	    	Calendar cal = Calendar.getInstance();
	    	cal.add(Calendar.DATE, 1);
	    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	    	
	        date = format1.format(cal.getTime());
	        textDate.setText(date);
	    	
	    }
	public void refreshEP(){
		
		int ep;
		String sql = "select count(Epcode) from employeecheck";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				String sum = rs.getString("count(Epcode)");
				ep = Integer.parseInt(sum);
				ep = ep+1;
				
				  if(ep<10){
				    sum = Integer.toString(ep);
				    Epcode = "EP000"+sum;
				}else if(ep<100){
				    sum = Integer.toString(ep);
				    Epcode = "EP00"+sum;
				}else if(ep<1000){
				    sum = Integer.toString(ep);
				    Epcode = "EP0"+sum;
				}else if(ep<10000){
				    sum = Integer.toString(ep);
				    Epcode = "EP"+sum;
				}
				  
				
		}
			  textEp.setText(Epcode);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		
	}

	public void refreshTable(){
		GregorianCalendar gcalendar = new GregorianCalendar();
		//System.out.println(gcalendar.get(Calendar.HOUR)+ ":" + gcalendar.get(Calendar.MINUTE) + ":" + gcalendar.get(Calendar.SECOND));
		String sql = "select Fcode รหัสพนักงาน,Fname ชื่อ,Lname นามสกุล,Ddesc แผนก from Employee,Departmet where employee.dcode = departmet.dcode ";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			 table.setModel(DbUtils.resultSetToTableModel(rs));
			  pst.close();
			  rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void refreshTable2(){
		
		String sql = "select Date วันเดือนปี,Fname ชื่อ,Lname นามสกุล,timestart เวลาเริ่มงาน,timeend เวลาเลิกงาน ,hpd จำนวนชั่วโมงที่ทำงาน from employeecheck,employee where employeecheck.fcode = employee.fcode ";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			 table_1.setModel(DbUtils.resultSetToTableModel(rs));
			  pst.close();
			  rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
public void refreshTable3(){
		
		String sql = "select Scode รหัสบิล,salarydate วันออกใบเสร็จ,fname ชื่อ,lname นามสกุล,Total เงินเดือน  from salary,employee where salary.fcode = employee.fcode";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			 table_2.setModel(DbUtils.resultSetToTableModel(rs));
			  pst.close();
			  rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Employee() {
		conn = DBConnected.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 852, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(44, 39, 782, 420);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("รายชื่อพนักงงาน", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 22, 477, 326);
		panel.add(scrollPane_1);
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try 
				{
					int row = table.getSelectedRow();
				    Fcode = (table.getModel().getValueAt(row, 0)).toString();
				    String query = "select * from employee where Fcode='"+Fcode+"' ";
					PreparedStatement pst;
					pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{
						String add1 = rs.getString("Fname");
						textEname.setText(add1);
						String add2 = rs.getString("Lname");
						textLname.setText(add2);
						
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		});
		scrollPane_1.setViewportView(scrollPane);
		
		
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("\u0E0A\u0E37\u0E48\u0E2D");
		label.setBounds(513, 66, 56, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u0E19\u0E32\u0E21\u0E2A\u0E01\u0E38\u0E25");
		label_1.setBounds(513, 106, 67, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u0E23\u0E2B\u0E31\u0E2A\u0E15\u0E32\u0E23\u0E32\u0E07");
		label_2.setBounds(513, 30, 67, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u0E40\u0E27\u0E25\u0E32\u0E40\u0E02\u0E49\u0E32");
		label_3.setBounds(513, 147, 67, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u0E40\u0E27\u0E25\u0E32\u0E2D\u0E2D\u0E01");
		label_4.setBounds(513, 191, 67, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("\u0E27\u0E31\u0E19/\u0E40\u0E14\u0E37\u0E2D\u0E19/\u0E1B\u0E35");
		label_5.setBounds(513, 236, 67, 14);
		panel.add(label_5);
		
		textEp = new JTextField();
		textEp.setBounds(590, 27, 177, 20);
		panel.add(textEp);
		textEp.setColumns(10);
		
		textEname = new JTextField();
		textEname.setColumns(10);
		textEname.setBounds(590, 63, 177, 20);
		panel.add(textEname);
		
		textLname = new JTextField();
		textLname.setColumns(10);
		textLname.setBounds(590, 103, 177, 20);
		panel.add(textLname);
		
		textTimeS = new JTextField();
		textTimeS.setText("hh:mm:00");
		textTimeS.setColumns(10);
		textTimeS.setBounds(590, 144, 177, 20);
		panel.add(textTimeS);
		
		textTimeE = new JTextField();
		textTimeE.setText("hh:mm:00");
		textTimeE.setColumns(10);
		textTimeE.setBounds(590, 188, 177, 20);
		panel.add(textTimeE);
		
		textDate = new JTextField();
		textDate.setColumns(10);
		textDate.setBounds(590, 233, 177, 20);
		panel.add(textDate);
		
		JButton button = new JButton("\u0E1A\u0E31\u0E19\u0E17\u0E36\u0E01\u0E40\u0E27\u0E25\u0E32\u0E17\u0E33\u0E07\u0E32\u0E19");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "ต้องการยืนยันข้อมูลหรือไม่","คำเตือน",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
			String SplitStart[] = textTimeS.getText().split(":");
			String SplitEnd[] = textTimeE.getText().split(":");
			String calhrStart = SplitStart[0];
			String calminStart = SplitStart[1];

			String calhrEnd = SplitEnd[0];
			String calminEnd = SplitEnd[1];

			String calStart = calhrStart+"."+calminStart;
		    String calEnd  = calhrEnd+"."+calminEnd;
			/*String calhrStart = textTimeS.getText().substring(0, 2);
			String calminStart =  textTimeS.getText().substring(3, 5);
			//System.out.println(calhrStart);
			//System.out.println(calminStart);
			String calhrEnd = textTimeE.getText().substring(0, 2);
			String calminEnd = textTimeE.getText().substring(3,5);
			//System.out.println(calhrEnd);
			//System.out.println(calminEnd);
		    String calStart = calhrStart+"."+calminStart;
		    String calEnd  = calhrEnd+"."+calminEnd; */
			float numStart = Float.parseFloat(calStart);
			//System.out.println(numStart);
			
			float numEnd = Float.parseFloat(calEnd);
			//System.out.println(numEnd);
			float result =  numEnd - numStart;
			//System.out.println(result);
			String time = Float.toString(result);
			String temp = time;
			//System.out.println(time.length());
			for(int i=0;i<time.length();i++){
				if(time.charAt(i)=='.'){
					    time = time.substring(0,i);
					    System.out.println(time);
					String timemin = temp.substring(i+1,i+2);
					System.out.println(timemin);
					  int convert = Integer.parseInt(timemin);
					   if(convert>=6){
						   
						   convert = convert-4;
					    timemin = Integer.toString(convert);
					   }else{
						   timemin = Integer.toString(convert);
					   }
			        	time = time+"."+timemin;
			        	//System.out.println(time);
			        	result = Float.parseFloat(time); //ผลลัพท์
			        	break;
				}
			}
			
				String query = "insert into employeecheck (Epcode,fcode,Date,TimeStart,TimeEnd,Hpd) value(?,?,?,?,?,?)";
				try {
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textEp.getText());
					pst.setString(2, Fcode);
					pst.setString(3, textDate.getText());
					pst.setString(4, textTimeS.getText());
					pst.setString(5, textTimeE.getText());
					pst.setString(6, time);
					pst.executeUpdate();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				refreshEP();
				refreshText();	
				refreshTable2();
			   JOptionPane.showMessageDialog(null, "yess");
			}
			
			}
			
		});
		button.setBounds(530, 281, 141, 23);
		panel.add(button);
		
		JButton btnNewButton = new JButton("\u0E2D\u0E2D\u0E01\u0E43\u0E1A\u0E40\u0E2A\u0E23\u0E47\u0E08");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "ต้องการยืนยันข้อมูลหรือไม่","คำเตือน",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
				int sc;
				String sql = "select count(Scode) from salary";
				PreparedStatement pst;
				try {
					pst = conn.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					if(rs.next()){
						String sum = rs.getString("count(Scode)");
						sc = Integer.parseInt(sum);
						sc = sc+1;
						
						  if(sc<10){
						    sum = Integer.toString(sc);
						    Scode = "S00"+sum;
						}else if(sc<100){
						    sum = Integer.toString(sc);
						    Scode = "S0"+sum;
						}else if(sc<1000){
						    sum = Integer.toString(sc);
						    Scode = "S"+sum;
						}	  
						
				}
					  
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				float total=0;
				String query = "select * from employeecheck where employeecheck.fcode= '"+Fcode+"'";
				ResultSet rs;
				//System.out.println(query);
				 try {
					pst = conn.prepareStatement(query);
					rs = pst.executeQuery();
				
					
					while(rs.next()){
						String add = rs.getString("Date");
						String year = add.substring(0,4);
						String month = add.substring(5,7);
						String year2 = date.substring(0,4);
						String month2 = date.substring(5,7);
						if(year.equals(year2)&&month.equals(month2)){
						
						
					    String num = rs.getString("Hpd");
							float n = Float.parseFloat(num);
							//System.out.println(n);
							total+=n;
							//System.out.println(num);
						}
						
					}
					Salary = total*35;
					//System.out.println(salary);
					 salary = Float.toString(Salary);
					Total = Float.toString(total);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
				
				 
				String q = "insert into salary(Scode,Fcode,Hour,salarydate,total) value(?,?,?,?,?)";
				try {
					pst = conn.prepareStatement(q);
					pst.setString(1, Scode);
					pst.setString(2, Fcode);
					pst.setString(3, Total);
					pst.setString(4, date);
					pst.setString(5, salary);
					pst.executeUpdate();
					//System.out.println(pst);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				refreshTable3();
				JOptionPane.showMessageDialog(null,"ออกใบเสร็จเรียบร้อย");
			}
				}
		});
		btnNewButton.setBounds(530, 315, 141, 23);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("ตารางเข้า-ออกทำงาน", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(24, 11, 743, 343);
		panel_1.add(scrollPane_2);
		
		table_1 = new JTable();
		scrollPane_2.setViewportView(table_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("ตารางวันออกใบเสร็จ", null, panel_2, null);
		panel_2.setLayout(null);
		table_2 = new JTable();
		JScrollPane scrollPane_3 = new JScrollPane();
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table_2.getSelectedRow();
				    Scode = (table_2.getModel().getValueAt(row, 0)).toString();
				 
			       	
			}
		});
		scrollPane_3.setBounds(91, 11, 611, 330);
		panel_2.add(scrollPane_3);
		
		
		scrollPane_3.setViewportView(table_2);
		
		JButton btnNewButton_1 = new JButton("\u0E15\u0E23\u0E27\u0E08\u0E2A\u0E2D\u0E1A\u0E43\u0E1A\u0E40\u0E2A\u0E23\u0E47\u0E08");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BillEmployee bill = new BillEmployee();
				bill.fillBill(Scode);
				bill.setVisible(true);
				
				
			}
		});
		btnNewButton_1.setBounds(534, 358, 146, 23);
		panel_2.add(btnNewButton_1);
		
		JLabel label_6 = new JLabel("\u0E1B\u0E23\u0E30\u0E27\u0E31\u0E15\u0E34\u0E1E\u0E19\u0E31\u0E01\u0E07\u0E32\u0E19");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_6.setBounds(417, 11, 128, 14);
		contentPane.add(label_6);
		refreshTable();
		refreshTable2();
		refreshEP();
		CurrentDay();
		 refreshTable3();
	}
}
