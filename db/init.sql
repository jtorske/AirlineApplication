/* This is to initialize database */

-- Aircraft Table
CREATE TABLE Aircraft (
    AircraftID INT PRIMARY KEY,
    Company VARCHAR(255),
    Model VARCHAR(255),
    SeatCapacity INT,
    Type VARCHAR(255)
);
-- Location Table
CREATE TABLE Location (
    LocationID INT PRIMARY KEY,
    Country VARCHAR(255),
    Province VARCHAR(255),
    City VARCHAR(255)
);
-- Flight Table
CREATE TABLE Flight (
    FlightID INT PRIMARY KEY,
    AircraftID INT,
    DepartureLocationID INT,
    ArrivalLocationID INT,
    DepartureDate DATETIME,
    ArrivalDate DATETIME,
    FOREIGN KEY (AircraftID) REFERENCES Aircraft(AircraftID),
    FOREIGN KEY (DepartureLocationID) REFERENCES Location(LocationID),
    FOREIGN KEY (ArrivalLocationID) REFERENCES Location(LocationID)
);
-- FlightSchedule Table (Assuming it's a junction table for many-to-many relationship)
CREATE TABLE FlightSchedule (
    FlightID INT,
    FlightScheduleID INT,
    PRIMARY KEY (FlightID, FlightScheduleID),
    FOREIGN KEY (FlightID) REFERENCES Flight(FlightID)
);
-- CrewMember Table
CREATE TABLE CrewMember (
    CrewMemberID INT PRIMARY KEY,
    Name VARCHAR(255),
    Role VARCHAR(255)
);
-- CrewSchedule Table
CREATE TABLE CrewSchedule (
    FlightID INT,
    CrewMemberID INT,
    PRIMARY KEY (FlightID, CrewMemberID),
    FOREIGN KEY (FlightID) REFERENCES Flight(FlightID),
    FOREIGN KEY (CrewMemberID) REFERENCES CrewMember(CrewMemberID)
);
-- User Table
CREATE TABLE User (
    UserID INT PRIMARY KEY,
    CreditID INT,
    Username VARCHAR(255),
    Password VARCHAR(255)
);
-- CreditCard Table
CREATE TABLE CreditCard (
    CreditCardID INT PRIMARY KEY,
    CardNumber VARCHAR(255),
    CvvNumber INT,
    ExpiryDate DATE
);
-- Admin Table
CREATE TABLE Admin (
    AdminID INT PRIMARY KEY,
    Username VARCHAR(255),
    Password VARCHAR(255)
);
-- Name Table
CREATE TABLE Name (
    NameID INT PRIMARY KEY,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    MiddleName VARCHAR(255)
);
-- Passenger Table
CREATE TABLE Passenger (
    PassengerID INT PRIMARY KEY,
    NameID INT,
    PhoneID INT,
    AddressID INT,
    PassportID INT,
    Email VARCHAR(255),
    FOREIGN KEY (NameID) REFERENCES Name(NameID)
);
-- Phone Table
CREATE TABLE Phone (
    PhoneID INT PRIMARY KEY,
    CountryCode VARCHAR(255),
    DistrictCode VARCHAR(255),
    Number VARCHAR(255)
);
-- Address Table
CREATE TABLE Address (
    AddressID INT PRIMARY KEY,
    StreetName VARCHAR(255),
    District VARCHAR(255),
    PostalCode VARCHAR(255)
);
-- Passport Table
CREATE TABLE Passport (
    PassportID INT PRIMARY KEY,
    IssueCountry VARCHAR(255),
    IssueDate DATE,
    ExpiryDate DATE
);
-- Ticket Table
CREATE TABLE Ticket (
    TicketID INT PRIMARY KEY,
    FlightID INT,
    PassengerID INT,
    InsuranceID INT,
    SeatType VARCHAR(255),
    SeatRow INT,
    SeatColumn INT,
    FOREIGN KEY (FlightID) REFERENCES Flight(FlightID),
    FOREIGN KEY (PassengerID) REFERENCES Passenger(PassengerID)
);
-- Insurance Table
CREATE TABLE Insurance (
    InsuranceID INT PRIMARY KEY,
    Policy VARCHAR(255)
);
-- SeatType Table
CREATE TABLE SeatType (
    SeatTypeID INT PRIMARY KEY,
    Price DECIMAL(10, 2)
);