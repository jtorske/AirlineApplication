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

public class OrdinarySeat implements Seat{
    double Price = 50.00;
    int SeatNum;
    int SeatRow;
    char SeatColumn;
    boolean isBooked;

    public OrdinarySeat(int SeatNum, int SeatRow, char SeatColumn){
        this.SeatNum = SeatNum;
        this.SeatRow = SeatRow;
        this.SeatColumn = SeatColumn;
        isBooked = false;
    }
    public OrdinarySeat(){
        SeatNum = 0;
        SeatRow = 0;
        SeatColumn = ' ';
        isBooked = false;
    }
    public OrdinarySeat(int SeatNum, int SeatRow, char SeatColumn, boolean isBooked){
        this.SeatNum = SeatNum;
        this.SeatRow = SeatRow;
        this.SeatColumn = SeatColumn;
        this.isBooked = isBooked;
    }
    @Override
    public double GetPrice(){
        return Price;
    }
    @Override
    public int getRow(){return SeatRow;}
    @Override
    public char getColumn(){return SeatColumn;}
    @Override
    public boolean booked(){return isBooked;}
    @Override
    public void Book(){isBooked = !isBooked;}
    @Override
    public String Display(){
        return  Integer.toString(SeatRow) + Character.toString(SeatColumn);
    }
}
