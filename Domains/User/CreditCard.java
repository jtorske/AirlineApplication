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

import java.util.Random;

import Domains.Flights.TimeDate;

public class CreditCard {
    private String cardNum;
    private String cvvNum;
    private String expDate;
    private RegisteredUser owner;

    private String RandomNum(int range){
        //Helper function to generate random numbers as a string within a range
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(range);
        for (int i = 0; i < range; i++) {
            //Append a random digit (0-9) to the string
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

    public CreditCard(RegisteredUser owner){
        this.owner = owner;
        this.cardNum = RandomNum(16);
        this.cvvNum = RandomNum(3);
        TimeDate td = new TimeDate();
        this.expDate = td.getMonth() + "/" + td.getYear();
    }

    public String Display(){
        return "Credit card registered to: " + owner.getName()
        + "\nCard number: " + cardNum
        + "\nCVV number: " + cvvNum
        + "\nExpiration Date: " + expDate;
    }

    public String getCardNum() {return this.cardNum;}
    public String getCvvNum() {return this.cvvNum;}
    public String getExpDate() {return this.expDate;}
}
