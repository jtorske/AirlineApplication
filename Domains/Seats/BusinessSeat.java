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

public class BusinessSeat implements Seat{
    double Price = 100.00;
    int SeatNum;
    int SeatRow;
    char SeatColumn;
    boolean isBooked;

    public BusinessSeat(int SeatNum, int SeatRow, char SeatColumn){
        this.SeatNum = SeatNum;
        this.SeatRow = SeatRow;
        this.SeatColumn = SeatColumn;
        isBooked = false;
    }
    @Override
    public double GetPrice(){
        return Price;
    }
    @Override
    public void Book(){isBooked = !isBooked;}
    @Override
    public int getRow(){return SeatRow;}
    @Override
    public char getColumn(){return SeatColumn;}
    @Override
    public boolean booked(){return isBooked;}
    @Override
    public String Display(){
        return  Integer.toString(SeatRow) + Character.toString(SeatColumn);
    }
}
