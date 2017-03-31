package ATM;
/**
 * Central Class
 * @author afahd
 *
 */

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;

public class Central {

	private Security security;
	
	static Connection conexionBD = null;
	
	private int getBalance(String user_id){	
		int balance = 0; 
		
		Connection dbConnection = null;
		Statement statement = null;
		
		
		try
		{
			connectDB();
			
			dbConnection = conexionBD;
			statement = dbConnection.createStatement();				

			//Prepare SQL statement
			String sql = "select balance from BALANCE "
						+ "where USER_ID = " + user_id;
			
			//Execute statement and receive resultset
			ResultSet rs = statement.executeQuery(sql);
			// Go through ResultSet
			if(!rs.next()){					
				System.out.println("INVALID USER_ID");   //DELETE THIS
			}
			// Go through ResultSet
			if(rs.next()){					
				balance = Integer.parseInt(rs.getString("balance"));
				System.out.println("User Balance: " + balance);   //DELETE THIS
			}
			System.out.println("fin results");
			
			//Close resources
			rs.close();
			statement.close();
		}
		catch (SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			}
		
		return balance;
	}
	
	
	private void connectDB() {
		// TODO Auto-generated method stub
		
	}


	private void deposit(String user_id, int amount){	
		Connection dbConnection = null;
		Statement statement = null;
		
		try
		{
			connectDB();
			
			dbConnection = conexionBD;
			statement = dbConnection.createStatement();				

			//Prepare SQL statement
			String sql = "UPDATE balance "
						+ "SET balance = " 
						+ "(SELECT balance FROM balance WHERE USER_ID = " + user_id + ")"
						+ " + " + amount
						+ " WHERE USER_ID = " + user_id;
			
			//Execute statement and receive resultset
			statement.executeUpdate(sql);
			
			//Close resources
			statement.close();
		}
		catch (SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			}
	}
}
