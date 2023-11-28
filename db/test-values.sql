-- Adding sample data to Aircraft Table
INSERT INTO Aircraft (AircraftID, Company, Model, SeatCapacity, Type)
VALUES (1, 'Boeing', '737', 800, 'Narrow-body'),
       (2, 'Boeing', '777', 300, 'Wide-body');

-- Adding sample data to Location Table
INSERT INTO Location (LocationID, Country, Province, City)
VALUES   (1, 'USA', 'California', 'Los Angeles'),
        (2, 'USA', 'New York', 'New York City'),
        (3, 'USA', 'Texas', 'Houston'),
        (4, 'USA', 'Florida', 'Miami'),
        (5, 'USA', 'Illinois', 'Chicago'),
        (6, 'Canada', 'Ontario', 'Toronto'),
        (7, 'Canada', 'Quebec', 'Montreal'),
        (8, 'Canada', 'British Columbia', 'Vancouver'),
        (9, 'Canada', 'Alberta', 'Calgary'),
        (10, 'Canada', 'Manitoba', 'Winnipeg'),
        (11, 'USA', 'Pennsylvania', 'Philadelphia'),
        (12, 'USA', 'Georgia', 'Atlanta'),
        (13, 'USA', 'Massachusetts', 'Boston'),
        (14, 'Canada', 'Nova Scotia', 'Halifax'),
        (15, 'USA', 'Washington', 'Seattle'),
        (16, 'USA', 'Michigan', 'Detroit'),
        (17, 'Canada', 'Saskatchewan', 'Regina'),
        (18, 'USA', 'Arizona', 'Phoenix'),
        (19, 'Canada', 'Newfoundland and Labrador', 'St. Johns'),
        (20, 'USA', 'North Carolina', 'Charlotte'),
        (21, 'Canada', 'New Brunswick', 'Fredericton'),
        (22, 'USA', 'Tennessee', 'Nashville'),
        (23, 'Canada', 'Prince Edward Island', 'Charlottetown'),
        (24, 'USA', 'Minnesota', 'Minneapolis'),
        (25, 'Canada', 'Quebec', 'Quebec City');

-- Adding sample data to Flight Table
INSERT INTO Flight (FlightID, AircraftID, DepartureLocationID, ArrivalLocationID, DepartureDate, ArrivalDate)
VALUES (1, 1, 1, 2, '2023-12-01 08:00:00', '2023-12-01 10:00:00'),
       (2, 2, 2, 1, '2023-12-02 12:00:00', '2023-12-02 14:00:00'),
        (3, 1, 3, 4, '2023-12-03 15:30:00', '2023-12-03 17:30:00'),
        (4, 2, 4, 3, '2023-12-04 18:45:00', '2023-12-04 20:45:00'),
        (5, 1, 5, 6, '2023-12-05 09:15:00', '2023-12-05 11:15:00'),
        (6, 2, 6, 5, '2023-12-06 13:20:00', '2023-12-06 15:20:00'),
        (7, 1, 7, 8, '2023-12-07 21:00:00', '2023-12-07 23:00:00'),
        (8, 2, 8, 7, '2023-12-08 10:30:00', '2023-12-08 12:30:00'),
        (9, 1, 9, 10, '2023-12-09 16:45:00', '2023-12-09 18:45:00'),
        (10, 2, 10, 9, '2023-12-10 07:30:00', '2023-12-10 09:30:00'),
        (11, 1, 11, 12, '2023-12-11 14:00:00', '2023-12-11 16:00:00'),
        (12, 2, 12, 11, '2023-12-12 19:20:00', '2023-12-12 21:20:00'),
        (13, 1, 13, 14, '2023-12-13 11:45:00', '2023-12-13 13:45:00'),
        (14, 2, 14, 13, '2023-12-14 22:10:00', '2023-12-15 00:10:00'),
        (15, 1, 15, 16, '2023-12-15 16:30:00', '2023-12-15 18:30:00'),
        (16, 2, 16, 15, '2023-12-16 03:45:00', '2023-12-16 05:45:00'),
        (17, 1, 17, 18, '2023-12-17 14:20:00', '2023-12-17 16:20:00'),
        (18, 2, 18, 17, '2023-12-18 09:00:00', '2023-12-18 11:00:00'),
        (19, 1, 19, 20, '2023-12-19 20:15:00', '2023-12-19 22:15:00'),
        (20, 2, 20, 19, '2023-12-20 12:30:00', '2023-12-20 14:30:00'),
        (21, 1, 21, 22, '2023-12-21 17:45:00', '2023-12-21 19:45:00'),
        (22, 2, 22, 21, '2023-12-22 08:00:00', '2023-12-22 10:00:00'),
        (23, 1, 23, 24, '2023-12-23 13:30:00', '2023-12-23 15:30:00'),
        (24, 2, 24, 23, '2023-12-24 18:00:00', '2023-12-24 20:00:00'),
        (25, 1, 25, 6, '2023-12-25 09:45:00', '2023-12-25 11:45:00');

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
