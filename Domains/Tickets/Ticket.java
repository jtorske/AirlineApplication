package Domains.Tickets;
import Domains.Seats.Seat;
import Domains.Flights.Flights;
import Domains.Passenger.Passenger;

public class Ticket {
    private static int ID = 0;
    private Flights flight;
    private Seat seat;
    private Passenger passenger;
    private Insurance policy;
    private final Receipt ticketReceipt;

    public Ticket(Flights flight, Seat seat, Passenger owner, int cardNumber){
        this.flight = flight;
        this.seat = seat;
        this.passenger = owner;
        this.policy = null;
        ID++;
        this.ticketReceipt = new Receipt(ID, owner.getName().toString(), cardNumber, this);
    }

    public Ticket(Flights flight, Seat seat, Passenger owner, Insurance policy, int cardNumber){
        this.flight = flight;
        this.seat = seat;
        this.passenger = owner;
        this.policy = policy;
        ID++;
        this.ticketReceipt = new Receipt(ID, owner.getName().toString(), cardNumber, this);
    }

    public Flights getFlight(){
        return this.flight;
    }

    public Seat getSeat(){
        return this.seat;
    }

    public Passenger getPassenger(){
        return this.passenger;
    }

    public Receipt getReceipt(){
        return this.ticketReceipt;
    }

    public String Display(){
        return "Ticket ID: " + Integer.toString(ID)
        + "Flight: " + this.flight.getFlightNum() 
        + "Seat: " + this.seat.Display() 
        + "Passenger: " + this.passenger.getName().toString() 
        + "Insurance Policy: " + (this.policy != null ? this.policy.getPolicy() : "No policy");
    }
}
