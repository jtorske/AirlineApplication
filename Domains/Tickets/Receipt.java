package Domains.Tickets;

import Domains.Flights.TimeDate;

public class Receipt {
    private int receiptNum;
    private String userName;
    private TimeDate transactionTime;
    private int cardNum;

    public Receipt(int num, String name, int cardNum){
        this.receiptNum = num;
        this.userName = name;
        this.cardNum = cardNum;
        this.transactionTime = new TimeDate();
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
        + ", with passenger: " + userName
        + ", and card: " + cardString
        + ". Dated: " + transactionTime.toString();
    }

    public void Email(){
        //Send the ticket receipt via email
        String receiptInfo = Display();
        //TODO: implement this function
    }
}
