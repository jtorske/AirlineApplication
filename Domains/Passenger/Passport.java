package Domains.Passenger;
import java.util.Date;
import java.util.Calendar;

public class Passport {
    private String passportNumber;
    private String country;
    private Date expiryDate;
    private Date issueDate;
    private Name name;

    public Passport(String passportNumber, String country, int expiryYear, int expiryMonth, int expiryDay, int issueYear, int issueMonth, int issueDay, String firstName, String middleName, String lastName){
        this.passportNumber = passportNumber;
        this.country = country;

        Calendar expiryCalendar = Calendar.getInstance();
        expiryCalendar.set(expiryYear, expiryMonth, expiryDay);
        this.expiryDate = expiryCalendar.getTime();

        Calendar issueCalendar = Calendar.getInstance();
        issueCalendar.set(issueYear, issueMonth, issueDay);
        this.issueDate = issueCalendar.getTime();

        this.name = new Name(firstName, middleName, lastName);
    }
    public Passport(){
        this.passportNumber = "";
        this.country = "";
        this.expiryDate = new Date();
        this.issueDate = new Date();
        this.name = new Name();
    }
    public void setPassportNumber(String passportNumber){this.passportNumber = passportNumber;}
    public void setCountry(String country){this.country = country;}
    public void setExpiryDate(int expiryYear, int expiryMonth, int expiryDay){
        Calendar expiryCalendar = Calendar.getInstance();
        expiryCalendar.set(expiryYear, expiryMonth, expiryDay);
        this.expiryDate = expiryCalendar.getTime();
    }
    public void setIssueDate(int issueYear, int issueMonth, int issueDay){
        Calendar issueCalendar = Calendar.getInstance();
        issueCalendar.set(issueYear, issueMonth, issueDay);
        this.issueDate = issueCalendar.getTime();
    }
    public void setName(Name name){this.name = name;}
    
    public String getPassportNumber(){return passportNumber;}
    public String getCountry(){return country;}
    public Date getExpiryDate(){return expiryDate;}
    public Date getIssueDate(){return issueDate;}
    public Name getName(){return name;}
    public String toString(){
        return "Passport Number: " + passportNumber + "\nCountry: " + country + "\nExpiry Date: " + expiryDate + "\nIssue Date: " + issueDate + "\nName: " + name;
    }
}
