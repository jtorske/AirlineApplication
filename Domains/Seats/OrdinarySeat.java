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
    char SeatRow;
    int SeatColumn;

    public OrdinarySeat(int SeatNum, char SeatRow, int SeatColumn){
        this.SeatNum = SeatNum;
        this.SeatRow = SeatRow;
        this.SeatColumn = SeatColumn;
    }

    public double GetPrice(){
        return Price;
    }

    @Override
    public String Display(){
        return Integer.toString(SeatNum) + SeatRow + Integer.toString(SeatColumn);
    }
}
