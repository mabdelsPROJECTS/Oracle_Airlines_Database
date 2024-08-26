package airline_Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoveUser {

	
	public void removeUser(Connection c, int userId) {
	    String query = "SELECT bookingID FROM bookings WHERE userID = ?";
	    ResultSet resultSet = null;

	    try (PreparedStatement pres = c.prepareStatement(query)) {
	        pres.setInt(1, userId);
	        resultSet = pres.executeQuery();

	        while (resultSet.next()) {
	            String bookingId = resultSet.getString("bookingID");

	            try (PreparedStatement pres2 = c.prepareStatement("DELETE FROM payments WHERE bookingID = ?")) {
	                pres2.setString(1, bookingId);
	                pres2.executeUpdate();
	            }

	            try (PreparedStatement pres3 = c.prepareStatement("DELETE FROM passengers WHERE bookingID = ?")) {
	                pres3.setString(1, bookingId);
	                pres3.executeUpdate();
	            }

	            try (PreparedStatement pres4 = c.prepareStatement("DELETE FROM bookings WHERE bookingID = ?")) {
	                pres4.setString(1, bookingId);
	                pres4.executeUpdate();
	            }
	        }

	        try (PreparedStatement pres5 = c.prepareStatement("DELETE FROM users WHERE userID = ?")) {
	            pres5.setInt(1, userId);
	            pres5.executeUpdate();
	        }

	       // c.commit(); // Commit if not auto-committed

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (resultSet != null) {
	            try {
	                resultSet.close();
	                System.out.println("Account Deleted");
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
}