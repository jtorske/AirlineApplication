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
    public Name getName(){return name;}
    public String getRole(){return role;}
    public String getId(){return id;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
    public String toString(){return "Crew member\nName:"+name + "\n Role:"+role + "\n ID:"+id+"\n";}
    public ArrayList<Passenger> BrowsePassengers(String flightNum){ 
        //TODO: Connect to the dabaase and return the list of passengers
        ArrayList<Passenger> passengers = new ArrayList<Passenger>();
        return passengers;
    }
}
