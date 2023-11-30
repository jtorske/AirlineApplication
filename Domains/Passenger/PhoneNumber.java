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
    public PhoneNumber(){
        this.countryCode = 0;
        this.areaCode = 0;
        this.phoneNumber = 0;
    }
    public PhoneNumber(String phoneNumber){
        this.countryCode = Integer.parseInt(phoneNumber.substring(0,2));
        this.areaCode = Integer.parseInt(phoneNumber.substring(3,5));
        this.phoneNumber = Integer.parseInt(phoneNumber.substring(6));
    }
    public void setCountryCode(int countryCode){this.countryCode = countryCode;}
    public void setAreaCode(int areaCode){this.areaCode = areaCode;}
    public void setPhoneNumber(int phoneNumber){this.phoneNumber = phoneNumber;}
    public int getCountryCode(){return countryCode;}
    public int getAreaCode(){return areaCode;}
    public int getPhoneNumber(){return phoneNumber;}
    public String toString(){
        return "+" + countryCode + " " + areaCode + " " + phoneNumber;
    }
}
