package airline_Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewFlightSchedule {
	
	public void viewSchedule(Connection c, int userId) {
		String schedule = "select bookingid, flightDescription, price, airlinename, a1.airportname as Departure, a2.airportname as Arrival from flights, airlines,airports a1, airports a2, bookings where flights.airlineid = airlines.airlineid and flights.departureairportid = a1.airportid and flights.arrivalairportid = a2.airportid and flights.flightid = bookings.flightid and bookings.userid = ?";
		//String scheduleString = " ";
		ResultSet res = null;
		try {
			PreparedStatement pres = c.prepareStatement(schedule);
			pres.setInt(1, userId);
			res = pres.executeQuery();
			if(res.next()) {
				int bookingId = res.getInt("bookingId");
				String flightDescription = res.getString("flightDescription");
				int price = res.getInt("price");
				String airlineName = res.getString("airlinename");
				String departureAirport = res.getString("Departure");
				String arrivalAirport = res.getString("Arrival");
				System.out.println("Your Flight Schedule");
				System.out.printf("%-10s %-30s %-10s %-20s %-20s %-20s%n", 
					    "BookingId", "Flight Description", "Price", "Airlines", "Departure Airport", "Arrival Airport");
					System.out.printf("%-10d %-30s %-10d %-20s %-20s %-20s%n", 
					    bookingId, flightDescription, price, airlineName, departureAirport, arrivalAirport);
				//System.out.println("BookingId " + bookingId + ", Flight Description: " + flightDescription + ", Price: " + price + ", Airlines: " + airlineName + ", Departure Airport: " + departureAirport + ", Arrival Airport: " + arrivalAirport);
			}
		}
		catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
