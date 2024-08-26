package airline_Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class NewUser {

	public LoggedIn logIn = new LoggedIn();
	//public BookFlight bk = new BookFlight();
	
	public void insertNewUser(Connection c, Scanner scan, BookFlight bk) {
		ResultSet res = null;
		int numUsers = -1;
		Random rand = new Random();
		numUsers = 10000 + rand.nextInt(90000);
		String numberOfUsers = "Select Count(*) from users";
		try {
		PreparedStatement numbStatement = c.prepareStatement(numberOfUsers);
		res = numbStatement.executeQuery();
	//	if(res.next()) {
		//	 numUsers = res.getInt(1);
			// numUsers += 1;
	//	}
		}
		catch (SQLException e) {
			System.out.println("Insert Failed");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Please provide the following details");
		System.out.print("First Name: ");
		String firstName = scan.nextLine();
		System.out.print("Last Name: " );
		String lastName = scan.nextLine();
		System.out.print("User Name: ");
		String userName = scan.nextLine();
		System.out.print("Password: ");
		String password = scan.nextLine();
		System.out.print("Email: ");
		String email = scan.nextLine();
		System.out.print("Phone Number: ");
		String phoneNumber = scan.nextLine();
		String query = " Insert into users values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pres = c.prepareStatement(query);
			pres.setInt(1, numUsers);
			pres.setString(2, firstName);
			pres.setString(3, lastName);
			pres.setString(4, userName);
			pres.setString(5, password);
			pres.setString(6, email);
			pres.setString(7, phoneNumber);
			pres.executeUpdate();
			//c.commit();
			System.out.println("Hello " + firstName + " " + lastName + " your account has been created");
			logIn.loggedInUser(c, numUsers,bk);
		}
		catch (SQLException e) {
			System.out.println("Insert Failed");
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	
	}
	
}
