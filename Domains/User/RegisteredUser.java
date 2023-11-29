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
import Front.Forms.*;
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
        this.Username = user;
        this.Password = pass;
        this.name = name;
        this.address = addr;
        this.email = email;
        this.ID = Register.RegisterMembership(name, addr, email, pass);
    }

    public String RegisterForCreditCard(){
        String applicationResult = ApplyCreditCard.ApplyforCreditCard(this.ID, this);
        return applicationResult;
    }

    public String getID() {return this.ID;}
    public String getUsername() {return this.Username;}
    public String getPassword() {return this.Password;}
    public String getName() {return this.name.toString();}
    public String getCard() {return this.Card != null ? this.Card.Display() : "No credit card registered";}
}
