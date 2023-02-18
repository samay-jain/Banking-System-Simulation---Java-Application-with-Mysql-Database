import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class forgotpage {

	private JFrame frame;
	private JTextField n;
	private JTextField d;
	private JTextField em;
	private JTextField ac;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					forgotpage window = new forgotpage();
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
	static String accountno = ""; 
	DBCONNECT db = new DBCONNECT();
	public forgotpage() {
		db.connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(102, 204, 153));
		frame.setResizable(false);
		frame.setBounds(100, 100, 1061, 681);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblVerifyYourself = new JLabel("Verify yourself");
		lblVerifyYourself.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerifyYourself.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblVerifyYourself.setBounds(365, 37, 308, 52);
		frame.getContentPane().add(lblVerifyYourself);
		
		JPanel panel = new JPanel();
		panel.setBounds(144, 116, 780, 422);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblName.setBounds(65, 134, 176, 52);
		panel.add(lblName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.LEFT);
		lblDateOfBirth.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblDateOfBirth.setBounds(65, 196, 176, 52);
		panel.add(lblDateOfBirth);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblEmail.setBounds(65, 258, 176, 52);
		panel.add(lblEmail);
		
		JButton btnVerify = new JButton("Verify");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				accountno = ac.getText();
				String name = n.getText();
				String dob = d.getText();
				String email = em.getText();
				if(accountno.isEmpty() || name.isEmpty() || dob.isEmpty() || email.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter all the details!");
				}
				else
				{
				boolean result = db.verifyIdentity(accountno, name, dob, email);
				if(result==true)
				{
					frame.setVisible(false);
					resetpassword.main(null);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Details are Incorrect!");
					ac.setText("");
					n.setText("");
					d.setText("");
					em.setText("");
				}
				}
			}
		});
		btnVerify.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnVerify.setBackground(new Color(255, 204, 204));
		btnVerify.setBounds(311, 347, 132, 49);
		panel.add(btnVerify);
		
		n = new JTextField();
		n.setFont(new Font("Tahoma", Font.PLAIN, 24));
		n.setColumns(10);
		n.setBounds(311, 135, 279, 52);
		panel.add(n);
		
		d = new JTextField();
		d.setFont(new Font("Tahoma", Font.PLAIN, 24));
		d.setColumns(10);
		d.setBounds(311, 197, 279, 52);
		panel.add(d);
		
		em = new JTextField();
		em.setFont(new Font("Tahoma", Font.PLAIN, 24));
		em.setColumns(10);
		em.setBounds(311, 259, 279, 52);
		panel.add(em);
		
		JLabel lblAccountNumber = new JLabel("Account Number");
		lblAccountNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountNumber.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAccountNumber.setBounds(65, 72, 235, 52);
		panel.add(lblAccountNumber);
		
		ac = new JTextField();
		ac.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ac.setColumns(10);
		ac.setBounds(310, 73, 279, 52);
		panel.add(ac);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				loginpage.main(null);
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReturn.setBackground(new Color(255, 204, 204));
		btnReturn.setBounds(431, 571, 194, 49);
		frame.getContentPane().add(btnReturn);
	}
	public static String getAccno()
	{
		return accountno;
	}
}
