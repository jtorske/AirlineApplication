
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
