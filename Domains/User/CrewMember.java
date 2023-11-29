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

import java.util.ArrayList;
import java.util.List;

import Database.Database.Database;
import Domains.Passenger.*;



public class CrewMember {
    private Name name;
    private String role;
    private String id;
    private String password;

    public CrewMember(String firstname, String middlename, String lastname, String role, String id, String password){
        this.name = new Name(firstname, middlename, lastname);
        this.role = role;
        this.id = id;
        this.password = password;
    }
    public CrewMember(){
        this.name = new Name();
        this.role = "";
        this.id = "";
        this.password = "";
    }

    public void setName(String firstname, String middlename, String lastname){this.name = new Name(firstname, middlename, lastname);}
    public void setRole(String role){this.role = role;}
    public void setId(String id){this.id = id;}
    public Name getName(){return name;}
    public String getRole(){return role;}
    public String getId(){return id;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
    public String toString(){return "Crew member\nName:"+name + "\n Role:"+role + "\n ID:"+id+"\n";}
    static public ArrayList<Passenger> BrowsePassengers(String flightNum){ 
        int fn = Integer.valueOf(flightNum);
        //Query database to get relevant information
        List<List<String>> flightNames = Database.dbExecute(
        "select n.FirstName, n.MiddleName, n.LastName " 
        + "from ticket as t, passenger as p, name as n "
        + "where t.FlightID = " + fn + " and t.PassengerID = p.PassengerID and p.NameID = n.NameID"
        );
        List<List<String>> flightAddresses = Database.dbExecute(
        "select a.StreetName, a.District, a.PostalCode " 
        + "from ticket as t, passenger as p, address as a "
        + "where t.FlightID = " + fn + " and t.PassengerID = p.PassengerID and p.AddressID = a.AddressID"
        );
        List<List<String>> flightPassports = Database.dbExecute(
        "select b.IssueCountry, b.IssueDate, b.ExpiryDate " 
        + "from ticket as t, passenger as p, passport as b "
        + "where t.FlightID = " + fn + " and t.PassengerID = p.PassengerID and p.PassportID = b.PassportID"
        );
        List<List<String>> flightPhones = Database.dbExecute(
        "select c.CountryCode, c.DistrictCode, c.Number "
        + "from ticket as t, passenger as p, phone as c "
        + "where t.FlightID = " + fn + " and t.PassengerID = p.PassengerID and p.PhoneID = c.PhoneID"
        );

        ArrayList<Passenger> passengers = new ArrayList<Passenger>();

        //Construct passenger objects
        for(int i = 0; i < flightNames.size(); i++){
            List<String> currName = flightNames.get(i);
            List<String> currAddr = flightAddresses.get(i);
            List<String> currPassp = flightPassports.get(i);
            List<String> currPhone = flightPhones.get(i);

            Name n = new Name(currName.get(0), currName.get(2), currName.get(1));

            Address a = new Address(currAddr.get(0), currAddr.get(1), currAddr.get(2));

            String[] expiry = currPassp.get(2).split("-");
            String[] issue = currPassp.get(1).split("-");
            Passport pt = new Passport("12345", currPassp.get(0), 
            Integer.valueOf(expiry[0]), Integer.valueOf(expiry[1]), Integer.valueOf(expiry[2]), 
            Integer.valueOf(issue[0]), Integer.valueOf(issue[1]), Integer.valueOf(issue[2]), 
            currName.get(0), currName.get(1), currName.get(2));

            PhoneNumber pn = new PhoneNumber(Integer.valueOf(currPhone.get(0).substring(1)), 
            Integer.valueOf(currPhone.get(1)), 
            Integer.valueOf(currPhone.get(2).replace("-", "")));

            Passenger p = new Passenger(n, pt, a, "me@email.com", pn);
            passengers.add(p);
        }

        return passengers;
    }
}
