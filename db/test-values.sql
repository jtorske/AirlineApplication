-- Adding sample data to Aircraft Table
INSERT INTO Aircraft (AircraftID, Company, Model, SeatCapacity, Type)
VALUES (1, 'Boeing', '747', 400, 'Wide-body'),
       (2, 'Airbus', 'A320', 150, 'Narrow-body');

-- Adding sample data to Location Table
INSERT INTO Location (LocationID, Country, Province, City)
VALUES (1, 'USA', 'California', 'Los Angeles'),
       (2, 'Canada', 'Ontario', 'Toronto');

-- Adding sample data to Flight Table
INSERT INTO Flight (FlightID, AircraftID, DepartureLocationID, ArrivalLocationID, DepartureDate, ArrivalDate)
VALUES (1, 1, 1, 2, '2023-12-01 08:00:00', '2023-12-01 10:00:00'),
       (2, 2, 2, 1, '2023-12-02 12:00:00', '2023-12-02 14:00:00');

-- Adding sample data to CrewMember Table
INSERT INTO CrewMember (CrewMemberID, Name, Role)
VALUES (1, 'John Smith', 'Pilot'),
       (2, 'Jane Doe', 'Flight Attendant');

-- Adding sample data to CrewSchedule Table
INSERT INTO CrewSchedule (FlightID, CrewMemberID)
VALUES (1, 1),
       (1, 2),
       (2, 1);

-- Adding sample data to User Table
INSERT INTO User (UserID, CreditID, Username, Password)
VALUES (1, 1, 'john_doe', 'password123'),
       (2, 2, 'jane_smith', 'securepass');

-- Adding sample data to CreditCard Table
INSERT INTO CreditCard (CreditCardID, CardNumber, CvvNumber, ExpiryDate)
VALUES (1, '1234-5678-9101-1121', 123, '2025-12-31'),
       (2, '5678-9101-1121-3141', 456, '2024-06-30');

-- Adding sample data to Admin Table
INSERT INTO Admin (AdminID, Username, Password)
VALUES (1, 'admin1', 'adminpass'),
       (2, 'admin2', 'adminpass2');

-- Adding sample data to Name Table
INSERT INTO Name (NameID, FirstName, LastName, MiddleName)
VALUES (1, 'John', 'Doe', 'A'),
       (2, 'Jane', 'Smith', 'B');

-- Adding sample data to Phone Table
INSERT INTO Phone (PhoneID, CountryCode, DistrictCode, Number)
VALUES (1, '+1', '123', '456-7890'),
       (2, '+1', '456', '789-0123');

-- Adding sample data to Address Table
INSERT INTO Address (AddressID, StreetName, District, PostalCode)
VALUES (1, '123 Main St', 'Downtown', '12345'),
       (2, '456 Oak St', 'Uptown', '56789');

-- Adding sample data to Passport Table
INSERT INTO Passport (PassportID, IssueCountry, IssueDate, ExpiryDate)
VALUES (1, 'USA', '2020-01-01', '2030-01-01'),
       (2, 'Canada', '2019-05-15', '2029-05-15');

-- Adding sample data to Passenger Table
INSERT INTO Passenger (PassengerID, NameID, PhoneID, AddressID, PassportID, Email)
VALUES (1, 1, 1, 1, 1, 'john.doe@email.com'),
       (2, 2, 2, 2, 2, 'jane.smith@email.com');

-- Adding sample data to Ticket Table
INSERT INTO Ticket (TicketID, FlightID, PassengerID, InsuranceID, SeatType, SeatRow, SeatColumn)
VALUES (1, 1, 1, 1, 'Economy', 10, 'A'),
       (2, 2, 2, 2, 'Business', 5, 'C');

-- Adding sample data to Insurance Table
INSERT INTO Insurance (InsuranceID, Policy)
VALUES (1, 'Basic Coverage'),
       (2, 'Premium Coverage');

-- Adding sample data to SeatType Table
INSERT INTO SeatType (SeatTypeID, Price)
VALUES (1, 100.00),
       (2, 200.00);
