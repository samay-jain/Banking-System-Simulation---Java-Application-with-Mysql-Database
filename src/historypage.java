import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
public class historypage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					historypage window = new historypage();
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
	private JTable table;
	public historypage() {
		db.connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBackground(new Color(240, 240, 240));
		frame.getContentPane().setBackground(new Color(102, 204, 153));
		frame.setBounds(100, 100, 887, 659);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String accno = loginpage.getAccno();
		JLabel lblTransactionHistoryOf = new JLabel("Transaction History of Account Number - "+accno);
		lblTransactionHistoryOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransactionHistoryOf.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTransactionHistoryOf.setBackground(SystemColor.menu);
		lblTransactionHistoryOf.setBounds(10, 41, 853, 37);
		frame.getContentPane().add(lblTransactionHistoryOf);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 100, 741, 452);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		try {
			String query="Select remark as Remark,dateandtime as Timestamp,balance as Balance from Transaction where accountno=?";
			db.stmt = db.con.prepareStatement(query);
			db.stmt.setString(1, accno);
			db.rs=db.stmt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(db.rs));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
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
		btnGoBack.setBounds(352, 562, 152, 49);
		frame.getContentPane().add(btnGoBack);
	}
}
