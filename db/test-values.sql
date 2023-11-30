-- Adding sample data to Aircraft Table
INSERT INTO Aircraft (AircraftID, Company, Model, SeatCapacity, Type)
VALUES (1, 'Boeing', '737', 189, 1),
       (2, 'Boeing', '777', 388, 2);

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
        (10, 'Canada', 'Manitoba', 'Winnipeg');

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
        (10, 2, 10, 9, '2023-12-10 07:30:00', '2023-12-10 09:30:00');

-- Adding sample data to CrewMember Table
INSERT INTO CrewMember (CrewMemberID, Name, Role, Username, Password)
VALUES (1, 'John A Smith', 'Pilot', 'john.smith', 'pass123'),
        (2, 'Jane B White', 'Flight Attendant', 'jane.white', 'secure123'),
        (3, 'Emily C Davis', 'Flight Attendant', 'emily.davis', 'securepass'),
        (4, 'Robert D Brown', 'Pilot', 'robert.brown', 'password123');

-- Adding sample data to CrewSchedule Table
INSERT INTO CrewSchedule (FlightID, CrewMemberID)
VALUES   (1, 1), (1, 2),  -- Flight 1
        (2, 4), (2, 3),  -- Flight 2
        (3, 1), (3, 2),  -- Flight 3
        (4, 4), (4, 3),  -- Flight 4
        (5, 1), (5, 2),  -- Flight 5
        (6, 4), (6, 3),  -- Flight 6
        (7, 1), (7, 2),  -- Flight 7
        (8, 4), (8, 3),  -- Flight 8
        (9, 1), (9, 2),  -- Flight 9
        (10, 4), (10, 3); -- Flight 10

-- Adding sample data to Name Table
INSERT INTO Name (NameID, FirstName, LastName, MiddleName)
VALUES (2, 'Jane', 'Smith', 'B'),
        (3, 'Emma', 'Jones', 'C'),
        (4, 'Sam', 'Roberts', 'D'),
        (5, 'Lily', 'Williams', 'E'),
        (6, 'Lily', 'Will', 'F');

-- Adding sample data to User Table
INSERT INTO User (UserID, NameID, Username, Password)
VALUES  (2, 2, 'jane_smith', 'securepass'),
        (3, 3, 'emma_jones', 'pass123'),
        (4, 4, 'sam_roberts', 'strongpass'),
        (5, 5, 'lily_williams', 'secure123'),
        (6, 6, 'jane_smith2', 'securepass');

-- Adding sample data to CreditCard Table
INSERT INTO CreditCard (CreditCardID, UserID,CardNumber, CvvNumber, ExpiryDate)
VALUES (1, 1, '1234-5678-9101-1121', 123, '2025-12-31'),
        (2, 2, '5678-9101-1121-3141', 456, '2024-06-30'),
        (3, 3, '9876-5432-1098-7654', 789, '2023-11-30'),
        (4, 4, '4321-8765-2109-8765', 321, '2024-09-30'),
        (5, 5, '8765-4321-0987-6543', 654, '2025-05-31');

-- Adding sample data to Admin Table
INSERT INTO Admin (AdminID, Username, Password)
VALUES (1, 'admin1', 'adminpass'),
       (2, 'admin2', 'adminpass2');

-- Adding sample data to Phone Table
INSERT INTO Phone (PhoneID, CountryCode, DistrictCode, Number)
VALUES (6, '+1', '123', '456-7890'),
        (2, '+1', '456', '789-0123'),
        (3, '+1', '789', '012-3456'),
        (4, '+1', '012', '345-6789'),
        (5, '+1', '234', '567-8901');

-- Adding sample data to Address Table
INSERT INTO Address (AddressID, StreetName, District, PostalCode)
VALUES (6, '123 Main St', 'Downtown', '12345'),
        (2, '456 Oak St', 'Uptown', '56789'),
        (3, '789 Pine St', 'Midtown', '67890'),
        (4, '210 Maple St', 'Westside', '78901'),
        (5, '345 Elm St', 'East End', '01234');

-- Adding sample data to Passport Table
INSERT INTO Passport (PassportID, IssueCountry, IssueDate, ExpiryDate)
VALUES (6, 'USA', '2020-01-01', '2030-01-01'),
        (2, 'Canada', '2019-05-15', '2029-05-15'),
        (3, 'USA', '2022-03-01', '2032-03-01'),
        (4, 'Canada', '2021-08-10', '2031-08-10'),
        (5, 'USA', '2023-06-15', '2033-06-15');

-- Adding sample data to Passenger Table
INSERT INTO Passenger (PassengerID, NameID, PhoneID, AddressID, PassportID, UserID, Email)
VALUES (1, 1, 1, 1, 1, 1, 'james.white@email.com'),
        (2, 2, 2, 2, 2, 2, 'jane.smith@email.com'),
        (3, 3, 3, 3, 3, 3, 'emma.jones@email.com'),
        (4, 4, 4, 4, 4, 4, 'sam.roberts@email.com'),
        (5, 5, 5, 5, 5, 5, 'lily.williams@email.com');

-- Adding sample data to Ticket Table
INSERT INTO Ticket (TicketID, FlightID, PassengerID, InsuranceID, SeatTypeID, SeatRow, SeatColumn)
VALUES (1, 1, 1, 1, 1, 10, 'A'),
        (2, 2, 2, 2, 3, 5, 'C'),
        (3, 3, 3, 3, 2, 8, 'B'),
        (4, 4, 4, 4, 1, 15, 'D'),
        (5, 5, 5, 5, 3, 3, 'A');

-- Adding sample data to Insurance Table
INSERT INTO Insurance (InsuranceID, Policy)
VALUES (1, 'Basic Coverage'),
       (2, 'Premium Coverage'),
       (3, 'Basic Coverage'),
       (4, NULL),
       (5, 'Basic Coverage');

-- Adding sample data to SeatType Table
INSERT INTO SeatType (SeatTypeID, Type, Price)
VALUES   (1, 'Ordinary', 50.00), -- Ordinary
        (2, 'Comfort', 70.00), -- Comfort
        (3, 'Business', 100.00); -- Business
