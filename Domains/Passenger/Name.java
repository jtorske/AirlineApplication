/*
@author 
Chun Lok Chan
Jordan Torske
Mohamad Hussein
Logan Nightingale
@version: 1.0
@since: 2023-11-23
 */

package Domains.Passenger;

public class Name {
    private String firstName;
    private String middleName;
    private String lastName;
    
    public Name(String firstName, String middleName, String lastName){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
    public Name(){
        this.firstName = "";
        this.middleName = "";
        this.lastName = "";
    }
    
    public void setFirstName(String firstName){this.firstName = firstName;}
    public void setMiddleName(String middleName){this.middleName = middleName;}
    public void setLastName(String lastName){this.lastName = lastName;}
    public String getFirstName(){return firstName;}
    public String getMiddleName(){return middleName;}
    public String getLastName(){return lastName;}
    
    public String toString(){
        return firstName + " " + middleName.charAt(0) + " " + lastName;
    }
}
