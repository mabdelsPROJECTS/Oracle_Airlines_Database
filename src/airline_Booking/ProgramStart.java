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