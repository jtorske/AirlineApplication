package Domains.Passenger;

public class PhoneNumber {
    private int countryCode;
    private int areaCode;
    private int phoneNumber; 
    
    public PhoneNumber(int countryCode, int areaCode, int phoneNumber){
        this.countryCode = countryCode;
        this.areaCode = areaCode;
        this.phoneNumber = phoneNumber;
    }
    public String toString(){
        return "+" + countryCode + " " + areaCode + " " + phoneNumber;
    }
}
