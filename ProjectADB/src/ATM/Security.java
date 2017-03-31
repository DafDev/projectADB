package ATM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;



public class Security {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "atm";
	private static final String DB_PASSWORD = "atm";
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private Interface window;
	static Connection conexionBD = null;
	
	public Security(Interface interface1)
	{
		window=interface1;
		connectDB();
		disconnectDB();
		
	}
	
	public void connectDB(){
		
	      if (this.conexionBD == null)
	      {
	         try
	         {  //Load and register Oracle's JDBC driver
	            Class.forName(DB_DRIVER).newInstance();
	         }
	         catch (Exception ex)
	         {  
	            System.out.println(ex.getMessage());
	         }
	   
	         try
	         {  
	            //Establish connection
	            this.conexionBD = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);  
	         }
	         catch (SQLException ex)
	         {
	            JOptionPane.showMessageDialog(window,"Error when connecting to database.\n" + ex.getMessage(),
	                                          "Error",JOptionPane.ERROR_MESSAGE);
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	         }
	      }
	   }

	  
	  private void disconnectDB()
	   {
	      if (this.conexionBD != null)
	      {
	         try
	         {
	            this.conexionBD.close();
	            this.conexionBD = null;
	         }
	         catch (SQLException ex)
	         {
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	         }
	      }
	   }
	  
	 
		private void getUser(){
			Connection dbConnection = null;
			Statement statement = null;
			
			try
			{
				connectDB();
				
				dbConnection = conexionBD;
				statement = dbConnection.createStatement();		

				//Prepare SQL statement
				String sql = "SELECT USER_ID, FIRSTNAME from USERS";

				//Execute statement and receive ResultSet
				ResultSet rs = statement.executeQuery(sql);
				//Go through resultset
				if(rs.next()){					
					System.out.println("user id: " + rs.getString("USER_ID"));
					System.out.println("first name: " + rs.getString("FIRSTNAME"));
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
		}
		

		public boolean validateUser(String number_card, String PIN){
			boolean validUser = false;
			
			Connection dbConnection = null;
			Statement statement = null;
			
			try
			{
				connectDB();
				
				dbConnection = conexionBD;
				statement = dbConnection.createStatement();				

				//Prepare SQL statement
				String sql = "select * from CLIENTS "
							+ "where NUMBER_CARD = " + number_card + " and PIN = " + PIN;
				
				//Execute statement and receive resultset
				ResultSet rs = statement.executeQuery(sql);
				// Go through ResultSet
				if(rs.next()){					
					System.out.println("user id: " + rs.getString("NUMBER_CARD"));   //DELETE THIS
					System.out.println("PIN: " + rs.getString("PIN"));          //DELETE THIS
					validUser = true;
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
			
			return validUser;
		}
		
		public boolean validateUser2(String number_card, String PIN){
		return true;
		}
		private static String getCurrentTimeStamp() {

			java.util.Date today = new java.util.Date();
			return dateFormat.format(today.getTime());

		}
}
