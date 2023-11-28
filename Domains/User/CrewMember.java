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
        //TODO: Connect to the dabaase and return the list of passengers
        ArrayList<Passenger> passengers = new ArrayList<Passenger>();
        return passengers;
    }
}
