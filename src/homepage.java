import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class homepage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homepage window = new homepage();
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
	JLabel acno;
	JLabel welc;
	public homepage() {
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
		frame.setBounds(100, 100, 1095, 684);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String accno = loginpage.getAccno();
		String name = db.getName(accno);
		
		welc = new JLabel("Welcome Mr/Ms "+name);
		welc.setHorizontalAlignment(SwingConstants.CENTER);
		welc.setBackground(new Color(240, 240, 240));
		welc.setBounds(126, 34, 839, 37);
		welc.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 204));
		panel.setBounds(126, 97, 839, 512);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setBackground(new Color(255, 204, 204));
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				depositpage.main(null);
			}
		});
		btnDeposit.setBounds(289, 53, 230, 63);
		btnDeposit.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBackground(new Color(255, 204, 204));
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				withdrawpage.main(null);
			}
		});
		btnWithdraw.setBounds(289, 356, 230, 63);
		btnWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.setBackground(new Color(255, 204, 204));
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				transferpage.main(null);
			}
		});
		btnTransfer.setBounds(65, 202, 193, 63);
		btnTransfer.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnBalance = new JButton("Balance");
		btnBalance.setBackground(new Color(255, 204, 204));
		btnBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				bankbalance.main(null);
			}
		});
		btnBalance.setBounds(551, 202, 230, 63);
		btnBalance.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnHistory = new JButton("History");
		btnHistory.setBackground(new Color(255, 204, 204));
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				historypage.main(null);
			}
		});
		btnHistory.setBounds(289, 202, 230, 63);
		btnHistory.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnNewButton = new JButton("Log out");
		btnNewButton.setBackground(new Color(255, 153, 204));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				loginpage.main(null);
			}
		});
		btnNewButton.setBounds(721, 473, 108, 29);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(welc);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.add(btnTransfer);
		panel.add(btnHistory);
		panel.add(btnBalance);
		panel.add(btnWithdraw);
		panel.add(btnDeposit);
		panel.add(btnNewButton);
		
		acno = new JLabel("Account Number - "+accno);
		acno.setFont(new Font("Tahoma", Font.BOLD, 20));
		acno.setBounds(10, 6, 319, 29);
		panel.add(acno);
		
	}
	public JLabel getAcno() {
		return acno;
	}
	public JLabel getWelc() {
		return welc;
	}
}
