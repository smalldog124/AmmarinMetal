package database;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 642, 403);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JButton button = new JButton("\u0E02\u0E32\u0E22\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			CheckCust check = new CheckCust();
			check.setVisible(true);
			
			
			}
		});
		button.setBounds(50, 106, 146, 23);
		frame.getContentPane().add(button);
		
		JLabel label = new JLabel("\u0E42\u0E1B\u0E23\u0E41\u0E01\u0E23\u0E21\u0E08\u0E31\u0E14\u0E01\u0E32\u0E23\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(273, 28, 165, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u0E23\u0E49\u0E32\u0E19\u0E28\u0E38\u0E20\u0E01\u0E23\u0E27\u0E31\u0E2A\u0E14\u0E38\u0E01\u0E48\u0E2D\u0E2A\u0E23\u0E49\u0E32\u0E07");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(273, 63, 205, 14);
		frame.getContentPane().add(label_1);
		
		JButton button_1 = new JButton("\u0E01\u0E23\u0E2D\u0E01\u0E1B\u0E23\u0E30\u0E27\u0E31\u0E15\u0E34\u0E25\u0E39\u0E01\u0E04\u0E49\u0E32");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCustomer addcust = new AddCustomer();
				addcust.setVisible(true);
				
			}
		});
		button_1.setBounds(50, 242, 146, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u0E23\u0E32\u0E22\u0E01\u0E32\u0E23\u0E2A\u0E34\u0E19\u0E04\u0E49\u0E32");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Product prod = new Product();
			prod.setVisible(true);
			}
		});
		button_2.setBounds(50, 140, 146, 23);
		frame.getContentPane().add(button_2);
		
		JButton btnNewButton = new JButton("\u0E1B\u0E23\u0E30\u0E27\u0E31\u0E15\u0E34\u0E01\u0E32\u0E23\u0E0B\u0E37\u0E49\u0E2D\u0E02\u0E32\u0E22");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			History hist = new History();
			hist.setVisible(true);
			}
			
		});
		btnNewButton.setBounds(50, 174, 146, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton button_3 = new JButton("\u0E1B\u0E23\u0E30\u0E27\u0E31\u0E15\u0E34\u0E25\u0E39\u0E01\u0E04\u0E49\u0E32");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			HistCust histcust = new HistCust();
			histcust.setVisible(true);
			
			
			}
		});
		button_3.setBounds(50, 276, 146, 23);
		frame.getContentPane().add(button_3);
		
		JButton btnNewButton_1 = new JButton("\u0E1E\u0E19\u0E31\u0E01\u0E07\u0E32\u0E19");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employee employ =new Employee();
				employ.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(50, 208, 146, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\13153377_1754392428131350_2023926747_n.jpg"));
		lblNewLabel.setBounds(235, 123, 359, 151);
		frame.getContentPane().add(lblNewLabel);
	}
}
