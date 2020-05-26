package Domain.System;//roei cohen
import Domain.Events.*;
import Domain.User.*;
import Domain.Jobs.*;
import Domain.Game.*;
import Domain.Association.*;

import java.util.HashMap;
import java.util.Scanner;

public class Login {

    public Member loginToSystem(String user_name, String password){
        Member logged_in_member = (Member)AlphaSystem.getSystem().GetSpecificFromDB(2,user_name);
        if(logged_in_member==null){
            return null;
        }
        if(!password.equals(logged_in_member.getUser_password())) {
            return null;
        }
        logged_in_member.setOnline(true);
        return logged_in_member;
    }
}
