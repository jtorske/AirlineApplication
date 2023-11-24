package Domains.Tickets;

public class Insurance {
    private String policy;

    public Insurance(){
        this.policy = "Basic";
    }

    public Insurance(String policyType){
        this.policy = policyType;
    }

    public String getPolicy(){
        return this.policy;
    }
}
