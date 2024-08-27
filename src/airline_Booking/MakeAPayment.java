package airline_Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MakeAPayment {
	
	public void makePayment(int userId, Connection c) {
		int bookingId = -1;
		System.out.println("What payment method would you like to use?\n1) Credit Card\n2) PayPal\n3) Debit Credit");
		System.out.print(">> ");
		Scanner scan = new Scanner(System.in);
		String paymentMethod = scan.nextLine();
		String query = "Select bookingid from bookings where userId = ?";
		ResultSet res = null;
		try {
			PreparedStatement pres = c.prepareStatement(query);
			pres.setInt(1, userId);
			res = pres.executeQuery();
			if(res.next()) {
				 bookingId = res.getInt("bookingId");
			}
		}
		catch (SQLException e) {
            e.printStackTrace();
        }
		
		String updatePayments = "Update payments set paymentdate = NULL , paymentmethod = ?, status = 'Paid' where bookingid = ?";
		try {
			PreparedStatement pres1 = c.prepareStatement(updatePayments);
			pres1.setString(1, paymentMethod);
			pres1.setInt(2, bookingId);
			pres1.executeUpdate();
			System.out.println("Payment Processed, Thank You!");
		}
		catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
