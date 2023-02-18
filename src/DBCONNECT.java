import java.sql.*;

import net.proteanit.sql.DbUtils;

import java.awt.*;
public class DBCONNECT 
{
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	public void connect()
	{
		try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3307/project";
            String username = "root";
            String password = "";
            con = DriverManager.getConnection(url,username,password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	public boolean checkCredentials(String AccountNumber,String Password)
	{
		try {
			String query = "SELECT password FROM BANKDATA WHERE accountno="+'"'+AccountNumber+'"';
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			String pass="";
			while(rs.next())
				pass = rs.getString(1);
			if(Password.equals(pass))
				return true;
			else
				return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public String registration(String name,String dob,String gender,String address,String email,String password){
		try
		{
			String query = "INSERT INTO BANKDATA(name,dob,gender,address,email,password) values(?,?,?,?,?,?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1,name);
			stmt.setString(2,dob);
			stmt.setString(3,gender);
			stmt.setString(4,address);
			stmt.setString(5,email);
			stmt.setString(6,password);
			stmt.executeUpdate();			
			
			query="SELECT accountno FROM BANKDATA WHERE email=? and password=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			String accno="";
			while(rs.next())
				accno = rs.getString(1);
			return accno;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "false";
		}
	}
	public void registrationAccountinfo(String accno)
	{
		try
		{
			String query = "INSERT INTO ACCOUNTINFO(accountno) values(?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1,accno);
			//stmt.setString(2,balance);
			stmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public String getName(String accno)
	{
		try {
			String name="";
			String query="Select name from BANKDATA where accountno=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, accno);
			rs = stmt.executeQuery();
			while(rs.next())
				name=rs.getString(1);
			return name;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "false";
		}
	}
	public String getBalance(String accno)
	{
		try {
			String balance="";
			String query="Select balance from Accountinfo where accountno=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, accno);
			rs = stmt.executeQuery();
			while(rs.next())
				balance=rs.getString(1);
			return balance;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "false";
		}
	}
	public String withdrawMoney(String accno,long money)
	{
		try {
			String balance="";
			String query="Select balance from Accountinfo where accountno=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, accno);
			rs = stmt.executeQuery();
			while(rs.next())
				balance=rs.getString(1);
			long bal = Long.parseLong(balance);
			if(bal-money>=500)
				bal-=money;
			else
				return "Insufficient Balance";
			
			query="UPDATE Accountinfo set balance=? where accountno=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, bal+"");
			stmt.setString(2,accno);
			stmt.executeUpdate();
			
			query="INSERT into Transaction(accountno,remark,balance) values(?,?,?)";
			stmt=con.prepareStatement(query);
			stmt.setString(1, accno);
			stmt.setString(2, "Withdrawn Rs."+money);
			stmt.setString(3, bal+"");
			stmt.executeUpdate();
			
			return "true";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "false";
		}
	}
	public boolean depositMoney(String accno,long money)
	{
		try {
			String balance="";
			String query="Select balance from Accountinfo where accountno=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, accno);
			rs = stmt.executeQuery();
			while(rs.next())
				balance=rs.getString(1);
			long bal = Long.parseLong(balance);
			bal+=money;
			
			query="UPDATE Accountinfo set balance=? where accountno=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, bal+"");
			stmt.setString(2,accno);
			stmt.executeUpdate();
			
			query="INSERT into Transaction(accountno,remark,balance) values(?,?,?)";
			stmt=con.prepareStatement(query);
			stmt.setString(1, accno);
			stmt.setString(2, "Deposited Rs."+money);
			stmt.setString(3, bal+"");
			stmt.executeUpdate();
			
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public boolean isPresent(String toaccno)
	{
		try {
			String query="Select accountno from accountinfo";
			stmt=con.prepareStatement(query);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				String no=rs.getString(1);
				if(toaccno.equals(no))
					return true;
			}
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public String transfer(String accno,long money,String toaccbo)
	{
		try {
			String query = "Select Balance from accountinfo where accountno=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1,accno);
			rs = stmt.executeQuery();
			long ourbalance=0;
			while(rs.next())
			{
				ourbalance = Long.parseLong(rs.getString(1));
			}
			
			query="Select Balance from accountinfo where accountno=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1,toaccbo);
			rs = stmt.executeQuery();
			long therebalance=0;
			while(rs.next())
			{
				therebalance = Long.parseLong(rs.getString(1));
			}
			if(ourbalance-money>=500)
			{
			ourbalance-=money;
			therebalance+=money;
			
			query = "UPDATE accountinfo set balance=? where accountno=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1,ourbalance+"");
			stmt.setString(2,accno);
			stmt.executeUpdate();
			
			query = "UPDATE accountinfo set balance=? where accountno=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1,therebalance+"");
			stmt.setString(2,toaccbo);
			stmt.executeUpdate();
			
			query = "Insert into Transaction(accountno,remark,balance) values(?,?,?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1,toaccbo);
			stmt.setString(2,"Received Rs."+money+" from account number - "+accno);
			stmt.setString(3,therebalance+"");
			stmt.executeUpdate();
			
			query = "Insert into Transaction(accountno,remark,balance) values(?,?,?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1,accno);
			stmt.setString(2,"Transferred Rs."+money+" to account number - "+toaccbo);
			stmt.setString(3,ourbalance+"");
			stmt.executeUpdate();
			
			return "true";
			}
			else
				return "Insufficient Balance";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "false";
		}
	}
	public boolean verifyIdentity(String accno,String name,String dob,String email)
	{
		try {
			String query = "Select name,dob,email from BANKDATA where accountno = ?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, accno);
			rs = stmt.executeQuery();
			String n="",d="",e="";
			while(rs.next())
			{
				n = rs.getString(1);
				d = rs.getString(2);
				e = rs.getString(3);
			}
			if(name.equals(n) && dob.equals(d) && email.equals(e))
				return true;
			else
				return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public boolean resetpassword(String accno,String password)
	{
		try {
			
			String query = "Update BANKDATA set password=? where accountno=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, password);
			stmt.setString(2, accno);
			stmt.executeUpdate();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
