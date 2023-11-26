/*
@author 
Chun Lok Chan
Jordan Torske
Mohamad Hussein
Logan Nightingale
@version: 1.0
@since: 2023-11-23
 */

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
