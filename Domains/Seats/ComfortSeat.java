/*
@author 
Chun Lok Chan
Jordan Torske
Logan Nightingale
Mohamad Hussein
@version: 1.0
@since: 2023-11-23
*/

package Domains.Seats;

public class ComfortSeat implements Seat{
    double Price = 70.00;
    int SeatNum;
    char SeatRow;
    int SeatColumn;
    boolean isBooked;

    public ComfortSeat(int SeatNum, char SeatRow, int SeatColumn){
        this.SeatNum = SeatNum;
        this.SeatRow = SeatRow;
        this.SeatColumn = SeatColumn;
        isBooked = false;
    }
    @Override
    public int getRow(){return SeatNum;}
    @Override
    public char getColumn(){return SeatRow;}
    public double GetPrice(){
        return Price;
    }
    @Override
    public boolean booked(){return isBooked;}
    @Override
    public String Display(){
        return Integer.toString(SeatNum) + SeatRow + Integer.toString(SeatColumn);
    }
}
