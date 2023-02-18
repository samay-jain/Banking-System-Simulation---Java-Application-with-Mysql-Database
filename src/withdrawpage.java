import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class withdrawpage {

	private JFrame frame;
	private JTextField textwithdraw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					withdrawpage window = new withdrawpage();
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
	public withdrawpage() {
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
		frame.setBounds(100, 100, 753, 568);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWithdrawMoney = new JLabel("Withdraw Money");
		lblWithdrawMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblWithdrawMoney.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblWithdrawMoney.setBackground(SystemColor.menu);
		lblWithdrawMoney.setBounds(89, 29, 542, 37);
		frame.getContentPane().add(lblWithdrawMoney);
		
		JPanel panel = new JPanel();
		panel.setBounds(100, 93, 542, 344);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBackground(new Color(255, 204, 204));
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String m = textwithdraw.getText();
					if(!m.isEmpty())
					{
						long money = Long.parseLong(m);
						String accno = loginpage.getAccno();
						String result = db.withdrawMoney(accno,money);
						if(result.equals("true"))
						{
							JOptionPane.showMessageDialog(null, "Withdrawal Successfull!");
							textwithdraw.setText("");
						}
						else if(result.equals("Insufficient Balance"))
						{
							JOptionPane.showMessageDialog(null, "Amount can't be Withdrawn as you have to keep atleast Rs.500 in the Account");
							textwithdraw.setText("");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Withdrawal unsuccessfull due to some Technical reason!");
							textwithdraw.setText("");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please Enter amount to be withdrawn!");
					}
				
			}
		});
		btnWithdraw.setBounds(206, 301, 136, 33);
		btnWithdraw.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(btnWithdraw);
		
		JLabel lblEnterAmountTo = new JLabel("Enter Amount to be \r\nWithdrawn");
		lblEnterAmountTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterAmountTo.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblEnterAmountTo.setBackground(SystemColor.menu);
		lblEnterAmountTo.setBounds(0, 45, 542, 108);
		panel.add(lblEnterAmountTo);
		
		textwithdraw = new JTextField();
		textwithdraw.setBackground(new Color(255, 255, 255));
		textwithdraw.setHorizontalAlignment(SwingConstants.CENTER);
		textwithdraw.setFont(new Font("Tahoma", Font.PLAIN, 26));
		textwithdraw.setColumns(10);
		textwithdraw.setBounds(154, 163, 243, 57);
		panel.add(textwithdraw);
		
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
		btnGoBack.setBounds(297, 459, 152, 49);
		frame.getContentPane().add(btnGoBack);
	}
}
