package airline_Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoggedIn {
	
	public RemoveUser removeUser = new RemoveUser();
	public CancelFlight cancelFlight = new CancelFlight();
	public MakeAPayment makePayment = new MakeAPayment();
	
	
	public void loggedInUser(Connection c, int userId, BookFlight bk) {
		String checkPayments = "select payments.status from payments, bookings where payments.bookingid = bookings.bookingid and bookings.userId = ? ";
		ResultSet res= null;
		boolean hasPaymentDue = false;
		try {
			PreparedStatement pres = c.prepareStatement(checkPayments);
			pres.setInt(1, userId);
			res = pres.executeQuery();
			if(res.next()) {
				//String status = res.getString("status");
				hasPaymentDue = true;
				String answer = " ";
				while(true) {
					Scanner scan = new Scanner(System.in);
					System.out.println("==================	Main Menu	===================");
					System.out.println();
					System.out.println("1) Delete Account\n2) Book A Flight\n3) Cancel A Flight");
					if(hasPaymentDue) {
						System.out.println("4) Make A Payment");
					}
					System.out.print(">> ");
					 answer = scan.nextLine().trim();
					if(answer.toLowerCase().equals("Delete Account".toLowerCase())) {
						removeUser.removeUser(c, userId);
					}
					else if(answer.toLowerCase().equals("Book A Flight".toLowerCase())) {
						bk.BookAFlight(c, userId);
						hasPaymentDue = true;
					}
					else if(answer.toLowerCase().equals("Cancel A Flight".toLowerCase())) {
						cancelFlight.cancelMyFlight(userId, c);
						hasPaymentDue = false;
					}
					else if(answer.toLowerCase().equals("End".toLowerCase())) {
						break;
					}
					else if(answer.toLowerCase().equals("Make A Payment".toLowerCase()) && hasPaymentDue) {
						makePayment.makePayment(userId, c);
						hasPaymentDue = false;
					}
					}
				//System.out.println(status);
					}
			else {
				 hasPaymentDue = false;
				while(true) {
					if(!hasPaymentDue) {
					String answer = " ";
					Scanner scan = new Scanner(System.in);
					System.out.println("==================	Main Menu	===================");
					System.out.println();
					System.out.println("1)Delete Account\n2)Book A Flight\n3)Cancel A Flight\n");
					System.out.print(">> ");
					 answer = scan.nextLine().trim();
					if(answer.toLowerCase().equals("Delete Account".toLowerCase())) {
						removeUser.removeUser(c, userId);
					}
					else if(answer.toLowerCase().equals("Book A Flight".toLowerCase())) {
						bk.BookAFlight(c, userId);
						hasPaymentDue = true;
					}
					else if(answer.toLowerCase().equals("Cancel A Flight".toLowerCase())) {
						cancelFlight.cancelMyFlight(userId, c);
					}
					else if(answer.toLowerCase().equals("End".toLowerCase())) {
						break;
					}
					}
					else if(hasPaymentDue) {
						String answer = " ";
						Scanner scan = new Scanner(System.in);
						System.out.println("==================	Main Menu	===================");
						System.out.println();
						System.out.println("1) Delete Account\n2) Book A Flight\n3) Cancel A Flight\n4) Make A Payment");
						System.out.print(">> ");
						 answer = scan.nextLine().trim();
						if(answer.toLowerCase().equals("Delete Account".toLowerCase())) {
							removeUser.removeUser(c, userId);
						}
						else if(answer.toLowerCase().equals("Book A Flight".toLowerCase())) {
							bk.BookAFlight(c, userId);
						}
						else if(answer.toLowerCase().equals("Cancel A Flight".toLowerCase())) {
							cancelFlight.cancelMyFlight(userId, c);
							hasPaymentDue = false;
						}
						else if(answer.toLowerCase().equals("End".toLowerCase())) {
							break;
						}
						else if(answer.toLowerCase().equals("Make A Payment".toLowerCase())) {
							makePayment.makePayment(userId, c);
							hasPaymentDue = false;
						}
					}
				}
			}
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//if(status.equals("Pending"))
		String answer = " ";
		
	}
}
