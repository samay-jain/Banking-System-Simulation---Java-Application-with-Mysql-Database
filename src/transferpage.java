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

public class transferpage {

	private JFrame frame;
	private JTextField texttransfer;
	private JTextField textaccno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					transferpage window = new transferpage();
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
	public transferpage() {
		initialize();
		db.connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(102, 204, 153));
		frame.setBounds(100, 100, 875, 624);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTransferMoney = new JLabel("Transfer Money");
		lblTransferMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransferMoney.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTransferMoney.setBackground(SystemColor.info);
		lblTransferMoney.setBounds(158, 35, 542, 37);
		frame.getContentPane().add(lblTransferMoney);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(97, 102, 664, 355);
		frame.getContentPane().add(panel);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.setBackground(new Color(255, 204, 204));
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String accno = loginpage.getAccno();
				String money=texttransfer.getText();
				String toaccno=textaccno.getText();
				if(money.isEmpty() || toaccno.isEmpty())
					JOptionPane.showMessageDialog(null, "Please Enter all necessary details!");
				else
				{
					boolean result = db.isPresent(toaccno);
					long m = Long.parseLong(money);
					if(result==true)
					{
						String nr = db.transfer(accno, m, toaccno);
						if(nr.equals("true"))
						{
							JOptionPane.showMessageDialog(null, "Amount is Transferred Successfully!");
							textaccno.setText("");
							texttransfer.setText("");
						}
						else if(nr.equals("Insufficient Balance"))
						{
							JOptionPane.showMessageDialog(null, "Amount can't be Transfer as you have to keep atleast Rs.500 in the Account");
							textaccno.setText("");
							texttransfer.setText("");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Amount can't be transferred due to some Technical reason!");
							textaccno.setText("");
							texttransfer.setText("");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Account Number is invalid!");
						textaccno.setText("");
						texttransfer.setText("");
					}
				}
			}
		});
		btnTransfer.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnTransfer.setBounds(256, 249, 150, 58);
		panel.add(btnTransfer);
		
		JLabel lblEnterAmountTo_1 = new JLabel("Enter amount to \r\nTransfer");
		lblEnterAmountTo_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterAmountTo_1.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblEnterAmountTo_1.setBackground(SystemColor.menu);
		lblEnterAmountTo_1.setBounds(33, 42, 378, 58);
		panel.add(lblEnterAmountTo_1);
		
		texttransfer = new JTextField();
		texttransfer.setHorizontalAlignment(SwingConstants.CENTER);
		texttransfer.setFont(new Font("Tahoma", Font.PLAIN, 24));
		texttransfer.setColumns(10);
		texttransfer.setBackground(new Color(255, 255, 255));
		texttransfer.setBounds(432, 46, 169, 51);
		panel.add(texttransfer);
		
		JLabel lblEnterAmountTo_1_1 = new JLabel("Enter Account Number");
		lblEnterAmountTo_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterAmountTo_1_1.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblEnterAmountTo_1_1.setBackground(SystemColor.menu);
		lblEnterAmountTo_1_1.setBounds(33, 142, 350, 58);
		panel.add(lblEnterAmountTo_1_1);
		
		textaccno = new JTextField();
		textaccno.setHorizontalAlignment(SwingConstants.CENTER);
		textaccno.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textaccno.setColumns(10);
		textaccno.setBackground(new Color(255, 255, 255));
		textaccno.setBounds(432, 142, 169, 51);
		panel.add(textaccno);
		
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
		btnGoBack.setBounds(353, 501, 152, 49);
		frame.getContentPane().add(btnGoBack);
	}
}
