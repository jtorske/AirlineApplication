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
import java.util.Date;
import java.time.ZoneId;

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
    public TimeDate(String dbDate) {
        this.year = Integer.parseInt(dbDate.substring(0, 4));
        this.month = Integer.parseInt(dbDate.substring(5, 7));
        this.day = Integer.parseInt(dbDate.substring(8, 10));
        this.hour = Integer.parseInt(dbDate.substring(11, 13));
        this.minute = Integer.parseInt(dbDate.substring(14, 16));
    }

    public TimeDate(Date departureDate) {
        this.year = departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
        this.month = departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
        this.day = departureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth();
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
    public String displayDate(){
        return year + "/" + month + "/" + day;
    }
    public String displayTime(){
        return hour + ":" + minute;
    }
}
