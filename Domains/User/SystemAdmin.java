package Domains.User;

import java.util.ArrayList;
import java.util.List;

import Database.Database.Database;

public class SystemAdmin extends User{
    private String Username;
    private String Password;

    public SystemAdmin(String username){
        this.Username = username;
        List<List<String>> res = Database.dbExecute("select Password from admin where Username = " + "\"" + username + "\"");
        this.Password = res.get(0).get(0);
    }

    public String getUsername() {return this.Username;}
    public String getPassword() {return this.Password;}

    public ArrayList<CrewMember> getCrewList(){
        ArrayList<CrewMember> ret = new ArrayList<CrewMember>();

        List<List<String>> cMembers = Database.dbExecute("select * from CrewMember");

        for(int i = 0; i < cMembers.size(); i++){
            List<String> curr = cMembers.get(i);
            System.out.println(curr.get(1));
            String[] n = curr.get(1).split(" ");
            CrewMember cm = new CrewMember(n[0], n[1], n[2], curr.get(2), curr.get(0), this.Password);
            ret.add(cm);
        }

        return ret;
    }
}
