import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class resetpassword {

	private JFrame frame;
	private JTextField np;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					resetpassword window = new resetpassword();
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
	private JPasswordField cp;
	public resetpassword() {
		db.connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(102, 204, 153));
		frame.setBounds(100, 100, 774, 564);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblResetYourPassword = new JLabel("Reset your password");
		lblResetYourPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblResetYourPassword.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblResetYourPassword.setBounds(215, 25, 353, 52);
		frame.getContentPane().add(lblResetYourPassword);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(31, 101, 693, 317);
		frame.getContentPane().add(panel);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblConfirmPassword.setBounds(65, 134, 256, 52);
		panel.add(lblConfirmPassword);
		
		JButton btnVerify = new JButton("Reset");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String newp = np.getText();
				String ccp = cp.getText();
				if(newp.isEmpty() || ccp.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter all the fields");
				}
				else if(newp.equals(ccp))
				{
					String accn = forgotpage.getAccno();
					boolean b = db.resetpassword(accn,newp);
					if(b==true)
					{
						JOptionPane.showMessageDialog(null, "Password successfully changed!");
						frame.setVisible(false);
						loginpage.main(null);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Password can't be changed!");
						np.setText("");
						cp.setText("");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "New Password and Confirmed Password are different!");
					np.setText("");
					cp.setText("");
				}
			}
		});
		btnVerify.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnVerify.setBackground(new Color(255, 204, 204));
		btnVerify.setBounds(276, 232, 132, 49);
		panel.add(btnVerify);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewPassword.setBounds(65, 52, 235, 52);
		panel.add(lblNewPassword);
		
		np = new JTextField();
		np.setFont(new Font("Tahoma", Font.PLAIN, 24));
		np.setColumns(10);
		np.setBounds(343, 53, 279, 52);
		panel.add(np);
		
		cp = new JPasswordField();
		cp.setFont(new Font("Tahoma", Font.PLAIN, 24));
		cp.setBounds(347, 134, 275, 52);
		panel.add(cp);
		
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
		btnReturn.setBounds(281, 438, 194, 49);
		frame.getContentPane().add(btnReturn);
	}
}
