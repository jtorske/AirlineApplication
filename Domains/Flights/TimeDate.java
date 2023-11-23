/*
@author 
Chun Lok Chan
Jordan Torske
Mohamad Hussein
Logan Nightingale
@version: 1.0
@since: 2023-11-23
 */

package Domains.Flights;
import java.time.LocalDateTime;

public class TimeDate {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public TimeDate(int year, int month, int day, int hour, int minute){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
    public TimeDate(){
        LocalDateTime now = LocalDateTime.now();
        this.year = now.getYear();
        this.month = now.getMonthValue();
        this.day = now.getDayOfMonth();
        this.hour = now.getHour();
        this.minute = now.getMinute();
    }
    public int getYear(){return year;}
    public int getMonth(){return month;}
    public int getDay(){return day;}
    public int getHour(){return hour;}
    public int getMinute(){return minute;}
    public String toString(){return year + "-" + month + "-" + day + " " + hour + ":" + minute;}
    public boolean compareDay(int year, int month, int day){
        return (this.year == year && this.month == month && this.day == day);
    }
    public boolean compareTime(int hour, int minute){
        return (this.hour == hour && this.minute == minute);
    }
}
