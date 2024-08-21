package airline_Booking;
import java.util.Scanner;
import java.sql.*;
public class BookFlight {

	public void BookAFlight (Connection c, int userId) {
		int numUsers = -1;
		int flightId = -1;
		System.out.println("Which Flight Would You Like To Book");
		String query = "Select fid, toWhere, price from flights";
		String formatted = " ";
		ResultSet res = null;
		try {
		PreparedStatement pres = c.prepareStatement(query);
		res = pres.executeQuery();
		while(res.next()) {
			 flightId = res.getInt("FID");
			String toWhere = res.getString("ToWhere");
            int price = res.getInt("Price");
            System.out.println( flightId + ": " + toWhere + ", Price: " + price);
		}
		}
		catch (SQLException e) {
			System.out.println("Insert Failed");
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		System.out.print(">");
		Scanner scanner = new Scanner(System.in);
		String answer = scanner.nextLine();
		int answerInt = Integer.parseInt(answer);
		String numberOfUsers = "Select Count(*) from bookings";
		try {
		PreparedStatement numbStatement = c.prepareStatement(numberOfUsers);
		res = numbStatement.executeQuery();
		if(res.next()) {
			 numUsers = res.getInt(1);
			 numUsers += 1;
		}
		}
		catch (SQLException e) {
			System.out.println("Insert Failed");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		String insertQuery = "insert into bookings values (?,?,?,?)";
		try {
		PreparedStatement pres = c.prepareStatement(insertQuery);
		pres.setInt(1, numUsers);
		pres.setInt(2, userId);
		pres.setInt(3, answerInt);
		Date sqlDate = new Date(System.currentTimeMillis());
        pres.setDate(4, sqlDate); 
        pres.executeUpdate();
        System.out.println("Flight Booked");
		}
		catch (SQLException e) {
			System.out.println("Insert Failed");
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
}

