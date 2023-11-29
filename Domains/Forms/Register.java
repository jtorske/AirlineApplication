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
import java.util.List;

import Domains.Database.Database;
import Domains.Passenger.*;

public class Register implements Forms{
    static int membershipNum = 6;

    public static String RegisterMembership(Name name, Address address, String email, String password){
        try{
            String mn = String.valueOf(membershipNum);
            List<String> nameValues = List.of(mn, name.getFirstName(), name.getLastName(), name.getMiddleName());
            Database.dbInsert("Name", nameValues);

            List<String> userValues = List.of(mn, mn, email, password);
            Database.dbInsert("User", userValues);
            membershipNum++;
        }
        catch (Exception e){
            return "User duplicate";
        }
        return String.valueOf(membershipNum);
    }
    
}
