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
    int SeatRow;
    char SeatColumn;
    boolean isBooked;

    public ComfortSeat(int SeatNum, int SeatRow, char SeatColumn){
        this.SeatNum = SeatNum;
        this.SeatRow = SeatRow;
        this.SeatColumn = SeatColumn;
        isBooked = false;
    }
    @Override
    public int getRow(){return SeatNum;}
    @Override
    public char getColumn(){return SeatColumn;}
    @Override
    public double GetPrice(){
        return Price;
    }
    @Override
    public void Book(){isBooked = !isBooked;}
    @Override
    public boolean booked(){return isBooked;}
    @Override
    public String Display(){
        return  Integer.toString(SeatRow) + Character.toString(SeatColumn);
    }
}
