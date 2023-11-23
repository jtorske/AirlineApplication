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

public class Passenger {
    private Name name;
    private Passport passport;
    private Address address;
    private String email;
    private PhoneNumber phoneNumber;

    public Passenger(String firstName, String middleName, String lastName, String passportNumber, 
        String country, int expiryYear, int expiryMonth, int expiryDay, 
        int issueYear, int issueMonth, int issueDay, String streetNumber, String streetName, 
        String city, String province, String countryAddress, String postalCode, String email, 
        int countryCode, int areaCode, int phoneNumber){
        this.name = new Name(firstName, middleName, lastName);
        this.passport = new Passport(passportNumber, country, expiryYear, expiryMonth, expiryDay, issueYear, issueMonth, issueDay, firstName, middleName, lastName);
        this.address = new Address(streetNumber, streetName, city, province, countryAddress, postalCode);
        this.email = email;
        this.phoneNumber = new PhoneNumber(countryCode,areaCode, phoneNumber);
    }
    public Name getName(){return name;}
    public Passport getPassport(){return passport;}
    public Address getAddress(){return address;}
    public String getEmail(){return email;}
    public String getPhoneNumber(){return phoneNumber.toString();}   
}
