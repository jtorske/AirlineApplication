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

    private String insertHyphen(String input, int interval) {
        StringBuilder result = new StringBuilder(input);

        for (int i = interval; i < result.length(); i += interval + 1) {
            result.insert(i, '-');
        }

        return result.toString();
    }

    public CreditCard(RegisteredUser owner){
        this.owner = owner;
        String numbers = RandomNum(16);
        this.cardNum = insertHyphen(numbers, 4);
        this.cvvNum = RandomNum(3);
        TimeDate td = new TimeDate();
        this.expDate = td.displayDate().replace('/', '-');
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
