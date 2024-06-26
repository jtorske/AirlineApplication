/*
@author 
Chun Lok Chan
Jordan Torske
Mohamad Hussein
Logan Nightingale
@version: 1.0
@since: 2023-11-23
 */
package Database.Login;

import java.util.List;

import Database.Database.Database;

public class Verify {
    static public boolean verifyUser(String username, String password) {
        // connect to database to check if username and password are correct
        String query = String.format("SELECT * FROM User WHERE Username = \"%s\" AND Password = \"%s\"", username,
                password);
        List<List<String>> result = Database.dbExecute(query);
        if (result.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    static public boolean verifyAdmin(String username, String password) {
        // connect to database to check if username and password are correct
        String query = String.format("SELECT * FROM Admin WHERE Username = \"%s\" AND Password = \"%s\"", username,
                password);
        List<List<String>> result = Database.dbExecute(query);
        if (result.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    static public boolean verifyCrewMember(String username, String password) {
        // connect to database to check if username and password are correct
        String query = String.format("SELECT * FROM CrewMember WHERE Username = \"%s\" AND Password = \"%s\" AND Role = \"Flight Attendant\"", 
                username,
                password);
        List<List<String>> result = Database.dbExecute(query);
        if (result.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
