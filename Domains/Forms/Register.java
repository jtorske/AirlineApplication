/*
@author 
Chun Lok Chan
Jordan Torske
Mohamad Hussein
Logan Nightingale
@version: 1.0
@since: 2023-11-23
 */

package Domains.Forms;
import Domains.Passenger.*;

public class Register implements Forms{
    static int membershipNum = 0;

    public static String RegisterMembership(Name name, Address address, String email, String password){
        try{
            //call the database to add the user
            membershipNum++;
        }
        catch (Exception e){
            return "User duplicate";
        }
        return String.valueOf(membershipNum);
    }
    
}
