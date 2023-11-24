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
import java.lang.reflect.Array;
import java.util.ArrayList;

import Domains.Passenger.*;

public class Flights {
    private String flightNumber;
    private TimeDate departureDate;
    private TimeDate arrivalDate;
    private Location departureLocation;
    private Location arrivalLocation;
    private Aircraft aircraft;
    private ArrayList<Passenger> passengerList;

    public String getFlightNum(){
        return this.flightNumber;
    }
}
