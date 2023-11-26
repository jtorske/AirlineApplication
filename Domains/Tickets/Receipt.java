/*
@author 
Chun Lok Chan
Jordan Torske
Mohamad Hussein
Logan Nightingale
@version: 1.0
@since: 2023-11-23
 */

package Domains.Tickets;

import Domains.Flights.TimeDate;

public class Receipt {
    private int receiptNum;
    private String userName;
    private TimeDate transactionTime;
    private int cardNum;
    private Ticket ticket;

    public Receipt(int num, String name, int cardNum, Ticket ticket){
        this.receiptNum = num;
        this.userName = name;
        this.cardNum = cardNum;
        this.transactionTime = new TimeDate();
        this.ticket = ticket;
    }

    public String Display(){
        String cardString = Integer.toString(this.cardNum);
        int length = cardString.length();

        if (length > 4) {
            String lastFourDigits = cardString.substring(length - 4);
            String mask = "*".repeat(length - 4);
            cardString = mask + lastFourDigits;
        }

        return "Receipt for ticket number: " + Integer.toString(this.receiptNum) 
        + "\n Passenger: " + userName
        + "\n Card: " + cardString
        + "\n Ticket Details: \n" + ticket.Display()
        + "\n Dated: " + transactionTime.toString();
    }

    public void Email(){
        //Send the ticket receipt via email
        String receiptInfo = Display();
        //TODO: implement this function
    }
}
