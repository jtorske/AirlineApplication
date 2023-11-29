package Database.Login;
import java.util.List;

import Database.Database.Database;

public class Verify {
    static public boolean verifyUser(String username, String password){
        //connect to database to check if username and password are correct
        List<List<String>> result = Database.dbExecute("select * from user"
        + " where username = " + username
        + " and password = " + password
        );
        if (result.size() > 0){
            return true;
        }
        else{
            return false;
        }
    }
    static public boolean verifyAdmin(String username, String password){
        //connect to database to check if username and password are correct
        List<List<String>> result = Database.dbExecute("select * from admin"
        + " where username = " + username
        + " and password = " + password
        );
        if (result.size() > 0){
            return true;
        }
        else{
            return false;
        }
    }
    static public boolean verifyCrewMember(String username, String password){
        //connect to database to check if username and password are correct
        List<List<String>> result = Database.dbExecute("select * from crewmember"
        + " where username = " + username
        + " and password = " + password
        );
        if (result.size() > 0){
            return true;
        }
        else{
            return false;
        }
    }
}
