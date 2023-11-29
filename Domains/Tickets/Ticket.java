/*
@author 
Chun Lok Chan
Jordan Torske
Mohamad Hussein
Logan Nightingale
@version: 1.0
@since: 2023-11-23
 */

package Domains.Tickets;
import Domains.Seats.Seat;

import javax.mail.MessagingException;

import Domains.Flights.Flights;
import Domains.Passenger.Passenger;

public class Ticket {
    private static int TicNum = 0;
    private int ID;
    private Flights flight;
    private Seat seat;
    private Passenger passenger;
    private Insurance policy;
    private double price;
    private Receipt ticketReceipt;
    //private final Receipt ticketReceipt;
    public Ticket(Flights flight, Seat seat, Passenger owner, String cardNumber){
        this.flight = flight;
        this.seat = seat;
        price = seat.GetPrice();
        this.passenger = owner;
        this.policy = null;
        this.ID=TicNum++;
        this.ticketReceipt = new Receipt(TicNum, owner.getName().toString(), cardNumber, this);
    }
    public Ticket(){
        this.ID = 0;
        this.flight = null;
        this.seat = null;
        this.passenger = null;
        this.policy = null;
        this.ticketReceipt = null;
    }

    public Ticket(Flights flight, Seat seat, Passenger owner, Insurance policy, String cardNumber){
        this.flight = flight;
        this.seat = seat;
        this.passenger = owner;
        this.policy = policy;
        this.price = seat.GetPrice();
        this.ID=TicNum++;
        this.ticketReceipt = new Receipt(TicNum+Integer.parseInt(cardNumber.substring(0,4)), owner.getName().toString(), cardNumber, this);
    }

    public int getID(){return this.ID;}
    public void setID(int ID){this.ID = ID;}
    public void setFlight(Flights flight){this.flight = flight;}
    public void setSeat(Seat seat){this.seat = seat;}
    public void setPassenger(Passenger passenger){this.passenger = passenger;}
    public void setPolicy(Insurance policy){this.policy = policy;}
    public void setPrice(double price){this.price = price;}
    public void setReceipt(Receipt ticketReceipt){this.ticketReceipt = ticketReceipt;}

    static public void setTicNum(int num){TicNum = num;}
    
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
        return "Ticket ID: " + Integer.toString(TicNum)
        + "Flight: " + this.flight.getFlightNum() 
        + "Seat: " + this.seat.Display() 
        + "Price: " + Double.toString(this.price)
        + "Passenger: " + this.passenger.getName().toString() 
        + "Insurance Policy: " + (this.policy != null ? this.policy.getPolicy() : "No policy");
    }

    public void Email() throws MessagingException{
        //Send the ticket via email
        String ticketInfo = Display();
        Email email = new Email("Airline Ticket", ticketInfo);
        System.out.println("Sending email to " + this.passenger.getEmail());
        email.send(this.passenger.getEmail());
    }
}
