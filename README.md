# AirlinesDatabase
 Simple emulation of an airlines database using java on the front-end and oracle at the back-end



## Dropping the tables

drop table baggage;
drop table payments;
drop table passengers;
drop table bookings;
drop table FlightSchedules;
drop table flights;
drop table airports;
drop table airlines;
drop table airplanes;
drop table users;

#Creating the tables

CREATE TABLE Users (
    userID INT PRIMARY KEY,
    firstName VARCHAR2(50),
    lastName VARCHAR2(50),
    username VARCHAR2(50),
    password VARCHAR2(255),
    email VARCHAR2(100),
    phoneNumber VARCHAR2(20)
);

CREATE TABLE Airlines (
    airlineID INT PRIMARY KEY,
    airlineName VARCHAR2(100),
    location VARCHAR2(100)
);

CREATE TABLE Flights (
    flightID INT PRIMARY KEY,
    airlineID INT,
    departureAirportID INT,
    arrivalAirportID INT,
    flightDescription VARCHAR2(255),
    price DECIMAL(10, 2),
    FOREIGN KEY (airlineID) REFERENCES Airlines(airlineID),
    FOREIGN KEY (departureAirportID) REFERENCES Airports(airportID),
    FOREIGN KEY (arrivalAirportID) REFERENCES Airports(airportID)
);

CREATE TABLE Bookings (
    bookingID INT PRIMARY KEY,
    userID INT,
    flightID INT,
    bookingDate DATE,
    status VARCHAR2(50),
    passportNumber varchar2(50),
    FOREIGN KEY (userID) REFERENCES Users(userID),
    FOREIGN KEY (flightID) REFERENCES Flights(flightID)
);

CREATE TABLE Passengers (
    passengerID INT PRIMARY KEY,
    bookingID INT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    passportNumber VARCHAR(20),
    FOREIGN KEY (bookingID) REFERENCES Bookings(bookingID)
);

CREATE TABLE Airplanes (
    airplaneID INT PRIMARY KEY,
    model VARCHAR2(100),
    capacity INT,
    airlineName VARCHAR(100)
);

CREATE TABLE FlightSchedules (  
    scheduleID INT PRIMARY KEY,
    flightID INT,
    airplaneID INT,
    departureTime varchar2(30),
    arrivalTime varchar2(30),
    duration number,
    FOREIGN KEY (flightID) REFERENCES Flights(flightID),
    FOREIGN KEY (airplaneID) REFERENCES Airplanes(airplaneID)
);

CREATE TABLE Airports (
    airportID INT PRIMARY KEY,
    airportName VARCHAR2(100),
    location VARCHAR2(100)
);

CREATE TABLE Payments (
    paymentID INT PRIMARY KEY,
    bookingID INT,
    amount DECIMAL(10, 2),
    paymentDate DATE,
    paymentMethod VARCHAR2(50),
    status VARCHAR2(50),
    FOREIGN KEY (bookingID) REFERENCES Bookings(bookingID)
);

CREATE TABLE Baggage (
    baggageID INT PRIMARY KEY,
    bookingID INT,
    weight DECIMAL(5, 2),
    type VARCHAR2(50),
    fee DECIMAL(10, 2),
    FOREIGN KEY (bookingID) REFERENCES Bookings(bookingID)
);

insert into airplanes values (452, 'Boeing-767', 200, 'Southwest Airlines');
insert into airplanes values (437, 'Boeing-747', 450, 'Spirit');
insert into airplanes values (455, 'Boeing-787', 300, 'American Airlines');

insert into airlines values (8250, 'American Airlines', 'Chicago');
insert into airlines values (4599, 'Southwest Airlines', 'Dallas');
insert into airlines values (2673, 'Spirit Airlines', 'Florida');

insert into airports values (62989, 'O''Hare', 'Chicago');
insert into airports values (24760, 'Dallas Fort Worth International', 'Dallas');
insert into airports values (59023, 'Orlando International', 'Florida');
commit;



insert into flights values(05, 8250, 62989, 24760, 'Chicago To Dallas', 250);
insert into flights values(06, 4599, 24760, 59023, 'Dallas To Florida', 450);
insert into flights values (07, 2673,59023,62989, 'Florida To Chicago', 300);
commit;



insert into flightSchedules values( 111, 05, 455, '2024-08-22 03:30 PM', '2024-08-22 05:30 PM', 2);
insert into flightschedules values( 222, 06, 452, '2024-08-25 07:30 PM','2024-08-25 9:00 PM', 1.50);
insert into flightschedules values (333, 07, 437, '2024-09-02 9:30 AM', '2024-09-02 011:30 AM', 2);
commit;




create or replace trigger addPassenger 
after insert on bookings
for each row
declare 
randomNum number;
firstNamey varchar2(30);
lastNamey varchar2(30);
begin
select dbms_random.value(100, 999) into randomNum from dual;
select firstName, lastName into firstNamey, lastNamey from users where userid = :new.userid;
insert into passengers (passengerid, bookingid, firstname, lastname, passportnumber) values (randomNum, :new.bookingid, firstNamey, lastNamey, :new.passportnumber);
end;
/



create or replace trigger updatePayments 
after insert on bookings
for each row
declare 
randomNum number;
flightsPrice number;
--lastNamey varchar2(30);
begin
select dbms_random.value(100, 999) into randomNum from dual;
select price into flightsPrice from flights where flightid = :new.flightid;
insert into payments (paymentid, bookingid, amount, paymentdate, paymentmethod, status) values (randomNum, :new.bookingid, flightsprice, :new.bookingdate, 'N/A', 'Pending');
end;
/