/*
@author 
Chun Lok Chan
Jordan Torske
Mohamad Hussein
Logan Nightingale
@version: 1.0
@since: 2023-11-23
 */

package Domains.User;

import Domains.Flights.*;
import Front.GUI.GUI;
import Domains.Seats.*;
import Database.Database.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.mail.MessagingException;

import Domains.Tickets.*;
import Domains.Passenger.*;

public class User {
    static Flights flight = null;
    static Seat seat = null;

    /*
     * Returns a list of all flights in the database
     */
    static public ArrayList<Flights> BrowseFlights(TimeDate departure, Location origin, Location destination) {
        // connect to database and return the list of flights
        ArrayList<Flights> flights = new ArrayList<Flights>();

        String query = String.format(
                "SELECT f.FlightID, l.Country, l.Province, l.City, " +
                    "l2.Country as Country2, l2.Province as Province2, l2.City as City2, " +
                    "f.DepartureDate, f.ArrivalDate, a.AircraftID, a.Company, a.Model, a.SeatCapacity, a.Type, f.SeatMap " +
                "FROM flight AS f " +
                "JOIN Location AS l ON f.DepartureLocationID = l.LocationID " +
                "LEFT JOIN Location AS l2 ON f.ArrivalLocationID = l2.LocationID " +
                "LEFT JOIN Aircraft AS a ON f.AircraftID = a.AircraftID");

        List<List<String>> dbResult = Database.dbExecute(query);

        // Making objects from the database result
        for (List<String> row : dbResult) {
            String flightID = row.get(0);
            Location originLocation = new Location(row.get(1), row.get(2), row.get(3));
            Location destinationLocation = new Location(row.get(4), row.get(5), row.get(6));

            // Date objs
            TimeDate departureDate = new TimeDate(row.get(7));
            TimeDate arrivalDate = new TimeDate(row.get(8));

            // Aircraft
            Aircraft aircraft = new Aircraft(row.get(9), row.get(10), row.get(11), Integer.parseInt(row.get(12)),
                    Integer.parseInt(row.get(13)));
            Flights flight = new Flights(flightID, departureDate, arrivalDate, originLocation, destinationLocation,
                    aircraft);
            ArrayList<Seat> seats = new ArrayList<Seat>();
            // get the seat map from the database
            String seatMap = row.get(14);
            if (seatMap != null) {
                String[] seatRow = seatMap.split("\n");
                for (int i = 0; i < seatRow.length; i++) {
                    String[] seatCol = seatRow[i].split("\\|");
                    for (int j = 1; j < seatCol.length; j++) {
                        if (seatCol[j].contains("X")) {
                            seats.add(new OrdinarySeat(i * seatCol.length + j + 1, i, (char) (j + 64), true));
                        } else {
                            seats.add(new OrdinarySeat(i * seatCol.length + j + 1, i, (char) (j + 64), false));
                        }
                    }
                }
                flight.setSeatList(seats);
            }
            flights.add(flight);
        }

        return flights;
    }

    static public void SelectFlight(Flights f) {
        flight = f;
    }

    static public Flights GetFlight() {
        return flight;
    }

    static public Seat GetSeat() {
        return seat;
    }

    static public String BrowseSeat(Flights flight) {
        return flight.getSeatMapString();
    }

