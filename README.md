# Airlines Database Schema

This document describes the database schema for an airline booking system. The database consists of several tables to store information about users, airlines, flights, bookings, airplanes, passengers, payments, and baggage.

## Tables

### 1. Users Table

| Column      | Data Type     | Constraints  |
|-------------|---------------|--------------|
| userID      | INT           | PRIMARY KEY  |
| firstName   | VARCHAR2(50)  | NOT NULL     |
| lastName    | VARCHAR2(50)  | NOT NULL     |
| username    | VARCHAR2(50)  | NOT NULL     |
| password    | VARCHAR2(255) | NOT NULL     |
| email       | VARCHAR2(100) |              |
| phoneNumber | VARCHAR2(20)  |              |

---

### 2. Airlines Table

| Column      | Data Type     | Constraints  |
|-------------|---------------|--------------|
| airlineID   | INT           | PRIMARY KEY  |
| airlineName | VARCHAR2(100) | NOT NULL     |
| location    | VARCHAR2(100) |              |

---

### 3. Flights Table

| Column             | Data Type       | Constraints  |
|--------------------|-----------------|--------------|
| flightID           | INT             | PRIMARY KEY  |
| airlineID          | INT             | FOREIGN KEY (References Airlines.airlineID) |
| departureAirportID | INT             | FOREIGN KEY (References Airports.airportID) |
| arrivalAirportID   | INT             | FOREIGN KEY (References Airports.airportID) |
| flightDescription  | VARCHAR2(255)   |              |
| price              | DECIMAL(10, 2)  |              |

---

### 4. Bookings Table

| Column         | Data Type      | Constraints  |
|----------------|----------------|--------------|
| bookingID      | INT            | PRIMARY KEY  |
| userID         | INT            | FOREIGN KEY (References Users.userID) |
| flightID       | INT            | FOREIGN KEY (References Flights.flightID) |
| bookingDate    | DATE           |              |
| status         | VARCHAR2(50)   |              |
| passportNumber | VARCHAR2(50)   |              |

---

### 5. Passengers Table

| Column         | Data Type     | Constraints  |
|----------------|---------------|--------------|
| passengerID    | INT           | PRIMARY KEY  |
| bookingID      | INT           | FOREIGN KEY (References Bookings.bookingID) |
| firstName      | VARCHAR(50)   | NOT NULL     |
| lastName       | VARCHAR(50)   | NOT NULL     |
| passportNumber | VARCHAR(20)   |              |

---

### 6. Airplanes Table

| Column      | Data Type      | Constraints  |
|-------------|----------------|--------------|
| airplaneID  | INT            | PRIMARY KEY  |
| model       | VARCHAR2(100)  |              |
| capacity    | INT            |              |
| airlineName | VARCHAR2(100)  |              |

---

### 7. Flight Schedules Table

| Column        | Data Type     | Constraints  |
|---------------|---------------|--------------|
| scheduleID    | INT           | PRIMARY KEY  |
| flightID      | INT           | FOREIGN KEY (References Flights.flightID) |
| airplaneID    | INT           | FOREIGN KEY (References Airplanes.airplaneID) |
| departureTime | VARCHAR2(30)  |              |
| arrivalTime   | VARCHAR2(30)  |              |
| duration      | NUMBER        |              |

---

### 8. Airports Table

| Column      | Data Type      | Constraints  |
|-------------|----------------|--------------|
| airportID   | INT            | PRIMARY KEY  |
| airportName | VARCHAR2(100)  |              |
| location    | VARCHAR2(100)  |              |

---

### 9. Payments Table

| Column        | Data Type     | Constraints  |
|---------------|---------------|--------------|
| paymentID     | INT           | PRIMARY KEY  |
| bookingID     | INT           | FOREIGN KEY (References Bookings.bookingID) |
| amount        | DECIMAL(10, 2)|              |
| paymentDate   | DATE          |              |
| paymentMethod | VARCHAR2(50)  |              |
| status        | VARCHAR2(50)  |              |

---

### 10. Baggage Table

| Column        | Data Type      | Constraints  |
|---------------|----------------|--------------|
| baggageID     | INT            | PRIMARY KEY  |
| bookingID     | INT            | FOREIGN KEY (References Bookings.bookingID) |
| weight        | DECIMAL(5, 2)  |              |
| type          | VARCHAR2(50)   |              |
| fee           | DECIMAL(10, 2) |              |

---

## Inserted Data

### Airplanes

| airplaneID | model       | capacity | airlineName        |
|------------|-------------|----------|--------------------|
| 452        | Boeing-767  | 200      | Southwest Airlines |
| 437        | Boeing-747  | 450      | Spirit             |
| 455        | Boeing-787  | 300      | American Airlines  |

---

### Airlines

| airlineID | airlineName        | location |
|-----------|--------------------|----------|
| 8250      | American Airlines   | Chicago  |
| 4599      | Southwest Airlines  | Dallas   |
| 2673      | Spirit Airlines     | Florida  |

---

### Airports

| airportID | airportName               | location |
|-----------|---------------------------|----------|
| 62989     | O'Hare                    | Chicago  |
| 24760     | Dallas Fort Worth Intl     | Dallas   |
| 59023     | Orlando International      | Florida  |

---

### Flights

| flightID | airlineID | departureAirportID | arrivalAirportID | flightDescription      | price  |
|----------|-----------|--------------------|------------------|------------------------|--------|
| 05       | 8250      | 62989              | 24760            | Chicago To Dallas       | 250.00 |
| 06       | 4599      | 24760              | 59023            | Dallas To Florida       | 450.00 |
| 07       | 2673      | 59023              | 62989            | Florida To Chicago      | 300.00 |

---

### Flight Schedules

| scheduleID | flightID | airplaneID | departureTime          | arrivalTime            | duration |
|------------|----------|------------|------------------------|------------------------|----------|
| 111        | 05       | 455        | 2024-08-22 03:30 PM    | 2024-08-22 05:30 PM    | 2.00     |
| 222        | 06       | 452        | 2024-08-25 07:30 PM    | 2024-08-25 09:00 PM    | 1.50     |
| 333        | 07       | 437        | 2024-09-02 09:30 AM    | 2024-09-02 11:30 AM    | 2.00     |

---

## Commit Statements
- All data has been committed using `COMMIT;` statements after each set of inserts.

## Triggers

### 1. Trigger to Update Airline Capacity

This trigger automatically updates the remaining capacity of an airline after a booking is made.

```sql
CREATE OR REPLACE TRIGGER update_airline_capacity
AFTER INSERT ON Bookings
FOR EACH ROW
BEGIN
  UPDATE Airplanes
  SET capacity = capacity - 1
  WHERE airplaneID = (SELECT airplaneID FROM FlightSchedules WHERE flightID = :NEW.flightID);
END;
/

### 2. Trigger to Update Payments

```sql
CREATE OR REPLACE TRIGGER updatePayments 
AFTER INSERT ON Bookings
FOR EACH ROW
DECLARE 
  randomNum NUMBER;
  flightsPrice NUMBER;
BEGIN
  SELECT dbms_random.value(100, 999) INTO randomNum FROM dual;
  SELECT price INTO flightsPrice FROM Flights WHERE flightID = :NEW.flightID;

  INSERT INTO Payments (paymentID, bookingID, amount, paymentDate, paymentMethod, status)
  VALUES (randomNum, :NEW.bookingID, flightsPrice, :NEW.bookingDate, 'N/A', 'Pending');
END;
/