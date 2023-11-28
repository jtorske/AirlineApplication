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
import Domains.Seats.*;
import java.util.ArrayList;
import Domains.Tickets.*;
import Domains.Passenger.*;


public class User {
    static Flights flight=null;
    static Seat seat=null;
    static public ArrayList<Flights> BrowseFlights(TimeDate departure, Location origin, Location destination){
        //connect to database and return the list of flights
        ArrayList<Flights> flights = new ArrayList<Flights>();
        return flights;
    }
    static public void SelectFlight(Flights f){flight = f;}
    static public String BrowseSeat(Flights flight){return flight.getSeatMapString();}
    static public void SelectSeat(Seat s){seat = s;}

    static public void BuyTicket(String firstName, String middleName, String lastName, String passportNumber, 
        String country, int expiryYear, int expiryMonth, int expiryDay, 
        int issueYear, int issueMonth, int issueDay, String streetNumber, String streetName, 
        String city, String province, String countryAddress, String postalCode, String email, 
        int countryCode, int areaCode, int phoneNumber, int cardNumber, Insurance policy){
            double price = seat.GetPrice();
            //connect to the billingport to pay

            Passenger p = new Passenger(firstName, middleName, lastName, passportNumber,
                country, expiryYear, expiryMonth, expiryDay, issueYear, issueMonth, issueDay,
                streetNumber, streetName, city, province, countryAddress, postalCode, email,
                countryCode, areaCode, phoneNumber);
            Ticket t = new Ticket(flight, seat, p, policy, cardNumber);
            //connect to the database to store the ticket

            t.getReceipt().Email();
            seat.booked();
            flight.addPassenger(p);
            //update the flight in the database
    }
    static public void CancelTicket(int ticketNum){
        //connect to the database to get the ticket
        // browse ticket
        String flighNum=""; //get the flight number from the ticket
        String seatNum=""; //get the seat number from the ticket
        //connect to the database to get the flight
        String seatMap=""; //get the seat map from the flight
        //find the location of seatNum in the string seatMap and changed the "X" that is in front of seatNum to "O"
        String[] seatRow=seatMap.split("\n");
        for (int i = 0; i < seatRow.length; i++){
            String[] seatCol=seatRow[i].split("|");
            for (int j = 0; j < seatCol.length; j++){
                if (seatCol[j].contains(seatNum)){
                    seatCol[j] = seatCol[j].replace("X", "O");
                    break;
                }
            }
            seatRow[i] = String.join("|", seatCol);
        }
        seatMap = String.join("\n", seatRow);
        //connect to the database to update the seat map
        //connect to the database to delete the ticket

    }

    static public ArrayList<Passenger> BrowsePassengers(String flightNum){ 
        return null;
    }
}
