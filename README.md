# ENSF 480 Term Project - Flight Reservation System
## Group 19

### Members
- Chun Lok Chan - 30129645
- Jordan Torske - 30142873
- Mohamad Hussein - 30145507
- Logan Nightingale - 30141334

## Description
The Flight Reservation System is a web application designed to simplify and enhance the process of reserving flights for various stakeholders including tourism agents, airline passengers, airline agents, flight attendants, and system administrators. This system allows for efficient management of flight information, seat selection, payments, cancellations, and flight operations.

## Features
### For Users
- **Browse Available Flights:** View all available flights to your desired destination.
- **Select Desired Flight:** Choose a flight based on your requirements.
- **Browse Seat Map:** Visual representation of seat availability on the aircraft.
- **Select Seat:** Choose your preferred seat.
- **Select Insurance:** Opt for flight insurance.
- **Make Payment:** Complete your booking through a secure payment gateway.
- **Receive Ticket and Receipt:** Get your ticket and payment receipt via email.
- **Cancel Flight:** Option to cancel your booking.

### For Airline Agents and Flight Attendants
- **Browse List of Passengers:** Access the passenger list for flight management.
- **All functions in User Module:** Full access to all user functionalities for service purposes.

### For System Admins
- **Manage Flight Information:** Add, update or remove flight details.
- **Manage Crew Information:** Assign crew members to flights.
- **Manage Aircraft Information:** Update and maintain aircraft details.
- **Manage Flight Destinations:** Add or remove destinations.
- **Manage Users:** Oversee user accounts and permissions.

## Technical Overview
### Client-Side
- Web application facilitates interaction with the server and user.
- Supports email notification for tickets.
- Modular design for maintainability and scalability.
- Polymorphism allows for unique functionalities for different user roles.

### Server-Side
- Utilizes MySQL for database management.
- Stores information on flights, passengers, tickets, and more.
- Easy access and management of data through queries.

### Authentication and Validation
- Ensures secure login and data entry through comprehensive validation and authentication mechanisms.

## Development and Deployment
### Modular Design
- The application is structured into distinct modules for specific functionalities, promoting efficient maintenance and scalability.
### Authentication and Validation
- Implements secure login procedures and validates user inputs to maintain data integrity and security.
