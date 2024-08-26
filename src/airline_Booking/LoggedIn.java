package airline_Booking;

import java.sql.Connection;
import java.util.Scanner;

public class LoggedIn {
	
	public RemoveUser removeUser = new RemoveUser();
	
	
	public void loggedInUser(Connection c, int userId, BookFlight bk) {
		Scanner scan = new Scanner(System.in);
		System.out.println("==================	Main Menu	===================");
		System.out.println();
		System.out.println("1)Delete Account\n2)Book A Flight\n3)Cancel A Flight\n");
		System.out.print(">> ");
		String answer = scan.nextLine();
		if(answer.toLowerCase().equals("Delete Account".toLowerCase())) {
			//removeUser(userId);
			removeUser.removeUser(c, userId);
		}
		else if(answer.toLowerCase().equals("Book A Flight".toLowerCase())) {
			bk.BookAFlight(c, userId);
		}
		}

}
