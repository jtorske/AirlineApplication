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

public class Aircraft {
    private String id;
    private String company;
    private String model;
    private int type;
    private int capacity;

    public Aircraft(String id, String company, String model, int capacity, int type){
        this.id = id;
        this.company = company;
        this.model = model;
        this.capacity = capacity;
        this.type = type;
    }
    public String getId(){return id;}
    public String getCompany(){return company;}
    public String getModel(){return model;}
    public int getCapacity(){return capacity;}
    public int getType(){return type;}
    public String toString(){return id + " " + company + " " + model + " " + capacity;}
    
}
