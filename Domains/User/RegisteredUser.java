package Domains.User;
import Domains.Forms.*;
import Domains.Passenger.*;

public class RegisteredUser extends User{
    private String ID;
    private String Username;
    private String Password;
    private CreditCard Card;
    private Name name;
    private Address address;
    private String email;

    public RegisteredUser(String id, String user, String pass, Name name, Address addr, String email){
        this.ID = id;
        this.Username = user;
        this.Password = pass;
        this.name = name;
        this.address = addr;
        this.email = email;
        //TODO: Send the registered user's info to the database
    }

    public String RegisterForCreditCard(){
        String applicationResult = ApplyCreditCard.ApplyforCreditCard(name, address, email);
        if(applicationResult == "Card will be sent to your address"){
            this.Card = new CreditCard(this);
        }
        else{
            this.Card = null;
        }
        return applicationResult;
    }

    public String getID() {return this.ID;}
    public String getUsername() {return this.Username;}
    public String getPassword() {return this.Password;}
    public String getName() {return this.name.toString();}
    public String getCard() {return this.Card != null ? this.Card.Display() : "No credit card registered";}
}
