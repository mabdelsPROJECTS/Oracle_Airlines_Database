package airline_Booking;
import java.util.Random;
import java.util.Scanner;
import java.sql.*;
public class BookFlight {

	public void BookAFlight (Connection c, int userId) {
		int numUsers = -1;
		int counter = 0;
		int flightId = -1;
		System.out.println("Which Flight Would You Like To Book, Please Type The Flight ID Number");
		String query = "Select flightid, airlinename, flightdescription, airportname, location, price from (Select flightid, airlinename, departureairportid, arrivalairportid, flightdescription, price from flights join airlines on flights.airlineid = airlines.airlineid) t1 inner join airports on t1.departureairportid = airports.airportid";
		String formatted = " ";
		ResultSet res = null;
		try {
		PreparedStatement pres = c.prepareStatement(query);
		res = pres.executeQuery();
		while(res.next()) {
			counter += 1;
			 flightId = res.getInt("flightid");
			String airlineName = res.getString("airlinename");
			String flightDescrip = res.getString("FlightDescription");
			String airportName = res.getString("AirportName");
			String location = res.getString("Location");
            int price = res.getInt("Price");
            System.out.println( counter + ") Airline: " + airlineName + ", Flight Description: " + flightDescrip + ", Airport: " + airportName + ", Location: " + location + ", Price: " + price + ", FlightID Number: " + flightId);
		}
		}
		catch (SQLException e) {
			System.out.println("Insert Failed");
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		System.out.print(">> ");
		Scanner scanner = new Scanner(System.in);
		String answer = scanner.nextLine();
		int answerInt = Integer.parseInt(answer);
		System.out.println("Please enter your passport number: ");
		System.out.print(">> ");
		String passportNumber = scanner.nextLine();
		String numberOfUsers = "Select Count(*) from bookings";
		try {
		PreparedStatement numbStatement = c.prepareStatement(numberOfUsers);
		res = numbStatement.executeQuery();
		//if(res.next()) {
			// numUsers = res.getInt(1);
			// numUsers += 1;
		//}
		Random rand = new Random();
		numUsers = 10000 + rand.nextInt(90000);
		}
		catch (SQLException e) {
			System.out.println("Insert Failed");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		String insertQuery = "insert into bookings values (?,?,?,?,?,?)";
		try {
		PreparedStatement pres = c.prepareStatement(insertQuery);
		pres.setInt(1, numUsers);
		pres.setInt(2, userId);
		pres.setInt(3, answerInt);
		Date sqlDate = new Date(System.currentTimeMillis());
        pres.setDate(4, sqlDate);
        pres.setString(5, "Confirmed");
        pres.setString(6, passportNumber);
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

