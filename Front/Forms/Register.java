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
import java.util.List;

import Database.Database.*;
import Domains.Passenger.*;

public class Register implements Forms{
    static int membershipNum = 6;
    static void setMembershipNum(int num){
        membershipNum = num;
    }

    public static String RegisterMembership(Name name, Address address, String email, String password){
        try{
            //go to database and get highest number of the ticketID
            int memNum = 0;
            //dbexecute
            String query = String.format("SELECT MAX(UserID) FROM user");
            List<List<String>> dbResult = Database.dbExecute(query);
            for (List<String> row : dbResult) {
                memNum = Integer.parseInt(row.get(0));
            }
            memNum++;
            setMembershipNum(memNum);
            //prepare values for insert
            String mn = String.valueOf(membershipNum);
            List<String> nameValues = List.of(mn, name.getFirstName(), name.getLastName(), name.getMiddleName());
            Database.dbInsert("Name", nameValues);

            List<String> userValues = List.of(mn, mn, email, password);
            //insert into database
            Database.dbInsert("User", userValues);
            membershipNum++;
        }
        catch (Exception e){
            return "User duplicate";
        }
        return String.valueOf(membershipNum);
    }
    
}
