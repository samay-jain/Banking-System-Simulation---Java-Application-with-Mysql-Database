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

public class depositpage {

	private JFrame frame;
	private JTextField textdeposit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					depositpage window = new depositpage();
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
	public depositpage() {
		initialize();
		db.connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(102, 204, 153));
		frame.getContentPane().setBackground(new Color(102, 204, 153));
		frame.setBounds(100, 100, 750, 567);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWithdrawMoney = new JLabel("Deposit Money");
		lblWithdrawMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblWithdrawMoney.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblWithdrawMoney.setBackground(SystemColor.menu);
		lblWithdrawMoney.setBounds(98, 35, 542, 37);
		frame.getContentPane().add(lblWithdrawMoney);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(98, 99, 542, 344);
		frame.getContentPane().add(panel);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setBackground(new Color(255, 204, 204));
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String m = textdeposit.getText();
				if(!m.isEmpty())
				{
					long money = Long.parseLong(m);
					String accno = loginpage.getAccno();
					boolean result = db.depositMoney(accno,money);
					if(result==true)
					{
						JOptionPane.showMessageDialog(null, "Deposit Successfull!");
						textdeposit.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Deposit unsuccessfull due to some Technical reason!");
						textdeposit.setText("");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Enter amount to be Deposited!");
				}
			}
		});
		btnDeposit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDeposit.setBounds(206, 301, 136, 33);
		panel.add(btnDeposit);
		
		JLabel lblEnterAmountTo_1 = new JLabel("Enter Amount to be \r\nDeposited");
		lblEnterAmountTo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterAmountTo_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblEnterAmountTo_1.setBackground(SystemColor.menu);
		lblEnterAmountTo_1.setBounds(0, 45, 542, 108);
		panel.add(lblEnterAmountTo_1);
		
		textdeposit = new JTextField();
		textdeposit.setHorizontalAlignment(SwingConstants.CENTER);
		textdeposit.setFont(new Font("Tahoma", Font.PLAIN, 26));
		textdeposit.setColumns(10);
		textdeposit.setBackground(new Color(255, 255, 255));
		textdeposit.setBounds(154, 163, 243, 57);
		panel.add(textdeposit);
		
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
		btnGoBack.setBounds(297, 471, 152, 49);
		frame.getContentPane().add(btnGoBack);
	}

}
