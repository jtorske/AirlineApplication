/*
@author 
Chun Lok Chan
Jordan Torske
Mohamad Hussein
Logan Nightingale
@version: 1.0
@since: 2023-11-23
 */

package Front.Forms;

import Domains.Passenger.*;

public class ApplyCreditCard implements Forms{
    public static String ApplyforCreditCard(Name name, Address address, String email){
        try{
            //call the database to add the user
        }
        catch (Exception e){
            return "Fail to apply for credit card";
        }
        return "Card will be sent to your address";
    }   
}
