/* This is to grant permissions to user*/
DROP user 'oop' @'%';
CREATE USER 'oop' @'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'oop' @'%';
FLUSH privileges;
/* This is to initialize database */
DROP DATABASE IF EXISTS AIRLINE;
CREATE DATABASE AIRLINE;
USE AIRLINE;
-- Aircraft Table
DROP TABLE IF EXISTS Aircraft;
CREATE TABLE Aircraft (
    AircraftID INT PRIMARY KEY,
    Company VARCHAR(255),
    Model VARCHAR(255),
    SeatCapacity INT,
    Type VARCHAR(255)
);
-- Location Table
DROP TABLE IF EXISTS Location;
CREATE TABLE Location (
    LocationID INT PRIMARY KEY,
    Country VARCHAR(255),
    Province VARCHAR(255),
    City VARCHAR(255)
);
-- Flight Table
DROP TABLE IF EXISTS Flight;
CREATE TABLE Flight (
    FlightID INT PRIMARY KEY,
    AircraftID INT,
    DepartureLocationID INT,
    ArrivalLocationID INT,
    DepartureDate DATETIME,
    ArrivalDate DATETIME,
    SeatMap VARCHAR(4096),
    FOREIGN KEY (AircraftID) REFERENCES Aircraft(AircraftID),
    FOREIGN KEY (DepartureLocationID) REFERENCES Location(LocationID),
    FOREIGN KEY (ArrivalLocationID) REFERENCES Location(LocationID)
);
-- FlightSchedule Table (Assuming it's a junction table for many-to-many relationship)
-- DROP TABLE IF EXISTS FlightSchedule;
-- CREATE TABLE FlightSchedule (
--     FlightID INT,
--     FlightScheduleID INT,
--     PRIMARY KEY (FlightID, FlightScheduleID),
--     FOREIGN KEY (FlightID) REFERENCES Flight(FlightID)
-- );
-- CrewMember Table
DROP TABLE IF EXISTS CrewMember;
CREATE TABLE CrewMember (
    CrewMemberID INT PRIMARY KEY,
    Name VARCHAR(255),
    Role VARCHAR(255),
    Username VARCHAR(255),
    Password VARCHAR(255)
);
-- CrewSchedule Table
DROP TABLE IF EXISTS CrewSchedule;
CREATE TABLE CrewSchedule (
    FlightID INT,
    CrewMemberID INT,
    PRIMARY KEY (FlightID, CrewMemberID),
    FOREIGN KEY (FlightID) REFERENCES Flight(FlightID),
    FOREIGN KEY (CrewMemberID) REFERENCES CrewMember(CrewMemberID)
);
-- Name Table
DROP TABLE IF EXISTS Name;
CREATE TABLE Name (
    NameID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    MiddleName VARCHAR(255)
);
-- User Table
DROP TABLE IF EXISTS User;
CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    -- CreditID INT,
    NameID INT,
    Username VARCHAR(255),
    Password VARCHAR(255),
    Email VARCHAR(255),
    FOREIGN KEY (NameID) REFERENCES Name(NameID)
);
-- CreditCard Table
DROP TABLE IF EXISTS CreditCard;
CREATE TABLE CreditCard (
    CreditCardID INT PRIMARY KEY,
    UserID INT,
    CardNumber VARCHAR(255),
    CvvNumber INT,
    ExpiryDate DATE,
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);
-- Admin Table
DROP TABLE IF EXISTS Admin;
CREATE TABLE Admin (
    AdminID INT PRIMARY KEY,
    Username VARCHAR(255),
    Password VARCHAR(255)
);
-- Passenger Table
DROP TABLE IF EXISTS Passenger;
CREATE TABLE Passenger (
    PassengerID INT AUTO_INCREMENT PRIMARY KEY,
    NameID INT,
    PhoneID INT,
    AddressID INT,
    PassportID INT,
    UserID INT,
    Email VARCHAR(255),
    FOREIGN KEY (NameID) REFERENCES Name(NameID),
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);
-- Phone Table
DROP TABLE IF EXISTS Phone;
CREATE TABLE Phone (
    PhoneID INT AUTO_INCREMENT PRIMARY KEY,
    CountryCode VARCHAR(255),
    DistrictCode VARCHAR(255),
    Number VARCHAR(255)
);
-- Address Table
DROP TABLE IF EXISTS Address;
CREATE TABLE Address (
    AddressID INT AUTO_INCREMENT PRIMARY KEY,
    StreetName VARCHAR(255),
    District VARCHAR(255),
    PostalCode VARCHAR(255)
);
-- Passport Table
DROP TABLE IF EXISTS Passport;
CREATE TABLE Passport (
    PassportID INT AUTO_INCREMENT PRIMARY KEY,
    IssueCountry VARCHAR(255),
    IssueDate DATE,
    ExpiryDate DATE
);
-- Ticket Table
DROP TABLE IF EXISTS Ticket;
CREATE TABLE Ticket (
    TicketID INT PRIMARY KEY,
    FlightID INT,
    PassengerID INT,
    InsuranceID INT,
    SeatTypeID INT,
    SeatRow INT,
    SeatColumn CHAR,
    FOREIGN KEY (FlightID) REFERENCES Flight(FlightID),
    FOREIGN KEY (PassengerID) REFERENCES Passenger(PassengerID)
);
-- Insurance Table
DROP TABLE IF EXISTS Insurance;
CREATE TABLE Insurance (
    InsuranceID INT PRIMARY KEY,
    Policy VARCHAR(255)
);
-- SeatType Table
DROP TABLE IF EXISTS SeatType;
CREATE TABLE SeatType (
    SeatTypeID INT PRIMARY KEY,
    Type VARCHAR(255),
    Price DECIMAL(10, 2)
);
-- -- Adding initializing data to AUTO_INCREMENT tables
-- INSERT INTO User (UserID)
-- VALUES (1);
-- INSERT INTO Name (NameID)
-- VALUES (1);
-- INSERT INTO Phone (PhoneID)
-- VALUES (1);
-- INSERT INTO Address (AddressID)
-- VALUES (1);
-- INSERT INTO Passport (PassportID)
-- VALUES (1);