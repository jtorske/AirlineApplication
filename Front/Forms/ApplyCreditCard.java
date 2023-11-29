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

import Database.Database.Database;
import Domains.User.CreditCard;
import Domains.User.RegisteredUser;

public class ApplyCreditCard implements Forms{
    static int cardID = 6;

    public static String ApplyforCreditCard(String userID, RegisteredUser user){
        try{
            CreditCard card = new CreditCard(user);
            List<String> cardValues = List.of(String.valueOf(cardID), userID, card.getCardNum(), card.getCvvNum(), card.getExpDate());
            Database.dbInsert("CreditCard", cardValues);
            cardID++;
        }
        catch (Exception e){
            return "Fail to apply for credit card";
        }
        return "Card will be sent to your address";
    }   
}