    /*
     * Select a seat from the seat map, set the seat object to the selected seat
     */
    static public int SelectSeat(String s) {
        String map = flight.getSeatMapString();
        //slice the string to get the seat number
        String[] seatRow = map.split("\n");
        int SeatNum = 0;
        int SeatRow = 0;
        char SeatColumn = 0;
        boolean isBooked = false;
        boolean found = false;
        // find the location of seatNum in the string seatMap
        for (int i = 0; i < seatRow.length; i++) {
            String[] seatCol = seatRow[i].split("\\|");
            for (int j = 0; j < seatCol.length; j++) {
                SeatNum++;
                if (seatCol[j].contains(s)) {
                    //if seat is found and prepare the seat object
                    isBooked = seatCol[j].contains("X");
                    SeatRow = i;
                    SeatColumn = (char) (j + 64);
                    found = true;
                    break;
                }
            }
            if (found)
                break;
        }
        if (isBooked || found == false) // if seat is booked or not found
            return -1;
        // create the seat object according to the aircraft type and seat type
        if (flight.getAircraft().getType() == 1) {
            seat = new OrdinarySeat(SeatNum, SeatRow, SeatColumn, isBooked);
        } else if (flight.getAircraft().getType() == 2) {
            if (SeatRow <= 5) {
                seat = new BusinessSeat(SeatNum, SeatRow, SeatColumn, isBooked);
            } else if (SeatRow <= 9) {
                seat = new ComfortSeat(SeatNum, SeatRow, SeatColumn, isBooked);
            } else {
                seat = new OrdinarySeat(SeatNum, SeatRow, SeatColumn, isBooked);
            }
        }
        return 0;
    }
    /*
     * Buy a ticket for the selected seat
     */
    static public void BuyTicket(Passenger p, String cardNumber, Insurance policy)
            throws MessagingException {
        
        double price = seat.GetPrice();
        if (GUI.getUsername() != null) {
            price = price * 0.9; // discount for members
        }
        //go to database and get highest number of the ticketID
        int ticketID = 0;
        //dbexecute
        String query = String.format("SELECT MAX(TicketID) FROM Ticket");
        List<List<String>> dbResult = Database.dbExecute(query);
        for (List<String> row : dbResult) {
            ticketID = Integer.parseInt(row.get(0));
        }
        ticketID++;
        Ticket.setTicNum(ticketID);
        Ticket t = new Ticket(flight, seat, p, policy, cardNumber);
        // check if name in database
        // if not, add to database
        query = String.format("SELECT * FROM name WHERE FirstName = '%s' AND LastName = '%s'", p.getName().getFirstName(), p.getName().getLastName());
        dbResult = Database.dbExecute(query);
        String nameId="";
        if (dbResult.size() == 0) {
            List<String> passengerInfo = new ArrayList<String>();
            passengerInfo.add(p.getName().getFirstName());
            passengerInfo.add(p.getName().getLastName());
            passengerInfo.add(p.getName().getMiddleName());
            nameId=Database.dbInsert(" name (FirstName, LastName, MiddleName)", passengerInfo);
        } else {
            for (List<String> row : dbResult) {
                nameId = row.get(0);
            }
        }
        //do the same for phone number
        String phoneId="";
        query = String.format("SELECT * FROM phone WHERE Number = '%s' AND CountryCode = '%s' AND DistrictCode = '%s'", Integer.toString(p.getPhoneNumberObj().getPhoneNumber()), Integer.toString(p.getPhoneNumberObj().getCountryCode()), Integer.toString(p.getPhoneNumberObj().getAreaCode()));
        dbResult = Database.dbExecute(query);
        if (dbResult.size() == 0) {
            List<String> phoneInfo = new ArrayList<String>();
            phoneInfo.add(Integer.toString(p.getPhoneNumberObj().getPhoneNumber()));
            phoneInfo.add(Integer.toString(p.getPhoneNumberObj().getCountryCode()));
            phoneInfo.add(Integer.toString(p.getPhoneNumberObj().getAreaCode()));
            phoneId=Database.dbInsert("phone (CountryCode, DistrictCode, Number)", phoneInfo);
        } else {
            for (List<String> row : dbResult) {
                phoneId = row.get(0);
            }
        }
        //do the same for passport
        String passportId="";
        query = String.format("SELECT * FROM passport WHERE PassportID = '%s' AND IssueCountry = '%s'", p.getPassport().getPassportNumber(), p.getPassport().getCountry());
        dbResult = Database.dbExecute(query);
        if (dbResult.size() == 0) {
            List<String> passportInfo = new ArrayList<String>();
            passportInfo.add(p.getPassport().getPassportNumber());
            passportInfo.add(p.getPassport().getCountry());
            //convert date to "year-month-day hour:minute:second" format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            Date issueDate = p.getPassport().getIssueDate();
            Date expiryDate = p.getPassport().getExpiryDate();

            LocalDateTime issueDateTime = LocalDateTime.ofInstant(issueDate.toInstant(), ZoneId.systemDefault());
            LocalDateTime expiryDateTime = LocalDateTime.ofInstant(expiryDate.toInstant(), ZoneId.systemDefault());

            String formattedIssueDate = issueDateTime.format(formatter);
            String formattedExpiryDate = expiryDateTime.format(formatter);
            
            passportInfo.add(formattedIssueDate);
            passportInfo.add(formattedExpiryDate);
            passportId=Database.dbInsert("passport (PassportID, IssueCountry, IssueDate , ExpiryDate)", passportInfo);
        } else {
            for (List<String> row : dbResult) {
                passportId = row.get(0);
            }
        }
        //do the same for address
        String addressId="";
        query = String.format("SELECT * FROM address WHERE StreetName = '%s' AND PostalCode = '%s'", p.getAddress().getStreetName(), p.getAddress().getPostalCode());
        dbResult = Database.dbExecute(query);
        if (dbResult.size() == 0) {
            List<String> addressInfo = new ArrayList<String>();
            addressInfo.add(p.getAddress().getStreetName());
            addressInfo.add(p.getAddress().getProvDist());
            addressInfo.add(p.getAddress().getPostalCode());
            addressId=Database.dbInsert("address (StreetName, District, PostalCode)", addressInfo);
        } else {
            for (List<String> row : dbResult) {
                addressId = row.get(0);
            }
        }
        
        String passengerId="";
        query = String.format("SELECT * FROM passenger WHERE NameID = '%s' AND PhoneID = '%s' AND PassportID = '%s'", nameId, phoneId, passportId);
        dbResult = Database.dbExecute(query);
        if (dbResult.size() == 0) {
            List<String> passengerInfo = new ArrayList<String>();
            passengerInfo.add(nameId);
            passengerInfo.add(phoneId);
            passengerInfo.add(addressId);
            passengerInfo.add(passportId);
            passengerInfo.add("1");
            passengerInfo.add(p.getEmail());
            passengerId=Database.dbInsert("passenger (NameID, PhoneID, AddressID, PassportID, UserID, Email)", passengerInfo);
        } else {
            for (List<String> row : dbResult) {
                passengerId = row.get(0);
            }
        }
        int InsuranceID = Integer.parseInt(policy.getPolicy());
        int SeatType = 0;
        if (seat.GetPrice()==50) {SeatType = 1;}        
        else if (seat.GetPrice()==70) {SeatType = 2;}
        else {SeatType = 3;}
        int seatRows=seat.getRow();
        char seatColumn=seat.getColumn();

        List<String> ticketInfo = new ArrayList<String>();
        ticketInfo.add(Integer.toString(ticketID));
        ticketInfo.add(flight.getFlightNum());
        ticketInfo.add(passengerId);
        ticketInfo.add(Integer.toString(InsuranceID));
        ticketInfo.add(Integer.toString(SeatType));
        ticketInfo.add(Integer.toString(seatRows));
        ticketInfo.add(Character.toString(seatColumn));
        String tickStringID = Database.dbInsert("ticket (TicketID, FlightID, PassengerID, InsuranceID, SeatTypeID, SeatRow, SeatColumn)", ticketInfo);


        String mapString = flight.getSeatMapString();
        // find the location of seat.Display() in the string seatMap and changed the "O"
        // that is in front of seatNum to "X"
        String[] seatRow = mapString.split("\n");
        for (int i = 0; i < seatRow.length; i++) {
            String[] seatCol = seatRow[i].split("\\|");
            for (int j = 0; j < seatCol.length; j++) {
                if (seatCol[j].contains(" "+seat.Display())) {
                    seatCol[j] = seatCol[j].replace("O", "X");
                    break;
                }
            }
            seatRow[i] = String.join("|", seatCol);
        }
        // send email notification to user
        t.getReceipt().Email();
        t.Email();
        seat.booked();
        //joint the seatmap string back together
        mapString = String.join("\n", seatRow);
        // update the flight in the database
        query = String.format("UPDATE flight SET SeatMap = '%s' WHERE FlightID = '%s'", mapString, flight.getFlightNum());
        Database.dbUpdate(query);
    }
}