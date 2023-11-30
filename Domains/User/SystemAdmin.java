package Domains.User;

import java.util.ArrayList;
import java.util.List;

import Database.Database.Database;
import Domains.Flights.Aircraft;
import Domains.Flights.Flights;
import Domains.Flights.Location;
import Domains.Flights.TimeDate;

public class SystemAdmin extends User{
    private String Username;
    private String Password;

    public SystemAdmin(String username){
        this.Username = username;
        List<List<String>> res = Database.dbExecute("select Password from admin where Username = " + "\"" + username + "\"");
        this.Password = res.get(0).get(0);
    }

    public String getUsername() {return this.Username;}
    public String getPassword() {return this.Password;}

    public ArrayList<CrewMember> getCrewList(){
        ArrayList<CrewMember> ret = new ArrayList<CrewMember>();
        List<List<String>> cMembers = Database.dbExecute("select * from CrewMember");

        for(int i = 0; i < cMembers.size(); i++){
            List<String> curr = cMembers.get(i);
            String[] n = curr.get(1).split(" ");
            CrewMember cm = new CrewMember(n[0], n[1], n[2], curr.get(2), curr.get(0), this.Password);
            ret.add(cm);
        }

        return ret;
    }

    public ArrayList<Flights> getFlightList(){
        ArrayList<Flights> ret = new ArrayList<Flights>();
        List<List<String>> fList = Database.dbExecute("select * from Flight");

        for(int i = 0; i < fList.size(); i++){
            List<String> curr = fList.get(i);
            Flights toAdd = new Flights();
            toAdd.setFlightNum(curr.get(0));
            //construct dates
            toAdd.setDepartureDate(new TimeDate(curr.get(4)));
            toAdd.setArrivalDate(new TimeDate(curr.get(5)));
            //construct locations
            List<String> depLocation = Database.dbExecute("select * from Location where LocationID = " + curr.get(2)).get(0);
            List<String> arrLocation = Database.dbExecute("select * from Location where LocationID = " + curr.get(3)).get(0);
            toAdd.setDepartureLocation(new Location(depLocation.get(1), depLocation.get(2), depLocation.get(3)));
            toAdd.setArrivalLocation(new Location(arrLocation.get(1), arrLocation.get(2), arrLocation.get(3)));
            //construct aircraft
            List<String> airC = Database.dbExecute("select * from Aircraft where AircraftID = " + curr.get(1)).get(0);
            toAdd.setAircraft(new Aircraft(airC.get(0), airC.get(1), airC.get(2), Integer.parseInt(airC.get(3)), Integer.parseInt(airC.get(4))));

            ret.add(toAdd);
        }

        return ret;
    }

    public ArrayList<Aircraft> getAircraftList(){
        ArrayList<Aircraft> ret = new ArrayList<Aircraft>();

        List<List<String>> aList = Database.dbExecute("select * from Aircraft");

        for(int i = 0; i < aList.size(); i++){
            List<String> curr = aList.get(i);
            Aircraft toAdd = new Aircraft(curr.get(0), curr.get(1), curr.get(2), Integer.parseInt(curr.get(3)), Integer.parseInt(curr.get(4)));
            ret.add(toAdd);
        }

        return ret;
    }

    public void addCrewMember(String name, String role, String username, String password){
        //Check for valid entries
        if(name.split(" ").length != 3){
            System.out.println("Invalid name provided");
            return;
        }

        List<List<String>> prevEntries = Database.dbExecute("select * from CrewMember");
        List<String> values = new ArrayList<String>();
        values.add(String.valueOf(prevEntries.size() + 1));
        values.add(name);
        values.add(role);
        values.add(username);
        values.add(password);
        Database.dbInsert("CrewMember", values);
    }

    public void removeCrewMember(String id){
        Database.dbDelete("CrewSchedule", "CrewMemberID", id);
        Database.dbDelete("CrewMember", "CrewMemberID", id);
    }

    public void addFlight(String aircraftID, String depID, String arrID, String depDate, String arrDate){
        List<List<String>> prevEntries = Database.dbExecute("select * from Flights");
        List<String> values = new ArrayList<String>();
        values.add(String.valueOf(prevEntries.size() + 1));
        values.add(aircraftID);
        values.add(depID);
        values.add(arrID);
        values.add(depDate);
        values.add(arrDate);
        Database.dbInsert("Flight", values);
    }

    public void removeFlight(String id){
        Database.dbDelete("Ticket", "FlightID", id);
        Database.dbDelete("CrewSchedule", "FlightID", id);
        Database.dbDelete("Flight", "FlightID", id);
    }
}
