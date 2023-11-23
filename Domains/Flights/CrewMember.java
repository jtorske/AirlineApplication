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

import Domains.Passenger.Name;

public class CrewMember {
    private Name name;
    private String role;
    private String id;

    public CrewMember(String firstname, String middlename, String lastname, String role, String id){
        this.name = new Name(firstname, middlename, lastname);
        this.role = role;
        this.id = id;
    }
    public Name getName(){return name;}
    public String getRole(){return role;}
    public String getId(){return id;}
    public String toString(){return "Crew member\nName:"+name + "\n Role:"+role + "\n ID:"+id+"\n";}
}
