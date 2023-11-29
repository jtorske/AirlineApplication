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
import Domains.Flights.Location;

public class Address extends Location{
    private String streetName;
    private String streetNumber;
    private String postalCode;

    public Address(String Country, String ProvDist, String City, String streetName, String streetNumber, String postalCode){
        super(Country, ProvDist, City);
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
    }
    public Address(String streetName, String streetNumber, String postalCode){
        super();
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
    }
    public Address(){
        super();
        this.streetName = "";
        this.streetNumber = "";
        this.postalCode = "";
    }
    public void setStreetName(String streetName){this.streetName = streetName;}
    public void setStreetNumber(String streetNumber){this.streetNumber = streetNumber;}
    public void setPostalCode(String postalCode){this.postalCode = postalCode;}
    
    public String getStreetName(){return streetName;}
    public String getStreetNumber(){return streetNumber;}
    public String getPostalCode(){return postalCode;}
    public String toString(){return streetNumber + " " + streetName + ", " + super.toString() + ", " + postalCode;}
}
