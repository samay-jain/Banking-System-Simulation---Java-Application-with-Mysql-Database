import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import java.awt.Color;

public class registerpage {

	private JFrame frame;
	private JTextField textname;
	private JTextField textdob;
	private JTextField textaddress;
	private JTextField textemail;
	private JPasswordField confpassword;
	private JTextField textpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerpage window = new registerpage();
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
	public registerpage() 
	{
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
		frame.setBounds(100, 100, 1023, 681);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblOpenABank = new JLabel("Open a Bank Account");
		lblOpenABank.setBounds(365, 27, 329, 37);
		lblOpenABank.setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.getContentPane().add(lblOpenABank);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(114, 87, 794, 481);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblName.setBounds(40, 42, 81, 37);
		panel.add(lblName);
		
		textname = new JTextField();
		textname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textname.setBounds(143, 42, 222, 37);
		panel.add(textname);
		textname.setColumns(10);
		
		JLabel lblAge = new JLabel("Date of Birth");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAge.setBounds(40, 101, 144, 37);
		panel.add(lblAge);
		
		textdob = new JTextField();
		textdob.setToolTipText("yyyy-mm-dd");
		textdob.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textdob.setColumns(10);
		textdob.setBounds(194, 102, 222, 37);
		panel.add(textdob);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblGender.setBounds(40, 160, 95, 37);
		panel.add(lblGender);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAddress.setBounds(40, 221, 104, 37);
		panel.add(lblAddress);
		
		textaddress = new JTextField();
		textaddress.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textaddress.setColumns(10);
		textaddress.setBounds(154, 221, 352, 37);
		panel.add(textaddress);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEmail.setBounds(40, 285, 104, 37);
		panel.add(lblEmail);
		
		JLabel lblCreateAPassword = new JLabel("Create a password");
		lblCreateAPassword.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblCreateAPassword.setBounds(40, 346, 224, 37);
		panel.add(lblCreateAPassword);
		
		textemail = new JTextField();
		textemail.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textemail.setColumns(10);
		textemail.setBounds(154, 285, 352, 37);
		panel.add(textemail);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblConfirmPassword.setBounds(40, 406, 224, 37);
		panel.add(lblConfirmPassword);
		
		confpassword = new JPasswordField();
		confpassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		confpassword.setBounds(274, 406, 248, 37);
		panel.add(confpassword);
		
		textpassword = new JTextField();
		textpassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textpassword.setColumns(10);
		textpassword.setBounds(273, 346, 249, 37);
		panel.add(textpassword);
		
		final JCheckBox cb1 = new JCheckBox("Male");
		cb1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cb1.setBounds(170, 160, 81, 37);
		panel.add(cb1);
		
		final JCheckBox cb2 = new JCheckBox("Female");
		cb2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cb2.setBounds(253, 160, 91, 39);
		panel.add(cb2);
		
		final JCheckBox cb3 = new JCheckBox("Others");
		cb3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cb3.setBounds(358, 160, 81, 39);
		panel.add(cb3);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(255, 204, 204));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
				loginpage.main(null);
			}
		});
		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(new Color(255, 204, 204));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String name = textname.getText();
				String dob = textdob.getText();
				String address = textaddress.getText();
				String gender="";
				String email=textemail.getText();
				String password=textpassword.getText();
				String cp=confpassword.getText();
				if(cb1.isSelected())
					gender="Male";
				else if(cb2.isSelected())
					gender="Female";
				else if(cb3.isSelected())
					gender="Others";
				if(name.isEmpty() || dob.isEmpty() || address.isEmpty() || gender.isEmpty() || email.isEmpty() || password.isEmpty() || cp.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter all the required information!");
				}
				else if(!password.equals(cp))
				{
					JOptionPane.showMessageDialog(null, "Your password doesn't match with the confirmed password!");
					textpassword.setText("");
					confpassword.setText("");
				}
				else
				{
					String accno=db.registration(name, dob, gender, address, email, password);
					if(!accno.equals("false"))
					{
						db.registrationAccountinfo(accno);
						JOptionPane.showMessageDialog(null, "Registration Successfull! Your Account Number is "+'"'+accno+'"');
						clear();
						cb1.setSelected(false);
						cb2.setSelected(false);
						cb3.setSelected(false);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Registration unsuccessfull!");
						clear();
						cb1.setSelected(false);
						cb2.setSelected(false);
						cb3.setSelected(false);
					}
				}
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnRegister.setBounds(321, 583, 135, 49);
		frame.getContentPane().add(btnRegister);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnLogin.setBounds(531, 583, 135, 49);
		frame.getContentPane().add(btnLogin);
		cb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				cb2.setSelected(false);
				cb3.setSelected(false);
			}
		});
		cb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				cb1.setSelected(false);
				cb3.setSelected(false);
			}
		});
		cb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				cb1.setSelected(false);
				cb2.setSelected(false);
			}
		});
	}
	public void clear()
	{
		textname.setText("");
		textdob.setText("");
		textaddress.setText("");
		textemail.setText("");
		textpassword.setText("");
		confpassword.setText("");
	}
}
