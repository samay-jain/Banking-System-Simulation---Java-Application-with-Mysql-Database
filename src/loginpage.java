import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;

public class loginpage {

	private JFrame frame;
	private JTextField textaccno;
	private JPasswordField textpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginpage window = new loginpage();
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
	public static String accountnumber="";
	
	DBCONNECT db = new DBCONNECT();
	public loginpage() {
		initialize();
		db.connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(255, 204, 255));
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(102, 204, 153));
		frame.setBounds(100, 100, 965, 599);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login to the Bank");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(354, 39, 280, 51);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(137, 121, 700, 331);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAccountNumber = new JLabel("Account Number");
		lblAccountNumber.setBounds(67, 68, 248, 34);
		lblAccountNumber.setFont(new Font("Tahoma", Font.BOLD, 28));
		panel.add(lblAccountNumber);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblPassword.setBounds(67, 166, 248, 34);
		panel.add(lblPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(255, 204, 204));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String accno = textaccno.getText();
				String pass = textpassword.getText();
				if(accno.isEmpty() || pass.isEmpty())
					JOptionPane.showMessageDialog(null, "Please enter your login credentials!");
				else
				{
					if(db.checkCredentials(accno, pass))
					{
						frame.setVisible(false);
						accountnumber = accno;
						homepage hp = new homepage();
						homepage.main(null);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid Account Number or Password!");
					}
				}
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(274, 253, 132, 49);
		panel.add(btnNewButton);
		
		textaccno = new JTextField();
		textaccno.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textaccno.setBounds(350, 68, 227, 42);
		panel.add(textaccno);
		textaccno.setColumns(10);
		
		textpassword = new JPasswordField();
		textpassword.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textpassword.setBounds(350, 158, 227, 42);
		panel.add(textpassword);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(new Color(255, 204, 204));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				registerpage.main(null);
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRegister.setBounds(261, 483, 194, 49);
		frame.getContentPane().add(btnRegister);
		
		JButton btnForgotPassword = new JButton("forgot password");
		btnForgotPassword.setBackground(new Color(255, 204, 204));
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				forgotpage.main(null);
			}
		});
		btnForgotPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnForgotPassword.setBounds(496, 483, 194, 49);
		frame.getContentPane().add(btnForgotPassword);
	}
	public static String getAccno()
	{
		return accountnumber;
	}
}
