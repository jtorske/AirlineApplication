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

    public ComfortSeat(int SeatNum, char SeatRow, int SeatColumn){
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
