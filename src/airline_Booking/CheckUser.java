package airline_Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CheckUser {

	
	public LoggedIn logIn = new LoggedIn();
	
	public void checkUserInfo(Connection c, String username, String password, BookFlight bk) {
		int idOfUser = -1;
		String query = "Select count(*) from users where username = ? AND password = ?";
		//Statement statement = null;
		ResultSet res = null;
		try {
			PreparedStatement pres = c.prepareStatement(query);
			pres.setString(1, username);
			pres.setString(2, password);
			res = pres.executeQuery();
			
			if(res.next()) {
				int count = res.getInt(1);
				//System.out.println("Count value: " + count);
				if(count == 1) {
					System.out.println("Your Logged In");
					String getId = "Select userId from users where username = ? and password = ?";
					try {
						pres = c.prepareStatement(getId);
						pres.setString(1, username);
						pres.setString(2, password);
						res = pres.executeQuery();
						if(res.next()) {
						 idOfUser = res.getInt(1);
						}
						logIn.loggedInUser(c, idOfUser, bk);
						}
					catch (SQLException e) {
						System.out.println("Insert Failed");
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				}
				else {
					while(count != 1) {
					System.out.println("Incorrect username or password");
					Scanner scan = new Scanner(System.in);
					System.out.print("Enter username: ");
					String returningUsername = scan.nextLine();
					System.out.print("Enter password: ");
					String returningPassword = scan.nextLine();
					String query2 = "Select count(*) from users where username= ? and password = ?";
					//Statement statement = null;
					ResultSet res2 = null;
					try {
						PreparedStatement pres2 = c.prepareStatement(query2);
						pres2.setString(1, returningUsername);
						pres2.setString(2, returningPassword);
						res = pres2.executeQuery();
						if(res.next()) {
							count = res.getInt(1);
						}
						}
					catch (SQLException e) {
						System.out.println("Insert Failed");
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
					System.out.println("Logged In");
					logIn.loggedInUser(c,idOfUser,bk);
				}
			}
		}
		
		catch (SQLException e) {
			System.out.println("Insert Failed");
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
}
