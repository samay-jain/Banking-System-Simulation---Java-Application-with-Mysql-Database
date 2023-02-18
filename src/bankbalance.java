import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class bankbalance {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bankbalance window = new bankbalance();
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
	DBCONNECT db = new DBCONNECT();
	public bankbalance() {
		db.connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(102, 204, 153));
		frame.setBounds(100, 100, 722, 531);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account Balance");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(184, 46, 342, 53);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(134, 143, 446, 240);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblYourCurrentBalance = new JLabel("Your Current Balance is");
		lblYourCurrentBalance.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourCurrentBalance.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblYourCurrentBalance.setBounds(51, 45, 342, 53);
		panel.add(lblYourCurrentBalance);
		
		
		String accno = loginpage.getAccno();
		String balance = db.getBalance(accno);
		JLabel lblRs = new JLabel("Rs."+balance);
		lblRs.setBackground(SystemColor.textHighlight);
		lblRs.setHorizontalAlignment(SwingConstants.CENTER);
		lblRs.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblRs.setBounds(51, 128, 342, 53);
		panel.add(lblRs);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setBackground(new Color(255, 204, 204));
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				homepage.main(null);
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnGoBack.setBounds(290, 410, 132, 49);
		frame.getContentPane().add(btnGoBack);
	}
}
