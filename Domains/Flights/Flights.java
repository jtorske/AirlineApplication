/*
@author 
Chun Lok Chan
Jordan Torske
Mohamad Hussein
Logan Nightingale
@version: 1.0
@since: 2023-11-23
 */

package Domains.Flights;
import java.util.ArrayList;

import Domains.Passenger.*;
import Domains.Seats.*;
import Domains.User.CrewMember;

public class Flights {
    private String flightNumber;
    private TimeDate departureDate;
    private TimeDate arrivalDate;
    private Location departureLocation;
    private Location arrivalLocation;
    private Aircraft aircraft;
    private ArrayList<Passenger> passengerList;
    private ArrayList<Seat> seatList;
    private ArrayList<CrewMember> crewList;

    public Flights(String flightNumber, TimeDate departureDate, TimeDate arrivalDate, Location departureLocation, Location arrivalLocation, Aircraft aircraft){
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.aircraft = aircraft;
        this.passengerList = new ArrayList<Passenger>();
        this.seatList = new ArrayList<Seat>();
        this.crewList = new ArrayList<CrewMember>();
        if (aircraft.getType()==1){ //if aircraft is a small plane, like a 737, only have odinary seats, 30 rows of 6 seats
            for (int i = 1; i <= 30; i++){
                for (int j = 1; j <= 6; j++){
                    seatList.add(new OrdinarySeat(i, (char)(j+64), j));
                }
            }
        }
        else if (aircraft.getType()==2){ //if aircraft is a large plane, like a 777
            //have 5 rows of 6seats for bussiness
            for (int i = 1; i <= 5; i++){
                for (int j = 1; j <= 6; j++){
                    seatList.add(new BusinessSeat(i, (char)(j+64), j));
                }
            }
            //have 4 rows of 6 seats for comfort
            for (int i = 6; i <= 9; i++){
                for (int j = 1; j <= 6; j++){
                    seatList.add(new ComfortSeat(i, (char)(j+64), j));
                }
            }
            //have 29 rows of 9 seats for ordinary
            for (int i = 10; i <= 38; i++){
                for (int j = 1; j <= 9; j++){
                    seatList.add(new OrdinarySeat(i, (char)(j+64), j));
                }
            }
        }
    }

    public String getFlightNum(){return this.flightNumber;}
    public TimeDate getDepartureDate(){return this.departureDate;}
    public TimeDate getArrivalDate(){return this.arrivalDate;}
    public Location getDepartureLocation(){return this.departureLocation;}
    public Location getArrivalLocation(){return this.arrivalLocation;}
    public Aircraft getAircraft(){return this.aircraft;}
    public ArrayList<Passenger> getPassengerList(){return this.passengerList;}
    public ArrayList<Seat> getSeatList(){return this.seatList;}
    
    public String getSeatMapString(){ //each seat separate by |, new row separate by \n
        String seatMap = "";
        int row = 0;
        for (int i = 0; i < seatList.size(); i++){
            if (seatList.get(i).getRow() != row){
                seatMap += "\n";
                row = seatList.get(i).getRow();
            }
            if (seatList.get(i).booked()){
                seatMap += "|X";
            }
            else{
                seatMap += "|O";
            }
            seatMap += seatList.get(i).Display();
        }
        return seatMap;
    }
    public void addPassenger(Passenger passenger){this.passengerList.add(passenger);}
    public void addCrewMember(CrewMember crewMember){this.crewList.add(crewMember);}

}
