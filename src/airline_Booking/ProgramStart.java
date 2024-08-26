package airline_Booking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;
public class ProgramStart {
	
	private  Connection c;
	public BookFlight bk = new BookFlight();
	public NewUser newUser = new NewUser();
	public CheckUser checkUser = new CheckUser();
	public ProgramStart() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@acadoradbprd01.dpu.depaul.edu:1521:ACADPRD0";
			 c = DriverManager.getConnection(url, "MABDELS1", "cdm2118442");
			System.out.println("Connected");
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("Not Connected");
			e1.printStackTrace();
		}
		}

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		ProgramStart sp = new ProgramStart();
		sp.startLogin();
		}
	
	
//	private void removeUser(int userId) {
//		String query = "Delete from usersLog where userid = ?";
//		try {
//			PreparedStatement pres = c.prepareStatement(query);
//			pres.setInt(1, userId);
//			pres.executeUpdate();
//			System.out.println("Your account has been deleted");
//			String updateUserIds = "Update usersLog set userId = userId -1 where userId != ?";
//			try {
//				PreparedStatement pres2 = c.prepareStatement(updateUserIds);
//				pres.setInt(1, userId);
//				pres.executeUpdate();
//			}
//			catch (SQLException e) {
//				System.out.println("Insert Failed");
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public void loggedInUser(int userId) {
//		Scanner scan = new Scanner(System.in);
//		System.out.println("==================	Main Menu	===================");
//		System.out.println();
//		System.out.println("1)Delete Account\n2)Book A Flight\n3)Cancel A Flight\n");
//		System.out.print(">> ");
//		String answer = scan.nextLine();
//		if(answer.toLowerCase().equals("Delete Account".toLowerCase())) {
//			removeUser(userId);
//		}
//		else if(answer.toLowerCase().equals("Book A Flight".toLowerCase())) {
//			bk.BookAFlight(c, userId);
//		}
//		}
	
//	private void insertNewUser(Scanner scan) {
//		ResultSet res = null;
//		int numUsers = -1;
//		Random rand = new Random();
//		numUsers = 10000 + rand.nextInt(90000);
//		String numberOfUsers = "Select Count(*) from users";
//		try {
//		PreparedStatement numbStatement = c.prepareStatement(numberOfUsers);
//		res = numbStatement.executeQuery();
//	//	if(res.next()) {
//		//	 numUsers = res.getInt(1);
//			// numUsers += 1;
//	//	}
//		}
//		catch (SQLException e) {
//			System.out.println("Insert Failed");
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println("Please provide the following details");
//		System.out.print("First Name: ");
//		String firstName = scan.nextLine();
//		System.out.print("Last Name: " );
//		String lastName = scan.nextLine();
//		System.out.print("User Name: ");
//		String userName = scan.nextLine();
//		System.out.print("Password: ");
//		String password = scan.nextLine();
//		System.out.print("Email: ");
//		String email = scan.nextLine();
//		System.out.print("Phone Number: ");
//		String phoneNumber = scan.nextLine();
//		String query = " Insert into users values(?,?,?,?,?,?,?)";
//		try {
//			PreparedStatement pres = c.prepareStatement(query);
//			pres.setInt(1, numUsers);
//			pres.setString(2, firstName);
//			pres.setString(3, lastName);
//			pres.setString(4, userName);
//			pres.setString(5, password);
//			pres.setString(6, email);
//			pres.setString(7, phoneNumber);
//			pres.executeUpdate();
//			//c.commit();
//			System.out.println("Hello " + firstName + " " + lastName + " your account has been created");
//			loggedInUser(numUsers);
//		}
//		catch (SQLException e) {
//			System.out.println("Insert Failed");
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//	}
//	
//	}
	
//	public void checkUserInfo(String username, String password) {
//		int idOfUser = -1;
//		String query = "Select count(*) from users where username= ? and password = ?";
//		//Statement statement = null;
//		ResultSet res = null;
//		try {
//			PreparedStatement pres = c.prepareStatement(query);
//			pres.setString(1, username);
//			pres.setString(2, password);
//			res = pres.executeQuery();
//			
//			if(res.next()) {
//				int count = res.getInt(1);
//				if(count == 1) {
//					System.out.println("Your Logged In");
//					String getId = "Select userId from users where username = ? and password = ?";
//					try {
//						pres = c.prepareStatement(getId);
//						pres.setString(1, username);
//						pres.setString(2, password);
//						res = pres.executeQuery();
//						if(res.next()) {
//						 idOfUser = res.getInt(1);
//						}
//						loggedInUser(idOfUser);
//						}
//					catch (SQLException e) {
//						System.out.println("Insert Failed");
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//				}
//				}
//				else {
//					while(count != 1) {
//					System.out.println("Incorrect username or password");
//					Scanner scan = new Scanner(System.in);
//					System.out.print("Enter username: ");
//					String returningUsername = scan.nextLine();
//					System.out.print("Enter password: ");
//					String returningPassword = scan.nextLine();
//					String query2 = "Select count(*) from users where username= ? and password = ?";
//					//Statement statement = null;
//					ResultSet res2 = null;
//					try {
//						PreparedStatement pres2 = c.prepareStatement(query2);
//						pres2.setString(1, returningUsername);
//						pres2.setString(2, returningPassword);
//						res = pres2.executeQuery();
//						if(res.next()) {
//							count = res.getInt(1);
//						}
//						}
//					catch (SQLException e) {
//						System.out.println("Insert Failed");
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			}
//					System.out.println("Logged In");
//					loggedInUser(idOfUser);
//				}
//			}
//		}
//		
//		catch (SQLException e) {
//			System.out.println("Insert Failed");
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//	}
//	}
	
	
		
		
		
		
		
		public void startLogin() {
			System.out.println("Welcome\nAre you a new user or already have an account");
			String[] usersInfo = new String[4];
			Scanner scan = new Scanner(System.in);
			String option1 = "new user";
			String option2 = "already have an account";
			String answer = scan.nextLine();
			if(answer.equals(option1.toLowerCase())) {
				//insertNewUser(scan);
				newUser.insertNewUser(c, scan, bk);
				}
			else if(answer.equals(option2.toLowerCase())) {
				System.out.println("Hello returning user");
				System.out.print("Enter username: ");
				String returningUsername = scan.nextLine();
				String trimmedUser = returningUsername.trim();
				System.out.print("Enter password: ");
				String returningPassword = scan.nextLine();
				String trimmedPass = returningPassword.trim();
				
				checkUser.checkUserInfo(c, trimmedUser, trimmedPass, bk);
			}
			
		}
		
		
		}