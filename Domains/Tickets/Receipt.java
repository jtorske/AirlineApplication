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

import javax.mail.MessagingException;

import Domains.Flights.TimeDate;

public class Receipt {
    private int receiptNum;
    private String userName;
    private TimeDate transactionTime;
    private String cardNum;
    private Ticket ticket;

    public Receipt(int num, String name, String cardNum, Ticket ticket){
        this.receiptNum = num;
        this.userName = name;
        this.cardNum = cardNum;
        this.transactionTime = new TimeDate();
        this.ticket = ticket;
    }
    public Receipt(){
        this.receiptNum = 0;
        this.userName = "";
        this.cardNum = "";
        this.transactionTime = new TimeDate();
        this.ticket = null;
    }
    public int getReceiptNum(){return receiptNum;}
    public String getUserName(){return userName;}
    public TimeDate getTransactionTime(){return transactionTime;}
    public String getCardNum(){return cardNum;}
    public Ticket getTicket(){return ticket;}

    public void setReceiptNum(int num){this.receiptNum = num;}
    public void setUserName(String name){this.userName = name;}
    public void setTransactionTime(TimeDate time){this.transactionTime = time;}
    public void setCardNum(String num){this.cardNum = num;}
    public void setTicket(Ticket ticket){this.ticket = ticket;}

    public String Display(){
        String cardString = this.cardNum;
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

    public void Email() throws MessagingException{
        //Send the ticket receipt via email
        String receiptInfo = Display();
        Email email = new Email("Airline Ticket Receipt", receiptInfo);
        email.send(this.ticket.getPassenger().getEmail());
    }
}
